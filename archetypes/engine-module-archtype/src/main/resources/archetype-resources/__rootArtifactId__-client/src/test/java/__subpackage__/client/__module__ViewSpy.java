#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.client;

import ${package}.${subpackage}.client.views.Default${module}View;

public class ${module}ViewSpy extends Default${module}View {

    private String welcomeMessage;

    @Override
    public void setWelcomeMessage(String welcomeMessage) {
        super.setWelcomeMessage(welcomeMessage);
        this.welcomeMessage=welcomeMessage;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}
