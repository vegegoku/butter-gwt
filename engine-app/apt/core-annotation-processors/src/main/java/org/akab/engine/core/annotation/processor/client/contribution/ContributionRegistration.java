package org.akab.engine.core.annotation.processor.client.contribution;

import org.akab.engine.core.annotation.processor.client.BaseElementRegistration;
import org.akab.engine.core.annotation.processor.client.RegistrationImplementation;
import org.akab.engine.core.api.client.extension.ContributionsRegistry;

public class ContributionRegistration extends BaseElementRegistration {
    public ContributionRegistration(RegistrationImplementation implementation) {
        super(implementation);
    }

    @Override
    protected Class<?> argumentType() {
        return ContributionsRegistry.class;
    }

    @Override
    protected String methodName() {
        return "registerContributions";
    }
}
