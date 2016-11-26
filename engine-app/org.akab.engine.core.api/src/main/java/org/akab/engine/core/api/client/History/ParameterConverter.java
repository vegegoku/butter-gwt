package org.akab.engine.core.api.client.History;

public interface ParameterConverter<P> {

    P convert(String parameterValue);
}
