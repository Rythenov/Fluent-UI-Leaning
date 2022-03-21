package com.jermyn.fluentuilearning.list;

import android.text.TextUtils;
import android.view.View;

import com.microsoft.fluentui.listitem.ListItemView;

public interface IListItem extends IBaseListItem{
    String getSubTitle();
    void setSubtitle(String subtitle);

    String getFooter();
    void setFooter(String footer);

    int getTitleMaxLines();
    void setTitleMaxLines(int maxLines);

    int getSubtitleMaxLines();
    void setSubtitleMaxLines(int maxLines);

    int getFooterMaxLines();
    void setFooterMaxLines(int maxLines);

    TextUtils.TruncateAt getTitleTruncateAt();
    void setTitleTruncateAt(TextUtils.TruncateAt at);

    TextUtils.TruncateAt getSubtitleTruncateAt();
    void setSubtitleTruncateAt(TextUtils.TruncateAt at);

    TextUtils.TruncateAt getFooterTruncateAt();
    void setFooterTruncateAt(TextUtils.TruncateAt at);

    View getCustomView();
    void setCustomView(View view);

    ListItemView.CustomViewSize getCustomViewSize();
    void setCustomViewSize(ListItemView.CustomViewSize size);

    View getCustomAccessoryView();
    void setCustomAccessoryView(View view);

    View getCustomSecondarySubtitleView();
    void setCustomSecondarySubtitleView(View view);

    ListItemView.LayoutDensity getLayoutDensity();
    void setLayoutDensity(ListItemView.LayoutDensity density);
}
