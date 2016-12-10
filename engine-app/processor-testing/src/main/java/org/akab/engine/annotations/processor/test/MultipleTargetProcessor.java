package org.akab.engine.annotations.processor.test;

import com.google.common.truth.Truth;
import com.google.testing.compile.CompileTester;
import com.google.testing.compile.JavaFileObjects;

import javax.annotation.processing.Processor;
import javax.tools.JavaFileObject;
import java.util.Arrays;

import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

public class MultipleTargetProcessor extends BaseTargetProcessor {

    private final String[] inputClassesNames;
    private final Processor processor;

    public MultipleTargetProcessor(String[] inputClassesNames, Processor processor) {
        this.inputClassesNames = inputClassesNames;
        this.processor = processor;
    }

    @Override
    public CompileTester.SuccessfulCompilationClause compilesWithoutErrors() {
        return Truth.assert_().about(javaSources()).that(Arrays.asList(asJavaFileObjectsArray()))
                .processedWith(processor).compilesWithoutError();
    }

    private JavaFileObject[] asJavaFileObjectsArray() {
        JavaFileObject[] result = new JavaFileObject[inputClassesNames.length];
        for (int i = 0; i < inputClassesNames.length; i++)
            result[i] = JavaFileObjects.forResource(inputClassesNames[i]);
        return result;
    }
}
