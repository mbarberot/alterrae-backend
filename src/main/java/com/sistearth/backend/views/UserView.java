package com.sistearth.backend.views;

import com.sistearth.backend.models.beans.User;
import lombok.Data;

@Data
public abstract class UserView implements View {
    protected User user;
}
