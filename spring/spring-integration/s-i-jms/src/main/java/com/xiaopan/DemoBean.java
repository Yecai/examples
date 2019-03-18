package com.xiaopan;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

//消息端点
@MessageEndpoint
public class DemoBean {

    @ServiceActivator
    public String upperCase(String input) {
        return "JMS response:" + input.toUpperCase();
    }

}
