package com.sistearth.view.response.jsonapi;

import com.sistearth.api.beans.Post;
import com.sistearth.api.beans.User;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.GregorianCalendar;

import static com.google.common.collect.Lists.newArrayList;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JsonApiPostViewTest {
    @Test
    public void testRenderSinglePost() throws Exception {

        JSONAssert.assertEquals(
                "{\"data\":{\"relationships\":{\"author\":{\"data\":{\"type\":\"users\",\"id\":\"1\"}}},\"attributes\":{\"created_at\":\"2015-10-13 00:00:00\",\"title\":\"Foo\",\"body\":\"Lorem ipsum.\"},\"id\":\"1\",\"type\":\"posts\"},\"included\":[{\"attributes\":{\"email\":\"jon@snow.com\",\"username\":\"Jon\"},\"id\":\"1\",\"type\":\"users\"}]}",
                new JsonApiPostView(
                        new Post(1, "Foo", "Lorem ipsum.", new GregorianCalendar(2015, 9, 13).getTime(), 1),
                        new User(1, "Jon", "winterfell", "jon@snow.com")
                ).render(),
                STRICT
        );
    }

    @Test
    public void testRenderMultiplePost() throws Exception {

        JSONAssert.assertEquals(
                "{\"data\":[{\"relationships\":{\"author\":{\"data\":{\"type\":\"users\",\"id\":\"1\"}}},\"attributes\":{\"created_at\":\"2015-10-13 00:00:00\",\"title\":\"Foo\",\"body\":\"Lorem ipsum.\"},\"id\":\"1\",\"type\":\"posts\"},{\"relationships\":{\"author\":{\"data\":{\"type\":\"users\",\"id\":\"2\"}}},\"attributes\":{\"created_at\":\"2015-11-15 00:00:00\",\"title\":\"Bar\",\"body\":\"Dolor sit amet.\"},\"id\":\"2\",\"type\":\"posts\"}],\"included\":[{\"attributes\":{\"email\":\"jon@snow.com\",\"username\":\"Jon\"},\"id\":\"1\",\"type\":\"users\"},{\"attributes\":{\"email\":\"bran@stark.com\",\"username\":\"Bran\"},\"id\":\"2\",\"type\":\"users\"}]}",
                new JsonApiPostView(
                        newArrayList(
                                new Post(1, "Foo", "Lorem ipsum.", new GregorianCalendar(2015, 9, 13).getTime(), 1),
                                new Post(2, "Bar", "Dolor sit amet.", new GregorianCalendar(2015, 10, 15).getTime(), 2)
                        ),
                        newArrayList(
                                new User(1, "Jon", "winterfell", "jon@snow.com"),
                                new User(2, "Bran", "wolves", "bran@stark.com")
                        )
                ).render(),
                STRICT
        );
    }
}