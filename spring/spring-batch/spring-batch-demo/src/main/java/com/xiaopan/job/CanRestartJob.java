package com.xiaopan.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CanRestartJob {

	
	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job footballJob() {
	        return this.jobBuilderFactory.get("footballJob")
	                                .flow(playerLoad())
	                                .next(gameLoad())
	                                .next(playerSummarization())
	                                .end()
	                                .build();
	}

	@Bean
	public Step playerLoad() {
	        return this.stepBuilderFactory.get("playerLoad")
	                        .<String, String>chunk(10)
//	                        .reader(playerFileItemReader())
//	                        .writer(playerWriter())
	                        .build();
	}

	@Bean
	public Step gameLoad() {
	        return this.stepBuilderFactory.get("gameLoad")
	                        .allowStartIfComplete(true) // 支持重启后每次都运行gameLoad这个step
	                        .<String, String>chunk(10)
//	                        .reader(gameFileItemReader())
//	                        .writer(gameWriter())
	                        .build();
	}

	@Bean
	public Step playerSummarization() {
	        return this.stepBuilderFactory.get("playerSummarization")
	                        .startLimit(2) // 支持重启，但是限制执行2次，重启第三次失败
	                        .<String, String>chunk(10)
//	                        .reader(playerSummarizationSource())
//	                        .writer(summaryWriter())
	                        .build();
	}
}
