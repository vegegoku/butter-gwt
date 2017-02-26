package com.progressoft.security.repository;

import com.progressoft.security.authentication.shared.extension.Principal;

import java.util.Deque;
import java.util.LinkedList;

public class FakePrincipal implements Principal {

    private final String name;
    private final String tenant;
    private final String displayName;

    private final Deque<String> chains;

    public FakePrincipal(String name, String tenant) {
        this.name = name;
        this.tenant = tenant;
        this.displayName=name;
        this.chains= new LinkedList<>();
    }

    public FakePrincipal(String name, String tenant, String displayName, Deque<String> chains) {
        this.name = name;
        this.tenant = tenant;
        this.displayName=displayName;
        this.chains= chains;
    }

    public FakePrincipal(Deque<String> chains) {
        this.name = "";
        this.tenant = "";
        this.displayName="";
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
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public Deque<String> chains() {
        return chains;
    }
}
