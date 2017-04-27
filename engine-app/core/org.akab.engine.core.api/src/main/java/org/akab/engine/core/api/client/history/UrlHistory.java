package org.akab.engine.core.api.client.history;

@FunctionalInterface
public interface UrlHistory {
    void apply(String urlToken);
}
