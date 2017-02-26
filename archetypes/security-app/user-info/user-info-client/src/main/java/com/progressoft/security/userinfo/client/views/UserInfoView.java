package com.progressoft.security.userinfo.client.views;

import com.progressoft.security.app.layout.shared.extension.LayoutItem;
import org.akab.engine.core.api.client.mvp.view.View;

public interface UserInfoView extends View, LayoutItem{
    String HEADER="HEADER";
    String MENU="MENU";
    void setDisplayName(String userDisplayName);
}