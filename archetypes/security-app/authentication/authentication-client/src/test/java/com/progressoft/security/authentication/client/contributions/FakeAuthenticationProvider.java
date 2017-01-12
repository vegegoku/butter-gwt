package com.progressoft.security.authentication.client.contributions;

import com.progressoft.security.authentication.shared.extension.*;

public class FakeAuthenticationProvider implements ClientAuthenticationProvider {

    public static int ORDER=0;

    public AuthenticationContext context;
    private boolean started;
    public int callOrder=0;

    public FakeAuthenticationProvider(AuthenticationContext context) {
        this.context = context;
    }

    @Override
    public void begin() {
        this.started=true;
        ORDER++;
        callOrder=ORDER;
    }

    public void chainAuthenticationCompletedSuccessfully(final Principal principal) {
        context.onChainCompleted(new CompletedChainContext() {
            @Override
            public Principal getPrincipal() {
                return principal;
            }
        });
    }

    public AuthenticationContext getContext() {
        return context;
    }

    public boolean isStarted() {
        return started;
    }

    public void chainAuthenticationFailed() {
        context.onChainFailed(new FailedChainContext(){});
    }
}
