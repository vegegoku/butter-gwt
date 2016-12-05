package org.akab.engine.core.server;

import org.akab.engine.core.api.shared.server.GlobalInterceptor;
import org.akab.engine.core.api.shared.server.InterceptorsRepository;
import org.akab.engine.core.api.shared.server.RequestInterceptor;

import java.util.*;

public class InMemoryInterceptorsRepository implements InterceptorsRepository {

    private final Map<String, Map<String, Set<RequestInterceptor>>> interceptors = new HashMap<>();

    private final Map<String, Set<GlobalInterceptor>> globalInterceptors=new HashMap<>();

    @Override
    public void addInterceptor(String requestName, String entryPointName, RequestInterceptor interceptor) {
        if (interceptors.containsKey(requestName))
            interceptors.get(requestName).get(entryPointName).add(interceptor);
        else
            interceptors.put(requestName, new HashMap<String, Set<RequestInterceptor>>(){{
            put(entryPointName, new HashSet<RequestInterceptor>(){{
                add(interceptor);
            }});
        }});
    }

    @Override
    public Collection<RequestInterceptor> findInterceptors(String requestName, String entryPointName) {
        if(interceptors.containsKey(requestName))
            return interceptors.get(requestName).get(entryPointName);
        return new HashSet<>();
    }

    @Override
    public void addGlobalInterceptor(String entryPointName, GlobalInterceptor interceptor) {
        if(globalInterceptors.containsKey(entryPointName))
            globalInterceptors.get(entryPointName).add(interceptor);
        else
            globalInterceptors.put(entryPointName, new HashSet<GlobalInterceptor>(){{
                add(interceptor);
            }});
    }

    @Override
    public Collection<GlobalInterceptor> findGlobalInterceptors(String entryPointName) {
        if(globalInterceptors.containsKey(entryPointName))
            return globalInterceptors.get(entryPointName);
        return new HashSet<>();
    }
}
