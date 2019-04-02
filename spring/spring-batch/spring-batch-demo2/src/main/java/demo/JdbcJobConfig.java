package demo;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import demo.domain.Customer;
import demo.domain.CustomerPreparedStatementSetter;
import demo.domain.CustomerRowMapper;

@Component
public class JdbcJobConfig {
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public Job jdbcJob() {
		return jobBuilderFactory.get("jdbcJob")
				.incrementer(new RunIdIncrementer())
				.start(systemOutStep())
				.build();
	}

	public Step systemOutStep() {
		return stepBuilderFactory.get("systemOutStep")
				.<Customer, Customer>chunk(1)
				.reader(jdbcReader())
				.processor(systemOutProcessor())
				.writer(jdbcWriter())
				.build();
	}

	private ItemProcessor<Customer, Customer> systemOutProcessor() {
		return new SystemOutProcessor();
	}

	private ItemReader<Customer> jdbcReader() {
		return new JdbcCursorItemReaderBuilder<Customer>()
				.name("jdbcReader")
				.dataSource(dataSource)
				.sql("select id, name from customer")
//				.fetchSize(500) // 游标一次读取的条数
				.rowMapper(new CustomerRowMapper())
				.build();
	}

	private ItemWriter<Customer> jdbcWriter() {
		
		return new JdbcBatchItemWriterBuilder<Customer>()
				.dataSource(dataSource)
				.sql("insert into customer_bak (id, name) values (?, ?)")
				.itemPreparedStatementSetter(new CustomerPreparedStatementSetter())
				.build();
	}
}
