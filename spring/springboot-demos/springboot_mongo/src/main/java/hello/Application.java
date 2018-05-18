package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner run(CustomerRepository repository) {
		return args -> {
			repository.deleteAll();
			
			//save a couple of customers
			repository.save(new Customer("Alice", "Smith"));
			repository.save(new Customer("Bob", "Smith"));
			
			//fetch all customers
			log.info("Customers found with findAll()");
			log.info("------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");
			
			//fetch on individual customer
			log.info("Customer found with findByFirstName('Alice'):");
			log.info("------------------------------");
			log.info(repository.findByfirstName("Alice").toString());
			log.info("");
			

			log.info("Customer found with findByLastName('Smith'):");
			log.info("------------------------------");
			repository.findByLastName("Smith").forEach(smith -> {
				log.info(smith.toString());
			});
			log.info("");
			
		};
	}
}
