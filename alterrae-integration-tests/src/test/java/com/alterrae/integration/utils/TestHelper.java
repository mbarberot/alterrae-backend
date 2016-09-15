package com.alterrae.integration.utils;

import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.HttpClientConfig.httpClientConfig;
import static com.jayway.restassured.config.RestAssuredConfig.newConfig;
import static com.jayway.restassured.config.SSLConfig.sslConfig;
import static java.lang.String.format;

public class TestHelper {
    public static RequestSpecification restApi() {
        String keystorePath = TestHelper.class.getResource("/ssl/keystore").getPath();
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

    public static RequestSpecification authRestApi(String username, String password) {
        String token = tryAuthenticate(username, password).extract().body().jsonPath().get("token");
        return restApi()
                .given()
                .header("Authorization", "Bearer " + token);
    }

    public static ValidatableResponse tryAuthenticate(String username, String password) {
        return restApi()
                .contentType("application/json")
                .content(loginData(username, password))
                .when()
                .post("/api/login")
                .then();
    }

    public static String loginData(String username, String password) {
        return format("{\"username\": \"%s\",\"password\": \"%s\"}", username, password);
    }
}
