package com.xunlei.tdlive;

import com.tencent.bugly.crashreport.CrashReport.CrashHandleCallback;
import com.xunlei.tdlive.util.XLog;
import java.util.Map;

// compiled from: XLLiveApplication.java
class gc extends CrashHandleCallback {
    final /* synthetic */ gb a;

    gc(gb gbVar) {
        this.a = gbVar;
    }

    public synchronized Map<String, String> onCrashHandleStart(int i, String str, String str2, String str3) {
        XLog.d("Bugly", new StringBuilder("Exception: category: ").append(i).append(", crashType: ").append(str).append(", errorMessage: ").append(str2).toString());
        XLog.d("Bugly", str3);
        return super.onCrashHandleStart(i, str, str2, str3);
    }
}
