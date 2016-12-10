package org.akab.engine.core.api.shared.server;

import org.akab.engine.core.api.shared.request.ServerRequest;

import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Handler {

    @NotNull
    Class<? extends ServerRequest> request();
}
