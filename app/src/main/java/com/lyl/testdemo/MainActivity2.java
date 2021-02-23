package com.lyl.testdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lyl.testeventbus.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    View view;
    boolean isR = false;

    public void xx(View view) {
        this.view = view;
        if (isR) {
            isR = true;
            EventBus.getDefault().unregister(this);
        }
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
    public void r2(final String aa) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((Button) view).setText(((Button) view).getText().toString() + "rev1接到了:" + aa + "\n");
            }
        });
    }
}