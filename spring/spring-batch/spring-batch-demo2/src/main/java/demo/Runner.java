package demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	ApplicationContext context;
	
	@Override
	public void run(String... args) throws Exception {
		
		Job runJob = (Job) context.getBean("jdbcJob");
		
		if (runJob != null) {
			JobParameters jobParameters = 
					  new JobParametersBuilder()
					  .addLong("time",System.currentTimeMillis()).toJobParameters(); //防止无法重复执行同一个job
			
			JobExecution jobExecution = jobLauncher.run(runJob, jobParameters);
			System.out.println(jobExecution.getStatus().name());
		}
		
	}

}
