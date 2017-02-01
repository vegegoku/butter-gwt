package com.progressoft.security.jpa;

import com.progressoft.security.JpaStoresLoader;
import com.progressoft.security.jpa.gateway.UserGateway;


import static java.util.Objects.*;

public class GatewayContext {

    private static UserGateway userGateway;

    private GatewayContext(){}

    public static UserGateway userGateway() {
        if(isNull(userGateway))
            JpaStoresLoader.load();
        return userGateway;
    }

    public static void withUserGateway(UserGateway userGateway) {
        GatewayContext.userGateway = userGateway;
    }
}
