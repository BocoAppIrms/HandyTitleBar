package com.handy.titlebar.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.WindowManager;

/**
 * 相关工具类
 *
 * @author LiuJie https://github.com/Handy045
 * @description functional description.
 * @date Created in 2019/3/29 10:57 AM
 * @modified By liujie
 */
public class HandyTitleBarUtils {

    /**
     * dp转px
     */
    public static int dpTopx(int dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * sp 转 px
     */
    public static int spTopx(final float spValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * Return the width of screen, in pixel.
     *
     * @return the width of screen, in pixel
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
        Point point = new Point();
        wm.getDefaultDisplay().getRealSize(point);
        return point.x;
    }

    /**
     * 通过代码改变图片颜色
     *
     * @param drawable  原图片
     * @return 改色后的Drawable
     */
    public static Drawable tintDrawable(Context context, Drawable drawable, @ColorRes int normal, @ColorRes int pressed, @ColorRes int focus) {
        DrawableCompat.setTintList(drawable, getStateColor(context, normal, pressed, focus));
        return drawable;
    }

    /**
     * 通过代码设置Selector效果
     *
     * @param context   上下文
     * @param idNormal  默认样式（或者图片和颜色的资源ID）
     * @param idPressed 点击样式（或者图片和颜色的资源ID）
     * @param idFocused 焦点样式（或者图片和颜色的资源ID）
     * @return Selector样式
     */
    public static StateListDrawable getStateDrawable(@NonNull Context context, @DrawableRes int idNormal, @DrawableRes int idPressed, @DrawableRes int idFocused) {
        Drawable normal = idNormal == 0 ? null : context.getResources().getDrawable(idNormal);
        Drawable pressed = idPressed == 0 ? null : context.getResources().getDrawable(idPressed);
        Drawable focus = idFocused == 0 ? null : context.getResources().getDrawable(idFocused);
        return getStateDrawable(normal, pressed, focus);
    }

    /**
     * 通过代码设置Selector效果
     *
     * @return Selector样式
     */
    public static StateListDrawable getStateDrawable(Drawable normal, Drawable pressed, Drawable focus) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        //所以不要把大范围放在前面了，如果sd.addState(new[]{},normal)放在第一个的话，就没有什么效果了
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}, focus);
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
        stateListDrawable.addState(new int[]{android.R.attr.state_focused}, focus);
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressed);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, normal);
        stateListDrawable.addState(new int[]{}, normal);
        return stateListDrawable;
    }

    /**
     * 通过代码设置Selector效果
     *
     * @param context   上下文
     * @param idNormal  默认样式（或者图片和颜色的资源ID）
     * @param idPressed 点击样式（或者图片和颜色的资源ID）
     * @param idFocused 焦点样式（或者图片和颜色的资源ID）
     * @return Selector样式
     */
    public static ColorStateList getStateColor(@NonNull Context context, @ColorRes int idNormal, @ColorRes int idPressed, @ColorRes int idFocused) {
        int normal = context.getResources().getColor(idNormal);
        int pressed = context.getResources().getColor(idPressed);
        int focus = context.getResources().getColor(idFocused);
        return getStateColor(normal, pressed, focus);
    }

    /**
     * 通过代码设置Selector效果
     *
     * @return Selector样式
     */
    public static ColorStateList getStateColor(@ColorInt int normal, @ColorInt int pressed, @ColorInt int focus) {
        int[] colors = new int[]{focus, pressed, focus, pressed, normal, normal};
        int[][] states = new int[6][];
        states[0] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[1] = new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled};
        states[2] = new int[]{android.R.attr.state_focused};
        states[3] = new int[]{android.R.attr.state_pressed};
        states[4] = new int[]{android.R.attr.state_enabled};
        states[5] = new int[]{};
        return new ColorStateList(states, colors);
    }
}
