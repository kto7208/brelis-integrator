package essilor.integrator.adapter.service;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import essilor.integrator.adapter.domain.uploadfile.UploadOrderByAction;
import essilor.integrator.adapter.domain.uploadfile.UploadOrderByActionResponse;
import org.apache.log4j.Logger;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import java.io.StringReader;
import java.util.List;

public class UploadOrderByActionResultBuilder extends ResultBuilder {
	private static final Logger logger = Logger.getLogger(UploadOrderByActionResultBuilder.class);

	private UploadOrderByAction wsRequest;
	private UploadOrderByActionResponse wsResponse;

	private UploadOrderByActionResultBuilder(AdapterRequest request,
			UploadOrderByAction wsRequest, UploadOrderByActionResponse wsResponse) {
		super.request = request;
		this.wsRequest = wsRequest;
		this.wsResponse = wsResponse;
	}

	
	public static  ResultBuilder getInstance(AdapterRequest request,
			UploadOrderByAction wsRequest, UploadOrderByActionResponse wsResponse) {
		return new UploadOrderByActionResultBuilder(request, wsRequest, wsResponse);
	}


	@Override
	protected void build(Result result) {
		if (wsResponse.getUploadOrderByActionResult().getURL() != null) {
			result.setUrl(wsResponse.getUploadOrderByActionResult().getURL());
		}
		result.setXmlInput(wsRequest.getData());
		result.setOrderId(wsResponse.getUploadOrderByActionResult().getOrderId());
		if ("OK".equals(wsResponse.getUploadOrderByActionResult().getStatus()) ||
			"KO".equals(wsResponse.getUploadOrderByActionResult().getStatus())) {
			result.setProcessed(Result.PROCESSED);
			result.setReturnCode(wsResponse.getUploadOrderByActionResult().getStatus());
		} else {
			result.setReturnCode("ER");
		}
		if (wsResponse.getUploadOrderByActionResult().getErrorMessages() != null) {
			result.setErrorText(wsResponse.getUploadOrderByActionResult().getErrorMessages());
		}
		if ("KO".equals(wsResponse.getUploadOrderByActionResult().getStatus()) &&
				wsResponse.getUploadOrderByActionResult().getErrorMessages() != null) {
			result.setErrorCode(extractErrorCode(wsResponse.getUploadOrderByActionResult().getErrorMessages()));
		}
	}

	String extractErrorCode(String errorMessages) {
		try {
			XPathExpression<Element> expr = XPathFactory.instance().compile("//*[local-name()='ERROR']", Filters.element());
			List<Element> errors = expr.evaluate(new SAXBuilder().build(new StringReader(errorMessages)));
			for (Element error : errors) {
				if (error.getAttribute("CODE") != null) {
					return error.getAttribute("CODE").getValue();
				}
			}
		} catch (Exception e) {
			logger.warn("failed to parse error code", e);
		}
		return null;
	}

}
