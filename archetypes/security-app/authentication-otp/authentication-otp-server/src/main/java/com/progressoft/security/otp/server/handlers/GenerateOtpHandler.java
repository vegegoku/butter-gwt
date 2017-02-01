package com.progressoft.security.otp.server.handlers;

import com.progressoft.security.authentication.shared.registry.UserSessionContext;
import com.progressoft.security.model.otp.OtpHolder;
import com.progressoft.security.otp.server.OtpConfigurationContext;
import com.progressoft.security.otp.server.usecase.SendOtpUseCase;
import com.progressoft.security.repository.RepositoryContext;
import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import com.progressoft.security.otp.shared.response.GenerateOtpResponse;
import com.progressoft.security.otp.shared.request.GenerateOtpRequest;

import java.util.logging.Logger;

@Handler
public class GenerateOtpHandler implements RequestHandler<GenerateOtpRequest, GenerateOtpResponse> {
    private static final Logger LOGGER = Logger.getLogger(GenerateOtpHandler.class.getName());

    @Override
    public GenerateOtpResponse handleRequest(GenerateOtpRequest request) {
        UserSessionContext.get().setProperty("OTP_HOLDER", generatedOtpHolder());
        return new GenerateOtpResponse();
    }

    private OtpHolder generatedOtpHolder() {
        return new SendOtpUseCase(RepositoryContext.userRepository(), OtpConfigurationContext.configuration())
                .sendOtp(UserSessionContext.get().getPrincipal());
    }
}
