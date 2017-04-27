package org.akab.engine.core.annotation.processor.client;

import com.google.auto.common.MoreElements;
import com.google.auto.service.AutoService;
import org.akab.engine.annotations.processor.utils.BaseProcessor;
import org.akab.engine.annotations.processor.utils.FullClassName;
import org.akab.engine.core.api.client.annotations.InjectContext;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@AutoService(Processor.class)
public class InjectContextProcessor extends BaseProcessor {

    private static final Logger LOGGER = Logger.getLogger(ContributionClientRequestProcessor.class.getName());

    private final Set<String> supportedAnnotations = new HashSet<>();

    public InjectContextProcessor() {
        supportedAnnotations.add(InjectContext.class.getCanonicalName());
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return supportedAnnotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(InjectContext.class)
                .forEach(this::generateContribution);
        return false;
    }

    private void generateContribution(Element e) {
        String extensionPoint=getExtensionPoint(e);
        String presenter=asTypeElement((DeclaredType)e.getEnclosingElement().asType()).getQualifiedName().toString();
        FullClassName presenterFulLClassName=new FullClassName(presenter);
        FullClassName extensionPointFullClassName=new FullClassName(extensionPoint);
        String generatedClassName=presenterFulLClassName.asSimpleName()+"ContributionTo"+extensionPointFullClassName.asSimpleName();
        String targetPackage=presenterFulLClassName.asPackage().replace("presenters","contributions");
        try (Writer sourceWriter = obtainSourceWriter(targetPackage,generatedClassName)) {
            sourceWriter
                    .write(new InjectContextSourceWriter(newProcessorElement(e), presenter, extensionPoint, targetPackage, generatedClassName).write());
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Could not generate classes : ", ex);
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class");
        }
    }

    private String getExtensionPoint(Element e) {
        AnnotationMirror
                providerAnnotation = (AnnotationMirror) MoreElements.getAnnotationMirror(e, InjectContext.class).get();
        DeclaredType providerInterface = this.getProviderInterface(providerAnnotation);
        TypeElement typeElement=asTypeElement(providerInterface);
        return typeElement.getQualifiedName().toString();

    }


    private TypeElement asTypeElement(DeclaredType p) {
        return (TypeElement)p.asElement();
    }

    private DeclaredType getProviderInterface(AnnotationMirror providerAnnotation) {
        Map valueIndex = providerAnnotation.getElementValues();
        AnnotationValue value = (AnnotationValue)valueIndex.values().iterator().next();
        return (DeclaredType)value.getValue();
    }
}
