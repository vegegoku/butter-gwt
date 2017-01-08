package org.akab.engine.core.api.client.history;

@FunctionalInterface
public interface ParameterConverter<P> {
    P convert(String value);
}
