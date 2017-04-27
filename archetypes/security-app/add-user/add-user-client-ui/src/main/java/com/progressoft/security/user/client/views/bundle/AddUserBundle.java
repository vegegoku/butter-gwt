package com.progressoft.security.user.client.views.bundle;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface AddUserBundle extends ClientBundle{

    interface Style extends CssResource {
        String AddUser();
    }

    @Source("css/AddUser.gss")
    Style style();

}