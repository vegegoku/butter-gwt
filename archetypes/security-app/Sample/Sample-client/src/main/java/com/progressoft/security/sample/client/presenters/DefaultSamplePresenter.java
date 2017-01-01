package com.progressoft.security.sample.client.presenters;

import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;
import com.progressoft.security.sample.client.views.SampleView;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@Presenter
public class DefaultSamplePresenter extends BaseClientPresenter<SampleView> implements SamplePresenter {

    @Override
    public void initView(SampleView view) {

    }

    @Override
    public void contributeToMainModule(MainExtensionPoint mainExtensionPoint, String welcomeMessage) {
        view.setWelcomeMessage(welcomeMessage);
        mainExtensionPoint.context().appendWidgetToRoot(view);
    }
}