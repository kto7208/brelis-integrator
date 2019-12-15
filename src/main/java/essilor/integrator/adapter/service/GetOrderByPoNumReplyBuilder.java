package essilor.integrator.adapter.service;

public class GetOrderByPoNumReplyBuilder extends AdapterReplyBuilder {

	@Override
	public String build() {
		StringBuilder reply = new StringBuilder();
		if (result.getErrorText() == null) {
			reply.append("OK").append(
					ServiceCallTimestampHolder.getAsDateTime());
		} else {
			reply.append("KO")
					.append(ServiceCallTimestampHolder.getAsDateTime())
					.append(result.getErrorText());
		}
		return reply.toString();
	}

}
