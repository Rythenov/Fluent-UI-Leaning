package com.jermyn.fluenttest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.microsoft.fluentui.appbarlayout.AppBarLayout;
import com.microsoft.fluentui.snackbar.Snackbar;
import com.microsoft.fluentui.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.root_view)
    CoordinatorLayout rootView;

    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("Fluent UI 学习");
    }

    @OnClick(R.id.btn_announcement_snake_bar)
    void onAnnouncementSnakeBar(){
        Snackbar.Companion.make(rootView, "ANNOUNCEMENT Snake Bar 公告蛇栏", 3000, Snackbar.Style.ANNOUNCEMENT).show();
    }
    @OnClick(R.id.btn_regular_snake_bar)
    void onRegularSnakeBar(){
        Snackbar.Companion.make(rootView, "REGULAR Snake Bar 常规蛇栏", 3000, Snackbar.Style.REGULAR).show();
    }
}