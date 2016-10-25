package com.tencent.wxop.stat;

import android.content.Context;
import com.alipay.sdk.util.h;
import com.tencent.bugly.BuglyStrategy.a;
import com.tencent.wxop.stat.a.e;
import com.tencent.wxop.stat.a.j;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;

class ap implements Runnable {
    private Context a;
    private Map<String, Integer> b;
    private StatSpecifyReportedInfo c;

    public ap(Context context, Map<String, Integer> map, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = context;
        this.c = statSpecifyReportedInfo;
        if (map != null) {
            this.b = map;
        }
    }

    private NetworkMonitor a(String str, int i) {
        Throwable th;
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
            } catch (Throwable th2) {
                StatServiceImpl.q.e(th2);
            }
        } catch (Throwable e) {
            th2 = e;
            i2 = -1;
            try {
                StatServiceImpl.q.e(th2);
                try {
                    socket.close();
                } catch (Throwable th22) {
                    StatServiceImpl.q.e(th22);
                }
            } catch (Throwable th3) {
                try {
                    socket.close();
                } catch (Throwable th222) {
                    StatServiceImpl.q.e(th222);
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
                        } catch (Throwable e) {
                            StatServiceImpl.q.e(e);
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    public void run() {
        try {
            if (this.b == null) {
                this.b = a();
            }
            if (this.b == null || this.b.size() == 0) {
                StatServiceImpl.q.i("empty domain list.");
                return;
            }
            JSONArray jSONArray = new JSONArray();
            for (Entry entry : this.b.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null || str.length() == 0) {
                    StatServiceImpl.q.w("empty domain name.");
                } else if (((Integer) entry.getValue()) == null) {
                    StatServiceImpl.q.w(new StringBuilder("port is null for ").append(str).toString());
                } else {
                    jSONArray.put(a((String) entry.getKey(), ((Integer) entry.getValue()).intValue()).toJSONObject());
                }
            }
            if (jSONArray.length() != 0) {
                e jVar = new j(this.a, StatServiceImpl.a(this.a, false, this.c), this.c);
                jVar.a(jSONArray.toString());
                new aq(jVar).a();
            }
        } catch (Throwable th) {
            StatServiceImpl.q.e(th);
        }
    }
}
