package org.akab.engine.core.api.client.history;

import org.akab.engine.core.api.client.request.Request;

@FunctionalInterface
public interface RequestFromPath<R extends Request> {

    R buildRequest(TokenizedPath path);
}
