package com.progressoft.security.login.server.usecase;

import com.progressoft.security.login.server.model.User;
import com.progressoft.security.login.server.repository.UserRepository;
import com.progressoft.security.login.shared.extension.LoginCredentials;
import com.progressoft.security.login.shared.response.LoginResponse;

import static java.util.Objects.isNull;

public class LoginUserUseCase {
    private final UserRepository userRepository;

    public LoginUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginCredentials credentials) {
        if (isInvalidCredentials(credentials))
            throw new InvalidCredentialsException();
        return preparedResponse(credentials);
    }

    private LoginResponse preparedResponse(LoginCredentials credentials) {
        return makeResponse(findUser(credentials));

    }

    private LoginResponse makeResponse(User user) {
        return new LoginResponse(user.makePrincipal());
    }

    private User findUser(LoginCredentials credentials) {
        return getValidUser(userRepository.findUser(credentials.getUserName(), credentials.getTenant()), credentials);
    }

    private User getValidUser(User user, LoginCredentials credentials) {
        if (isNull(user))
            throw new UserNotFoundException();
        if (!user.isSamePassword(credentials.getPassword()))
            throw new BadCredentialsException();
        return user;
    }

    private boolean isInvalidCredentials(LoginCredentials credentials) {
        return isNull(credentials) || isInvalid(credentials.getUserName()) || isInvalid(credentials.getPassword()) ||
                isInvalid(credentials.getTenant());
    }

    private boolean isInvalid(String value) {
        return isNull(value) || value.trim().isEmpty();
    }

    public class InvalidCredentialsException extends RuntimeException {
    }

    public static class UserNotFoundException extends RuntimeException {
    }

    public static class BadCredentialsException extends RuntimeException {
    }
}
