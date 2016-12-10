#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.shared.request;

import org.akab.engine.core.api.shared.request.ServerRequest;

public class ${module}Args extends ServerRequest {

    private String message;

    public ${module}Args() {
    }

    public ${module}Args(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
