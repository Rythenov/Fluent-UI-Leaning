package com.jermyn.fluentuilearning;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.widget.NestedScrollView;

import com.microsoft.fluentui.appbarlayout.AppBarLayout;
import com.microsoft.fluentui.persona.AvatarSize;
import com.microsoft.fluentui.persona.AvatarView;
import com.microsoft.fluentui.progress.ProgressBar;
import com.microsoft.fluentui.snackbar.Snackbar;
import com.microsoft.fluentui.util.ViewUtilsKt;

import java.util.Timer;
import java.util.TimerTask;

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

    @OnClick(R.id.btn_short_duration_with_action_and_medium_custom_view)
    void onShortDurationWithActionAndMediumCustomView(){
        AvatarView avatarView = new AvatarView(this);
        avatarView.setAvatarSize(AvatarSize.MEDIUM);
        avatarView.setName(getString(R.string.persona_name_jermyn_gao));
        Snackbar.Companion.make(rootView, getString(R.string.short_duration_with_action_and_medium_custom_view), Snackbar.LENGTH_SHORT, Snackbar.Style.REGULAR)
                .setCustomView(avatarView, Snackbar.CustomViewSize.MEDIUM)
                .setAction(getString(R.string.action)
                        , v ->{
                                    Toast.makeText(SnackBarActivity.this, getString(R.string.response_action), Toast.LENGTH_SHORT).show();
                        })
                .show();
    }

    @OnClick(R.id.btn_multi_line_long_duration)
    void onMultiLineLongDuration(){
        Snackbar.Companion.make(rootView, getString(R.string.snack_bar_multiline_text), Snackbar.LENGTH_LONG, Snackbar.Style.REGULAR).show();
    }

    @OnClick(R.id.btn_indefinite_duration_with_action_and_text_update)
    void onIndefiniteDurationWithActionAndTextUpdate(){
        Snackbar snackbar = Snackbar.Companion.make(rootView, getString(R.string.snack_bar_multiline_text), Snackbar.LENGTH_INDEFINITE, Snackbar.Style.REGULAR)
                .setAction(getString(R.string.action), v -> {
                    Toast.makeText(SnackBarActivity.this, getString(R.string.response_action), Toast.LENGTH_SHORT).show();
            });

        snackbar.show();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                snackbar.getView().post(() -> {
                    snackbar.setText(getString(R.string.snack_bar_update_text));
                });
            }
        }, 2000);
    }

    @OnClick(R.id.btn_long_duration_with_action_and_small_custom_view)
    void longDurationWithActionAndSmallCustomView(){
        ImageView checkmarkIconImageView = ViewUtilsKt.createImageView(this
                , com.microsoft.fluentui.R.drawable.ms_ic_checkmark_24_filled
                , ContextCompat.getColor(this, R.color.fluentui_white));
        Snackbar.Companion.make(rootView, getString(R.string.snack_bar_multiline_text), Snackbar.LENGTH_LONG, Snackbar.Style.REGULAR)
                .setCustomView(checkmarkIconImageView, Snackbar.CustomViewSize.MEDIUM)
                .show();
    }

    @OnClick(R.id.btn_short_duration_with_action_and_medium_custom_view_multi_line)
    void shortDurationWithActionAndMediumCustomView(){
        ImageView thumbnailImageView = new ImageView(this);
        Bitmap thumbnailBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.thumbnail_example_32);
        RoundedBitmapDrawable roundedCornerThumbnailDrawable = RoundedBitmapDrawableFactory.create(getResources(), thumbnailBitmap);
        roundedCornerThumbnailDrawable.setCornerRadius(getResources().getDimension(com.microsoft.fluentui.transients.R.dimen.fluentui_snackbar_background_corner_radius));
        thumbnailImageView.setImageDrawable(roundedCornerThumbnailDrawable);
        Snackbar.Companion.make(rootView, getString(R.string.snack_bar_multiline_text), Snackbar.LENGTH_SHORT, Snackbar.Style.REGULAR)
                .setCustomView(thumbnailImageView, Snackbar.CustomViewSize.MEDIUM)
                .setAction(getString(R.string.action), v -> {
                    Toast.makeText(SnackBarActivity.this, getString(R.string.response_action), Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    @OnClick(R.id.btn_short_duration_with_long_action)
    void shortDurationWithLongAction(){
        Snackbar.Companion.make(rootView, getString(R.string.snack_bar_multiline_text), Snackbar.LENGTH_SHORT, Snackbar.Style.REGULAR)
                .setAction(getString(R.string.action_long), v -> {
                    Toast.makeText(SnackBarActivity.this, getString(R.string.response_action), Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    @OnClick(R.id.btn_snackbar_announcement)
    void announcement(){
        ImageView announcementIconImageView = new ImageView(this);
        announcementIconImageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_gift_24_filled));

        Snackbar.Companion.make(rootView, getString(R.string.snackbar_announcement), Snackbar.LENGTH_SHORT,Snackbar.Style.ANNOUNCEMENT)
                .setCustomView(announcementIconImageView, Snackbar.CustomViewSize.SMALL)
                .setAction(getString(R.string.action), v -> {
                    Toast.makeText(SnackBarActivity.this, getString(R.string.response_action), Toast.LENGTH_SHORT).show();
                })
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