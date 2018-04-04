package com.xiaopan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class AbstractCommandLineRunner implements CommandLineRunner, Ordered{

    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner run");
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
