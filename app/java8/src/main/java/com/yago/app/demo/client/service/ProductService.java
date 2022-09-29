package com.yago.app.demo.client.service;

import com.yago.app.demo.client.model.Product;
import com.yago.app.demo.client.model.response.ListProductsResponse;
import com.yago.app.demo.client.properties.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@Slf4j
public class ProductService {

    ApplicationProperties applicationProperties;
    RestTemplate restTemplate;

    public ProductService(ApplicationProperties applicationProperties, RestTemplate restTemplate) {
        this.applicationProperties = applicationProperties;
        this.restTemplate = restTemplate;

        LOG.info("\n************************************************\n ApplicationProperties : " + applicationProperties + "\n************************************************\n");
    }

    public Product createProductAndListProducts(final Product product) {

        String SANDBOX_API_PAYPAL = applicationProperties.getExternalAPI().getBaseURI();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

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

        LOG.info("Get product :" + getProductResponse.getBody());

        return createProductResponse.getBody();
    }
}
