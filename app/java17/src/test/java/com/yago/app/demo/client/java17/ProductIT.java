package com.yago.app.demo.client.java17;

import com.github.javafaker.Faker;
import com.yago.app.demo.client.java17.model.Product;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
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

        LOG.info("useLessUUID : " + useLessUUID);

        String randomUUID = UUID.randomUUID().toString();

        LOG.info("randomUUID : " + randomUUID);

        Random random = new Random();

        Integer randomInteger = Math.abs(random.nextInt(100000));
        Integer randomInteger2 = Math.abs(random.nextInt());

        LOG.info("randomInteger : " + randomInteger);
        LOG.info("randomInteger2 : " + randomInteger2);

        Long randomLong = Math.abs(random.nextLong());
        Long randomLong2 = Math.abs(random.nextLong());

        LOG.info("randomLong : " + randomLong);
        LOG.info("randomLong2 : " + randomLong2);

        Float randomFloat = random.nextFloat();
        Float randomFloat2 = random.nextFloat();

        LOG.info("randomFloat : " + randomFloat);
        LOG.info("randomFloat2 : " + randomFloat2);

        Double randomDouble = random.nextDouble();
        Double randomDouble2 = random.nextDouble();

        LOG.info("randomDouble : " + randomDouble);
        LOG.info("randomDouble2 : " + randomDouble2);

        Double randomDouble3 = Math.random();

        LOG.info("randomDouble3 : " + randomDouble3);

        Boolean randomBoolean = random.nextBoolean();
        Boolean randomBoolean2 = random.nextBoolean();

        LOG.info("randomBoolean : " + randomBoolean);
        LOG.info("randomBoolean2 : " + randomBoolean2);

        Faker faker = new Faker();

        String streetName = faker.address().streetName();
        String number = faker.address().buildingNumber();
        String city = faker.address().city();
        String country = faker.address().country();

        LOG.info("streetName : " + streetName);
        LOG.info("number : " + number);
        LOG.info("city : " + city);
        LOG.info("country : " + country);

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

        Assertions.assertEquals(randomUUID, responseProduct.getId(), "Id is equals ?");
        Assertions.assertEquals(testName, responseProduct.getName(), "Name is equals ?");
        Assertions.assertEquals(testDescription, responseProduct.getDescription(), "Description is equals ?");

    }
}
