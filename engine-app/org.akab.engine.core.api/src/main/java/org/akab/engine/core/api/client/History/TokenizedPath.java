package org.akab.engine.core.api.client.History;

public interface TokenizedPath {
    String path();
    String getParameter(String name);
    boolean containsParameter(String name);
}
