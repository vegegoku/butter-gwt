package org.akab.engine.app.test;

import org.junit.Before;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.easymock.EasyMock.*;

public abstract class ModuleTestCase {

    protected TestModule testModule;
    protected TestEntryPointContext testEntryPointContext;
    protected HttpServletRequest httpRequest;
    protected HttpServletResponse httpResponse;
    protected HttpSession session;

    @Before
    public void moduleSetup(){

        httpRequest=createMock(HttpServletRequest.class);
        httpResponse=createMock(HttpServletResponse.class);
        session=createMock(HttpSession.class);
        expect(httpRequest.getSession()).andReturn(session).anyTimes();
        replay(httpRequest);

        testModule = new TestModule();
        testEntryPointContext=new TestEntryPointContext();
        testModule.init(testEntryPointContext);
        testEntryPointContext.setHttpRequest(httpRequest);
        testEntryPointContext.setHttpServletResponse(httpResponse);
        setUp();
        testModule.run();
    }

    protected abstract void setUp();

}