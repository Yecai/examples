import org.springframework.test.context.ContextConfiguration;
import polling.MasterConfiguration;
import polling.WorkerConfiguration;

/**
 * 测试作业的主步骤将创建3个分区供workers使用
 * @author Yecai
 * @date 2019/3/21 16:04
 */
@ContextConfiguration(classes = {JobRunnerConfiguration.class, MasterConfiguration.class})
public class RemotePartitioningJobWithRepositoryPollingFunctionalTests extends RemotePartitioningJobFunctionalTests {
    @Override
    protected Class<WorkerConfiguration> getWorkerConfigurationClass() {
        return WorkerConfiguration.class;
    }
}
