package org.akab.engine.annotations.processor.test;


public class ProcessorAssert {

    private ProcessorAssert(){

    }

    public static InputSource assertProcessing(String inputClassName) {
        return new SingleInputSource(inputClassName);
    }

    public static MultipleInputSources assertProcessing(String... inputClassesNames) {
        return new MultipleInputSources(inputClassesNames);
    }
}
