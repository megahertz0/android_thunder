package anetwork.channel.g;

import anet.channel.appmonitor.AppMonitor;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.StatObject;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;

// compiled from: Taobao
final class d implements Runnable {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final void run() {
        if (this.a.e != null && this.a.e.d.compareAndSet(false, true)) {
            ALog.e("ANet.UnifiedNetworkTask", "task time out", this.a.c, new Object[0]);
            this.a.a(ErrorConstant.ERROR_REQUEST_TIME_OUT, null, this.a.e.f);
            if (this.a.e.e != null) {
                this.a.e.e.cancel();
            }
            StatObject statObject = this.a.a.k;
            statObject.statusCode = -202;
            statObject.msg = ErrorConstant.getErrMsg(ErrorConstant.ERROR_REQUEST_TIME_OUT);
            statObject.protocolType = this.a.e.f.a;
            AppMonitor.getInstance().commitStat(statObject);
            AppMonitor.getInstance().commitStat(new ExceptionStatistic(-202, null, statObject, null));
            this.a.e = null;
            this.a.f = null;
        }
    }
}
