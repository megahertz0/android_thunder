package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.AccsSessionManager;
import anet.channel.strategy.k.c;
import anet.channel.util.ALog;
import anet.channel.util.LruCache;
import java.io.Serializable;
import java.util.Map;

// compiled from: Taobao
class UnitMap implements Serializable {
    private Map<String, String> a;

    UnitMap() {
        a();
    }

    void a() {
        if (this.a == null) {
            this.a = new LruCache(6);
        }
    }

    void a(c cVar) {
        Object obj = null;
        Object obj2 = cVar.b;
        if (!(TextUtils.isEmpty(obj2) || obj2.equalsIgnoreCase("center"))) {
            int i = 1;
        }
        String b = b(cVar.d, cVar.e);
        if (i == 0) {
            synchronized (this.a) {
                this.a.remove(b);
            }
        } else if (b != null) {
            synchronized (this.a) {
                this.a.put(b, obj2);
            }
        }
        if (ALog.isPrintLog(1)) {
            synchronized (this.a) {
                ALog.d("awcn.UnitMap", new StringBuilder("UnitMap : ").append(toString()).toString(), null, new Object[0]);
            }
        }
        AccsSessionManager.getInstance().checkAndStartAccsSession();
    }

    String a(String str, String str2) {
        String b = b(str, str2);
        if (b == null) {
            return null;
        }
        synchronized (this.a) {
            b = (String) this.a.get(b);
        }
        return b;
    }

    void a(String str, String str2, String str3) {
        String b = b(str, str2);
        if (b != null) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.UnitMap", "set unit prefix", null, "key", b, "unitPrefix", str3);
            }
            if (TextUtils.isEmpty(str3) || str3.equalsIgnoreCase("center")) {
                synchronized (this.a) {
                    this.a.remove(b);
                }
                return;
            }
            synchronized (this.a) {
                this.a.put(b, str3);
            }
            AccsSessionManager.getInstance().checkAndStartAccsSession();
        }
    }

    private String b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return !TextUtils.isEmpty(str2) ? str2 : null;
        } else {
            return str;
        }
    }

    public String toString() {
        String toString;
        synchronized (this.a) {
            toString = new StringBuilder("UnitMap: ").append(this.a.toString()).toString();
        }
        return toString;
    }
}
