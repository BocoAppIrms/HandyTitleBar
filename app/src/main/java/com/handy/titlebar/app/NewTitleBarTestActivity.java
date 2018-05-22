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

        final HandyTitleBar handyTitleBar = findViewById(R.id.titlebarNew);
        handyTitleBar.addLeftAction(new HandyTitleBar.BaseAction(handyTitleBar) {

            @Override
            public int setImageSrc() {
                return R.mipmap.ic_launcher;
            }

            @Override
            public void onClick() {
                Toast.makeText(NewTitleBarTestActivity.this, "111", Toast.LENGTH_SHORT).show();
            }
        });
        handyTitleBar.getRightActionsLayout().setBackgroundColor(Color.RED);

    }
}
