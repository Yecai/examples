package aggregating;

import config.BrokerConfiguration;
import config.DataSourceConfiguration;
import org.apache.activemq.ActiveMQConnectionFactory;
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
 * @date 2019/3/21 19:43
 */
@Configuration
@EnableBatchProcessing
@EnableBatchIntegration
@Import(value = {DataSourceConfiguration.class, BrokerConfiguration.class})
public class MasterConfiguration {

    private static final int GRID_SIZE = 3;

    private final JobBuilderFactory jobBuilderFactory;

    private final RemotePartitioningMasterStepBuilderFactory masterStepBuilderFactory;

    public MasterConfiguration(JobBuilderFactory jobBuilderFactory,
                               RemotePartitioningMasterStepBuilderFactory masterStepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.masterStepBuilderFactory = masterStepBuilderFactory;
    }

    @Bean
    public DirectChannel requests() {
        return new DirectChannel();
    }

    public IntegrationFlow outboundFlow(ActiveMQConnectionFactory connectionFactory) {
        return IntegrationFlows
                .from(requests())
                .handle(Jms.outboundAdapter(connectionFactory).destination("requests"))
                .get();
    }


}
