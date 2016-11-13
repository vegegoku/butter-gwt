package org.akab.engine.core.api.client.request;

import javax.validation.constraints.NotNull;

public class RequestHolder {

    @NotNull
    private final String requestName;
    @NotNull
    private final String presenterName;

    public RequestHolder(String requestName, String presenterName) {
        this.requestName = requestName;
        this.presenterName = presenterName;
    }

    public String getRequestName() {
        return requestName;
    }

    public String getPresenterName() {
        return presenterName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        if (!getRequestName().equals(((RequestHolder) other).getRequestName())) return false;
        return getPresenterName().equals(((RequestHolder) other).getPresenterName());
    }

    @Override
    public int hashCode() {
        int result = getRequestName().hashCode();
        result = 31 * result + getPresenterName().hashCode();
        return result;
    }
}
