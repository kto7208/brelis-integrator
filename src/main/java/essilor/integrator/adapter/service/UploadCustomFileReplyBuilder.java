package essilor.integrator.adapter.service;

public class UploadCustomFileReplyBuilder extends AdapterReplyBuilder {

	@Override
	public String build() {
		StringBuilder reply = new StringBuilder();
		if (result.getErrorText() == null) {
			reply.append("OK")
					.append(ServiceCallTimestampHolder.getAsDateTime())
					.append(result.getUrl());
		} else {
			reply.append("KO")
					.append(ServiceCallTimestampHolder.getAsDateTime())
					.append(result.getErrorText());
		}
		return reply.toString();
	}

}
