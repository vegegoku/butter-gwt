package com.progressoft.security.authentication.shared.registry;

import com.progressoft.security.authentication.shared.extension.Principal;

public interface AuthenticationHolder {
    boolean isEmpty();

    Principal getPrincipal();
}
