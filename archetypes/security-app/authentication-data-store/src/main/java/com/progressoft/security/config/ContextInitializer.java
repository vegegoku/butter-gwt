package com.progressoft.security.config;

import com.progressoft.security.jpa.GatewayContext;
import com.progressoft.security.jpa.gateway.Gateways;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextInitializer {

    public static void initialize(){
        System.out.println("initializing context");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        GatewayContext.withUserGateway(context.getBean("gateways", Gateways.class).getUserGateway());
        GatewayContext.withChainsGateway(context.getBean("gateways", Gateways.class).getChainsGateway());
    }
}
