package com.univates.vitaldonationapi.app.model.user;

import com.univates.vitaldonationapi.domain.entity.User;
import com.univates.vitaldonationapi.helper.PropertyHelper;

import java.util.UUID;

public class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static User map(UserForm form) {
        var user = new User();
        PropertyHelper.copy(form, user);
        return user;
    }

    public static User map(UserDetail detail, String id) {
        var user = new User();
        PropertyHelper.copy(detail, user);
        user.setId(UUID.fromString(id));
        return user;
    }


    public static UserDetail map(User user) {
        var detail = new UserDetail();
        PropertyHelper.copy(user, detail);
        detail.setPassword(null);
        return detail;
    }


}
