package org.android.agoo.control;

import android.text.TextUtils;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.b;
import com.xunlei.analytics.b.c;
import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;

// compiled from: Taobao
class d implements Runnable {
    final /* synthetic */ byte[] a;
    final /* synthetic */ boolean b;
    final /* synthetic */ AgooFactory c;

    d(AgooFactory agooFactory, byte[] bArr, boolean z) {
        this.c = agooFactory;
        this.a = bArr;
        this.b = z;
    }

    public void run() {
        try {
            String str = new String(this.a, "utf-8");
            if (TextUtils.isEmpty(str)) {
                b.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_fail_ack", "msg==null", 0.0d);
                return;
            }
            ALog.i("AgooFactory", new StringBuilder("message = ").append(str).toString(), new Object[0]);
            JSONObject jSONObject = new JSONObject(str);
            CharSequence charSequence = null;
            CharSequence string = jSONObject.getString("api");
            String string2 = jSONObject.getString(AgooConstants.MESSAGE_ID);
            if (TextUtils.equals(string, "agooReport")) {
                charSequence = jSONObject.getString("status");
            }
            if (TextUtils.equals(string, AgooConstants.AGOO_SERVICE_AGOOACK)) {
                b.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_success_ack", "handlerACKMessage", 0.0d);
            }
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(charSequence)) {
                b.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_fail_ack", "json key null", 0.0d);
                return;
            }
            if (ALog.isPrintLog(Level.I)) {
                ALog.i("AgooFactory", new StringBuilder("updateMsg data begin,api=").append(string).append(",id=").append(string2).append(",status=").append(charSequence).append(",reportTimes=").append(org.android.agoo.common.b.c(AgooFactory.access$000())).toString(), new Object[0]);
            }
            if (TextUtils.equals(string, "agooReport")) {
                if (TextUtils.equals(charSequence, c.d) && this.b) {
                    AgooFactory.access$100(this.c).a(string2, c.f);
                } else if ((TextUtils.equals(charSequence, "8") || TextUtils.equals(charSequence, "9")) && this.b) {
                    AgooFactory.access$100(this.c).a(string2, "100");
                }
                b.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_success_ack", charSequence, 0.0d);
            }
        } catch (Throwable th) {
            ALog.e("AgooFactory", new StringBuilder("updateMsg get data error,e=").append(th).toString(), new Object[0]);
            b.a(AgooConstants.MESSAGE_SOURCE_ACCS, "agoo_fail_ack", "json exception", 0.0d);
        }
    }
}
