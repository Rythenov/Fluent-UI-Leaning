package com.jermyn.fluentuilearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;

import com.microsoft.fluentui.appbarlayout.AppBarLayout;

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
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("Fluent UI 学习");
    }

    @OnClick(R.id.btn_snack_bar)
    void onSnackBar(){
        SnackBarActivity.open(this);
    }

    @OnClick(R.id.btn_app_bar)
    void onAppBar(){
        AppBarActivity.open(this);
    }
}