package org.akab.rafa.hello.world.client.requests;

import org.akab.engine.core.api.client.History.PathToken;
import org.akab.engine.core.api.client.History.TokenConstruct;
import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import org.akab.rafa.hello.world.client.presenters.HelloWorldPresenter;

@Request(presentable = HelloWorldPresenter.class)
public class HelloWorldSampleClientRequest extends ClientRequest<HelloWorldPresenter> {

    private final MainExtensionPoint mainExtensionPoint;

    private String welcomeMessage;

    public HelloWorldSampleClientRequest(MainExtensionPoint mainExtensionPoint, String welcomeMessage) {
        this.mainExtensionPoint=mainExtensionPoint;
        this.welcomeMessage=welcomeMessage;
    }

    @Override
    protected void process(HelloWorldPresenter presenter) {
        presenter.contributeToMainModule(mainExtensionPoint, welcomeMessage);
    }

    @Override
    protected void constructHistoryToken(TokenConstruct tokenConstruct) {
        tokenConstruct.appendOnce(
                new SampleBuilder() PathToken.make("somePath").appendParameter("welcomeMessage", welcomeMessage));
    }
}