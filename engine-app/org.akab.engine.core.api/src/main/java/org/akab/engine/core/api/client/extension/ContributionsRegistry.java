package org.akab.engine.core.api.client.extension;

import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.ExtensionPoint;

public interface ContributionsRegistry {
    void registerContribution(Class<? extends ExtensionPoint> extensionPoint, Contribution contribution);
}
