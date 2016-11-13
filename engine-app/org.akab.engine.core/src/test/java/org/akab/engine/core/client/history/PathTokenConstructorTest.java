package org.akab.engine.core.client.history;

import org.akab.engine.core.api.client.History.PathToken;
import org.akab.engine.core.api.client.History.TokenConstruct;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PathTokenConstructorTest {

    private TokenConstruct constructor;

    @Before
    public void setUp() throws Exception {
        constructor = new PathTokenConstructor();
    }

    @Test
    public void givenNewlyCreatedConstructor_thenGeneratedUrlShouldBeEmpty() throws Exception {
        assertEquals("", constructor.toUrl());
    }

    @Test
    public void givenHistoryConstructor_appendingNoParametersHistoryToken_then_generatedUrlShouldContainAppendedTokenPath()
            throws Exception {
        assertEquals("_path=somePath", constructor.append(PathToken.make("somePath")).toUrl());
    }

    @Test
    public void givenHistoryConstructor_appendingTowNoParametersHistoryToken_then_generatedUrlShouldContainBothAppendedTokenPathsSeparatedbyAndSymbol()
            throws Exception {
        assertEquals("_path=somePath&_path=anotherPath",
                constructor.append(PathToken.make("somePath")).append(PathToken.make("anotherPath")).toUrl());
    }

    @Test
    public void givenHistoryConstructor_appendingHistoryTokenWithParameters_then_generatedUrlShouldContainHistoryTokenString()
            throws Exception {
        assertEquals("_path=somePath&parameter=value",
                constructor.append(PathToken.make("somePath").appendParameter("parameter", "value")).toUrl());
    }

    @Test
    public void givenHistoryConstructor_appendingHistoryTokenWithManyParameters_then_generatedUrlShouldContainHistoryTokenString()
            throws Exception {
        assertEquals("_path=somePath&parameter=value&parameter2=value2", constructor
                .append(PathToken.make("somePath").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2")).toUrl());
    }

    @Test
    public void givenHistoryConstructor_appendingHistoryTokensWithManyParameters_then_generatedUrlShouldContainAllHistoryTokensString()
            throws Exception {
        assertEquals("_path=somePath&parameter=value&parameter2=value2&_path=somePath2&parameter=value&parameter2=value2", constructor

                .append(PathToken.make("somePath").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2"))

                .append(PathToken.make("somePath2").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2")).toUrl());
    }

    @Test
    public void givenHistoryConstructor_replacingAllTokensWithAnotherToken_then_generatedUrlShouldContainOnlyTheReplacerTokenStringOnly()
            throws Exception {
        assertEquals("_path=somePath3", constructor

                .append(PathToken.make("somePath").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2"))

                .append(PathToken.make("somePath2").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2")).replaceAll(PathToken.make("somePath3")).toUrl());

        assertEquals("_path=somePath3&parameter=value", constructor

                .append(PathToken.make("somePath").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2"))

                .append(PathToken.make("somePath2").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2")).replaceAll(PathToken.make("somePath3").appendParameter("parameter", "value")).toUrl());
    }

    @Test
    public void givenHistoryConstructor_replacingLastTokenWithAnotherToken_then_generatedUrlShouldContainTheOldTokensExceptTheLastOneConcatenatedWithTheNewOne()
            throws Exception {
        assertEquals("_path=somePath&parameter=value&parameter2=value2&_path=somePath3", constructor

                .append(PathToken.make("somePath").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2"))

                .append(PathToken.make("somePath2").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2")).replaceLast(PathToken.make("somePath3")).toUrl());
        constructor.clear();

        assertEquals("_path=somePath&parameter=value&parameter2=value2&_path=somePath3&parameter=value", constructor

                .append(PathToken.make("somePath").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2"))

                .append(PathToken.make("somePath2").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2")).replaceLast(PathToken.make("somePath3").appendParameter("parameter", "value")).toUrl());
    }

    @Test
    public void givenHistoryConstructor_clearingTheConstructor_then_generatedUrlShouldBeEmpty()
            throws Exception {
        assertEquals("", constructor

                .append(PathToken.make("somePath").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2"))

                .append(PathToken.make("somePath2").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2")).clear().toUrl());

    }

    @Test
    public void givenHistoryConstructor_clearingTheToken_then_generatedUrlShouldAllTokenExcepTheLastOne()
            throws Exception {
        assertEquals("_path=somePath&parameter=value&parameter2=value2", constructor

                .append(PathToken.make("somePath").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2"))

                .append(PathToken.make("somePath2").appendParameter("parameter", "value")
                        .appendParameter("parameter2", "value2")).clearLast().toUrl());

    }


}
