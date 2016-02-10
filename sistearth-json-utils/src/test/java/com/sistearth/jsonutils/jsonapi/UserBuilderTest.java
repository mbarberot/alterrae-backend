package com.sistearth.jsonutils.jsonapi;

import com.sistearth.db.beans.User;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static com.google.common.collect.Lists.newArrayList;

public class UserBuilderTest extends JsonTest {

    @Test
    public void testBuildSingle() throws Exception {
        JSONAssert.assertEquals(
                "{'data':{'attributes':{'email':'jon@dot.com','username':'Jon'},'id':'1','type':'users'}}",
                jsonify(new UserBuilder().build(new User(1, "Jon", "jonsecret", "jon@dot.com"))),
                JSONCompareMode.STRICT
        );
    }

    @Test
    public void testBuildMultiple() throws Exception {
        JSONAssert.assertEquals(
                "{'data':[{'attributes':{'email':'jon@dot.com','username':'Jon'},'id':'3','type':'users'},{'attributes':{'email':'jane@dot.com','username':'Jane'},'id':'5','type':'users'}]}",
                jsonify(new UserBuilder().build(newArrayList(
                        new User(3, "Jon", "jonsecret", "jon@dot.com"),
                        new User(5, "Jane", "janesecret", "jane@dot.com")
                ))),
                JSONCompareMode.STRICT
        );
    }
}