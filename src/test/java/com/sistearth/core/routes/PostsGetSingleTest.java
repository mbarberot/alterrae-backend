package com.sistearth.core.routes;

import com.sistearth.api.handler.Answer;
import com.sistearth.api.handler.Handler;
import com.sistearth.core.database.PostManager;
import com.sistearth.core.models.Post;
import com.sistearth.core.serializers.PostSerializer;
import org.junit.Test;
import org.mockito.Mockito;

import static com.google.common.collect.Lists.newArrayList;
import static com.sistearth.utils.TestUtils.createPost;
import static org.junit.Assert.assertEquals;

public class PostsGetSingleTest {
    @Test
    public void testGet() throws Exception {
        Post post = createPost("Lorem ipsum", "Dolor sit amet, consectetur adipiscing elit.");

        PostManager model = Mockito.mock(PostManager.class);
        Mockito.when(model.getById(post.getId())).thenReturn(post);

        String json = new PostSerializer().serialize(newArrayList(post));

        Handler handler = new PostsGetSingle(model);
        assertEquals(new Answer(200, json), handler.process("0"));
    }
}