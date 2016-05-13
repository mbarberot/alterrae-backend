package com.sistearth.jsonutils.jsonapi;

import com.sistearth.api.beans.Error;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static com.google.common.collect.Lists.newArrayList;

public class ErrorBuilderTest extends JsonTest {

    @Test
    public void testBuildSingle() throws Exception {
        JSONAssert.assertEquals(
                "{'errors':[{'status':'500','title':'Internal server error'}]}",
                jsonify(
                        new ErrorBuilder().build(
                                newArrayList(new Error("500", "Internal server error"))
                        )
                ),
                JSONCompareMode.STRICT
        );
    }

    @Test
    public void testBuildMultiple() throws Exception {
        JSONAssert.assertEquals(
                "{'errors':[{'status':'500','title':'Internal server error'},{'status':'400','title':'Page not found'}]}",
                jsonify(
                        new ErrorBuilder().build(
                                newArrayList(
                                        new Error("500", "Internal server error"),
                                        new Error("400", "Page not found")
                                )
                        )
                ),
                JSONCompareMode.STRICT
        );
    }
}