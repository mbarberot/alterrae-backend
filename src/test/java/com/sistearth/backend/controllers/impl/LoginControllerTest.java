package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.payloads.extractors.impl.LoginPayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.LoginPayload;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelException;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.utils.TestUserManager;
import com.sistearth.backend.utils.TokenManager;
import com.sistearth.backend.views.impl.LoginView;
import com.sistearth.backend.views.impl.error.SimpleErrorView;
import org.junit.Test;

import static com.sistearth.backend.utils.Errors.Login.BAD_CREDENTIALS;
import static com.sistearth.backend.utils.TestUtils.createUser;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class LoginControllerTest {
    @Test
    public void testLoginSuccess() throws Exception {
        User databaseUser = createUser(1, "Jon", "winterfell", "jon@snow.com");

        LoginPayload payload = mock(LoginPayload.class);
        doReturn(true).when(payload).isValid();
        doReturn("Jon").when(payload).getUsername();
        doReturn("winterfell").when(payload).getPassword();

        ModelManager<User> userManager = mock(TestUserManager.class);
        doReturn(databaseUser).when(userManager).getBy("username", "Jon");

        TokenManager tokenManager = mock(TokenManager.class);
        doReturn("my-token").when(tokenManager).createToken(anyObject());

        LoginView exptectedView = new LoginView();
        exptectedView.setUser(databaseUser);
        exptectedView.setToken("my-token");

        assertEquals(
                new Answer(200, exptectedView.render()),
                new LoginController(userManager, tokenManager, new LoginPayloadExtractor(), new LoginView())
                        .process(payload, emptyMap())
        );

        verify(userManager, atLeastOnce()).getBy("username", "Jon");
    }

    @Test
    public void testLoginBadUsername() throws Exception {
        LoginPayload payload = mock(LoginPayload.class);
        doReturn(true).when(payload).isValid();
        doReturn("Foo").when(payload).getUsername();

        ModelManager<User> userManager = mock(TestUserManager.class);
        doThrow(new ModelException("bar")).when(userManager).getBy(eq("username"), anyString());

        TokenManager tokenManager = mock(TokenManager.class);
        doReturn("my-token").when(tokenManager).createToken(anyObject());

        assertEquals(
                new Answer(400, new SimpleErrorView("400", BAD_CREDENTIALS).render()),
                new LoginController(userManager, tokenManager, new LoginPayloadExtractor(), new LoginView())
                        .process(payload, emptyMap())
        );
    }

    @Test
    public void testLoginWrongPassword() throws Exception {
        User user = createUser(1, "Jon", "winterfell", "jon@snow.com");

        LoginPayload payload = mock(LoginPayload.class);
        doReturn(true).when(payload).isValid();
        doReturn("Jon").when(payload).getUsername();
        doReturn("wrong-password").when(payload).getPassword();

        ModelManager<User> userManager = mock(TestUserManager.class);
        doReturn(user).when(userManager).getBy(eq("username"), eq(user.getUsername()));

        TokenManager tokenManager = mock(TokenManager.class);
        doReturn("my-token").when(tokenManager).createToken(anyObject());

        assertEquals(
                new Answer(400, new SimpleErrorView("400", BAD_CREDENTIALS).render()),
                new LoginController(userManager, tokenManager, new LoginPayloadExtractor(), new LoginView())
                        .process(payload, emptyMap())
        );
    }
}
