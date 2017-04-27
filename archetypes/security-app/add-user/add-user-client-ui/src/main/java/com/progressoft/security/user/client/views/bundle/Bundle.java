package com.progressoft.security.user.client.views.bundle;

import com.google.gwt.core.client.GWT;

public class Bundle {

    public static final AddUserBundle INSTANCE= GWT.create(AddUserBundle.class);

    private Bundle() {
    }
}
