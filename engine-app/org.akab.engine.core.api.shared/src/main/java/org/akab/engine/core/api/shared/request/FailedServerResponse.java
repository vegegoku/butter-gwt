package org.akab.engine.core.api.shared.request;

public class FailedResponse extends Response {

    private static final long serialVersionUID = 7146258885910449957L;

    private Throwable error;

    public FailedResponse() {
    }

    public FailedResponse(Throwable error) {
        this.error = error;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
