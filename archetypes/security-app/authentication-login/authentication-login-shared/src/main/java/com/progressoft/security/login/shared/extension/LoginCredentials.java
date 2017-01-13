package com.progressoft.security.login.shared.extension;

import java.io.Serializable;

public interface LoginCredentials extends Serializable{

    String getUserName();
    String getPassword();
    String getTenant();
}
