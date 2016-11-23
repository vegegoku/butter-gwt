package org.akab.engine.core.client.history;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import org.akab.engine.core.api.client.History.PathToRequestMappersRepository;
import org.akab.engine.core.api.client.History.TokenizedPath;
import org.akab.engine.core.api.client.History.UrlPathTokenizer;
import org.akab.engine.core.api.client.request.Request;

import java.util.Deque;
import java.util.Objects;

public class HistoryChangeHandler implements ValueChangeHandler<String> {

    public static final String PATH="_path";
    public static final String PARAMETER_SEPARATOR="&";

    private final PathToRequestMappersRepository mappersRepository;

    public HistoryChangeHandler(PathToRequestMappersRepository repository) {
        this.mappersRepository=repository;
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        Deque<TokenizedPath> tokens=new UrlPathTokenizer(PATH, PARAMETER_SEPARATOR).tokenize(event.getValue());

        if(!tokens.isEmpty()) {
            Request rootRequest=mapTokenToRequest(tokens.pop());
            chainRequest(rootRequest, tokens);
            rootRequest.send();
        }

    }

    private void chainRequest(Request rootRequest, Deque<TokenizedPath> tokens) {
        if(Objects.nonNull(tokens.peek()))
            buildChain(rootRequest, tokens, mapTokenToRequest(tokens.pop()));
    }

    private void buildChain(Request rootRequest, Deque<TokenizedPath> tokens, Request chain) {
        rootRequest.chainRequest(chain);
        chainRequest(chain, tokens);
    }

    private Request mapTokenToRequest(TokenizedPath rootPath) {
        return mappersRepository.getMapper(rootPath.path()).buildRequest(rootPath);
    }
}
