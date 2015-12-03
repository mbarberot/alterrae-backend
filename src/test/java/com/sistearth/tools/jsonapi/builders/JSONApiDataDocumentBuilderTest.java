package com.sistearth.tools.jsonapi.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistearth.tools.jsonapi.JSONApi;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.sistearth.tools.jsonapi.JSONApi.Data.newAttributes;
import static com.sistearth.tools.jsonapi.JSONApi.Data.newData;
import static com.sistearth.tools.jsonapi.JSONApi.newDataDocument;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JSONApiDataDocumentBuilderTest {

    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
    }

    @Test
    public void testBasicSingleData() throws Exception {
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
    public void testBasicMultipleData() throws Exception {
        Map<String, Object> jsonApiObject = newDataDocument().data(
                newData("0", "foo")
                        .attributes(
                                newAttributes()
                                        .add("bar", "25")
                                        .add("shmoops", "bazinga")
                        ),
                newData("1", "nya")
                        .attributes(
                                newAttributes()
                                        .add("kb", "azerty")
                        )
        ).build();

        String expectedJson = "{ data : [{ " +
                "type: 'foo', id: '0', " +
                "attributes: { bar: '25', shmoops: 'bazinga' } " +
                "},{ " +
                "type: 'nya', id: '1', " +
                "attributes: { kb: 'azerty' }" +
                "}]}";

        assertEquals(expectedJson, mapper.writeValueAsString(jsonApiObject), STRICT);
    }

    @Test
    public void testDataWithRelationShip() throws Exception {
        Map<String, Object> jsonApiObject = newDataDocument()
                .data(
                        newData("0", "foo")
                                .attributes(
                                        newAttributes()
                                                .add("cookie", "25")
                                                .add("shmoops", "bazinga")
                                )
                                .relationships(
                                        JSONApi.Data.newRelationships()
                                                .addSingleData("giggles", "1", "bar")

                                )
                )
                .included(
                        newData("1", "bar")
                                .attributes(
                                        newAttributes()
                                                .add("cacao", "yes")
                                )
                )
                .build();

        String expectedJson =
                "{" +
                "   data : { " +
                "       type: 'foo', " +
                "       id: '0', " +
                "       attributes: { cookie: '25', shmoops: 'bazinga' }, " +
                "       relationships: { " +
                "           giggles: { data: { type: 'bar', id: '1' } }" +
                "       } " +
                "   }, " +
                "   included: [{ "   +
                "       type: 'bar', id: '1'," +
                "       attributes: { cacao: 'yes' } " +
                "   }]" +
                "}";

        assertEquals(
                expectedJson,
                mapper.writeValueAsString(jsonApiObject),
                STRICT
        );
    }

}