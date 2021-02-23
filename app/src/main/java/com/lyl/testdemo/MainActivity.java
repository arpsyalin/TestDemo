package com.lyl.testdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lyl.testeventbus.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

//import io.reactivex.rxjava3.annotations.NonNull;
//import io.reactivex.rxjava3.core.Observable;
//import io.reactivex.rxjava3.core.ObservableEmitter;
//import io.reactivex.rxjava3.core.ObservableOnSubscribe;
//import io.reactivex.rxjava3.core.Observer;
//import io.reactivex.rxjava3.core.Scheduler;
//import io.reactivex.rxjava3.disposables.Disposable;
//import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.xxx);
        EventBus.getDefault().postSticky("注册前发送");
        EventBus.getDefault().register(this);
        EventBus.getDefault().post("注册后发送");
        BiometricManager.Authenticators.DEVICE_CREDENTIAL
//        Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//
//                e.onNext("xxxxx11xxxxxxx");
//                Log.e("", "subscribe"+Thread.currentThread());
//            }
//        }).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.e("", "onSubscribe");
//            }
//
//            @Override
//            public void onNext(@NonNull String s) {
//                Log.e("", "onNext" + s);
//                Log.e("", "onNext"+Thread.currentThread());
//                textView.setText(s);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//                Log.e("", "onError" + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.e("", "onComplete");
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 2)
    public void rev(String aa) {
        textView.setText(textView.getText().toString() + "rev接到了:" + aa + "\n");
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 1, sticky = true)
    public void rev1(String aa) {
        textView.setText(textView.getText().toString() + "rev1接到了:" + aa + "\n");
        EventBus.getDefault().removeStickyEvent(aa);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void r1(View view) {
        startActivity(new Intent(this, MainActivity2.class));
    }

    public void r2(View view) {
        try {
            UserService userService = HttpMethod.getInstance().get(UserService.class);
            userService.helloTest().unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CustomSubscriber<String>() {

                @Override
                public void onSuccess(String result) {
                    textView.setText(textView.getText().toString() + "網絡請求結果:" + result + "\n");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}