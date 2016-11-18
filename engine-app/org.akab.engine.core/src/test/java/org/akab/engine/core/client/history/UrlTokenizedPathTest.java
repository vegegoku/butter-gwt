package org.akab.engine.core.client.history;

import org.akab.engine.core.api.client.History.TokenizedPath;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class UrlTokenizedPathTest {

    public static final String SOME_PATH = "somePath";
    private TokenizedPath tokenizedPath;

    @Before
    public void setUp() throws Exception {
        tokenizedPath =new UrlTokenizedPath(SOME_PATH);
    }

    @Test
    public void createdUrlTokenizedPath_shouldHaveAPath() throws Exception {
        assertEquals(SOME_PATH, tokenizedPath.path());
    }

    @Test
    public void createdUrlTokenizedPath_shouldHaveReturnItsPath() throws Exception {
        assertEquals(SOME_PATH, tokenizedPath.path());
    }

    @Test
    public void createdUrlTokenizedPathWithoutParameters_shouldNotContainAnyParameters() throws Exception {
        assertFalse(tokenizedPath.containsParameter("someParameter"));
        assertNull(tokenizedPath.getParameter("someParameter"));
    }

    @Test
    public void createdUrlTokenizedPathWithParameters_shouldProvideItsParameters() throws Exception {
        TokenizedPath tokenizedPath=new UrlTokenizedPath(SOME_PATH, new HashMap<String, String>(){
            private static final long serialVersionUID = -2238998960564497658L;

            {
            put("parameter1","value1");
            put("parameter2", "value2");
        }});
        assertTrue(tokenizedPath.containsParameter("parameter1"));
        assertEquals("value1", tokenizedPath.getParameter("parameter1"));
        assertEquals("value2", tokenizedPath.getParameter("parameter2"));
        assertFalse(tokenizedPath.containsParameter("parameter3"));
        assertNull(tokenizedPath.getParameter("parameter3"));
    }



}
