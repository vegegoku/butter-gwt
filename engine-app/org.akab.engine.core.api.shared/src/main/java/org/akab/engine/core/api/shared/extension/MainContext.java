package org.akab.engine.core.api.shared.extension;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.IsWidget;

public interface MainContext extends Context{
    void appendElementToRoot(Element e);
    void appendWidgetToRoot(IsWidget w);
    void removeElement(Element e);
    void removeWidget(IsWidget w);
}