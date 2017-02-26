package org.akab.engine.core.annotation.processor.client;

import com.google.auto.common.MoreElements;
import com.google.auto.service.AutoService;
import org.akab.engine.annotations.processor.utils.BaseProcessor;
import org.akab.engine.annotations.processor.utils.FullClassName;
import org.akab.engine.annotations.processor.utils.ProcessorElement;
import org.akab.engine.core.api.client.annotations.AutoRequest;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

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
import java.util.stream.Collectors;

@AutoService(Processor.class)
public class ContributionClientRequestProcessor extends BaseProcessor {

    private static final Logger LOGGER = Logger.getLogger(ContributionClientRequestProcessor.class.getName());

    private final Set<String> supportedAnnotations = new HashSet<>();

    public ContributionClientRequestProcessor() {
        supportedAnnotations.add(AutoRequest.class.getCanonicalName());
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
        roundEnv.getElementsAnnotatedWith(AutoRequest.class).stream().filter(e -> hasPresenters(e))
                .forEach(e -> generateContributionClientRequest(e));
        return false;
    }

    private void generateContributionClientRequest(Element e) {
        Set<String> presenters=getContributionPresenters(e);
        String presenterMethod=getMethodName(e);
        presenters.forEach(p-> generateRequest(newProcessorElement(e), p, presenterMethod));

    }

    private void generateRequest(ProcessorElement processorElement, String presenterName, String presenterMethod) {
        String contributionName = processorElement.getInterfaceFullQualifiedGenericName(Contribution.class);
        FullClassName fullClassname = new FullClassName(contributionName);
        String contextName = fullClassname.allImports().get(1);
        FullClassName contextFullName = new FullClassName(contextName);
        String contextSimpleName = contextFullName.asSimpleName();
        String presenterSimpleName=new FullClassName(presenterName).asSimpleName();
        String targetPackage=processorElement.elementPackage().replace("contributions", "requests");
        String generatedClassName="Obtain"+contextSimpleName+"For"+presenterSimpleName + "ClientRequest";
        try (Writer sourceWriter = obtainSourceWriter(
                targetPackage,generatedClassName)) {
            sourceWriter
                    .write(new ContributionRequestSourceWriter(processorElement, presenterName, targetPackage, generatedClassName, presenterMethod).write());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Could not generate classes : ", e);
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class");
        }
    }

    private boolean hasPresenters(Element e) {
        Set<String> presenters = getContributionPresenters(e);
        if (Objects.nonNull(presenters) && presenters.size() > 0)
            return true;
        return false;
    }

    private Set<String> getContributionPresenters(Element e) {
        AnnotationMirror
                providerAnnotation = (AnnotationMirror) MoreElements.getAnnotationMirror(e, AutoRequest.class).get();
        List<DeclaredType> providerInterfaces = this.getProviderInterface(providerAnnotation);
        Set<TypeElement> elements=providerInterfaces.stream().map(p->asTypeElement(p)).collect(Collectors.toSet());
        return elements.stream().map(t->t.getQualifiedName().toString()).collect(Collectors.toSet());
    }

    private String getMethodName(Element e) {
        AnnotationMirror
                providerAnnotation = (AnnotationMirror) MoreElements.getAnnotationMirror(e, AutoRequest.class).get();
        String method = this.getMethod(providerAnnotation);
        return method;
    }


    private TypeElement asTypeElement(DeclaredType p) {
        return (TypeElement)p.asElement();
    }

    private List<DeclaredType> getProviderInterface(AnnotationMirror providerAnnotation) {
        Map valueIndex = providerAnnotation.getElementValues();
        AnnotationValue presenters=(AnnotationValue)valueIndex.get(valueIndex.keySet().stream().filter(k->(k.toString().equals("presenters()"))).findFirst().get());
        return ((List<AnnotationValue>)presenters.getValue()).stream().map(v->(DeclaredType)v.getValue()).collect(Collectors.toList());
    }

    private String getMethod(AnnotationMirror providerAnnotation) {
        Map valueIndex = providerAnnotation.getElementValues();
        AnnotationValue method=(AnnotationValue)valueIndex.get(valueIndex.keySet().stream().filter(k->(k.toString().equals("method()"))).findFirst().get());
        return (String)method.getValue();
    }
}
