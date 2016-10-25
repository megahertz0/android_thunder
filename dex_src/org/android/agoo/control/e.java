package org.android.agoo.control;

import android.text.TextUtils;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.xunlei.analytics.b.c;
import org.android.agoo.common.b;

// compiled from: Taobao
class e implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ AgooFactory c;

    e(AgooFactory agooFactory, String str, String str2) {
        this.c = agooFactory;
        this.a = str;
        this.b = str2;
    }

    public void run() {
        try {
            if (!TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.b)) {
                if (ALog.isPrintLog(Level.I)) {
                    ALog.i("AgooFactory", new StringBuilder("updateNotifyMsg begin,messageId=").append(this.a).append(",status=").append(this.b).append(",reportTimes=").append(b.c(AgooFactory.access$000())).toString(), new Object[0]);
                }
                if (TextUtils.equals(this.b, "8")) {
                    AgooFactory.access$100(this.c).a(this.a, c.e);
                } else if (TextUtils.equals(this.b, "9")) {
                    AgooFactory.access$100(this.c).a(this.a, c.c);
                }
            }
        } catch (Throwable th) {
            ALog.e("AgooFactory", new StringBuilder("updateNotifyMsg e=").append(th.toString()).toString(), new Object[0]);
        }
    }
}
