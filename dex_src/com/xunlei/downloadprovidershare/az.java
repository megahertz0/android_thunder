package com.xunlei.downloadprovidershare;

import android.content.Context;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.socialize.PlatformConfig;

// compiled from: ShareHelperCommon.java
public class az {
    public static boolean a;
    private static az e;
    public Context b;
    a c;
    public IWXAPI d;

    public static az a() {
        if (e == null) {
            synchronized (az.class) {
                if (e == null) {
                    e = new az();
                }
            }
        }
        return e;
    }

    static {
        a = true;
    }

    private az() {
        this.d = WXAPIFactory.createWXAPI(d.a(), "wx3e6556568beeebdd", false);
        PlatformConfig.setWeixin("wx3e6556568beeebdd", "1d0e9649237be9fc548a641487d255d6");
        PlatformConfig.setQQZone("1101105049", "NbcsjO1otax6pi3L");
        PlatformConfig.setSinaWeibo("4286195229", "93b740fa1feca6c6a3b7487107cd274c");
    }
}
