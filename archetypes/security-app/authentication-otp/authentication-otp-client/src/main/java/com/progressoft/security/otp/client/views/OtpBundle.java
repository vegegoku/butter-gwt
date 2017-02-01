package com.progressoft.security.otp.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;

public interface OtpBundle extends ClientBundle{

    interface Style extends CssResource {
        String Otp();
    }

    @Source("css/Otp.gss")
    Style style();

    @Source("text/welcome.txt")
    ExternalTextResource welcome();

}