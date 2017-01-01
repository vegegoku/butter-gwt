package com.progressoft.security.sample.client.views.bundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;

public interface SampleBundle extends ClientBundle{

    SampleBundle INSTANCE=GWT.create(SampleBundle.class);

    interface Style extends CssResource {
        String Sample();
    }

    @Source("css/Sample.gss")
    Style style();

    @Source("images/gwt.png")
    ImageResource gwt();

    @Source("text/welcome.txt")
    ExternalTextResource welcome();

}