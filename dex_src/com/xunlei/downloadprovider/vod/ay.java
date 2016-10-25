package com.xunlei.downloadprovider.vod;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.view.WindowManager.LayoutParams;

// compiled from: VodPlayerView.java
final class ay implements OnShowListener {
    final /* synthetic */ VodPlayerView a;

    ay(VodPlayerView vodPlayerView) {
        this.a = vodPlayerView;
    }

    public final void onShow(DialogInterface dialogInterface) {
        Dialog dialog = (Dialog) dialogInterface;
        LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = (int) (((double) ((Activity) this.a.getContext()).getWindowManager().getDefaultDisplay().getWidth()) * 0.5d);
        dialog.getWindow().setAttributes(attributes);
    }
}
