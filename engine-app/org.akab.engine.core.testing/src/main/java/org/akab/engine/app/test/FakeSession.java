package org.akab.engine.app.test;

import org.apache.commons.collections.iterators.IteratorEnumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.*;

public class FakeSession implements HttpSession {

    private final Map<String, Object> attributes;
    private Date creationDate;
    private String id=UUID.randomUUID().toString();

    public FakeSession(Map<String, Object> attributes) {
        this.attributes = attributes;
        creationDate=new Date();
    }

    @Override
    public long getCreationTime() {
        return creationDate.getTime();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public void setMaxInactiveInterval(int i) {

    }

    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    @Override
    public Object getAttribute(String s) {
        return attributes.get(s);
    }

    @Override
    public Object getValue(String s) {
        return attributes.get(s);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return new IteratorEnumeration(attributes.keySet().iterator());
    }

    @Override
    public String[] getValueNames() {
        String[] names=new String[attributes.keySet().size()];
        return attributes.keySet().toArray(names);
    }

    @Override
    public void setAttribute(String s, Object o) {
        attributes.put(s,o);
    }

    @Override
    public void putValue(String s, Object o) {
        attributes.put(s,o);
    }

    @Override
    public void removeAttribute(String s) {
        attributes.remove(s);
    }

    @Override
    public void removeValue(String s) {
        attributes.remove(s);
    }

    @Override
    public void invalidate() {
        id=null;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    public void clear(){
        attributes.clear();
    }
}
