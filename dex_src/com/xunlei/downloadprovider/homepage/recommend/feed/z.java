package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.view.WindowManager.LayoutParams;
import com.xunlei.downloadprovider.a.g;

// compiled from: FeedDataUtils.java
public final class z implements OnShowListener {
    final /* synthetic */ Context a;

    public z(Context context) {
        this.a = context;
    }

    public final void onShow(DialogInterface dialogInterface) {
        Dialog dialog = (Dialog) dialogInterface;
        LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = g.a(this.a, 314.0f);
        dialog.getWindow().setAttributes(attributes);
    }
}
