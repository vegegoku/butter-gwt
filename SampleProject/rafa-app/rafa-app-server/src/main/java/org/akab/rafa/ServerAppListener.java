package org.akab.rafa;

import org.akab.engine.core.server.ServerConfigurationLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

@WebListener
public class ServerAppListener implements ServletContextListener {

    private static final Logger LOGGER= Logger.getLogger(ServerAppListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.info("ServerAppListener is running ------------------------ > ");
        new ServerConfigurationLoader().loadModules();
        LOGGER.info("ServerAppListener is done ------------------------ > ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
