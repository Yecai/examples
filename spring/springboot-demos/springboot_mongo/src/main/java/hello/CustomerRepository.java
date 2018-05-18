package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String>{
	public Customer findByfirstName(String firstName);
	public List<Customer> findByLastName(String lastName);
	

}
