package com.jermyn.fluentuilearning.list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jermyn.fluentuilearning.R;
import com.jermyn.fluentuilearning.list.base.IBaseListItem;
import com.jermyn.fluentuilearning.list.base.IButtonItem;
import com.jermyn.fluentuilearning.list.base.IListItem;
import com.jermyn.fluentuilearning.list.base.IListSubHeader;
import com.microsoft.fluentui.listitem.ListItemView;
import com.microsoft.fluentui.listitem.ListSubHeaderView;
import com.microsoft.fluentui.snackbar.Snackbar;
import com.microsoft.fluentui.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private enum ViewType {
        SUB_HEADER, ITEM, BUTTON_ITEM
    }

    List<IBaseListItem> listItems = new ArrayList<>();

    Context context;

    public ListAdapter(Context context){
        this.context = context;
    }

    public void setListItems(List<IBaseListItem> list){
        listItems = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        switch (ViewType.values()[viewType]){
            case SUB_HEADER:{
                ListSubHeaderView listSubHeaderView = new ListSubHeaderView(context);
                listSubHeaderView.setLayoutParams(lp);
                return new ListSubHeaderViewHolder(listSubHeaderView);
            }
            case ITEM:{
                ListItemView listItemView = new ListItemView(context);
                listItemView.setLayoutParams(lp);
                return new ListItemViewHolder(listItemView);
            }
            case BUTTON_ITEM:{
                FrameLayout buttonItemView = new FrameLayout(context);
                buttonItemView.setLayoutParams(lp);

                Button button = new Button(context);
                button.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT
                        , (int)context.getResources().getDimension(com.microsoft.fluentui.R.dimen.fluentui_button_min_height)));
                button.setLayoutParams(new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        (int)context.getResources().getDimension(com.microsoft.fluentui.R.dimen.fluentui_button_min_height)));
                buttonItemView.addView(button);

                int paddingHorizontal = (int)context.getResources().getDimension(R.dimen.default_layout_margin);
                int paddingVertical = (int)context.getResources().getDimension(R.dimen.button_list_item_vertical_padding);
                buttonItemView.setPaddingRelative(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);

                return new ButtonItemViewHolder(buttonItemView);
            }

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        IBaseListItem listItem = listItems.get(position);

        if (listItem instanceof IListSubHeader) {
            if (holder instanceof ListSubHeaderViewHolder){
                ((ListSubHeaderViewHolder) holder).setListSubHeader((IListSubHeader) listItem);
            }
        }

        if (listItem instanceof IListItem) {
            if (holder instanceof ListItemViewHolder){
                ((ListItemViewHolder) holder).setListItem((IListItem) listItem);
            }
        }
        if (listItem instanceof IButtonItem) {
            if (holder instanceof ButtonItemViewHolder){
                ((ButtonItemViewHolder) holder).setButtonItem((IButtonItem) listItem);
            }
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof ListItemViewHolder){
            ((ListItemViewHolder) holder).clearCustomViews();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (listItems.get(position) instanceof IListSubHeader){
            return ViewType.SUB_HEADER.ordinal();
        } else if (listItems.get(position) instanceof IButtonItem){
            return ViewType.BUTTON_ITEM.ordinal();
        } else {
            return ViewType.ITEM.ordinal();
        }
    }

    private class ListItemViewHolder extends RecyclerView.ViewHolder {
        private ListItemView listItemView;

        ListItemViewHolder(ListItemView view){
            super(view);
            listItemView = view;
            listItemView.setOnClickListener(v ->{
                Snackbar.Companion.make(listItemView, context.getString(R.string.list_item_click), Snackbar.LENGTH_SHORT, Snackbar.Style.REGULAR).show();
            });
        }

        void setListItem(IListItem listItem) {
            listItemView.setTitle(listItem.getTitle());
            listItemView.setSubtitle(listItem.getSubTitle());
            listItemView.setFooter(listItem.getFooter());

            listItemView.setTitleMaxLines(listItem.getTitleMaxLines());
            listItemView.setSubtitleMaxLines(listItem.getSubtitleMaxLines());
            listItemView.setFooterMaxLines(listItem.getFooterMaxLines());

            listItemView.setTitleTruncateAt(listItem.getTitleTruncateAt());
            listItemView.setSubtitleTruncateAt(listItem.getSubtitleTruncateAt());
            listItemView.setFooterTruncateAt(listItem.getFooterTruncateAt());

            listItemView.setCustomView(listItem.getCustomView());
            listItemView.setCustomViewSize(listItem.getCustomViewSize());
            listItemView.setCustomAccessoryView(listItem.getCustomAccessoryView());
            listItemView.setCustomSecondarySubtitleView(listItem.getCustomSecondarySubtitleView());
            listItemView.setLayoutDensity(listItem.getLayoutDensity());
        }

        void clearCustomViews() {
            listItemView.setCustomView(null);
            listItemView.setCustomAccessoryView(null);
        }
    }

    private class ListSubHeaderViewHolder extends RecyclerView.ViewHolder {

        private ListSubHeaderView listSubHeaderView;

        ListSubHeaderViewHolder(ListSubHeaderView view){
            super(view);
            listSubHeaderView = view;
        }

        void setListSubHeader(IListSubHeader listSubHeader) {
            listSubHeaderView.setTitle(listSubHeader.getTitle());
            listSubHeaderView.setTitleColor(listSubHeader.getTitleColor());
            listSubHeaderView.setCustomAccessoryView(listSubHeader.getCustomAccessoryView());
        }
    }

    private class ButtonItemViewHolder extends RecyclerView.ViewHolder {
        private Button button;

        ButtonItemViewHolder(FrameLayout view){
            super(view);
            View childView = view.getChildAt(0);
            if (childView != null && childView instanceof Button){
                button = (Button) childView;
            }

        }

        void setButtonItem(IButtonItem buttonItem) {
            button.setText(buttonItem.getButtonText());
            button.setId(buttonItem.getId());
            button.setOnClickListener(buttonItem.getOnClickListener());
        }
    }

    private static void setListItem(ListItemView liv, IListItem li){
        liv.setTitle(li.getTitle());
        liv.setSubtitle(li.getSubTitle());
        liv.setFooter(li.getFooter());

        liv.setTitleMaxLines(li.getTitleMaxLines());
        liv.setSubtitleMaxLines(li.getSubtitleMaxLines());
        liv.setFooterMaxLines(li.getFooterMaxLines());

        liv.setTitleTruncateAt(li.getTitleTruncateAt());
        liv.setSubtitleTruncateAt(li.getSubtitleTruncateAt());
        liv.setFooterTruncateAt(li.getFooterTruncateAt());

        liv.setCustomView(li.getCustomView());
        liv.setCustomViewSize(li.getCustomViewSize());
        liv.setCustomAccessoryView(li.getCustomAccessoryView());
        liv.setCustomSecondarySubtitleView(li.getCustomSecondarySubtitleView());
        liv.setLayoutDensity(li.getLayoutDensity());
    }

    private static void setSubHeader(ListSubHeaderView lshv, IListSubHeader lsh){
        lshv.setTitle(lsh.getTitle());
        lshv.setTitleColor(lsh.getTitleColor());
        lshv.setCustomAccessoryView(lsh.getCustomAccessoryView());
    }
}



