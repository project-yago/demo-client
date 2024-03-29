package com.yago.app.demo.client.java8.service;

import com.yago.app.demo.client.java8.model.Product;
import com.yago.app.demo.client.java8.model.response.ListProductsResponse;
import com.yago.app.demo.client.java8.properties.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

        LOG.debug("\n************************************************\n ApplicationProperties : " + applicationProperties + "\n************************************************\n");
    }

    public Product createProductAndListProducts(final Product product) {

        LOG.debug("************ Product in input ************ : " + product);

        String SANDBOX_API_PAYPAL = applicationProperties.getExternalAPI().getBaseURI();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntityListProducts = new HttpEntity<>(headers);

        LOG.info("\n\n**********************************************\n Calling List Products API Endpoint ...\n**********************************************\n");

        ResponseEntity<ListProductsResponse> listProductResponse1 = restTemplate.exchange(
            SANDBOX_API_PAYPAL + "/v1/catalogs/products?page_size=100&page=1&total_required=true",
            HttpMethod.GET,
            requestEntityListProducts,
            ListProductsResponse.class);

        LOG.debug("list of products before creation :" + listProductResponse1.getBody().getProducts());

        LOG.info("\n\n**********************************************\n Number of products before creation : "
            + listProductResponse1.getBody().getTotalItems()
            + "\n**********************************************\n");

        HttpEntity<Product> requestEntityCreateProduct = new HttpEntity<>(product, headers);

        LOG.info("\n\n**********************************************\n Calling Create Product API Endpoint ...\n**********************************************\n");

        ResponseEntity<Product> createProductResponse = restTemplate.exchange(
            SANDBOX_API_PAYPAL + "/v1/catalogs/products",
            HttpMethod.POST,
            requestEntityCreateProduct,
            Product.class);

        Product createdProduct = createProductResponse.getBody();

        LOG.info("\n\n**********************************************\n Product created : " + createdProduct + "\n**********************************************\n");

        LOG.info("\n\n**********************************************\n Calling List Products API Endpoint ...\n**********************************************\n");

        ResponseEntity<ListProductsResponse> listProductResponse2 = restTemplate.exchange(
            SANDBOX_API_PAYPAL + "/v1/catalogs/products?page_size=100&page=1&total_required=true",
            HttpMethod.GET,
            requestEntityListProducts,
            ListProductsResponse.class);

        LOG.debug("list of products after creation :" + listProductResponse2.getBody().getProducts());

        LOG.info("\n\n**********************************************\n Number of products after creation : "
            + listProductResponse2.getBody().getTotalItems()
            + "\n**********************************************\n");

        LOG.info("\n\n**********************************************\n Calling Get Product API Endpoint ...\n**********************************************\n");

        HttpEntity<Void> requestEntityGetProduct = new HttpEntity<>(headers);

        String productIdToGet = product.getId() != null ? product.getId() : createdProduct.getId();

        ResponseEntity<Product> getProductResponse = restTemplate.exchange(
            SANDBOX_API_PAYPAL + "/v1/catalogs/products/" + productIdToGet,
            HttpMethod.GET,
            requestEntityGetProduct,
            Product.class);

        LOG.info("\n\n**********************************************\n Get product : " + getProductResponse.getBody() + "\n**********************************************\n");

        return createProductResponse.getBody();
    }
}
