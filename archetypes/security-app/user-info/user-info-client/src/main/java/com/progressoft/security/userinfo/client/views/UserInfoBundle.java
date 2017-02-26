package com.progressoft.security.userinfo.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;

public interface UserInfoBundle extends ClientBundle{

    interface Style extends CssResource {
        String UserInfo();
        String userDisplayName();
    }

    @Source("css/UserInfo.gss")
    Style style();

    @Source("text/welcome.txt")
    ExternalTextResource welcome();

}