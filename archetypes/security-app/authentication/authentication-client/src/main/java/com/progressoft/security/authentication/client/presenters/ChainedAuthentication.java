package com.progressoft.security.authentication.client.presenters;

import com.progressoft.security.authentication.client.registry.AuthenticationProviderRegistry;

import java.util.Deque;
import java.util.Objects;


public class ChainedAuthentication {

    private final AuthenticationPresenter presenter;
    private Deque<String> chains;

    public ChainedAuthentication(AuthenticationPresenter presenter, Deque<String> chains) {
        this.presenter = presenter;
        this.chains=chains;
    }

    public void fireNextChain(){
        if(Objects.isNull(AuthenticationProviderRegistry.get(chains.peek())))
           presenter.showErrorMessage();
        else
            AuthenticationProviderRegistry.get(chains.pop()).begin();
    }
}
