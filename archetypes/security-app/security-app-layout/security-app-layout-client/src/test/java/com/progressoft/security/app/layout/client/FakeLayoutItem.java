package com.progressoft.security.app.layout.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.progressoft.security.app.layout.shared.extension.LayoutItem;

public class FakeLayoutItem implements LayoutItem {

    private Button button=new Button();

    @Override
    public Widget asWidget() {
        return button;
    }
}
