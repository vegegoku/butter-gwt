package com.progressoft.security.userinfo.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;

import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialRow;
import org.akab.engine.core.api.client.annotations.UiView;

import com.progressoft.security.userinfo.client.presenters.UserInfoPresenter;

public class HeaderUserInfoView extends Composite implements UserInfoView{

    interface HeaderUserInfoUiBinder extends UiBinder<MaterialRow, HeaderUserInfoView> {
    }

    private static HeaderUserInfoUiBinder ourUiBinder = GWT.create(HeaderUserInfoUiBinder.class);

    @UiField
    MaterialRow root;

    @UiField
    MaterialChip userChip;

    public HeaderUserInfoView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void setDisplayName(String userDisplayName) {
        userChip.setText(userDisplayName);
        userChip.setLetter(userDisplayName.charAt(0)+"");
    }

}