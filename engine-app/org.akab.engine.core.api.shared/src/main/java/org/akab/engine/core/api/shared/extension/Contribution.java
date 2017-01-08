package org.akab.engine.core.api.shared.extension;

@FunctionalInterface
public interface Contribution<E extends ExtensionPoint> {
    void contribute(E extensionPoint);
}
