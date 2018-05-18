package hello;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(AppRunner.class);

	private GitHubLookupService gitHubLookupService;
	
	public AppRunner(GitHubLookupService gitHubLookupService) {
		this.gitHubLookupService = gitHubLookupService;
	}
	
	@Override
	public void run(String... args) throws Exception {
		// start the clock
		long start = System.currentTimeMillis();
		
		//Kick of multiple, asynchronous lookups
		CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
		CompletableFuture<User> page2 = gitHubLookupService.findUser("CloudFoundry");
		CompletableFuture<User> page3 = gitHubLookupService.findUser("Spring-Projects");
		
		//Wait until they are all done
		CompletableFuture.allOf(page1, page2, page3).join();
		
		//Print results, including elapsed time
		log.info("Elapsed time: " + (System.currentTimeMillis() - start));
		log.info("--> " + page1.get());
		log.info("--> " + page2.get());
		log.info("--> " + page3.get());
	}
	
	

}
