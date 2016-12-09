package org.akab.engine.app;


import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.akab.engine.annotations.processor.test.ProcessorAssert.assertProcessing;

public class CoreAnnotationProcessorTest {

    private static final String BASE_PACKAGE = "org/akab/engine/app/";

    private CoreAnnotationProcessor processor() {
        return new CoreAnnotationProcessor();
    }

    private String getExpectedResultFileContent(String resourceName) throws IOException {
        try (InputStream resourceInputStream = this.getClass().getResourceAsStream("results/" + resourceName)) {
            return IOUtils.toString(resourceInputStream, "UTF-8");
        }
    }

    private void assertProcessedClassWith(String className, String resultClassName) throws IOException {
        assertProcessing(BASE_PACKAGE + className)
                .withProcessor(processor())
                .generates(getExpectedResultFileContent(resultClassName));
    }

    @Test
    public void givenNotAnnotatedClassShouldDoNothing() throws Exception {
        assertProcessing(BASE_PACKAGE + "NotAnnotatedClass.java")
                .withProcessor(processor())
                .compilesWithoutErrors();
    }

    @Test
    public void givenAnnotatedClassWithClientModule_ShouldGenerateClassImplementsModuleConfigurations() throws Exception {
        assertProcessedClassWith("AnnotatedClassWithClientModuleWithNameTest.java", "TestModuleConfiguration.java");
    }

}
