package com.progressoft.security.jpa.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="gateways")
public class Gateways {

    @Autowired
    private UserGateway userGateway;

    public UserGateway getUserGateway() {
        return userGateway;
    }

    public void setUserGateway(UserGateway userGateway) {
        this.userGateway = userGateway;
    }
}
