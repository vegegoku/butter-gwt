package org.akab.engine.core.api.client.History;

public interface TokenizedPath {
    String path();
    void addParameter(String name, String value);
    String getParameter(String name);
    boolean containsParameter(String name);
}
