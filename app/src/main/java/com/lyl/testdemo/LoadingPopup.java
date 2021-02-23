package com.lyl.testdemo;

import android.content.Context;
import android.view.View;

import com.lyl.testeventbus.R;

import razerdp.basepopup.BasePopupWindow;

public class LoadingPopup extends BasePopupWindow {
    public LoadingPopup(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        return View.inflate(this.getContext(), R.layout.loading, null);
    }
}
