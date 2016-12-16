package org.akab.engine.annotations.processor.test;

import com.google.testing.compile.CompileTester;
import com.google.testing.compile.JavaFileObjects;

import javax.annotation.processing.Processor;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import java.nio.charset.Charset;

public abstract class BaseTargetProcessor {
    private CompileTester.SuccessfulCompilationClause successfulCompilationClause;

    public void generates(String result, String... rest) {
        compilesWithoutErrors()
                .and()
                .generatesSources(JavaFileObjects.forSourceString("", result), asJavaFileObjectsArray(rest));
    }

    private JavaFileObject[] asJavaFileObjectsArray(String... rest) {
        JavaFileObject[] result = new JavaFileObject[rest.length];
        for (int i = 0; i < rest.length; i++)
            result[i] = JavaFileObjects.forSourceString("", rest[i]);
        return result;
    }

    public abstract CompileTester.SuccessfulCompilationClause compilesWithoutErrors();
    public abstract CompileTester.UnsuccessfulCompilationClause failsToCompile();

    public void generatesResource(String basePackage, String fileName, String content) {
        compilesWithoutErrors()
                .and()
                .generatesFileNamed(StandardLocation.SOURCE_OUTPUT, basePackage, fileName).withStringContents(Charset.defaultCharset(), content);
    }
}
