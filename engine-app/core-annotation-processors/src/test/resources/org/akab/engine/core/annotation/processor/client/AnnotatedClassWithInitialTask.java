package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.core.api.client.InitializeTask;
import org.akab.engine.core.api.client.annotations.InitialTask;

@InitialTask
public class AnnotatedClassWithInitialTask implements InitializeTask {

    @Override
    public void execute() {
    }
}