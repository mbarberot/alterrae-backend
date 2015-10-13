package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.payloads.extractors.impl.LoginPayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.LoginPayload;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.utils.TestUserManager;
import com.sistearth.backend.views.impl.LoginView;
import org.junit.Test;

import static com.sistearth.backend.utils.TestUtils.createUser;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class LoginControllerTest {
    @Test
    public void testLoginSuccess() throws Exception {
        User payloadUser = new User();
        payloadUser.setUsername("Jon");
        payloadUser.setPassword("winterfell");

        User databaseUser = createUser(1, "Jon", "winterfell", "jon@snow.com");

        LoginPayload payload = mock(LoginPayload.class);
        doReturn(true).when(payload).isValid();
        doReturn(payloadUser).when(payload).getEntity();

        ModelManager<User> userManager = mock(TestUserManager.class);
        doReturn(databaseUser).when(userManager).getBy(eq("username"), eq(databaseUser.getUsername()));

        LoginView exptectedView = new LoginView();
        exptectedView.setUser(databaseUser);

        assertEquals(
                new Answer(200, exptectedView.render()),
                new LoginController(userManager, new LoginPayloadExtractor(), new LoginView())
                        .process(payload, emptyMap())
        );
    }
}
