package com.sistearth.spark.extractors;

import com.sistearth.view.request.PayloadException;
import com.sistearth.view.request.payloads.UserCreationPayload;
import org.junit.Test;
import spark.Request;

import static com.sistearth.spark.extractors.UserPayloadExtractor.PayloadType.CREATION;
import static junit.framework.Assert.assertEquals;

public class UserPayloadExtractorTest {
    @Test
    public void testExtractUserValidPayload() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"data\":{\"attributes\":{\"email\":\"jon@dot.com\",\"username\":\"Jon\",\"password\":\"jonsecret\"}}}")
                .build();

        assertEquals(
                new UserCreationPayload("Jon", "jonsecret", "jon@dot.com"),
                new UserPayloadExtractor(CREATION).extractPayload(request)
        );
    }

    @Test
    public void testExtractUserInvalidPayload_EmptyJson() throws Exception {
        Request request = RequestMock.builder()
                .body("{}")
                .build();

        assertEquals(
                new UserCreationPayload(),
                new UserPayloadExtractor(CREATION).extractPayload(request)
        );
    }

    @Test
    public void testExtractUserInvalidPayload_MissingUsername() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"data\":{\"attributes\":{\"email\":\"jon@dot.com\",\"password\":\"jonsecret\"}}}")
                .build();

        UserCreationPayload expectedPayload = new UserCreationPayload();
        expectedPayload.setEmail("jon@dot.com");
        expectedPayload.setPassword("jonsecret");
        assertEquals(
                expectedPayload,
                new UserPayloadExtractor(CREATION).extractPayload(request)
        );
    }

    @Test
    public void testExtractUserInvalidPayload_MissingEmail() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"data\":{\"attributes\":{\"username\":\"jon\",\"password\":\"jonsecret\"}}}")
                .build();

        UserCreationPayload expectedPayload = new UserCreationPayload();
        expectedPayload.setUsername("jon");
        expectedPayload.setPassword("jonsecret");
        assertEquals(
                expectedPayload,
                new UserPayloadExtractor(CREATION).extractPayload(request)
        );
    }

    @Test
    public void testExtractUserInvalidPayload_MissingPassword() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"data\":{\"attributes\":{\"username\":\"jon\",\"email\":\"jon@dot.com\"}}}")
                .build();

        UserCreationPayload expectedPayload = new UserCreationPayload();
        expectedPayload.setUsername("jon");
        expectedPayload.setEmail("jon@dot.com");
        assertEquals(
                expectedPayload,
                new UserPayloadExtractor(CREATION).extractPayload(request)
        );
    }

    @Test(expected = PayloadException.class)
    public void testFailExtractUserPayload_IncorrectJson() throws Exception {
        Request request = RequestMock.builder()
                .body("{\"data\":{\"attributes\":{\"email\":\"jon@dot.com\",\"username\":\"Jon\",\"password\":\"jonsecret\"")
                .build();
        new UserPayloadExtractor(CREATION).extractPayload(request);
    }
}