package com.progressoft.security.app.layout.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;

public interface LayoutBundle extends ClientBundle{

    interface Style extends CssResource {
        String Layout();
    }

    @Source("css/Layout.gss")
    Style style();

    @Source("text/welcome.txt")
    ExternalTextResource welcome();

}