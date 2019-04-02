package demo;

import org.springframework.batch.item.ItemProcessor;

public class SystemOutProcessor implements ItemProcessor<Customer, Customer> {

	public Customer process(Customer customer) throws Exception {
		if (customer != null) {
			System.out.println("customer: id=" + customer.getId()  + " ,name="+ customer.getName());
		}
		return customer;
	}

}
