package com.xunlei.tdlive.usercenter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.xunlei.tdlive.PhotoSelectActivity;

// compiled from: UserCenterFragment.java
final class r implements OnClickListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ int b;

    r(Activity activity, int i) {
        this.a = activity;
        this.b = i;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (i == 1) {
            this.a.startActivityForResult(new Intent(this.a, PhotoSelectActivity.class).putExtra("start_image_capture", true).putExtra("image_crop", true), this.b);
        } else if (i == 2) {
            this.a.startActivityForResult(new Intent(this.a, PhotoSelectActivity.class).putExtra("capture_image_tip", "\u62cd\u6444\u5934\u50cf").putExtra("image_crop", true), this.b);
        }
    }
}
