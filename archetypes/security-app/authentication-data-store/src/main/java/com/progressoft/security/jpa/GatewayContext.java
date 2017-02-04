package com.progressoft.security.jpa;

import com.progressoft.security.JpaStoresLoader;
import com.progressoft.security.jpa.gateway.ChainsGateway;
import com.progressoft.security.jpa.gateway.UserGateway;

import static java.util.Objects.isNull;

public class GatewayContext {

    private static UserGateway userGateway;
    private static ChainsGateway chainsGateway;

    private GatewayContext() {
    }

    public static UserGateway userGateway() {
        if (isNull(userGateway))
            JpaStoresLoader.load();
        return userGateway;
    }

    public static void withUserGateway(UserGateway userGateway) {
        GatewayContext.userGateway = userGateway;
    }

    public static void withChainsGateway(ChainsGateway chainsGateway) {
        GatewayContext.chainsGateway = chainsGateway;
    }

    public static ChainsGateway chainsGateway() {
        if (isNull(chainsGateway))
            JpaStoresLoader.load();
        return chainsGateway;
    }
}
