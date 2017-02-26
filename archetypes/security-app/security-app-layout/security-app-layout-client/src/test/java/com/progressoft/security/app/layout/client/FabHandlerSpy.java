package com.progressoft.security.app.layout.client;

import com.progressoft.security.app.layout.shared.extension.FabHandler;

public class FabHandlerSpy implements FabHandler {

    public boolean clicked;

    @Override
    public void onFabClick() {
        this.clicked=true;
    }
}
