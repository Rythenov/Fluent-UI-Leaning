package com.jermyn.fluentuilearning.list;

import android.view.View;

import com.jermyn.fluentuilearning.list.base.IButtonItem;

public class ButtonItem extends IButtonItem {

    public ButtonItem(String title, String buttonText, int id, View.OnClickListener onClickListener){
        this.title = title;
        this.buttonText = buttonText;
        this.id = id;
        this.onClickListener = onClickListener;
    }

    public ButtonItem(String buttonText, int id, View.OnClickListener onClickListener){
        this.title = "";
        this.buttonText = buttonText;
        this.id = id;
        this.onClickListener = onClickListener;
    }
}
