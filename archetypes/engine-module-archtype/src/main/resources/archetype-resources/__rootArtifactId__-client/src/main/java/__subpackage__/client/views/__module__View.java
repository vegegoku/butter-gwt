#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.client.views;

import org.akab.engine.core.api.client.mvp.view.View;

public interface ${module}View extends View{
    void setWelcomeMessage(String welcomeMessage);
}