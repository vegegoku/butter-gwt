package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.history.RequestFromPath;
import org.akab.engine.core.api.client.history.TokenizedPath;

public class SampleMapper implements RequestFromPath<AnnotatedClassWithPathAndCustomMapper> {

    @Override
    public AnnotatedClassWithPathAndCustomMapper buildRequest(TokenizedPath path) {
        return null;
    }
}