package com.sistearth.spark.extractors;

import com.sistearth.api.payloads.UserDeletePayload;
import com.sistearth.view.request.PayloadException;
import org.junit.Test;
import spark.Request;

import static org.junit.Assert.assertEquals;

public class UserDeletePayloadExtractorTest {
    @Test
    public void testExtractValidPayload() throws Exception {
        Request body = RequestMock.builder()
                .body("{\"data\":{\"attributes\":{\"email\":\"ignored@ignored.com\",\"username\":\"Ignored\",\"password\":\"ignored\",\"actualPassword\":\"supersecretpassword\"}}}")
                .build();

        assertEquals(
                new UserDeletePayload("supersecretpassword"),
                new UserDeletePayloadExtractor().extractPayload(body)
        );
    }

    @Test
    public void testExtractUserInvalidPayload_EmptyJson() throws Exception {
        Request request = RequestMock.builder()
                .body("{}")
                .build();

        assertEquals(
                new UserDeletePayload(),
                new UserDeletePayloadExtractor().extractPayload(request)
        );
    }

    @Test
    public void testExtractUserInvalidPayload_MissingActualPassword() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"data\":{\"attributes\":{\"email\":\"jon@new.com\",\"username\":\"Jon\",\"password\":\"newsecret\"}}}")
                .build();

        assertEquals(
                new UserDeletePayload(),
                new UserDeletePayloadExtractor().extractPayload(request)
        );
    }

    @Test(expected = PayloadException.class)
    public void testFailExtractUserPayload_IncorrectJson() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"data\":{\"attributes\":{\"email\":\"jon@new.com\",\"username\":\"Jon\",\"password\":\"newsecret\",\"actualPassword\":\"jonsecret\"")
                .build();
        new UserDeletePayloadExtractor().extractPayload(request);
    }
}