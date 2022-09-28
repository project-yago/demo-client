package com.yago.app.demo.client.interceptor;

import com.yago.app.demo.client.model.response.AccessTokenResponse;
import com.yago.app.demo.client.properties.ApplicationProperties;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

@Component

public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {

    private ApplicationProperties applicationProperties;
    private String accessToken;

    public RestTemplateHeaderModifierInterceptor(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        if (accessToken == null) {
            accessToken = getAccessToken();
        }

        request.getHeaders().setBearerAuth(accessToken);
        ClientHttpResponse response = execution.execute(request, body);
        return response;
    }

    private String getAccessToken() {
        String accessToken = null;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(
            applicationProperties.getExternalAPI().getClientId(),
            applicationProperties.getExternalAPI().getClientSecret()
        );

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<AccessTokenResponse> responseAccessToken =
            restTemplate.exchange(applicationProperties.getExternalAPI().getBaseURI() + "/v1/oauth2/token",
                HttpMethod.POST,
                entity,
                AccessTokenResponse.class);

        accessToken = responseAccessToken.getBody().getAccessToken();

        return accessToken;
    }
}
