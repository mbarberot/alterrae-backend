package com.sistearth.view.request.payloads;

import com.sistearth.db.beans.Error;
import com.sistearth.db.beans.User;
import com.sistearth.view.request.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginPayload implements Payload<User> {
    private String username;
    private String password;

    @Override
    public boolean isValid() {
        return isNotBlank(username)
                && isNotBlank(password);
    }


    @Override
    public User getEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    @Override
    public List<Error> getErrors() {
        List<Error> errors = newArrayList();
        if(isBlank(username)) {
            errors.add(new Error("400", "username"));
        }
        if(isBlank(password)) {
            errors.add(new Error("400", "password"));
        }
        return errors;
    }
}
