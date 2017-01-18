package com.progressoft.security.login.server;

import com.progressoft.security.login.server.repository.JpaUserRepository;
import com.progressoft.security.login.server.repository.UserRepository;

import java.util.Objects;

public class LoginContext {

    private static UserRepository userRepository;


    private LoginContext(){}

    public static void withUserRepository(UserRepository userRepository){
        LoginContext.userRepository=userRepository;
    }

    public static UserRepository userRepository(){
        if(Objects.isNull(LoginContext.userRepository))
            LoginContext.withUserRepository(new JpaUserRepository());
        return LoginContext.userRepository;
    }
}
