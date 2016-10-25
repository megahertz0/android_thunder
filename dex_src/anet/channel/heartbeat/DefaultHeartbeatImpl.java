package anet.channel.heartbeat;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.Session;
import anet.channel.SessionCenter;
import anet.channel.c.c;
import anet.channel.session.AccsSession;
import anet.channel.util.ALog;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.concurrent.TimeUnit;

// compiled from: Taobao
public class DefaultHeartbeatImpl implements IHeartbeat, Runnable {
    private static final String TAG = "awcn.DefaultHeartbeatImpl";
    private int bgHeartbeatCount;
    private volatile long executeTime;
    protected long interval;
    private volatile boolean isCancelled;
    private final Session session;

    public DefaultHeartbeatImpl(Session session) {
        this.interval = 0;
        this.executeTime = 0;
        this.isCancelled = false;
        this.bgHeartbeatCount = 0;
        this.session = session;
        if (session instanceof AccsSession) {
            ((AccsSession) session).setFrameCb(SessionCenter.getInstance().getDataChannelCb());
        }
        this.interval = (long) session.getConnStrategy().getHeartbeat();
    }

    public void start() {
        ALog.i(TAG, "heartbeat start", null, "session", this.session);
        long interval = getInterval();
        this.executeTime = System.currentTimeMillis() + interval;
        submit(interval);
    }

    public void stop() {
        ALog.i(TAG, "heartbeat stop", null, "session", this.session);
        this.isCancelled = true;
    }

    public void setNextHeartbeat(long j) {
        if (this.executeTime + 1000 < j) {
            if (ALog.isPrintLog(1)) {
                ALog.d(TAG, "setNextHeartbeat", null, "session", this.session, ParamKey.OFFSET, Long.valueOf(j - this.executeTime));
            }
            this.executeTime = j;
        }
    }

    public long getInterval() {
        return this.interval;
    }

    public void run() {
        int i = 0;
        if (!this.isCancelled) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < this.executeTime) {
                submit(this.executeTime - currentTimeMillis);
                return;
            }
            boolean isAppBackground = GlobalAppRuntimeInfo.isAppBackground();
            if (!isAppBackground || this.bgHeartbeatCount <= 0) {
                if (ALog.isPrintLog(1)) {
                    ALog.d(TAG, "heartbeat", null, "session", this.session);
                }
                this.session.ping(true);
                if (isAppBackground) {
                    i = this.bgHeartbeatCount + 1;
                }
                this.bgHeartbeatCount = i;
                this.executeTime = getInterval() + currentTimeMillis;
                submit(this.interval);
                return;
            }
            ALog.e(TAG, "close session in background", null, new Object[0]);
            this.session.close(false);
        }
    }

    private void submit(long j) {
        try {
            c.a(this, 50 + j, TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            ALog.e(TAG, "Submit heartbeat task to thread pool failed.", null, e, new Object[0]);
        }
    }
}
