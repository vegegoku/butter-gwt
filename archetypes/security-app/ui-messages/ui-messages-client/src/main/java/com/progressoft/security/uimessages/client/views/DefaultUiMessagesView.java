package com.progressoft.security.uimessages.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTitle;
import org.akab.engine.core.api.client.annotations.UiView;

import com.progressoft.security.uimessages.client.presenters.UiMessagesPresenter;

import java.util.ArrayList;
import java.util.List;

@UiView(presentable = UiMessagesPresenter.class)
public class DefaultUiMessagesView extends Composite implements UiMessagesView{

    interface DefaultUiMessagesViewUiBinder extends UiBinder<MaterialRow, DefaultUiMessagesView> {
    }

    private static DefaultUiMessagesViewUiBinder ourUiBinder = GWT.create(DefaultUiMessagesViewUiBinder.class);

    @UiField
    MaterialRow root;

    @UiField
    MaterialModal errorDialog;

    @UiField
    MaterialTitle errorDialogTitle;

    public DefaultUiMessagesView() {
        root=ourUiBinder.createAndBindUi(this);
        initWidget(root);
        root.getElement().getStyle().setMarginBottom(0, Style.Unit.PX);
    }

    @Override
    public void showError(String message, String details) {
        errorDialogTitle.setTitle(message);
        errorDialogTitle.setDescription(details);
        errorDialog.open();
    }


    @UiHandler("errorDialog")
    void onEscapeKey(KeyPressEvent event) {
        if(event.getNativeEvent().getKeyCode()== KeyCodes.KEY_ESCAPE)
            errorDialog.close();
    }

}