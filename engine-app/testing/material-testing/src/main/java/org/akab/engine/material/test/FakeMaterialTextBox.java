package org.akab.engine.material.test;

import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialValueBox;

public class FakeMaterialTextBox extends MaterialTextBox {
    public static MaterialLabel makeLabel(){
        return new MaterialLabel(){

            private String text;
            @Override
            public String getText() {
                return text;
            }

            @Override
            public void setText(String text) {
                this.text = text;
            }
        };
    }
    private String text;
    private MaterialLabel errorLabel;

    public FakeMaterialTextBox() {
        errorLabel=makeLabel();
    }

    private ErrorMixin errorMixin=new ErrorMixin<MaterialValueBox, MaterialLabel>(this, errorLabel){
        @Override
        public void setError(String error) {
            errorLabel.setText(error);
        }
    };

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setError(String error) {
        errorMixin.setError(error);
    }

    @Override
    protected ErrorMixin<AbstractValueWidget, MaterialLabel> getErrorMixin() {
        return errorMixin;
    }

    public String getError() {
        return errorLabel.getText();
    }
}
