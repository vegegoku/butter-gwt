package org.akab.engine.core.api.client.annotations;

import org.akab.engine.core.api.client.History.RequestFromPath;
import org.akab.engine.core.api.client.History.TokenizedPath;
import org.akab.engine.core.api.client.request.Request;

import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Path {

    @NotNull
    String path();

    Class<? extends RequestFromPath> mapper() default DefaultPathToRequestMapper.class;

    final class DefaultPathToRequestMapper implements RequestFromPath {

        public Request buildRequest(TokenizedPath path) {
            return null;
        }
    }
}
