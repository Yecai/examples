package com.xiaopan.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * job流程
 * 1.顺序
 * 2.分支
 */
public class FlowJob {

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;

	/**
	 * 顺序
	 */
	@Bean
	public Job job1() {
	        return this.jobBuilderFactory.get("job")
	                                .start(stepA())
	                                .next(stepB())
	                                .next(stepC())
	                                .build();
	}
	
	/**
	 * 分支
	 * stepA
	 * stepA成功：执行stepB
	 * stepA失败：执行stepC
	 */
	@Bean
	public Job job2() {
	        return this.jobBuilderFactory.get("job")
	                                .start(stepA())
	                                .on("*").to(stepB())
	                                .from(stepA()).on("FAILED").to(stepC())
	                                .end()
	                                .build();
	}
	
	/**
	 * 分支
	 * step1
	 * step1失败：结束job
	 * step1抛出"COMPLETED WITH SKIPS"：执行errorPrint1
	 * step1成功：执行step2
	 */
	@Bean
	public Job job3() {
	        return this.jobBuilderFactory.get("job")
	                        .start(step1()).on("FAILED").end()
	                        .from(step1()).on("COMPLETED WITH SKIPS").to(errorPrint1())
	                        .from(step1()).on("*").to(step2())
	                        .end()
	                        .build();
	}
	
	/**
	 * 自定义决策器
	 */
	public class MyDecider implements JobExecutionDecider {
	    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
	        String status;
	        if (someCondition()) {
	            status = "FAILED";
	        }
	        else {
	            status = "COMPLETED";
	        }
	        return new FlowExecutionStatus(status);
	    }
	}
	@Bean
	public Job job4() {
	        return this.jobBuilderFactory.get("job")
	                        .start(step1())
	                        .next(decider()).on("FAILED").to(step2())
	                        .from(decider()).on("COMPLETED").to(step3())
	                        .end()
	                        .build();
	}
	@Bean
	public JobExecutionDecider decider() {
		return new MyDecider();
	}
	
	/**
	 * 分离流
	 */
	@Bean
	public Job job5() {
	        Flow flow1 = new FlowBuilder<SimpleFlow>("flow1")
	                        .start(step1())
	                        .next(step2())
	                        .build();
	        Flow flow2 = new FlowBuilder<SimpleFlow>("flow2")
	                        .start(step3())
	                        .build();

	        return this.jobBuilderFactory.get("job")
	                                .start(flow1)
	                                .split(new SimpleAsyncTaskExecutor())
	                                .add(flow2)
	                                .next(step4())
	                                .end()
	                                .build();
	}
	
	
	/**
	 * flow重用
	 */
	@Bean
	public Job job() {
	        return this.jobBuilderFactory.get("job")
	                                .start(flow1())
	                                .next(step3())
	                                .end()
	                                .build();
	}

	@Bean
	public Flow flow1() {
	        return new FlowBuilder<SimpleFlow>("flow1")
	                        .start(step1())
	                        .next(step2())
	                        .build();
	}
	
	/**
	 * step重用
	 */
	@Bean
	public Job jobStepJob() {
	        return this.jobBuilderFactory.get("jobStepJob")
	                                .start(jobStepJobStep1(null))
	                                .build();
	}

	@Bean
	public Step jobStepJobStep1(JobLauncher jobLauncher) {
	        return this.stepBuilderFactory.get("jobStepJobStep1")
	                                .job(job())
	                                .launcher(jobLauncher)
	                                .parametersExtractor(jobParametersExtractor())
	                                .build();
	}

	@Bean
	public Job job6() {
	        return this.jobBuilderFactory.get("job")
	                                .start(step1())
	                                .build();
	}

	@Bean
	public DefaultJobParametersExtractor jobParametersExtractor() {
	        DefaultJobParametersExtractor extractor = new DefaultJobParametersExtractor();

	        extractor.setKeys(new String[]{"input.file"});

	        return extractor;
	}
	
	
	/**
	 * 外部指定属性，比如文件名
	 * -Dinput.file.name="file://outputs/file.txt"
	 */
//	@Bean
//	public FlatFileItemReader flatFileItemReader(@Value("${input.file.name}") String name) {
//	        return new FlatFileItemReaderBuilder<Foo>()
//	                        .name("flatFileItemReader")
//	                        .resource(new FileSystemResource(name));
////	                        ...
//	}
//	@StepScope
//	@Bean
//	public FlatFileItemReader flatFileItemReader(@Value("#{jobParameters['input.file.name']}") String name) {
//	        return new FlatFileItemReaderBuilder<Foo>()
//	                        .name("flatFileItemReader")
//	                        .resource(new FileSystemResource(name))
//	                        ...
//	}
//	@StepScope
//	@Bean
//	public FlatFileItemReader flatFileItemReader(@Value("#{jobExecutionContext['input.file.name']}") String name) {
//	        return new FlatFileItemReaderBuilder<Foo>()
//	                        .name("flatFileItemReader")
//	                        .resource(new FileSystemResource(name))
//	                        ...
//	}
//	@StepScope
//	@Bean
//	public FlatFileItemReader flatFileItemReader(@Value("#{stepExecutionContext['input.file.name']}") String name) {
//	        return new FlatFileItemReaderBuilder<Foo>()
//	                        .name("flatFileItemReader")
//	                        .resource(new FileSystemResource(name))
//	                        ...
//	}
}
