package com.yago.app.demo.client.java11;

import com.yago.app.demo.client.java11.properties.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
@Slf4j
public class DemoJava11Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoJava11Application.class, args);
    }

}
