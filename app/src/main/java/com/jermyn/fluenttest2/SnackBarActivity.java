package com.jermyn.fluenttest2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.microsoft.fluentui.appbarlayout.AppBarLayout;
import com.microsoft.fluentui.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SnackBarActivity extends AppCompatActivity {
    @BindView(R.id.root_view)
    CoordinatorLayout rootView;

    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Fluent UI Snack Bar 快餐栏");
    }

    @OnClick(R.id.btn_announcement_snack_bar)
    void onAnnouncementSnackBar(){
        Snackbar.Companion.make(rootView, "ANNOUNCEMENT Snack Bar 公告快餐栏", Snackbar.LENGTH_LONG, Snackbar.Style.ANNOUNCEMENT).show();
    }

    @OnClick(R.id.btn_regular_snack_bar)
    void onRegularSnackBar(){
        Snackbar.Companion.make(rootView, "REGULAR Snack Bar 常规快餐栏", Snackbar.LENGTH_SHORT, Snackbar.Style.REGULAR).show();
    }

    @OnClick(R.id.btn_action_snack_bar)
    void onActionSnackBar(){
        Snackbar.Companion.make(rootView, "ACTION Snack Bar 动作快餐栏", Snackbar.LENGTH_SHORT, Snackbar.Style.REGULAR)
                .setAction("ACTION", view -> {
                    Toast.makeText(this,"触发ACTION", Toast.LENGTH_SHORT).show();
                })
                .show();

    }

    @OnClick(R.id.btn_action_snack_bar)
    void onLongSnakeBar(){
        Snackbar snackbar =  Snackbar.Companion.make(rootView, "ACTION Snack Bar 动作快餐栏", Snackbar.LENGTH_SHORT, Snackbar.Style.REGULAR);

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