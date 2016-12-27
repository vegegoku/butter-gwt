package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.annotations.Path;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.annotation.processor.client.PresenterInterface;

@Path(path = "somePath")
public class AnnotatedClassWithPathAndNoParamters extends ClientRequest<PresenterInterface> {
    @Override
    protected void process(PresenterInterface presenter) {

    }
}