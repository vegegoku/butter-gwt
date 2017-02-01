package com.progressoft.security.otp.shared.request;

import com.progressoft.security.authentication.shared.extension.Principal;
import org.akab.engine.core.api.shared.request.ServerRequest;


public class GenerateOtpRequest extends ServerRequest {

    private Principal principal;

    public GenerateOtpRequest() {
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }
}
