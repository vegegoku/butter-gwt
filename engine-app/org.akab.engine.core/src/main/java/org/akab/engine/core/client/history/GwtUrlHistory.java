package org.akab.engine.core.client.history;

import com.google.gwt.user.client.History;
import org.akab.engine.core.api.client.History.UrlHistory;

public class GwtUrlHistory implements UrlHistory{

    @Override
    public void apply(String urlToken) {
        History.replaceItem(urlToken, false);
    }
}
