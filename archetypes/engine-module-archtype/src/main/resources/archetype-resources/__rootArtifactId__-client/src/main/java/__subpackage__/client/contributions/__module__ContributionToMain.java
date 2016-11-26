#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.client.contributions;

import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;
import ${package}.${subpackage}.client.requests.${module}SampleClientRequest;

@Contribute(extensionPoint = MainExtensionPoint.class)
public class ${module}ContributionToMain implements Contribution<MainExtensionPoint> {
    @Override
    public void contribute(MainExtensionPoint extensionPoint) {
        new ${module}SampleClientRequest(extensionPoint).send();
    }
}
