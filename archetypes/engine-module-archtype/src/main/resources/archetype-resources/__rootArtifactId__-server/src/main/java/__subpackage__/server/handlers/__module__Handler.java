#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.server.handlers;

import org.akab.engine.core.api.shared.server.Handler;
import org.akab.engine.core.api.shared.server.RequestHandler;
import ${package}.${subpackage}.shared.response.${module}Response;
import ${package}.${subpackage}.shared.request.${module}Request;

import java.util.logging.Logger;

@Handler
public class ${module}Handler implements RequestHandler<${module}Request, ${module}Response> {
    private static final Logger LOGGER= Logger.getLogger(${module}Handler.class.getName());
    @Override
    public ${module}Response handleRequest(${module}Request request) {
        LOGGER.info("message recieved from client : "+request.getMessage());
        return new ${module}Response("Server message");
    }
}
