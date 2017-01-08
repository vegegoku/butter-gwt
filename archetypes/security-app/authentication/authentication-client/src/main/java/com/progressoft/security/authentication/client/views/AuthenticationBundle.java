package com.progressoft.security.authentication.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;

public interface AuthenticationBundle extends ClientBundle{

    AuthenticationBundle INSTANCE=GWT.create(AuthenticationBundle.class);

    interface Style extends CssResource {
        String authentication();
    }

    @Source("css/Authentication.gss")
    Style style();

    @Source("images/gwt.png")
    ImageResource gwt();

    @Source("text/welcome.txt")
    ExternalTextResource welcome();
}