package com.sistearth.backend.views.impl;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.GregorianCalendar;

import static com.sistearth.backend.utils.TestUtils.createPost;
import static com.sistearth.backend.utils.TestUtils.createUser;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JsonApiPostViewTest {
    @Test
    public void testRenderSinglePost() throws Exception {
        JsonApiPostView view = new JsonApiPostView();
        view.setPosts(createPost(1, "Foo", "Lorem ipsum.", new GregorianCalendar(2015,9,13).getTime(), 1));
        view.setAuthors(createUser(1, "Jon", "winterfell", "jon@snow.com"));

        JSONAssert.assertEquals(
                "{ \"data\" : { \"relationships\" : { \"author\" : { \"data\" : { \"type\" : \"users\", \"id\" : \"1\" } } }, \"attributes\" : { \"created_at\" : \"2015-10-13 00:00:00\", \"title\" : \"Foo\", \"body\" : \"Lorem ipsum.\" }, \"id\" : \"1\", \"type\" : \"posts\" } , \"included\" : [ { \"attributes\" : { \"email\" : \"jon@snow.com\", \"username\" : \"Jon\" }, \"id\" : \"1\", \"type\" : \"users\" } ] }",
                view.render(),
                STRICT
        );
    }

    @Test
    public void testRenderMultiplePost() throws Exception {
        JsonApiPostView view = new JsonApiPostView();
        view.setPosts(
                createPost(1, "Foo", "Lorem ipsum.", new GregorianCalendar(2015,9,13).getTime(), 1),
                createPost(2, "Bar", "Dolor sit amet.", new GregorianCalendar(2015,10,15).getTime(), 2)
        );
        view.setAuthors(
                createUser(1, "Jon", "winterfell", "jon@snow.com"),
                createUser(2, "Bran", "wolves", "bran@stark.com")
        );

        JSONAssert.assertEquals(
                "{ \"data\" : [ { \"relationships\" : { \"author\" : { \"data\" : { \"type\" : \"users\", \"id\" : \"1\" } } }, \"attributes\" : { \"created_at\" : \"2015-10-13 00:00:00\", \"title\" : \"Foo\", \"body\" : \"Lorem ipsum.\" }, \"id\" : \"1\", \"type\" : \"posts\" }, { \"relationships\" : { \"author\" : { \"data\" : { \"type\" : \"users\", \"id\" : \"2\" } } }, \"attributes\" : { \"created_at\" : \"2015-11-15 00:00:00\", \"title\" : \"Bar\", \"body\" : \"Dolor sit amet.\" }, \"id\" : \"2\", \"type\" : \"posts\" } ], \"included\" : [ { \"attributes\" : { \"email\" : \"jon@snow.com\", \"username\" : \"Jon\" }, \"id\" : \"1\", \"type\" : \"users\" }, { \"attributes\" : { \"email\" : \"bran@stark.com\", \"username\" : \"Bran\" }, \"id\" : \"2\", \"type\" : \"users\" } ] }",
                view.render(),
                STRICT
        );
    }
}