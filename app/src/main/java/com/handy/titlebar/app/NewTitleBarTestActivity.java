package com.handy.titlebar.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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
        final HandyTitleBar handyTitleBar2 = findViewById(R.id.titlebarNew2);
        final HandyTitleBar handyTitleBar3 = findViewById(R.id.titlebarNew3);
        handyTitleBar1.addLeftAction(new HandyTitleBar.BaseAction(handyTitleBar1) {

            @Override
            public String setText() {
                return "返回";
            }

            @Override
            public void onClick() {
                Toast.makeText(NewTitleBarTestActivity.this, "111", Toast.LENGTH_SHORT).show();
            }
        });

        handyTitleBar2.addLeftAction(new HandyTitleBar.BaseAction(handyTitleBar2) {

            @Override
            public int setImageSrc() {
                return R.drawable.hdb_back_c;
            }

            @Override
            public void onClick() {
                Toast.makeText(NewTitleBarTestActivity.this, "111", Toast.LENGTH_SHORT).show();
            }
        });

        handyTitleBar2.addRightAction(new HandyTitleBar.BaseAction(handyTitleBar2) {

            @Override
            public int setImageSrc() {
                return R.drawable.hdb_menu_c;
            }

            @Override
            public void onClick() {
                Toast.makeText(NewTitleBarTestActivity.this, "111", Toast.LENGTH_SHORT).show();
            }
        });
        handyTitleBar2.addRightAction(new HandyTitleBar.BaseAction(handyTitleBar2) {

            @Override
            public int setImageSrc() {
                return R.drawable.hdb_setting_c;
            }

            @Override
            public void onClick() {
                Toast.makeText(NewTitleBarTestActivity.this, "111", Toast.LENGTH_SHORT).show();
            }
        });

        handyTitleBar3.addLeftAction(new HandyTitleBar.BaseAction(handyTitleBar3) {

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
        handyTitleBar3.addRightAction(new HandyTitleBar.BaseAction(handyTitleBar3) {

            @Override
            public int setImageSrc() {
                return R.drawable.hdb_menu_n;
            }

            @Override
            public void onClick() {
            }
        });
        handyTitleBar3.addRightAction(new HandyTitleBar.BaseAction(handyTitleBar3) {

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
