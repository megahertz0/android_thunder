package com.xunlei.downloadprovider.frame.user.account;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;

// compiled from: ActionSheetHelper.java
public final class a {

    // compiled from: ActionSheetHelper.java
    public static interface a {
        void a(ImageView imageView, ImageView imageView2);
    }

    // compiled from: ActionSheetHelper.java
    public static interface b {
        void a(int i);
    }

    public static Dialog a(Context context, int i) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(i, null);
        inflate.setMinimumWidth(10000);
        Dialog dialog = new Dialog(context, 2131427577);
        LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = -1000;
        attributes.gravity = 80;
        dialog.onWindowAttributesChanged(attributes);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        return dialog;
    }
}
