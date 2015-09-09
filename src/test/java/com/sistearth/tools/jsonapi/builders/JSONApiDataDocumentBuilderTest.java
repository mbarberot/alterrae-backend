package com.sistearth.tools.jsonapi.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistearth.core.models.User;
import com.sistearth.core.serializers.JSONApiUserBuilder;
import com.sistearth.tools.jsonapi.JSONApi;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.Map;

import static com.sistearth.test.TestUtils.createUser;
import static com.sistearth.test.TestUtils.serialize;
import static com.sistearth.tools.jsonapi.JSONApi.newAttributes;
import static com.sistearth.tools.jsonapi.JSONApi.newData;
import static com.sistearth.tools.jsonapi.JSONApi.newDataDocument;

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