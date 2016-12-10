package org.akab.rafa.hello.world.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import org.akab.engine.core.api.client.annotations.UiView;
import org.akab.rafa.hello.world.client.presenters.HelloWorldPresenter;

@UiView(presentable = HelloWorldPresenter.class)
public class DefaultHelloWorldView extends Composite implements HelloWorldView{

    interface DefaultHelloWorldViewUiBinder extends UiBinder<HTMLPanel, DefaultHelloWorldView> {
    }

    private static DefaultHelloWorldViewUiBinder ourUiBinder = GWT.create(DefaultHelloWorldViewUiBinder.class);

    @UiField
    DivElement mainDiv;

    public DefaultHelloWorldView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void setWelcomeMessage(String welcomeMessage) {
        mainDiv.setInnerText(welcomeMessage);
    }
}