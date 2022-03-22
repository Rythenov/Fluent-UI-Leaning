package com.jermyn.fluentuilearning.list.base;

import android.text.TextUtils;
import android.view.View;

import com.microsoft.fluentui.listitem.ListItemView;

public abstract class IListItem extends IBaseListItem{

    protected String subtitle;
    protected String footer;

    protected int titleMaxLines;
    protected int subtitleMaxLines;
    protected int footerMaxLines;

    protected TextUtils.TruncateAt titleTruncateAt;
    protected TextUtils.TruncateAt subtitleTruncateAt;
    protected TextUtils.TruncateAt footerTruncateAt;

    protected View customView;
    protected ListItemView.CustomViewSize customViewSize;
    protected View customAccessoryView;
    protected View customSecondarySubtitleView;

    protected ListItemView.LayoutDensity layoutDensity;


    public String getSubTitle(){
        return subtitle;
    }

    public void setSubtitle(String subtitle){
        this.subtitle = subtitle;
    }

    public String getFooter(){
        return footer;
    }

    public void setFooter(String footer){
        this.footer = footer;
    }

    public int getTitleMaxLines(){
        return titleMaxLines;
    }

    public void setTitleMaxLines(int maxLines){
        this.titleMaxLines = maxLines;
    }

    public int getSubtitleMaxLines(){
        return subtitleMaxLines;
    }

    public void setSubtitleMaxLines(int maxLines){
        this.subtitleMaxLines = maxLines;
    }

    public int getFooterMaxLines(){
        return  footerMaxLines;
    }

    public void setFooterMaxLines(int maxLines){
        this.footerMaxLines = maxLines;
    }

    public TextUtils.TruncateAt getTitleTruncateAt(){
        return titleTruncateAt;
    }

    public void setTitleTruncateAt(TextUtils.TruncateAt at){
        this.titleTruncateAt = at;
    }

    public TextUtils.TruncateAt getSubtitleTruncateAt(){
        return subtitleTruncateAt;
    }

    public void setSubtitleTruncateAt(TextUtils.TruncateAt at){
        this.subtitleTruncateAt = at;
    }

    public TextUtils.TruncateAt getFooterTruncateAt(){
        return footerTruncateAt;
    }

    public void setFooterTruncateAt(TextUtils.TruncateAt at){
        this.footerTruncateAt = at;
    }

    public View getCustomView(){
        return customView;
    }

    public void setCustomView(View view){
        this.customView = view;
    }

    public ListItemView.CustomViewSize getCustomViewSize(){
        return customViewSize;
    }

    public void setCustomViewSize(ListItemView.CustomViewSize size){
        this.customViewSize = size;
    }

    public View getCustomAccessoryView(){
        return customAccessoryView;
    }

    public void setCustomAccessoryView(View view){
        this.customAccessoryView = view;
    }

    public View getCustomSecondarySubtitleView(){
        return customSecondarySubtitleView;
    }

    public void setCustomSecondarySubtitleView(View view){
        this.customSecondarySubtitleView = view;
    }

    public ListItemView.LayoutDensity getLayoutDensity(){
        return layoutDensity;
    }
    public void setLayoutDensity(ListItemView.LayoutDensity density){
        this.layoutDensity = density;
    }
}
