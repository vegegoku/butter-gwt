package com.progressoft.security.logout.client.views;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.progressoft.security.logout.client.presenters.LogoutHandler;
import com.progressoft.security.logout.client.presenters.LogoutPresenter;
import org.akab.engine.core.api.client.annotations.UiView;

import java.util.HashMap;
import java.util.Map;

@UiView(presentable = LogoutPresenter.class)
public class DefaultCompositeLogoutView implements CompositeLogoutView {

    private final Map<String, LogoutView> logoutViews=new HashMap<>();

    public DefaultCompositeLogoutView() {
        logoutViews.put(LogoutView.MENU, new MenuLogoutView());
        logoutViews.put(LogoutView.HEADER, new HeaderLogoutView());
    }

    @Override
    public LogoutView getView(String classifier) {
        if(logoutViews.containsKey(classifier))
            return logoutViews.get(classifier);
        throw new ViewNotFoundInCompositeView(classifier);
    }

    @Override
    public Widget asWidget() {
        return getView(defaultViewClassifier()).asWidget();
    }

    @Override
    public String defaultViewClassifier() {
        return LogoutView.MENU;
    }

    @Override
    public void onLoggedOut() {
        Window.Location.reload();
    }

    @Override
    public void setLogoutHandler(LogoutHandler logoutHandler) {
        logoutViews.values().forEach(v->v.setLogoutHandler(logoutHandler));
    }
}
