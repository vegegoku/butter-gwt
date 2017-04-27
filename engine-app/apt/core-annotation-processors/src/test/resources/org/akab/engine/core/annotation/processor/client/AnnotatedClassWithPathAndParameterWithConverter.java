package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.annotations.Path;
import org.akab.engine.core.api.client.annotations.PathParameter;
import org.akab.engine.core.api.client.request.ClientRequest;

import java.math.BigDecimal;

@Path(path = "somePath")
public class AnnotatedClassWithPathAndParameterWithConverter extends ClientRequest<PresenterInterface> {

    private BigDecimal value;

    public AnnotatedClassWithPathAndParameterWithConverter(@PathParameter(converter = BigDecimalConverter.class) BigDecimal value) {
        this.value = value;
    }

    @Override
    protected void process(PresenterInterface presenter) {

    }
}