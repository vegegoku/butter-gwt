package com.progressoft.security.repository;

import java.util.Objects;

public class RepositoryContext {

    private static UserRepository userRepository;


    private RepositoryContext(){}

    public static void withUserRepository(UserRepository userRepository){
        RepositoryContext.userRepository=userRepository;
    }

    public static UserRepository userRepository(){
        if(Objects.isNull(RepositoryContext.userRepository))
            RepositoryContext.withUserRepository(new JpaUserRepository());
        return RepositoryContext.userRepository;
    }
}
