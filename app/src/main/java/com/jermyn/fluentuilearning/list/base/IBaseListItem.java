package com.jermyn.fluentuilearning.list.base;

public abstract class IBaseListItem {

    protected String title;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}