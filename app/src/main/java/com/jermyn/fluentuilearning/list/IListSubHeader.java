package com.jermyn.fluentuilearning.list;

import android.view.View;

import com.microsoft.fluentui.listitem.ListSubHeaderView;

public interface IListSubHeader {
    ListSubHeaderView.TitleColor getTitleColor();
    void setTitleColor(ListSubHeaderView.TitleColor color);

    View getCustomAccessoryView();
    void setCustomAccessoryView(View view);
}
