package org.akab.engine.core.annotation.processor.client.registration;

import com.google.auto.common.MoreElements;
import org.akab.engine.core.annotation.processor.client.RegistrationHelper;
import org.akab.engine.core.annotation.processor.client.TwoArgumentsRegistrationFactory;
import org.akab.engine.core.annotation.processor.client.ElementRegistration;
import org.akab.engine.core.annotation.processor.client.uiview.ViewRegistration;
import org.akab.engine.core.annotation.processor.client.uiview.ViewRegistrationImplementation;
import org.akab.engine.core.api.client.annotations.UiView;
import org.akab.engine.core.api.client.mvp.view.View;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.DeclaredType;
import java.lang.annotation.Annotation;
import java.util.Map;

public class UiViewsRegistrationFactory extends TwoArgumentsRegistrationFactory {

    public UiViewsRegistrationFactory(RegistrationHelper helper) {
        super(helper);
    }

    @Override
    protected ElementRegistration typeRegistration() {
        return new ViewRegistration(new ViewRegistrationImplementation(items()));
    }

    @Override
    protected Element targetType(Element e) {
        AnnotationMirror annotationMirror = MoreElements.getAnnotationMirror(e, UiView.class).get();
        return getProviderInterface(annotationMirror);
    }

    private Element getProviderInterface(AnnotationMirror providerAnnotation) {
        Map<? extends ExecutableElement, ? extends AnnotationValue> valueIndex =
                providerAnnotation.getElementValues();

        AnnotationValue value = valueIndex.values().iterator().next();
        return ((DeclaredType) value.getValue()).asElement();
    }

    @Override
    protected boolean isValid(Element e) {
        return isImplementInterface(e, View.class);
    }

    @Override
    protected Class<? extends Annotation> annotation() {
        return UiView.class;
    }
}
