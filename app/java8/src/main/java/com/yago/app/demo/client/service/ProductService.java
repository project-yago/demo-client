package com.yago.app.demo.client.service;

import com.yago.app.demo.client.model.Product;
import com.yago.app.demo.client.model.response.ListProductsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@Slf4j
public class ProductService {

    RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product createProductAndListProducts(Product product) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth("A21AAIPLyCLmmxw_WDOoprwfBm9iUvYy7-YQqDfw7RW9ICvByjlAEK3hFX3zIWNsDISSnL1Xfloc8o0eTvAM-WL4DguAopxOQ");

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<ListProductsResponse> response = restTemplate.exchange("https://api-m.sandbox.paypal.com/v1/catalogs/products?page_size=10&page=1&total_required=true", HttpMethod.GET, requestEntity, ListProductsResponse.class);

        LOG.info("response" + response);

        return Product.builder()
            .id(product.getId())
            .description(product.getDescription())
            .type(product.getType())
            .category(product.getCategory())
            .imageUrl(product.getImageUrl())
            .build();
    }
}
