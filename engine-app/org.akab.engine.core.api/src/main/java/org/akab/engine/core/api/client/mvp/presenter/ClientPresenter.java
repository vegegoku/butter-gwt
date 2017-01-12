package org.akab.engine.core.api.client.mvp.presenter;

import org.akab.engine.core.api.client.mvp.view.View;

public interface ClientPresenter<V extends View>{
    default void initView(V view){}
    ClientPresenter<V> process();
}
