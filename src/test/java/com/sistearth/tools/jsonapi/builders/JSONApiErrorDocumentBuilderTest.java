package com.sistearth.tools.jsonapi.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistearth.tools.jsonapi.JSONApi;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.sistearth.tools.jsonapi.JSONApi.Error.newError;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JSONApiErrorDocumentBuilderTest {
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
    }

    @Test
    public void testError() throws Exception {
        Map<String, Object> jsonApiObject = JSONApi.newErrorDocument().error(
                newError()
                        .status("400")
                        .title("Invalid foo")
        ).build();

        String expectedJson = "{ errors : [{ " +
                "status: '400', title: 'Invalid foo'" +
                "}]}";
        assertEquals(expectedJson, mapper.writeValueAsString(jsonApiObject), STRICT);
    }
}
