package com.progressoft.security.login.server.usecase;

import com.progressoft.security.login.server.repository.InMemoryUserRepository;
import com.progressoft.security.login.server.repository.UserRepository;
import com.progressoft.security.login.shared.LoginPrincipal;
import com.progressoft.security.login.shared.extension.LoginCredentials;
import org.junit.*;
import static org.junit.Assert.*;

public class LoginUserUseCaseTest {
    private static final String SOMETHING = "SOMETHING";
    private static final String FOUND_USER = "FOUND_USER";
    private static final String TENANT = "TENANT";
    private UserRepository userRepository;
    private LoginUserUseCase loginUserUseCase;

    private LoginCredentials nullCredentials = makeCredentials(null, null, null);
    private LoginCredentials emptyCredentials = makeCredentials("", "", "");

    private LoginCredentials makeCredentials(String username, String password, String tenant) {
        return new LoginCredentials() {
            @Override
            public String getUserName() {
                return username;
            }

            @Override
            public String getPassword() {
                return password;
            }

            @Override
            public String getTenant() {
                return tenant;
            }
        };
    }

    @Before
    public void setUp() throws Exception {
        userRepository = new InMemoryUserRepository();
        loginUserUseCase = new LoginUserUseCase(userRepository);
    }

    @Test(expected = LoginUserUseCase.InvalidCredentialsException.class)
    public void givenUseCase_WhenLoginWithNullCredentials_ShouldThrowException() throws Exception {
        loginUserUseCase.login(null);
    }

    @Test(expected = LoginUserUseCase.InvalidCredentialsException.class)
    public void givenUseCase_WhenLoginWithNullUsername_ShouldThrowException() throws Exception {
        loginUserUseCase.login(nullCredentials);
    }

    @Test(expected = LoginUserUseCase.InvalidCredentialsException.class)
    public void givenUseCase_WhenLoginWithEmptyUsername_ShouldThrowException() throws Exception {
        loginUserUseCase.login(emptyCredentials);
    }

    @Test(expected = LoginUserUseCase.InvalidCredentialsException.class)
    public void givenUseCase_WhenLoginWithNullPassword_ShouldThrowException() throws Exception {
        loginUserUseCase.login(nullCredentials);
    }

    @Test(expected = LoginUserUseCase.InvalidCredentialsException.class)
    public void givenUseCase_WhenLoginWithEmptyPassword_ShouldThrowException() throws Exception {
        loginUserUseCase.login(emptyCredentials);
    }

    @Test(expected = LoginUserUseCase.InvalidCredentialsException.class)
    public void givenUseCase_WhenLoginWithNullTenant_ShouldThrowException() throws Exception {
        loginUserUseCase.login(nullCredentials);
    }

    @Test(expected = LoginUserUseCase.InvalidCredentialsException.class)
    public void givenUseCase_WhenLoginWithEmptyTenant_ShouldThrowException() throws Exception {
        loginUserUseCase.login(emptyCredentials);
    }

    @Test(expected = LoginUserUseCase.UserNotFoundException.class)
    public void givenUseCase_WhenLoginWithUserNotExist_ThenShouldThrowException() throws Exception {
        loginUserUseCase.login(makeCredentials("NOT_FOUND_USER", SOMETHING, SOMETHING));
    }

    @Test(expected = LoginUserUseCase.BadCredentialsException.class)
    public void givenUseCase_WhenLoginWithExistUserWithWrongPassword_ThenShouldThrowException() throws Exception {
        loginUserUseCase.login(makeCredentials(FOUND_USER, "WRONG_PASSWORD", TENANT));
    }

    @Test(expected = LoginUserUseCase.UserNotFoundException.class)
    public void givenUseCase_WhenLoginWithExistUserAndWrongTenant_ThenShouldThrowException() throws Exception {
        loginUserUseCase.login(makeCredentials(FOUND_USER, SOMETHING, "WRONG_TENANT"));
    }

    @Test
    public void givenUseCase_WhenLoginWithExistUserValidCredentials_ThenShouldReturnLoginResponse() throws Exception {
        assertEquals(new LoginPrincipal(FOUND_USER, TENANT), loginUserUseCase.login(makeCredentials(FOUND_USER, "SECRET",
                TENANT)).getPrincipal());
    }
}
