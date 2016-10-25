package com.baidu.mobads.production.f;

import android.os.CountDownTimer;
import com.xunlei.xiazaibao.sdk.XZBDevice;

class e extends CountDownTimer {
    final /* synthetic */ b a;

    e(b bVar, long j, long j2) {
        this.a = bVar;
        super(j, j2);
    }

    public void onTick(long j) {
        int i = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
        int i2 = (int) (j / 1000);
        if (i2 <= 5) {
            i = i2;
        }
        this.a.z.setText(String.valueOf(i));
    }

    public void onFinish() {
        this.a.x.d("CountDownTimer finished");
        this.a.q();
        this.a.h.stop();
    }
}
