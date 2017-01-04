package com.progressoft.security.authentication.client.initialtask;

import com.progressoft.security.authentication.client.requests.ApplyAuthenticationContributionsRequest;
import org.akab.engine.core.api.client.InitializeTask;
import org.akab.engine.core.api.client.annotations.InitialTask;

@InitialTask
public class ApplyAuthenticationContributionsInitialTask implements InitializeTask {

    @Override
    public void execute() {
        new ApplyAuthenticationContributionsRequest().send();
    }
}
