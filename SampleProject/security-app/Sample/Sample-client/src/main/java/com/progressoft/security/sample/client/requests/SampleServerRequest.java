package com.progressoft.security.sample.client.requests;

import com.google.gwt.user.client.Window;
import org.akab.engine.core.api.client.request.ClientServerRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;
import com.progressoft.security.sample.shared.response.SampleResponse;
import com.progressoft.security.sample.shared.request.SampleRequest;
import com.progressoft.security.sample.client.presenters.SamplePresenter;
import org.akab.engine.core.api.client.annotations.Request;

@Request
public class SampleServerRequest extends ClientServerRequest<SamplePresenter, SampleRequest, SampleResponse> {

    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(SampleServerRequest.class);

    @Override
    protected void process(SamplePresenter presenter, SampleRequest serverRequest, SampleResponse response) {
        LOGGER.info("Message recieved from server :  Sample module : "+response.getServerMessage());
        presenter.setResponse(response.getServerMessage());
    }

    @Override
    public SampleRequest buildArguments() {
        return new SampleRequest("client message");
    }
}
