package org.akab.engine.core.server;

import org.akab.engine.core.api.shared.server.*;
import org.junit.Before;
import org.junit.Test;

import static org.akab.engine.core.api.shared.server.HandlersRepository.*;
import static org.junit.Assert.*;

public class ServerAppTest {

    private ServerApp serverApp;
    private RequestExecutor requestExecutor;
    private HandlersRepository handlersRepository;
    private InterceptorsRepository interceptorsRepository;
    private TestRequest request;

    @Before
    public void setUp() throws Exception {
        handlersRepository=new InMemoryHandlersRepository();
        interceptorsRepository=new InMemoryInterceptorsRepository();
        requestExecutor=new DefaultRequestExecutor(handlersRepository, interceptorsRepository);
        serverApp=new ServerApp.ServerAppBuilder().executor(requestExecutor).handlersRepository(handlersRepository).interceptorsRepository(interceptorsRepository).build().run();
        request=new TestRequest();
    }

    @Test
    public void canRegisterRequestHandler() throws Exception {
        serverApp.registerHandler(TestRequest.class.getCanonicalName(), new TestRequestHandler());
        assertNotNull(serverApp.handlersRepository().findHandler(TestRequest.class.getCanonicalName()));
    }

    @Test(expected = RequestHandlerHaveAlreadyBeenRegistered.class)
    public void givenServerApp_whenRegisteringRequestHandlerMoreThanOnce_shouldThrowException()
            throws Exception {
        serverApp.registerHandler(TestRequest.class.getCanonicalName(), new TestRequestHandler());
        serverApp.registerHandler(TestRequest.class.getCanonicalName(), new TestRequestHandler());
    }

    @Test(expected =  RequestHandlerNotFound.class)
    public void givenServerApp_whenTryingToFindARequestHandlerThatHaveNotBeenRegistered_shouldThrowException()
            throws Exception {
        serverApp.handlersRepository().findHandler(TestRequest.class.getCanonicalName());
    }

    @Test
    public void givenServerApp_whenExecutingARequest_theRequestHandlerShouldBeInvoked() throws Exception {
        serverApp.registerHandler(TestRequest.class.getCanonicalName(), new TestRequestHandler());
        TestResponse response= (TestResponse) serverApp.executeRequest(request,
                new TestServerEntryPointContext());
        assertEquals("-handled", request.getTestWord());
    }

    @Test
    public void givenServerApp_whenExecutingARequest_thenTheRequestShouldBeInterceptedBeforeCallingTheHandler() throws Exception {
        serverApp.registerHandler(TestRequest.class.getCanonicalName(), new TestRequestHandler());
        serverApp.registerInterceptor(TestRequest.class.getCanonicalName(), TestServerEntryPointContext.class.getCanonicalName(), new TestInterceptor());
        TestResponse response= (TestResponse) serverApp.executeRequest(request, new TestServerEntryPointContext());
        assertEquals("-intercepted-entry-point-parameter-handled", request.getTestWord());
    }

    @Test
    public void givenServerApp_whenExecutingARequest_thenTheRequestShouldBeInterceptedByTheGlobalInterceptorsBeforeCallingTheHandler() throws Exception {
        serverApp.registerHandler(TestRequest.class.getCanonicalName(), new TestRequestHandler());
        serverApp.registerInterceptor(TestRequest.class.getCanonicalName(), TestServerEntryPointContext.class.getCanonicalName(), new TestInterceptor());
        serverApp.registerGlobalInterceptor(TestServerEntryPointContext.class.getCanonicalName(), new TestGlobalInterceptor());
        TestResponse response= (TestResponse) serverApp.executeRequest(request, new TestServerEntryPointContext());
        assertEquals("-intercepted-entry-point-parameter-globally-intercepted-entry-point-parameter-handled", request.getTestWord());
    }
}
