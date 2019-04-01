import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.JobLauncherTestUtils;

/**
 * @author Yecai
 * @date 2019/3/21 16:05
 */
@Configuration
@EnableBatchProcessing
public class JobRunnerConfiguration {

    @Bean
    public JobLauncherTestUtils utils() throws Exception {
        return new JobLauncherTestUtils();
    }
}
