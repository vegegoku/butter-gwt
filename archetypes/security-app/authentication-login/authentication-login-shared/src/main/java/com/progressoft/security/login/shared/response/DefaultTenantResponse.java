package com.progressoft.security.login.shared.response;

import org.akab.engine.core.api.shared.request.ServerResponse;

public class DefaultTenantResponse extends ServerResponse{
    private String defaultTenant;

    private DefaultTenantResponse() {
        //for GWT serialization.
    }

    public DefaultTenantResponse(String defaultTenant) {
        this.defaultTenant = defaultTenant;
    }

    public String getDefaultTenant() {
        return defaultTenant;
    }

    public void setDefaultTenant(String defaultTenant) {
        this.defaultTenant = defaultTenant;
    }
}
