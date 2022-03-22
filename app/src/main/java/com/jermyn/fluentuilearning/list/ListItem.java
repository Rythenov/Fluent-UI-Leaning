package com.jermyn.fluentuilearning.list;

import com.jermyn.fluentuilearning.list.base.IListItem;
import com.microsoft.fluentui.listitem.ListItemView;

public class ListItem extends IListItem {

    public ListItem(String title){
        this.title = title;
        this.subtitle = "";
        this.footer = "";

        this.titleMaxLines = ListItemView.DEFAULT_MAX_LINES;
        this.subtitleMaxLines = ListItemView.DEFAULT_MAX_LINES;
        this.footerMaxLines = ListItemView.DEFAULT_MAX_LINES;

        this.titleTruncateAt = ListItemView.Companion.getDEFAULT_TRUNCATION();
        this.subtitleTruncateAt = ListItemView.Companion.getDEFAULT_TRUNCATION();
        this.footerTruncateAt = ListItemView.Companion.getDEFAULT_TRUNCATION();

        this.customView = null;
        this.customViewSize = ListItemView.Companion.getDEFAULT_CUSTOM_VIEW_SIZE();
        this.customAccessoryView = null;
        this.customSecondarySubtitleView = null;

        this.layoutDensity = ListItemView.Companion.getDEFAULT_LAYOUT_DENSITY();
    }
}
