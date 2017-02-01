package org.akab.engine.core.test;

import org.akab.engine.core.server.ServerConfigurationLoader;
import org.junit.Before;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static org.easymock.EasyMock.*;

public abstract class ModuleTestCase {

    protected TestModule testModule;
    protected TestEntryPointContext testEntryPointContext;
    protected HttpServletRequest httpRequest;
    protected HttpServletResponse httpResponse;
    protected Map<String, Object> attributes=new HashMap<>();
    protected HttpSession session=new FakeSession(attributes);
    protected TestFilterChain filterChain;

    @Before
    public void moduleSetup(){

        httpRequest=createMock(HttpServletRequest.class);
        httpResponse=createMock(HttpServletResponse.class);
        expect(httpRequest.getSession()).andReturn(session).anyTimes();
        replay(httpRequest);

        filterChain=new TestFilterChain(httpRequest, httpResponse);

        testModule = new TestModule();
        testEntryPointContext=new TestEntryPointContext();
        testModule.init(testEntryPointContext, filterChain);
        testEntryPointContext.setHttpRequest(httpRequest);
        testEntryPointContext.setHttpServletResponse(httpResponse);
        attributes.clear();
        filterChain.clear();
        setUp();
        new ServerConfigurationLoader().loadModules();
        testModule.run();
        afterRun();
    }

    protected abstract void setUp();

    protected void afterRun(){

    }

}