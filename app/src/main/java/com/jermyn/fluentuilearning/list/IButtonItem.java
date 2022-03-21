package com.jermyn.fluentuilearning.list;

import android.view.View;

public interface IButtonItem extends IBaseListItem{
    String getButtonText();
    void setButtonText();
    int getId();
    void setId(int id);
    View.OnClickListener getOnClickListener();
    void setOnClickListener(View.OnClickListener listener);


}
