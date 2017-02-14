package com.progressoft.security.app.layout.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.akab.engine.core.api.client.annotations.UiView;

import com.progressoft.security.app.layout.client.presenters.AppLayoutPresenter;

@UiView(presentable = AppLayoutPresenter.class)
public class DefaultLayoutView extends Composite implements LayoutView{

    interface DefaultLayoutViewUiBinder extends UiBinder<HTMLPanel, DefaultLayoutView> {
    }

    private static DefaultLayoutViewUiBinder ourUiBinder = GWT.create(DefaultLayoutViewUiBinder.class);


    public DefaultLayoutView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

}