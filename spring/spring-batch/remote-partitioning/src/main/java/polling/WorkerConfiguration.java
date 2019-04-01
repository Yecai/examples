package polling;

import config.BrokerConfiguration;
import config.DataSourceConfiguration;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.integration.config.annotation.EnableBatchIntegration;
import org.springframework.batch.integration.partition.RemotePartitioningWorkerStepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;

/**
 * 分区处理的工作端(worker)
 * 每个worker将处理主步骤(master step)发过来的分区（partition）
 * @author Yecai
 * @date 2019/3/21 15:24
 */
@Configuration
@EnableBatchProcessing
@EnableBatchIntegration
@Import(value = {DataSourceConfiguration.class, BrokerConfiguration.class})
public class WorkerConfiguration {

    private final RemotePartitioningWorkerStepBuilderFactory workerStepBuilderFactory;

    public WorkerConfiguration(RemotePartitioningWorkerStepBuilderFactory workerStepBuilderFactory) {
        this.workerStepBuilderFactory = workerStepBuilderFactory;
    }

    /**
     * ========= 配置接收任务的流 ===============================
     */
    @Bean
    public DirectChannel requests() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow inboundFlow(ActiveMQConnectionFactory connectionFactory) {
        return IntegrationFlows
                .from(Jms.messageDrivenChannelAdapter(connectionFactory).destination("requests"))
                .channel(requests())
                .get();
    }

    /**
     * ========= 配置工作step ===============================
     */
    @Bean
    public Step workerStep() {
        return this.workerStepBuilderFactory.get("workerStep")
                .inputChannel(requests())
                .tasklet(tasklet(null))
                .build();
    }

    @Bean
    @StepScope
    public Tasklet tasklet(@Value("#{stepExecutionContext['partition']}")String partition) {
        return ((stepContribution, chunkContext) -> {
            System.out.println("processing" + partition);
            return RepeatStatus.FINISHED;
        });
    }

}
