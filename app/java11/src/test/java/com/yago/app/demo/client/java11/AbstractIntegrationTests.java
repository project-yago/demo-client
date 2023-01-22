package com.yago.app.demo.client.java11;

import io.restassured.RestAssured;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractIntegrationTests {


    @Value("${local.server.port}")
    private int port;

    @BeforeAll
    protected void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        String hostAPIProperty = System.getProperty("host.api");

        if (StringUtils.isNotEmpty(hostAPIProperty)) {
            System.out.println(" __     __                                          _                      _     _          _  ");
            System.out.println(" \\ \\   / /                    /\\                   | |                    | |   | |        | |");
            System.out.println("  \\ \\_/ /_ _  __ _  ___      /  \\   __ _  ___ _ __ | |_    ___ _ __   __ _| |__ | | ___  __| |");
            System.out.println("   \\   / _` |/ _` |/ _ \\    / /\\ \\ / _` |/ _ \\ '_ \\| __|  / _ \\ '_ \\ / _` | '_ \\| |/ _ \\/ _` |");
            System.out.println("    | | (_| | (_| | (_) |  / ____ \\ (_| |  __/ | | | |_  |  __/ | | | (_| | |_) | |  __/ (_| |");
            System.out.println("    |_|\\__,_|\\__, |\\___/  /_/    \\_\\__, |\\___|_| |_|\\__|  \\___|_| |_|\\__,_|_.__/|_|\\___|\\__,_|");
            System.out.println("              __/ |                 __/ |                                                     ");
            System.out.println("             |___/                 |___/                                                      ");
        } else {
            System.out.println(" __     __                                          _         _ _           _     _          _ ");
            System.out.println(" \\ \\   / /                    /\\                   | |       | (_)         | |   | |        | |");
            System.out.println("  \\ \\_/ /_ _  __ _  ___      /  \\   __ _  ___ _ __ | |_    __| |_ ___  __ _| |__ | | ___  __| |");
            System.out.println("   \\   / _` |/ _` |/ _ \\    / /\\ \\ / _` |/ _ \\ '_ \\| __|  / _` | / __|/ _` | '_ \\| |/ _ \\/ _` |");
            System.out.println("    | | (_| | (_| | (_) |  / ____ \\ (_| |  __/ | | | |_  | (_| | \\__ \\ (_| | |_) | |  __/ (_| |");
            System.out.println("    |_|\\__,_|\\__, |\\___/  /_/    \\_\\__, |\\___|_| |_|\\__|  \\__,_|_|___/\\__,_|_.__/|_|\\___|\\__,_|");
            System.out.println("              __/ |                 __/ |                                                      ");
            System.out.println("             |___/                 |___/                                                       ");
        }


    }
}
