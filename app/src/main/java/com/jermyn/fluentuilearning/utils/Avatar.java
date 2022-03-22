package com.jermyn.fluentuilearning.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.fluentui.persona.IAvatar;

public class Avatar implements IAvatar {

    String name;
    String email;
    Bitmap avatarImageBitmap;
    Drawable avatarImageDrawable;
    Integer avatarImageResourceId;
    Uri avatarImageUri;
    Integer avatarBackgroundColor;
    String avatarContentDescriptionLabel;

    public Avatar(String name){
        this.name = name;
        this.email = "";
        this.avatarImageBitmap = null;
        this.avatarImageDrawable = null;
        this.avatarImageResourceId = null;
        this.avatarImageUri = null;
        this.avatarBackgroundColor = null;
        this.avatarContentDescriptionLabel = "";
    }

    @Nullable
    @Override
    public Integer getAvatarBackgroundColor() {
        return avatarBackgroundColor;
    }

    @Override
    public void setAvatarBackgroundColor(@Nullable Integer integer) {
        avatarBackgroundColor = integer;
    }

    @NonNull
    @Override
    public String getAvatarContentDescriptionLabel() {
        return avatarContentDescriptionLabel;
    }

    @Override
    public void setAvatarContentDescriptionLabel(@NonNull String s) {
        avatarContentDescriptionLabel = s;
    }

    @Nullable
    @Override
    public Bitmap getAvatarImageBitmap() {
        return avatarImageBitmap;
    }

    @Override
    public void setAvatarImageBitmap(@Nullable Bitmap bitmap) {
        avatarImageBitmap = bitmap;
    }

    @Nullable
    @Override
    public Drawable getAvatarImageDrawable() {
        return avatarImageDrawable;
    }

    @Override
    public void setAvatarImageDrawable(@Nullable Drawable drawable) {
        avatarImageDrawable = drawable;
    }

    @Nullable
    @Override
    public Integer getAvatarImageResourceId() {
        return avatarImageResourceId;
    }

    @Override
    public void setAvatarImageResourceId(@Nullable Integer integer) {
        avatarImageResourceId = integer;
    }

    @Nullable
    @Override
    public Uri getAvatarImageUri() {
        return avatarImageUri;
    }

    @Override
    public void setAvatarImageUri(@Nullable Uri uri) {
        avatarImageUri = uri;
    }

    @NonNull
    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(@NonNull String s) {
        email = s;
    }

    @NonNull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(@NonNull String s) {
        name = s;
    }
}