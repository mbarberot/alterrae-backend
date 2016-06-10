package com.sistearth.game.auth;

import com.sistearth.db.api.manager.ModelException;
import com.sistearth.db.api.manager.ModelManager;
import com.sistearth.db.api.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Objects;

import static com.sistearth.game.auth.Authenticator.Result.ACCEPTED;
import static com.sistearth.game.auth.Authenticator.Result.REJECTED;

public class Authenticator {
    private static Log LOG = LogFactory.getLog(Authenticator.class.getName());
    private User authenticatedUser;
    private Result authenticationResult;

    public enum Result {
        ACCEPTED,
        REJECTED
    }

    private ModelManager<User> userManager;

    public Authenticator(ModelManager<User> userManager) {
        this.userManager = userManager;
        this.authenticatedUser = null;
        this.authenticationResult = null;
    }

    public Result authenticate(String username, String password) {
        try {
            User user = userManager.getBy("username", username);
            if (Objects.equals(user.getPassword(), password)) {
                authenticatedUser = user;
                authenticationResult = ACCEPTED;
            } else {
                authenticationResult = REJECTED;
            }
        } catch (ModelException e) {
            LOG.warn("Failed to authenticate because of : ", e);
            authenticationResult = REJECTED;
        }
        return authenticationResult;
    }

    public boolean isAuthenticated() throws AuthenticationException {
        if(authenticationResult != null) {
            return authenticationResult == ACCEPTED;
        } else {
            throw new AuthenticationException("Not authenticated");
        }

    }

    public User getAuthenticatedUser() throws AuthenticationException {
        if (isAuthenticated()) {
            return authenticatedUser;
        } else {
            throw new AuthenticationException("Authentication rejected");
        }
    }
}
