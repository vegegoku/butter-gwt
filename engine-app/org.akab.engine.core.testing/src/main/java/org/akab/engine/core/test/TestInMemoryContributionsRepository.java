package org.akab.engine.core.test;

import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.ExtensionPoint;
import org.akab.engine.core.client.extensions.InMemoryContributionRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestInMemoryContributionsRepository extends InMemoryContributionRepository {

    protected final Map<String, Contribution> testContributions =new HashMap<>();

    @Override
    public void registerContribution(Class<? extends ExtensionPoint> extensionPoint, Contribution contribution) {
        super.registerContribution(extensionPoint, contribution);
        testContributions.put(contribution.getClass().getCanonicalName(), contribution);
    }

    @Override
    public Set<Contribution> findExtensionPointContributions(Class<? extends ExtensionPoint> extensionPoint) {
        return super.findExtensionPointContributions(extensionPoint);
    }

    public <C extends Contribution> C getContribution(Class<C> contributionClass) {
        return (C) testContributions.get(contributionClass.getCanonicalName());
    }
}
