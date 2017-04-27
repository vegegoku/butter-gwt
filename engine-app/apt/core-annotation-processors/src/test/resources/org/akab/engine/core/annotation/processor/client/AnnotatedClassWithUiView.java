package org.akab.engine.core.annotation.processor.client;

import com.google.gwt.user.client.ui.Widget;
import org.akab.engine.core.api.client.annotations.UiView;
import org.akab.engine.core.api.client.mvp.view.View;

@UiView(presentable = PresenterInterface.class)
public class AnnotatedClassWithUiView implements View {

    @Override
    public Widget asWidget() {
        return null;
    }
}