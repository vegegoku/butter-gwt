package org.akab.engine.core.api.shared.request;

public class FailedServerResponse extends ServerResponse {

    private static final long serialVersionUID = 7146258885910449957L;

    private Throwable error;

    public FailedServerResponse() {
    }

    public FailedServerResponse(Throwable error) {
        this.error = error;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
