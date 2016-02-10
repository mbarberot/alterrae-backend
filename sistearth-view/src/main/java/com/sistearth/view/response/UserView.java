package com.sistearth.view.response;

import com.sistearth.db.beans.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserView implements View {
    protected User user;
}
