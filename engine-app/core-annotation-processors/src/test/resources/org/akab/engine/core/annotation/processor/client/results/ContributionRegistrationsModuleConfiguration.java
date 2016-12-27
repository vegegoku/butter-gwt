package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.ModuleConfiguration;
import org.akab.engine.core.api.client.extension.ContributionsRegistry;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import org.akab.engine.core.annotation.processor.client.AnnotatedClassWithContribution;

public class ContributionRegistrationsModuleConfiguration implements ModuleConfiguration {

    @Override
    public void registerContributions(ContributionsRegistry registry) {
        registry.registerContribution(MainExtensionPoint.class, new AnnotatedClassWithContribution());
    }
}