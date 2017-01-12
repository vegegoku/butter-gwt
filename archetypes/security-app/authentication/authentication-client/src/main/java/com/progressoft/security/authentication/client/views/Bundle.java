package com.progressoft.security.authentication.client.views;

import com.google.gwt.core.client.GWT;

public class Bundle {

    public static final AuthenticationBundle INSTANCE= GWT.create(AuthenticationBundle.class);

    private Bundle() {
    }
}
