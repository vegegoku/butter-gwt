#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${subpackage}.client.views.bundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ExternalTextResource;
import com.google.gwt.resources.client.ImageResource;

public interface ${module}Bundle extends ClientBundle{

    ${module}Bundle INSTANCE=GWT.create(${module}Bundle.class);

    interface Style extends CssResource {
        String ${module}();
    }

    @Source("css/${module}.gss")
    Style style();

    @Source("images/gwt.png")
    ImageResource gwt();

    @Source("text/welcome.txt")
    ExternalTextResource welcome();

}