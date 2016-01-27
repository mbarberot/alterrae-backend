package com.sistearth.jsonutils.jsonapi;

import com.sistearth.db.beans.Post;
import com.sistearth.db.beans.User;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.GregorianCalendar;

import static com.google.common.collect.Lists.newArrayList;

public class PostBuilderTest extends JsonTest {
    @Test
    public void testBuildSingle() throws Exception {
        JSONAssert.assertEquals(
                "{'data':{'relationships':{'author':{'data':{'type':'users','id':'5'}}},'attributes':{'created_at':'2016-02-01 00:00:00','title':'Foo','body':'Lorem ipsum'},'id':'0','type':'posts'},'included':[{'attributes':{'email':'jon@dot.com','username':'John'},'id':'5','type':'users'}]}",
                jsonify(
                        new PostBuilder().build(
                                new Post(0, "Foo", "Lorem ipsum", new GregorianCalendar(2016,1,1).getTime(), 5),
                                new User(5, "John", "jonsecret", "jon@dot.com")
                        )
                ),
                JSONCompareMode.STRICT
        );
    }

    @Test
    public void testBuildMultiple() throws Exception {
        JSONAssert.assertEquals(
                "{'data':[{'relationships':{'author':{'data':{'type':'users','id':'5'}}},'attributes':{'created_at':'2016-02-01 00:00:00','title':'Foo','body':'Lorem ipsum'},'id':'0','type':'posts'},{'relationships':{'author':{'data':{'type':'users','id':'3'}}},'attributes':{'created_at':'2016-03-02 00:00:00','title':'Bar','body':'Dolor sit amet'},'id':'0','type':'posts'}],'included':[{'attributes':{'email':'jon@dot.com','username':'John'},'id':'5','type':'users'},{'attributes':{'email':'jane@dot.com','username':'Jane'},'id':'3','type':'users'}]}",
                jsonify(
                        new PostBuilder().build(
                                newArrayList(
                                        new Post(0, "Foo", "Lorem ipsum", new GregorianCalendar(2016,1,1).getTime(), 5),
                                        new Post(0, "Bar", "Dolor sit amet", new GregorianCalendar(2016,2,2).getTime(), 3)
                                ),
                                newArrayList(
                                        new User(5, "John", "jonsecret", "jon@dot.com"),
                                        new User(3, "Jane", "janesecret", "jane@dot.com")
                                )
                        )
                ),
                JSONCompareMode.STRICT
        );
    }

}