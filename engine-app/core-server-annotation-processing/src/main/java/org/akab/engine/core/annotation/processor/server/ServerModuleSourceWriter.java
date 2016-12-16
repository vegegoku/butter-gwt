package org.akab.engine.core.annotation.processor.server;

import com.google.auto.service.AutoService;
import org.akab.engine.annotations.processor.utils.JavaSourceWriter;
import org.akab.engine.annotations.processor.utils.ProcessorElement;
import org.akab.engine.core.api.shared.server.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ServerModuleSourceWriter extends JavaSourceWriter {

    private class ImportItem{
        private final String importString;

        private ImportItem(String importString) {
            this.importString = importString;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ImportItem that = (ImportItem) o;

            return importString != null ? importString.equals(that.importString) : that.importString == null;
        }

        @Override
        public int hashCode() {
            return importString != null ? importString.hashCode() : 0;
        }
    }

    private final List<ProcessorElement> handlers;
    private final List<ProcessorElement> interceptors;
    private final List<ProcessorElement> globalInterceptors;
    List<ImportItem> imports=new LinkedList<>();

    public ServerModuleSourceWriter(ProcessorElement serverModuleElement, List<ProcessorElement> handlers,
                                    List<ProcessorElement> interceptors, List<ProcessorElement> globalInterceptors) {
        super(serverModuleElement);
        this.handlers = handlers;
        this.interceptors=interceptors;
        this.globalInterceptors=globalInterceptors;
        imports.add(new ImportItem("import " + ServerModuleConfiguration.class.getCanonicalName() + ";"));
        imports.add(new ImportItem("import " + AutoService.class.getCanonicalName() + ";"));
    }

    public String write() {
        return writePackage() +
                writeImports() +
                writeAutoServiceAnnotation() +
                writeClass() +
                writeBody() +
                writeEnd();
    }

    private String writeAutoServiceAnnotation() {
        return "\n\n@AutoService(ServerModuleConfiguration.class)";
    }

    private String writePackage() {
        return "package " + processorElement.elementPackage() + ";\n";
    }

    private void addImport(String importString){
        if(!imports.contains(new ImportItem(importString.trim()))){
           imports.add(new ImportItem(importString.trim()));
        }
    }

    private String writeImports() {

        addHandlersImports();
        addInterceptorsImports();
        addGlobalInterceptorsImports();

        StringBuilder sb=new StringBuilder();
        imports.forEach(i -> sb.append("\n"+i.importString));

        return sb.toString();
    }


    private void addHandlersImports() {
        if(!handlers.isEmpty()) {
            handlers.stream().map(e -> e.asImports()).collect(Collectors.toList())
                    .forEach(i -> addImport(i.asImportsString()));
            handlers.stream()
                    .forEach(h -> addImport(getRequestImport(h.genericInterfaceImports(RequestHandler.class))));

            addImport("\nimport " + HandlerRegistry.class.getCanonicalName() + ";");
        }

    }

    public void addInterceptorsImports() {
        if(!interceptors.isEmpty()){

            interceptors.stream().map(e -> e.asImports()).collect(Collectors.toList())
                    .forEach(i ->addImport(i.asImportsString()));
            interceptors.stream().forEach(h -> h.genericInterfaceImports(RequestInterceptor.class).forEach(i -> addImport("\nimport "+i+";")));

            addImport("\nimport " + InterceptorsRegistry.class.getCanonicalName() + ";");
        }

    }

    private void addGlobalInterceptorsImports() {
        if(!globalInterceptors.isEmpty()){

            globalInterceptors.stream().map(e -> e.asImports()).collect(Collectors.toList())
                    .forEach(i ->addImport(i.asImportsString()));
            globalInterceptors.stream().forEach(h -> h.genericInterfaceImports(GlobalRequestInterceptor.class).forEach(i -> addImport("\nimport "+i+";")));

            addImport("\nimport " + InterceptorsRegistry.class.getCanonicalName() + ";");
        }
    }


    private String getRequestImport(List<String> imports) {
        Iterator<String> i=imports.iterator();
        return "\nimport "+i.next()+";";
    }


    private String writeClass() {
        return "\npublic class " + processorElement.getAnnotation(ServerModule.class).name() +
                "ServerModule implements ServerModuleConfiguration{";
    }

    private String writeBody() {
        return writeHandlersRegisterMethod()+writeInterceptorsRegisterMethod()+writeGlobalInterceptorsRegisterMethod();
    }

    private String writeHandlersRegisterMethod() {
        StringBuilder sb = new StringBuilder();
        if (handlers.size() > 0) {
            sb.append("\n\n\t@Override")
                    .append("\n\tpublic void registerHandlers(HandlerRegistry registry) {");

            handlers.forEach(h ->
                    sb.append("\n\t\tregistry.registerHandler(")
                            .append(getRequestFromHandler(h))
                            .append(".class.getCanonicalName(), new ").append(h.simpleName()).append("());"));
            sb.append("\n\t}\n");
        }

        return sb.toString();
    }

    private String writeInterceptorsRegisterMethod() {
        StringBuilder sb = new StringBuilder();
        if (interceptors.size() > 0) {
            sb.append("\n\n\t@Override")
                    .append("\n\tpublic void registerInterceptors(InterceptorsRegistry registry) {");

            interceptors.forEach(i ->
                    sb.append("\n\t\tregistry.registerInterceptor(")
                            .append(getRequestFromInterceptor(i))
                            .append(".class.getCanonicalName(), ").append(getEntryPointFromInterceptor(i))
                            .append(".class.getCanonicalName(),").append(" new ").append(i.simpleName()).append("());"));
            sb.append("\n\t}\n");
        }

        return sb.toString();
    }

    private String writeGlobalInterceptorsRegisterMethod() {
        StringBuilder sb = new StringBuilder();
        if (globalInterceptors.size() > 0) {
            sb.append("\n\n\t@Override")
                    .append("\n\tpublic void registerGlobalInterceptors(InterceptorsRegistry registry) {");

            globalInterceptors.forEach(i ->
                    sb.append("\n\t\tregistry.registerGlobalInterceptor(")
                            .append(getEntryPointFromGlobalInterceptor(i))
                            .append(".class.getCanonicalName(),").append(" new ").append(i.simpleName()).append("());"));
            sb.append("\n\t}\n");
        }

        return sb.toString();
    }

    private String getRequestFromHandler(ProcessorElement handler) {
        return handler.genericInterfaceImportsElements(RequestHandler.class).stream().findFirst().get().simpleName();
    }

    private String getRequestFromInterceptor(ProcessorElement interceptor) {
        return interceptor.genericInterfaceImportsElements(RequestInterceptor.class).stream().findFirst().get().simpleName();
    }

    private String getEntryPointFromInterceptor(ProcessorElement handler) {
        Iterator<ProcessorElement> i=handler.genericInterfaceImportsElements(RequestInterceptor.class).iterator();
        i.next();
        return i.next().simpleName();
    }

    private String getEntryPointFromGlobalInterceptor(ProcessorElement interceptor) {
        return interceptor.genericInterfaceImportsElements(GlobalRequestInterceptor.class).iterator().next().simpleName();
    }

    private String writeEnd() {
        return "\n}";
    }



}
