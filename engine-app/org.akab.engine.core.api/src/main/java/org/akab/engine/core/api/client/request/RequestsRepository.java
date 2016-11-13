package org.akab.engine.core.api.client.request;

public interface RequestsRepository {

    void registerRequest(RequestHolder requestHolder);
    RequestHolder findRequestPresenterWrapper(String requestKey);
    void clear();

    class RequestCannotBeRegisteredMoreThanOnce extends RuntimeException {
        private static final long serialVersionUID = -4925004944311728702L;

        public RequestCannotBeRegisteredMoreThanOnce() {
        }

        public RequestCannotBeRegisteredMoreThanOnce(String message) {
            super(message);
        }
    }
    class RequestKeyNotFoundException extends RuntimeException {
        private static final long serialVersionUID = -6084468443200600274L;

        public RequestKeyNotFoundException() {
        }

        public RequestKeyNotFoundException(String message) {
            super(message);
        }
    }
}
