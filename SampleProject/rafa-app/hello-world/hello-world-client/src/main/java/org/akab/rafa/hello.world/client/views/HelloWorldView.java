package org.akab.rafa.hello.world.client.views;

import org.akab.engine.core.api.client.mvp.view.View;

public interface HelloWorldView extends View{
    void setWelcomeMessage(String welcomeMessage);
}