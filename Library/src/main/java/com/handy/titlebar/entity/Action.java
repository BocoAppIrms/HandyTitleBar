package com.handy.titlebar.entity;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
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

    private View actionView;
    private ImageView imageView;
    private TextView textView;

    private String actionText;
    private int actionTextSize;
    private int actionTextColor;
    private int actionImageSrc;
    private int actionImageSize;

    private int pressType;
    private int normalImage;
    private int pressImage;
    private int normalColorId;
    private int pressColorId;

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

    public String getText() {
        return actionText;
    }

    public int getTextSize() {
        return actionTextSize;
    }

    public int getTextColor() {
        return actionTextColor;
    }

    public int getImageSrc() {
        return actionImageSrc;
    }

    public int getImageSize() {
        return actionImageSize;
    }

    public void setActionView(@NonNull View actionView) {
        this.actionView = actionView;
    }

    public void setChildImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setChildTextView(TextView textView) {
        this.textView = textView;
    }

    public int getPressType() {
        return pressType;
    }

    public void setPressType(int pressType) {
        this.pressType = pressType;
    }

    public int getNormalImage() {
        return normalImage;
    }

    public void setNormalImage(int normalImage) {
        this.normalImage = normalImage;
    }

    public int getPressImage() {
        return pressImage;
    }

    public void setPressImage(int pressImage) {
        this.pressImage = pressImage;
    }

    public int getNormalColorId() {
        return normalColorId;
    }

    public void setNormalColorId(int normalColorId) {
        this.normalColorId = normalColorId;
    }

    public int getPressColorId() {
        return pressColorId;
    }

    public void setPressColorId(int pressColorId) {
        this.pressColorId = pressColorId;
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

    public Action setTextColor(@ColorInt int colorId) {
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

    public Action setImageSrc(@DrawableRes int actionImageSrc, @ColorInt int normalColorId, @ColorInt int pressColorId) {
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
