package com.progressoft.security.otp.server.usecase;

import com.progressoft.security.authentication.shared.extension.Principal;
import com.progressoft.security.model.otp.Otp;
import com.progressoft.security.model.otp.OtpGeneratorFactory;
import com.progressoft.security.model.otp.OtpHolder;
import com.progressoft.security.model.user.User;
import com.progressoft.security.otp.shared.extension.OtpConfiguration;
import com.progressoft.security.repository.UserRepository;

import static java.util.Objects.isNull;

public class SendOtpUseCase {

    private final UserRepository userRepository;
    private final OtpConfiguration configuration;

    public SendOtpUseCase(UserRepository userRepository, OtpConfiguration configuration) {
        if(isNull(userRepository))
            throw new InvalidUserRepositoryProvidedException();
        if(isNull(configuration))
            throw new InvalidOtpConfigurationProvidedException();
        this.configuration = configuration;
        this.userRepository = userRepository;
    }

    public OtpHolder sendOtp(Principal principal) {
        if(isNull(principal))
            throw new CantSendOtpForNullPrincipalException();
        return getUser(principal).sendOtp(makeFactory());
    }

    private OtpGeneratorFactory makeFactory() {
        return makeOtpFactory(configuration.digitsCount(), configuration.timeout());
    }

    private User getUser(Principal principal) {
        return nonNullUser(userRepository.findUser(principal.getUserName(), principal.getTenant()));
    }

    private User nonNullUser(User user) {
        if(isNull(user))
            throw new UserNotFoundException();
        return user;
    }

    private OtpGeneratorFactory makeOtpFactory(final int digitsCount, final int timeout) {
       return secret -> new Otp(secret, digitsCount, timeout);
    }

    public class InvalidUserRepositoryProvidedException extends RuntimeException{}

    public class InvalidOtpConfigurationProvidedException extends RuntimeException{}

    public class CantSendOtpForNullPrincipalException extends RuntimeException{}

    public class UserNotFoundException extends RuntimeException{}
}
