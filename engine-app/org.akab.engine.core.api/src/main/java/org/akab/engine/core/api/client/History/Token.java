package org.akab.engine.core.api.client.History;


public interface Token {

    String asTokenString();
    Token appendParameter(String name, String value);
}
