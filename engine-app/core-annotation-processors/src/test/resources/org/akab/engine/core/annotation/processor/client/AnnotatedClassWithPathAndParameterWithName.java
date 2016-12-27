package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.annotations.Path;
import org.akab.engine.core.api.client.annotations.PathParameter;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.annotation.processor.client.PresenterInterface;

@Path(path = "somePath")
public class AnnotatedClassWithPathAndParameterWithName extends ClientRequest<PresenterInterface> {

    private String value;

    public AnnotatedClassWithPathAndParameterWithName(@PathParameter(name = "someValue") String value) {
        this.value = value;
    }

    @Override
    protected void process(PresenterInterface presenter) {

    }
}