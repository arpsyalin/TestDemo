package com.lyl.testdemo;

import java.util.ArrayList;
import java.util.List;


public class HttpMethod {
    private static HttpMethod instance;
    List<RetrofitFactory> retrofitFactories;

    private HttpMethod() {
        retrofitFactories = new ArrayList<>();
    }

    public static HttpMethod getInstance() {
        if (instance == null) {
            synchronized (HttpMethod.class) {
                if (instance == null) {
                    instance = new HttpMethod();
                }
            }
        }
        return instance;
    }

    HttpMethod put(RetrofitFactory retrofitFactory) {
        retrofitFactories.add(retrofitFactory);
        return this;
    }

    <T> T get(Class<T> tClass) throws Exception {
        if (retrofitFactories.size() > 0) {
            for (RetrofitFactory retrofitFactory : retrofitFactories) {
                return retrofitFactory.getT(tClass);
            }
        } else {
            throw new Exception("please put RetrofitFactory!");
        }
        return null;
    }
}
