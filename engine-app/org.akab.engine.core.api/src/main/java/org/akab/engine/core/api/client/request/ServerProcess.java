package org.akab.engine.core.api.client.request;

import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.shared.request.ServerRequest;


public interface ServerProcess<P extends Presentable, R extends ServerRequest, S extends ServerResponse> extends Request<P>{


}
