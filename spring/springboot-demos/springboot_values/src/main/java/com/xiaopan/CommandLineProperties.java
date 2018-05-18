package com.xiaopan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 命令行使用 “--name=value”传值，比如--server.port=9000
 */
@Component
public class CommandLineProperties implements CommandLineRunner {

//    @Value("${server.port}")
//    private String port;

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("port:" + port);
    }
}
