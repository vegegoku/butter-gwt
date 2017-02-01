package com.progressoft.security.otp.client.requests;

import org.akab.engine.core.api.client.request.ClientServerRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;
import com.progressoft.security.otp.shared.response.GenerateOtpResponse;
import com.progressoft.security.otp.shared.request.GenerateOtpRequest;
import com.progressoft.security.otp.client.presenters.OtpPresenter;
import org.akab.engine.core.api.client.annotations.Request;

@Request
public class OtpServerRequest extends ClientServerRequest<OtpPresenter, GenerateOtpRequest, GenerateOtpResponse> {

    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(OtpServerRequest.class);

    @Override
    protected void process(OtpPresenter presenter, GenerateOtpRequest serverRequest, GenerateOtpResponse response) {
    }

    @Override
    public GenerateOtpRequest buildArguments() {
        return new GenerateOtpRequest();
    }
}
