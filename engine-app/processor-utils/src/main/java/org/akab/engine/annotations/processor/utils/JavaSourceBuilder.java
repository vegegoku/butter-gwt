package org.akab.engine.annotations.processor.utils;

import java.util.Objects;

public class JavaSourceBuilder {

    private final StringBuilder sourceWriter = new StringBuilder();
    private final String className;
    private String sourcePackage;
    private final StringBuilder modifiersWriter = new StringBuilder();
    private final ImportsBuilder importsBuilder = new ImportsBuilder();
    private final StringBuilder extendsWriter = new StringBuilder();
    private final StringBuilder implementsWriter = new StringBuilder();
    private final StringBuilder classAnnotationWriter = new StringBuilder();
    private final StringBuilder methodsWriter = new StringBuilder();

    public JavaSourceBuilder(String className) {
        this.className = className;
    }

    public String build() {
        writePackage();
        writeImports();
        writeClassAnnotations();
        writeModifiers();
        writeClassHeader();
        writeExtend();
        writeImplements();
        writeClassBegin();
        writeMethods();
        writeClassEnd();
        return sourceWriter.toString();
    }


    private void writePackage() {
        sourceWriter.append(getPackage());
    }

    private void writeImports() {
        sourceWriter.append(getImports());
    }

    private void writeClassAnnotations() {
        sourceWriter.append(classAnnotationWriter.toString());
    }

    private void writeModifiers() {
        sourceWriter.append(modifiersWriter.toString());
    }

    private String getImports() {
        return importsBuilder.hasImports() ? importsBuilder.writeImports() + "\n" : "";
    }

    private String getPackage() {
        return Objects.isNull(sourcePackage) ? "" : ("package " + sourcePackage + ";\n\n");
    }

    private void writeClassHeader() {
        sourceWriter.append("class ").append(className);
    }

    private void writeExtend() {
        sourceWriter.append(extendsWriter.toString());
    }

    private void writeImplements() {
        sourceWriter.append(implementsWriter.toString());
    }

    private void writeClassBegin() {
        sourceWriter.append("{\n");
    }

    private void writeMethods() {
        sourceWriter
                .append(methodsWriter.toString());
    }

    private void writeClassEnd() {
        sourceWriter.append("}").toString();
    }

    public JavaSourceBuilder onPackage(String sourcePackage) {
        this.sourcePackage = sourcePackage;
        return this;
    }

    public JavaSourceBuilder imports(String classImport) {
        importsBuilder.addImport(classImport);
        return this;
    }

    public JavaSourceBuilder withModifiers(ModifierBuilder.ModifierWriter modifierWriter) {
        modifiersWriter.append(modifierWriter.writeModifiers());
        return this;
    }

    public JavaSourceBuilder extend(String fullClassName) {
        if (extendsWriter.length() > 0)
            throw new CouldNotExtendsMoreThanOneClass();
        extend(new FullClassName(fullClassName));
        return this;
    }

    private void extend(FullClassName fullClassName) {
        fullClassName.allImports().forEach(i -> imports(i));
        extendsWriter.append(" extends ").append(fullClassName.asSimpleGenericName());
    }

    public JavaSourceBuilder implement(String fullInterfaceName) {
        implement(new FullClassName(fullInterfaceName));
        return this;
    }

    private void implement(FullClassName fullClassName) {
        fullClassName.allImports().forEach(i -> imports(i));
        implementsWriter.append(getImplementsPart(fullClassName));
    }

    private String getImplementsPart(FullClassName fullClassName) {
        return implementsWriter.length() == 0 ? (" implements " + fullClassName.asSimpleGenericName()) :
                (", " + fullClassName.asSimpleGenericName());
    }

    public JavaSourceBuilder annotate(String annotation) {
        classAnnotationWriter.append(annotation).append("\n");
        return this;
    }

    public MethodBuilder method(String name) {
        return new MethodBuilder(name, this);
    }

    public JavaSourceBuilder imports(FullClassName fullClassName) {
        fullClassName.allImports().forEach(i -> importsBuilder.addImport(i));
        return this;
    }

    JavaSourceBuilder writeMethod(MethodBuilder methodBuilder) {
        methodsWriter.append(methodBuilder.write());
        return this;
    }

    public class CouldNotExtendsMoreThanOneClass extends RuntimeException {
    }
}
