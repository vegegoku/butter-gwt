package org.akab.engine.core.annotation.processor.server.multiGlobalInterceptors;

import com.google.auto.service.AutoService;
import org.akab.engine.core.api.shared.server.ServerModuleConfiguration;
import org.akab.engine.core.api.shared.server.InterceptorsRegistry;
import org.akab.engine.core.annotation.processor.server.multiGlobalInterceptors.FirstGlobalInterceptor;
import org.akab.engine.core.annotation.processor.server.multiGlobalInterceptors.TestServerEntryPointContext;
import org.akab.engine.core.annotation.processor.server.multiGlobalInterceptors.SecondGlobalInterceptor;
import org.akab.engine.core.annotation.processor.server.multiGlobalInterceptors.ThirdGlobalInterceptor;

@AutoService(ServerModuleConfiguration.class)
public class TestServerModule implements ServerModuleConfiguration{

    @Override
    public void registerGlobalInterceptors(InterceptorsRegistry registry) {
        registry.registerGlobalInterceptor(TestServerEntryPointContext.class.getCanonicalName(), new FirstGlobalInterceptor());
	    registry.registerGlobalInterceptor(TestServerEntryPointContext.class.getCanonicalName(), new SecondGlobalInterceptor());
	    registry.registerGlobalInterceptor(TestServerEntryPointContext.class.getCanonicalName(), new ThirdGlobalInterceptor());
    }
}
