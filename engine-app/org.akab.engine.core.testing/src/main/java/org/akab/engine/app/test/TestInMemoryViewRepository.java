package org.akab.engine.app.test;

import org.akab.engine.core.api.client.mvp.presenter.LazyPresenterLoader;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.client.mvp.view.LazyViewLoader;
import org.akab.engine.core.api.client.mvp.view.View;
import org.akab.engine.core.api.client.mvp.view.ViewsRepository;
import org.akab.engine.core.client.mvp.view.InMemoryViewRepository;

import java.util.HashMap;

public class TestInMemoryViewRepository extends InMemoryViewRepository{

    private final HashMap<String, LazyViewLoader> replacedViews=new HashMap<>();


    @Override
    public View getView(String presenterName) {
        if(replacedViews.containsKey(presenterName))
            return replacedViews.get(presenterName).getView();
        return super.getView(presenterName);
    }

    public void replaceView(String presenterName, View view){
        replacedViews.put(presenterName, new TestViewLoader(getViewLoader(presenterName), view));
    }

    @Override
    public void clear() {
        super.clear();
        replacedViews.clear();
    }

    private class TestViewLoader extends LazyViewLoader {

        private final LazyViewLoader lazyViewLoader;
        private final View view;

        public TestViewLoader(LazyViewLoader lazyViewLoader, View view) {
            super(lazyViewLoader.getPresenterName());
            this.lazyViewLoader=lazyViewLoader;
            this.view = view;
        }

        @Override
        public String getPresenterName() {
            return lazyViewLoader.getPresenterName();
        }

        @Override
        public View getView() {
            return view;
        }

        @Override
        protected View make() {
            return view;
        }
    }

}
