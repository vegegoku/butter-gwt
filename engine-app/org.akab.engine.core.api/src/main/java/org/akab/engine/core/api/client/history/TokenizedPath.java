package org.akab.engine.core.api.client.history;

public interface TokenizedPath {
    String path();
    String getParameter(String name);
    boolean containsParameter(String name);
}
