package com.handy.titlebar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private int actionImageSrc;
    private float actionImageSize;

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

    public TitleBarNew(Context context) {
        this(context, null);
    }

    public TitleBarNew(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("CustomViewStyleable")
    public TitleBarNew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        displayWidth = getResources().getDisplayMetrics().widthPixels;

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
        subTextColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_subTextColor, Color.BLACK);
        subTextBackgroundColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_subTextBackgroundColor, Color.TRANSPARENT);

        bottomLineHeight = (int) typedArray.getDimension(R.styleable.HandyTitleBarStyleable_bottomLineHeight, 0);
        bottomLineColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_bottomLineColor, Color.GRAY);

        actionPadding = typedArray.getDimension(R.styleable.HandyTitleBarStyleable_actionPadding, dpTopx(4));
        actionTextSize = typedArray.getDimension(R.styleable.HandyTitleBarStyleable_actionTextSize, 13);
        actionTextColor = typedArray.getColor(R.styleable.HandyTitleBarStyleable_actionTextColor, Color.BLACK);
        actionImageSrc = typedArray.getResourceId(R.styleable.HandyTitleBarStyleable_actionImageSrc, 0);
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
        leftActionsLayout.setPadding((int) actionPadding, 0, (int) actionPadding, 0);
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
        rightActionsLayout.setPadding((int) actionPadding, 0, (int) actionPadding, 0);
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
    public View addLeftAction(TitleBarNew.BaseAction action) {
        final int index = leftActionsLayout.getChildCount();
        return addLeftAction(action, index);
    }

    /**
     * 在左侧容器内指定的位置新增动作按钮
     */
    public View addLeftAction(TitleBarNew.BaseAction action, int index) {
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
    public View addRightAction(TitleBarNew.BaseAction action) {
        final int index = rightActionsLayout.getChildCount();
        return addRightAction(action, index);
    }

    /**
     * 在右侧容器内指定的位置新增动作按钮
     */
    public View addRightAction(TitleBarNew.BaseAction action, int index) {
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
    private View inflateAction(TitleBarNew.BaseAction action) {
        LinearLayout view = new LinearLayout(getContext());
        view.setGravity(Gravity.CENTER_VERTICAL);
        view.setPadding((int) actionPadding, 0, (int) actionPadding, 0);
        view.setTag(action);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final Object tag = v.getTag();
                if (tag instanceof TitleBarNew.BaseAction) {
                    final TitleBarNew.BaseAction action = (TitleBarNew.BaseAction) tag;
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
            //动作按钮中文字举例图片4dp
            text.setPadding(dpTopx(4), 0, 0, 0);
            text.setTextSize(action.setTextSize());
            text.setTextColor(action.setTextColor());
            view.addView(text);
        }
        return view;
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
     */
    private int dpTopx(int dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 自定义对象类
     */
    public static abstract class BaseAction {

        private View actionView;
        private TitleBarNew titleBarNew;

        public BaseAction(TitleBarNew titleBarNew) {
            this.titleBarNew = titleBarNew;
        }

        public abstract void onClick();

        public String setText() {
            return null;
        }

        public int setTextColor() {
            return titleBarNew.actionTextColor;
        }

        public int setTextSize() {
            return (int) titleBarNew.actionTextSize;
        }

        public int setImageSrc() {
            return titleBarNew.actionImageSrc;
        }

        public int setImageSize() {
            return (int) titleBarNew.actionImageSize;
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
}
