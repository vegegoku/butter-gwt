package org.akab.engine.core.api.shared.extension;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public interface MainContext extends Context{
    void appendElementToRoot(Element e);
    void appendWidgetToRoot(IsWidget w);
}