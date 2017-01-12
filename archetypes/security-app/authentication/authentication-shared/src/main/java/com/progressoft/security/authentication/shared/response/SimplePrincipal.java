package com.progressoft.security.authentication.shared.response;

import com.progressoft.security.authentication.shared.extension.Principal;

import java.util.Deque;
import java.util.LinkedList;

public class SimplePrincipal implements Principal {

    private Deque<String> chains=new LinkedList<String>();

    public SimplePrincipal() {
        chains.add("XXX");
        chains.add("YYY");
    }

    public Deque<String> getChains() {
        return chains;
    }

    public void setChains(Deque<String> chains) {
        this.chains = chains;
    }

    @Override
    public Deque<String> chains() {
        return chains();
    }

    @Override
    public String toString() {
        return chains.toString();
    }
}
