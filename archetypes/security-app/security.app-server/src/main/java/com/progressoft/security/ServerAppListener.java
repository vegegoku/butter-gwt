package com.progressoft.security;

import org.akab.engine.core.server.ServerConfigurationLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerAppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        new ServerConfigurationLoader().loadModules();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // nothing to destroy so far
    }
}
