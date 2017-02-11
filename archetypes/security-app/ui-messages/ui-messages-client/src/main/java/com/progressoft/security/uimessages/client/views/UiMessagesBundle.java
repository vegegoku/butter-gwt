package com.progressoft.security.uimessages.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;

public interface UiMessagesBundle extends ClientBundle{

    interface Style extends CssResource {
        String errorDialogTitle();
    }

    @Source("css/UiMessages.gss")
    Style style();

}