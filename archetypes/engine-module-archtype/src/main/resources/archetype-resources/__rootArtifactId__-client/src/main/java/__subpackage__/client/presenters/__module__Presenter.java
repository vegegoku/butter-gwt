#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.client.presenters;

import org.akab.engine.core.api.client.mvp.presenter.Presentable;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public interface ${module}Presenter extends Presentable{
    void contributeToMainModule(MainExtensionPoint mainExtensionPoint, String welcomeMessage);
}