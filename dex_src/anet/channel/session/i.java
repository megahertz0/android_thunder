package anet.channel.session;

import android.content.Context;
import anet.channel.Session.Status;
import anet.channel.entity.a;
import org.android.spdy.SpdySession;

// compiled from: Taobao
public class i extends k {
    private static String a;

    static {
        a = "awcn.StandardSpdySession";
    }

    public i(Context context, a aVar) {
        super(context, aVar, aVar.c());
    }

    protected Runnable getRecvTimeOutRunnable() {
        return new j(this);
    }

    public void spdyPingRecvCallback(SpdySession spdySession, long j, Object obj) {
        if (this.mStatus == Status.CONNECTED) {
            notifyStatus(Status.AUTH_SUCC, null);
            this.mHasUnrevPing = false;
            return;
        }
        super.spdyPingRecvCallback(spdySession, j, obj);
    }

    protected void auth() {
        this.mSessionStat.ret = 1;
        notifyStatus(Status.AUTH_SUCC, null);
    }
}
