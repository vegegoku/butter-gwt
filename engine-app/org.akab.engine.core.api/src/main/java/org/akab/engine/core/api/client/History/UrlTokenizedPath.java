package org.akab.engine.core.api.client.History;

import org.akab.engine.core.api.client.History.TokenizedPath;

import java.util.HashMap;
import java.util.Map;

public class UrlTokenizedPath implements TokenizedPath{

    private final String path;
    private final Map<String, String> parameters=new HashMap<>();

    public UrlTokenizedPath(String path) {
        this.path=path;
    }

    public void addParameter(String name, String value) {
        parameters.put(name, value);
    }

    public UrlTokenizedPath(String path, Map<String, String> parameters) {
        this.path=path;
        this.parameters.putAll(parameters);
    }

    @Override
    public String path() {
        return this.path;
    }

    @Override
    public String getParameter(String name) {
        return parameters.get(name);
    }

    @Override
    public boolean containsParameter(String name) {
        return parameters.containsKey(name);
    }

    @Override
    public String toString() {
        return "UrlTokenizedPath{" +
                "path='" + path + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
