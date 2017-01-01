#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.client;

import ${package}.${subpackage}.client.presenters.Default${module}Presenter;
import org.akab.engine.core.api.shared.extension.MainExtensionPoint;

public class ${module}PresenterSpy extends Default${module}Presenter{

    private boolean contributionCompleted;

    @Override
    public void contributeToMainModule(MainExtensionPoint mainExtensionPoint, String welcomeMessage) {
        super.contributeToMainModule(mainExtensionPoint, welcomeMessage);
        this.contributionCompleted=true;
    }

    public boolean isContributionCompleted() {
        return contributionCompleted;
    }

    @Override
    protected String getConcrete() {
        return Default${module}Presenter.class.getCanonicalName();
    }
}
