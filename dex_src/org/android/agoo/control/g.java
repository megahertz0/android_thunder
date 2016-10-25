package org.android.agoo.control;

import android.content.Intent;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.a;
import org.android.agoo.common.AgooConstants;
import org.android.agoo.common.d;

// compiled from: Taobao
class g implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ NotifManager e;

    g(NotifManager notifManager, int i, String str, String str2, String str3) {
        this.e = notifManager;
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    public void run() {
        try {
            if ((this.a == 100 || d.a(100, a.b(NotifManager.access$000())) <= this.a) && NotifManager.access$100(this.e, this.b)) {
                Intent intent = new Intent();
                intent.setAction(this.c);
                intent.setClassName(this.b, this.d);
                intent.putExtra(AgooConstants.MESSAGE_FROM_PKG, NotifManager.access$000().getPackageName());
                NotifManager.access$000().startService(intent);
                String access$200 = NotifManager.access$200(this.e, this.b);
                if (ALog.isPrintLog(Level.I)) {
                    ALog.i("NotifManager", new StringBuilder("pingApp [begin],action=").append(this.c).append(",pack=").append(this.b).append(",service=").append(this.d).append(",appVersion=").append(access$200).toString(), new Object[0]);
                }
                UTMini.getInstance().commitEvent(66001, "pingApp", a.b(NotifManager.access$000()), this.b, access$200);
            }
        } catch (Throwable th) {
            UTMini.getInstance().commitEvent(AgooConstants.AGOO_EVENT_ID, "pingApp", a.b(NotifManager.access$000()), th.toString());
            ALog.e("NotifManager", "pingApp", th, new Object[0]);
        }
    }
}
