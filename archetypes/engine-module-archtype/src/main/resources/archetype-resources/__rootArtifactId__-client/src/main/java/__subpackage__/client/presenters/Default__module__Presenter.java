#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.client.presenters;

import org.akab.engine.core.api.client.annotations.Presenter;
import org.akab.engine.core.api.client.mvp.presenter.BaseClientPresenter;
import ${package}.${subpackage}.client.views.${module}View;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

@Presenter
public class Default${module}Presenter extends BaseClientPresenter<${module}View> implements ${module}Presenter {

    @Override
    public void contributeToMainModule(MainExtensionPoint mainExtensionPoint, String welcomeMessage) {
        view.setWelcomeMessage(welcomeMessage);
        mainExtensionPoint.context().appendWidgetToRoot(view);
    }
}