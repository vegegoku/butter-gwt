package com.progressoft.security.userinfo.client.requests;

import com.progressoft.security.userinfo.client.presenters.UserInfoPresenter;
import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@Request
public class UserInfoSampleClientRequest extends ClientRequest<UserInfoPresenter> {

    private final MainExtensionPoint mainExtensionPoint;

    public UserInfoSampleClientRequest(MainExtensionPoint mainExtensionPoint) {
        this.mainExtensionPoint=mainExtensionPoint;
    }

    @Override
    protected void process(UserInfoPresenter presenter) {
    }
}