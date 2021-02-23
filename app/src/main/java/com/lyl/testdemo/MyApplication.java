package com.lyl.testdemo;

import android.app.Application;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Observable.create(new ObservableOnSubscribe<OkHttpClient>() {
            @Override
            public void subscribe(ObservableEmitter<OkHttpClient> emitter) throws Exception {
                OkHttpFactory okHttpFactory = new OkHttpFactory();
                emitter.onNext(okHttpFactory.build());
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Consumer<OkHttpClient>() {
            @Override
            public void accept(OkHttpClient okHttpClient) throws Exception {
                RetrofitFactory.Builder builder = new RetrofitFactory.Builder();
                builder.baseUrl("http://192.168.1.111:8080/").okHttpClient(okHttpClient).callAdapterFactory(RxJava2CallAdapterFactory.create()).converterFactory(GsonConverterFactory.create(gson));
                try {
                    RetrofitFactory retrofitFactory = builder.build();
                    builder.service(UserService.class);
                    HttpMethod.getInstance().put(retrofitFactory);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
