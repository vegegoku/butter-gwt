package com.progressoft.security.logout.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.progressoft.security.logout.client.presenters.LogoutHandler;
import com.progressoft.security.logout.client.presenters.LogoutPresenter;
import gwt.material.design.client.ui.MaterialLink;
import org.akab.engine.core.api.client.annotations.UiView;

import static java.util.Objects.nonNull;

public class HeaderLogoutView extends Composite implements LogoutView{

    interface HeaderLogoutViewUiBinder extends UiBinder<MaterialLink, HeaderLogoutView> {
    }

    private LogoutHandler logoutHandler;

    @Override
    public void setLogoutHandler(LogoutHandler logoutHandler) {
        this.logoutHandler=logoutHandler;
    }

    private static HeaderLogoutViewUiBinder ourUiBinder = GWT.create(HeaderLogoutViewUiBinder.class);

    @UiField
    MaterialLink root;

    public HeaderLogoutView() {
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