package com.xiaopan.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;

/**
 * 如何使用Tasklet
 *
 */
public class TaskletJob {

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job taskletJob() {
	        return this.jobBuilderFactory.get("taskletJob")
	                                .start(deleteFilesInDir())
	                                .build();
	}

	@Bean
	public Step deleteFilesInDir() {
	        return this.stepBuilderFactory.get("deleteFilesInDir")
	                                .tasklet(fileDeletingTasklet())
	                                .build();
	}

	@Bean
	public FileDeletingTasklet fileDeletingTasklet() {
	        FileDeletingTasklet tasklet = new FileDeletingTasklet();

	        tasklet.setDirectoryResource(new FileSystemResource("target/test-outputs/test-dir"));

	        return tasklet;
	}
}
