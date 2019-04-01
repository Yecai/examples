import org.apache.activemq.broker.BrokerService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import test.JobLauncherTestUtils;

/**
 * @author Yecai
 * @date 2019/3/21 15:46
 */
@RunWith(SpringRunner.class)
@PropertySource("classpath:remote-partitioning.properties")
public abstract class RemotePartitioningJobFunctionalTests {

    private static final String BROKER_DATA_DIRECTORY = "build/activemq-data";

    @Value("${broker.url}")
    private String brokerUrl;

    @Autowired
    protected JobLauncherTestUtils jobLauncherTestUtils;

//    private BrokerService brokerService;

//    private EmbeddedDatabase embeddedDatabase;

    private AnnotationConfigApplicationContext workerApplicationContext;

    protected abstract Class<?> getWorkerConfigurationClass();

    @Before
    public void setUp() throws Exception {
//        this.brokerService = new BrokerService();
//        this.brokerService.addConnector(this.brokerUrl);
//        this.brokerService.setDataDirectory(BROKER_DATA_DIRECTORY);
//        this.brokerService.start();

//        this.embeddedDatabase = new EmbeddedDatabaseBuilder()
//                .addScript("schema-drop-hsqldb.sql")
//                .addScript("schema-hsqldb.sql")
//                .build();

        this.workerApplicationContext = new AnnotationConfigApplicationContext(getWorkerConfigurationClass());
    }

    @Test
    public void testRemotePartitioningJob() throws Exception {
        // when
        JobExecution jobExecution = this.jobLauncherTestUtils.launchJob();

        // then
        Assert.assertEquals(ExitStatus.COMPLETED.getExitCode(), jobExecution.getExitStatus().getExitCode());
        Assert.assertEquals(4, jobExecution.getStepExecutions().size()); // master + 3 workers
    }

    @After
    public void tearDown() throws Exception {
        this.workerApplicationContext.close();
//        this.brokerService.stop();
//        this.embeddedDatabase.shutdown();
    }

}
