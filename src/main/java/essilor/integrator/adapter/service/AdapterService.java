package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.domain.AdapterConfigInfo;
import essilor.integrator.adapter.domain.b2boptic.B2BOptic;
import essilor.integrator.adapter.domain.getorder.GetOrderAsPDFByPoNum;
import essilor.integrator.adapter.domain.getorder.GetOrderAsPDFByPoNumResponse;
import essilor.integrator.adapter.domain.getorder.GetOrderByPoNum;
import essilor.integrator.adapter.domain.getorder.GetOrderByPoNumResponse;
import essilor.integrator.adapter.domain.owvalidation.ValidateOrderFromPMS;
import essilor.integrator.adapter.domain.owvalidation.ValidateOrderFromPMSResponse;
import essilor.integrator.adapter.domain.supplier.GetSuppliers;
import essilor.integrator.adapter.domain.supplier.GetSuppliersResponse;
import essilor.integrator.adapter.domain.uploadfile.UploadCustomFile;
import essilor.integrator.adapter.domain.uploadfile.UploadCustomFileResponse;
import essilor.integrator.adapter.domain.uploadfile.UploadOrderByAction;
import essilor.integrator.adapter.domain.uploadfile.UploadOrderByActionResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.xml.transform.StringSource;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@Service
public class AdapterService {

	private static final Logger logger = Logger.getLogger(AdapterService.class);
	
	@Autowired
	private EssilorService essilorService;

	@Autowired
	private LogService logService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private B2BOpticBuilder b2bBuilder;
	
	@Autowired
	private JAXBContext jaxbContext;

	@Value("${adapter.temp}")
	private String temp;

	private Map<String, AdapterConfigInfo> adapterConfigMap;

	@Resource
	private void setAdapterConfigMap(Map<String, AdapterConfigInfo> adapterConfigMap) {
				this.adapterConfigMap = adapterConfigMap;
	}


	private AdapterConfigInfo getAdapterConfigInfo(AdapterRequest request) {
	   AdapterConfigInfo adapterConfigInfo = adapterConfigMap.get(request.getDataSourceName() + "-" + request.getBranchCode());
	   if (adapterConfigInfo == null) {
	       throw new IllegalStateException("adapterConfigInfo is null");
       } else {
	       return adapterConfigInfo;
       }
    }

	public Result uploadCustomFile(AdapterRequest request) {
		try {

		    AdapterConfigInfo adapterConfigInfo = getAdapterConfigInfo(request);

            B2BOptic b2bOptic = b2bBuilder.new Builder()
                    .withZakazka(request.getOrderNumber())
                    .withSkupina(request.getOrderGroup())
                    .withObjednavka(request.getPurchaseOrderNumber())
                    .withBranchCode(request.getBranchCode())
                    .withOriginator(adapterConfigInfo.getOriginator())
			        .withShipto(adapterConfigInfo.getShipto())
                    .build();

			UploadCustomFile wsRequest = new UploadCustomFile();
			wsRequest.setUsername(adapterConfigInfo.getUser());
			wsRequest.setPassword(adapterConfigInfo.getPassword());
			wsRequest.setRefid(adapterConfigInfo.getRefid());
			wsRequest.setLocale(adapterConfigInfo.getLocale());
			wsRequest.setTempId(temp);
			wsRequest.setTrace(null);

//			JAXBContext context = JAXBContext.newInstance(B2BOptic.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();
			marshaller.marshal(b2bOptic, sw);
			wsRequest.setData(sw.toString());

			UploadCustomFileResponse wsResponse = essilorService
					.uploadCustomFile(wsRequest);

			Result result = UploadCustomFileResultBuilder.getInstance(request,
					wsRequest, wsResponse).buildResult();
			result.setUsername(adapterConfigInfo.getUser());

			if (Result.PROCESSED == result.getProcessed()) {
				orderService.updateOrderAfterUpload(request, result.getUrl());
			}
			logService.logResult(request, result);

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public Result getOrderByPoNum(AdapterRequest request) {
        AdapterConfigInfo adapterConfigInfo = getAdapterConfigInfo(request);

        GetOrderByPoNum wsRequest = new GetOrderByPoNum();
		wsRequest.setUsername(adapterConfigInfo.getUser());
		wsRequest.setPassword(adapterConfigInfo.getPassword());
		wsRequest.setRefid(adapterConfigInfo.getRefid());
		StringBuilder sb = new StringBuilder();
		sb.append(request.getOrderNumber().trim()).append("-")
				.append(request.getOrderGroup().trim());
		wsRequest.setPonum(sb.toString());

		GetOrderByPoNumResponse wsResponse = essilorService
				.getOrderByPoNum(wsRequest);

		B2BOptic b2bOptic;
		try {
//			JAXBContext context = JAXBContext.newInstance(B2BOptic.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			b2bOptic = (B2BOptic) unmarshaller.unmarshal(new StringSource(
					wsResponse.getGetOrderByPoNumResult()));
		} catch (JAXBException e) {
			// returned error structure from web service
			b2bOptic = null;
		}

		Result result = GetOrderByPoNumResultBuilder.getInstance(request,
				wsRequest, wsResponse, b2bOptic).buildResult();
		result.setUsername(adapterConfigInfo.getUser());

		if (b2bOptic != null) {
			orderService.updateOrderAfterGetOrder(request, b2bOptic);
		}
		logService.logResult(request, result);
		return result;
	}

	public Result getOrderByPoNum_2(AdapterRequest request) {
        AdapterConfigInfo adapterConfigInfo = getAdapterConfigInfo(request);

		GetOrderByPoNum wsRequest = new GetOrderByPoNum();
		wsRequest.setUsername(adapterConfigInfo.getUser());
		wsRequest.setPassword(adapterConfigInfo.getPassword());
		wsRequest.setRefid(adapterConfigInfo.getRefid());
		StringBuilder sb = new StringBuilder();
		sb.append(request.getOrderNumber().trim()).append("-")
				.append(request.getOrderGroup().trim());
		wsRequest.setPonum(sb.toString());

		GetOrderByPoNumResponse wsResponse = essilorService
				.getOrderByPoNum(wsRequest);

		B2BOptic b2bOptic;
		try {
//			JAXBContext context = JAXBContext.newInstance(B2BOptic.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			b2bOptic = (B2BOptic) unmarshaller.unmarshal(new StringSource(
					wsResponse.getGetOrderByPoNumResult()));
		} catch (JAXBException e) {
			// returned error structure from web service
			b2bOptic = null;
		}

		Result result = GetOrderByPoNumResultBuilder.getInstance(request,
				wsRequest, wsResponse, b2bOptic).buildResult();
		result.setUsername(adapterConfigInfo.getUser());

		logService.logResult(request, result);
		return result;
	}

	public Result getOrderAsPDFByPoNum(AdapterRequest request) {
        AdapterConfigInfo adapterConfigInfo = getAdapterConfigInfo(request);
		GetOrderAsPDFByPoNum wsRequest = new GetOrderAsPDFByPoNum();
		wsRequest.setUsername(adapterConfigInfo.getUser());
		wsRequest.setPassword(adapterConfigInfo.getPassword());
		wsRequest.setRefid(adapterConfigInfo.getRefid());
		StringBuilder sb = new StringBuilder();
		sb.append(request.getOrderNumber().trim()).append("-")
				.append(request.getOrderGroup().trim());
		wsRequest.setPoNum(sb.toString());

		GetOrderAsPDFByPoNumResponse wsResponse = essilorService
				.getOrderAsPDFByPoNum(wsRequest);

		String pathToFile = null;
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		StringBuilder fileName = new StringBuilder();
		fileName.append(wsRequest.getPoNum()).append("_")
				.append(df.format(new Date(ServiceCallTimestampHolder.getAsLong())))
				.append(".pdf");
		if (wsResponse.getGetOrderAsPDFByPoNumResult().getError() == null ||
				wsResponse.getGetOrderAsPDFByPoNumResult().getError().isEmpty()) {
			pathToFile = orderService.saveOrderAsPDF(request, fileName.toString(), wsResponse.getGetOrderAsPDFByPoNumResult()
					.getPdf());
		}

		Result result = GetOrderAsPDFByPoNumResultBuilder.getInstance(request,
				wsRequest, wsResponse, pathToFile,
				wsResponse.getGetOrderAsPDFByPoNumResult().getPdf())
				.buildResult();
		result.setUsername(adapterConfigInfo.getUser());

		logService.logResult(request, result);

		return result;
	}
	
	public Result uploadOrderByAction(AdapterRequest request) {
		try {
            AdapterConfigInfo adapterConfigInfo = getAdapterConfigInfo(request);
            B2BOptic b2bOptic = b2bBuilder.new Builder()
                    .withZakazka(request.getOrderNumber())
                    .withSkupina(request.getOrderGroup())
                    .withObjednavka(request.getPurchaseOrderNumber())
                    .withBranchCode(request.getBranchCode())
                    .withOriginator(adapterConfigInfo.getOriginator())
                    .withShipto(adapterConfigInfo.getShipto())
                    .build();

            UploadOrderByAction wsRequest = new UploadOrderByAction();
			wsRequest.setUsername(adapterConfigInfo.getUser());
			wsRequest.setPassword(adapterConfigInfo.getPassword());
			wsRequest.setRefid(adapterConfigInfo.getRefid());
			wsRequest.setLocale(adapterConfigInfo.getLocale());
			wsRequest.setTempId(temp);
			wsRequest.setTrace(null);
			wsRequest.setSloid(request.getSloId());
			wsRequest.setActionType(request.getActionType().name());

//			JAXBContext context = JAXBContext.newInstance(B2BOptic.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();
			marshaller.marshal(b2bOptic, sw);
			wsRequest.setData(sw.toString());

			UploadOrderByActionResponse wsResponse = essilorService
					.uploadOrderByAction(wsRequest);

			Result result = UploadOrderByActionResultBuilder.getInstance(request,
					wsRequest, wsResponse).buildResult();
			result.setUsername(adapterConfigInfo.getUser());

			if (Result.PROCESSED == result.getProcessed()) {
				orderService.updateOrderAfterUploadOrderByAction(request, result);
			}
			logService.logResult(request, result);

			return result;
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}
	
	public Result validateOrderFromPMS(AdapterRequest request) {
		try {
            AdapterConfigInfo adapterConfigInfo = getAdapterConfigInfo(request);
            B2BOptic b2bOptic = b2bBuilder.new Builder()
                    .withZakazka(request.getOrderNumber())
                    .withSkupina(request.getOrderGroup())
                    .withObjednavka(request.getPurchaseOrderNumber())
                    .withBranchCode(request.getBranchCode())
                    .withOriginator(adapterConfigInfo.getOriginator())
                    .withShipto(adapterConfigInfo.getShipto())
                    .build();

            ValidateOrderFromPMS wsRequest = new ValidateOrderFromPMS();
			wsRequest.setUsername(adapterConfigInfo.getUser());
			wsRequest.setPassword(adapterConfigInfo.getPassword());
			wsRequest.setRefid(adapterConfigInfo.getRefid());
			wsRequest.setLocale(adapterConfigInfo.getLocale());
			wsRequest.setSloId(request.getSloId());

//			JAXBContext context = JAXBContext.newInstance(B2BOptic.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();
			marshaller.marshal(b2bOptic, sw);
			wsRequest.setOrderFile(sw.toString());
			
			ValidateOrderFromPMSResponse wsResponse = essilorService
					.validateOrderFromPMS(wsRequest);
			
			Result result = ValidateOrderFromPMSResultBuilder.getInstance(request,
					wsRequest, wsResponse).buildResult();
			result.setUsername(adapterConfigInfo.getUser());

			logService.logResult(request, result);

			return result;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Result getSuppliers(AdapterRequest request) {
        AdapterConfigInfo adapterConfigInfo = getAdapterConfigInfo(request);
		GetSuppliers wsRequest = new GetSuppliers();
		wsRequest.setUsername(adapterConfigInfo.getUser());
		wsRequest.setPassword(adapterConfigInfo.getPassword());

		GetSuppliersResponse wsResponse = essilorService
				.getSuppliers(wsRequest);
		
		Result result = GetSuppliersResultBuilder.getInstance(request,
				wsRequest, wsResponse).buildResult();
		result.setUsername(adapterConfigInfo.getUser());

		logService.logResult(request, result);
		return result;
	}
}
