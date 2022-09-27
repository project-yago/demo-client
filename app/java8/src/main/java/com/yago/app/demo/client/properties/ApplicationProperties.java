package com.yago.app.demo.client.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "demo")
public class ApplicationProperties {

    private final ExternalAPI externalAPI = new ExternalAPI();

    @Getter
    @Setter
    @ToString
    public static class ExternalAPI {

        private String baseURI;

    }
}
