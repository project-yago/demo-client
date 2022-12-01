package com.yago.app.demo.client.java8;

import com.yago.app.demo.client.java8.model.Product;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.Random;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@Slf4j
public class ProductIT extends AbstractIntegrationTests {

    @Test
    public void createProductAndListProductsTests() {

        String useLessUUID = UUID.randomUUID().toString();

        String randomUUID = UUID.randomUUID().toString();

        Random random = new Random();

        Integer randomInteger = Math.abs(random.nextInt(100000));
        Integer randomInteger2 = Math.abs(random.nextInt());

        Double randomDouble = random.nextDouble();
        Double randomDouble2 = random.nextDouble();

        Double randomDouble3 = Math.random();

        String testName = "Test Name " + randomInteger;
        String testDescription = "Test Description " + randomInteger;

        Product testProduct = Product.builder()
            .id(randomUUID)
            .name(testName)
            .description(testDescription)
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

        LOG.info("response : " + response.asPrettyString());

        Product responseProduct = response.getBody().as(Product.class);

        Assert.assertEquals("Id is equals ?", randomUUID, responseProduct.getId());
        Assert.assertEquals("Name is equals ?", testName, responseProduct.getName());
        Assert.assertEquals("Description is equals ?", testDescription, responseProduct.getDescription());

    }
}
