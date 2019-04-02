package demo.domain;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

public class CustomerPreparedStatementSetter implements ItemPreparedStatementSetter<Customer>{

	@Override
	public void setValues(Customer customer, PreparedStatement ps) throws SQLException {
		ps.setLong(1, customer.getId());
		ps.setString(2, customer.getName());
	}

}
