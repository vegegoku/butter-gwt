package com.progressoft.security.authentication.shared.response;

import org.akab.engine.core.api.shared.request.ServerResponse;

public class FindRootChainResponse extends ServerResponse{

    private String rootChain;

    public FindRootChainResponse() {
    }

    public FindRootChainResponse(String rootChain) {
        this.rootChain = rootChain;
    }

    public String getRootChain() {
        return rootChain;
    }

    public void setRootChain(String rootChain) {
        this.rootChain = rootChain;
    }
}
