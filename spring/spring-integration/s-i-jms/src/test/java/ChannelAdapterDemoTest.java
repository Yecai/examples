import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class ChannelAdapterDemoTest {

    private final static String[] configFilesChannelAdapterDemo = {
           "/common.xml",
           "/inboundChannelAdapter.xml",
           "/outboundChannelAdapter.xml"
    };

    @Test
    public void testChannelAdapterDemo() {
        System.setProperty("spring.profiles.active", "testCase");

        final GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext(configFilesChannelAdapterDemo);

        final MessageChannel stdinToJmsOutChannel = applicationContext.getBean("stdinToJmsOutChannel", MessageChannel.class);

        stdinToJmsOutChannel.send(MessageBuilder.withPayload("jms test").build());

        final QueueChannel queueChannel = applicationContext.getBean("queueChannel", QueueChannel.class);

        Message<String> replay = (Message<String>) queueChannel.receive(20000);
        Assert.assertNotNull(replay);
        String out = replay.getPayload();
        Assert.assertEquals("jms test", out);

    }

}
