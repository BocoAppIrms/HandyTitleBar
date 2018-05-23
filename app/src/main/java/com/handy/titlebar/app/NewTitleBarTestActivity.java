package com.handy.titlebar.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.handy.titlebar.HandyTitleBar;

/**
 * 类名
 *
 * @author LiuJie https://github.com/Handy045
 * @description 类功能内容
 * @date Created in 2018/5/21 下午3:03
 * @modified By LiuJie
 */
public class NewTitleBarTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtitlebar);

        final HandyTitleBar handyTitleBar1 = findViewById(R.id.titlebarNew1);
        handyTitleBar1.setShowCustomStatusBar(NewTitleBarTestActivity.this, true);
        handyTitleBar1.addLeftAction(new HandyTitleBar.BaseAction(handyTitleBar1) {

            @Override
            public int setImageSrc() {
                return R.drawable.hdb_back_n;
            }

            @Override
            public String setText() {
                return "返回";
            }

            @Override
            public int setTextColor() {
                return Color.WHITE;
            }

            @Override
            public void onClick() {
            }
        });
        handyTitleBar1.addRightAction(new HandyTitleBar.BaseAction(handyTitleBar1) {

            @Override
            public int setImageSrc() {
                return R.drawable.hdb_menu_n;
            }

            @Override
            public void onClick() {
            }
        });
        handyTitleBar1.addRightAction(new HandyTitleBar.BaseAction(handyTitleBar1) {

            @Override
            public int setImageSrc() {
                return R.drawable.hdb_setting_n;
            }

            @Override
            public void onClick() {
            }
        });

    }
}
