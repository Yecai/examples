package com.xiaopan.quartz.e2;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {
	public static void main(String[] args) {
		
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			JobDetail job = JobBuilder.newJob(DumbJob.class)
					.withIdentity("job", "group1")
					.usingJobData("jobSays", "Hello World!")
					.usingJobData("myFloatValue", 3.141f)
					.build();
			
			Trigger trigger = TriggerBuilder.newTrigger()
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(10)
							.repeatForever())
					.build();
			
			scheduler.scheduleJob(job, trigger);
			
			scheduler.start();
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
