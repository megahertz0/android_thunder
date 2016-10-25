package com.xunlei.analytics.b;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import com.xunlei.common.yunbo.XLYunboMassage;
import java.util.HashMap;

class b implements Callback {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case XLYunboMassage.MSG_TASKCREATED:
                Bundle data = message.getData();
                if (data != null) {
                    String str = (String) data.get("MSG_EVENT_INTERID");
                    if (str == c.f) {
                        this.a.c();
                        return true;
                    }
                    String str2 = (String) data.get("MSG_EVENT_EVENTID");
                    HashMap hashMap = null;
                    if (message.obj != null) {
                        hashMap = (HashMap) message.obj;
                    }
                    this.a.b(str, str2, hashMap);
                }
                break;
        }
        return false;
    }
}
