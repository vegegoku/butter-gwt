package org.akab.engine.app.test;

import org.akab.engine.core.api.client.mvp.presenter.LazyPresenterLoader;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.client.mvp.presenter.InMemoryPresentersRepository;

import java.util.HashMap;


public class TestInMemoryPresenterRepository extends InMemoryPresentersRepository {

    private final HashMap<String, LazyPresenterLoader> replacedPresenters=new HashMap<>();

    @Override
    public void clear() {
        super.clear();
        replacedPresenters.clear();
    }

    @Override
    public Presentable getPresenter(String presenterName) {
        if(replacedPresenters.containsKey(presenterName))
            return replacedPresenters.get(presenterName).getPresenter();
        return super.getPresenter(presenterName);
    }

    public void replacePresenter(String presenterName, Presentable presenter){
        replacedPresenters.put(presenterName, new TestPresenterLoader(super.getPresenterLoader(presenterName), presenter));
    }

    private class TestPresenterLoader extends LazyPresenterLoader{

        private final LazyPresenterLoader lazyPresenterLoader;
        private final Presentable presenter;

        public TestPresenterLoader(LazyPresenterLoader lazyPresenterLoader, Presentable presenter) {
            super(lazyPresenterLoader.getName(), lazyPresenterLoader.getConcreteName());
            this.lazyPresenterLoader=lazyPresenterLoader;
            this.presenter = presenter;
        }

        @Override
        public String getName() {
            return lazyPresenterLoader.getName();
        }

        @Override
        public String getConcreteName() {
            return lazyPresenterLoader.getConcreteName();
        }

        @Override
        public Presentable getPresenter() {
            return presenter;
        }

        @Override
        protected Presentable make() {
            return presenter;
        }
    }
}
