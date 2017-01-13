package com.progressoft.security.login.server;

import com.progressoft.security.login.server.repository.UserRepository;

public class LoginContext {

    private static UserRepository userRepository;


    private LoginContext(){}

    public static void withUserRepository(UserRepository userRepository){
        LoginContext.userRepository=userRepository;
    }

    public static UserRepository userRepository(){
        return LoginContext.userRepository;
    }
}
