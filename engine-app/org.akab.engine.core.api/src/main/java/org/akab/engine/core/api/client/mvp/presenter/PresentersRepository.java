package org.akab.engine.core.api.client.mvp.presenter;

public interface PresentersRepository {

    void registerPresenter(PresenterHolder presenterHolder);

    PresenterHolder getPresenter(String presenterName);

    void clear();

    class PresenterCannotBeRegisteredMoreThanOnce extends RuntimeException {
        public PresenterCannotBeRegisteredMoreThanOnce() {
        }

        public PresenterCannotBeRegisteredMoreThanOnce(String message) {
            super(message);
        }
    }

    class PresenterNotFoundException extends RuntimeException {
        public PresenterNotFoundException() {
        }

        public PresenterNotFoundException(String message) {
            super(message);
        }
    }
}
