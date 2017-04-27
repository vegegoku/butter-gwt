package org.akab.engine.core.client.extensions;

import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.client.extension.ContributionsRepository;
import org.akab.engine.core.api.shared.extension.ExtensionPoint;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryContributionRepository implements ContributionsRepository {

    private final Map<String, Set<ContributionWrapper>> contributions = new HashMap<>();

    @Override
    public void registerContribution(Class<? extends ExtensionPoint> extensionPoint, Contribution contribution) {
        initializeExtensionPointContributions(extensionPoint);
        contributions.get(extensionPoint.getCanonicalName()).add(new ContributionWrapper(contribution));
    }

    @Override
    public Set<Contribution> findExtensionPointContributions(Class<? extends ExtensionPoint> extensionPoint) {
        initializeExtensionPointContributions(extensionPoint);
        return contributions.get(extensionPoint.getCanonicalName()).stream().map(cw-> cw.contribution).collect(
                Collectors.toSet());
    }

    private void initializeExtensionPointContributions(Class<? extends ExtensionPoint> extensionPoint) {
        if (Objects.isNull(contributions.get(extensionPoint.getCanonicalName())))
            contributions.put(extensionPoint.getCanonicalName(), new HashSet<>());
    }

    private class ContributionWrapper{

        private final Contribution contribution;

        public ContributionWrapper(Contribution contribution) {
            this.contribution = contribution;
        }

        @Override
        public boolean equals(Object other) {
            return contribution.getClass().getCanonicalName().equals(((ContributionWrapper)other).contribution.getClass().getCanonicalName());
        }

        @Override
        public int hashCode() {
            return contribution.getClass().getCanonicalName().hashCode();
        }
    }


}
