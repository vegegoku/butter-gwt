package org.akab.engine.core.api.client;


public class ModuleConfigurator {

    public void configureModule(ModuleConfiguration moduleConfiguration){
        ClientApp.make().configureModule(moduleConfiguration);
    }

}
