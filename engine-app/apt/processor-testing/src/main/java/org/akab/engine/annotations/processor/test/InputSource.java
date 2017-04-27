package org.akab.engine.annotations.processor.test;

import javax.annotation.processing.Processor;

public interface InputSource {
    BaseTargetProcessor withProcessor(Processor processor);
}
