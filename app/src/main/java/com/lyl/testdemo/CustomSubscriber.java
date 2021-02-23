package com.lyl.testdemo;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class CustomSubscriber<T> implements Observer<ServiceResponse<T>> {
    public CustomSubscriber() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        CustomDialogManager.getInstance().show();
    }

    @Override
    public void onNext(ServiceResponse<T> tServiceResponse) {
        if (tServiceResponse.result == 200) {
            onSuccess(tServiceResponse.data);
        } else {
            if (!responseFail(tServiceResponse.result)) {
                Log.e("", "code" + tServiceResponse.result);
            }
        }
    }

    /**
     * 返回失敗
     *
     * @return false代表不需要自定義處理
     */
    public boolean responseFail(int code) {
        return false;
    }

    public abstract void onSuccess(T result);

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {
        CustomDialogManager.getInstance().dismiss();
    }
}
