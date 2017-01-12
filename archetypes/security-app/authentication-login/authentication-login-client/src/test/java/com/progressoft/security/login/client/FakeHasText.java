package com.progressoft.security.login.client;

import com.google.gwt.user.client.ui.HasText;

public class FakeHasText implements HasText {

    private String text;

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text=text;
    }
}
