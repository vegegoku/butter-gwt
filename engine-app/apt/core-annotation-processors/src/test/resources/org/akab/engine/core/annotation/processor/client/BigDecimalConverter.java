package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.history.ParameterConverter;

import java.math.BigDecimal;

public class BigDecimalConverter implements ParameterConverter<BigDecimal> {

    @Override
    public BigDecimal convert(String value) {
        return new BigDecimal(value);
    }
}