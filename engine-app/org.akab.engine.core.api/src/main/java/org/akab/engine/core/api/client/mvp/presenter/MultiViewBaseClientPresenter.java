package org.akab.engine.core.api.client.mvp.presenter;

import org.akab.engine.core.api.client.mvp.view.CompositeView;
import org.akab.engine.core.api.client.mvp.view.View;

public class MultiViewBaseClientPresenter<V extends CompositeView<? extends View>> extends BaseClientPresenter<V> {
}
