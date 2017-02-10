package com.progressoft.security.otp.server.handlers;

import com.progressoft.security.authentication.server.shared.AuthenticationProcessContext;
import com.progressoft.security.model.otp.OtpHolder;
import com.progressoft.security.otp.shared.request.DefaultVerifyOtpRequest;
import com.progressoft.security.otp.shared.response.DefaultVerifyOtpResponse;
import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;

@Handler
public class VerifyOtpCodeHandler implements RequestHandler<DefaultVerifyOtpRequest, DefaultVerifyOtpResponse> {
    @Override
    public DefaultVerifyOtpResponse handleRequest(DefaultVerifyOtpRequest request) {
        return new DefaultVerifyOtpResponse(isValidOtpCode(request), AuthenticationProcessContext.get().getPrincipal());
    }

    private boolean isValidOtpCode(DefaultVerifyOtpRequest request) {
        return otpHolder().verify(request.getOtpCode());
    }

    private OtpHolder otpHolder() {
        return AuthenticationProcessContext.get().getProperty("OTP_HOLDER");
    }
}
