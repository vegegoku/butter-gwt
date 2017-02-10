package com.progressoft.security.otp.client.requests;

import com.google.gwt.user.client.Window;
import com.progressoft.security.otp.client.presenters.OtpPresenter;
import com.progressoft.security.otp.shared.request.DefaultVerifyOtpRequest;
import com.progressoft.security.otp.shared.response.DefaultVerifyOtpResponse;
import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.client.request.ClientServerRequest;

@Request
public class VerifyOtpCodeServerRequest extends ClientServerRequest<OtpPresenter, DefaultVerifyOtpRequest, DefaultVerifyOtpResponse> {
    private String otpCode;

    public VerifyOtpCodeServerRequest(String otpCode) {
        this.otpCode = otpCode;
    }

    @Override
    protected void process(OtpPresenter presenter, DefaultVerifyOtpRequest serverArgs, DefaultVerifyOtpResponse response) {
        if (!response.isValidOtpCode())
            presenter.showErrorMessage();
        else
            presenter.onChainCompletedSuccessfully(response.getPrincipal());
    }

    @Override
    public DefaultVerifyOtpRequest buildArguments() {
        return new DefaultVerifyOtpRequest(otpCode);
    }
}
