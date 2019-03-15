package com.xiaopan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerPeersApplication {

    /**
     * #host文件中添加
     * #127.0.0.1 peer1
     * #127.0.0.1 peer2
     *
     * #启动peer1和peer2
     * #java -jar spring-cloud-eureka-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1
     * #java -jar spring-cloud-eureka-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerPeersApplication.class, args);
    }
}

