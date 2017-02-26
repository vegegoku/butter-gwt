package com.progressoft.security.userinfo.client.views;

import com.google.gwt.user.client.ui.Widget;
import com.progressoft.security.userinfo.client.presenters.UserInfoPresenter;
import org.akab.engine.core.api.client.annotations.UiView;

import java.util.HashMap;
import java.util.Map;

@UiView(presentable = UserInfoPresenter.class)
public class DefaultCompositeUserInfoView implements UserInfoCompositeView {

    private final Map<String, UserInfoView> views=new HashMap<>();

    public DefaultCompositeUserInfoView() {
        views.put(UserInfoView.HEADER, new HeaderUserInfoView());
        views.put(UserInfoView.MENU, new MenuUserInfoView());
    }

    @Override
    public UserInfoView getView(String classifier) {
        if(views.containsKey(classifier))
            return views.get(classifier);
        throw new ViewNotFoundInCompositeView(classifier);
    }

    @Override
    public String defaultViewClassifier() {
        return UserInfoView.MENU;
    }

    @Override
    public Widget asWidget() {
        return getView(defaultViewClassifier()).asWidget();
    }

    @Override
    public void setDisplayName(String userDisplayName) {
        views.values().forEach(v->v.setDisplayName(userDisplayName));
    }
}
