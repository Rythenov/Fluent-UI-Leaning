package com.jermyn.fluentuilearning.list.base;

import android.view.View;

import com.microsoft.fluentui.listitem.ListSubHeaderView;

public abstract class IListSubHeader extends IBaseListItem{
    protected ListSubHeaderView.TitleColor titleColor;
    protected View customAccessoryView;

    public ListSubHeaderView.TitleColor getTitleColor(){
        return titleColor;
    }
    public void setTitleColor(ListSubHeaderView.TitleColor color){
        this.titleColor = color;
    }

    public View getCustomAccessoryView(){
        return customAccessoryView;
    }
    public void setCustomAccessoryView(View view){
        this.customAccessoryView = view;
    }
}
