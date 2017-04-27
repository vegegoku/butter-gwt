package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.annotations.Path;
import org.akab.engine.core.api.client.annotations.PathParameter;
import org.akab.engine.core.api.client.request.ClientRequest;

@Path(path = "somePath")
public class AnnotatedClassWithPathAndPathParameter extends ClientRequest<PresenterInterface> {

    private String value;

    public AnnotatedClassWithPathAndPathParameter(@PathParameter String value) {
        this.value = value;
    }

    @Override
    protected void process(PresenterInterface presenter) {

    }
}