package com.umeng.socialize.a;

import android.content.Context;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.base.SocializeClient;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.Log;

// compiled from: SocialAnalytics.java
public class c {
    private static SocializeClient a;

    static {
        a = new SocializeClient();
    }

    public static void a(Context context, String str, String str2, UMediaObject uMediaObject) {
        new Thread(new d(context, str, str2, uMediaObject)).start();
    }

    public static void b(Context context, String str, String str2, UMediaObject uMediaObject) {
        a aVar = new a(context, str, str2);
        aVar.c(SocializeProtocolConstants.PROTOCOL_SHAKE_SHARE);
        aVar.a(uMediaObject);
        b bVar = (b) a.execute(aVar);
        if (bVar == null || !bVar.isOk()) {
            Log.d("fail to send log");
        } else {
            Log.d("send log succeed");
        }
    }
}
