package org.akab.engine.core.api.client.mvp.presenter;

public interface PresentersRepository {

    void registerPresenter(PresenterHolder presenterHolder);

    PresenterHolder getPresenter(String presenterName);

    void clear();

    class PresenterCannotBeRegisteredMoreThanOnce extends RuntimeException {
        private static final long serialVersionUID = 956087089156886416L;

        public PresenterCannotBeRegisteredMoreThanOnce() {
        }

        public PresenterCannotBeRegisteredMoreThanOnce(String message) {
            super(message);
        }
    }

    class PresenterNotFoundException extends RuntimeException {
        private static final long serialVersionUID = -6455103815754837305L;

        public PresenterNotFoundException() {
        }

        public PresenterNotFoundException(String message) {
            super(message);
        }
    }
}
