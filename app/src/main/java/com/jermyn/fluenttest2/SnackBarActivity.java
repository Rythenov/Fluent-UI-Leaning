package com.jermyn.fluenttest2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.microsoft.fluentui.appbarlayout.AppBarLayout;
import com.microsoft.fluentui.progress.ProgressBar;
import com.microsoft.fluentui.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SnackBarActivity extends AppCompatActivity {
    @BindView(R.id.root_view)
    CoordinatorLayout rootView;

    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;

    @BindView(R.id.demo_detail_scrollable_container)
    NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Fluent UI Snack Bar 快餐栏");
    }

    @OnClick(R.id.btn_regular_length_short_snack_bar)
    void onRegularLengthShortSnackBar(){
        Snackbar.Companion.make(rootView, getString(R.string.short_duration), Snackbar.LENGTH_SHORT, Snackbar.Style.REGULAR).show();
    }

    @OnClick(R.id.btn_regular_length_short_action_snack_bar)
    void onRegularLengthShortActionSnackBar(){
        Snackbar.Companion.make(rootView, getString(R.string.short_duration_with_action), Snackbar.LENGTH_SHORT, Snackbar.Style.REGULAR)
                .setAction(getString(R.string.action), v -> {
                    Toast.makeText(SnackBarActivity.this, getString(R.string.response_action), Toast.LENGTH_SHORT).show();
                }).show();
    }
    @OnClick(R.id.btn_regular_length_long_custom_view_circular_progress_snack_bar)
    void onRegularLengthLongWithCircularProgressAsSmallCustomViewSnackBar(){
        ProgressBar circularProgress = new ProgressBar(this
                , null
                , 0
                , com.microsoft.fluentui.progress.R.style.Widget_FluentUI_CircularProgress_Small);

        circularProgress.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this
                , R.color.snackbar_circular_progress_drawable)
                , PorterDuff.Mode.SRC_IN);

        Snackbar.Companion.make(rootView, getString(R.string.long_duration_with_circular_progress_as_small_custom_view), Snackbar.LENGTH_LONG, Snackbar.Style.REGULAR)
                .setCustomView(circularProgress, Snackbar.CustomViewSize.SMALL)
                .show();
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