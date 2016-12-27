package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.History.ParameterConverter;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;

import java.math.BigDecimal;

public class BigDecimalConverter implements ParameterConverter<BigDecimal> {

    @Override
    public BigDecimal convert(String value) {
        return new BigDecimal(value);
    }
}