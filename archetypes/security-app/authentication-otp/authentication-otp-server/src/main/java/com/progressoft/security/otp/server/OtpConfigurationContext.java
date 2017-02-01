package com.progressoft.security.otp.server;


import com.progressoft.security.otp.shared.extension.OtpConfiguration;
import com.progressoft.security.otp.shared.extension.OtpConfigurationLoader;

public class OtpConfigurationContext {
    private static OtpConfigurationLoader configurationLoader;

    private OtpConfigurationContext(){
    }

    public static void withConfigurationLoader(OtpConfigurationLoader loader){
        configurationLoader=loader;
    }

    public static OtpConfiguration configuration() {
        return configurationLoader.load();
    }
}
