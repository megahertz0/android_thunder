package com.baidu.mobads;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

class i implements OnTouchListener {
    String a;
    final /* synthetic */ AppActivity b;

    i(AppActivity appActivity) {
        this.b = appActivity;
        this.a = "click";
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.b.y = true;
        try {
            switch (motionEvent.getAction()) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    this.b.z = true;
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.b.A.r.incrementAndGet();
                    view.performClick();
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    break;
                default:
                    this.b.D.e(AppActivity.o, new StringBuilder("unprocessed action=").append(motionEvent.getAction()).toString());
                    break;
            }
        } catch (Exception e) {
            this.b.D.d(AppActivity.o, e.getMessage());
        }
        return false;
    }
}
