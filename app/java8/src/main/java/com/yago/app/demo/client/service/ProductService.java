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

    private final static String SANDBOX_API_PAYPAL = "https://api-m.sandbox.paypal.com";

    RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product createProductAndListProducts(final Product product) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("A21AAL_7M9FiSIQqkRk1sdU5KG5aXfVLBzzxkq39nSiHQHibGZ52sw7xsBYg7QBBr4m4aaUP2uUXE34U4xUtWuWPkPvnMl2Mg");

        HttpEntity<Void> requestEntityListProducts = new HttpEntity<>(headers);

        ResponseEntity<ListProductsResponse> listProductResponse1 = restTemplate.exchange(
            SANDBOX_API_PAYPAL + "/v1/catalogs/products?page_size=100&page=1&total_required=true",
            HttpMethod.GET,
            requestEntityListProducts,
            ListProductsResponse.class);

        LOG.info("list of products before creation :" + listProductResponse1.getBody().getProducts());

        HttpEntity<Product> requestEntityCreateProduct = new HttpEntity<>(product, headers);

        ResponseEntity<Product> createProductResponse = restTemplate.exchange(
            SANDBOX_API_PAYPAL + "/v1/catalogs/products",
            HttpMethod.POST,
            requestEntityCreateProduct,
            Product.class);

        Product createdProduct = createProductResponse.getBody();

        LOG.info("Product created :" + createdProduct);

        ResponseEntity<ListProductsResponse> listProductResponse2 = restTemplate.exchange(
            SANDBOX_API_PAYPAL + "/v1/catalogs/products?page_size=100&page=1&total_required=true",
            HttpMethod.GET,
            requestEntityListProducts,
            ListProductsResponse.class);

        LOG.info("list of products after creation :" + listProductResponse2.getBody().getProducts());

        HttpEntity<Void> requestEntityGetProduct = new HttpEntity<>(headers);

        ResponseEntity<Product> getProductResponse = restTemplate.exchange(
            SANDBOX_API_PAYPAL + "/v1/catalogs/products/" + createdProduct.getId(),
            HttpMethod.GET,
            requestEntityGetProduct,
            Product.class);

        LOG.info("Get product :" + createProductResponse.getBody());

        return createProductResponse.getBody();
    }
}
