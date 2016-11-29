package org.akab.engine.app;

import org.akab.engine.annotations.processor.utils.BaseProcessor;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

public class CoreAnnotationProcessor extends BaseProcessor{
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
