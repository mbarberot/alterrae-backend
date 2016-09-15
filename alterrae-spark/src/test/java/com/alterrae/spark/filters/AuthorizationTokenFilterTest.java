package com.alterrae.spark.filters;

import com.alterrae.spark.extractors.RequestMock;
import org.junit.Test;
import spark.HaltException;
import spark.Request;
import spark.Response;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class AuthorizationTokenFilterTest {

    @Test
    public void testAuthorizationHeaderPass() throws Exception {
        Request request = RequestMock.builder()
                .addHeader("Authorization", "Bearer some-token")
                .build();
        Response response = mock(Response.class);

        try {
            new AuthorizationTokenFilter().handle(request, response);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testOptionsMethodAlwaysPass() throws Exception {
        Request request = RequestMock.builder()
                .method("OPTIONS")
                .build();
        Response response = mock(Response.class);

        try {
            new AuthorizationTokenFilter().handle(request, response);
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = HaltException.class)
    public void testNoAuthorizationHeaderFail() throws Exception {
        Request request = RequestMock.builder().build();
        Response response = mock(Response.class);

        new AuthorizationTokenFilter().handle(request, response);
    }

}