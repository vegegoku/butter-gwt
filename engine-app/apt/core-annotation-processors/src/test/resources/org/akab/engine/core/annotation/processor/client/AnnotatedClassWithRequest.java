package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.client.request.ClientRequest;

@Request
public class AnnotatedClassWithRequest extends ClientRequest<PresenterInterface> {

    @Override
    protected void process(PresenterInterface presenter) {

    }
}