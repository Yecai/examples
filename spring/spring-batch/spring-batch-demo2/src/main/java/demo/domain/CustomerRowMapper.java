package demo.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Customer> {
	
	private static final String ID_COLUMN = "id";
	private static final String NAME_COLUMN = "name";

	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		
		customer.setId(rs.getLong(ID_COLUMN));
		customer.setName(rs.getString(NAME_COLUMN));
		
		return customer;
	}

}
