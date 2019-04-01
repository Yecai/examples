package com.xiaopan.quartz.e1;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * hello world 示例
 */
public class QuartzTest {
	public static void main(String[] args) {
		
		try {
			// Grab the Scheduler instance from the Factory
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			scheduler.start();
			
			JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
			
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
					.startNow()
					.withSchedule(simpleSchedule()
							.withIntervalInSeconds(40)
							.repeatForever())
					.build();
			
			scheduler.scheduleJob(job, trigger);
			
//			scheduler.shutdown();
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}
}
