package com.alterrae.spark.extractors;

import com.alterrae.api.payloads.TokenPayload;
import com.alterrae.view.request.PayloadException;
import org.junit.Test;
import spark.Request;

import static org.junit.Assert.*;

public class TokenPayloadExtractorTest {
    @Test
    public void extractPayload_Ok() throws Exception {
        Request request = RequestMock.builder()
                .addHeader("Authorization", "Bearer secret-token")
                .build();

        assertEquals(
                new TokenPayload("secret-token"),
                new TokenPayloadExtractor().extractPayload(request)
        );
    }

    @Test(expected = PayloadException.class)
    public void extractPayload_EmptyToken() throws Exception {
        Request request = RequestMock.builder()
                .addHeader("Authorization", "Bearer")
                .build();

        new TokenPayloadExtractor().extractPayload(request);
    }

    @Test(expected = PayloadException.class)
    public void extractPayload_NoHeader() throws Exception {
        Request request = RequestMock.builder().build();

        new TokenPayloadExtractor().extractPayload(request);
    }

    @Test(expected = PayloadException.class)
    public void extractPayload_IncorrectHeader() throws Exception {
        Request request = RequestMock.builder()
                .addHeader("Authorization", "Foo secret-token")
                .build();

        new TokenPayloadExtractor().extractPayload(request);
    }
}