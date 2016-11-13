package org.akab.engine.core.api.client.History;


public interface TokenConstruct {

    String toUrl();
    TokenConstruct append(Token token);
    TokenConstruct appendOnce(Token token);
    TokenConstruct replaceAll(Token token);
    TokenConstruct replaceLast(Token token);
    TokenConstruct clear();
    TokenConstruct clearLast();
    TokenConstruct replaceAllOfSamePath(Token firstTestToken);
}
