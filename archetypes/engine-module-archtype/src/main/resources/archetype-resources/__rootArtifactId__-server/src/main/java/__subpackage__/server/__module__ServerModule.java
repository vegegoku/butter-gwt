#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.server;

import com.google.auto.service.AutoService;
import org.akab.engine.core.api.shared.server.HandlerRegistry;
import org.akab.engine.core.api.shared.server.InterceptorsRegistry;
import org.akab.engine.core.api.shared.server.ServerModuleConfiguration;
import ${package}.${subpackage}.server.handlers.${module}Handler;
import ${package}.${subpackage}.shared.request.${module}Args;

@AutoService(ServerModuleConfiguration.class)
public class ${module}ServerModule implements ServerModuleConfiguration{

    @Override
    public void registerHandlers(HandlerRegistry registry) {
        registry.registerHandler(${module}Args.class.getCanonicalName(), new ${module}Handler());
    }

    @Override
    public void registerInterceptors(InterceptorsRegistry registry) {

    }

    @Override
    public void registerGlobalInterceptors(InterceptorsRegistry registry) {

    }
}
