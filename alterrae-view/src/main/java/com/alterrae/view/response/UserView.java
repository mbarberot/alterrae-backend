package com.alterrae.view.response;

import com.alterrae.db.api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserView implements View {
    protected User user;
}
