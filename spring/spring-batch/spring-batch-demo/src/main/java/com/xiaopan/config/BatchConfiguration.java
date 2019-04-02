package com.xiaopan.config;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	JobRepository jobRepository;

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	JobRegistry jobRegistry;

	@Autowired
	PlatformTransactionManager transactionManager;

	@Autowired
	JobBuilderFactory jobBuilders;

	@Autowired
	StepBuilderFactory stepBuilders;
	
	@Bean
	public BatchConfigurer batchConfigurer() {
		return new DefaultBatchConfigurer() {
			//覆盖默认事务控制器
//			@Override
//			public PlatformTransactionManager getTransactionManager() {
//				return new MyTransactionManager();
//			}

			//覆盖默认JobRepository
//			@Override
//			protected JobRepository createJobRepository() throws Exception {
//				JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//				factory.setDataSource(dataSource); #必填，数据源
//				factory.setTransactionManager(transactionManager); #必填，事务
//				factory.setIsolationLevelForCreate("ISOLATION_SERIALIZABLE"); #选填，事务隔离级别（ISOLATION_REPEATABLE_READ）
//				factory.setTablePrefix("BATCH_"); #选填，表名前缀
//				factory.setMaxVarCharLength(1000); #选填
//				return factory.getObject();
//			}
			
			//覆盖JobLauncher
//			@Override
//			protected JobLauncher createJobLauncher() throws Exception {
//				SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//		        jobLauncher.setJobRepository(jobRepository);
//	        	jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor()); //支持异步处理任务
//		        jobLauncher.afterPropertiesSet();
//		        return jobLauncher;
//			}
			
//			@Override
//			public JobExplorer getJobExplorer() {
//				JobExplorerFactoryBean factoryBean = new JobExplorerFactoryBean();
//		        factoryBean.setDataSource(this.dataSource);
//	        	factoryBean.setTablePrefix("BATCH_"); // 如果上面换了表名前缀，这里要一起换
//		        return factoryBean.getObject();
//			}
			
		};
	}
	
	
	//事务行为
//	@Bean
//	public TransactionProxyFactoryBean baseProxy() {
//	        TransactionProxyFactoryBean transactionProxyFactoryBean = new TransactionProxyFactoryBean();
//	        Properties transactionAttributes = new Properties();
//	        transactionAttributes.setProperty("*", "PROPAGATION_REQUIRED");
//	        transactionProxyFactoryBean.setTransactionAttributes(transactionAttributes);
//	        transactionProxyFactoryBean.setTarget(jobRepository());
//	        transactionProxyFactoryBean.setTransactionManager(transactionManager());
//	        return transactionProxyFactoryBean;
//	}
	
	
	//jobOperator
	@Bean
	 public SimpleJobOperator jobOperator(JobExplorer jobExplorer,
	                                JobRepository jobRepository,
	                                JobRegistry jobRegistry) {

	        SimpleJobOperator jobOperator = new SimpleJobOperator();

	        jobOperator.setJobExplorer(jobExplorer);
	        jobOperator.setJobRepository(jobRepository);
	        jobOperator.setJobRegistry(jobRegistry);
	        jobOperator.setJobLauncher(jobLauncher);

	        return jobOperator;
	 }
	
}
