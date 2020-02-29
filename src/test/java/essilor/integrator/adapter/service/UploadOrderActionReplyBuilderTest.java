package essilor.integrator.adapter.service;

import java.io.StringReader;
import java.util.List;

import essilor.integrator.adapter.Result;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;

public class UploadOrderActionReplyBuilderTest {

//	private String errorText = "<ERROR_MESSAGE><LOGIN UserName=\"ESCZ#OPTOSYSCZTEST\" RefID=\"VWOPTOSYSCZ\" /><ERROR>&lt;ERROR CODE='33610' SEVERITY='Error'&gt;Nie je mozne nahrat pozadovanz zakazku. BillID: 83170000667 ShipID: 83170000667  &lt;/ERROR&gt;&lt;ERROR CODE='33607' SEVERITY='Error'&gt;Nie je mozne nahrat pozadovanu sosovku. Kod: 981259&lt;/ERROR&gt;&lt;ERROR CODE='33608' SEVERITY='Error'&gt;Nie je mozne nahrat pozadovany priemer. Kod: 70&lt;/ERROR&gt;</ERROR></ERROR_MESSAGE>";

	private String errorText = "<?xml version=\"1.0\" encoding=\"utf-16\"?>\n" +
			"<ERROR_MESSAGE xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
			"  <LOGIN UserName=\"ESCZ#CZ999000\" RefID=\"VWOPTOSYSCZ\" />\n" +
			"  <ERROR xmlns=\"http://services.visionweb.com/UploadCustomFileAction\">\n" +
			"    <ERROR CODE=\"33608\" d3p1:SEVERITY=\"Error\" xmlns:d3p1=\"WebServiceErrorSeverity\">Není možné použít zvolený průměr.</ERROR>\n" +
			"    <ERROR CODE=\"33608\" d3p1:SEVERITY=\"Error\" xmlns:d3p1=\"WebServiceErrorSeverity\">Není možné použít zvolený průměr.</ERROR>\n" +
			"  </ERROR>\n" +
			"</ERROR_MESSAGE>";

	@Test
	public void testCreateXml() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("ERROR MESSAGE").append("\r\n");
		
		SAXBuilder sbl=new SAXBuilder();
		Document doc=sbl.build(new StringReader(errorText));
		Element root = doc.getRootElement();
		String text = root.getChild("ERROR").getText();
		text = "<E>" + text + "</E>";
		Document doc1 = sbl.build(new StringReader(text));
		List<Element> l1 = doc1.getRootElement().getChildren("ERROR");
		for (Element e1 : l1) {
				sb.append(e1.getText()).append("\r\n");
		}
		System.out.println(sb.toString());
	}

	@Test
	public void testBuilder() {
		ServiceCallTimestampHolder.setTimestamp(System.currentTimeMillis());
		UploadOrderByActionReplyBuilder builder = new UploadOrderByActionReplyBuilder();
		builder.result = new Result();
		builder.result.setErrorText(errorText);
		builder.result.setProcessed(Result.PROCESSED);
		builder.result.setOrderId("1");
		builder.result.setReturnCode("KO");

		String reply = builder.build();
		System.out.println(reply);
	}
}
