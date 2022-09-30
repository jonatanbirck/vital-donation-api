package com.univates.vitaldonationapi.domain.security;

public class UserAuthority {

    private UserAuthority() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ROLE_SUPER_USER = "'SUPER_USER'";
    public static final String ROLE_ADMIN = "'ADMIN'";
    public static final String ROLE_MANAGER = "'MANAGER'";
    public static final String ROLE_USER = "'USER'";

    private static final String HAS = "hasAnyAuthority";

    public static final String PROFILE_SUPER_USER =
             "hasAuthority(" + ROLE_SUPER_USER + ")";

    public static final String PROFILE_ADMIN =
            HAS + "(" + ROLE_SUPER_USER + ", " + ROLE_ADMIN + ")";

    public static final String PROFILE_MANAGER =
            HAS + "(" + ROLE_SUPER_USER + ", " + ROLE_ADMIN + ", " + ROLE_MANAGER + ")";

    public static final String PROFILE_USER =
            HAS + "(" + ROLE_SUPER_USER + ", " + ROLE_ADMIN + ", " + ROLE_MANAGER + ", " + ROLE_USER + ")";

}
