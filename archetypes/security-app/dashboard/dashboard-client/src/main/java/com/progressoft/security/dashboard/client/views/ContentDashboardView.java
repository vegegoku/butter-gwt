package com.progressoft.security.dashboard.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.progressoft.security.dashboard.client.presenters.DashboardPresenter;
import gwt.material.design.client.ui.MaterialLink;
import org.akab.engine.core.api.client.annotations.UiView;

@UiView(presentable = DashboardPresenter.class)
public class ContentDashboardView extends Composite implements DashboardView{

    interface DefaultDashboardViewUiBinder extends UiBinder<MaterialLink, ContentDashboardView> {
    }

    private static DefaultDashboardViewUiBinder ourUiBinder = GWT.create(DefaultDashboardViewUiBinder.class);

    public ContentDashboardView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }


}