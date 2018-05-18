package com.xiaopan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1、@Value会自动获取application.properties中对应的值
 * 2、可以使用spring.application.json覆盖
 *     方式一：java -Dspring.application.json='{"name":"test"}' -jar myapp.jar
 *     方式二：java -jar myapp.jar --spring.application.json='{"name":"test"}'
 * 3、指定配置文件名称
 *     java -jar myproject.jar --spring.config.name=xiaoPanApplication
 * 4、指定配置文件路径
 *     java -jar myproject.jar --spring.config.location=classpath:/default.properties,classpath:/override.properties
 *     多个路径从后往前搜索
 * 5、添加配置文件路径“spring.config.additional-location”
 * 
 */
@SpringBootApplication
public class Application implements CommandLineRunner{
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Value("${name}")
    private String name;
    @Value("${description}")
    private String description;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("========= name : " + name);
        System.out.println("========= description : " + description);
    }
}
