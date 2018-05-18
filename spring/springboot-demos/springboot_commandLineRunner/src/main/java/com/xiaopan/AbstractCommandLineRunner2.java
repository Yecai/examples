package com.xiaopan;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

@Component
public class AbstractCommandLineRunner2 implements CommandLineRunner, Ordered{

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner2 run");
    }

}
