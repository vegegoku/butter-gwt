package org.akab.rafa;

import org.akab.engine.core.server.ServerConfigurationLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;

public class ServerAppListener implements ServletContextListener {

    private static final Logger LOGGER= Logger.getLogger(ServerAppListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        new ServerConfigurationLoader().loadModules();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
