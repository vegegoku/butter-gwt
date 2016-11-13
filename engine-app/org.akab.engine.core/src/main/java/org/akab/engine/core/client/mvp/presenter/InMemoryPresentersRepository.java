package org.akab.engine.core.client.mvp.presenter;

import org.akab.engine.core.api.client.mvp.presenter.PresenterHolder;
import org.akab.engine.core.api.client.mvp.presenter.PresentersRepository;

import java.util.HashMap;

public class InMemoryPresentersRepository implements PresentersRepository {

    private final HashMap<String, PresenterHolder> presenters=new HashMap<>();

    @Override
    public void clear() {
        presenters.clear();
    }

    @Override
    public void registerPresenter(PresenterHolder presenterHolder) {
        if(isRegisteredPresenter(presenterHolder.getName()))
            throw new PresenterCannotBeRegisteredMoreThanOnce(presenterHolder.getName());
        presenters.put(presenterHolder.getName(), presenterHolder);
    }

    @Override
    public PresenterHolder getPresenter(String presenterName) {
        if(isRegisteredPresenter(presenterName))
            return presenters.get(presenterName);
        throw new PresenterNotFoundException(presenterName);
    }

    private boolean isRegisteredPresenter(String presenterName) {
        return presenters.containsKey(presenterName);
    }
}
