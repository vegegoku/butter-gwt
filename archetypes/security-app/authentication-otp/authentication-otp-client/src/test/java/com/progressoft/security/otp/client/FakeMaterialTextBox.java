package com.progressoft.security.otp.client;

import gwt.material.design.client.ui.MaterialTextBox;

public class FakeMaterialTextBox extends MaterialTextBox {

    private String value;

    @Override
    public void setValue(String value) {
        super.setValue(value);
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
