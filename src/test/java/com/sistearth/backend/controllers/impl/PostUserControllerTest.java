package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.payloads.extractors.impl.UserPayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.UserPayload;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.utils.Error;
import com.sistearth.backend.utils.TestUserManager;
import com.sistearth.backend.views.impl.ErrorView.ErrorView;
import com.sistearth.backend.views.impl.JsonApiUserView;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.sistearth.backend.controllers.payloads.extractors.impl.UserPayloadExtractor.PayloadType.CREATION;
import static com.sistearth.backend.utils.TestUtils.createUser;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class PostUserControllerTest {
    @Test
    public void testSuccess() throws Exception {
        User payloadUser = new User();
        payloadUser.setUsername("Jon");
        payloadUser.setPassword("winterfell");
        payloadUser.setEmail("jon@snow.com");

        User databaseUser = createUser(1, "Jon", "winterfell", "jon@snow.com");

        UserPayload payload = mock(UserPayload.class);
        doReturn(true).when(payload).isValid();
        doReturn(payloadUser).when(payload).getEntity();

        ModelManager<User> userManager = spy(TestUserManager.class);
        doNothing().when(userManager).create(anyObject());
        doReturn(databaseUser).when(userManager).getBy(eq("username"), anyObject());

        JsonApiUserView exptectedView = new JsonApiUserView();
        exptectedView.setUser(databaseUser);

        assertEquals(
                new Answer(200, exptectedView.render()),
                new PostUserController(userManager, new UserPayloadExtractor(CREATION), new JsonApiUserView())
                        .process(payload, emptyMap())
        );

        verify(userManager, times(1)).create(anyObject());
    }

    @Test
    public void testPayloadNotValid() throws Exception {
        List<Error> errors = newArrayList(
                new Error("400", "username"),
                new Error("400", "password"),
                new Error("400", "email")
        );

        UserPayload payload = mock(UserPayload.class);
        doReturn(false).when(payload).isValid();
        doReturn(errors).when(payload).getErrors();

        ModelManager<User> userManager = mock(TestUserManager.class);

        ErrorView expectedView = new ErrorView();
        expectedView.setErrors(errors);

        assertEquals(
                new Answer(400, expectedView.render()),
                new PostUserController(userManager, new UserPayloadExtractor(CREATION), new JsonApiUserView())
                        .process(payload, emptyMap())
        );
    }

    @Test
    public void testUserAlreadyExists() throws Exception {
        User payloadUser = new User();
        payloadUser.setUsername("Jon");
        payloadUser.setPassword("winterfell");
        payloadUser.setEmail("jon@snow.com");

        UserPayload payload = mock(UserPayload.class);
        doReturn(true).when(payload).isValid();

        ModelManager<User> userManager = mock(TestUserManager.class);
        doThrow(new ModelException("foo", "username")).when(userManager).create(anyObject());

        ErrorView expectedView = new ErrorView();
        expectedView.setErrors(new Error("400", "username"));

        assertEquals(
                new Answer(400, expectedView.render()),
                new PostUserController(userManager, new UserPayloadExtractor(CREATION), new JsonApiUserView())
                        .process(payload, emptyMap())
        );
    }
}
