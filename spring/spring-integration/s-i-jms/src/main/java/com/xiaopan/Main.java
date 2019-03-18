package com.xiaopan;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.util.Scanner;

/**
 *
 */
public class Main {

    private final static String[] configFilesGatewayDemo = {
            "/common.xml",
            "/inboundGateway.xml",
            "/outboundGateway.xml"
    };

    private final static String[] configFilesChannelAdapterDemo = {
            "/common.xml",
            "/inboundChannelAdapter.xml",
            "/outboundChannelAdapter.xml"
    };

    private final static String[] configFilesAggregationDemo = {
            "/common.xml",
            "/aggregation.xml"
    };

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        System.out.print("\n====================================================="
                        +"\n                                                     "
                        +"\n    Welcome to the Spring Integration JMS Sample!    "
                        +"\n                                                     "
                        +"\n=====================================================");

        ActiveMqTestUtils.prepare();

        System.out.println("\n    Which Demo would you like to run? <enter>:\n");
        System.out.println("\t1. Channel Adapter Demo");
        System.out.println("\t2. Gateway Demo");
        System.out.println("\t3. Aggregation Demo");

        while (true) {
            final String input = scanner.nextLine();

            if ("1".equals(input.trim())) {
                System.out.println("    Loading Channel Adapter Demo...");
                new ClassPathXmlApplicationContext(configFilesChannelAdapterDemo, Main.class);
                break;
            }
            else if ("2".equals(input.trim())) {
                System.out.println("    Loading Gateway Demo...");
                new ClassPathXmlApplicationContext(configFilesGatewayDemo, Main.class);
                break;
            }
            else if ("3".equals(input.trim())) {
                System.out.println("    Loading Aggregation Demo...");
                new ClassPathXmlApplicationContext(configFilesAggregationDemo, Main.class);
                break;
            }
            else {
                System.out.println("Invalid choice\n\n");
                System.out.println("Enter you choice: ");
            }

            System.out.println("    Please type something and hit <enter>\n");
            scanner.close();
        }
    }
}
