package org.akab.engine.app.test;

import org.akab.engine.core.api.client.ClientApp;
import org.junit.After;
import org.junit.Before;

public class SampleTest {

    protected ClientApp clientApp;

    @Before
    public void setUp() throws Exception {
        System.out.println("Parent Setup is being called ...");
        clientApp=TestClientAppFactory.make(new TestEntryPointContext());
    }

    @After
    public void tearDown() throws Exception {
        clientApp=null;
    }
}
