package org.akab.engine.core.client.request;

import org.akab.engine.core.api.client.request.RequestHolder;
import org.akab.engine.core.api.client.request.RequestsRepository;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

import java.util.HashMap;

public class InMemoryRequestsRepository implements RequestsRepository{

    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(InMemoryRequestsRepository.class);

    private final HashMap<String, RequestHolder> requestPresenterWrappers=new HashMap<>();

    @Override
    public void registerRequest(RequestHolder requestHolder) {
        LOGGER.info("registering request : "+requestHolder.getRequestName()+ " , "+requestHolder.getPresenterName());
        if(isRegisteredRequest(requestHolder.getRequestName()))
            throw new RequestCannotBeRegisteredMoreThanOnce("Request key ["+ requestHolder.getRequestName()+"]");
        requestPresenterWrappers.put(requestHolder.getRequestName(), requestHolder);
    }

    @Override
    public RequestHolder findRequestPresenterWrapper(String requestName) {
        if(isRegisteredRequest(requestName))
            return requestPresenterWrappers.get(requestName);
        throw new RequestKeyNotFoundException("Request Key ["+requestName+"]");
    }

    @Override
    public void clear() {
        requestPresenterWrappers.clear();
    }

    private boolean isRegisteredRequest(String requestName) {
        return requestPresenterWrappers.containsKey(requestName);
    }
}
