package essilor.integrator.adapter.dao;

import essilor.integrator.adapter.domain.optosys.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class CustomerDao {

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void init() {
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}

	public Customer getCustomer(long customerNumber) {
		String sql = "select prijmeni, jmeno, titul from odber where ci_reg=?";
		List<Customer> list = jdbcTemplate.query(sql,  new Object[]{customerNumber}, new CustomerRowMapper(customerNumber));
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
