package essilor.integrator.adapter.service;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import essilor.integrator.adapter.domain.b2boptic.B2BOptic;
import essilor.integrator.adapter.service.B2BOpticBuilder;

public class B2BOpticBuilderTest {

	private static ClassPathXmlApplicationContext context = null;

	@Before
	public void setUp() {
		context = new ClassPathXmlApplicationContext("/system-test-config.xml");
	}

	@Test
	public void testBuilder() throws Exception {
		B2BOpticBuilder b2bBuilder = context.getBean("b2BOpticBuilder",
				B2BOpticBuilder.class);

		B2BOptic b2bOptic = b2bBuilder.new Builder()
				.withZakazka("15505168")
				.withSkupina("1")
				.withObjednavka("3247")
				.withBranchCode("ZL")
				.withShipto("0000666")
				.withOriginator("0000666")
				.build();


		JAXBContext context = JAXBContext.newInstance(B2BOptic.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		StringWriter sw = new StringWriter();
		marshaller.marshal(b2bOptic, sw);
		System.out.println(sw.toString());

	}

}
