package com.progressoft.security.user.client.views;

import com.progressoft.security.user.client.presenters.AddUserPresenter;
import org.akab.engine.core.api.client.mvp.view.CompositeView;

public interface AddUserCompositeView extends CompositeView<AddUserView>{

    interface Fields{
        String USERNAME = "USERNAME";
    }

    interface AddUserTriggerHandler {
        void handle();
    }

    interface CreateUserHandler {
        void onCreate(AddUserPresenter.NewUserData newUserData);
    }

    String BUTTON = "BUTTON";
    String FORM = "FORM";


    void setAddUserTriggerHandler(AddUserTriggerHandler handler);
    void setCreateUserHandler(AddUserCompositeView.CreateUserHandler handler);
    void markFieldInvalid(String username, String required);


}
