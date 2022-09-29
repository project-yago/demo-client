package com.yago.app.demo.client.configuration;

import com.yago.app.demo.client.controller.ProductController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(ProductController.class);
    }
}
