package com.handy.titlebar.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.handy.titlebar.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiuJie on 2016/11/1.
 */
public class TitleBarTestActivity extends AppCompatActivity {

    @BindView(R.id.base_mytitlebar)
    TitleBar titleBar;
    @BindView(R.id.content_main_edt)
    EditText contentMainEdt;
    @BindView(R.id.content_second_edt)
    EditText contentSecondEdt;
    @BindView(R.id.content_style_btn)
    Button contentStyleBtn;
    @BindView(R.id.content_setting_btn)
    Button contentSettingBtn;
    @BindView(R.id.main_size_edt)
    EditText mainSizeEdt;
    @BindView(R.id.main_size_btn)
    Button mainSizeBtn;
    @BindView(R.id.second_size_edt)
    EditText secondSizeEdt;
    @BindView(R.id.second_size_btn)
    Button secondSizeBtn;
    @BindView(R.id.statebar_background_btn)
    Button statebarBackgroundBtn;
    @BindView(R.id.titlebar_background_btn)
    Button titlebarBackgroundBtn;
    @BindView(R.id.titlebar_recover_btn)
    Button titlebarRecoverBtn;
    @BindView(R.id.mytitlebar_htight_edt)
    EditText mytitlebarHtightEdt;
    @BindView(R.id.mytitlebar_setting_btn)
    Button mytitlebarSettingBtn;
    @BindView(R.id.statebar_immersive_btn)
    Button statebarImmersiveBtn;
    @BindView(R.id.topline_background_btn)
    Button toplineBackgroundBtn;
    @BindView(R.id.topline_htight_edt)
    EditText toplineHtightEdt;
    @BindView(R.id.topline_setting_btn)
    Button toplineSettingBtn;
    @BindView(R.id.bottomline_background_btn)
    Button bottomlineBackgroundBtn;
    @BindView(R.id.bottomline_htight_edt)
    EditText bottomlineHtightEdt;
    @BindView(R.id.bottomline_setting_btn)
    Button bottomlineSettingBtn;
    @BindView(R.id.leftaction_add_btn)
    Button leftactionAddBtn;
    @BindView(R.id.leftaction_remove_btn)
    Button leftactionRemoveBtn;
    @BindView(R.id.rightaction_add_btn)
    Button rightactionAddBtn;
    @BindView(R.id.rightaction_remove_btn)
    Button rightactionRemoveBtn;
    @BindView(R.id.action_content_edt)
    EditText actionContentEdt;
    @BindView(R.id.action_textsize_edt)
    EditText actionTextsizeEdt;
    @BindView(R.id.action_imgsize_edt)
    EditText actionImgsizeEdt;
    @BindView(R.id.action_clicktoast_edt)
    EditText actionClicktoastEdt;
    @BindView(R.id.action_add_btn)
    Button actionAddBtn;
    @BindView(R.id.action_remove_btn)
    Button actionRemoveBtn;
    @BindView(R.id.action_initialize_btn)
    Button actionInitializeBtn;
    @BindView(R.id.main_textcolor_btn)
    Button mainTextColorBtn;
    @BindView(R.id.second_textcolor_btn)
    Button secondTextColorBtn;
    @BindView(R.id.main_background_btn)
    Button mainBackgroundBtn;
    @BindView(R.id.second_background_btn)
    Button secondBackgroundBtn;

    private TitleBar.Action action = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_titlebar);
        ButterKnife.bind(this);
        titleBar.setTitle("主标题" + "\n" + "副标题");
        titleBar.setImmersive(TitleBarTestActivity.this, true);
        titleBar.setBackgroundColor(Color.parseColor("#1f1f1f"));
        titleBar.addLeftAction(new TitleBar.Action() {
            @Override
            public void onClick() {
                finish();
            }

            @Override
            public String setText() {
                return "返回";
            }
        });
    }

    @OnClick({R.id.content_style_btn, R.id.content_setting_btn, R.id.main_size_btn, R.id.statebar_immersive_btn, R.id.second_size_btn, R.id.statebar_background_btn, R.id.titlebar_background_btn, R.id.titlebar_recover_btn, R.id.mytitlebar_setting_btn, R.id.topline_background_btn, R.id.topline_setting_btn, R.id.bottomline_background_btn, R.id.bottomline_setting_btn, R.id.leftaction_add_btn, R.id.leftaction_remove_btn, R.id.rightaction_add_btn, R.id.rightaction_remove_btn, R.id.action_add_btn, R.id.action_remove_btn, R.id.action_initialize_btn, R.id.main_textcolor_btn, R.id.second_textcolor_btn, R.id.main_background_btn, R.id.second_background_btn})
    public void onClick(View view) {
        String contentMain = contentMainEdt.getText().toString().length() == 0 ? "主标题" : contentMainEdt.getText().toString();
        String contentSecond = contentSecondEdt.getText().toString().length() == 0 ? "副标题" : contentSecondEdt.getText().toString();

        switch (view.getId()) {
            case R.id.content_style_btn:// 标题格式
                if (contentStyleBtn.getText().equals("标题格式(竖)")) {
                    contentStyleBtn.setText("标题格式(横)");
                    titleBar.setTitle(contentMain + "\n" + contentSecond);
                } else if (contentStyleBtn.getText().equals("标题格式(横)")) {
                    contentStyleBtn.setText("标题格式(竖)");
                    titleBar.setTitle(contentMain + "\t" + contentSecond);
                }
                break;
            case R.id.content_setting_btn:// 标题内容
                if (contentSettingBtn.getText().equals("显示主标题")) {
                    contentSettingBtn.setText("显示全标题");
                    titleBar.setTitle(contentMain);
                } else if (contentSettingBtn.getText().equals("显示全标题")) {
                    contentSettingBtn.setText("显示主标题");
                    if (contentStyleBtn.getText().equals("标题格式(竖)")) {
                        titleBar.setTitle(contentMain + "\t" + contentSecond);
                    } else if (contentStyleBtn.getText().equals("标题格式(横)")) {
                        titleBar.setTitle(contentMain + "\n" + contentSecond);
                    }
                }
                break;
            case R.id.main_size_btn:// 主标题大小
                titleBar.setTitleSize(mainSizeEdt.getText().toString().length() == 0 ? TitleBar.DEFAULT_MAIN_TEXT_SIZE : Float.valueOf(mainSizeEdt.getText().toString()));
                break;
            case R.id.second_size_btn:// 副标题大小
                titleBar.setSubTitleSize(secondSizeEdt.getText().toString().length() == 0 ? TitleBar.DEFAULT_SUB_TEXT_SIZE : Float.valueOf(secondSizeEdt.getText().toString()));
                break;
            case R.id.main_textcolor_btn:// 改变主标题字体
                titleBar.setTitleColor(R.color.myTitleBar_TestColor_Title);
                break;
            case R.id.second_textcolor_btn:// 改变副标题字体
                titleBar.setSubTitleColor(R.color.myTitleBar_TestColor_SubTitle);
                break;
            case R.id.main_background_btn:// 改变主标题颜色
                titleBar.setTitleBackground(R.color.myTitleBar_TestColor_TitleBG);
                break;
            case R.id.second_background_btn:// 改变副标题颜色
                titleBar.setSubTitleBackground(R.color.myTitleBar_TestColor_SubTitleBG);
                break;
            case R.id.statebar_background_btn:// 改变状态栏颜色
                titleBar.setStatusBarBackground(R.color.myTitleBar_TestColor_StatuBar);
                break;
            case R.id.titlebar_background_btn:// 改变标题栏颜色
                titleBar.setTitleBarBackground(R.color.myTitleBar_TestColor_TitleBar);// myTitleBar.setTitleBarBackground(R.mipmap.ic_launcher);
                break;
            case R.id.titlebar_recover_btn:// 恢复标题栏颜色
                titleBar.setTitleColor(Color.WHITE);
                titleBar.setSubTitleColor(Color.WHITE);
                titleBar.setBackgroundColor(Color.parseColor("#1f1f1f"));
                titleBar.setTitleBackground(R.color.myTitleBar_TestColor_Transparent);
                titleBar.setSubTitleBackground(R.color.myTitleBar_TestColor_Transparent);
                titleBar.setStatusBarBackground(R.color.myTitleBar_TestColor_Transparent);
                break;
            case R.id.statebar_immersive_btn:// 设置浸入式效果
                if (statebarImmersiveBtn.getText().equals("打开浸入式效果")) {
                    statebarImmersiveBtn.setText("关闭浸入式效果");
                    titleBar.setImmersive(TitleBarTestActivity.this, true);
                } else if (statebarImmersiveBtn.getText().equals("关闭浸入式效果")) {
                    statebarImmersiveBtn.setText("打开浸入式效果");
                    titleBar.setImmersive(TitleBarTestActivity.this, false);
                }
                break;
            case R.id.mytitlebar_setting_btn:// 设置标题栏高度
                titleBar.setTitleBarHeight(mytitlebarHtightEdt.getText().toString().length() == 0 ? TitleBar.DEFAULT_TITLEBAR_HEIGHT : Integer.valueOf(mytitlebarHtightEdt.getText().toString()));
                break;
            case R.id.topline_background_btn:// 设置顶部分割线颜色
                titleBar.setTopLineBackground(R.color.myTitleBar_TestColor_TopLine);
                break;
            case R.id.topline_setting_btn:// 设置底部分割线高度
                titleBar.setTopLineHeight(toplineHtightEdt.getText().toString().length() == 0 ? TitleBar.DEFAULT_TOPLINE_HEIGHT : Integer.valueOf(toplineHtightEdt.getText().toString()));
                break;
            case R.id.bottomline_background_btn:// 改变底部分割线颜色
                titleBar.setBottomLineBackground(R.color.myTitleBar_TestColor_BottomLine);
                break;
            case R.id.bottomline_setting_btn:// 改变底部分割线高度
                titleBar.setBottomLineHeight(bottomlineHtightEdt.getText().toString().length() == 0 ? TitleBar.DEFAULT_BOTTOMLINE_HEIGHT : Integer.valueOf(bottomlineHtightEdt.getText().toString()));
                break;
            case R.id.leftaction_add_btn:// 增加一个左侧按钮
                titleBar.addLeftAction(new TitleBar.Action() {
                    @Override
                    public void onClick() {

                    }

                    @Override
                    public String setText() {
                        return "L";
                    }
                });
                break;
            case R.id.leftaction_remove_btn:// 删除左侧按钮
                titleBar.removeAllLeftActions();
                titleBar.addLeftAction(new TitleBar.Action() {
                    @Override
                    public void onClick() {
                        finish();
                    }

                    @Override
                    public String setText() {
                        return "返回";
                    }
                });
                break;
            case R.id.rightaction_add_btn:// 增加一个右侧按钮
                titleBar.addRightAction(new TitleBar.Action() {
                    @Override
                    public void onClick() {

                    }

                    @Override
                    public String setText() {
                        return "R";
                    }
                });
                break;
            case R.id.rightaction_remove_btn:// 删除右侧按钮
                titleBar.removeAllRightActions();
                break;
            case R.id.action_add_btn:// 增加一个自定义按钮
                String Text = actionContentEdt.getText().toString().length() == 0 ? "Action" : actionContentEdt.getText().toString();
                int TextSize = actionTextsizeEdt.getText().toString().length() == 0 ? TitleBar.DEFAULT_TITLEBAR_HEIGHT : Integer.valueOf(actionTextsizeEdt.getText().toString());
                int img = R.mipmap.ic_launcher;
                int ImgSize = actionImgsizeEdt.getText().toString().length() == 0 ? TitleBar.DEFAULT_TITLEBAR_HEIGHT : Integer.valueOf(actionImgsizeEdt.getText().toString());
                String ClickToast = actionClicktoastEdt.getText().toString().length() == 0 ? "已点击" : actionClicktoastEdt.getText().toString();

                getAction(Text, TextSize, img, ImgSize, ClickToast);
                titleBar.addRightAction(action);
                break;
            case R.id.action_remove_btn:// 移除指定按钮
                titleBar.removerightAction(action);
                break;
            case R.id.action_initialize_btn:// 按钮初始化
                titleBar.removeAllLeftActions();
                titleBar.removeAllRightActions();
                titleBar.addLeftAction(new TitleBar.Action() {
                    @Override
                    public void onClick() {
                        finish();
                    }

                    @Override
                    public String setText() {
                        return "返回";
                    }
                });
                break;
        }
    }

    private void getAction(final String text, final int textSize, final int img, final int imgSize, final String clickToast) {
        action = new TitleBar.Action() {
            @Override
            public void onClick() {
                Toast.makeText(TitleBarTestActivity.this, clickToast, Toast.LENGTH_SHORT).show();
            }

            @Override
            public String setText() {
                return text;
            }

            @Override
            public int setTextSize() {
                return textSize;
            }

            @Override
            public int setTextColor() {
                return getResources().getColor(R.color.myTitleBar_TestColor_ActionText);// 文字颜色的设置需通过getResources方法
            }

            @Override
            public int setDrawable() {
                return img;
            }

            @Override
            public int setDrawableSize() {
                return imgSize;
            }
        };
    }
}
