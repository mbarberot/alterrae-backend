package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.payloads.impl.EmptyPayload;
import com.sistearth.backend.models.beans.Post;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.utils.TestPostManager;
import com.sistearth.backend.utils.TestUserManager;
import com.sistearth.backend.views.PostView;
import com.sistearth.backend.views.impl.JsonApiPostView;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;

import static com.google.common.collect.Lists.newArrayList;
import static com.sistearth.backend.utils.TestUtils.createPost;
import static com.sistearth.backend.utils.TestUtils.createUser;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAllPostsControllerTest {
    @Test
    public void testProcess() throws Exception {
        Post post0 = createPost(0, "Foo", "Lorem ipsum", new Date(), 1);
        Post post1 = createPost(1, "Bar", "Dolor sit amet", new Date(), 5);

        User author1 = createUser(1, "Jon", "winterfell", "jon@snow.com");
        User author5 = createUser(5, "Bran", "wolf", "bran@snow.com");

        PostView expectedView = new JsonApiPostView();
        expectedView.setPosts(post0, post1);
        expectedView.setAuthors(author1, author5);

        ModelManager<Post> postManager = mock(TestPostManager.class);
        when(postManager.getAll()).thenReturn(newArrayList(post0, post1));

        ModelManager<User> userManager = mock(TestUserManager.class);
        when(userManager.getAll()).thenReturn(newArrayList(author1, author5));
        when(userManager.getById(post0.getAuthor())).thenReturn(author1);
        when(userManager.getById(post1.getAuthor())).thenReturn(author5);

        assertEquals(
                new Answer(200, expectedView.render()),
                new GetAllPostsController(postManager, userManager, new JsonApiPostView())
                        .process(new EmptyPayload(), Collections.emptyMap())
        );
    }
}
