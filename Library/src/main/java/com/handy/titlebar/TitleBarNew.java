package com.handy.titlebar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

/**
 * 标题栏
 *
 * @author LiuJie https://github.com/Handy045
 * @description 类功能内容
 * @date Created in 2018/5/19 下午5:55
 * @modified By LiuJie
 */
public class TitleBarNew extends ViewGroup {

    private int statusBarHeight;
    private int statusBarBackgroundColor;
    private boolean isImmersiveStatusBar;

    private int topLineHeight;
    private int topLineColor;

    private int titleBarMargin;
    private int titleBarMarginTop;
    private int titleBarMarginLeft;
    private int titleBarMarginRight;
    private int titleBarMarginBottom;
    private int titleBarWidth;
    private int titleBarHeight;
    private int titleBarBackground;
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

    private float actionPadding;
    private float actionTextSize;
    private int actionTextColor;
    private Drawable actionImageSrc;
    private float actionImageSize;

    private View statusBar;
    private View topLineView;
    private View titleBar;
    private LinearLayout leftActionsLayout;
    private LinearLayout contentLayout;
    private MarqueeTextView mainTextView;
    private MarqueeTextView subTextView;
    private LinearLayout rightActionsLayout;
    private View bottomLineView;

    public TitleBarNew(Context context) {
        this(context, null);
    }

    public TitleBarNew(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("CustomViewStyleable")
    public TitleBarNew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HandyTitleBarStyleable);
        statusBarHeight = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_statusBarHeight, getStatusBarHeight(context));
        statusBarBackgroundColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_statusBarBackgroundColor, 0xFF0091ea);
        isImmersiveStatusBar = typedArray.getBoolean(R.styleable.HandyTitleBarStyleable_isImmersiveStatusBar, false);

        topLineHeight = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_topLineHeight, 0);
        topLineColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_topLineColor, Color.GRAY);

        titleBarMargin = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_titleBarMargin, 0);
        titleBarMarginTop = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_titleBarMarginTop, 0);
        titleBarMarginLeft = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_titleBarMarginLeft, 0);
        titleBarMarginRight = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_titleBarMarginRight, 0);
        titleBarMarginBottom = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_titleBarMarginBottom, 0);
        titleBarHeight = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_titleBarHeight, dpTopx(48));
        titleBarBackground = typedArray.getColor(R.styleable.HandyTitleBarStyleable_titleBarBackground, Color.WHITE);

        mainText = typedArray.getString(R.styleable.HandyTitleBarStyleable_mainText);
        mainTextSize = typedArray.getDimension(R.styleable.HandyTitleBarStyleable_mainTextSize, 17);
        mainTextColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_mainTextColor, Color.BLACK);
        mainTextBackgroundColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_mainTextBackgroundColor, Color.TRANSPARENT);
        subText = typedArray.getString(R.styleable.HandyTitleBarStyleable_subText);
        subTextSize = typedArray.getDimension(R.styleable.HandyTitleBarStyleable_subTextSize, 11);
        subTextColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_subTextColor, Color.WHITE);
        subTextBackgroundColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_subTextBackgroundColor, Color.TRANSPARENT);

        bottomLineHeight = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_bottomLineHeight, 0);
        bottomLineColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_bottomLineColor, Color.GRAY);

        actionPadding = typedArray.getDimension(R.styleable.HandyTitleBarStyleable_actionPadding, dpTopx(4));
        actionTextSize = typedArray.getDimension(R.styleable.HandyTitleBarStyleable_actionTextSize, 13);
        actionTextColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_actionTextColor, Color.WHITE);
        actionImageSrc = typedArray.getDrawable(R.styleable.HandyTitleBarStyleable_actionImageSrc);
        actionImageSize = typedArray.getDimension(R.styleable.HandyTitleBarStyleable_actionImageSize, 18);
        typedArray.recycle();

        initView(context);
    }

    private void initView(Context context) {
        statusBar = new View(context);
        statusBar.setBackgroundColor(statusBarBackgroundColor);

        topLineView = new View(context);
        topLineView.setBackgroundColor(topLineColor);

        titleBar = new View(context);
        titleBar.setBackgroundColor(titleBarBackground);

        leftActionsLayout = new LinearLayout(context);
        leftActionsLayout.setOrientation(LinearLayout.HORIZONTAL);
        leftActionsLayout.setPadding(titleBarMarginLeft, titleBarMarginTop, 0, titleBarMarginBottom);
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
        mainTextView.setVisibility((mainText == null || mainText.equals("")) ? GONE : VISIBLE);

        subTextView = new MarqueeTextView(context);
        subTextView.setText(subText);
        subTextView.setTextColor(subTextColor);
        subTextView.setTextSize(subTextSize);
        subTextView.setBackgroundColor(subTextBackgroundColor);
        subTextView.setSingleLine();
        subTextView.setGravity(Gravity.CENTER);
        subTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        subTextView.setVisibility((subText == null || subText.equals("")) ? GONE : VISIBLE);

        contentLayout = new LinearLayout(context);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setPadding(0, titleBarMarginTop, 0, titleBarMarginBottom);
        contentLayout.setGravity(Gravity.CENTER);
        contentLayout.setBackgroundColor(Color.TRANSPARENT);
        contentLayout.addView(mainTextView);
        contentLayout.addView(subTextView);

        rightActionsLayout = new LinearLayout(context);
        rightActionsLayout.setOrientation(LinearLayout.HORIZONTAL);
        rightActionsLayout.setPadding(0, titleBarMarginTop, titleBarMarginRight, titleBarMarginBottom);
        rightActionsLayout.setBackgroundColor(Color.TRANSPARENT);
        rightActionsLayout.setGravity(Gravity.CENTER);

        bottomLineView = new View(context);
        bottomLineView.setBackgroundColor(bottomLineColor);

        addView(leftActionsLayout, new LayoutParams(LayoutParams.WRAP_CONTENT, titleBarHeight));
        addView(contentLayout, new LayoutParams(LayoutParams.MATCH_PARENT, titleBarHeight));
        addView(rightActionsLayout, new LayoutParams(LayoutParams.WRAP_CONTENT, titleBarHeight));
        addView(statusBar, new LayoutParams(LayoutParams.MATCH_PARENT, statusBarHeight));
        addView(topLineView, new LayoutParams(LayoutParams.MATCH_PARENT, topLineHeight));
        addView(titleBar, new LayoutParams(LayoutParams.MATCH_PARENT, titleBarHeight));
        addView(bottomLineView, new LayoutParams(LayoutParams.MATCH_PARENT, bottomLineHeight));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode != MeasureSpec.AT_MOST) {
            titleBarHeight = heightSize;
        }

        measureChild(statusBar, widthMeasureSpec, statusBarHeight);
        measureChild(topLineView, widthMeasureSpec, topLineHeight);
        if (leftActionsLayout.getMeasuredWidth() > rightActionsLayout.getMeasuredWidth()) {
            rightActionsLayout.measure(MeasureSpec.makeMeasureSpec(leftActionsLayout.getMeasuredWidth(), MeasureSpec.EXACTLY), titleBarHeight);
        } else {
            leftActionsLayout.measure(MeasureSpec.makeMeasureSpec(rightActionsLayout.getMeasuredWidth(), MeasureSpec.EXACTLY), titleBarHeight);
        }
        measureChild(titleBar, titleBarWidth > 0 ? titleBarWidth : widthMeasureSpec, titleBarHeight);
        measureChild(bottomLineView, widthMeasureSpec, bottomLineHeight);

        setMeasuredDimension(widthMode != MeasureSpec.AT_MOST ? widthSize : widthMeasureSpec, statusBarHeight + topLineHeight + titleBarHeight + bottomLineHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        statusBar.layout(0, 0, statusBar.getMeasuredWidth(), statusBar.getMeasuredHeight());
        topLineView.layout(0, statusBar.getMeasuredHeight(), getMeasuredWidth(), statusBar.getMeasuredHeight() + topLineView.getMeasuredHeight());
        titleBar.layout(0, statusBar.getMeasuredHeight() + topLineView.getMeasuredHeight(), leftActionsLayout.getMeasuredWidth() + rightActionsLayout.getMeasuredWidth() + contentLayout.getMeasuredWidth(), getMeasuredHeight());
        leftActionsLayout.layout(0, statusBar.getMeasuredHeight() + topLineView.getMeasuredHeight(), leftActionsLayout.getMeasuredWidth(), getMeasuredHeight());
        rightActionsLayout.layout(getMeasuredWidth() - rightActionsLayout.getMeasuredWidth(), statusBar.getMeasuredHeight() + topLineView.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
        if (leftActionsLayout.getMeasuredWidth() > rightActionsLayout.getMeasuredWidth()) {
            contentLayout.layout(leftActionsLayout.getMeasuredWidth(), statusBar.getMeasuredHeight() + topLineView.getMeasuredHeight(), getMeasuredWidth() - leftActionsLayout.getMeasuredWidth(), getMeasuredHeight());
        } else {
            contentLayout.layout(rightActionsLayout.getMeasuredWidth(), statusBar.getMeasuredHeight() + topLineView.getMeasuredHeight(), getMeasuredWidth() - rightActionsLayout.getMeasuredWidth(), getMeasuredHeight());
        }
        bottomLineView.layout(0, statusBar.getMeasuredHeight() + topLineView.getMeasuredHeight() + getMeasuredHeight(), getMeasuredWidth(), bottomLineView.getMeasuredHeight());
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
     * 带跑马灯功能的TextView
     */
    class MarqueeTextView extends android.support.v7.widget.AppCompatTextView {
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
     *
     * @param dpValue dp值
     */
    public int dpTopx(int dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(final float spValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
