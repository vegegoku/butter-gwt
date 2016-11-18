package org.akab.engine.core.api.client.History;

import org.akab.engine.core.api.client.request.Request;

public interface RequestFromPath<R extends Request, T extends TokenizedPath> {

    R buildRequest(T tokenizePath);
}
