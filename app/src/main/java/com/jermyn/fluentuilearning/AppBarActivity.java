package com.jermyn.fluentuilearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuWrapperICS;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.jermyn.fluentuilearning.list.ButtonItem;
import com.jermyn.fluentuilearning.list.ListAdapter;
import com.jermyn.fluentuilearning.list.ListItem;
import com.jermyn.fluentuilearning.list.ListSubHeader;
import com.jermyn.fluentuilearning.list.base.IBaseListItem;
import com.jermyn.fluentuilearning.utils.Avatar;
import com.jermyn.fluentuilearning.utils.Constrains;
import com.microsoft.fluentui.snackbar.Snackbar;
import com.microsoft.fluentui.appbarlayout.AppBarLayout;
import com.microsoft.fluentui.listitem.ListItemDivider;
import com.microsoft.fluentui.listitem.ListSubHeaderView;
import com.microsoft.fluentui.search.Searchbar;
import com.microsoft.fluentui.theming.FluentUIContextThemeWrapper;
import com.microsoft.fluentui.util.ThemeUtil;
import com.microsoft.fluentui.util.ThemeUtilsKt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kotlin.collections.CollectionsKt;

public class AppBarActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SCROLL_BEHAVIOR = "scrollBehavior";
    private static final String NAVIGATION_ICON_TYPE = "navigationIconType";
    private static final String SEARCHBAR_IS_ACTION_MENU_VIEW = "searchbarIsAc";
    private static final String SEARCHBAR_HAS_FOCUS = "searchbarHasFocus";
    private static final String SEARCHBAR_QUERY = "searchbarQuery";


    @BindView(R.id.root_view)
    CoordinatorLayout rootView;

    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;

    @BindView(R.id.app_bar_layout_list)
    RecyclerView recyclerView;

    //??????????????????
    enum NavigationIconType {
        NONE,
        AVATAR,
        BACK_ICON
    }

    //??????
    Menu optionsMenu;

    //????????????
    private void setOptionsMenu(Menu menu) {
        optionsMenu = menu;
        updateSearchbar();
        updateSearchbarFocus();
        updateSearchbarQuery();
    }

    //????????????
    AppBarLayout.ScrollBehavior scrollBehavior = AppBarLayout.ScrollBehavior.COLLAPSE_TOOLBAR;

    private void setScrollBehavior(AppBarLayout.ScrollBehavior scrollBehavior) {
        this.scrollBehavior = scrollBehavior;
        updateScrollBehavior();
    }

    //???????????????
    NavigationIconType navigationIconType = NavigationIconType.BACK_ICON;

    private void setNavigationIconType(NavigationIconType type) {
        navigationIconType = type;
        updateNavigationIcon();
    }

    //????????????????????????????????????????????????????????????
    boolean searchbarIsActionMenuView = false;

    private void setSearchbarIsActionMenuView(boolean var) {
        searchbarIsActionMenuView = var;
        updateSearchbar();
    }

    //???????????????????????????
    boolean searchbarHasFocus = false;

    private void setSearchbarHasFocus(boolean var) {
        searchbarHasFocus = var;
        updateSearchbarFocus();
    }


    String searchbarQuery = "";

    private void setSearchbarQuery(String str) {
        searchbarQuery = str;
        updateSearchbarQuery();
    }

    //?????????Adapter????????????IBaseListItem???????????????
    private ListAdapter listAdapter = new ListAdapter(this);

    //????????????????????????
    private ListSubHeader scrollBehaviorSubHeader;
    //?????????????????????
    private ButtonItem navigationIconButton;
    private ButtonItem searchbarButton;
    //Fluent ?????????
    private Searchbar searchbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //????????????
        setTheme(Constrains.THEME_ID);

        //?????????????????????????????????
        int scrollBehaviorOrdinal = scrollBehavior.ordinal();
        //???????????????????????????????????????
        int navigationIconTypeOrdinal = navigationIconType.ordinal();
        if (savedInstanceState != null) {
            scrollBehaviorOrdinal = savedInstanceState.getInt(SCROLL_BEHAVIOR);
            navigationIconTypeOrdinal = savedInstanceState.getInt(NAVIGATION_ICON_TYPE);
            //?????? ?????????????????????????????????????????????????????????
            setSearchbarIsActionMenuView(savedInstanceState.getBoolean(SEARCHBAR_IS_ACTION_MENU_VIEW));
            //?????? ???????????????????????????
            setSearchbarHasFocus(savedInstanceState.getBoolean(SEARCHBAR_HAS_FOCUS));
            //?????????????????????
            setSearchbarQuery(savedInstanceState.getString(SEARCHBAR_QUERY) == null ? savedInstanceState.getString(SEARCHBAR_QUERY) : "");
        }
        //?????? ????????????
        setScrollBehavior(AppBarLayout.ScrollBehavior.values()[scrollBehaviorOrdinal]);
        //?????? ?????????????????????
        setNavigationIconType(NavigationIconType.values()[navigationIconTypeOrdinal]);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_learning);
        ButterKnife.bind(this);
        setTitle("Fluent UI App Bar ?????????");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //??????SearchBar
        searchbar = createSearchbar();

        //??????RecycleView??????
        setupList();
        //????????????View
        appBarLayout.setScrollTargetViewId(R.id.app_bar_layout_list);

        //??????????????????
        updateScrollBehavior();
        //??????????????????
        updateNavigationIcon();
        //???????????????
        updateSearchbar();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        searchbarQuery = searchbar.getQuery().toString();

        outState.putInt(SCROLL_BEHAVIOR, scrollBehavior.ordinal());
        outState.putInt(NAVIGATION_ICON_TYPE, navigationIconType.ordinal());
        outState.putBoolean(SEARCHBAR_IS_ACTION_MENU_VIEW, searchbarIsActionMenuView);
        outState.putBoolean(SEARCHBAR_HAS_FOCUS, searchbarHasFocus);
        outState.putString(SEARCHBAR_QUERY, searchbarQuery);
    }

    public static void open(Context context) {
        context.startActivity(new Intent(context, AppBarActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //??????Menu??????
        getMenuInflater().inflate(R.menu.menu_app_bar_layout, menu);
        //????????????
        setOptionsMenu(menu);

        for (int index = 0; index < menu.size(); index++) {
            Drawable drawable = menu.getItem(index).getIcon();
            if (drawable != null) {
                //??????????????????
                drawable.setColorFilter(
                        ThemeUtil.INSTANCE.getThemeAttrColor(this, com.microsoft.fluentui.topappbars.R.attr.fluentuiToolbarIconColor)
                        , PorterDuff.Mode.SRC_IN);
            }
        }
        return true;
    }

    /**
     * ???????????????
     */
    private Searchbar createSearchbar() {
        //???????????????
        Searchbar searchbar = new Searchbar(new ContextThemeWrapper(this, Constrains.THEME_ID));
        //??????????????????
        searchbar.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            setSearchbarHasFocus(hasFocus);
        });
        return searchbar;
    }

    /**
     * ??????????????????????????????
     */
    private ListSubHeader createListSubHeader(String text) {
        ListSubHeader listSubHeader = new ListSubHeader(text);
        listSubHeader.setTitleColor(ListSubHeaderView.TitleColor.SECONDARY);
        return listSubHeader;
    }

    /**
     * ?????????Recycler View
     */
    private void setupList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<IBaseListItem> baseListItemList = createList();
        listAdapter.setListItems(baseListItemList);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new ListItemDivider(this, DividerItemDecoration.VERTICAL));
    }

    /**
     * ???????????????
     */
    private List<IBaseListItem> createSection(ListSubHeader subHeader, List<IBaseListItem> items) {
        List<IBaseListItem> itemArray = new ArrayList<>();
        itemArray.add(subHeader);
        itemArray.addAll(items);
        return itemArray;
    }

    /**
     * ??????????????????list
     */
    private List<IBaseListItem> createList() {

        List<IBaseListItem> scrollBehaviorButtons = new ArrayList<>();
        ButtonItem scrollBehaviorButton = new ButtonItem(getString(R.string.app_bar_layout_toggle_scroll_behavior_button)
                , R.id.app_bar_layout_toggle_scroll_behavior_button
                , this);
        scrollBehaviorButtons.add(scrollBehaviorButton);
        scrollBehaviorSubHeader = createListSubHeader(String.format(getString(R.string.app_bar_layout_toggle_scroll_behavior_sub_header), scrollBehavior.toString()));
        List<IBaseListItem> scrollBehaviorSection = createSection(scrollBehaviorSubHeader, scrollBehaviorButtons);

        List<IBaseListItem> navigationIconButtons = new ArrayList<>();
        navigationIconButton = new ButtonItem(getString(R.string.app_bar_layout_hide_icon_button)
                , R.id.app_bar_layout_toggle_navigation_icon_button
                , this);
        navigationIconButtons.add(navigationIconButton);
        ListSubHeader navigationIconSubHeader = createListSubHeader(getString(R.string.app_bar_layout_toggle_navigation_icon_sub_header));
        List<IBaseListItem> navigationIconSection = createSection(navigationIconSubHeader, navigationIconButtons);

        List<IBaseListItem> searchButtons = new ArrayList<>();
        searchbarButton = new ButtonItem(getString(R.string.app_bar_layout_searchbar_accessory_view_button)
                , R.id.app_bar_layout_toggle_searchbar_type_button
                , this);
        searchButtons.add(searchbarButton);
        ListSubHeader searchSubHeader = createListSubHeader(getString(R.string.app_bar_layout_toggle_searchbar_sub_header));
        List<IBaseListItem> searchbarSection = createSection(searchSubHeader, searchButtons);

        List<IBaseListItem> themeButtons = new ArrayList<>();
        ButtonItem themeButton = new ButtonItem(getString(R.string.app_bar_layout_toggle_theme_button)
                , R.id.app_bar_layout_toggle_theme_button
                , this);
        themeButtons.add(themeButton);
        ListSubHeader themeSubHeader = createListSubHeader(getString(R.string.app_bar_layout_toggle_theme_sub_header));
        List<IBaseListItem> themeSection = createSection(themeSubHeader
                , themeButtons);

        List<IBaseListItem> extraListItems = new ArrayList<>();
        for (int index = 0; index < 35; index++) {
            ListItem temp = new ListItem(getString(R.string.item) + " " + index);
            extraListItems.add(temp);
        }

        ListSubHeader extraScrollableContextSubHeader = createListSubHeader(getString(R.string.app_bar_layout_list_sub_header));
        List<IBaseListItem> extraScrollableContextSection = createSection(extraScrollableContextSubHeader, extraListItems);

        List<IBaseListItem> totalBaseListItem = new ArrayList<>();
        totalBaseListItem.addAll(scrollBehaviorSection);
        totalBaseListItem.addAll(navigationIconSection);
        totalBaseListItem.addAll(searchbarSection);
        totalBaseListItem.addAll(themeSection);
        totalBaseListItem.addAll(extraScrollableContextSection);

        return totalBaseListItem;
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.app_bar_layout_toggle_scroll_behavior_button: {
                switch (scrollBehavior) {
                    case NONE:
                        setScrollBehavior(AppBarLayout.ScrollBehavior.COLLAPSE_TOOLBAR);
                        break;
                    case COLLAPSE_TOOLBAR:
                        setScrollBehavior(AppBarLayout.ScrollBehavior.PIN);
                        break;
                    case PIN:
                        setScrollBehavior(AppBarLayout.ScrollBehavior.NONE);
                        break;
                    default:
                        break;
                }
                break;
            }

            case R.id.app_bar_layout_toggle_navigation_icon_button: {
                switch (navigationIconType) {
                    case NONE:
                        setNavigationIconType(NavigationIconType.AVATAR);
                        break;
                    case AVATAR:
                        setNavigationIconType(NavigationIconType.BACK_ICON);
                        break;
                    case BACK_ICON:
                        setNavigationIconType(NavigationIconType.NONE);
                        break;
                    default:
                        break;
                }
                break;
            }

            case R.id.app_bar_layout_toggle_searchbar_type_button:
                setSearchbarIsActionMenuView(!searchbarIsActionMenuView);
                break;
            case R.id.app_bar_layout_toggle_theme_button: {
                switch (Constrains.THEME_ID) {
                    case R.style.AppTheme:
                        Constrains.THEME_ID = R.style.AppTheme_Neutral;
                        break;
                    case R.style.AppTheme_Neutral:
                        Constrains.THEME_ID = R.style.AppTheme_Orange;
                        break;
                    case R.style.AppTheme_Orange:
                        Constrains.THEME_ID = R.style.AppTheme;
                        break;
                    default:
                        break;
                }
                recreate();
                break;
            }
        }

        new Handler(Looper.getMainLooper()).post(() -> {
            findViewById(viewId).requestFocus();
        });

    }

    /**
     * ??????????????????
     */
    private void updateScrollBehavior() {
        if (appBarLayout == null) {
            return;
        }

        appBarLayout.setScrollBehavior(scrollBehavior);


        scrollBehaviorSubHeader.setTitle(getString(R.string.app_bar_layout_toggle_scroll_behavior_sub_header
                , scrollBehavior.toString()));

        listAdapter.notifyDataSetChanged();
    }

    /**
     * ??????????????????
     */
    private void updateNavigationIcon() {
        if (appBarLayout == null) {
            return;
        }
        searchbar.clearFocus();

        if (optionsMenu != null) {
            MenuItem menuItem = optionsMenu.findItem(R.id.app_bar_layout_action_search);
            if (menuItem != null) {
                menuItem.collapseActionView();
            }
        }

        switch (navigationIconType) {
            case NONE: {
                appBarLayout.getToolbar().setNavigationIcon(null);
                navigationIconButton.setButtonText(getString(R.string.app_bar_layout_show_avatar_button));
                break;
            }
            case AVATAR: {
                Avatar avatar = new Avatar(getString(R.string.persona_name_jermyn_gao));
                avatar.setAvatarImageResourceId(R.drawable.avatar_mauricio_august);
                appBarLayout.getToolbar().setNavigationOnClickListener(v -> {
                    Snackbar.Companion.make(rootView, getString(R.string.app_bar_layout_navigation_icon_clicked), Snackbar.LENGTH_SHORT, Snackbar.Style.REGULAR).show();
                });
                appBarLayout.getToolbar().setAvatar(avatar);

                navigationIconButton.setButtonText(getString(R.string.app_bar_layout_show_back_icon_button));
                break;
            }
            case BACK_ICON: {
                Drawable backArrow = ContextCompat.getDrawable(this, com.microsoft.fluentui.R.drawable.ms_ic_arrow_left_24_filled);
                /*
                 Wrapping this by FluentUIContext so that we need not declare this attr in Theme,
                 In case our theme is not extending Fluent Theme.
                 But if declare this attr in theme then no context wrapping is required
                 */
                if (backArrow != null) {
                    backArrow.setTint(ThemeUtil.INSTANCE.getThemeAttrColor(
                            new FluentUIContextThemeWrapper(this
                                    , com.microsoft.fluentui.R.style.Theme_FluentUI_Components)
                            , com.microsoft.fluentui.topappbars.R.attr.fluentuiToolbarIconColor)
                    );
                }
                appBarLayout.getToolbar().setNavigationIcon(backArrow);
                appBarLayout.getToolbar().setNavigationOnClickListener(v -> {
                    onBackPressed();
                });

                navigationIconButton.setButtonText(getString(R.string.app_bar_layout_hide_icon_button));
                break;
            }
            default:
                break;
        }

        listAdapter.notifyDataSetChanged();
    }

    /**
     * ???????????????
     */
    private void updateSearchbar() {
        if (appBarLayout == null) {
            return;
        }

        searchbar.setActionMenuView(searchbarIsActionMenuView);
        if (searchbarIsActionMenuView) {
            if (optionsMenu == null) {
                return;
            }

            appBarLayout.setAccessoryView(null);

            Drawable searchIcon = ThemeUtilsKt.getTintedDrawable(this
                    , com.microsoft.fluentui.R.drawable.ms_ic_search_24_filled
                    , ThemeUtil.INSTANCE.getThemeAttrColor(this, com.microsoft.fluentui.topappbars.R.attr.fluentuiToolbarIconColor));

            optionsMenu.add(R.id.app_bar_menu, R.id.app_bar_layout_action_search, 0, getString(R.string.app_bar_layout_menu_search))
                    .setIcon(searchIcon)
                    .setActionView(searchbar)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_ALWAYS);

            searchbarButton.setButtonText(getString(R.string.app_bar_layout_searchbar_accessory_view_button));
        } else {
            if (optionsMenu != null) {
                optionsMenu.removeItem(R.id.app_bar_layout_action_search);
            }
            appBarLayout.setAccessoryView(searchbar);
            searchbarButton.setButtonText(getString(R.string.app_bar_layout_searchbar_action_view_button));
        }

        listAdapter.notifyDataSetChanged();
    }

    /**
     * ?????????????????????
     */
    private void updateSearchbarFocus() {
        if (appBarLayout == null) {
            return;
        }

        if (searchbarHasFocus) {
            if (optionsMenu != null) {
                optionsMenu.performIdentifierAction(R.id.app_bar_layout_action_search, 0);
            }
            searchbar.requestSearchViewFocus();
        }
    }

    private void updateSearchbarQuery() {
        if (appBarLayout == null) {
            return;
        }
        searchbar.setQuery(searchbarQuery, false);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                //navigateUpTo(new Intent(this, MainActivity.class));
                finish();
            }
            case R.id.app_bar_layout_action_search: {
                if (item.getActionView() instanceof Searchbar) {
                    ((Searchbar) item.getActionView()).requestSearchViewFocus();
                }
                break;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}