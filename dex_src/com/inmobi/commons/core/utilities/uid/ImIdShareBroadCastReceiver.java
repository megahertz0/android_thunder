package com.inmobi.commons.core.utilities.uid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.tencent.open.SocialConstants;
import com.xiaomi.mipush.sdk.MiPushClient;

public class ImIdShareBroadCastReceiver extends BroadcastReceiver {
    private static final String a;

    static {
        a = ImIdShareBroadCastReceiver.class.getSimpleName();
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.inmobi.share.id")) {
            b bVar = new b(context);
            CharSequence string = intent.getExtras().getString(SocialConstants.PARAM_APP_ID);
            String d = bVar.d();
            String string2 = intent.getExtras().getString("imid");
            String f = bVar.f();
            String string3 = intent.getExtras().getString("appendedid");
            long e = bVar.e();
            long j = intent.getExtras().getLong("imidts");
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Received broadcast for IDs. Received ID:").append(string2).append(" AppID:").append(string).append(" AID:").append(string3).toString());
            if (f != null && string != null) {
                if (!f.contains(string)) {
                    if (j < e) {
                        bVar.c(string2);
                    }
                    Intent intent2 = new Intent();
                    intent2.setAction("com.inmobi.share.id");
                    intent2.putExtra("imid", d);
                    intent2.putExtra("appendedid", f);
                    intent2.putExtra("imidts", e);
                    intent2.putExtra(SocialConstants.PARAM_APP_ID, c.a().c());
                    context.sendBroadcast(intent2);
                }
                bVar.d(a(string3, f));
            }
        }
    }

    public static String a(String str, String str2) {
        if (str == null && str2 == null) {
            return null;
        }
        if (str == null || str2 == null) {
            return str != null ? str : str2;
        } else {
            String[] split = str.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
            for (int i = 0; i < split.length; i++) {
                if (!str2.contains(split[i])) {
                    str2 = str2 + MiPushClient.ACCEPT_TIME_SEPARATOR + split[i];
                }
            }
            return str2;
        }
    }
}
