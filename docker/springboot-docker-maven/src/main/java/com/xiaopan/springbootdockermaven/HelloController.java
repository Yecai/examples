package com.xiaopan.springbootdockermaven;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${name}")
    public String name;
    
    @GetMapping("/hello")
    public String hello() {
    	return "Hello " + name;
    }
}

