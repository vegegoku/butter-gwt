package com.progressoft.security;

import com.progressoft.security.jpa.GatewayContext;
import com.progressoft.security.jpa.gateway.Gateways;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Objects;

public class JpaStoresLoader {
    private JpaStoresLoader() {
    }

    public static void load() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        GatewayContext.withUserGateway(context.getBean("gateways", Gateways.class).getUserGateway());
    }
}
