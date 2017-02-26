package com.progressoft.security.app.layout.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.progressoft.security.app.layout.client.presenters.AppLayoutPresenter;
import com.progressoft.security.app.layout.shared.extension.FabHandler;
import com.progressoft.security.app.layout.shared.extension.LayoutItem;
import gwt.material.design.client.ui.*;
import org.akab.engine.core.api.client.annotations.UiView;

import java.util.ArrayDeque;
import java.util.Deque;

import static java.util.Objects.nonNull;

@UiView(presentable = AppLayoutPresenter.class)
public class DefaultLayoutView extends Composite implements LayoutView{

    interface DefaultLayoutViewUiBinder extends UiBinder<HTMLPanel, DefaultLayoutView> {
    }

    private static DefaultLayoutViewUiBinder ourUiBinder = GWT.create(DefaultLayoutViewUiBinder.class);

    private FabHandler fabHandler;

    @UiField
    MaterialNavSection headerBar;

    @UiField
    MaterialSideNav menuPanel;

    @UiField
    MaterialRow rightPanel;

    @UiField
    MaterialContainer mainContainer;

    @UiField
    MaterialButton fabButton;

    @UiField
    MaterialFAB fab;

    @UiField
    MaterialFABList fabList;

    public DefaultLayoutView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    @Override
    public void addHeaderItem(LayoutItem headerItem) {
        Deque<Widget> widgets=new ArrayDeque<>();
        int widgetsCount=headerBar.getWidgetCount();
        for(int i=0;i<widgetsCount;i++){
            widgets.push(headerBar.getWidget(i));
        }
        headerBar.clear();
        headerBar.add(headerItem);
        while(widgets.peek()!=null){
            headerBar.add(widgets.pop());
        }
    }

    @Override
    public void addMenuItem(LayoutItem menuItem, int beforeIndex) {
        if(beforeIndex>-1)
            menuPanel.insert(menuItem.asWidget(), beforeIndex);
        else
            menuPanel.add(menuItem);
    }

    @Override
    public void showContent(LayoutItem contentItem) {
        mainContainer.clear();
        mainContainer.add(contentItem);
    }

    @Override
    public void showRightPanel() {
        rightPanel.setVisibility(Style.Visibility.VISIBLE);
    }

    @Override
    public void hideRightPanel() {
        rightPanel.setVisibility(Style.Visibility.HIDDEN);
    }

    @Override
    public void setFabHandler(FabHandler fabHandler) {
        if(nonNull(fabHandler)) {
            fabButton.setVisibility(Style.Visibility.VISIBLE);
            this.fabHandler = fabHandler;
        }
    }

    @UiHandler("fabButton")
    void onFabButtonClick(ClickEvent event){
        fireFabClick();
    }

    protected void fireFabClick() {
        if(nonNull(fabHandler))
            fabHandler.onFabClick();
    }

    @Override
    public void addFabItem(LayoutItem fabItem) {
        fabList.add(fabItem);
    }

    @Override
    public void setSideContent(LayoutItem content) {
        rightPanel.add(content);
    }

}