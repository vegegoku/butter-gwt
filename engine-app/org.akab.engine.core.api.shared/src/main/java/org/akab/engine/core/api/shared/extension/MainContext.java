package org.akab.engine.core.api.shared.extension;

import com.google.gwt.dom.client.Element;

public interface MainContext extends Context{
    void appendToRootElement(Element e);
}