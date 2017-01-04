package com.progressoft.security.sample.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.akab.engine.core.api.client.annotations.UiView;

import com.progressoft.security.sample.client.presenters.SamplePresenter;

@UiView(presentable = SamplePresenter.class)
public class DefaultSampleView extends Composite implements SampleView{

    interface DefaultSampleViewUiBinder extends UiBinder<HTMLPanel, DefaultSampleView> {
    }

    private static DefaultSampleViewUiBinder ourUiBinder = GWT.create(DefaultSampleViewUiBinder.class);

    @UiField
    DivElement mainDiv;

    public DefaultSampleView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void setWelcomeMessage(String welcomeMessage) {
        mainDiv.setInnerText(welcomeMessage);
    }
}