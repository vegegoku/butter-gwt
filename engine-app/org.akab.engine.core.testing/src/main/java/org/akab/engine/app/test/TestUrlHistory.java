package org.akab.engine.app.test;

import org.akab.engine.core.api.client.history.UrlHistory;

public class TestUrlHistory implements UrlHistory{

    private String token;

    @Override
    public void apply(String urlToken) {
        token=urlToken;
    }

    public String getToken() {
        return token;
    }
}
