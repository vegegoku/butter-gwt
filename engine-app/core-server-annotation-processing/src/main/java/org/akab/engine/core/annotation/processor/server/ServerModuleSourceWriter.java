package org.akab.engine.core.annotation.processor.server;

import com.google.auto.service.AutoService;
import org.akab.engine.annotations.processor.utils.*;
import org.akab.engine.core.api.shared.server.*;

import java.util.List;

public class ServerModuleSourceWriter extends JavaSourceWriter {

    private final JavaSourceBuilder sourceBuilder;

    private final List<ProcessorElement> handlers;
    private final List<ProcessorElement> interceptors;
    private final List<ProcessorElement> globalInterceptors;

    public ServerModuleSourceWriter(ProcessorElement serverModuleElement,
                                    List<ProcessorElement> handlers,
                                    List<ProcessorElement> interceptors,
                                    List<ProcessorElement> globalInterceptors) {
        super(serverModuleElement);
        this.sourceBuilder =new JavaSourceBuilder( processorElement.getAnnotation(ServerModule.class).name() +
                "ServerModule");
        this.handlers = handlers;
        this.interceptors=interceptors;
        this.globalInterceptors=globalInterceptors;
    }

    public String write() {
         sourceBuilder.onPackage(processorElement.elementPackage())
                .imports(AutoService.class.getCanonicalName())
                .annotate("@AutoService(ServerModuleConfiguration.class)")
                .withModifiers(new ModifierBuilder().asPublic())
                .implement(ServerModuleConfiguration.class.getCanonicalName());

            writeBody();

        return sourceBuilder.build();
    }


    private void writeBody() {
        writeHandlersRegisterMethod();
        writeInterceptorsRegisterMethod();
        writeGlobalInterceptorsRegisterMethod();
    }

    private void writeHandlersRegisterMethod() {
        if (!handlers.isEmpty()) {
            MethodBuilder method= sourceBuilder.method("registerHandlers")
                    .annotate("@Override")
                    .withModifier(new ModifierBuilder().asPublic())
                    .returns("void")
                    .takes(HandlerRegistry.class.getCanonicalName(), "registry");

            handlers.forEach(h ->
                   method.line(getHandlerRegistrationLine(h)));
            method.end();
        }
    }

    private String getHandlerRegistrationLine(ProcessorElement handler) {
            sourceBuilder.imports(new FullClassName(handler.getFullQualifiedGenericName()));
            FullClassName request=new FullClassName(new FullClassName(handler.getInterfaceFullQualifiedGenericName(RequestHandler.class)).allImports().get(1));
            sourceBuilder.imports(request);

        return "registry.registerHandler("+request.asSimpleGenericName()+".class.getCanonicalName(), new "+handler.simpleName()+"())";
    }

    private void writeInterceptorsRegisterMethod() {
        if (!interceptors.isEmpty()) {
            MethodBuilder method= sourceBuilder.method("registerInterceptors")
                    .annotate("@Override")
                    .withModifier(new ModifierBuilder().asPublic())
                    .returns("void")
                    .takes(InterceptorsRegistry.class.getCanonicalName(), "registry");

            interceptors.forEach(i ->
                    method.line(getInterceptorRegistrationLine(i)));
            method.end();
        }
    }

    private String getInterceptorRegistrationLine(ProcessorElement interceptor) {
        sourceBuilder.imports(new FullClassName(interceptor.getFullQualifiedGenericName()));
        FullClassName request=new FullClassName(new FullClassName(interceptor.getInterfaceFullQualifiedGenericName(RequestInterceptor.class)).allImports().get(1));
        FullClassName entryPoint=new FullClassName(new FullClassName(interceptor.getInterfaceFullQualifiedGenericName(RequestInterceptor.class)).allImports().get(2));
        sourceBuilder.imports(request);
        sourceBuilder.imports(entryPoint);

        return "registry.registerInterceptor("+request.asSimpleGenericName()+".class.getCanonicalName(), "+entryPoint.asSimpleGenericName()+".class.getCanonicalName(), new "+interceptor.simpleName()+"())";
    }

    private void writeGlobalInterceptorsRegisterMethod() {
        if (!globalInterceptors.isEmpty()) {
            MethodBuilder method= sourceBuilder.method("registerGlobalInterceptors")
                    .annotate("@Override")
                    .withModifier(new ModifierBuilder().asPublic())
                    .returns("void")
                    .takes(InterceptorsRegistry.class.getCanonicalName(), "registry");

            globalInterceptors.forEach(g ->
                    method.line(getGlobalInterceptorRegistrationLine(g)));
            method.end();
        }
    }

    private String getGlobalInterceptorRegistrationLine(ProcessorElement interceptor) {
        sourceBuilder.imports(new FullClassName(interceptor.getFullQualifiedGenericName()));
        FullClassName entryPoint=new FullClassName(new FullClassName(interceptor.getInterfaceFullQualifiedGenericName(GlobalRequestInterceptor.class)).allImports().get(1));
        sourceBuilder.imports(entryPoint);

        return "registry.registerGlobalInterceptor("+entryPoint.asSimpleGenericName()+".class.getCanonicalName(), new "+interceptor.simpleName()+"())";
    }
}
