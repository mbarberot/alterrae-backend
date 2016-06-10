package com.sistearth.jsonutils.jsonapi;

import com.sistearth.db.api.entity.User;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static com.google.common.collect.Lists.newArrayList;

public class UserBuilderTest extends JsonTest {

    @Test
    public void testBuildSingle() throws Exception {
        JSONAssert.assertEquals(
                "{'data':{'attributes':{'email':'jon@dot.com','username':'Jon'},'id':'57595f70fc13ae7c88001be9','type':'users'}}",
                jsonify(new UserBuilder().build(new User("57595f70fc13ae7c88001be9", "Jon", "jonsecret", "jon@dot.com"))),
                JSONCompareMode.STRICT
        );
    }

    @Test
    public void testBuildMultiple() throws Exception {
        JSONAssert.assertEquals(
                "{'data':[{'attributes':{'email':'jon@dot.com','username':'Jon'},'id':'57595f70fc13ae7c88001bea','type':'users'},{'attributes':{'email':'jane@dot.com','username':'Jane'},'id':'57595f70fc13ae7c88001beb','type':'users'}]}",
                jsonify(new UserBuilder().build(newArrayList(
                        new User("57595f70fc13ae7c88001bea", "Jon", "jonsecret", "jon@dot.com"),
                        new User("57595f70fc13ae7c88001beb", "Jane", "janesecret", "jane@dot.com")
                ))),
                JSONCompareMode.STRICT
        );
    }
}