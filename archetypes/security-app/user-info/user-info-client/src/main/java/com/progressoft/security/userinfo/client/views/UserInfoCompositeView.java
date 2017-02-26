package com.progressoft.security.userinfo.client.views;

import org.akab.engine.core.api.client.mvp.view.CompositeView;

public interface UserInfoCompositeView extends CompositeView<UserInfoView>{
    void setDisplayName(String userDisplayName);
}
