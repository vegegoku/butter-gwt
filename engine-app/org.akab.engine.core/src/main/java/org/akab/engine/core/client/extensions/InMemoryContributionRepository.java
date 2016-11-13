package org.akab.engine.core.client.extensions;

import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.client.extension.ContributionsRepository;
import org.akab.engine.core.api.shared.extension.ExtensionPoint;

import java.util.*;

public class InMemoryContributionRepository implements ContributionsRepository {

    private static final Map<String, Set<Contribution>> contributions = new HashMap<>();

    @Override
    public void registerContribution(Class<? extends ExtensionPoint> extensionPoint, Contribution contribution) {
        initializeExtensionPointContributions(extensionPoint);
        contributions.get(extensionPoint.getCanonicalName()).add(contribution);
    }

    @Override
    public Set<Contribution> findExtensionPointContributions(Class<? extends ExtensionPoint> extensionPoint) {
        initializeExtensionPointContributions(extensionPoint);
        return contributions.get(extensionPoint.getCanonicalName());
    }

    private void initializeExtensionPointContributions(Class<? extends ExtensionPoint> extensionPoint) {
        if (Objects.isNull(contributions.get(extensionPoint.getCanonicalName())))
            contributions.put(extensionPoint.getCanonicalName(), new HashSet<>());
    }
}
