package com.taobao.accs.ut.statistics;

import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.UT;
import com.taobao.accs.utl.UTMini;
import java.util.HashMap;
import java.util.Map;

// compiled from: Taobao
public class e implements UTInterface {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    private final String g;
    private boolean h;

    public e() {
        this.g = "sendAck";
        this.h = false;
    }

    public void commitUT() {
        Throwable th;
        String str;
        String str2;
        if (!this.h) {
            this.h = true;
            Map hashMap = new HashMap();
            try {
                Object obj = this.a;
                try {
                    Object obj2 = "212";
                    try {
                        hashMap.put("device_id", this.a);
                        hashMap.put("session_id", this.b);
                        hashMap.put("data_id", this.c);
                        hashMap.put("ack_date", this.d);
                        hashMap.put("service_id", this.e);
                        hashMap.put("fail_reasons", this.f);
                        if (ALog.isPrintLog(Level.D)) {
                            ALog.d("accs.SendAckStatistic", UTMini.getCommitInfo((int) UT.EVENT_ID, (String) obj, null, (String) obj2, hashMap), new Object[0]);
                        }
                        UTMini.getInstance().commitEvent((int) UT.EVENT_ID, "sendAck", obj, null, obj2, hashMap);
                    } catch (Throwable th2) {
                        th = th2;
                        ALog.d("accs.SendAckStatistic", UTMini.getCommitInfo((int) UT.EVENT_ID, str, null, str2, hashMap) + " " + th.toString(), new Object[0]);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    str2 = null;
                    ALog.d("accs.SendAckStatistic", UTMini.getCommitInfo((int) UT.EVENT_ID, str, null, str2, hashMap) + " " + th.toString(), new Object[0]);
                }
            } catch (Throwable th4) {
                th = th4;
                str2 = null;
                str = null;
                ALog.d("accs.SendAckStatistic", UTMini.getCommitInfo((int) UT.EVENT_ID, str, null, str2, hashMap) + " " + th.toString(), new Object[0]);
            }
        }
    }
}
