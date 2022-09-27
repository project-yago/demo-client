package com.yago.app.demo.client.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        request.getHeaders().setBearerAuth("A21AAIhuDcgqY0gCmoOBEPhx5W1paES9T5d17A_iB52BDABGc1m5HHrQ2Nc0ZLGGQO184M3VclKN80KVVg8-q_e-vB4X8dvmw");
        ClientHttpResponse response = execution.execute(request, body);
        return response;
    }
}
