package anet.channel;

import android.content.Context;
import anet.channel.Session.Status;
import anet.channel.c.c;
import anet.channel.entity.ConnType;
import anet.channel.entity.EventCb;
import anet.channel.entity.EventType;
import anet.channel.entity.a;
import anet.channel.entity.d;
import anet.channel.request.Cancelable;
import anet.channel.request.Request;
import anet.channel.statist.SessionStatistic;
import anet.channel.strategy.IConnStrategy;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;

// compiled from: Taobao
public abstract class Session implements Comparable<Session> {
    private static final String TAG = "awcn.Session";
    public boolean autoReCreate;
    public boolean isHorseRide;
    public IConnStrategy mConnStrategy;
    public int mConnTimeout;
    public ConnType mConnType;
    public Context mContext;
    Map<EventCb, Integer> mEventCallBacks;
    public String mHost;
    public String mIp;
    private boolean mIsConnTimeOut;
    public int mPort;
    public String mProxyIp;
    public int mProxyPort;
    Runnable mRecvTimeOutRunnable;
    public int mReqTimeout;
    public String mSeq;
    public SessionStatistic mSessionStat;
    public Status mStatus;
    private ScheduledFuture<?> timeoutTaskFuture;
    protected boolean tryNextWhenFail;

    // compiled from: Taobao
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Status.values().length];
            try {
                a[Status.CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Status.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Status.DISCONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[Status.CONNETFAIL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[Status.DISCONNECTED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[Status.AUTHING.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[Status.AUTH_SUCC.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[Status.AUTH_FAIL.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    // compiled from: Taobao
    public enum Status {
        CONNECTED,
        CONNECTING,
        CONNETFAIL,
        AUTHING,
        AUTH_SUCC,
        AUTH_FAIL,
        DISCONNECTED,
        DISCONNECTING
    }

    public abstract void close();

    public abstract Runnable getRecvTimeOutRunnable();

    public abstract boolean isAvailable();

    public abstract Cancelable request(Request request, RequestCb requestCb);

    public boolean sameSession(Session session) {
        return session != null && this.mIp != null && this.mPort == session.mPort && this.mConnType == session.mConnType && this.mIp.equals(session.mIp);
    }

    public void setIsHorseRide(boolean z) {
        this.isHorseRide = z;
    }

    public int compareTo(Session session) {
        return ConnType.compare(this.mConnType, session.mConnType);
    }

    public Session(Context context, a aVar, ConnType connType) {
        this.mEventCallBacks = new LinkedHashMap();
        this.mIsConnTimeOut = false;
        this.mStatus = Status.DISCONNECTED;
        this.autoReCreate = false;
        this.tryNextWhenFail = true;
        this.mContext = context.getApplicationContext();
        this.mIp = aVar.a();
        this.mPort = aVar.b();
        this.mConnType = connType;
        this.mHost = aVar.g();
        this.mReqTimeout = aVar.e();
        this.mConnTimeout = aVar.d();
        this.mConnStrategy = aVar.a;
        this.mSessionStat = new SessionStatistic(aVar);
        this.mSessionStat.retryTimes = (long) aVar.b;
        SessionStatistic.maxRetryTime = aVar.c;
        this.mSeq = aVar.i();
    }

    public void sendCustomFrame(int i, byte[] bArr, int i2) {
    }

    public void checkAvailable() {
        ping(true);
    }

    public static void configTnetALog(Context context, String str, int i, int i2) {
        SpdyAgent instance = SpdyAgent.getInstance(context, SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION);
        if (instance == null || !SpdyAgent.checkLoadSucc()) {
            ALog.e("agent null or configTnetALog load so fail!!!", null, "loadso", Boolean.valueOf(SpdyAgent.checkLoadSucc()));
            return;
        }
        instance.configLogFile(str, i, i2);
    }

    public void connect() {
    }

    public void close(boolean z) {
        this.autoReCreate = z;
        close();
    }

    public void ping(boolean z) {
    }

    public void registerEventcb(int i, EventCb eventCb) {
        if (this.mEventCallBacks != null) {
            this.mEventCallBacks.put(eventCb, Integer.valueOf(i));
        }
    }

    protected void unReceiveEventCb(EventCb eventCb) {
        if (this.mEventCallBacks != null) {
            this.mEventCallBacks.remove(eventCb);
        }
    }

    public String getIp() {
        return this.mIp;
    }

    public int getPort() {
        return this.mPort;
    }

    public ConnType getConnType() {
        return this.mConnType;
    }

    public String getHost() {
        return this.mHost;
    }

    public IConnStrategy getConnStrategy() {
        return this.mConnStrategy;
    }

    public void handleCallbacks(EventType eventType, d dVar) {
        c.a(new d(this, eventType, dVar));
    }

    public void onDisconnect() {
    }

    public synchronized void notifyStatus(Status status, d dVar) {
        ALog.e(TAG, "notifyStatus", this.mSeq, Impl.COLUMN_STATUS, status.name());
        if (status.equals(this.mStatus)) {
            ALog.i(TAG, "ignore notifyStatus", this.mSeq, new Object[0]);
        } else {
            this.mStatus = status;
            switch (AnonymousClass_1.a[this.mStatus.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                case R.styleable.Toolbar_contentInsetEnd:
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    handleCallbacks(EventType.CONNECTED, dVar);
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    handleCallbacks(EventType.CONNECT_FAIL, dVar);
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    onDisconnect();
                    if (!this.mIsConnTimeOut) {
                        handleCallbacks(EventType.DISCONNECTED, dVar);
                    }
                    break;
                case R.styleable.Toolbar_contentInsetLeft:
                    handleCallbacks(EventType.AUTH_SUCC, dVar);
                    break;
                case XZBDevice.Wait:
                    handleCallbacks(EventType.AUTH_FAIL, dVar);
                    break;
                default:
                    break;
            }
        }
    }

    public void setPingTimeout() {
        if (this.mRecvTimeOutRunnable == null) {
            this.mRecvTimeOutRunnable = getRecvTimeOutRunnable();
        }
        cancelTimeout();
        if (this.mRecvTimeOutRunnable != null) {
            this.timeoutTaskFuture = c.a(this.mRecvTimeOutRunnable, HttpConstant.RECV_TIMEOUT, TimeUnit.MILLISECONDS);
        }
    }

    protected void cancelTimeout() {
        if (this.mRecvTimeOutRunnable != null && this.timeoutTaskFuture != null) {
            this.timeoutTaskFuture.cancel(true);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Session[");
        stringBuilder.append(this.mSeq).append('|').append(this.mConnType).append(']');
        return stringBuilder.toString();
    }
}
