package com.taobao.accs.ut.statistics;

import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.UT;
import com.taobao.accs.utl.UTMini;
import com.umeng.socialize.common.SocializeConstants;
import java.util.HashMap;
import java.util.Map;

// compiled from: Taobao
public class d implements UTInterface {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public boolean h;
    public String i;
    private final String j;
    private boolean k;

    public d() {
        this.j = "receiveMessage";
        this.h = false;
        this.k = false;
    }

    public void commitUT() {
        Throwable th;
        String str;
        String str2;
        if (!this.k) {
            this.k = true;
            Map hashMap = new HashMap();
            try {
                Object obj = this.a;
                try {
                    Object obj2 = "212";
                    try {
                        hashMap.put("device_id", this.a);
                        hashMap.put("data_id", this.b);
                        hashMap.put("receive_date", this.c);
                        hashMap.put("to_bz_date", this.d);
                        hashMap.put("service_id", this.e);
                        hashMap.put("data_length", this.f);
                        hashMap.put("msg_type", this.g);
                        hashMap.put("repeat", this.h ? "y" : IXAdRequestInfo.AD_COUNT);
                        hashMap.put(SocializeConstants.TENCENT_UID, this.i);
                        if (ALog.isPrintLog(Level.D)) {
                            ALog.d("accs.ReceiveMessage", UTMini.getCommitInfo((int) UT.EVENT_ID, (String) obj, null, (String) obj2, hashMap), new Object[0]);
                        }
                        UTMini.getInstance().commitEvent((int) UT.EVENT_ID, "receiveMessage", obj, null, obj2, hashMap);
                    } catch (Throwable th2) {
                        th = th2;
                        ALog.d("accs.ReceiveMessage", UTMini.getCommitInfo((int) UT.EVENT_ID, str, null, str2, hashMap) + " " + th.toString(), new Object[0]);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    str2 = null;
                    ALog.d("accs.ReceiveMessage", UTMini.getCommitInfo((int) UT.EVENT_ID, str, null, str2, hashMap) + " " + th.toString(), new Object[0]);
                }
            } catch (Throwable th4) {
                th = th4;
                str2 = null;
                str = null;
                ALog.d("accs.ReceiveMessage", UTMini.getCommitInfo((int) UT.EVENT_ID, str, null, str2, hashMap) + " " + th.toString(), new Object[0]);
            }
        }
    }
}
