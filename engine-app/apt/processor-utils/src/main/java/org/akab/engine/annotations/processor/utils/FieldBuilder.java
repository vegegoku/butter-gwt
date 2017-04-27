package org.akab.engine.annotations.processor.utils;


public class FieldBuilder {
    private final String methodName;
    private final JavaSourceBuilder javaSourceBuilder;

    private final StringBuilder modifiersWriter = new StringBuilder();
    private final StringBuilder typeWriter = new StringBuilder();
    private final StringBuilder fieldWriter=new StringBuilder();


    FieldBuilder(String name, JavaSourceBuilder javaSourceBuilder) {
        this.methodName = name;
        this.javaSourceBuilder = javaSourceBuilder;
    }

    public FieldBuilder withModifier(ModifierBuilder.ModifierWriter modifierWriter) {
        modifiersWriter.append(modifierWriter.writeModifiers());
        return this;
    }

    public FieldBuilder ofType(String type) {
        return setType(new FullClassName(type));
    }

    private FieldBuilder setType(FullClassName type) {
        javaSourceBuilder.imports(type);
        typeWriter.append(type.asSimpleGenericName() + " ");
        return this;
    }

    String write() {
        return fieldWriter
                .append("\n\t")
                .append(modifiersWriter.toString())
                .append(typeWriter.toString())
                .append(methodName).append(";\n")
                .toString();
    }
    public JavaSourceBuilder end() {
        return javaSourceBuilder.writeField(this);
    }

}
