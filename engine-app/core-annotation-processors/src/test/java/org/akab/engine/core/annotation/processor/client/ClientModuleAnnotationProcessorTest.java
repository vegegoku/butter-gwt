package org.akab.engine.core.annotation.processor.client;

import com.google.common.truth.Truth;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.akab.engine.annotations.processor.test.ProcessorAssert.assertProcessing;

public class ClientModuleAnnotationProcessorTest {

    private static final String BASE_PACKAGE = "org/akab/engine/core/annotation/processor/client/";

    private ClientModuleAnnotationProcessor processor() {
        return new ClientModuleAnnotationProcessor();
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

    @Test
    public void givenClassAnnotatedWithPresenter_ShouldAppendPresenterRegistrationToModuleConfiguration() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithClientModuleWithPresenterRegistration.java",
                BASE_PACKAGE + "DefaultAnnotatedClassWithPresenter.java",
                BASE_PACKAGE + "PresenterInterface.java")
                .withProcessor(processor())
                .generates(getExpectedResultFileContent("PresenterRegistrationModuleConfiguration.java"));
    }

    @Test
    public void givenTwoClassesAnnotatedWithPresenter_ShouldAppendPresentersRegistrationsToModuleConfiguration() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithClientModuleWithPresentersRegistrations.java",
                BASE_PACKAGE + "FirstAnnotatedClassWithPresenter.java",
                BASE_PACKAGE + "SecondAnnotatedClassWithPresenter.java",
                BASE_PACKAGE + "FirstPresenterInterface.java",
                BASE_PACKAGE + "SecondPresenterInterface.java")
                .withProcessor(processor())
                .generates(getExpectedResultFileContent("PresentersRegistrationsModuleConfiguration.java"));
    }

    @Test
    public void givenClassAnnotatedWithPresenterAndNotImplementsPresentableInterface_WhenProcess_ShouldThrowException() throws Exception {
        try {
            assertProcessing(BASE_PACKAGE + "AnnotatedClassWithClientModuleWithPresenterRegistration.java",
                    BASE_PACKAGE + "InvalidPresenterClass.java")
                    .withProcessor(processor())
                    .compilesWithoutErrors();
            Truth.THROW_ASSERTION_ERROR.fail("Should throw RuntimeException with message Invalid presenter");
        } catch (RuntimeException e) {
            Truth.assertThat(e).hasMessage("java.lang.RuntimeException: Invalid presenter");
        }
    }

    @Test
    public void givenClassAnnotatedWithUiView_WhenProcess_ShouldAddRegistrationLineToModuleConfiguration() throws Exception {
        assertProcessing(BASE_PACKAGE + "AnnotatedClassWithUiView.java",
                BASE_PACKAGE + "AnnotatedClassWithClientModuleWithUiViewRegistration.java",
                BASE_PACKAGE + "PresenterInterface.java")
                .withProcessor(processor())
                .generates(getExpectedResultFileContent("UiViewRegistrationModuleConfiguration.java"));

    }

    @Test
    public void givenClassAnnotatedWithUiViewAndNotImplementsViewInterface_WhenProcess_ShouldThrowException() throws Exception {
        try {
            assertProcessing(BASE_PACKAGE + "AnnotatedClassWithClientModuleWithUiViewRegistration.java",
                    BASE_PACKAGE + "InvalidViewClass.java",
                    BASE_PACKAGE + "PresenterInterface.java")
                    .withProcessor(processor())
                    .compilesWithoutErrors();
            Truth.THROW_ASSERTION_ERROR.fail("Should throw RuntimeException with message Invalid view");
        } catch (RuntimeException e) {
            Truth.assertThat(e).hasMessage("java.lang.RuntimeException: Invalid view");
        }
    }
}
