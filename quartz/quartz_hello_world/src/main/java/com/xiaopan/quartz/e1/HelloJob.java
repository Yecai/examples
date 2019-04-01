package com.xiaopan.quartz.e1;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job {
	
	private static Logger _log = LoggerFactory.getLogger(HelloJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		_log.info("Hello world! - " + new Date());
	}
	
	
}
