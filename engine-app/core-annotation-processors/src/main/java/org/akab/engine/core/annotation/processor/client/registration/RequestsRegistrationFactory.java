package org.akab.engine.core.annotation.processor.client.registration;

import org.akab.engine.core.annotation.processor.client.RegistrationHelper;
import org.akab.engine.core.annotation.processor.client.TwoArgumentsRegistrationFactory;
import org.akab.engine.core.annotation.processor.client.ElementRegistration;
import org.akab.engine.core.annotation.processor.client.request.RequestRegistration;
import org.akab.engine.core.annotation.processor.client.request.RequestRegistrationImplementation;
import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.client.request.BaseRequest;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import java.lang.annotation.Annotation;

public class RequestsRegistrationFactory extends TwoArgumentsRegistrationFactory {
    public RequestsRegistrationFactory(RegistrationHelper helper) {
        super(helper);
    }

    @Override
    protected ElementRegistration typeRegistration() {
        return new RequestRegistration(new RequestRegistrationImplementation(items()));
    }

    @Override
    protected Element targetType(Element e) {
        TypeMirror typeMirror = ((TypeElement) e).getSuperclass();
        TypeMirror generic = ((DeclaredType) typeMirror).getTypeArguments().get(0);
        return ((DeclaredType) generic).asElement();
    }

    @Override
    protected boolean isValid(Element e) {
        return helper.isSubtypeOfGenericClass(e, BaseRequest.class);
    }

    @Override
    protected String annotation() {
        return Request.class.getCanonicalName();
    }
}
