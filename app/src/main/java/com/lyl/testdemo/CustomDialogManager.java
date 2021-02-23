package com.lyl.testdemo;

public class CustomDialogManager {
    static CustomDialogManager instance = new CustomDialogManager();
    LoadingPopup progressDialog;

    public static CustomDialogManager getInstance() {

        return instance;
    }

    public void show() {
        if (progressDialog == null) {
            progressDialog = new LoadingPopup(MyApplication.instance);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.showPopupWindow();
        }
    }

    public void dismiss() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
