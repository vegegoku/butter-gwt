package com.progressoft.security.userinfo.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import gwt.material.design.addins.client.avatar.MaterialAvatar;
import gwt.material.design.addins.client.sideprofile.MaterialSideProfile;
import gwt.material.design.client.ui.MaterialChip;

public class MenuUserInfoView extends Composite implements UserInfoView{

    interface HeaderUserInfoUiBinder extends UiBinder<MaterialSideProfile, MenuUserInfoView> {
    }

    private static HeaderUserInfoUiBinder ourUiBinder = GWT.create(HeaderUserInfoUiBinder.class);

    @UiField
    MaterialSideProfile root;

    @UiField
    MaterialChip userChip;

    @UiField
    MaterialAvatar userAvatar;

    public MenuUserInfoView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void setDisplayName(String userDisplayName) {
        userChip.setText(userDisplayName);
        userAvatar.setName(userDisplayName);
    }

}