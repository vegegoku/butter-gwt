package org.akab.engine.core.api.client.request;

import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.request.FailedServerResponse;
import org.akab.engine.core.api.shared.request.ServerRequest;

public interface ServerFailedHandler<P extends Presentable, R extends ServerRequest> {

    default void processFailed(P presenter, R serverArgs, FailedServerResponse failedResponse){
    }
}
