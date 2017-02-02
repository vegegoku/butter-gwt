package com.progressoft.security.layout.shared.extension;

import com.google.gwt.user.client.ui.IsWidget;
import org.akab.engine.core.api.shared.extension.Context;

public interface AuthenticationLayoutContext extends Context {
    void showViewInMainPanel(IsWidget view);
    void hideViewFromMainPanel(IsWidget view);
}
