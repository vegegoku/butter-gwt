package org.akab.engine.app.test;

import org.akab.engine.core.api.client.mvp.view.LazyViewLoader;
import org.akab.engine.core.api.client.mvp.view.View;
import org.akab.engine.core.client.mvp.view.InMemoryViewRepository;

import java.util.HashMap;
import java.util.Objects;

public class TestInMemoryViewRepository extends InMemoryViewRepository{

    private final HashMap<String, LazyViewLoader> replacedViews=new HashMap<>();


    @Override
    public View getView(String presenterName) {
        if(replacedViews.containsKey(presenterName))
            return replacedViews.get(presenterName).getView();
        return super.getView(presenterName);
    }

    public void replaceView(String presenterName, TestViewFactory viewFactory){
        replacedViews.put(presenterName, new TestViewLoader(getViewLoader(presenterName), viewFactory));
    }

    @Override
    public void clear() {
        super.clear();
        replacedViews.clear();
    }

    private class TestViewLoader extends LazyViewLoader {

        private final LazyViewLoader lazyViewLoader;
        private final TestViewFactory viewFactory;
        private View view;

        public TestViewLoader(LazyViewLoader lazyViewLoader, TestViewFactory viewFactory) {
            super(lazyViewLoader.getPresenterName());
            this.lazyViewLoader=lazyViewLoader;
            this.viewFactory = viewFactory;
        }

        @Override
        public String getPresenterName() {
            return lazyViewLoader.getPresenterName();
        }

        @Override
        public View getView() {
            if(Objects.isNull(view))
                view=viewFactory.make();
            return view;
        }

        @Override
        protected View make() {
            return getView();
        }
    }

}
