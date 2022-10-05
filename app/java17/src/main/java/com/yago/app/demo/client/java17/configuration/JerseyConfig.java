package com.yago.app.demo.client.java17.configuration;

import com.yago.app.demo.client.java17.controller.ProductController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(ProductController.class);
    }
}
