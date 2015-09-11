package com.sistearth.tools.jsonapi.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.Map;

import static com.sistearth.tools.jsonapi.JSONApi.*;

public class JSONApiDataDocumentBuilderTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
    }

    @Test
    public void testBuild() throws Exception {

        Map<String, Object> jsonApiObject = newDataDocument().data(
                newData("0", "foo")
                        .attributes(
                                newAttributes()
                                        .add("bar", "25")
                                        .add("shmoops", "bazinga")
                        )
        ).build();

        String expectedJson = "{ data : { " +
                "type: 'foo', id: '0', " +
                "attributes: { bar: '25', shmoops: 'bazinga' } " +
                "}}";
        JSONAssert.assertEquals(expectedJson, mapper.writeValueAsString(jsonApiObject), JSONCompareMode.STRICT);
    }
}