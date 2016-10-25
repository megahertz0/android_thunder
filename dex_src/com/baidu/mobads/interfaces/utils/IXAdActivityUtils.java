package com.baidu.mobads.interfaces.utils;

import android.app.Activity;
import android.content.DialogInterface.OnClickListener;

public interface IXAdActivityUtils {
    Boolean isFullScreen(Activity activity);

    void showAlertDialog(Activity activity, String str, String str2, String str3, String str4, boolean z, OnClickListener onClickListener, OnClickListener onClickListener2);
}
