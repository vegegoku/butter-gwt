package org.akab.rafa.hello.world.client.contributions;

import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;

@Contribute(extensionPoint = MainExtensionPoint.class)
public class HelloWorldContributionToMain implements Contribution<MainExtensionPoint> {

    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(HelloWorldContributionToMain.class);

    public static MainExtensionPoint extensionPoint;

    @Override
    public void contribute(MainExtensionPoint extensionPoint) {
        LOGGER.info("contributing to Main extension point");
        HelloWorldContributionToMain.extensionPoint=extensionPoint;
    }
}
