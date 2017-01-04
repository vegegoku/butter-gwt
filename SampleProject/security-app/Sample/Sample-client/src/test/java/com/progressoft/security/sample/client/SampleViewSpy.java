package com.progressoft.security.sample.client;

import com.progressoft.security.sample.client.views.DefaultSampleView;

public class SampleViewSpy extends DefaultSampleView {

    private String welcomeMessage;

    @Override
    public void setWelcomeMessage(String welcomeMessage) {
        super.setWelcomeMessage(welcomeMessage);
        this.welcomeMessage=welcomeMessage;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}
