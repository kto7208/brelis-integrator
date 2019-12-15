package essilor.integrator.adapter.service;

import java.io.StringReader;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;

import essilor.integrator.adapter.Result;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

public class UploadOrderByActionReplyBuilder extends AdapterReplyBuilder {

	private static final Logger logger = Logger.getLogger(AdapterService.class);

	public String build() {
		try {
			StringBuilder builder = new StringBuilder();
			if (result.getProcessed() == Result.PROCESSED) {
				builder.append(result.getReturnCode())
						.append(ServiceCallTimestampHolder.getAsDateTime())
						.append(result.getOrderId() == null ? "" : result.getOrderId());
				int len = (result.getOrderId() == null) ? 15 : result.getOrderId().length();
				for (int i = 0; i < 15 - len; i++) {
					builder.append(" ");
				}
				builder.append(result.getUrl());
				if ("KO".equals(result.getReturnCode())
						&& result.getErrorText() != null) {
					builder.append("\r\n");
					builder.append(buildErrorMessages());
				}
			} else {
				builder.append("ER")
						.append(ServiceCallTimestampHolder.getAsDateTime())
						.append("               ")
						.append(result.getErrorText());
			}
			logger.info("reply: " + builder.toString());
			return builder.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private String buildErrorMessages() {
		StringBuilder sb = new StringBuilder();
		sb.append("ERROR MESSAGE").append("\r\n");
		try {
			XPathExpression<Element> expr = XPathFactory.instance().compile("//ERROR", Filters.element());
			List<Element> errors = expr.evaluate(new SAXBuilder().build(new StringReader(result.getErrorText())));
			for (Element error : errors) {
				if (!error.getTextTrim().isEmpty()) {
					sb.append(error.getTextTrim()).append("\r\n");
				}
			}
		} catch (Exception e) {
			// logger.warn("failed to parse error text", e);
			sb.append(result.getErrorText());
		}
		return sb.toString();
	}
}
