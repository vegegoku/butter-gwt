package com.progressoft.security;

import com.progressoft.security.config.ContextInitializer;
import com.progressoft.security.jpa.GatewayContext;
import com.progressoft.security.jpa.gateway.Gateways;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JpaStoresLoader {
    private JpaStoresLoader() {
    }

    public static void load() {
        ContextInitializer.initialize();
    }
}
