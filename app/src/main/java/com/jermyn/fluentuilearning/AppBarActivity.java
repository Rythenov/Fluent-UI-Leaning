package com.jermyn.fluentuilearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.microsoft.fluentui.appbarlayout.AppBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppBarActivity extends AppCompatActivity {

    @BindView(R.id.root_view)
    CoordinatorLayout rootView;

    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;

    //导航图标类型
    enum  NavigationIconType {
        NONE,
        AVATAR,
        BACK_ICON
    }


    //滚动行为
    AppBarLayout.ScrollBehavior scrollBehavior = AppBarLayout.ScrollBehavior.COLLAPSE_TOOLBAR;

    NavigationIconType navigationIconType = NavigationIconType.BACK_ICON;

    //搜索栏是否是动作菜单（右上角图标）的按钮
    boolean searchbarIsActionMenuView = false;

    boolean searchbarHasFocus = false;

    String searchbarQuery = "";

    //private ListAdapter listAdapter = new

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_learning);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Fluent UI App Bar 应用栏");
    }

    public static void open(Context context){
        context.startActivity(new Intent(context, AppBarActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                //navigateUpTo(new Intent(this, MainActivity.class));
                finish();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}