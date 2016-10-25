package com.xunlei.downloadprovider.i.a;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.model.protocol.f.c;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.xllib.a.b;
import java.io.File;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: Update.java
final class d extends Handler {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 10004:
                if (message.arg1 == 0) {
                    String str;
                    c.a(this.a, (c) ((com.xunlei.downloadprovider.b.c) message.obj).b);
                    if (TextUtils.isEmpty(c.a(this.a).r)) {
                        str = "\u53d6\u6d88";
                        if (c.a(this.a).c == 3) {
                            str = "\u9000\u51fa\u7a0b\u5e8f";
                        }
                        c.a(this.a).r = str;
                    }
                    if (TextUtils.isEmpty(c.a(this.a).s)) {
                        str = "\u7acb\u5373\u4f53\u9a8c";
                        if (c.a(this.a).c == 3) {
                            str = "\u7acb\u523b\u5347\u7ea7";
                        }
                        c.a(this.a).s = str;
                    }
                    new StringBuilder("updata info=>").append(c.a(this.a).toString());
                    c.a(c.b(this.a), c.a(this.a));
                    c.a(this.a, c.a(this.a, c.a(this.a).a));
                    if (c.a(this.a).c == 3) {
                        c.b(this.a, c.a(this.a).a);
                        c.c(this.a, c.a(this.a).a);
                        if (!c.b(this.a)) {
                            c.c(this.a);
                        }
                    }
                    if (c.a(this.a).c <= 0) {
                        c.i(this.a);
                        if (c.b(this.a)) {
                            c.d(this.a);
                        }
                    } else if (c.a(this.a).c == 4) {
                        if (c.b(this.a)) {
                            c.d(this.a);
                        } else if (!aa.b(BrothersApplication.a(), "latest_version_updated").equals(c.a(this.a).a) && b.f(c.e(this.a))) {
                            c.a = true;
                            c.f(this.a);
                            this.a.a(c.a(this.a).f);
                            aa.a(BrothersApplication.a(), "latest_version", c.a(this.a).a);
                            aa.a(BrothersApplication.a(), "gray_update_flag", c.a(this.a).e);
                            aa.a(BrothersApplication.a(), "is_reported", false);
                        }
                    } else if (c.b(this.a) || c.a(this.a).c == 3) {
                        c.a(this.a, c.a(this.a).c);
                    } else if (c.a(this.a).m == 1 && !c.g(this.a) && b.f(c.e(this.a))) {
                        c.f(this.a);
                        this.a.a(c.a(this.a).f);
                    } else {
                        c.h(this.a);
                    }
                } else if (c.b(this.a)) {
                    c.d(this.a);
                }
            case 20008:
                c.a(this.a, false);
                c.a(this.a, c.a(this.a).a, false);
                File file = new File(c.j(this.a), c.k(this.a));
                if (file.exists()) {
                    file.delete();
                }
                switch (message.arg1) {
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        if (!c.l(this.a) || c.b(this.a)) {
                            c.d(this.a, "SD\u5361\u5df2\u6ee1\u6216\u8005\u88ab\u5378\u8f7d\uff0c\u8bf7\u68c0\u67e5SD\u5361\uff01");
                            if (!c.b(this.a) && c.a(this.a).c != 4) {
                                c.m(this.a);
                                return;
                            }
                            return;
                        }
                        c.h(this.a);
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        if (c.n(this.a) && c.o(this.a) != null) {
                            c.b(this.a, 1);
                        } else if (!c.l(this.a) || c.b(this.a)) {
                            XLToast.a(c.e(this.a), XLToastType.XLTOAST_TYPE_ALARM, "\u4e0b\u8f7d\u5b89\u88c5\u5305\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\uff01");
                            if (!c.b(this.a) && c.a(this.a).c != 4) {
                                c.m(this.a);
                            }
                        } else {
                            c.h(this.a);
                        }
                    default:
                        break;
                }
            default:
                break;
        }
    }
}
