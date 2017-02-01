package com.progressoft.security.model.user;

import com.progressoft.security.authentication.shared.extension.Principal;

public interface PrincipalBuilder {
    PrincipalBuilder name(String name);
    PrincipalBuilder tenant(String tenant);
    Principal build();
}
