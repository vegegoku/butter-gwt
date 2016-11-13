package org.akab.engine.core.api.client.request;

import org.akab.engine.core.api.client.mvp.presenter.ClientPresenter;
import org.akab.engine.core.api.shared.request.Response;
import org.akab.engine.core.api.shared.request.ServerArgs;


public interface ServerProcess<P extends ClientPresenter, R extends ServerArgs, S extends Response> extends Request<P>{


}