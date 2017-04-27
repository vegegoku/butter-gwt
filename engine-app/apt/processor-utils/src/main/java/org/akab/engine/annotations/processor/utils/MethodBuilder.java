package org.akab.engine.annotations.processor.utils;


public class MethodBuilder {
    private final String methodName;
    private final JavaSourceBuilder javaSourceBuilder;

    private final StringBuilder modifiersWriter = new StringBuilder();
    private final StringBuilder returnTypeWriter = new StringBuilder();
    private final StringBuilder methodWriter = new StringBuilder();
    private final StringBuilder parametersWriter = new StringBuilder();
    private final StringBuilder annotationWriter = new StringBuilder();
    private final StringBuilder codeWriter = new StringBuilder();

    MethodBuilder(String name, JavaSourceBuilder javaSourceBuilder) {
        this.methodName = name;
        this.javaSourceBuilder = javaSourceBuilder;
    }

    public MethodBuilder withModifier(ModifierBuilder.ModifierWriter modifierWriter) {
        modifiersWriter.append(modifierWriter.writeModifiers());
        return this;
    }

    public MethodBuilder returns(String type) {
        return setReturnType(new FullClassName(type));
    }

    public MethodBuilder returnsVoid(){
        returnTypeWriter.append("void ");
        return this;
    }

    private MethodBuilder setReturnType(FullClassName type) {
        javaSourceBuilder.imports(type);
        returnTypeWriter.append(type.asSimpleGenericName() + " ");
        return this;
    }

    String write() {
        return methodWriter
                .append(annotationWriter.toString())
                .append("\n\t")
                .append(modifiersWriter.toString())
                .append(returnTypeWriter.toString())
                .append(methodName).append("(")
                .append(parametersWriter.toString())
                .append(")")
                .append("{\n")
                .append(codeWriter.toString())
                .append("\n\t}\n")
                .toString();
    }

    public JavaSourceBuilder end() {
        return javaSourceBuilder.writeMethod(this);
    }

    public MethodBuilder takes(String type, String name) {
        javaSourceBuilder.imports(new FullClassName(type));
        if (parametersWriter.length() > 0)
            parametersWriter.append(", ");

        parametersWriter.append(new FullClassName(type).asSimpleGenericName())
                .append(" ")
                .append(name);
        return this;
    }

    public MethodBuilder annotate(String annotation) {
        annotationWriter.append("\n\t").append(annotation);
        return this;
    }

    public MethodBuilder line(String codeLine) {
        codeWriter.append("\n\t\t")
                .append(codeLine);
        if (!codeLine.endsWith(";"))
            codeWriter.append(";");
        return this;
    }
}
