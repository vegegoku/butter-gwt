package com.progressoft.security.model.user;

import com.progressoft.security.authentication.shared.extension.Principal;

import java.util.Deque;

public interface PrincipalBuilder {
    PrincipalBuilder name(String name);
    PrincipalBuilder tenant(String tenant);
    PrincipalBuilder displayName(String displayName);

    PrincipalBuilder chains(Deque<String> chains);

    Principal build();
}
