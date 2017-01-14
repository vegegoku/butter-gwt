package com.progressoft.security.layout.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;

public interface AuthenticationLayoutBundle extends ClientBundle{

    interface Style extends CssResource {
        String AuthenticationLayout();
        String securityIcon();
    }

    @Source("css/AuthenticationLayout.gss")
    Style style();

    @Source("text/welcome.txt")
    ExternalTextResource welcome();

}