package com.handy.titlebar.entity;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.handy.titlebar.utils.HandyTitleBarUtils;

/**
 * file name
 *
 * @author LiuJie https://github.com/Handy045
 * @description functional description.
 * @date Created in 2019/3/29 10:59 AM
 * @modified By liujie
 */
public abstract class Action {

    public View actionView;
    public ImageView imageView;
    public TextView textView;

    public String actionText;
    public int actionTextSize;
    public int actionImageSize;

    public int textPressType;
    @ColorRes
    public int nTextColorId;
    @ColorRes
    public int pTextColorId;

    public int imagePressType;
    @DrawableRes
    public int actionImageSrc;
    @DrawableRes
    public int nImageResId;
    @DrawableRes
    public int pImageResId;
    @ColorRes
    public int nImageColorId;
    @ColorRes
    public int pImageColorId;

    public Action() {
    }

    public abstract void onClick();

    public View getActionView() {
        return actionView;
    }

    public ImageView getChildImageView() {
        return imageView;
    }

    public TextView getChildTextView() {
        return textView;
    }

    public Action setText(String text) {
        this.actionText = text;
        return this;
    }

    public Action setTextSize(int spSize) {
        if (spSize >= 0) {
            this.actionTextSize = HandyTitleBarUtils.spTopx(spSize);
        }
        return this;
    }

    public Action setTextColor(@ColorRes int nTextColorId) {
        this.textPressType = 0;
        this.nTextColorId = nTextColorId;
        return this;
    }

    public Action setTextColor(@ColorRes int nTextColorId, @ColorRes int pTextColorId) {
        this.textPressType = 1;
        this.nTextColorId = nTextColorId;
        this.pTextColorId = pTextColorId;
        return this;
    }

    public Action setImageSrc(@DrawableRes int nImageResId) {
        this.imagePressType = 0;
        this.actionImageSrc = nImageResId;
        return this;
    }

    public Action setImageSrc(@DrawableRes int nImageResId, @DrawableRes int pImageResId) {
        this.imagePressType = 1;
        this.actionImageSrc = nImageResId;
        this.nImageResId = nImageResId;
        this.pImageResId = pImageResId;
        return this;
    }

    public Action setImageSrc(@DrawableRes int nImageResId, @ColorRes int nImageColorId, @ColorRes int pImageColorId) {
        this.imagePressType = 2;
        this.actionImageSrc = nImageResId;
        this.nImageColorId = nImageColorId;
        this.pImageColorId = pImageColorId;
        return this;
    }

    public Action setImageSize(int dpSize) {
        if (dpSize >= 0) {
            this.actionImageSize = HandyTitleBarUtils.dpTopx(dpSize);
        }
        return this;
    }
}
