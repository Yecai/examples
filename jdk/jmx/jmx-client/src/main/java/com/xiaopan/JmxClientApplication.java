package com.xiaopan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
    public class JmxClientApplication {
        /**
         * 启动参数
         * -Dcom.sun.management.jmxremote
         * -Dcom.sun.management.jmxremote.port=9999
         * -Dcom.sun.management.jmxremote.local.only=false
         * -Dcom.sun.management.jmxremote.authenticate=false
         * -Dcom.sun.management.jmxremote.ssl=false
         */
        public static void main(String[] args) {
            SpringApplication.run(JmxClientApplication.class, args);
        }
}
