package org.akab.engine.app.test;

import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.ExtensionPoint;
import org.akab.engine.core.client.extensions.InMemoryContributionRepository;

import java.util.HashMap;
import java.util.Map;

public class TestInMemoryContributionsRepository extends InMemoryContributionRepository {

    protected static final Map<String, Contribution> testContributions =new HashMap<>();

    @Override
    public void registerContribution(Class<? extends ExtensionPoint> extensionPoint, Contribution contribution) {
        super.registerContribution(extensionPoint, contribution);
        testContributions.put(contribution.getClass().getCanonicalName(), contribution);
    }

    public <C extends Contribution> C getContribution(Class<C> contributionClass) {
        return (C) testContributions.get(contributionClass.getCanonicalName());
    }
}
