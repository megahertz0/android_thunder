package com.taobao.accs.ut.statistics;

import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.UT;
import com.taobao.accs.utl.UTMini;
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Map;

// compiled from: Taobao
public class c implements UTInterface {
    public int a;
    public int b;
    public boolean c;
    public int d;
    public int e;
    public String f;
    public String g;
    public long h;
    public boolean i;
    public boolean j;
    private long k;

    public c() {
        this.k = 0;
        this.c = false;
        this.d = 0;
        this.e = 0;
    }

    public void commitUT() {
        Throwable th;
        String str;
        String str2;
        long currentTimeMillis = System.currentTimeMillis();
        if (ALog.isPrintLog(Level.D)) {
            ALog.d("MonitorStatistic", new StringBuilder("commitUT interval:").append(currentTimeMillis - this.k).append(" interval1:").append(currentTimeMillis - this.h).toString(), new Object[0]);
        }
        if (currentTimeMillis - this.k > 1200000 && currentTimeMillis - this.h > 60000) {
            Map hashMap = new HashMap();
            try {
                Object valueOf;
                Object valueOf2 = String.valueOf(this.d);
                try {
                    valueOf = String.valueOf(this.e);
                } catch (Throwable th2) {
                    th = th2;
                    str = null;
                    ALog.d("MonitorStatistic", UTMini.getCommitInfo((int) UT.EVENT_ID, str2, str, null, hashMap) + " " + th.toString(), new Object[0]);
                }
                try {
                    Object obj = "212";
                    hashMap.put("connStatus", String.valueOf(this.a));
                    hashMap.put("connType", String.valueOf(this.b));
                    hashMap.put("tcpConnected", String.valueOf(this.c));
                    hashMap.put("proxy", String.valueOf(this.f));
                    hashMap.put("startServiceTime", String.valueOf(this.h));
                    hashMap.put("commitTime", String.valueOf(currentTimeMillis));
                    hashMap.put("networkAvailable", String.valueOf(this.i));
                    hashMap.put("threadIsalive", String.valueOf(this.j));
                    hashMap.put(SocialConstants.PARAM_URL, this.g);
                    if (ALog.isPrintLog(Level.D)) {
                        ALog.d("MonitorStatistic", UTMini.getCommitInfo((int) UT.EVENT_ID, (String) valueOf2, (String) valueOf, (String) obj, hashMap), new Object[0]);
                    }
                    UTMini.getInstance().commitEvent((int) UT.EVENT_ID, "MONITOR", valueOf2, valueOf, obj, hashMap);
                    this.k = currentTimeMillis;
                } catch (Throwable th3) {
                    th = th3;
                    ALog.d("MonitorStatistic", UTMini.getCommitInfo((int) UT.EVENT_ID, str2, str, null, hashMap) + " " + th.toString(), new Object[0]);
                }
            } catch (Throwable th4) {
                th = th4;
                str = null;
                str2 = null;
                ALog.d("MonitorStatistic", UTMini.getCommitInfo((int) UT.EVENT_ID, str2, str, null, hashMap) + " " + th.toString(), new Object[0]);
            }
        }
    }
}
