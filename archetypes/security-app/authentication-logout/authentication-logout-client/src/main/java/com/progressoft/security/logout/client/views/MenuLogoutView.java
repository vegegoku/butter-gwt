package com.progressoft.security.logout.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.progressoft.security.logout.client.presenters.LogoutHandler;
import gwt.material.design.client.ui.MaterialLink;

import static java.util.Objects.nonNull;

public class MenuLogoutView extends Composite implements LogoutView{

    interface MenuLogoutViewUiBinder extends UiBinder<MaterialLink, MenuLogoutView> {
    }

    private LogoutHandler logoutHandler;

    @Override
    public void setLogoutHandler(LogoutHandler logoutHandler) {
        this.logoutHandler=logoutHandler;
    }

    private static MenuLogoutViewUiBinder ourUiBinder = GWT.create(MenuLogoutViewUiBinder.class);

    @UiField
    MaterialLink root;

    public MenuLogoutView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @UiHandler("root")
    void onLogoutClick(ClickEvent event){
        logUserOut();
    }

    public void logUserOut() {
        if(nonNull(logoutHandler))
            logoutHandler.logout();
    }
}