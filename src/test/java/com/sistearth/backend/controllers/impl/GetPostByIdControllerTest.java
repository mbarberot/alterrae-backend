package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.payloads.impl.EmptyPayload;
import com.sistearth.backend.models.beans.Post;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.utils.TestPostManager;
import com.sistearth.backend.utils.TestUserManager;
import com.sistearth.backend.views.PostView;
import com.sistearth.backend.views.impl.JsonApiPostView;
import com.sistearth.backend.views.impl.error.ErrorView;
import com.sistearth.backend.views.impl.error.SimpleErrorView;
import org.junit.Test;

import java.util.Date;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;
import static com.sistearth.backend.utils.TestUtils.createPost;
import static com.sistearth.backend.utils.TestUtils.createUser;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetPostByIdControllerTest {
    @Test
    public void testSuccess() throws Exception {
        User user = createUser(1, "Jon", "winterfell", "jon@snow.com");
        Post post = createPost(0, "Foo", "Lorem ipsum", new Date(), user.getId());

        ModelManager<Post> postManager = mock(TestPostManager.class);
        when(postManager.getById(0)).thenReturn(post);

        ModelManager<User> userManager = mock(TestUserManager.class);
        when(userManager.getById(1)).thenReturn(user);

        PostView expectedView = new JsonApiPostView();
        expectedView.setPosts(post);
        expectedView.setAuthors(user);

        assertEquals(
                new Answer(200, expectedView.render()),
                new GetPostByIdController(postManager, userManager, new JsonApiPostView())
                        .process(new EmptyPayload(), newHashMap(of(":id", "0")))
        );
    }

    @Test
    public void testPostNotFound() throws Exception {
        ModelManager<Post> postManager = mock(TestPostManager.class);
        when(postManager.getById(anyInt())).thenThrow(new ModelException("foo"));

        ModelManager<User> userManager = mock(TestUserManager.class);

        ErrorView expectedView = new SimpleErrorView("404");

        assertEquals(
                new Answer(404, expectedView.render()),
                new GetPostByIdController(postManager, userManager, new JsonApiPostView())
                        .process(new EmptyPayload(), newHashMap(of(":id", "0")))
        );

    }
}
