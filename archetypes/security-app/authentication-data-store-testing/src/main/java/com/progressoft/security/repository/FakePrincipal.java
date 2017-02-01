package com.progressoft.security.repository;

import com.progressoft.security.authentication.shared.extension.Principal;

import java.util.Deque;
import java.util.LinkedList;

public class FakePrincipal implements Principal {

    private final String name;
    private final String tenant;
    private final Deque<String> chains;

    public FakePrincipal(String name, String tenant) {
        this.name = name;
        this.tenant = tenant;
        this.chains= new LinkedList<>();
    }

    public FakePrincipal(String name, String tenant, Deque<String> chains) {
        this.name = name;
        this.tenant = tenant;
        this.chains= chains;
    }

    public FakePrincipal(Deque<String> chains) {
        this.name = "";
        this.tenant = "";
        this.chains= chains;
    }

    @Override
    public String getUserName() {
        return name;
    }

    @Override
    public String getTenant() {
        return tenant;
    }

    @Override
    public Deque<String> chains() {
        return chains;
    }
}
