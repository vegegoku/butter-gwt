package org.akab.engine.core.annotation.processor.client;

import com.google.gwt.user.client.ui.Widget;
import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.client.annotations.UiView;
import org.akab.engine.core.api.client.mvp.view.View;
import org.akab.engine.core.api.client.request.ClientRequest;

@Request
public class AnnotatedClassWithRequest extends ClientRequest<PresenterInterface> {

    @Override
    protected void process(PresenterInterface presenter) {

    }
}