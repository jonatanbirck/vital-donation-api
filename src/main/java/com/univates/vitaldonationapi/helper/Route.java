package com.univates.vitaldonationapi.helper;

public class Route {

    public static final String API = "/api";
    public static final String LOGIN = API + "/login";
    public static final String REFRESH_TOKEN = API + "/refresh-token";

    private Route() {
        throw new IllegalStateException("Utility class");
    }

}
