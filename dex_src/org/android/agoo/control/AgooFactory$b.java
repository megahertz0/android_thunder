package org.android.agoo.control;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.taobao.accs.utl.ALog;
import com.xunlei.xllib.R;
import org.android.agoo.common.AgooConstants;

// compiled from: Taobao
class AgooFactory$b implements Runnable {
    final /* synthetic */ AgooFactory a;
    private String b;
    private Intent c;

    public AgooFactory$b(AgooFactory agooFactory, String str, Intent intent) {
        this.a = agooFactory;
        this.b = str;
        this.c = intent;
    }

    public void run() {
        try {
            ALog.d("AgooFactory", new StringBuilder("running tid:").append(Thread.currentThread().getId()).append(",pack=").append(this.b).toString(), new Object[0]);
            AgooFactory.access$000().sendBroadcast(this.c);
            ALog.d("AgooFactory", new StringBuilder("SendMessageRunnable for accs,pack=").append(this.b).toString(), new Object[0]);
            try {
                this.c.setPackage(this.b);
                this.c.setAction(AgooConstants.INTENT_FROM_AGOO_MESSAGE);
                AgooFactory.access$000().startService(this.c);
            } catch (Throwable th) {
            }
            Intent intent = new Intent(AgooConstants.BINDER_MSGRECEIVER_ACTION);
            intent.setPackage(this.b);
            ALog.d("AgooFactory", new StringBuilder("this message pack:").append(this.b).toString(), new Object[0]);
            ALog.d("AgooFactory", "start to service...", new Object[0]);
            ServiceConnection agooFactory$a = new AgooFactory$a(this.a, this.c.getStringExtra(AgooConstants.MESSAGE_ID), this.c);
            Context access$000 = AgooFactory.access$000();
            AgooFactory.access$000();
            AgooFactory.access$000();
            boolean bindService = access$000.bindService(intent, agooFactory$a, R.styleable.Toolbar_maxButtonHeight);
            ALog.d("AgooFactory", new StringBuilder("start service ret:").append(bindService).toString(), new Object[0]);
            if (!bindService) {
                ALog.d("AgooFactory", "SendMessageRunnable is error", new Object[0]);
            }
        } catch (Throwable th2) {
            ALog.e("AgooFactory", new StringBuilder("SendMessageRunnable is error,e=").append(th2.toString()).toString(), new Object[0]);
        }
    }
}
