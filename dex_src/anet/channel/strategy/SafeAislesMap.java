package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.AccsSessionManager;
import anet.channel.strategy.k.c;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.LruCache;
import com.alipay.sdk.cons.b;
import java.io.Serializable;

// compiled from: Taobao
class SafeAislesMap implements Serializable {
    public static final String NO_RESULT = "No_Result";
    private LruCache<String, String> a;

    SafeAislesMap() {
        this.a = null;
        a();
    }

    void a() {
        if (this.a == null) {
            this.a = new LruCache(128);
        }
        this.a.put(n.a(), b.a);
    }

    void a(c cVar) {
        if (cVar.c != null) {
            int i;
            synchronized (this.a) {
                i = 0;
                for (int i2 = 0; i2 < cVar.c.length; i2++) {
                    k.b bVar = cVar.c[i2];
                    if (bVar.m) {
                        this.a.remove(bVar.a);
                    } else if (!bVar.o) {
                        if (HttpConstant.HTTP.equalsIgnoreCase(bVar.c) || b.a.equalsIgnoreCase(bVar.c)) {
                            this.a.put(bVar.a, bVar.c);
                        } else {
                            this.a.put(bVar.a, NO_RESULT);
                        }
                        if (i == 0 && n.c(bVar.a)) {
                            i = 1;
                        }
                    }
                }
            }
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.SafeAislesMap", toString(), null, new Object[0]);
            }
            if (i != 0) {
                AccsSessionManager.getInstance().checkAndStartAccsSession();
            }
        }
    }

    String a(String str) {
        if (TextUtils.isEmpty(str) || !n.f(str)) {
            return NO_RESULT;
        }
        synchronized (this.a) {
            String str2 = (String) this.a.get(str);
            if (str2 == null) {
                this.a.put(str, NO_RESULT);
            }
        }
        if (n.c(str)) {
            return (TextUtils.isEmpty(str2) || NO_RESULT.equals(str2)) ? b.a : str2;
        } else {
            return str2;
        }
    }

    void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && n.f(str)) {
            if (HttpConstant.HTTP.equals(str2) || b.a.equals(str2)) {
                synchronized (this.a) {
                    this.a.put(str, str2);
                }
            }
        }
    }

    public String toString() {
        String toString;
        synchronized (this.a) {
            toString = new StringBuilder("SafeAislesMap: ").append(this.a.toString()).toString();
        }
        return toString;
    }
}
