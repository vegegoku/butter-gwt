package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.History.RequestFromPath;
import org.akab.engine.core.api.client.History.TokenizedPath;

public class SampleMapper implements RequestFromPath<AnnotatedClassWithPathAndCustomMapper> {

    @Override
    public AnnotatedClassWithPathAndCustomMapper buildRequest(TokenizedPath path) {
        return null;
    }
}