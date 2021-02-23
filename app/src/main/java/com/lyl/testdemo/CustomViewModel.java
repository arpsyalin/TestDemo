package com.lyl.testdemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CustomViewModel extends ViewModel {
    private MutableLiveData<String> mElapsedTimeStr = new MutableLiveData<>();

}
