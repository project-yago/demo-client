package com.yago.app.demo.client;

import com.yago.app.demo.client.properties.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
@Slf4j
public class DemoJava8Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoJava8Application.class, args);

        LOG.info("===========================================================");
        LOG.info("=================== Application started ===================");
        LOG.info("===========================================================");
	}

}
