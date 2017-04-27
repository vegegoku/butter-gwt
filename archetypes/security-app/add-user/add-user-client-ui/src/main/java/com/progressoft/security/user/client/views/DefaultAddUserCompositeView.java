package com.progressoft.security.user.client.views;

import com.google.gwt.user.client.ui.Widget;
import com.progressoft.security.user.client.presenters.AddUserPresenter;
import com.progressoft.security.user.client.views.button.AddUserButtonView;
import com.progressoft.security.user.client.views.form.AddUserFormView;
import gwt.material.design.client.ui.MaterialTextBox;
import org.akab.engine.core.api.client.annotations.UiView;

import java.util.HashMap;
import java.util.Map;

@UiView(presentable = AddUserPresenter.class)
public class DefaultAddUserCompositeView implements AddUserCompositeView {

    private Map<String, AddUserView> views = new HashMap<>();
    private final AddUserButtonView buttonView = new AddUserButtonView();

    private final AddUserFormView formView = new AddUserFormView();

    public DefaultAddUserCompositeView() {
        views.put(AddUserCompositeView.BUTTON, buttonView);
        views.put(AddUserCompositeView.FORM, formView);
    }

    @Override
    public AddUserView getView(String classifier) {
        if (views.containsKey(classifier))
            return views.get(classifier);
        throw new ViewNotFoundInCompositeView(classifier);
    }

    @Override
    public String defaultViewClassifier() {
        return AddUserCompositeView.FORM;
    }

    @Override
    public Widget asWidget() {
        return getView(defaultViewClassifier()).asWidget();
    }

    @Override
    public void markFieldInvalid(String username, String message) {
        formView.getUserNameField().setError(message);
    }


    @Override
    public void setCreateUserHandler(CreateUserHandler handler) {
        formView.setCreateUserHandler(handler);
    }

    @Override
    public void setAddUserTriggerHandler(AddUserTriggerHandler handler) {
        this.buttonView.setAddUserTriggerHandler(handler);
    }

    protected void triggerCreateUser() {
        formView.triggerCreateUserHandler();
    }

    protected MaterialTextBox getUserNameField(){
        return formView.getUserNameField();
    }

    protected void onAddUserTriggered() {
        this.buttonView.onTrigger();
    }
}
