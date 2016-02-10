package com.sistearth.integration.index;

import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.HttpClientConfig.httpClientConfig;
import static com.jayway.restassured.config.RestAssuredConfig.newConfig;
import static com.jayway.restassured.config.SSLConfig.sslConfig;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetTest {
    @Test
    public void testGetIndex() throws Exception {
        restApi()
                .when()
                .get("/")
                .then()
                .body("message", equalTo("Welcome to Sistearth v4 REST API."));
    }

    private RequestSpecification restApi() {
        String keystorePath = this.getClass().getResource("/ssl/keystore").getPath();
        String keystorePassword = "sistearth";
        return given()
                .baseUri("https://api.sistearth.com")
                .config(newConfig()
                        .httpClient(httpClientConfig()
                                .setParam("javax.net.ssl.trustStore", keystorePath)
                                .setParam("javax.net.ssl.trustStorePassword", keystorePassword)
                        )
                        .sslConfig(sslConfig()
                                .keystore(keystorePath, keystorePassword)
                        )
                );
    }
}
