package com.lyl.testdemo;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UserService {
    @GET("hello")
    Observable<ServiceResponse<String>> helloTest();
}
