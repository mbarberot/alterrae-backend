package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.payloads.extractors.impl.UserPayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.UserPayload;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.utils.TestUserManager;
import org.junit.Test;

import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class PostUserControllerTest {
    @Test
    public void testProcess() throws Exception {
        User user = new User();
        user.setUsername("Jon");
        user.setPassword("winterfell");
        user.setEmail("jon@snow.com");

        UserPayload payload = mock(UserPayload.class);
        doReturn(true).when(payload).isValid();
        doReturn(user).when(payload).getUser();

        ModelManager<User> userManager = spy(TestUserManager.class);
        doNothing().when(userManager).create(anyObject());

        assertEquals(
                new Answer(200, ""),
                new PostUserController(userManager, new UserPayloadExtractor())
                        .process(payload, emptyMap())
        );

        verify(userManager, times(1)).create(anyObject());
    }
}
