package u.aly;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.b;
import com.umeng.analytics.h;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import u.aly.am.a;

// compiled from: Sender.java
public final class dj {
    c a;
    df b;
    bp c;
    boolean d;
    boolean e;
    private ck f;
    private cm g;
    private final int h;
    private Context i;

    public dj(Context context, c cVar) {
        this.h = 1;
        this.d = false;
        this.f = ck.a(context);
        this.g = cm.a(context);
        this.i = context;
        this.a = cVar;
        this.b = new df(context);
        this.b.a = this.a;
    }

    public final void a(di diVar) {
        this.g.b = diVar;
    }

    public final void a() {
        if (this.c != null) {
            this.f.a();
            bp bpVar = this.c;
            bpVar.i = this.f.a;
            byte[] a = a(bpVar);
            if (a == null) {
                v.b("message is null");
                return;
            }
            z b;
            int i;
            if (this.d) {
                b = z.b(this.i, AnalyticsConfig.getAppkey(this.i), a);
            } else {
                b = z.a(this.i, AnalyticsConfig.getAppkey(this.i), a);
            }
            byte[] a2 = b.a();
            h.a(this.i).f();
            a = this.b.a(a2);
            if (a == null) {
                i = 1;
            } else {
                i = a(a);
            }
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    if (!this.e) {
                        h.a(this.i).b(a2);
                    }
                    v.c("connection error");
                    return;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    if (this.a.d()) {
                        this.a.c();
                    }
                    this.f.b();
                    this.a.b();
                    return;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    this.a.b();
                    return;
                default:
                    return;
            }
        }
        h.a(this.i).h().a(new dk(this));
    }

    final int a(byte[] bArr) {
        bl blVar = new bl();
        try {
            new ab(new a()).a(blVar, bArr);
            if (blVar.a == 1) {
                cm cmVar = this.g;
                bc bcVar = blVar.c;
                if (bcVar != null) {
                    Object obj;
                    if (bcVar.c.equals(cm.a(bcVar))) {
                        loop1:
                        for (bd bdVar : bcVar.a.values()) {
                            int i;
                            byte[] a = b.a(bdVar.c);
                            ByteBuffer allocate = ByteBuffer.allocate(SpdyProtocol.PUBKEY_SEQ_ADASH);
                            allocate.order(null);
                            allocate.putLong(bdVar.b);
                            byte[] array = allocate.array();
                            byte[] bArr2 = cm.a;
                            byte[] bArr3 = new byte[4];
                            for (i = 0; i < 4; i++) {
                                bArr3[i] = (byte) (array[i] ^ bArr2[i]);
                            }
                            for (i = 0; i < 4; i++) {
                                if (a[i] != bArr3[i]) {
                                    obj = null;
                                    break loop1;
                                }
                            }
                        }
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    if (obj != null) {
                        synchronized (cmVar) {
                            String str;
                            bc bcVar2;
                            boolean equals;
                            bc bcVar3 = cmVar.d;
                            if (bcVar3 == null) {
                                str = null;
                            } else {
                                str = bcVar3.c;
                            }
                            if (bcVar3 == null) {
                                Map map = bcVar.a;
                                List<String> arrayList = new ArrayList(map.size() / 2);
                                for (Entry entry : map.entrySet()) {
                                    if (!((bd) entry.getValue()).a()) {
                                        arrayList.add(entry.getKey());
                                    }
                                }
                                for (String str2 : arrayList) {
                                    map.remove(str2);
                                }
                                bcVar2 = bcVar;
                            } else {
                                bcVar2 = cm.a(bcVar3, bcVar);
                            }
                            cmVar.d = bcVar2;
                            if (bcVar2 == null) {
                                obj = null;
                            } else {
                                obj = bcVar2.c;
                            }
                            if (str != null) {
                                equals = str.equals(obj);
                            } else if (obj != null) {
                                equals = false;
                            } else {
                                equals = true;
                            }
                            if (equals) {
                                obj = null;
                            } else {
                                obj = 1;
                            }
                        }
                        if (!(cmVar.d == null || r0 == null)) {
                            cmVar.c.a(cmVar.d);
                            if (cmVar.b != null) {
                                cmVar.b.a(cmVar.c);
                            }
                        }
                    }
                }
                cm cmVar2 = this.g;
                if (cmVar2.d != null) {
                    try {
                        u.a(new File(cmVar2.e.getFilesDir(), ".imprint"), new ae().a(cmVar2.d));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            v.a(new StringBuilder("send log:").append(blVar.b).toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return blVar.a == 1 ? SimpleLog.LOG_LEVEL_DEBUG : MqttConnectOptions.MQTT_VERSION_3_1;
    }

    private static byte[] a(bp bpVar) {
        if (bpVar == null) {
            return null;
        }
        try {
            byte[] a = new ae().a(bpVar);
            if (v.a) {
                bpVar.toString();
            }
            return a;
        } catch (Throwable e) {
            v.b("Fail to serialize log ...", e);
            return null;
        }
    }
}
