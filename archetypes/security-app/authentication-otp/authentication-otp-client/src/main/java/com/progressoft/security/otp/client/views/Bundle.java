package com.progressoft.security.otp.client.views;

import com.google.gwt.core.client.GWT;

public class Bundle {

    public static final OtpBundle INSTANCE= GWT.create(OtpBundle.class);

    private Bundle() {
    }
}
