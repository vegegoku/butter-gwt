package com.progressoft.security.user.client.views.form;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.progressoft.security.user.client.presenters.AddUserPresenter;
import com.progressoft.security.user.client.views.AddUserCompositeView;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;

public class AddUserFormView extends Composite implements FormView {

    interface AddUserFormViewUiBinder extends UiBinder<MaterialRow, AddUserFormView> {}
    private static AddUserFormViewUiBinder ourUiBinder = GWT.create(AddUserFormViewUiBinder.class);

    @UiField
    MaterialTextBox userName;

    private AddUserCompositeView.CreateUserHandler createUserHandler;

    public AddUserFormView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void triggerCreateUserHandler() {
        this.createUserHandler.onCreate(makeNewUserData());
    }

    public void setCreateUserHandler(AddUserCompositeView.CreateUserHandler handler) {
        this.createUserHandler=handler;
    }

    private AddUserPresenter.NewUserData makeNewUserData() {
        return () -> userName.getText();
    }

    public MaterialTextBox getUserNameField() {
        return userName;
    }

    @UiHandler("createButton")
    void onClickCreate(ClickEvent event){
        triggerCreateUserHandler();
    }

}