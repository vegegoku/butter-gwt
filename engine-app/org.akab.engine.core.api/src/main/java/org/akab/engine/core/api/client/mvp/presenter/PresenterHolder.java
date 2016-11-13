package org.akab.engine.core.api.client.mvp.presenter;

import javax.validation.constraints.NotNull;

public class PresenterHolder {

    @NotNull
    private final String name;
    @NotNull
    private final ClientPresenter presenter;

    public PresenterHolder(String name, ClientPresenter presenter) {
        this.name = name;
        this.presenter = presenter;
    }

    public String getName() {
        return name;
    }

    public ClientPresenter getPresenter() {
        return presenter.process();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        return name.equals(((PresenterHolder) other).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
