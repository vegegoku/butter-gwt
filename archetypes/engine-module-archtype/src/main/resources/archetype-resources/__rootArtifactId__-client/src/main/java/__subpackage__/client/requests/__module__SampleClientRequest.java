#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.client.requests;

import org.akab.engine.core.api.client.request.ClientRequest;
import ${package}.${subpackage}.client.presenters.${module}Presenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

import org.akab.engine.core.api.client.annotations.Request;
import ${package}.${subpackage}.client.presenters.${module}Presenter;

@Request
public class ${module}SampleClientRequest extends ClientRequest<${module}Presenter> {

    private final MainExtensionPoint mainExtensionPoint;

    public ${module}SampleClientRequest(MainExtensionPoint mainExtensionPoint) {
        this.mainExtensionPoint=mainExtensionPoint;
    }

    @Override
    protected void process(${module}Presenter presenter) {
        presenter.contributeToMainModule(mainExtensionPoint, "Hello world! from login contribution request");
    }
}