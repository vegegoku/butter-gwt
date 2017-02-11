package com.progressoft.security.otp.client.requests;

import com.google.gwt.user.client.Window;
import com.progressoft.security.otp.client.presenters.OtpPresenter;
import com.progressoft.security.otp.shared.request.GenerateOtpRequest;
import com.progressoft.security.otp.shared.response.GenerateOtpResponse;
import org.akab.engine.core.api.client.request.ClientServerRequest;
import org.akab.engine.core.api.shared.request.FailedServerResponse;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import org.akab.engine.core.api.client.annotations.Request;

@Request
public class GenerateOtpCodeServerRequest
        extends ClientServerRequest<OtpPresenter, GenerateOtpRequest, GenerateOtpResponse> {

    private static final CoreLogger LOGGER = CoreLoggerFactory.getLogger(GenerateOtpCodeServerRequest.class);

    @Override
    protected void process(OtpPresenter presenter, GenerateOtpRequest serverRequest, GenerateOtpResponse response) {
        presenter.onOtpCodeGenerated();
    }

    @Override
    public void processFailed(OtpPresenter presenter, GenerateOtpRequest serverArgs, FailedServerResponse failedResponse) {
        Window.alert("Failed to generate otp code");
    }

    @Override
    public GenerateOtpRequest buildArguments() {
        return new GenerateOtpRequest();
    }
}
