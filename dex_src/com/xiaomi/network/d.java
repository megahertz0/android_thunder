package com.xiaomi.network;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.network.UploadHostStatHelper.HttpRecordCallback;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import org.apache.thrift.f;

class d extends TimerTask {
    final /* synthetic */ UploadHostStatHelper a;

    d(UploadHostStatHelper uploadHostStatHelper) {
        this.a = uploadHostStatHelper;
    }

    public void run() {
        List<HttpRecordCallback> arrayList = new ArrayList();
        synchronized (this.a) {
            arrayList.addAll(UploadHostStatHelper.a(this.a));
        }
        for (HttpRecordCallback httpRecordCallback : arrayList) {
            List a = httpRecordCallback.a();
            double b = httpRecordCallback.b();
            if (a != null) {
                try {
                    if (a.size() > 0) {
                        UploadHostStatHelper.a(this.a, a, b);
                    }
                } catch (f e) {
                    b.a(new StringBuilder("uploadHostStat exception").append(e.toString()).toString());
                }
            }
        }
        UploadHostStatHelper.a(this.a, false);
    }
}
