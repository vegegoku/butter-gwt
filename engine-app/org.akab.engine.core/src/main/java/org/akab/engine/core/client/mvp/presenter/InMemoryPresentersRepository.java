package org.akab.engine.core.client.mvp.presenter;

import org.akab.engine.core.api.client.mvp.presenter.PresenterHolder;
import org.akab.engine.core.api.client.mvp.presenter.PresentersRepository;

import java.util.HashMap;
import java.util.logging.Logger;

public class InMemoryPresentersRepository implements PresentersRepository {

    private static final Logger logger= Logger.getLogger(InMemoryPresentersRepository.class.getName());

    private final HashMap<String, PresenterHolder> presenters=new HashMap<>();
    private final HashMap<String, String> names=new HashMap<>();

    @Override
    public void clear() {
        presenters.clear();
    }

    @Override
    public void registerPresenter(PresenterHolder presenterHolder) {
        if(isRegisteredPresenter(presenterHolder.getName()))
            throw new PresenterCannotBeRegisteredMoreThanOnce(presenterHolder.getName());
        presenters.put(presenterHolder.getName(), presenterHolder);
        names.put(presenterHolder.getConcreteName(), presenterHolder.getName());

    }

    @Override
    public PresenterHolder getPresenter(String presenterName) {
        if(isRegisteredPresenter(presenterName))
            return presenters.get(presenterName);
        throw new PresenterNotFoundException(presenterName);
    }

    @Override
    public String getNameFromConcreteName(String concreteName) {
        if(isRegisteredName(concreteName))
            return names.get(concreteName);
        throw new PresenterNotFoundException(concreteName);
    }

    private boolean isRegisteredPresenter(String presenterName) {
        return presenters.containsKey(presenterName);
    }

    private boolean isRegisteredName(String concreteName) {
        return names.containsKey(concreteName);
    }

}
