package essilor.integrator.adapter.datasource;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.utils.PropertiesUtil;

@Aspect
public class SetDataSourceAspect {

	private static final Logger logger = Logger.getLogger(SetDataSourceAspect.class);
	
	@Before("execution(* essilor.integrator.adapter.service.LogService.logResult(..)) && args(request,..)")
	@Order(Ordered.LOWEST_PRECEDENCE)
	public void setDataSourceForLogService(AdapterRequest request) {
		logger.trace("in pointcut");
		DataSourceNameHolder.setDataSourceName(request.getDataSourceName());
		logger.trace("out pointcut");
	}

	@Before("execution(* essilor.integrator.adapter.service.AdapterService.*(..)) && args(request,..)")
	public void setDataSourceForAdapterService(AdapterRequest request) {
		logger.trace("in pointcut");
		logger.debug("ds: " + request.getDataSourceName());
		DataSourceNameHolder.setDataSourceName(request.getDataSourceName());
		logger.trace("out pointcut");
	}
}
