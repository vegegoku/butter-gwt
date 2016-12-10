package org.akab.engine.app;

import com.google.auto.common.MoreElements;
import com.google.auto.service.AutoService;
import org.akab.engine.annotations.processor.utils.BaseProcessor;
import org.akab.engine.annotations.processor.utils.ProcessorElement;
import org.akab.engine.app.presenter.PresenterRegistration;
import org.akab.engine.app.presenter.PresenterRegistrationImplementation;
import org.akab.engine.app.uiview.ViewRegistration;
import org.akab.engine.app.uiview.ViewRegistrationImplementation;
import org.akab.engine.core.api.client.annotations.ClientModule;
import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.annotations.UiView;
import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.client.mvp.view.View;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AutoService(Processor.class)
public class CoreAnnotationProcessor extends BaseProcessor {

    private RoundEnvironment roundEnv;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        this.roundEnv = roundEnv;

        roundEnv.getElementsAnnotatedWith(ClientModule.class).stream()
                .filter(e -> validateElementKind(e, ElementKind.CLASS))
                .forEach(e -> generateModuleConfiguration(newProcessorElement(e)));
        return true;
    }

    private void generateModuleConfiguration(ProcessorElement element) {
        try (Writer sourceWriter = obtainSourceWriter(element.elementPackage(), element.getAnnotation(ClientModule.class).name() + ConfigurationSourceWriter.MODEL_CONFIGURATION)) {
            sourceWriter.write(createConfigurationWriter(element).write());
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class");
        }
    }

    private ConfigurationSourceWriter createConfigurationWriter(ProcessorElement element) {
        return new ConfigurationSourceWriter.Builder()
                .withProcessorElement(element)
                .withElementRegistration(presenterRegistration())
                .withElementRegistration(uiViewRegistration()).build();
    }

    private ElementRegistration presenterRegistration() {
        if (!isTherePresenters())
            return NullElementRegistration.NULL_REGISTRATION;

        Map<Element, DeclaredType> presenters = presenters();
        return new PresenterRegistration(new PresenterRegistrationImplementation(presenters));
    }

    private boolean isTherePresenters() {
        return presentersAsStream().count() > 0;
    }

    private Map<Element, DeclaredType> presenters() {
        if (presentersAsStream().anyMatch(e -> !isImplementsPresentable(e)))
            throw new RuntimeException("Invalid presenter");
        return presentersAsStream()
                .collect(Collectors.toMap(e -> e,
                        e -> (DeclaredType) ((TypeElement) e).getInterfaces().get(0)
                        , throwingOnDuplicateKey()
                        , LinkedHashMap::new));
    }

    private boolean isImplementsPresentable(Element e) {
        return isImplementsInterface(e, Presentable.class);
    }

    private Stream<? extends Element> presentersAsStream() {
        return elementsAsStream(Presenter.class);
    }

    private ElementRegistration uiViewRegistration() {
        if (!isThereViews())
            return NullElementRegistration.NULL_REGISTRATION;

        Map<Element, DeclaredType> views = views();
        return new ViewRegistration(new ViewRegistrationImplementation(views));
    }

    private boolean isThereViews() {
        return elementsAsStream(UiView.class).count() > 0;
    }

    private Map<Element, DeclaredType> views() {
        if (viewsAsStream().anyMatch(e -> !isImplementsView(e)))
            throw new RuntimeException("Invalid view");

        return viewsAsStream().collect(Collectors.toMap(e -> e,
                e -> {
                    AnnotationMirror annotationMirror = MoreElements.getAnnotationMirror(e, UiView.class).get();
                    return getProviderInterface(annotationMirror);
                }
                , throwingOnDuplicateKey()
                , LinkedHashMap::new));
    }

    private Stream<? extends Element> viewsAsStream() {
        return elementsAsStream(UiView.class);
    }

    private boolean isImplementsView(Element e) {
        return isImplementsInterface(e, View.class);
    }

    private DeclaredType getProviderInterface(AnnotationMirror providerAnnotation) {
        Map<? extends ExecutableElement, ? extends AnnotationValue> valueIndex =
                providerAnnotation.getElementValues();

        AnnotationValue value = valueIndex.values().iterator().next();
        return (DeclaredType) value.getValue();
    }

    private BinaryOperator<DeclaredType> throwingOnDuplicateKey() {
        return (u, v) -> {
            throw new IllegalStateException("Duplicate key " + u);
        };
    }

    private boolean isImplementsInterface(Element e, Class<?> clazz) {
        return typeUtils.isAssignable(e.asType(), (TypeMirror) elementUtils.getTypeElement(clazz.getCanonicalName()).asType());
    }

    private Stream<? extends Element> elementsAsStream(Class<? extends Annotation> annotation) {
        return roundEnv.getElementsAnnotatedWith(annotation).stream().filter(e -> validateElementKind(e, ElementKind.CLASS));
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new HashSet<>();
        annotations.add(ClientModule.class.getCanonicalName());
        annotations.add(Presenter.class.getCanonicalName());
        annotations.add(UiView.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
