package polling;

import config.BrokerConfiguration;
import config.DataSourceConfiguration;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.integration.config.annotation.EnableBatchIntegration;
import org.springframework.batch.integration.partition.RemotePartitioningMasterStepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;

/**
 * 分区处理的主端(master)
 * 主步骤（master step）将会创建三个分区提供给子节点（workers）处理
 * @author Yecai
 * @date 2019/3/21 14:39
 */
@Configuration
@EnableBatchProcessing
@EnableBatchIntegration
@Import(value = {DataSourceConfiguration.class, BrokerConfiguration.class})
public class MasterConfiguration {

    ///3个分区
    private static final int GRID_SIZE = 3;

    private final JobBuilderFactory jobBuilderFactory;

    private final RemotePartitioningMasterStepBuilderFactory masterStepBuilderFactory;

    public MasterConfiguration(JobBuilderFactory jobBuilderFactory,
                               RemotePartitioningMasterStepBuilderFactory masterStepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.masterStepBuilderFactory = masterStepBuilderFactory;
    }

    /**
     * ============= 配置发送任务给workers的通道流 =============================
     */

    /**
     * 发送任务的通道
     */
    @Bean
    public DirectChannel requests() {
        return new DirectChannel();
    }

    /**
     * 发送通道适配器
     * 向requests队列发送任务
     */
    @Bean
    public IntegrationFlow outboundFlow(ActiveMQConnectionFactory connectionFactory) {
        return IntegrationFlows
                .from(requests())
                .handle(Jms.outboundAdapter(connectionFactory).destination("requests"))
                .get();
    }


    /**
     * ============= 配置主任务（master step） =============================
     */

    /**
     * master step
     */
    @Bean
    public Step masterStep() {
        return this.masterStepBuilderFactory.get("masterStep")
                .partitioner("workerStep", new BasicPartitioner())
                .gridSize(GRID_SIZE)
                .outputChannel(requests())
                .build();
    }

    /**
     * job
     */
    @Bean
    public Job remotePartitioningJob() {
        return this.jobBuilderFactory.get("remotePartitioningJob")
                .start(masterStep())
                .build();
    }




}
