package anet.channel.request;

import anet.channel.util.ALog;
import java.util.concurrent.Future;

// compiled from: Taobao
public class FutureCancelable implements Cancelable {
    public static final FutureCancelable NULL;
    private final Future<?> future;
    private final String seq;

    static {
        NULL = new FutureCancelable(null, null);
    }

    public FutureCancelable(Future<?> future, String str) {
        this.future = future;
        this.seq = str;
    }

    public void cancel() {
        if (this.future != null) {
            ALog.i("awcn.FutureCancelable", "cancel request", this.seq, new Object[0]);
            this.future.cancel(true);
        }
    }
}
