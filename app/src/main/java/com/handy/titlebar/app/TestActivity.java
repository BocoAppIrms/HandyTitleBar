package com.handy.titlebar.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.handy.titlebar.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.main_dynamic)
    Button mainDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        /* 初始化标题栏 */
        TitleBar titleBar = findViewById(R.id.base_mytitlebar);
        titleBar.setTitle("MyTitleBar 使用样例");
        titleBar.setImmersive(TestActivity.this, true);
        titleBar.setBackgroundColor(Color.parseColor("#1f1f1f"));
        titleBar.addLeftAction(new TitleBar.Action() {
            @Override
            public void onClick() {
                finish();
            }

            @Override
            public String setText() {
                return "退出";
            }
        });
    }

    @OnClick({R.id.main_dynamic, R.id.main_newtitlebar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_dynamic:
                Intent intent = new Intent(TestActivity.this, TitleBarTestActivity.class);
                startActivity(intent);
                break;
            case R.id.main_newtitlebar:
                Intent intent1 = new Intent(TestActivity.this, NewTitleBarTestActivity.class);
                startActivity(intent1);
                break;
            default:
        }
    }
}
