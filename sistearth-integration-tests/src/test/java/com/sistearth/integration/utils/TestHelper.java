package com.sistearth.integration.utils;

import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.HttpClientConfig.httpClientConfig;
import static com.jayway.restassured.config.RestAssuredConfig.newConfig;
import static com.jayway.restassured.config.SSLConfig.sslConfig;

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
}
