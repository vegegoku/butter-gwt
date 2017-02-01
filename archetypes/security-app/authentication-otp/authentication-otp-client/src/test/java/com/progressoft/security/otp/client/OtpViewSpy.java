package com.progressoft.security.otp.client;

import com.progressoft.security.otp.client.views.DefaultOtpView;

public class OtpViewSpy extends DefaultOtpView {

    private boolean otpDialogVisible=false;

    public boolean isOtpDialogVisible() {
        return otpDialogVisible;
    }

    @Override
    public void show() {
        super.show();
        this.otpDialogVisible=true;
    }

    @Override
    public String toString() {
        return "i am the view spy.";
    }
}
