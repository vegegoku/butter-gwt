package com.progressoft.security.otp.shared.response;

import com.progressoft.security.authentication.shared.extension.Principal;
import org.akab.engine.core.api.shared.request.ServerResponse;

public class DefaultVerifyOtpResponse extends ServerResponse {
    private boolean validOtpCode;
    private Principal principal;

    public DefaultVerifyOtpResponse() {
    }

    public DefaultVerifyOtpResponse(boolean validOtpCode, Principal principal) {
        this.validOtpCode = validOtpCode;
        this.principal = principal;
    }

    public boolean isValidOtpCode() {
        return validOtpCode;
    }

    public void setValidOtpCode(boolean validOtpCode) {
        this.validOtpCode = validOtpCode;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }
}
