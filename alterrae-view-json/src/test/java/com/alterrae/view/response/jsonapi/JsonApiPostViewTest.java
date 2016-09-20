package com.alterrae.view.response.jsonapi;

import com.alterrae.db.api.entity.Post;
import com.alterrae.db.api.entity.User;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.GregorianCalendar;

import static com.google.common.collect.Lists.newArrayList;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JsonApiPostViewTest {
    @Test
    public void testRenderSinglePost() throws Exception {

        User author = new User("57595f70fc13ae7c88001bec", "Jon", "winterfell", "jon@snow.com");
        JSONAssert.assertEquals(
                "{\"data\":{" +
                        "\"relationships\":{\"author\":{\"data\":{\"type\":\"users\",\"id\":\"57595f70fc13ae7c88001bec\"}}}," +
                        "\"attributes\":{\"creationDate\":\"1444687200000\",\"title\":\"Foo\",\"body\":\"Lorem ipsum.\"}," +
                        "\"id\":\"57595f70fc13ae7c88001bed\"," +
                        "\"type\":\"posts\"" +
                        "}," +
                        "\"included\":[{\"attributes\":{\"email\":\"jon@snow.com\",\"username\":\"Jon\"},\"id\":\"57595f70fc13ae7c88001bec\",\"type\":\"users\"}]}",
                new JsonApiPostView(
                        new Post("57595f70fc13ae7c88001bed", "Foo", "Lorem ipsum.", new GregorianCalendar(2015, 9, 13).getTime(), author),
                        author
                ).render(),
                STRICT
        );
    }   

    @Test
    public void testRenderMultiplePost() throws Exception {

        User user1 = new User("57595f70fc13ae7c88001bec", "Jon", "winterfell", "jon@snow.com");
        User user2 = new User("57595f70fc13ae7c88001bee", "Bran", "wolves", "bran@stark.com");
        JSONAssert.assertEquals(
                "{\"data\":[{" +
                        "\"relationships\":{\"author\":{\"data\":{\"type\":\"users\",\"id\":\"57595f70fc13ae7c88001bec\"}}}," +
                        "\"attributes\":{\"creationDate\":\"1444687200000\",\"title\":\"Foo\",\"body\":\"Lorem ipsum.\"}," +
                        "\"id\":\"57595f70fc13ae7c88001bed\"," +
                        "\"type\":\"posts\"" +
                        "},{" +
                        "\"relationships\":{\"author\":{\"data\":{\"type\":\"users\",\"id\":\"57595f70fc13ae7c88001bee\"}}}," +
                        "\"attributes\":{\"creationDate\":\"1447542000000\",\"title\":\"Bar\",\"body\":\"Dolor sit amet.\"}," +
                        "\"id\":\"57596105fc13ae0f3a001508\"," +
                        "\"type\":\"posts\"" +
                        "}]," +
                        "\"included\":[" +
                        "{\"attributes\":{\"email\":\"bran@stark.com\",\"username\":\"Bran\"},\"id\":\"57595f70fc13ae7c88001bee\",\"type\":\"users\"}," +
                        "{\"attributes\":{\"email\":\"jon@snow.com\",\"username\":\"Jon\"},\"id\":\"57595f70fc13ae7c88001bec\",\"type\":\"users\"}" +
                        "]}",
                new JsonApiPostView(
                        newArrayList(
                                new Post("57595f70fc13ae7c88001bed", "Foo", "Lorem ipsum.", new GregorianCalendar(2015, 9, 13).getTime(), user1),
                                new Post("57596105fc13ae0f3a001508", "Bar", "Dolor sit amet.", new GregorianCalendar(2015, 10, 15).getTime(), user2)
                        ),
                        newArrayList(user1, user2)
                ).render(),
                STRICT
        );
    }
}