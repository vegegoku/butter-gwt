package org.akab.engine.core.api.client.mvp;

import org.akab.engine.core.api.client.mvp.view.View;

public interface ViewRegistry{
    void registerView(String presenterName, View view);
}