package com.jermyn.fluentuilearning.list.base;

import android.view.View;

public abstract class IButtonItem extends IBaseListItem{

    protected String buttonText;
    protected int id;
    protected View.OnClickListener onClickListener;

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String text) {
        this.buttonText = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        onClickListener = onClickListener;
    }


}
