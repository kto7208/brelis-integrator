package essilor.integrator.adapter.service;

import essilor.integrator.adapter.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;

import essilor.integrator.adapter.AdapterRequest;
import essilor.integrator.adapter.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogService {

	@Autowired
	OrderDao dao;

	@Transactional
	public void logResult(AdapterRequest request, Result result) {
		dao.logResult(request, result);
	}

}
