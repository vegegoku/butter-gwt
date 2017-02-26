package com.progressoft.security.userinfo.client.views;

import com.google.gwt.core.client.GWT;

public class Bundle {

    public static final UserInfoBundle INSTANCE= GWT.create(UserInfoBundle.class);

    private Bundle() {
    }
}
