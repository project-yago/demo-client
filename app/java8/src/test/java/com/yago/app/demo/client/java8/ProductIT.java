package com.yago.app.demo.client.java8;

import com.yago.app.demo.client.java8.model.Product;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

//import java.util.UUID;

import static io.restassured.RestAssured.given;

@Slf4j
public class ProductIT extends AbstractIntegrationTests {

    @Test
    public void createProductAndListProductsTests() {

        Product testProduct = Product.builder()
            //.id(UUID.randomUUID().toString())
            //.id("123456789")
            .name("Test Name")
            .description("Test Description")
            .type("SERVICE")
            .category("SOFTWARE")
            .imageUrl("https://example.com/streaming.jpg")
            .build();

        Response response =
            given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(testProduct)
                .post("products")
                .then()
                .statusCode(200)
                .extract().response();

        LOG.info("response : " + response.asString());

    }
}
