package com.tencent.bugly.crashreport.inner;

import android.content.Context;
import com.tencent.bugly.crashreport.crash.d;
import com.tencent.bugly.proguard.w;
import com.umeng.a;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.tdlive.R;
import java.util.Map;

// compiled from: BUGLY
public class InnerAPI {
    public static Context context;

    public static void postU3dCrashAsync(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            w.e("post u3d fail args null", new Object[0]);
        }
        w.a("post u3d crash %s %s", str, str2);
        d.a(Thread.currentThread(), str, str2, str3);
    }

    public static void postCocos2dxCrashAsync(int i, String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            w.e("post cocos2d-x fail args null", new Object[0]);
        } else if (i == 5 || i == 6) {
            w.a("post cocos2d-x crash %s %s", str, str2);
            d.a(Thread.currentThread(), i, str, str2, str3);
        } else {
            w.e("post cocos2d-x fail category illeagle: %d", Integer.valueOf(i));
        }
    }

    public static void postH5CrashAsync(Thread thread, String str, String str2, String str3, Map<String, String> map) {
        if (str == null || str2 == null || str3 == null) {
            w.e("post h5 fail args null", new Object[0]);
            return;
        }
        w.a("post h5 crash %s %s", str, str2);
        d.a(thread, str, str2, str3, (Map) map);
    }

    public static void setOuterSdkVersion(Context context, String str, String str2) {
        int i;
        if (context == null) {
            w.d("context is null when putsdkdata", new Object[0]);
        }
        if (str == null || str.trim().length() <= 0) {
            i = 1;
        } else {
            i = 0;
        }
        if (i == 0) {
            if (str2 == null || str2.trim().length() <= 0) {
                i = 1;
            } else {
                i = 0;
            }
            if (i == 0) {
                String replace = str.replace("[a-zA-Z[0-9]]+", a.d);
                if (replace.length() > 50) {
                    w.d("putSdkData key length over limit %d , will drop this new key %s", Integer.valueOf(R.styleable.AppCompatTheme_buttonBarStyle), replace);
                    return;
                }
                if (str2.length() > 200) {
                    w.d("putSdkData value length over limit %d , has been cutted!", Integer.valueOf(Impl.STATUS_SUCCESS));
                    str2 = str2.substring(0, Impl.STATUS_SUCCESS);
                }
                com.tencent.bugly.crashreport.common.info.a.a(context).c(new StringBuilder("SDK_").append(replace).toString(), str2);
                w.b("[param] putSdkData data: %s - %s", replace, str2);
            }
        }
    }
}
