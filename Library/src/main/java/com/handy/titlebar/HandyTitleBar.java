package com.handy.titlebar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * 新标题栏控件
 *
 * @author LiuJie https://www.Handy045.com
 * @description 支持沉浸式，可以在xml布局中通过自定义属性配置标题栏样式
 * @date Created in 2018/5/19 下午5:55
 * @modified By LiuJie
 */
public class HandyTitleBar extends ViewGroup {

    private int statusBarHeight;
    private int statusBarBackgroundColor;
    private boolean isShowCustomStatusBar;

    private int topLineHeight;
    private int topLineColor;

    private int titleBarMargin;
    private int titleBarMarginTop;
    private int titleBarMarginLeft;
    private int titleBarMarginRight;
    private int titleBarMarginBottom;
    private int titleBarWidth;
    private int titleBarHeight;
    private Drawable titleBarBackground;
    private String mainText;
    private float mainTextSize;
    private int mainTextColor;
    private int mainTextBackgroundColor;
    private String subText;
    private float subTextSize;
    private int subTextColor;
    private int subTextBackgroundColor;

    private int bottomLineHeight;
    private int bottomLineColor;

    private int actionViewPadding;
    private int actionLayoutPadding;
    private int actionTextSize;
    private int actionTextColor;
    private int actionImageSrc;
    private int actionImageSize;

    private int displayWidth;
    private int parentWidth;
    private int parentHeight;

    private View statusBar;
    private View topLineView;
    private View titleBar;
    private LinearLayout leftActionsLayout;
    private LinearLayout contentLayout;
    private MarqueeTextView mainTextView;
    private MarqueeTextView subTextView;
    private LinearLayout rightActionsLayout;
    private View bottomLineView;

    public HandyTitleBar(Context context) {
        this(context, null);
    }

    public HandyTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("CustomViewStyleable")
    public HandyTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        displayWidth = getResources().getDisplayMetrics().widthPixels;

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HandyTitleBarStyleable);
        statusBarHeight = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_statusBarHeight, getStatusBarHeight(context));
        statusBarBackgroundColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_handy_statusBarBackgroundColor, 0xFF0091ea);
        isShowCustomStatusBar = typedArray.getBoolean(R.styleable.HandyTitleBarStyleable_handy_isShowCustomStatusBar, false);

        topLineHeight = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_topLineHeight, 0);
        topLineColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_handy_topLineColor, Color.GRAY);

        titleBarMargin = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_titleBarMargin, 0);
        titleBarMarginTop = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_titleBarMarginTop, 0);
        titleBarMarginLeft = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_titleBarMarginLeft, 0);
        titleBarMarginRight = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_titleBarMarginRight, 0);
        titleBarMarginBottom = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_titleBarMarginBottom, 0);
        titleBarHeight = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_titleBarHeight, dpTopx(48));
        titleBarBackground = typedArray.getDrawable(R.styleable.HandyTitleBarStyleable_handy_titleBarBackground);

        mainText = typedArray.getString(R.styleable.HandyTitleBarStyleable_handy_mainText);
        mainTextSize = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_mainTextSize, 17);
        mainTextColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_handy_mainTextColor, Color.BLACK);
        mainTextBackgroundColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_handy_mainTextBackgroundColor, Color.TRANSPARENT);
        subText = typedArray.getString(R.styleable.HandyTitleBarStyleable_handy_subText);
        subTextSize = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_subTextSize, 11);
        subTextColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_handy_subTextColor, Color.BLACK);
        subTextBackgroundColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_handy_subTextBackgroundColor, Color.TRANSPARENT);

        bottomLineHeight = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_bottomLineHeight, 0);
        bottomLineColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_handy_bottomLineColor, Color.GRAY);

        actionViewPadding = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_actionViewPadding, dpTopx(4));
        actionLayoutPadding = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_actionLayoutPadding, dpTopx(8));
        actionTextSize = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_actionTextSize, 13);
        actionTextColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_handy_actionTextColor, Color.BLACK);
        actionImageSrc = typedArray.getResourceId(R.styleable.HandyTitleBarStyleable_handy_actionImageSrc, 0);
        actionImageSize = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_handy_actionImageSize, 18);
        typedArray.recycle();
        initView(context);
    }

    private void initView(Context context) {
        statusBar = new View(context);
        statusBar.setBackgroundColor(statusBarBackgroundColor);
        initStatusBar((Activity) context, isShowCustomStatusBar);

        topLineView = new View(context);
        topLineView.setBackgroundColor(topLineColor);

        titleBar = new View(context);
        titleBar.setBackgroundColor(Color.WHITE);
        if (titleBarBackground != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                titleBar.setBackground(titleBarBackground);
            } else {
                titleBar.setBackgroundDrawable(titleBarBackground);
            }
        }

        leftActionsLayout = new LinearLayout(context);
        leftActionsLayout.setOrientation(LinearLayout.HORIZONTAL);
        leftActionsLayout.setPadding(actionLayoutPadding, 0, actionLayoutPadding, 0);
        leftActionsLayout.setBackgroundColor(Color.TRANSPARENT);
        leftActionsLayout.setGravity(Gravity.CENTER);

        mainTextView = new MarqueeTextView(context);
        mainTextView.setText(mainText);
        mainTextView.setTextColor(mainTextColor);
        mainTextView.setTextSize(mainTextSize);
        mainTextView.setBackgroundColor(mainTextBackgroundColor);
        mainTextView.setSingleLine();
        mainTextView.setGravity(Gravity.CENTER);
        mainTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mainTextView.setVisibility((mainText == null || "".equals(mainText)) ? GONE : VISIBLE);

        subTextView = new MarqueeTextView(context);
        subTextView.setText(subText);
        subTextView.setTextColor(subTextColor);
        subTextView.setTextSize(subTextSize);
        subTextView.setBackgroundColor(subTextBackgroundColor);
        subTextView.setSingleLine();
        subTextView.setGravity(Gravity.CENTER);
        subTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        subTextView.setVisibility((subText == null || "".equals(subText)) ? GONE : VISIBLE);

        contentLayout = new LinearLayout(context);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setGravity(Gravity.CENTER);
        contentLayout.setBackgroundColor(Color.TRANSPARENT);
        contentLayout.addView(mainTextView, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        contentLayout.addView(subTextView, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        rightActionsLayout = new LinearLayout(context);
        rightActionsLayout.setOrientation(LinearLayout.HORIZONTAL);
        rightActionsLayout.setPadding(actionLayoutPadding, 0, actionLayoutPadding, 0);
        rightActionsLayout.setBackgroundColor(Color.TRANSPARENT);
        rightActionsLayout.setGravity(Gravity.CENTER);

        bottomLineView = new View(context);
        bottomLineView.setBackgroundColor(bottomLineColor);

        addView(statusBar, new LayoutParams(LayoutParams.MATCH_PARENT, statusBarHeight));
        addView(topLineView, new LayoutParams(LayoutParams.MATCH_PARENT, topLineHeight));
        addView(titleBar, new LayoutParams(LayoutParams.MATCH_PARENT, titleBarHeight));
        addView(leftActionsLayout, new LayoutParams(LayoutParams.WRAP_CONTENT, titleBarHeight));
        addView(contentLayout, new LayoutParams(LayoutParams.WRAP_CONTENT, titleBarHeight));
        addView(rightActionsLayout, new LayoutParams(LayoutParams.WRAP_CONTENT, titleBarHeight));
        addView(bottomLineView, new LayoutParams(LayoutParams.MATCH_PARENT, bottomLineHeight));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        parentWidth = widthMode != MeasureSpec.AT_MOST ? widthSize : displayWidth;
        parentHeight = heightMode != MeasureSpec.AT_MOST ? heightSize : statusBarHeight + topLineHeight + titleBarHeight + bottomLineHeight;

        measureChild(statusBar, MeasureSpec.makeMeasureSpec(displayWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(statusBarHeight, MeasureSpec.EXACTLY));
        measureChild(titleBar, MeasureSpec.makeMeasureSpec(parentWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(titleBarHeight, MeasureSpec.EXACTLY));
        measureChild(topLineView, MeasureSpec.makeMeasureSpec(parentWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(topLineHeight, MeasureSpec.EXACTLY));
        measureChild(bottomLineView, MeasureSpec.makeMeasureSpec(parentWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(bottomLineHeight, MeasureSpec.EXACTLY));
        measureChild(leftActionsLayout, widthMeasureSpec, MeasureSpec.makeMeasureSpec(titleBarHeight, MeasureSpec.EXACTLY));
        measureChild(rightActionsLayout, widthMeasureSpec, MeasureSpec.makeMeasureSpec(titleBarHeight, MeasureSpec.EXACTLY));
        if (leftActionsLayout.getMeasuredWidth() > rightActionsLayout.getMeasuredWidth()) {
            contentLayout.measure(MeasureSpec.makeMeasureSpec(parentWidth - 2 * leftActionsLayout.getMeasuredWidth(), MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(titleBarHeight, MeasureSpec.EXACTLY));
        } else {
            contentLayout.measure(MeasureSpec.makeMeasureSpec(parentWidth - 2 * rightActionsLayout.getMeasuredWidth(), MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(titleBarHeight, MeasureSpec.EXACTLY));
        }

        setMeasuredDimension(displayWidth, parentHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        statusBar.layout(0, 0, displayWidth, statusBarHeight);

        topLineView.layout(0, statusBarHeight, parentWidth, statusBarHeight + topLineHeight);

        titleBar.layout(0, statusBarHeight + topLineHeight, parentWidth, statusBarHeight + topLineHeight + titleBarHeight);

        if (leftActionsLayout.getChildCount() > 0) {
            leftActionsLayout.layout(0, statusBarHeight + topLineHeight, leftActionsLayout.getMeasuredWidth(), statusBarHeight + topLineHeight + titleBarHeight);
        }

        if (leftActionsLayout.getChildCount() > 0 || rightActionsLayout.getChildCount() > 0) {
            if (leftActionsLayout.getMeasuredWidth() > rightActionsLayout.getMeasuredWidth()) {
                contentLayout.layout(leftActionsLayout.getMeasuredWidth(), statusBarHeight + topLineHeight, parentWidth - leftActionsLayout.getMeasuredWidth(), statusBarHeight + topLineHeight + titleBarHeight);
            } else {
                contentLayout.layout(rightActionsLayout.getMeasuredWidth(), statusBarHeight + topLineHeight, parentWidth - rightActionsLayout.getMeasuredWidth(), statusBarHeight + topLineHeight + titleBarHeight);
            }
        } else {
            contentLayout.layout(0, statusBarHeight + topLineHeight, parentWidth, statusBarHeight + topLineHeight + titleBarHeight);
        }

        if (rightActionsLayout.getChildCount() > 0) {
            rightActionsLayout.layout(parentWidth - rightActionsLayout.getMeasuredWidth(), statusBarHeight + topLineHeight, parentWidth, statusBarHeight + topLineHeight + titleBarHeight);
        }

        bottomLineView.layout(0, statusBarHeight + topLineHeight + titleBarHeight, parentWidth, statusBarHeight + topLineHeight + titleBarHeight + bottomLineHeight);
    }

    /**
     * 设置系统状态栏是否可见，安卓系统版本大于等于19
     */
    private void initStatusBar(Activity activity, boolean isShowCustomStatusBar) {
        this.isShowCustomStatusBar = isShowCustomStatusBar;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            statusBarHeight = 0;
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        } else {
            statusBarHeight = getStatusBarHeight(activity);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                if (isShowCustomStatusBar) {
                    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                } else {
                    activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (isShowCustomStatusBar) {
                    activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
                } else {
                    activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    activity.getWindow().setStatusBarColor(Color.BLACK);
                }
            }
        }
    }

    /**
     * 获取状态栏高度高度
     */
    @SuppressLint("PrivateApi")
    private int getStatusBarHeight(Context context) {
        try {
            Object obj = Class.forName("com.android.internal.R$dimen").newInstance();
            Field field = Class.forName("com.android.internal.R$dimen").getField("status_bar_height");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(field.get(obj).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 移除左右侧容器内所有的动作按钮
     */
    public void removeAllActions() {
        leftActionsLayout.removeAllViews();
        rightActionsLayout.removeAllViews();
    }

    /**
     * 在左侧容器内新增单个动作按钮
     */
    public View addLeftAction(HandyTitleBar.BaseAction action) {
        final int index = leftActionsLayout.getChildCount();
        return addLeftAction(action, index);
    }

    /**
     * 在左侧容器内指定的位置新增动作按钮
     */
    public View addLeftAction(HandyTitleBar.BaseAction action, int index) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        View view = inflateAction(action);
        action.setActionView(view);
        leftActionsLayout.addView(view, index, params);
        return view;
    }

    /**
     * 移除左侧容器内全部的动作按钮
     */
    public void removeLeftAction() {
        leftActionsLayout.removeAllViews();
    }

    /**
     * 移除左侧容器内指定位置的动作按钮
     */
    public void removeLeftAction(int index) {//
        leftActionsLayout.removeViewAt(index);
    }

    /**
     * 在右侧容器内新增单个动作按钮
     */
    public View addRightAction(HandyTitleBar.BaseAction action) {
        final int index = rightActionsLayout.getChildCount();
        return addRightAction(action, index);
    }

    /**
     * 在右侧容器内指定的位置新增动作按钮
     */
    public View addRightAction(HandyTitleBar.BaseAction action, int index) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        View view = inflateAction(action);
        action.setActionView(view);
        rightActionsLayout.addView(view, index, params);
        return view;
    }

    /**
     * 移除右侧容器内全部的动作按钮
     */
    public void removeRightAction() {
        rightActionsLayout.removeAllViews();
    }

    /**
     * 移除右侧容器内指定位置的动作按钮
     */
    public void removeRightAction(int index) {
        rightActionsLayout.removeViewAt(index);
    }

    /**
     * 初始化动作按钮控件布局
     */
    private View inflateAction(HandyTitleBar.BaseAction action) {
        LinearLayout view = new LinearLayout(getContext());
        view.setGravity(Gravity.CENTER_VERTICAL);
        view.setOrientation(LinearLayout.HORIZONTAL);
        view.setPadding(actionViewPadding, 0, actionViewPadding, 0);
        view.setTag(action);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Object tag = v.getTag();
                if (tag instanceof HandyTitleBar.BaseAction) {
                    final HandyTitleBar.BaseAction action = (HandyTitleBar.BaseAction) tag;
                    action.onClick();
                }
            }
        });

        //若图片设置不为空，添加动作按钮的图片
        if (action.setImageSrc() != 0) {
            ImageView img = new ImageView(getContext());
            LayoutParams imglp = new LayoutParams(dpTopx(action.setImageSize()), dpTopx(action.setImageSize()));
            img.setLayoutParams(imglp);
            img.setImageResource(action.setImageSrc());
            img.setClickable(false);
            view.addView(img);
        }

        //若文字设置不为空，添加动作按钮的文字
        if (!TextUtils.isEmpty(action.setText())) {
            TextView text = new TextView(getContext());
            text.setGravity(Gravity.CENTER);
            text.setText(action.setText());
            //动作按钮中文字距离图片4dp
            text.setPadding(action.setImageSrc() != 0 ? dpTopx(4) : 0, 0, 0, 0);
            text.setTextSize(action.setTextSize());
            text.setTextColor(action.setTextColor());
            view.addView(text);
        }
        return view;
    }

    /**
     * 带跑马灯功能的TextView
     */
    public class MarqueeTextView extends android.support.v7.widget.AppCompatTextView {
        public MarqueeTextView(Context context) {
            super(context);
        }

        public MarqueeTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public MarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        public boolean isFocused() {
            return true;
        }
    }

    /**
     * dp转px
     */
    private int dpTopx(int dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * sp 转 px
     */
    private static int spTopx(final float spValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 自定义对象类
     */
    public static abstract class BaseAction {

        private View actionView;
        private HandyTitleBar handyTitleBar;

        public BaseAction(HandyTitleBar handyTitleBar) {
            this.handyTitleBar = handyTitleBar;
        }

        public abstract void onClick();

        public String setText() {
            return null;
        }

        public int setTextColor() {
            return handyTitleBar.actionTextColor;
        }

        public int setTextSize() {
            return handyTitleBar.actionTextSize;
        }

        public int setImageSrc() {
            return handyTitleBar.actionImageSrc;
        }

        public int setImageSize() {
            return handyTitleBar.actionImageSize;
        }

        public View getActionView() {
            return actionView;
        }

        public void setActionView(View actionView) {
            this.actionView = actionView;
        }

    }

    /*============================== 内部控件开放 ==============================*/

    public View getStatusBar() {
        return statusBar;
    }

    public View getTopLineView() {
        return topLineView;
    }

    public View getTitleBar() {
        return titleBar;
    }

    public LinearLayout getLeftActionsLayout() {
        return leftActionsLayout;
    }

    public LinearLayout getContentLayout() {
        return contentLayout;
    }

    public MarqueeTextView getMainTextView() {
        return mainTextView;
    }

    public MarqueeTextView getSubTextView() {
        return subTextView;
    }

    public LinearLayout getRightActionsLayout() {
        return rightActionsLayout;
    }

    public View getBottomLineView() {
        return bottomLineView;
    }

    /*============================== 元素属性修改 ==============================*/

    public HandyTitleBar setStatusBarHeight(int statusBarHeight) {
        if (statusBarHeight >= 0) {
            this.statusBarHeight = dpTopx(statusBarHeight);
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setStatusBarBackgroundColor(@ColorInt int statusBarBackgroundColor) {
        if (statusBarBackgroundColor >= 0) {
            try {
                statusBar.setBackgroundColor(statusBarBackgroundColor);
                this.statusBarBackgroundColor = statusBarBackgroundColor;
                requestLayout();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public HandyTitleBar setShowCustomStatusBar(Activity activity, boolean showCustomStatusBar) {
        isShowCustomStatusBar = showCustomStatusBar;
        initStatusBar(activity, isShowCustomStatusBar);
        requestLayout();
        return this;
    }

    public HandyTitleBar setTopLineHeight(int topLineHeight) {
        if (topLineHeight >= 0) {
            this.topLineHeight = dpTopx(topLineHeight);
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setTopLineColor(@ColorInt int topLineColor) {
        if (topLineColor >= 0) {
            try {
                topLineView.setBackgroundColor(topLineColor);
                this.topLineColor = topLineColor;
                requestLayout();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public HandyTitleBar setTitleBarMargin(int titleBarMargin) {
        if (titleBarMargin >= 0) {
            this.titleBarMargin = dpTopx(titleBarMargin);
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setTitleBarMarginTop(int titleBarMarginTop) {
        if (titleBarMarginTop >= 0) {
            this.titleBarMarginTop = dpTopx(titleBarMarginTop);
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setTitleBarMarginLeft(int titleBarMarginLeft) {
        if (titleBarMarginLeft >= 0) {
            this.titleBarMarginLeft = dpTopx(titleBarMarginLeft);
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setTitleBarMarginRight(int titleBarMarginRight) {
        if (titleBarMarginRight >= 0) {
            this.titleBarMarginRight = dpTopx(titleBarMarginRight);
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setTitleBarMarginBottom(int titleBarMarginBottom) {
        if (titleBarMarginBottom >= 0) {
            this.titleBarMarginBottom = dpTopx(titleBarMarginBottom);
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setTitleBarHeight(int titleBarHeight) {
        if (titleBarHeight >= 0) {
            this.titleBarHeight = dpTopx(titleBarHeight);
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setTitleBarBackground(Drawable titleBarBackground) {
        if (titleBarBackground != null) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    titleBar.setBackground(titleBarBackground);
                } else {
                    titleBar.setBackgroundDrawable(titleBarBackground);
                }
                this.titleBarBackground = titleBarBackground;
                requestLayout();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    @SuppressLint("SetTextI18n")
    public HandyTitleBar setTitleBarContent(CharSequence title) {
        mainTextView.setVisibility(View.VISIBLE);
        subTextView.setVisibility(View.VISIBLE);
        int index = title.toString().indexOf("\n");
        if (index > 0) {
            contentLayout.setOrientation(LinearLayout.VERTICAL);
            mainTextView.setText(title.subSequence(0, index));
            subTextView.setText(title.subSequence(index + 1, title.length()));
        } else {
            index = title.toString().indexOf("\t");
            if (index > 0) {
                contentLayout.setOrientation(LinearLayout.HORIZONTAL);
                mainTextView.setText(title.subSequence(0, index));
                subTextView.setText("  " + title.subSequence(index + 1, title.length()));
            } else {
                mainTextView.setText(title);
                subTextView.setVisibility(View.GONE);
            }
        }
        return this;
    }

    public HandyTitleBar setMainText(String mainText) {
        this.mainText = mainText;
        mainTextView.setText(mainText);
        requestLayout();
        return this;
    }

    public HandyTitleBar setMainTextSize(float mainTextSize) {
        if (mainTextSize >= 0) {
            this.mainTextSize = spTopx(mainTextSize);
            mainTextView.setTextSize(mainTextSize);
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setMainTextColor(@ColorInt int mainTextColor) {
        if (mainTextColor >= 0) {
            mainTextView.setTextColor(mainTextColor);
            this.mainTextColor = mainTextColor;
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setMainTextBackgroundColor(@ColorInt int mainTextBackgroundColor) {
        if (mainTextBackgroundColor >= 0) {
            mainTextView.setBackgroundColor(mainTextBackgroundColor);
            this.mainTextBackgroundColor = mainTextBackgroundColor;
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setSubText(String subText) {
        this.subText = subText;
        subTextView.setText(subText);
        requestLayout();
        return this;
    }

    public HandyTitleBar setSubTextSize(float subTextSize) {
        if (subTextSize >= 0) {
            this.subTextSize = spTopx(subTextSize);
            subTextView.setTextSize(subTextSize);
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setSubTextColor(@ColorInt int subTextColor) {
        if (subTextColor >= 0) {
            subTextView.setTextColor(subTextColor);
            this.subTextColor = subTextColor;
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setSubTextBackgroundColor(@ColorInt int subTextBackgroundColor) {
        if (subTextBackgroundColor >= 0) {
            subTextView.setBackgroundColor(subTextBackgroundColor);
            this.subTextBackgroundColor = subTextBackgroundColor;
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setBottomLineHeight(int bottomLineHeight) {
        if (bottomLineHeight >= 0) {
            this.bottomLineHeight = bottomLineHeight;
            requestLayout();
        }
        return this;
    }

    public HandyTitleBar setBottomLineColor(int bottomLineColor) {
        if (bottomLineColor >= 0) {
            try {
                bottomLineView.setBackgroundColor(bottomLineColor);
                this.bottomLineColor = bottomLineColor;
                requestLayout();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }
}
