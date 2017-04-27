package com.progressoft.security.user.client.views.button;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;

import com.progressoft.security.user.client.views.AddUserCompositeView;
import gwt.material.design.client.ui.MaterialAnchorButton;

public class AddUserButtonView extends Composite implements ButtonView {


    private AddUserCompositeView.AddUserTriggerHandler addUserTriggerHandler;

    public void setAddUserTriggerHandler(AddUserCompositeView.AddUserTriggerHandler handler){
        this.addUserTriggerHandler=handler;
    }

    @Override
    public void onTrigger() {
        this.addUserTriggerHandler.handle();
    }

    interface AddUserButtonViewUiBinder extends UiBinder<MaterialAnchorButton, AddUserButtonView> {
    }

    private static AddUserButtonViewUiBinder ourUiBinder = GWT.create(AddUserButtonViewUiBinder.class);


    public AddUserButtonView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @UiHandler("root")
    void onAddUserButtonClicked(ClickEvent event){
        onTrigger();
    }


}