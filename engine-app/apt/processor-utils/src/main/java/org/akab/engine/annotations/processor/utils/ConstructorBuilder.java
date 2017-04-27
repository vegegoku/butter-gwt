package org.akab.engine.annotations.processor.utils;

public class ConstructorBuilder extends MethodBuilder{

    ConstructorBuilder(String name, JavaSourceBuilder javaSourceBuilder) {
        super(name, javaSourceBuilder);
    }

    @Override
    public MethodBuilder returns(String type) {
        return this;
    }
}
