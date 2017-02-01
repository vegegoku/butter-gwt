package com.progressoft.security.otp.server;

import com.google.auto.service.AutoService;
import com.progressoft.security.otp.shared.extension.OtpConfigurationLoader;
import org.akab.engine.core.api.shared.server.ServerAppEntryPoint;

import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;

@AutoService(ServerAppEntryPoint.class)
public class OtpModuleServerEntryPoint implements ServerAppEntryPoint {
    @Override
    public void onModulesLoaded() {
        Set<OtpConfigurationLoader> loaders=new HashSet<>();
        ServiceLoader.load(OtpConfigurationLoader.class).forEach(loaders::add);
        new OtpConfigurator(loaders);
    }
}
