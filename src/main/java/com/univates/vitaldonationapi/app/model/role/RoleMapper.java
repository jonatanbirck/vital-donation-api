package com.univates.vitaldonationapi.app.model.role;

import com.univates.vitaldonationapi.domain.entity.Role;
import com.univates.vitaldonationapi.helper.PropertyHelper;

public class RoleMapper {

    private RoleMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Role map(RoleForm form) {
        var role = new Role();
        PropertyHelper.copy(form, role);
        return role;
    }

}
