package com.sistearth.core.routes;

import com.sistearth.api.handler.Answer;
import com.sistearth.api.handler.Handler;
import com.sistearth.api.serializer.Serializer;
import com.sistearth.core.database.PostModel;
import com.sistearth.core.models.Post;
import com.sistearth.utils.TestUtils;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;

import static com.google.common.collect.Lists.newArrayList;

public class PostsGetSingleTest extends TestCase {
    @Test
    public void testGet() throws Exception {
        Post post = TestUtils.createPost("Lorem ipsum", "Dolor sit amet, consectetur adipiscing elit.");

        PostModel model = Mockito.mock(PostModel.class);
        Mockito.when(model.getById(post.getId())).thenReturn(post);

        String json = new Serializer<Post>("posts").serialize(newArrayList(post));

        Handler handler = new PostsGetSingle(model);
        assertEquals(new Answer(200, json), handler.process("0"));
    }
}