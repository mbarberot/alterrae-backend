package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.payloads.impl.EmptyPayload;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.utils.TestUserManager;
import com.sistearth.backend.utils.TestUtils;
import com.sistearth.backend.views.UserView;
import com.sistearth.backend.views.impl.JsonApiUserView;
import com.sistearth.backend.views.impl.error.SimpleErrorView;
import org.junit.Test;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetUserByIdControllerTest {

    @Test
    public void getUserSuccess() throws Exception {
        User user = TestUtils.createUser(0, "Jon", "winterfell", "jon@snow.com");

        ModelManager<User> userManager = mock(TestUserManager.class);
        when(userManager.getById(0)).thenReturn(user);

        UserView expectedView = new JsonApiUserView();
        expectedView.setUser(user);

        assertEquals(
                new Answer(200, expectedView.render()),
                new GetUserByIdController(userManager, new JsonApiUserView())
                        .process(new EmptyPayload(), newHashMap(of(":id", "0")))
        );
    }

    @Test
    public void getUserNotFound() throws Exception {

        ModelManager<User> userManager = mock(TestUserManager.class);
        when(userManager.getById(anyInt())).thenThrow(new ModelException("foo"));

        assertEquals(
                new Answer(404, new SimpleErrorView("404").render()),
                new GetUserByIdController(userManager, new JsonApiUserView())
                        .process(new EmptyPayload(), newHashMap(of(":id", "0")))
        );
    }

}