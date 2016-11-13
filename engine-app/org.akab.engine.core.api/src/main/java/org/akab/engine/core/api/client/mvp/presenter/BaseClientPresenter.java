package org.akab.engine.core.api.client.mvp.presenter;


import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.client.mvp.view.View;

public abstract class BaseClientPresenter<V extends View> implements ClientPresenter<V>{

    private final PresenterState INITIALIZED= () -> {};

    private final PresenterState NEW = ()-> {
        BaseClientPresenter.this.view=loadView();
        initView(BaseClientPresenter.this.view);
        state=INITIALIZED;
    };

    private PresenterState state;

    protected V view;

    public BaseClientPresenter() {
        this.state = NEW;
    }

    @Override
    public ClientPresenter<V> process() {
        state.process();
        return this;
    }

    private V loadView(){
        return (V) ClientApp.make().getViewsRepository().getView(getName()).getView();
    }

    private String getName(){
        return BaseClientPresenter.this.getClass().getCanonicalName();
    }
}
