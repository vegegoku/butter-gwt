package org.akab.engine.core.annotation.processor.client.registration;

import org.akab.engine.core.annotation.processor.client.ElementRegistration;
import org.akab.engine.core.annotation.processor.client.RegistrationHelper;
import org.akab.engine.core.annotation.processor.client.SingleArgumentRegistrationFactory;
import org.akab.engine.core.annotation.processor.client.path.PathRegistration;
import org.akab.engine.core.annotation.processor.client.path.PathRegistrationImplementation;
import org.akab.engine.core.api.client.annotations.Path;
import org.akab.engine.core.api.client.request.BaseRequest;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;

public class PathRegistrationFactory extends SingleArgumentRegistrationFactory {
    public PathRegistrationFactory(RegistrationHelper helper) {
        super(helper);
    }

    @Override
    protected ElementRegistration typeRegistration() {
        return new PathRegistration(new PathRegistrationImplementation(paths()));
    }

    private Map<Element, String> paths() {
        Map<Element, String> paths = new LinkedHashMap<>();
        elements().forEach(e -> paths.put(e, pathValue(e)));
        return paths;
    }

    protected String pathValue(Element e) {
        AnnotationMirror annotationMirror = e.getAnnotationMirrors().stream()
                .filter(a -> a.getAnnotationType().toString().equals(annotation().getCanonicalName()))
                .findAny().orElseThrow(IllegalArgumentException::new);
        AnnotationValue path = annotationMirror.getElementValues().entrySet()
                .stream()
                .filter(entry -> !"path".equals(entry.getKey().getSimpleName())).findAny().orElseThrow(IllegalArgumentException::new).getValue();

        return (String) path.getValue();
    }

    @Override
    protected boolean isValid(Element e) {
        return helper.isSubtypeOfGenericClass(e, BaseRequest.class);
    }

    @Override
    protected Class<? extends Annotation> annotation() {
        return Path.class;
    }
}
