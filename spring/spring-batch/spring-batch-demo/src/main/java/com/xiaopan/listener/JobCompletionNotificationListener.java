package com.xiaopan.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

/**
 * JobExecutionListenerSupport
 * StepExecutionListener
 * ChunkListener 
 * ItemReadListener
 * ItemProcessListener
 * ItemWriteListener
 * SkipListener
 *
 */
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("!!! JOB START!");
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB SUCCESS!");
		} else if (jobExecution.getStatus() == BatchStatus.FAILED) {
			log.info("!!! JOB FAILED!");
		}
	}
}
