package org.akab.engine.app.test;

import org.junit.Before;
import org.junit.Test;

public class ChildTest extends SampleTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        System.out.println("Child setup is being called");
    }

    @Test
    public void nothing() throws Exception {


    }
}
