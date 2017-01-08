package org.akab.engine.app.test;

import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.request.ServerResponse;

@FunctionalInterface
public interface TestServerService {

    interface RequestExecutionCallBack{
        void onSuccess(ServerResponse response);
        void onFailed(ServerResponse response);
    }

    ServerResponse executeRequest(ServerRequest request);
}
