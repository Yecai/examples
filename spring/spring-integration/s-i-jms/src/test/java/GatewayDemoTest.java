import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author Yecai
 * @date 2019/3/18 14:56
 */
public class GatewayDemoTest {

    private final static String[] configFilesGatewayDemo = {
            "/common.xml",
            "inboundGateway.xml",
            "outboundGateway.xml"
    };

    @Test
    public void testGatewayDemo() {
        System.setProperty("spring.profiles.active", "testCase");

        final GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext
                (configFilesGatewayDemo);

        final MessageChannel stdinToJmsOutChannel = applicationContext.getBean("stdinToJmsOutChannel", MessageChannel
                .class);

        stdinToJmsOutChannel.send(MessageBuilder.withPayload("jms test").build());

        final QueueChannel queueChannel = applicationContext.getBean("queueChannel", QueueChannel.class);

        Message<String> reply = (Message<String>) queueChannel.receive(20000);
        Assert.assertNotNull(reply);
        String out = reply.getPayload();

        Assert.assertEquals("JMS response: JMS TEST", out);

        applicationContext.close();

    }
}
