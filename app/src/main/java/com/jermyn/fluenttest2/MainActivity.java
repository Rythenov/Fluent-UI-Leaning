package com.jermyn.fluenttest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("Fluent UI 学习");
    }

    @OnClick(R.id.btn_snack_bar)
    void onRegularSnakeBarMatchParent(){
        startActivity(new Intent(this, SnackBarActivity.class));
    }
}