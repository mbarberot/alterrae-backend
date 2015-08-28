package com.sistearth.core.routes;

import com.sistearth.api.handler.Answer;
import com.sistearth.api.handler.Handler;
import com.sistearth.core.database.PostManager;
import com.sistearth.core.models.Post;
import com.sistearth.core.serializers.PostSerializer;
import com.sistearth.utils.TestUtils;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

public class PostsGetAllTest {
    @Test
    public void testGet() throws Exception {
        List<Post> posts = newArrayList(
                TestUtils.createPost("Lorem ipsum", "Dolor sit amet, consectetur adipiscing elit."),
                TestUtils.createPost("Donec a diam lectus", "Sed sit amet ipsum mauris")
        );

        PostManager model = Mockito.mock(PostManager.class);
        Mockito.when(model.getAll()).thenReturn(posts);

        String json = new PostSerializer().serialize(posts);

        Handler handler = new PostsGetAll(model);
        assertEquals(new Answer(200, json), handler.process(null)); // Todo replace null
    }

}