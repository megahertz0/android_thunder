package com.tencent.stat;

import android.content.Context;
import com.alipay.sdk.util.h;
import com.tencent.bugly.BuglyStrategy.a;
import com.tencent.stat.a.e;
import com.tencent.stat.a.i;
import com.tencent.stat.common.k;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;

class j implements Runnable {
    private Context a;
    private Map<String, Integer> b;

    public j(Context context, Map<String, Integer> map) {
        this.a = null;
        this.b = null;
        this.a = context;
        if (map != null) {
            this.b = map;
        }
    }

    private NetworkMonitor a(String str, int i) {
        NetworkMonitor networkMonitor = new NetworkMonitor();
        Socket socket = new Socket();
        int i2 = 0;
        try {
            networkMonitor.setDomain(str);
            networkMonitor.setPort(i);
            long currentTimeMillis = System.currentTimeMillis();
            SocketAddress inetSocketAddress = new InetSocketAddress(str, i);
            socket.connect(inetSocketAddress, a.MAX_USERDATA_VALUE_LENGTH);
            networkMonitor.setMillisecondsConsume(System.currentTimeMillis() - currentTimeMillis);
            networkMonitor.setRemoteIp(inetSocketAddress.getAddress().getHostAddress());
            socket.close();
            try {
                socket.close();
            } catch (Object th) {
                StatService.b().e(th);
            }
        } catch (Exception e) {
            r1 = e;
            i2 = -1;
            try {
                Exception exception;
                StatService.b().e(exception);
                try {
                    socket.close();
                } catch (Object th2) {
                    StatService.b().e(th2);
                }
            } catch (Throwable th3) {
                try {
                    socket.close();
                } catch (Object th22) {
                    StatService.b().e(th22);
                }
            }
        }
        networkMonitor.setStatusCode(i2);
        return networkMonitor;
    }

    private Map<String, Integer> a() {
        Map<String, Integer> hashMap = new HashMap();
        String a = StatConfig.a("__MTA_TEST_SPEED__", null);
        if (!(a == null || a.trim().length() == 0)) {
            String[] split = a.split(h.b);
            int length = split.length;
            for (int i = 0; i < length; i++) {
                String[] split2 = split[i].split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                if (split2 != null && split2.length == 2) {
                    String str = split2[0];
                    if (str != null && str.trim().length() != 0) {
                        try {
                            hashMap.put(str, Integer.valueOf(Integer.valueOf(split2[1]).intValue()));
                        } catch (Exception e) {
                            StatService.b().e(e);
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    public void run() {
        try {
            if (k.h(this.a)) {
                if (this.b == null) {
                    this.b = a();
                }
                if (this.b == null || this.b.size() == 0) {
                    StatService.b().w("empty domain list.");
                    return;
                }
                JSONArray jSONArray = new JSONArray();
                for (Entry entry : this.b.entrySet()) {
                    String str = (String) entry.getKey();
                    if (str == null || str.length() == 0) {
                        StatService.b().w("empty domain name.");
                    } else if (((Integer) entry.getValue()) == null) {
                        StatService.b().w(new StringBuilder("port is null for ").append(str).toString());
                    } else {
                        jSONArray.put(a((String) entry.getKey(), ((Integer) entry.getValue()).intValue()).toJSONObject());
                    }
                }
                if (jSONArray.length() != 0) {
                    e iVar = new i(this.a, StatService.a(this.a, false));
                    iVar.a(jSONArray.toString());
                    if (StatService.c(this.a) != null) {
                        StatService.c(this.a).post(new k(iVar));
                    }
                }
            }
        } catch (Object th) {
            StatService.b().e(th);
        }
    }
}
