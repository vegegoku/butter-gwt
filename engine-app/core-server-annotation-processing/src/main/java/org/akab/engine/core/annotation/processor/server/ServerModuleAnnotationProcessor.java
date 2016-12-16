package org.akab.engine.core.annotation.processor.server;

import com.google.auto.service.AutoService;
import org.akab.engine.annotations.processor.utils.BaseProcessor;
import org.akab.engine.annotations.processor.utils.ProcessorElement;
import org.akab.engine.core.api.shared.request.ServerRequest;
import org.akab.engine.core.api.shared.request.ServerResponse;
import org.akab.engine.core.api.shared.server.*;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AutoService(Processor.class)
public class ServerModuleAnnotationProcessor extends BaseProcessor{

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<String>(){{
            add(Handler.class.getCanonicalName());
            add(ServerModule.class.getCanonicalName());
        }};
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if(roundEnv.getElementsAnnotatedWith(ServerModule.class).size()==1)
            generateServerModule(getModule(roundEnv.getElementsAnnotatedWith(ServerModule.class).stream()
                    .findFirst().get()), getHandlers(roundEnv), getInterceptors(roundEnv), getGlobalInterceptors(roundEnv));

        else if(roundEnv.getElementsAnnotatedWith(ServerModule.class).size()>1){
            this.messager.printMessage(Diagnostic.Kind.ERROR, "Only one ServerModule is allowed per project.!");
        }
        return true;
    }

    private ProcessorElement getModule(Element element) {
        return newProcessorElement(element);
    }

    private List<ProcessorElement> getHandlers(RoundEnvironment roundEnv) {
        return roundEnv.getElementsAnnotatedWith(Handler.class).stream()
        .filter(e -> implementsHandler(newProcessorElement(e))).map(e -> newProcessorElement(e)).collect(Collectors.toList());
    }

    private List<ProcessorElement> getInterceptors(RoundEnvironment roundEnv) {
        return roundEnv.getElementsAnnotatedWith(Interceptor.class).stream()
                .filter(e -> implementsInterceptor(newProcessorElement(e))).map(e -> newProcessorElement(e)).collect(Collectors.toList());
    }

    private List<ProcessorElement> getGlobalInterceptors(RoundEnvironment roundEnv) {
        return roundEnv.getElementsAnnotatedWith(GlobalInterceptor.class).stream()
                .filter(e -> implementsGlobalInterceptor(newProcessorElement(e))).map(e -> newProcessorElement(e)).collect(Collectors.toList());
    }

    private boolean implementsHandler(ProcessorElement e) {
        if(e.isImplementsGenericInterface(RequestHandler.class))
            return true;
        this.messager.printMessage(Diagnostic.Kind.ERROR, "Classes annotated as Handlers must implement RequestHandler interface.!", e.asTypeElement());
        return false;
    }

    private boolean implementsInterceptor(ProcessorElement e) {
        if(e.isImplementsGenericInterface(RequestInterceptor.class))
            return true;
        this.messager.printMessage(Diagnostic.Kind.ERROR, "Classes annotated as Interceptors must implement RequestInterceptor interface.!", e.asTypeElement());
        return false;
    }
    private boolean implementsGlobalInterceptor(ProcessorElement e) {
        if(e.isImplementsGenericInterface(GlobalRequestInterceptor.class))
            return true;
        this.messager.printMessage(Diagnostic.Kind.ERROR, "Classes annotated as Global Interceptors must implement GlobalRequestInterceptor interface.!", e.asTypeElement());
        return false;
    }


    private boolean isSameInterface(TypeMirror i, Class<?> targetInterface) {
        return targetInterface.getCanonicalName().equals(newProcessorElement(typeUtils.asElement(i)).fullQualifiedNoneGenericName());
    }

    private void generateServerModule(ProcessorElement processorElement, List<ProcessorElement> handlers,
                                      List<ProcessorElement> interceptors, List<ProcessorElement> globalInterceptors) {
        try (Writer sourceWriter = obtainSourceWriter(processorElement.elementPackage(), processorElement.getAnnotation(ServerModule.class).name()+"ServerModule")) {
            sourceWriter.write(new ServerModuleSourceWriter(processorElement, handlers, interceptors, globalInterceptors).write());
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "could not generate class");
        }
    }

}
