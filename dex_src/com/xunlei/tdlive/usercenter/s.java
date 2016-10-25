package com.xunlei.tdlive.usercenter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.xunlei.tdlive.PhotoSelectActivity;

// compiled from: UserCenterFragment.java
final class s implements OnClickListener {
    final /* synthetic */ Fragment a;
    final /* synthetic */ int b;

    s(Fragment fragment, int i) {
        this.a = fragment;
        this.b = i;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (i == 1) {
            this.a.startActivityForResult(new Intent(this.a.getActivity(), PhotoSelectActivity.class).putExtra("start_image_capture", true).putExtra("image_crop", true), this.b);
        } else if (i == 2) {
            this.a.startActivityForResult(new Intent(this.a.getActivity(), PhotoSelectActivity.class).putExtra("capture_image_tip", "\u62cd\u6444\u5934\u50cf").putExtra("image_crop", true), this.b);
        }
    }
}
