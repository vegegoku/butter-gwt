package org.akab.engine.core.test;

import javax.servlet.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TestFilterChain implements FilterChain {

    private final List<Filter> filters=new LinkedList<>();
    private final ServletRequest servletRequest;
    private final ServletResponse servletResponse;
    private int filterIndex=0;

    public TestFilterChain(ServletRequest servletRequest, ServletResponse servletResponse) {
        this.servletRequest = servletRequest;
        this.servletResponse = servletResponse;
    }

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    public void filter() throws IOException, ServletException {
        filterIndex=0;
        if(!filters.isEmpty())
            executeFilter();
    }

    private void executeFilter() throws IOException, ServletException {
        filters.get(filterIndex++).doFilter(servletRequest, servletResponse, this);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse)
            throws IOException, ServletException {
        if(filterIndex<filters.size())
            filters.get(filterIndex++).doFilter(servletRequest, servletResponse, this);
    }

    void clear() {
        filters.clear();
    }
}
