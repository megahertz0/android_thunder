package anetwork.channel.g;

import anet.channel.appmonitor.AppMonitor;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// compiled from: Taobao
public final class a implements Future<Object> {
    private c a;
    private boolean b;

    public a(c cVar) {
        this.a = cVar;
    }

    public final boolean cancel(boolean z) {
        if (!this.b) {
            c cVar = this.a;
            if (cVar.e != null && cVar.e.d.compareAndSet(false, true)) {
                if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                    ALog.i("ANet.UnifiedNetworkTask", "task cancelled", cVar.c, new Object[0]);
                }
                if (cVar.e.e != null) {
                    cVar.e.e.cancel();
                }
                cVar.a(ErrorConstant.ERROR_REQUEST_CANCEL, null, cVar.e.f);
                AppMonitor.getInstance().commitStat(new ExceptionStatistic(-204, null, cVar.a.k, null));
            }
            this.b = true;
        }
        return true;
    }

    public final boolean isCancelled() {
        return this.b;
    }

    public final boolean isDone() {
        throw new RuntimeException("NOT SUPPORT!");
    }

    public final /* synthetic */ Object get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        throw new RuntimeException("NOT SUPPORT!");
    }

    public final /* synthetic */ Object get() throws InterruptedException, ExecutionException {
        throw new RuntimeException("NOT SUPPORT!");
    }
}
