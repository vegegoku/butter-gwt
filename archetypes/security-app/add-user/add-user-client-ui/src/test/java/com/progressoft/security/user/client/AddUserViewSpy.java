package com.progressoft.security.user.client;

import com.progressoft.security.user.client.views.DefaultAddUserCompositeView;
import org.akab.engine.material.test.FakeMaterialTextBox;

public class AddUserViewSpy extends DefaultAddUserCompositeView {

    public void clickAddButton() {
        super.onAddUserTriggered();
    }

    public void clickCreateUser() {
        super.triggerCreateUser();
    }

    public FakeMaterialTextBox getUserNameField() {
        return (FakeMaterialTextBox) super.getUserNameField();
    }
}
