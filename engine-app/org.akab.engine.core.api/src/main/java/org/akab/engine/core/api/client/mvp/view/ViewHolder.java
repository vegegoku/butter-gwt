package org.akab.engine.core.api.client.mvp.view;

public class ViewHolder {

    private final String presenterName;
    private final View view;

    public ViewHolder(String presenterName, View view) {
        this.presenterName = presenterName;
        this.view = view;
    }

    public String getPresenterName() {
        return presenterName;
    }

    public View getView() {
        return view;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        return getPresenterName().equals(((ViewHolder) other).getPresenterName());
    }

    @Override
    public int hashCode() {
        return getPresenterName().hashCode();
    }
}
