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
    public int actionTextColor;
    public int actionImageSrc;
    public int actionImageSize;

    public int pressType;
    public int normalImage;
    public int pressImage;
    public int normalColorId;
    public int pressColorId;

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

    public Action setTextColor(@ColorRes int colorId) {
        this.actionTextColor = colorId;
        return this;
    }

    public Action setImageSrc(@DrawableRes int normalImage) {
        this.pressType = 0;
        this.actionImageSrc = normalImage;
        return this;
    }

    public Action setImageSrc(@DrawableRes int normalImage, @DrawableRes int pressImage) {
        this.pressType = 1;
        this.actionImageSrc = normalImage;
        this.normalImage = normalImage;
        this.pressImage = pressImage;
        return this;
    }

    public Action setImageSrc(@DrawableRes int actionImageSrc, @ColorRes int normalColorId, @ColorRes int pressColorId) {
        this.pressType = 2;
        this.actionImageSrc = actionImageSrc;
        this.normalColorId = normalColorId;
        this.pressColorId = pressColorId;
        return this;
    }

    public Action setImageSize(int dpSize) {
        if (dpSize >= 0) {
            this.actionImageSize = HandyTitleBarUtils.dpTopx(dpSize);
        }
        return this;
    }
}
