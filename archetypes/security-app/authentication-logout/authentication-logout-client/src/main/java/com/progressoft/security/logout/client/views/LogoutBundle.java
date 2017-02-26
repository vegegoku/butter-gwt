package com.progressoft.security.logout.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;

public interface LogoutBundle extends ClientBundle{

    interface Style extends CssResource {
        String Logout();
    }

    @Source("css/Logout.gss")
    Style style();

    @Source("text/welcome.txt")
    ExternalTextResource welcome();

}