package com.progressoft.security.userinfo.client;

import com.google.gwt.user.client.ui.Widget;
import com.progressoft.security.userinfo.client.views.UserInfoCompositeView;
import com.progressoft.security.userinfo.client.views.UserInfoView;

import java.util.HashMap;
import java.util.Map;

public class UserInfoViewSpy implements UserInfoCompositeView {

    private final Map<String, UserInfoView> views=new HashMap<>();

    public UserInfoViewSpy() {
        views.put(UserInfoView.HEADER, new UserInfoView() {
            @Override
            public void setDisplayName(String userDisplayName) {

            }

            @Override
            public Widget asWidget() {
                return null;
            }
        });
        views.put(UserInfoView.MENU, new UserInfoView() {
            @Override
            public void setDisplayName(String userDisplayName) {

            }

            @Override
            public Widget asWidget() {
                return null;
            }
        });
    }

    @Override
    public void setDisplayName(String userDisplayName) {
        views.values().forEach(v->v.setDisplayName(userDisplayName));
    }

    @Override
    public UserInfoView getView(String classifier) {
        return views.get(classifier);
    }

    @Override
    public String defaultViewClassifier() {
        return UserInfoView.MENU;
    }

    @Override
    public Widget asWidget() {
        return views.get(defaultViewClassifier()).asWidget();
    }
}
