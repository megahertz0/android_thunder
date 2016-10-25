package com.xunlei.downloadprovider.app.a;

import com.tencent.bugly.crashreport.CrashReport.CrashHandleCallback;
import java.util.Map;

// compiled from: BuglyAgent.java
final class b extends CrashHandleCallback {
    b() {
    }

    public final synchronized Map<String, String> onCrashHandleStart(int i, String str, String str2, String str3) {
        return super.onCrashHandleStart(i, str, str2, str3);
    }

    public final synchronized byte[] onCrashHandleStart2GetExtraDatas(int i, String str, String str2, String str3) {
        return super.onCrashHandleStart2GetExtraDatas(i, str, str2, str3);
    }
}
