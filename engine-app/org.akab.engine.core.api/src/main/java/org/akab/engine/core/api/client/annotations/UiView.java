package org.akab.engine.core.api.client.annotations;

import org.akab.engine.core.api.client.mvp.presenter.ClientPresenter;

import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UiView {
    @NotNull
    Class<? extends ClientPresenter> presenter();
}
