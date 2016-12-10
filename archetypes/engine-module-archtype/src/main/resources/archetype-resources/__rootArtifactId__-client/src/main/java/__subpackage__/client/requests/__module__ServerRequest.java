#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.client.requests;

import org.akab.engine.core.api.client.request.ClientServerRequest;
import org.akab.engine.core.logger.client.CoreLogger;
import org.akab.engine.core.logger.client.CoreLoggerFactory;
import ${package}.${subpackage}.shared.response.${module}Response;
import ${package}.${subpackage}.shared.request.${module}Args;
import ${package}.${subpackage}.client.presenters.${module}Presenter;

public class ${module}ServerRequest extends ClientServerRequest<${module}Presenter, ${module}Args, ${module}Response> {

    private static final CoreLogger LOGGER= CoreLoggerFactory.getLogger(${module}ServerRequest.class);

    @Override
    protected void process(${module}Presenter presenter, ${module}Args serverArgs, ${module}Response response) {
        LOGGER.info("Message recieved from server : "+response.getServerMessage());
    }

    @Override
    public ${module}Args buildArguments() {
        return new ${module}Args("client message");
    }
}
