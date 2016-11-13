package org.akab.engine.core.client.history;

import org.akab.engine.core.api.client.History.Token;
import org.akab.engine.core.api.client.History.TokenConstruct;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class PathTokenConstructor implements TokenConstruct {


    private static final String EMPTY_TOKEN ="";
    private Deque<Token> tokens=new LinkedList<>();

    public String toUrl() {
        return asUrl(tokensIterator());
    }

    private String asUrl(Iterator<Token> tokenIterator){
        if(tokenIterator.hasNext())
            return concatTokensString(tokenIterator.next().asTokenString(), tokenIterator);
        return EMPTY_TOKEN;
    }

    private Iterator<Token> tokensIterator(){
        return tokens.iterator();
    }

    private String concatTokensString(String token, Iterator<Token> tokenIterator) {
        if(tokenIterator.hasNext())
            return concatTokensString(tokenIterator.next().asTokenString(), tokenIterator)+"&"+ token;
        return token;
    }

    public TokenConstruct append(Token token) {
        tokens.push(token);
        return this;
    }

    @Override
    public TokenConstruct appendOnce(Token token) {
        if(!tokens.contains(token))
            tokens.push(token);
        return this;
    }

    public TokenConstruct replaceAll(Token token) {
        tokens.clear();
        tokens.push(token);
        return this;
    }

    public TokenConstruct replaceLast(Token token) {
        tokens.pop();
        tokens.push(token);
        return this;
    }

    @Override
    public TokenConstruct replaceAllOfSamePath(Token token) {
        tokens.removeAll(tokens.stream().filter(t->t.hasSamePath(token)).collect(Collectors.toSet()));
        append(token);
        return this;
    }

    public TokenConstruct clear() {
        tokens.clear();
        return this;
    }

    public TokenConstruct clearLast() {
        tokens.pop();
        return this;
    }
}
