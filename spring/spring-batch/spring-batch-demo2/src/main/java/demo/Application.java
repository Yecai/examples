package demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 手动执行数据库读写job demo
 * （单job单step，验证数据库读取数据）
 * 
 * （注：关闭自动执行job需要配置spring.batch.job.enabled=false）
 */
@SpringBootApplication
@EnableBatchProcessing
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
