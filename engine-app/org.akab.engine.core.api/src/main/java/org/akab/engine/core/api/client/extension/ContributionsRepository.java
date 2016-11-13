package org.akab.engine.core.api.client.extension;

import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.ExtensionPoint;

import java.util.Set;

public interface ContributionsRepository {

    void registerContribution(Class<? extends ExtensionPoint> extensionPoint, Contribution contribution);
    Set<Contribution> findExtensionPointContributions(Class<? extends ExtensionPoint> extensionPoint);
}
