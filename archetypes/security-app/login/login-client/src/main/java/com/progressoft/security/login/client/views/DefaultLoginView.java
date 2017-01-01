package com.progressoft.security.login.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import gwt.material.design.client.base.HasTitle;
import gwt.material.design.client.ui.*;
import gwt.material.design.client.ui.animate.MaterialAnimation;
import gwt.material.design.client.ui.animate.Transition;
import org.akab.engine.core.api.client.annotations.UiView;

import com.progressoft.security.login.client.presenters.LoginPresenter;

@UiView(presentable = LoginPresenter.class)
public class DefaultLoginView implements LoginView{

    @Override
    public Widget asWidget() {
        return mainPanel;
    }

    interface DefaultLoginViewUiBinder extends UiBinder<Widget, DefaultLoginView> {
    }

    private static DefaultLoginViewUiBinder ourUiBinder = GWT.create(DefaultLoginViewUiBinder.class);

    Widget mainPanel;

    @UiField
    MaterialLabel supportAvailabilityTitle;

    @UiField
    MaterialLabel supportTitle;

    @UiField
    MaterialButton btnLogin;

    @UiField
    MaterialRow gridPanel;

    public DefaultLoginView() {
        mainPanel=ourUiBinder.createAndBindUi(this);
        gridPanel.getElement().getStyle().setOpacity(0);
        try {

            LoginBundle.LOGIN_BUNDLE.support().getText(new ResourceCallback<TextResource>() {
                @Override
                public void onError(ResourceException e) {

                }

                @Override
                public void onSuccess(TextResource textResource) {
                    setSupportDescription(textResource.getText());
                }
            });

            LoginBundle.LOGIN_BUNDLE.supportAvailability().getText(new ResourceCallback<TextResource>() {
                @Override
                public void onError(ResourceException e) {

                }

                @Override
                public void onSuccess(TextResource textResource) {
                    supportAvailabilityTitle.setText(textResource.getText());
                }
            });
        } catch (ResourceException e) {
            e.printStackTrace();
        }

        MaterialAnimation gridAnimation = new MaterialAnimation();
        gridAnimation.transition(Transition.SHOW_GRID);
        gridAnimation.animate(gridPanel);

    }

    @Override
    public HasClickHandlers loginTrigger() {
        return btnLogin;
    }

    protected void setSupportDescription(String text){
        supportTitle.setText(text);
    }
}