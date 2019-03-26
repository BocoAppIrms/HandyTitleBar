package com.handy.titlebar.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.handy.titlebar.HandyTitleBar;

public class TestActivity extends AppCompatActivity {

    private HandyTitleBar handyTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        handyTitleBar = findViewById(R.id.titlebarNew);
        handyTitleBar.showCustomStatusBar(TestActivity.this);
        handyTitleBar.setTitleBarContent("主标题\n副标题");
        handyTitleBar.setTitleBarBackground(R.color.colorPrimary);
        handyTitleBar.addLeftAction(new HandyTitleBar.BaseAction(handyTitleBar) {
            {
                this.setImageSrc(R.drawable.hdb_back_n);
                this.setText(null);
                this.setTextColor(Color.WHITE);
                this.setTextSize(15);
                this.setImageSize(10);
            }

            @Override
            public void onClick() {
                finish();
            }
        });
        handyTitleBar.addRightAction(new HandyTitleBar.BaseAction(handyTitleBar) {
            {
                this.setImageSrc(R.drawable.hdb_menu_n);
            }

            @Override
            public void onClick() {
                handyTitleBar.getContentLayout().setOrientation(LinearLayout.HORIZONTAL);
            }
        });
        handyTitleBar.addRightAction(new HandyTitleBar.BaseAction(handyTitleBar) {
            {
                this.setImageSrc(R.drawable.hdb_setting_n);
            }

            @Override
            public void onClick() {
                handyTitleBar.getContentLayout().setOrientation(LinearLayout.VERTICAL);
            }
        });
    }
}
