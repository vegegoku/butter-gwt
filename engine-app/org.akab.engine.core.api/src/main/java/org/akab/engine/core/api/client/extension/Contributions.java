package org.akab.engine.core.api.client.extension;

import org.akab.engine.core.api.client.ClientApp;
import org.akab.engine.core.api.shared.extension.ExtensionPoint;

public class Contributions {

    public static void apply(Class<? extends ExtensionPoint> extensionPointInterface, ExtensionPoint extensionPoint){
        ClientApp.make().applyContributions(extensionPointInterface, extensionPoint);
    }
}
