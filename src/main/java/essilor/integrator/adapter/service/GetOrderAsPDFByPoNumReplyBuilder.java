package essilor.integrator.adapter.service;

import org.apache.commons.io.FilenameUtils;

import essilor.integrator.adapter.Result;

public class GetOrderAsPDFByPoNumReplyBuilder extends AdapterReplyBuilder {

	@Override
	public String build() {
		StringBuilder reply = new StringBuilder();
		if (result.getProcessed() == Result.PROCESSED) {
			reply.append("OK")
					.append(ServiceCallTimestampHolder.getAsDateTime())
					.append(FilenameUtils.getName(result.getUrl()));
		} else {
			reply.append("KO")
					.append(ServiceCallTimestampHolder.getAsDateTime())
					.append(result.getErrorText());
		}
		return reply.toString();
	}
}
