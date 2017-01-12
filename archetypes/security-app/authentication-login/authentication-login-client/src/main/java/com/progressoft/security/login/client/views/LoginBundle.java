package com.progressoft.security.login.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;

public interface LoginBundle extends ClientBundle{

    interface Style extends CssResource {
        String login();
    }

    @Source("css/Login.gss")
    Style style();

    @Source("text/welcome.txt")
    ExternalTextResource welcome();

}