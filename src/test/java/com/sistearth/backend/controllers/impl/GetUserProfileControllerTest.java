package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.payloads.impl.TokenPayload;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.utils.TestUserManager;
import com.sistearth.backend.utils.TestUtils;
import com.sistearth.backend.utils.TokenManager;
import com.sistearth.backend.views.UserView;
import com.sistearth.backend.views.impl.JsonApiUserView;
import org.junit.Test;

import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetUserProfileControllerTest {
    @Test
    public void getUserSuccess() throws Exception {
        User user = TestUtils.createUser(0, "Jon", "winterfell", "jon@snow.com");

        ModelManager<User> userManager = mock(TestUserManager.class);
        when(userManager.getBy("username", "Jon")).thenReturn(user);

        UserView expectedView = new JsonApiUserView();
        expectedView.setUser(user);

        String token = new TokenManager().createToken(user);
        TokenPayload tokenPayload = mock(TokenPayload.class);
        when(tokenPayload.getToken()).thenReturn(token);

        assertEquals(
                new Answer(200, expectedView.render()),
                new GetUserProfileController(userManager, new JsonApiUserView())
                        .process(tokenPayload, emptyMap())
        );
    }
}
