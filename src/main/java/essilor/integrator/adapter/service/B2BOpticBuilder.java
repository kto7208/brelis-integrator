package essilor.integrator.adapter.service;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import essilor.integrator.adapter.dao.ConfDao;
import essilor.integrator.adapter.dao.CustomerDao;
import essilor.integrator.adapter.dao.OrderDao;
import essilor.integrator.adapter.domain.b2boptic.*;
import essilor.integrator.adapter.domain.b2boptic.Account.Id;
import essilor.integrator.adapter.domain.b2boptic.B2BOptic.Items;
import essilor.integrator.adapter.domain.b2boptic.Header.OrderParties;
import essilor.integrator.adapter.domain.b2boptic.Pair.Lens;
import essilor.integrator.adapter.domain.b2boptic.TimeStamps.DateTime;
import essilor.integrator.adapter.domain.optosys.Customer;
import essilor.integrator.adapter.domain.optosys.Order;
import essilor.integrator.adapter.domain.optosys.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.GregorianCalendar;

@Component
public class B2BOpticBuilder {

    private static ThreadLocal<DecimalFormat> decimalFormat = new ThreadLocal<DecimalFormat>() {
        @Override
        protected DecimalFormat initialValue() {
            DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
            symbols.setDecimalSeparator(',');
            symbols.setMinusSign('-');
            DecimalFormat format = new DecimalFormat();
            format.setDecimalFormatSymbols(symbols);
            return format;
        }
    };

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ConfDao confDao;

    @Autowired
    private CustomerDao customerDao;

    public class Builder {

        private String zakazka;
        private String skupina;
        private String objednavka;
        private String branchCode;
        private String originator;
        private String shipto;

        private String organisation;
        private String street;
        private String city;
        private String zipCode;

        public Builder() {
        }

        public Builder withZakazka(String zakazka) {
            this.zakazka = zakazka;
            return this;
        }

        public Builder withSkupina(String skupina) {
            this.skupina = skupina;
            return this;
        }

        public Builder withObjednavka(String objednavka) {
            this.objednavka = objednavka;
            return this;
        }

        public Builder withBranchCode(String branchCode) {
            this.branchCode = branchCode;
            return this;
        }

        public Builder withOriginator(String originator) {
            this.originator = originator;
            return this;
        }

        public Builder withShipto(String shipto) {
            this.shipto = shipto;
            return this;
        }

        private Header buildHeader(Order order) {
            Header header = new Header();
            header.setMsgType(MsgTypes.ORDER);
            header.setMsgState(MsgStates.NEW);
            header.setTestIndicator(false);

            StringBuilder sb = new StringBuilder();
            sb.append(order.getZakazka()).append("-").append(order.getSkupina());
            header.setCustomersOrderId(sb.toString());

            header.getTimeStamps().add(buildTimeStamps(MsgSteps.CREATE));

            OrderParties orderParties = new OrderParties();

            Account.Id id = new Account.Id();
            id.setValue(originator);
            orderParties.setId(id);
            orderParties.setRole(Roles.ORIGINATOR);

            Address address = new Address();
            address.getAddressLine().add(organisation);
            address.getAddressLine().add(street);
            address.setCity(city);
            address.setZip(zipCode);
            orderParties.setAddress(address);
            orderParties.setContact(new ContactInfo());
            header.getOrderParties().add(orderParties);

            Software originator = new Software();
            originator.setTypeOf(SoftwareTypes.ORIGINATOR);
            originator.setName(confDao.getSoftwareOriginatorName());
            originator.setVersion(confDao.getSoftwareOriginatorVersion());

            Software sender = new Software();
            sender.setTypeOf(SoftwareTypes.SENDER);
            sender.setName(confDao.getSoftwareSenderName());
            sender.setVersion(confDao.getSoftwareSenderVersion());
            header.getSoftware().add(originator);
            header.getSoftware().add(sender);

            return header;
        }

        private TimeStamps buildTimeStamps(MsgSteps step) {
            TimeStamps timeStamps = new TimeStamps();
            DateTime dateTime = new DateTime();
            dateTime.setStep(MsgSteps.CREATE);
            GregorianCalendar c = new GregorianCalendar();
            c.setTimeInMillis(System.currentTimeMillis());
            dateTime.setValue(new XMLGregorianCalendarImpl(c));
            timeStamps.setDateTime(dateTime);
            return timeStamps;
        }

        private Items buildItems(Order order) throws ParseException {
            Items items = new Items();
            Item item = new Item();

            // Parties
            Account.Id accountId = new Account.Id();
            accountId.setValue(shipto);
            Item.Parties parties = new Item.Parties();
            parties.setRole(Roles.SHIPTO);
            parties.setId(accountId);
            item.getParties().add(parties);

            OrderItem rightItem = order.getRightItem();
            OrderItem leftItem = order.getLeftItem();

            Customer customer;
            if (rightItem != null) {
                customer = customerDao.getCustomer(rightItem.getCi_reg());
            } else if (leftItem != null) {
                customer = customerDao.getCustomer(leftItem.getCi_reg());
            } else {
                throw new RuntimeException("RightItem and LeftItem are null, cannot determine customer");
            }

            StringBuilder sb = new StringBuilder();
            sb.append(order.getZakazka()).append("-").append(order.getSkupina());
            item.setReferenceNo(sb.toString());
            item.setReferenceText(buildReferenceText(customer));
            item.setManufacturer("ESSILOR");

            Pair pair = new Pair();
            Patient patient = new Patient();
            Id id = new Id();
            // TODO finish
            id.setMemberShipID(1L);
            id.setValue(String.valueOf(customer.getCustomerNumber()));
            patient.setId(id);
            patient.setName(zakazka + "-" + skupina);
            ContactInfo contactInfo = new ContactInfo();
            contactInfo.setTitle("");
            contactInfo.setFirstName("");
            contactInfo.setLastName(zakazka + "-" + skupina);
            patient.setContact(contactInfo);
            patient.setMailAllowed(false);

            if (rightItem != null) {
                pair.getLens().add(buildLens(patient, rightItem));
            }
            if (leftItem != null) {
                pair.getLens().add(buildLens(patient, leftItem));
            }

            pair.setThicknessMatching(false);
            pair.setWeightMatching(false);
            pair.setPrismMatching(false);
            pair.setBaseMatching(false);
            pair.setPatient(patient);
            item.setPair(pair);
            items.getItem().add(item);
            return items;
        }

        private Lens buildLens(Patient patient, OrderItem item)
                throws ParseException {
            Lens lens = new Lens();
            if ("A".equals(item.getVyrSklad())) {
                lens.setStockLens(true);
            } else {
                lens.setStockLens(false);
            }
            lens.setBalancingLens(false);
            lens.setVirtualLens(false);
            lens.setQuantity(BigInteger.ONE);
            lens.setCommercialCode(item.getKod());
            RXDataType rxData = new RXDataType();
            // sphere
            rxData.setSphere(decimalFormat.get()
                    .parse(item.getSph().replace('+', ' ').trim()).floatValue()); // fujtajbl

            // cylinder
            if (!item.getCyl().isEmpty() && !item.getOs().isEmpty()) {
                Cylinder cyl = new Cylinder();
                cyl.setPower(decimalFormat.get().parse(item.getCyl()).floatValue());
                cyl.setAxis(decimalFormat.get().parse(item.getOs()).floatValue());
                rxData.setCylinder(cyl);
            }

            // addition
            if (item.getAdice().compareTo(BigDecimal.ZERO) != 0) {
                rxData.setAddition(item.getAdice().floatValue());
            } else {
                rxData.setAddition(null);
            }


            // prism
            if (BigDecimal.ZERO.compareTo(item.getPrisma1()) != 0
                    || item.getBaza1() != 0) {
                Prism prism = new Prism();
                prism.setPower(item.getPrisma1().floatValue());
                prism.setBase(BigInteger.valueOf(item.getBaza1()));
                rxData.getPrism().add(prism);
            }
            if (BigDecimal.ZERO.compareTo(item.getPrisma2()) != 0
                    || item.getBaza2() != 0) {
                Prism prism = new Prism();
                prism.setPower(item.getPrisma2().floatValue());
                prism.setBase(BigInteger.valueOf(item.getBaza2()));
                rxData.getPrism().add(prism);
            }
            lens.setRxData(rxData);

            if (!item.getUprava1_typ().isEmpty()) {
                lens.getCoating().add(
                        buildCoating(item.getUprava1_typ(), item.getUprava1_kod(),
                                item.getUprava1_perc()));
            }
            if (!item.getUprava2_typ().isEmpty()) {
                lens.getCoating().add(
                        buildCoating(item.getUprava2_typ(), item.getUprava2_kod(),
                                item.getUprava2_perc()));
            }
            if (!item.getUprava3_typ().isEmpty()) {
                lens.getCoating().add(
                        buildCoating(item.getUprava3_typ(), item.getUprava3_kod(),
                                item.getUprava3_perc()));
            }
            if (!item.getUprava4_typ().isEmpty()) {
                lens.getCoating().add(
                        buildCoating(item.getUprava4_typ(), item.getUprava4_kod(),
                                item.getUprava4_perc()));
            }
            lens.setGeometry(buildGeometry(item));
            lens.setBranding(false);

            if ("P".equals(item.getOko())) {
                patient.setInterpupillaryDistanceRight(item.getPd().floatValue());
                lens.setSide(Sides.RIGHT);
            } else {
                patient.setInterpupillaryDistanceLeft(item.getPd().floatValue());
                lens.setSide(Sides.LEFT);
            }
            return lens;
        }

        private GeometryType buildGeometry(OrderItem orderItem) {
            GeometryType geometry = new GeometryType();
            geometry.setWaveFrontOptimisation(false);
            Diameter diameter = new Diameter();
            if (orderItem.getDiameter_h() != null && orderItem.getDiameter_h().length() > 0) {
                diameter.setPhysical(new BigInteger(orderItem.getDiameter_h()));
            } else {
                diameter.setPhysical(null);
            }
            if (orderItem.getDiameter_v() != null && orderItem.getDiameter_v().length() > 0) {
                diameter.setOptical(new BigInteger(orderItem.getDiameter_v()));
            }
            geometry.setDiameter(diameter);
            return geometry;
        }

        private Coating buildCoating(String uprava_typ, String uprava_kod,
                                     String uprava_perc) {
            Coating coating = new Coating();
            if (CoatingTypes.ANTIREFLEX.name().equals(uprava_typ)) {
                coating.setCoatingType(CoatingTypes.ANTIREFLEX);
            } else if (CoatingTypes.CLEAN.name().equals(uprava_typ)) {
                coating.setCoatingType(CoatingTypes.CLEAN);
            } else if (CoatingTypes.COLOR.name().equals(uprava_typ)) {
                coating.setCoatingType(CoatingTypes.COLOR);
            } else if (CoatingTypes.HARD.name().equals(uprava_typ)) {
                coating.setCoatingType(CoatingTypes.HARD);
            } else {
                coating.setCoatingType(CoatingTypes.OTHER);
            }
            if (!uprava_kod.isEmpty()) {
                coating.setCommercialCode(uprava_kod);
            }
            if (!uprava_perc.isEmpty()) {
                try {
                    decimalFormat.get().parse(uprava_perc);
                    coating.setMinIntensity(new BigInteger(uprava_perc));
                    coating.setMaxIntensity(new BigInteger(uprava_perc));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return coating;
        }

        public B2BOptic build() throws ParseException {
            if (zakazka == null) {
                throw new IllegalStateException("zakazka is null");
            }
            if (skupina == null) {
                throw new IllegalStateException("skupina is null");
            }
            if (objednavka == null) {
                throw new IllegalStateException("objednavka is null");
            }
            if (branchCode == null) {
                throw new IllegalStateException("branchCode is null");
            }

            Order order = orderDao.getOrder(zakazka, skupina, objednavka);

            this.organisation = confDao.getOrganisation(branchCode);
            this.city = confDao.getCity(branchCode);
            this.street = confDao.getStreet(branchCode);
            this.zipCode = confDao.getZipCode(branchCode);

            B2BOptic b2b = new B2BOptic();
            b2b.setHeader(buildHeader(order));
            b2b.setItems(buildItems(order));
            return b2b;
        }

        private String buildReferenceText(Customer customer) {
            StringBuilder sb = new StringBuilder();
            if (!customer.getFirstName().isEmpty()) {
                sb.append(customer.getFirstName().substring(0,1));
            }
            if (!customer.getSurname().isEmpty()) {
                sb.append(customer.getSurname().substring(0,1));
            }
            return sb.toString();
        }
    }
}
