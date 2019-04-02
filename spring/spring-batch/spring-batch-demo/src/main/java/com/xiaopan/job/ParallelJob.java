package com.xiaopan.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * 
 * 多线程step(单进程)
 * 并行step(单进程)
 * 远程step中的chunk(多进程)
 * step分区(单个或多个进程)
 *
 */
public class ParallelJob {

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	/**
	 * 多线程步骤(单进程)
	 * .taskExecutor(taskExecutor)
	 * 注意itemReader需要是多线程安全的，避免单点访问数据问题
	 */
	@Bean
	public TaskExecutor taskExecutor(){
	    return new SimpleAsyncTaskExecutor("spring_batch");
	}

	@Bean
	public Step sampleStep(TaskExecutor taskExecutor) {
	        return this.stepBuilderFactory.get("sampleStep")
	                                .<String, String>chunk(10)
//	                                .reader(itemReader())
//	                                .writer(itemWriter())
	                                .taskExecutor(taskExecutor)
	                                .throttleLimit(20) //异步taskExecutor线程池大小，默认为4（不能太大，必须确定数据库连接支持并发数量）
	                                .build();
	}
	
	
	/**
	 * 并行step(单进程)
	 * (step1,step2)与step3会同步执行
	 */
	@Bean
	public Job job() {
	    return jobBuilderFactory.get("job")
	        .start(splitFlow())
	        .next(step4())
	        .build()        //builds FlowJobBuilder instance
	        .build();       //builds Job instance
	}

	@Bean
	public Flow splitFlow() {
	    return new FlowBuilder<SimpleFlow>("splitFlow")
	        .split(taskExecutor())
	        .add(flow1(), flow2())
	        .build();
	}

	@Bean
	public Flow flow1() {
	    return new FlowBuilder<SimpleFlow>("flow1")
	        .start(step1())
	        .next(step2())
	        .build();
	}

	@Bean
	public Flow flow2() {
	    return new FlowBuilder<SimpleFlow>("flow2")
	        .start(step3())
	        .build();
	}
	
	/**
	 * 远程step中的chunk(多进程)
	 * 在远程执行chunk时，step被分配到多个进程中执行，进程之间通过中间件进行通信
	 * 
	 * 主模块是个单进程，从模块是多进程。处理的开销必须大于读取项的开销
	 * https://docs.spring.io/spring-batch/4.1.x/reference/html/spring-batch-integration.html#remote-chunking
	 */
	
	/**
	 * step分区(单个或多个进程)
	 */
	@Bean
	public Step step1Master() {
	    return stepBuilderFactory.get("step1.master")
	        .<String, String>partitioner("step1", partitioner())
	        .step(step1())
	        .gridSize(10) //网格（同时计算）数量限制
	        .taskExecutor(taskExecutor())
	        .build();
	}
	
	/**
	 * step分区(单个或多个进程)
	 * PartitionHandler
	 */
	
	@Bean
	public Step step1Master() {
	    return stepBuilderFactory.get("step1.master")
	        .partitioner("step1", partitioner())
	        .partitionHandler(partitionHandler())
	        .build();
	}

	@Bean
	public PartitionHandler partitionHandler() {
	    TaskExecutorPartitionHandler retVal = new TaskExecutorPartitionHandler();
	    retVal.setTaskExecutor(taskExecutor());
	    retVal.setStep(step1());
	    retVal.setGridSize(10);
	    return retVal;
	}
}
