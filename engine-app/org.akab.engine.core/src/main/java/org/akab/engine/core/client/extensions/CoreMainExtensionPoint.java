package org.akab.engine.core.client.extensions;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public class CoreMainExtensionPoint implements MainExtensionPoint {
    @Override
    public org.akab.engine.core.api.shared.extension.MainContext context() {
        return new CoreMainContext() {
            @Override
            public void appendToRootElement(Element e) {
                Document.get().getBody().appendChild(e);
            }
        };
    }
}
