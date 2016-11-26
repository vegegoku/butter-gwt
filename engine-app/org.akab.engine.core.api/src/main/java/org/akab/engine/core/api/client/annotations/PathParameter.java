package org.akab.engine.core.api.client.annotations;

import org.akab.engine.core.api.client.History.ParameterConverter;

import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PathParameter {

    @NotNull
    String name() default "";

    @NotNull
    Class<? extends ParameterConverter> converter() default DefaultParameterConverter.class;

    final class DefaultParameterConverter implements ParameterConverter {
        public Object convert(String value) {
            return value;
        }
    }
}
