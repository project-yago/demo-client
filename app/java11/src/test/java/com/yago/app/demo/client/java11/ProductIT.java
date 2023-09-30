package com.yago.app.demo.client.java11;

import com.github.javafaker.Faker;
import com.yago.app.demo.client.java11.model.Product;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@Slf4j
public class ProductIT extends AbstractIntegrationTests {

    @Test
    public void createProductAndListProductsTests() {

        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.now();

        LOG.debug("localDateTime1 : " + localDateTime1);
        LOG.debug("localDateTime2 : " + localDateTime2);

        String useLessUUID = UUID.randomUUID().toString();

        LOG.debug("useLessUUID : " + useLessUUID);

        String randomUUID = UUID.randomUUID().toString();

        LOG.debug("randomUUID : " + randomUUID);

        Random random = new Random();

        Integer randomInteger = Math.abs(random.nextInt(100000));
        Integer randomInteger2 = Math.abs(random.nextInt());

        LOG.debug("randomInteger : " + randomInteger);
        LOG.debug("randomInteger2 : " + randomInteger2);

        Long randomLong = Math.abs(random.nextLong());
        Long randomLong2 = Math.abs(random.nextLong());

        LOG.debug("randomLong : " + randomLong);
        LOG.debug("randomLong2 : " + randomLong2);

        Float randomFloat = random.nextFloat();
        Float randomFloat2 = random.nextFloat();

        LOG.debug("randomFloat : " + randomFloat);
        LOG.debug("randomFloat2 : " + randomFloat2);

        Double randomDouble = random.nextDouble();
        Double randomDouble2 = random.nextDouble();

        LOG.debug("randomDouble : " + randomDouble);
        LOG.debug("randomDouble2 : " + randomDouble2);

        Double randomDouble3 = Math.random();

        LOG.debug("randomDouble3 : " + randomDouble3);

        Boolean randomBoolean = random.nextBoolean();
        Boolean randomBoolean2 = random.nextBoolean();

        LOG.debug("randomBoolean : " + randomBoolean);
        LOG.debug("randomBoolean2 : " + randomBoolean2);

        Faker faker = new Faker();

        String streetName = faker.address().streetName();
        String number = faker.address().buildingNumber();
        String city = faker.address().city();
        String country = faker.address().country();

        LOG.debug("streetName : " + streetName);
        LOG.debug("number : " + number);
        LOG.debug("city : " + city);
        LOG.debug("country : " + country);

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

        LOG.debug("response : " + response.asPrettyString());

        Product responseProduct = response.getBody().as(Product.class);

        Assertions.assertEquals(randomUUID, responseProduct.getId(), "Id is equals ?");
        Assertions.assertEquals(testName, responseProduct.getName(), "Name is equals ?");
        Assertions.assertEquals(testDescription, responseProduct.getDescription(), "Description is equals ?");

    }
}
