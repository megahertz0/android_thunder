package com.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.umeng.common.UmLog;
import com.umeng.message.proguard.k;
import com.umeng.message.proguard.k$c;
import com.umeng.message.proguard.k$d;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Iterator;
import org.apache.commons.logging.impl.SimpleLog;

public class UmengMessageBootReceiver extends BroadcastReceiver {
    private static final String b;
    private static final String c = "android.intent.action.BOOT_COMPLETED";
    Runnable a;
    private Context d;

    public UmengMessageBootReceiver() {
        this.a = new Runnable() {
            public void run() {
                try {
                    Iterator it = k.a(UmengMessageBootReceiver.this.d).b().iterator();
                    while (it.hasNext()) {
                        k$c com_umeng_message_proguard_k_c = (k$c) it.next();
                        if (k.a(UmengMessageBootReceiver.this.d).a(UmengMessageBootReceiver.this) == null && com_umeng_message_proguard_k_c.b.equals("notification")) {
                            k.a(UmengMessageBootReceiver.this.d).a(UmengMessageBootReceiver.this, SimpleLog.LOG_LEVEL_DEBUG, System.currentTimeMillis());
                        }
                    }
                    Iterator it2 = k.a(UmengMessageBootReceiver.this.d).d().iterator();
                    while (it2.hasNext()) {
                        k$d com_umeng_message_proguard_k_d = (k$d) it2.next();
                        if (k.a(UmengMessageBootReceiver.this.d).c(UmengMessageBootReceiver.this) == null && com_umeng_message_proguard_k_d.c.equals("notification")) {
                            k.a(UmengMessageBootReceiver.this.d).a(UmengMessageBootReceiver.this, com_umeng_message_proguard_k_d.b, "9", System.currentTimeMillis());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    UmLog.d(b, e.toString());
                }
            }
        };
    }

    static {
        b = UmengMessageBootReceiver.class.getName();
    }

    public void onReceive(Context context, Intent intent) {
        try {
            UmLog.d(b, "Boot this system , UmengMessageBootReceiver onReceive()");
            String action = intent.getAction();
            if (action != null && !action.equals(BuildConfig.VERSION_NAME)) {
                UmLog.d(b, new StringBuilder("action=").append(intent.getAction()).toString());
                if (TextUtils.equals(intent.getAction(), c)) {
                    this.d = context;
                    new Thread(this.a).start();
                }
            }
        } catch (Exception e) {
            UmLog.d(b, e.toString());
        }
    }
}
