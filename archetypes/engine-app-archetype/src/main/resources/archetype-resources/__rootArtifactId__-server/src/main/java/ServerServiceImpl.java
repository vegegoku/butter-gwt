#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.akab.engine.core.api.shared.request.Response;
import org.akab.engine.core.api.shared.request.ServerArgs;
import org.akab.engine.core.api.shared.service.ServerService;

import java.util.logging.Logger;

public class ServerServiceImpl extends RemoteServiceServlet implements ServerService {

    private static final long serialVersionUID = 9016180344293259071L;

    private static final Logger LOGGER=Logger.getLogger(ServerServiceImpl.class.getName());

    @Override
    public Response executeRequest(ServerArgs arguments) throws Exception {
        LOGGER.info("Server call recieved for request : "+arguments.toString());
        return null;
    }
}
