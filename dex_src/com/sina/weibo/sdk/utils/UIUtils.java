package com.sina.weibo.sdk.utils;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.widget.Toast;
import com.xunlei.tdlive.R;

public class UIUtils {
    public static void showAlert(Context context, String str, String str2) {
        if (context != null) {
            new Builder(context).setTitle(str).setMessage(str2).create().show();
        }
    }

    public static void showAlert(Context context, int i, int i2) {
        if (context != null) {
            showAlert(context, context.getString(i), context.getString(i2));
        }
    }

    public static void showToast(Context context, int i, int i2) {
        if (context != null) {
            Toast.makeText(context, i, i2).show();
        }
    }

    public static void showToast(Context context, CharSequence charSequence, int i) {
        if (context != null) {
            Toast.makeText(context, charSequence, i).show();
        }
    }

    public static void showToastInCenter(Context context, int i, int i2) {
        if (context != null) {
            Toast makeText = Toast.makeText(context, i, i2);
            makeText.setGravity(R.styleable.Toolbar_maxButtonHeight, 0, 0);
            makeText.show();
        }
    }
}
