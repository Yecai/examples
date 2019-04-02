package demo;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSoureConfiguration {

	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.url("jdbc:mysql://localhost:3306/springbatch?serverTimezone=GMT%2B8")
				.driverClassName("com.mysql.cj.jdbc.Driver")
				.username("root")
				.password("7590011")
				.build();
	}
	
	@Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
