package com.progressoft.security.login.client.views;

import com.google.gwt.core.client.GWT;

public class Bundle {

    public static final LoginBundle INSTANCE= GWT.create(LoginBundle.class);

    public static final String USERNAME = "USERNAME";
    public static final String SECRET = "SECRET";
    public static final String TENANT = "TENANT";

    private Bundle() {
    }
}
