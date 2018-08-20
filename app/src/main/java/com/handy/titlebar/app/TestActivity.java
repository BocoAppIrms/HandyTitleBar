package com.handy.titlebar.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.handy.titlebar.HandyTitleBar;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final HandyTitleBar handyTitleBar1 = findViewById(R.id.titlebarNew);
        handyTitleBar1.showCustomStatusBar(TestActivity.this);
        handyTitleBar1.setTitleBarContent("主标题\n副标题");
        handyTitleBar1.addLeftAction(new HandyTitleBar.BaseAction(handyTitleBar1) {
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
        handyTitleBar1.addRightAction(new HandyTitleBar.BaseAction(handyTitleBar1) {
            {
                this.setImageSrc(R.drawable.hdb_menu_n);
            }

            @Override
            public void onClick() {
            }
        });
        handyTitleBar1.addRightAction(new HandyTitleBar.BaseAction(handyTitleBar1) {
            {
                this.setImageSrc(R.drawable.hdb_setting_n);
            }

            @Override
            public void onClick() {
            }
        });
    }
}
