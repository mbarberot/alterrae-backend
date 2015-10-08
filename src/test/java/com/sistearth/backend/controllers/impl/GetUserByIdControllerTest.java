package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.payloads.impl.EmptyPayload;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.views.View;
import com.sistearth.backend.views.ViewException;
import com.sistearth.test.TestUtils;
import org.apache.commons.lang.NotImplementedException;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetUserByIdControllerTest {

    @Test
    public void getUserSuccess() throws Exception {
        User user = TestUtils.createUser(0, "Jon", "winterfell", "jon@snow.com");

        ModelManager<User> userManager = mock(TestUserManager.class);
        when(userManager.getById(0)).thenReturn(user);

        View<User> userView = mock(TestUserView.class);
        when(userView.renderBean(user)).thenReturn(user.toString());

        assertEquals(
                new Answer(200, user.toString()),
                new GetUserByIdController(userManager, userView).process(new EmptyPayload(), newHashMap(of("id", "0")))
        );
    }

    private class TestUserManager implements ModelManager<User> {
        @Override
        public List<User> getAll() throws ModelException {
            throw new NotImplementedException();
        }

        @Override
        public User getById(int id) throws ModelException {
            throw new NotImplementedException();
        }

        @Override
        public User create(User entity) throws ModelException {
            throw new NotImplementedException();
        }

        @Override
        public boolean exists(int id) throws ModelException {
            throw new NotImplementedException();
        }
    }

    private class TestUserView implements View<User> {
        @Override
        public String renderBean(User bean) throws ViewException {
            throw new NotImplementedException();
        }
    }
}