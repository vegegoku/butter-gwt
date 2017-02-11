package com.progressoft.security.otp.client;

import com.progressoft.security.otp.client.views.DefaultOtpView;
import com.progressoft.security.otp.client.views.OtpView;

public class OtpViewSpy extends DefaultOtpView {

    private boolean otpDialogVisible = false;
    private boolean otpFieldInvalid = false;

    public boolean isOtpDialogVisible() {
        return otpDialogVisible;
    }

    @Override
    public OtpView show() {
        super.show();
        this.otpDialogVisible = true;
        return this;
    }

    @Override
    public String toString() {
        return "i am the view spy.";
    }

    @Override
    public void invalidateField() {
        super.invalidateField();
        otpFieldInvalid = true;
    }

    boolean isOtpFieldInvalid() {
        return otpFieldInvalid;
    }

    void clickVerify() {
        this.verifyOtpHandler.handle(getOtpCode());
    }
}
