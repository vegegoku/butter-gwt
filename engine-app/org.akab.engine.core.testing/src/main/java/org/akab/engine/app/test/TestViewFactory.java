package org.akab.engine.app.test;

import org.akab.engine.core.api.client.mvp.view.View;

@FunctionalInterface
public interface TestViewFactory {
    View make();
}
