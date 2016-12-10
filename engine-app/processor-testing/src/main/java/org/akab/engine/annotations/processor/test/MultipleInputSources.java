package org.akab.engine.annotations.processor.test;

import javax.annotation.processing.Processor;

public class MultipleInputSources implements InputSource {
    private String[] inputClassesNames;

    public MultipleInputSources(String[] inputClassesNames) {
        this.inputClassesNames = inputClassesNames;
    }

    @Override
    public BaseTargetProcessor withProcessor(Processor processor) {
        return new MultipleTargetProcessor(inputClassesNames, processor);
    }
}
