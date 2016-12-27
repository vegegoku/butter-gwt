package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.annotations.Path;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.annotation.processor.client.SampleMapper;

@Path(path = "somePath", mapper = SampleMapper.class)
public class AnnotatedClassWithPathAndCustomMapper extends ClientRequest<PresenterInterface> {

    @Override
    protected void process(PresenterInterface presenter) {

    }
}