package com.progressoft.security.dashboard.client.views;

import com.google.gwt.user.client.ui.Widget;
import com.progressoft.security.dashboard.client.presenters.DashboardPresenter;
import org.akab.engine.core.api.client.annotations.UiView;

import java.util.HashMap;
import java.util.Map;

@UiView(presentable = DashboardPresenter.class)
public class DefaultDashboardCompositeView implements CompositeDashboardView {

    private Map<String, DashboardView> views = new HashMap<>();

    public DefaultDashboardCompositeView() {
        views.put(CompositeDashboardView.TRIGGER, new TriggerDashboardView());
        views.put(CompositeDashboardView.CONTENT, new ContentDashboardView());
    }

    @Override
    public DashboardView getView(String classifier) {
        if (views.containsKey(classifier))
            return views.get(classifier);
        throw new ViewNotFoundInCompositeView(classifier);
    }

    @Override
    public String defaultViewClassifier() {
        return CompositeDashboardView.CONTENT;
    }

    @Override
    public Widget asWidget() {
        return getView(CompositeDashboardView.CONTENT).asWidget();
    }
}
