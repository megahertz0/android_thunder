package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.xmpush.thrift.a;
import com.xiaomi.xmpush.thrift.aa;
import com.xiaomi.xmpush.thrift.ac;
import com.xiaomi.xmpush.thrift.ad;
import com.xiaomi.xmpush.thrift.j;
import com.xiaomi.xmpush.thrift.k;
import com.xiaomi.xmpush.thrift.n;
import com.xiaomi.xmpush.thrift.o;
import com.xiaomi.xmpush.thrift.r;
import com.xiaomi.xmpush.thrift.t;
import com.xiaomi.xmpush.thrift.v;
import com.xiaomi.xmpush.thrift.w;
import com.xiaomi.xmpush.thrift.y;
import java.nio.ByteBuffer;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.thrift.b;
import org.apache.thrift.f;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class h {
    private static final byte[] a;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[a.values().length];
            try {
                a[a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.b.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.c.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[a.d.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[a.e.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[a.f.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[a.g.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[a.h.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[a.i.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[a.j.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    static {
        a = new byte[]{(byte) 100, (byte) 23, (byte) 84, (byte) 114, (byte) 72, (byte) 0, (byte) 4, (byte) 97, (byte) 73, (byte) 97, (byte) 2, (byte) 52, (byte) 84, (byte) 102, (byte) 18, (byte) 32};
    }

    protected static <T extends b<T, ?>> o a(Context context, T t, a aVar) {
        return a(context, t, aVar, !aVar.equals(a.a), context.getPackageName(), a.a(context).c());
    }

    protected static <T extends b<T, ?>> o a(Context context, T t, a aVar, boolean z, String str, String str2) {
        return a(context, t, aVar, !aVar.equals(a.a), str, str2, false);
    }

    protected static <T extends b<T, ?>> o a(Context context, T t, a aVar, boolean z, String str, String str2, boolean z2) {
        byte[] a = ad.a(t);
        if (a == null) {
            com.xiaomi.channel.commonutils.logger.b.a("invoke convertThriftObjectToBytes method, return null.");
            return null;
        }
        o oVar = new o();
        if (z2) {
            a = com.xiaomi.channel.commonutils.file.a.a(a);
        }
        if (z) {
            Object f = a.a(context).f();
            if (TextUtils.isEmpty(f)) {
                com.xiaomi.channel.commonutils.logger.b.a("regSecret is empty, return null");
                return null;
            }
            try {
                a = b(com.xiaomi.channel.commonutils.string.a.a(f), a);
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.d("encryption error. ");
            }
        }
        j jVar = new j();
        jVar.a = 5;
        jVar.b = "fakeid";
        oVar.a(jVar);
        oVar.a(ByteBuffer.wrap(a));
        oVar.a(aVar);
        oVar.c(true);
        oVar.b(str);
        oVar.a(z);
        oVar.a(str2);
        return oVar;
    }

    private static Cipher a(byte[] bArr, int i) {
        Key secretKeySpec = new SecretKeySpec(bArr, "AES");
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(a);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(i, secretKeySpec, ivParameterSpec);
        return instance;
    }

    public static b a(Context context, o oVar) {
        if (oVar.c()) {
            try {
                byte[] a = a(com.xiaomi.channel.commonutils.string.a.a(a.a(context).f()), oVar.f());
            } catch (Throwable e) {
                throw new f("the aes decrypt failed.", e);
            }
        }
        a = oVar.f();
        b a2 = a(oVar.a());
        if (a2 != null) {
            ad.a(a2, a);
        }
        return a2;
    }

    private static b a(a aVar) {
        switch (AnonymousClass_1.a[aVar.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return new t();
            case SimpleLog.LOG_LEVEL_DEBUG:
                return new aa();
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return new y();
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                return new ac();
            case SimpleLog.LOG_LEVEL_ERROR:
                return new w();
            case SimpleLog.LOG_LEVEL_FATAL:
                return new k();
            case SimpleLog.LOG_LEVEL_OFF:
                return new n();
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
                return new v();
            case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                return new r();
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
                return new n();
            default:
                return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        return a(bArr, (int) SimpleLog.LOG_LEVEL_DEBUG).doFinal(bArr2);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        return a(bArr, 1).doFinal(bArr2);
    }
}
