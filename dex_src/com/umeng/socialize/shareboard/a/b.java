package com.umeng.socialize.shareboard.a;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.DeviceConfig;

// compiled from: SNSPlatformAdapter.java
class b implements OnClickListener {
    final /* synthetic */ SnsPlatform a;
    final /* synthetic */ a b;

    b(a aVar, SnsPlatform snsPlatform) {
        this.b = aVar;
        this.a = snsPlatform;
    }

    public void onClick(View view) {
        this.b.c.dismiss();
        SHARE_MEDIA share_media = this.a.mPlatform;
        if (DeviceConfig.isNetworkAvailable(this.b.b) || share_media == SHARE_MEDIA.SMS) {
            this.b.a(this.a, share_media);
        } else {
            Toast.makeText(this.b.b, "\u60a8\u7684\u7f51\u7edc\u4e0d\u53ef\u7528,\u8bf7\u68c0\u67e5\u7f51\u7edc\u8fde\u63a5...", 0).show();
        }
    }
}
