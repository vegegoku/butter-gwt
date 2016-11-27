package org.akab.engine.core.api.client.mvp.view;


public interface ViewsRepository {

    void registerView(LazyViewLoader lazyViewLoader);

    View getView(String presenterName);

    void clear();

    class ViewCannotBeRegisteredMoreThanOnce extends RuntimeException {
        public ViewCannotBeRegisteredMoreThanOnce() {
        }

        public ViewCannotBeRegisteredMoreThanOnce(String message) {
            super(message);
        }
    }

    class ViewNotFoundException extends RuntimeException {
        public ViewNotFoundException() {
        }

        public ViewNotFoundException(String message) {
            super(message);
        }
    }

}
