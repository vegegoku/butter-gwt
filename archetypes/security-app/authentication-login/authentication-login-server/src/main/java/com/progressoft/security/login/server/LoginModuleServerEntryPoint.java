package com.progressoft.security.login.server;

import com.google.auto.service.AutoService;
import com.progressoft.security.login.server.repository.JpaUserRepository;
import org.akab.engine.core.api.shared.server.ServerAppEntryPoint;

import java.util.Objects;

@AutoService(ServerAppEntryPoint.class)
public class LoginModuleServerEntryPoint implements ServerAppEntryPoint {
    @Override
    public void onModulesLoaded() {

    }
}
