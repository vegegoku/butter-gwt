package com.progressoft.security.login.client.views;

import javax.inject.Inject;

public class ResourceLoader {
    @Inject
    ResourceLoader(
            LoginBundle loginBundle) {
        loginBundle.style().ensureInjected();
    }

}
