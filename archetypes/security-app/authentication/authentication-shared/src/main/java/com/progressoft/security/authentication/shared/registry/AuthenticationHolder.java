package com.progressoft.security.authentication.shared.registry;

import com.progressoft.security.authentication.shared.extension.Principal;
import java.io.Serializable;

public interface AuthenticationHolder extends Serializable{
    boolean isEmpty();
    void reset();
    Principal getPrincipal();
    void setProperty(String key, Object value);
    <T> T getProperty(String key);
}
