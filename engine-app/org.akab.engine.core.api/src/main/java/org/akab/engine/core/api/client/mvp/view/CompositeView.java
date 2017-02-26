package org.akab.engine.core.api.client.mvp.view;


public interface CompositeView<V extends View> extends View {
    V getView(String classifier);
    String defaultViewClassifier();

    class ViewNotFoundInCompositeView extends RuntimeException{
        public ViewNotFoundInCompositeView(String message) {
            super(message);
        }
    }
}
