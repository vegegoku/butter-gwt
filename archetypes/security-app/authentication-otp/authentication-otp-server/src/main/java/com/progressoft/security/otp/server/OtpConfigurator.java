package com.progressoft.security.otp.server;

import com.progressoft.security.otp.shared.extension.OtpConfigurationLoader;

import java.util.Objects;
import java.util.Set;

public class OtpConfigurator {
    public OtpConfigurator(Set<OtpConfigurationLoader> loaders) {
        if(Objects.isNull(loaders) || loaders.isEmpty())
            throw new InvalidConfigurationLoaders();
        if(loaders.size()>1)
            throw new MoreThanOneOtpLoaderFound();
        registerConfigurationLoader(loaders.stream().findFirst().orElseThrow(NoConfigurationLoadersFound::new));
    }

    private void registerConfigurationLoader(OtpConfigurationLoader loader) {
        OtpConfigurationContext.withConfigurationLoader(loader);
    }

    public class InvalidConfigurationLoaders extends RuntimeException{
    }

    public class MoreThanOneOtpLoaderFound extends RuntimeException{
    }

    public class NoConfigurationLoadersFound extends RuntimeException{

    }
}
