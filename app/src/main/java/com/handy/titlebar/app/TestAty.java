package com.handy.titlebar.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.handy.widget.titlebar.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestAty extends AppCompatActivity {

    @BindView(R.id.main_dynamic)
    Button mainDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_aty);
        ButterKnife.bind(this);

        /* 初始化标题栏 */
        TitleBar titleBar = (TitleBar) findViewById(R.id.base_mytitlebar);
        titleBar.setTitle("MyTitleBar 使用样例");
        titleBar.setImmersive(TestAty.this, true);
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

    @OnClick({R.id.main_dynamic})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_dynamic:
                Intent intent = new Intent(TestAty.this, DynamicAty.class);
                startActivity(intent);
                break;
        }
    }
}
