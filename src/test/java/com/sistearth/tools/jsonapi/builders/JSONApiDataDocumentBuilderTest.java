package com.sistearth.tools.jsonapi.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistearth.tools.jsonapi.JSONApi;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.Map;

import static com.sistearth.tools.jsonapi.JSONApi.*;
import static com.sistearth.tools.jsonapi.JSONApi.Data.newAttributes;
import static com.sistearth.tools.jsonapi.JSONApi.Data.newData;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JSONApiDataDocumentBuilderTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
    }

    @Test
    public void testBasicData() throws Exception {
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
        assertEquals(expectedJson, mapper.writeValueAsString(jsonApiObject), STRICT);
    }

    @Test
    public void testDataWithRelationShip() throws Exception {
        Map<String, Object> jsonApiObject = newDataDocument().data(
                newData("0", "foo")
                        .attributes(
                                newAttributes()
                                        .add("cookie", "25")
                                        .add("shmoops", "bazinga")
                        )
                        .relationships(
                                JSONApi.Data.newRelationships()
                                        .addData("giggles", "1", "bar")
                        )
        ).build();

        String expectedJson = "{ data : { " +
                "type: 'foo', id: '0', " +
                "attributes: { cookie: '25', shmoops: 'bazinga' }, " +
                "relationships: { giggles: { data: [{ type: 'bar', id: '1' }]}} " +
                "}}";

        assertEquals(
                expectedJson,
                mapper.writeValueAsString(jsonApiObject),
                STRICT
        );
    }

}