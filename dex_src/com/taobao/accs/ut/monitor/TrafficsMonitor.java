package com.taobao.accs.ut.monitor;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.statist.Dimension;
import anet.channel.statist.Measure;
import anet.channel.statist.Monitor;
import anet.channel.statist.StatObject;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// compiled from: Taobao
public class TrafficsMonitor {
    private Map<String, List<a>> a;
    private Map<String, String> b;
    private int c;
    private Context d;
    private String e;

    @Monitor(module = "NetworkSDK", monitorPoint = "TrafficStats")
    // compiled from: Taobao
    public static class StatTrafficMonitor extends BaseMonitor {
        @Dimension
        public String bizId;
        @Dimension
        public String date;
        @Dimension
        public String host;
        @Dimension
        public boolean isBackground;
        @Dimension
        public String serviceId;
        @Measure
        public long size;
    }

    // compiled from: Taobao
    public static class a {
        String a;
        String b;
        String c;
        boolean d;
        String e;
        long f;

        public a(String str, boolean z, String str2, long j) {
            this.c = str;
            this.d = z;
            this.e = str2;
            this.f = j;
        }

        public a(String str, String str2, String str3, boolean z, String str4, long j) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = z;
            this.e = str4;
            this.f = j;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(new StringBuilder("date:").append(this.a).toString());
            stringBuilder.append(" ");
            stringBuilder.append(new StringBuilder("bizId:").append(this.b).toString());
            stringBuilder.append(" ");
            stringBuilder.append(new StringBuilder("serviceId:").append(this.c).toString());
            stringBuilder.append(" ");
            stringBuilder.append(new StringBuilder("host:").append(this.e).toString());
            stringBuilder.append(" ");
            stringBuilder.append(new StringBuilder("isBackground:").append(this.d).toString());
            stringBuilder.append(" ");
            stringBuilder.append(new StringBuilder("size:").append(this.f).toString());
            return stringBuilder.toString();
        }
    }

    public TrafficsMonitor(Context context) {
        this.a = new HashMap();
        this.b = new HashMap<String, String>() {
            {
                put(IXAdRequestInfo.IMSI, "512");
                put("motu", "513");
                put("acds", "514");
                put(GlobalClientInfo.AGOO_SERVICE_ID, "515");
                put("agooAck", "515");
                put("agooTokenReport", "515");
                put("accsSelf", Constants.DEFAULT_UIN);
            }
        };
        this.c = 0;
        this.e = com.umeng.a.d;
        this.d = context;
    }

    public void a(a aVar) {
        if (aVar != null && aVar.e != null && aVar.f > 0) {
            aVar.c = TextUtils.isEmpty(aVar.c) ? "accsSelf" : aVar.c;
            synchronized (this.a) {
                String str = (String) this.b.get(aVar.c);
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                Object arrayList;
                aVar.b = str;
                if (ALog.isPrintLog(Level.D)) {
                    ALog.d("TrafficsMonitor", new StringBuilder("addTrafficInfo count:").append(this.c).append(" ").append(aVar.toString()).toString(), new Object[0]);
                }
                List<a> list = (List) this.a.get(str);
                if (list != null) {
                    Object obj;
                    for (a aVar2 : list) {
                        if (aVar2.d == aVar.d && aVar2.e != null && aVar2.e.equals(aVar.e)) {
                            aVar2.f += aVar.f;
                            obj = null;
                            break;
                        }
                    }
                    int i = 1;
                    if (obj != null) {
                        list.add(aVar);
                    }
                } else {
                    arrayList = new ArrayList();
                    arrayList.add(aVar);
                }
                this.a.put(str, arrayList);
                this.c++;
                if (this.c >= 10) {
                    b();
                }
            }
        }
    }

    private void b() {
        synchronized (this.a) {
            String str;
            String formatDay = UtilityImpl.formatDay(System.currentTimeMillis());
            if (TextUtils.isEmpty(this.e) || this.e.equals(formatDay)) {
                Object obj = null;
                str = formatDay;
            } else {
                str = this.e;
                int i = 1;
            }
            for (String str2 : this.a.keySet()) {
                for (a aVar : (List) this.a.get(str2)) {
                    if (aVar != null) {
                        com.taobao.accs.c.a.a(this.d).a(aVar.e, aVar.c, (String) this.b.get(aVar.c), aVar.d, aVar.f, str);
                    }
                }
            }
            if (ALog.isPrintLog(Level.D)) {
                ALog.d("TrafficsMonitor", new StringBuilder("savetoDay:").append(str).append(" saveTraffics").append(this.a.toString()).toString(), new Object[0]);
            }
            if (obj != null) {
                this.a.clear();
                c();
            } else if (ALog.isPrintLog(Level.D)) {
                ALog.d("TrafficsMonitor", new StringBuilder("no need commit lastsaveDay:").append(this.e).append(" currday:").append(formatDay).toString(), new Object[0]);
            }
            this.e = formatDay;
            this.c = 0;
        }
    }

    public void a() {
        try {
            synchronized (this.a) {
                this.a.clear();
            }
            List<a> a = com.taobao.accs.c.a.a(this.d).a(true);
            if (a != null) {
                for (a aVar : a) {
                    a(aVar);
                }
            }
        } catch (Exception e) {
            ALog.w("TrafficsMonitor", e.toString(), new Object[0]);
        }
    }

    private void c() {
        List<a> a = com.taobao.accs.c.a.a(this.d).a(false);
        if (a != null) {
            try {
                for (a aVar : a) {
                    if (aVar != null) {
                        StatObject statTrafficMonitor = new StatTrafficMonitor();
                        statTrafficMonitor.bizId = aVar.b;
                        statTrafficMonitor.date = aVar.a;
                        statTrafficMonitor.host = aVar.e;
                        statTrafficMonitor.isBackground = aVar.d;
                        statTrafficMonitor.size = aVar.f;
                        AppMonitor.getInstance().commitStat(statTrafficMonitor);
                    }
                }
                com.taobao.accs.c.a.a(this.d).a();
            } catch (Throwable th) {
                ALog.e(com.umeng.a.d, th.toString(), new Object[0]);
                th.printStackTrace();
            }
        }
    }
}
