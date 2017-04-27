package org.akab.engine.core.annotation.processor.client.registration;

import org.akab.engine.core.annotation.processor.client.ElementRegistration;
import org.akab.engine.core.annotation.processor.client.RegistrationHelper;
import org.akab.engine.core.annotation.processor.client.TwoArgumentsRegistrationFactory;
import org.akab.engine.core.annotation.processor.client.contribution.ContributionRegistration;
import org.akab.engine.core.annotation.processor.client.contribution.ContributionRegistrationImplementation;
import org.akab.engine.core.api.client.annotations.Contribute;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

public class ContributionRegistrationFactory extends TwoArgumentsRegistrationFactory {
    public ContributionRegistrationFactory(RegistrationHelper helper) {
        super(helper);
    }

    @Override
    protected ElementRegistration typeRegistration() {
        return new ContributionRegistration(new ContributionRegistrationImplementation(items()));
    }

    @Override
    protected Element targetType(Element e) {
        TypeMirror typeMirror = ((TypeElement) e).getInterfaces().get(0);
        TypeMirror generic = ((DeclaredType) typeMirror).getTypeArguments().get(0);
        return ((DeclaredType) generic).asElement();
    }

    @Override
    protected String annotation() {
        return Contribute.class.getCanonicalName();
    }
}
