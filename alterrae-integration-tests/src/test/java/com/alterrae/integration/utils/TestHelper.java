package com.alterrae.integration.utils;

import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.HttpClientConfig.httpClientConfig;
import static com.jayway.restassured.config.RestAssuredConfig.newConfig;
import static com.jayway.restassured.config.SSLConfig.sslConfig;
import static java.lang.String.format;

public class TestHelper {
    public static RequestSpecification restApi() {
        String keyStorePath = TestHelper.class.getResource("/ssl/keystore").getPath();
        String keyStorePassword = "alterrae";
        KeyStore keyStore = getKeyStore(keyStorePath, keyStorePassword);
        return given()
                .baseUri("https://api.alterrae.com")
                .config(newConfig()
                        .httpClient(httpClientConfig()
                                .setParam("javax.net.ssl.trustStore", keyStorePath)
                                .setParam("javax.net.ssl.trustStorePassword", keyStorePassword)
                                .setParam("javax.net.ssl.keyStore", keyStorePath)
                                .setParam("javax.net.ssl.keyStorePassword", keyStorePassword)
                        )
                        .sslConfig(sslConfig()
                                .keystore(keyStorePath, keyStorePassword)
                                .trustStore(keyStore)
                        )
                );
    }

    private static KeyStore getKeyStore(String keystorePath, String keystorePassword) {
        try {
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(new FileInputStream(keystorePath), keystorePassword.toCharArray());
            return keystore;
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
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
