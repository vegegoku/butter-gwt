package org.akab.engine.core.api.client.History;

import org.akab.engine.core.api.client.History.Token;

import java.util.Objects;

public class PathToken implements Token {

    public static final String PATH = "_path";
    public static final String PARAMETERS_SEPARATOR = "&";
    public static final String PARAMETER_VALUE_SEPARATOR = "=";

    private final StringBuilder token=new StringBuilder(PATH);

    private PathToken(String path) {
        if(isInvalidPart(path))
            throw new InvalidTokenPathException(path);
        this.token.append(PARAMETER_VALUE_SEPARATOR).append(path);
    }

    public String asTokenString() {
        return token.toString();
    }

    public Token appendParameter(String name, String value) {
        if(isInvalidPart(name) || isInvalidValue(value))
            throw new InvalidParameterNameOrValueException(name, value);
        this.token.append(PARAMETERS_SEPARATOR).append(name+PARAMETER_VALUE_SEPARATOR+value);
        return this;
    }

    private boolean isInvalidValue(String value) {
        return Objects.nonNull(value) && (containsSpecialWords(value));
    }

    private boolean isInvalidPart(String name) {
        return Objects.isNull(name) || name.trim().isEmpty() || containsSpecialWords(name);
    }

    private boolean containsSpecialWords(String part) {
        return part.contains(PARAMETERS_SEPARATOR) || part.contains(PARAMETER_VALUE_SEPARATOR) || part.contains(PATH);
    }

    public class InvalidTokenPathException extends RuntimeException {
        private static final long serialVersionUID = -4812121253102195040L;

        public InvalidTokenPathException(String path) {
            super(path);
        }
    }

    public class InvalidParameterNameOrValueException extends RuntimeException {
        private static final long serialVersionUID = 2707063232341325664L;

        public InvalidParameterNameOrValueException(String name, String value) {
            super("["+name+","+value+"]");
        }
    }

    public static Token make(String path){
        return new PathToken(path);
    }
}
