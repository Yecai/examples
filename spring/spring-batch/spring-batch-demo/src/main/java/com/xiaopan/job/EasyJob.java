package com.xiaopan.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.xiaopan.domain.Person;
import com.xiaopan.listener.JobCompletionNotificationListener;

@Component
public class EasyJob {

	
	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job easyJob(Step step1, Step step2) {
		return jobBuilderFactory.get("easyJob")
				.incrementer(new RunIdIncrementer())
				.preventRestart() //只能执行一次，不能重启（执行异常也无法重跑）
				.listener(new JobCompletionNotificationListener()) //监听器
				.validator(new DefaultJobParametersValidator()) //过滤器
				.flow(step1) //flow start
				.next(step2)
				.end() // flow end
				.build(); // build job
	}
	
	@Bean
	protected Step step1(ItemReader<Person> reader,
			ItemProcessor<Person, Person> processor,
			ItemWriter<Person> writer) {
		/**
		 * 事务属性可用于控制隔离、传播和超时设置。
        DefaultTransactionAttribute attribute = new DefaultTransactionAttribute();
        attribute.setPropagationBehavior(Propagation.REQUIRED.value());
        attribute.setIsolationLevel(Isolation.DEFAULT.value());
        attribute.setTimeout(30);
		 */
        
		return stepBuilderFactory.get("step1")
				.<Person, Person> chunk(10) // 一个事务10个chunk，每处理10条数据提交一次数据库
				.reader(reader)
				.processor(processor)
				.writer(writer)
				
				.faultTolerant()
				.skipLimit(10) // 最多跳过10个，第11个step抛出异常将导致该step失败
				.skip(FlatFileParseException.class) // 如果抛出FlatFileParseException异常，则跳过
				/**
				 * 更加明细的异常跳过设置，跳过除FileNotFoundException外的所有Exception
				.skip(Exception.class)
				.noSkip(FileNotFoundException.class)
				 */
				
				/**
				 * 重试
				.faultTolerant()
				.retryLimit(3)
				.retry(DeadlockLoserDataAccessException.class)
				 */
				
				/**
				 * 回滚
				.noRollback(ValidationException.class)
				 */
				
				/**
				 * 读取器是事务性的（通常是队列），失败的项目可能回滚并在后续事务中重新处理
				.readerIsTransactionalQueue()
				 */

				/**
				 * 设置事务属性，控制隔离、传播和超时设置
                .transactionAttribute(attribute)
				 */
				
				.startLimit(1) // 只允许step执行一次，再次执行会抛出StartLimitExceededException，默认限制次数为Integer.MAX_VALUE
				.allowStartIfComplete(true) // 重新运行job，会默认跳过成功的step，设置为true表示所有step全部重新执行
				
				
				.build();
	}

	@Bean
	protected Step step2() {
		return stepBuilderFactory.get("step2")
				.tasklet(myTasklet())
				.build();
	}
	
	@Bean
	public MethodInvokingTaskletAdapter myTasklet() {
	        MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();

//	        adapter.setTargetObject(fooDao());
//	        adapter.setTargetMethod("updateFoo");

	        return adapter;
	}
	
}
