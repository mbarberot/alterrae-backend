package com.sistearth.jsonutils.jsonapi;

import com.sistearth.db.api.entity.Post;
import com.sistearth.db.api.entity.User;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.GregorianCalendar;

import static com.google.common.collect.Lists.newArrayList;

public class PostBuilderTest extends JsonTest {
    @Test
    public void testBuildSingle() throws Exception {
        User author = new User("57595f70fc13ae7c88001be5", "John", "jonsecret", "jon@dot.com");
        JSONAssert.assertEquals(
                "{'data':{'relationships':{'author':{'data':{'type':'users','id':'57595f70fc13ae7c88001be5'}}},'attributes':{'created_at':'2016-02-01T00:00:00.000+0100','title':'Foo','body':'Lorem ipsum'},'id':'57595f70fc13ae7c88001be6','type':'posts'},'included':[{'attributes':{'email':'jon@dot.com','username':'John'},'id':'57595f70fc13ae7c88001be5','type':'users'}]}",
                jsonify(
                        new PostBuilder().build(
                                new Post("57595f70fc13ae7c88001be6", "Foo", "Lorem ipsum", new GregorianCalendar(2016, 1, 1).getTime(), author),
                                author
                        )
                ),
                JSONCompareMode.STRICT
        );
    }

    @Test
    public void testBuildMultiple() throws Exception {
        User user1 = new User("57595f70fc13ae7c88001be5", "John", "jonsecret", "jon@dot.com");
        User user2 = new User("57595f70fc13ae7c88001be7", "Jane", "janesecret", "jane@dot.com");
        JSONAssert.assertEquals(
                "{'data':[{'relationships':{'author':{'data':{'type':'users','id':'57595f70fc13ae7c88001be5'}}},'attributes':{'created_at':'2016-02-01T00:00:00.000+0100','title':'Foo','body':'Lorem ipsum'},'id':'57595f70fc13ae7c88001be6','type':'posts'},{'relationships':{'author':{'data':{'type':'users','id':'57595f70fc13ae7c88001be7'}}},'attributes':{'created_at':'2016-03-02T00:00:00.000+0100','title':'Bar','body':'Dolor sit amet'},'id':'57595f70fc13ae7c88001be7','type':'posts'}],'included':[{'attributes':{'email':'jon@dot.com','username':'John'},'id':'57595f70fc13ae7c88001be5','type':'users'},{'attributes':{'email':'jane@dot.com','username':'Jane'},'id':'57595f70fc13ae7c88001be7','type':'users'}]}",
                jsonify(
                        new PostBuilder().build(
                                newArrayList(
                                        new Post("57595f70fc13ae7c88001be6", "Foo", "Lorem ipsum", new GregorianCalendar(2016, 1, 1).getTime(), user1),
                                        new Post("57595f70fc13ae7c88001be7", "Bar", "Dolor sit amet", new GregorianCalendar(2016, 2, 2).getTime(), user2)
                                ),
                                newArrayList(user1, user2)
                        )
                ),
                JSONCompareMode.STRICT
        );
    }

}