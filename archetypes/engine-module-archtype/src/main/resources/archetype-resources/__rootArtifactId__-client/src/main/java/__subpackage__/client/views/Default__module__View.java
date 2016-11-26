#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class Default${module}View extends Composite implements ${module}View{

    interface Default${module}ViewUiBinder extends UiBinder<HTMLPanel, Default${module}View> {
    }

    private static Default${module}ViewUiBinder ourUiBinder = GWT.create(Default${module}ViewUiBinder.class);

    @UiField
    DivElement mainDiv;

    public Default${module}View() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void setWelcomeMessage(String welcomeMessage) {
        mainDiv.setInnerText(welcomeMessage);
    }
}