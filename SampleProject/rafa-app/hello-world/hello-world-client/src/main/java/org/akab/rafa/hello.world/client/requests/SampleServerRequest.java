package org.akab.rafa.hello.world.client.requests;


import org.akab.engine.core.api.client.request.ClientServerRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;
import org.akab.rafa.response.HelloResponse;
import org.akab.rafa.request.HelloWordsArgs;
import org.akab.rafa.hello.world.client.presenters.HelloWorldPresenter;

public class SampleServerRequest extends ClientServerRequest<HelloWorldPresenter, HelloWordsArgs, HelloResponse> {

    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(SampleServerRequest.class);

    @Override
    protected void process(HelloWorldPresenter presenter, HelloWordsArgs serverArgs, HelloResponse response) {
        LOGGER.info("Message recieved from server : "+response.getServerMessage());
    }

    @Override
    public HelloWordsArgs buildArguments() {
        return new HelloWordsArgs("client message");
    }
}
