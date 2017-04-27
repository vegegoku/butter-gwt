package org.akab.engine.core.api.shared.extension;

@FunctionalInterface
public interface ExtensionPoint<C extends Context> {
    C context();
}
