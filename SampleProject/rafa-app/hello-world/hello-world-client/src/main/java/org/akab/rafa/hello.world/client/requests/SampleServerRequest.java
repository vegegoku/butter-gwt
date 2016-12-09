package org.akab.rafa.hello.world.client.requests;


import com.google.gwt.user.client.Window;
import org.akab.engine.core.api.client.request.ClientServerRequest;
import org.akab.rafa.HelloResponse;
import org.akab.rafa.HelloWordsArgs;
import org.akab.rafa.hello.world.client.presenters.HelloWorldPresenter;

public class SampleServerRequest extends ClientServerRequest<HelloWorldPresenter, HelloWordsArgs, HelloResponse> {
    @Override
    protected void process(HelloWorldPresenter presenter, HelloWordsArgs serverArgs, HelloResponse response) {
        Window.alert(response.getServerMessage());
    }

    @Override
    public HelloWordsArgs buildArguments() {
        return new HelloWordsArgs("client message");
    }
}
