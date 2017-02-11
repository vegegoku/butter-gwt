package com.progressoft.security.uimessages.shared.extension;


import org.akab.engine.core.api.shared.extension.Context;

public interface UiMessagesContext extends Context {
    void showError(String message, String details);
}
