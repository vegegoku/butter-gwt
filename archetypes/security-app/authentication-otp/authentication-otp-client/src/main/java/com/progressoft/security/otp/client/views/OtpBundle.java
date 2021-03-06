package com.progressoft.security.otp.client.views;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;

public interface OtpBundle extends ClientBundle{

    interface Style extends CssResource {
        String mainPanel();
        String errorDialogTitle();
        String Otp();
    }

    @Source("css/Otp.gss")
    Style style();

    @Source("text/welcome.txt")
    ExternalTextResource welcome();

    @Source("images/logo.jpg")
    ImageResource logo();

}