package org.akab.engine.core.api.client.history;

import org.akab.engine.core.api.client.History.PathToken;
import org.akab.engine.core.api.client.History.Token;
import org.junit.Before;
import org.junit.Test;

import static org.akab.engine.core.api.client.History.PathToken.PARAMETERS_SEPARATOR;
import static org.akab.engine.core.api.client.History.PathToken.PARAMETER_VALUE_SEPARATOR;
import static org.akab.engine.core.api.client.History.PathToken.PATH;
import static org.junit.Assert.*;

public class HistoryTokenTest {

    private Token token;

    @Before
    public void setUp() throws Exception {
        this.token= PathToken.make("somePath");
    }

    @Test(expected = PathToken.InvalidTokenPathException.class)
    public void creatingHistoryTokenWithNullPath_shouldThrowExceptionT() throws Exception {
        PathToken.make(null);
    }

    @Test(expected = PathToken.InvalidTokenPathException.class)
    public void creatingHistoryTokenWithEmptyPath_shouldThrowExceptionT() throws Exception {
        PathToken.make("");
    }

    @Test(expected = PathToken.InvalidTokenPathException.class)
    public void creatingHistoryTokenWithSpacesOnlyPath_shouldThrowExceptionT() throws Exception {
        PathToken.make("   ");
    }

    @Test(expected = PathToken.InvalidTokenPathException.class)
    public void creatingHistoryTokenWithPathContainingParametersSeparator_shouldThrowException() throws Exception {
        PathToken.make("pathPart"+PARAMETERS_SEPARATOR+"someOtherpart");
    }

    @Test(expected = PathToken.InvalidTokenPathException.class)
    public void creatingHistoryTokenWithPathContainingParameterValueSeparator_shouldThrowException() throws Exception {
        PathToken.make("pathPart"+PARAMETER_VALUE_SEPARATOR+"someOtherpart");
    }

    @Test
    public void creatingHistoryTokenForSpecificPath_shouldMapToPathWithNameStringValue() throws Exception {
       assertEquals("_path=somePath",token.asTokenString());
    }

    @Test(expected = PathToken.InvalidParameterNameOrValueException.class)
    public void havingHistoryToken_appendingParameterWithNullName_shouldThrowException() throws Exception {
        token.appendParameter(null, "value1");
    }

    @Test(expected = PathToken.InvalidParameterNameOrValueException.class)
    public void havingHistoryToken_appendingParameterWithEmptyName_shouldThrowException() throws Exception {
        token.appendParameter("", "value1");
    }

    @Test(expected = PathToken.InvalidParameterNameOrValueException.class)
    public void havingHistoryToken_appendingParameterWithOnlySpacesName_shouldThrowException() throws Exception {
        token.appendParameter("   ", "value1");
    }

    @Test(expected = PathToken.InvalidParameterNameOrValueException.class)
    public void havingHistoryToken_appendingParameterWithNameContainingParametersSeparator_shouldThrowException() throws Exception {
        token.appendParameter("parameter"+PARAMETERS_SEPARATOR, "value1");
    }

    @Test(expected = PathToken.InvalidParameterNameOrValueException.class)
    public void havingHistoryToken_appendingParameterWithNameContainingParameterValueSeparator_shouldThrowException() throws Exception {
        token.appendParameter("parameter"+PARAMETER_VALUE_SEPARATOR, "value1");
    }

    @Test(expected = PathToken.InvalidParameterNameOrValueException.class)
    public void havingHistoryToken_appendingParameterWithNameContainingPathKeywordValueSeparator_shouldThrowException() throws Exception {
        token.appendParameter("parameter"+PATH, "value1");
    }

    @Test(expected = PathToken.InvalidParameterNameOrValueException.class)
    public void havingHistoryToken_appendingParameterWithValueContainingParametersSeparator_shouldThrowException() throws Exception {
        token.appendParameter("parameter", "value1"+PARAMETERS_SEPARATOR);
    }

    @Test(expected = PathToken.InvalidParameterNameOrValueException.class)
    public void havingHistoryToken_appendingParameterWithValueContainingParameterValueSeparator_shouldThrowException() throws Exception {
        token.appendParameter("parameter", "value1"+PARAMETER_VALUE_SEPARATOR);
    }

    @Test(expected = PathToken.InvalidParameterNameOrValueException.class)
    public void havingHistoryToken_appendingParameterWithValueContainingPathKeyWord_shouldThrowException() throws Exception {
        token.appendParameter("parameter", "value1"+PATH);
    }

    @Test
    public void havingHistoryTokenWithSpecificPath_appendingParameterToThatToken_shouldResultInConcatenatedPathAndParameters() throws Exception {
        assertEquals("_path=somePath&parameter1=value1", token.appendParameter("parameter1", "value1").asTokenString());
    }
}
