package org.akab.engine.app.test;

import org.akab.engine.core.api.client.History.UrlHistory;

public class TestUrlHistory implements UrlHistory{

    public String token;

    @Override
    public void apply(String urlToken) {
        token=urlToken;
    }
}
