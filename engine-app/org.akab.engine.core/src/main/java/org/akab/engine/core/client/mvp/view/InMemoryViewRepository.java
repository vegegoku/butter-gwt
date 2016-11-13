package org.akab.engine.core.client.mvp.view;

import org.akab.engine.core.api.client.mvp.view.ViewHolder;
import org.akab.engine.core.api.client.mvp.view.ViewsRepository;

import java.util.HashMap;

public class InMemoryViewRepository implements ViewsRepository {

    private final HashMap<String, ViewHolder> views=new HashMap<>();

    @Override
    public void registerView(ViewHolder viewHolder) {
        if(isRegisteredPresenterView(viewHolder.getPresenterName()))
            throw new ViewsRepository.ViewCannotBeRegisteredMoreThanOnce(viewHolder.getPresenterName());
        views.put(viewHolder.getPresenterName(), viewHolder);
    }

    @Override
    public ViewHolder getView(String presenterName) {
        if(isRegisteredPresenterView(presenterName))
            return views.get(presenterName);
        throw new ViewsRepository.ViewNotFoundException(presenterName);
    }

    private boolean isRegisteredPresenterView(String presenterName) {
        return views.containsKey(presenterName);
    }

    @Override
    public void clear() {
        views.clear();
    }
}
