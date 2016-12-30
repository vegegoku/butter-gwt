package com.progressoft.security.login.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;

public interface LoginBundle extends ClientBundle{

    LoginBundle LOGIN_BUNDLE= GWT.create(LoginBundle.class);

    interface Style extends CssResource{
        String mainPanel();
        String loginCard();
    }

    @Source("images/pslogo_1.jpg")
    ImageResource pslogo_1();

    @Source("images/pslogo_2.png")
    ImageResource pslogo_2();

    @Source("images/ps-ecc.jpg")
    ImageResource psEcc();

    @Source("images/ps-rtgs.jpg")
    ImageResource psRtgs();

    @Source("images/ps-eft.jpg")
    ImageResource psEft();

    @Source("images/ps-corpay.jpg")
    ImageResource psCorpay();

    @Source("pages/progressoft.html")
    ExternalTextResource progressoft();

    @Source("pages/support.txt")
    ExternalTextResource support();

    @Source("pages/supportAvailability.txt")
    ExternalTextResource supportAvailability();

    @Source("css/login.gss")
    Style style();
}
