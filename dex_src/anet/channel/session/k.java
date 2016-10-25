package anet.channel.session;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.RequestCb;
import anet.channel.Session;
import anet.channel.Session.Status;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.b.b;
import anet.channel.entity.ConnType;
import anet.channel.entity.EventType;
import anet.channel.entity.c;
import anet.channel.request.Cancelable;
import anet.channel.request.Request;
import anet.channel.request.TnetCancelable;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.RequestStatistic;
import anet.channel.statist.SessionStatistic;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.n;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import anet.channel.util.Utils;
import anet.channel.util.d;
import com.tencent.bugly.Bugly;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.android.spdy.RequestPriority;
import org.android.spdy.SessionCb;
import org.android.spdy.SessionInfo;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdyByteArray;
import org.android.spdy.SpdyDataProvider;
import org.android.spdy.SpdyErrorException;
import org.android.spdy.SpdyRequest;
import org.android.spdy.SpdySession;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;
import org.android.spdy.SuperviseConnectInfo;
import org.android.spdy.SuperviseData;

// compiled from: Taobao
public abstract class k extends Session implements SessionCb {
    private static final String TAG = "awcn.TnetSpdySession";
    protected SpdyAgent mAgent;
    protected long mConnectedTime;
    protected volatile boolean mHasUnrevPing;
    protected long mLastPingTime;
    protected SpdySession mSession;
    private int requestTimeoutCount;

    // compiled from: Taobao
    private class a extends b {
        private Request b;
        private RequestCb c;
        private long d;
        private long e;
        private long f;

        public a(Request request, RequestCb requestCb) {
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.b = request;
            this.c = requestCb;
            this.d = System.currentTimeMillis();
        }

        public void spdyDataChunkRecvCB(SpdySession spdySession, boolean z, long j, SpdyByteArray spdyByteArray, Object obj) {
            ALog.d(TAG, "spdyDataChunkRecvCB", k.this.mSeq, "len", Integer.valueOf(spdyByteArray.getDataLength()), "fin", Boolean.valueOf(z));
            if (this.c != null) {
                anet.channel.a.a a = a.a.a(spdyByteArray.getByteArray(), spdyByteArray.getDataLength());
                spdyByteArray.recycle();
                this.c.onDataReceive(a, z);
            }
            k.this.handleCallbacks(EventType.DATA_RECEIVE, null);
        }

        public void spdyStreamCloseCallback(SpdySession spdySession, long j, int i, Object obj, SuperviseData superviseData) {
            ALog.d(TAG, "spdyStreamCloseCallback", k.this.mSeq, "streamId", Long.valueOf(j));
            this.e = System.currentTimeMillis();
            a(superviseData);
            String str = HttpConstant.SUCCESS;
            if (i != 0) {
                if (i != -2005) {
                    str = ErrorConstant.formatMsg(ErrorConstant.ERROR_TNET_EXCEPTION, new StringBuilder("statusCode=").append(i).toString());
                    AppMonitor.getInstance().commitStat(new ExceptionStatistic(-300, str, this.b.rs, null));
                }
                ALog.e(TAG, "spdyStreamCloseCallback error", k.this.mSeq, "status code", Integer.valueOf(i));
            }
            if (this.c != null) {
                this.c.onFinish(i, str, this.b.rs);
            }
            if (i == -2004) {
                if (k.access$504(k.this) >= (anet.channel.monitor.a.a().b() == 1 ? XZBDevice.DOWNLOAD_LIST_FAILED : XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                    k.this.close(true);
                }
            }
            if (i <= -3000 && i > -4000) {
                b.a().a(XZBDevice.DOWNLOAD_LIST_FAILED, this.b.getHost());
            }
        }

        private void a(SuperviseData superviseData) {
            long j = 0;
            try {
                if (this.e > 0 && this.d > 0) {
                    this.b.rs.serverRT = this.f;
                    RequestStatistic requestStatistic = this.b.rs;
                    if (this.b.rs.firstDataTime != 0) {
                        j = this.e - this.b.rs.firstDataTime;
                    }
                    requestStatistic.recDataTime = j;
                    this.b.rs.waitingTime = this.e - this.d;
                    if (superviseData != null) {
                        this.b.rs.firstDataTime = superviseData.responseStart - superviseData.sendStart;
                        this.b.rs.oneWayTime = superviseData.responseEnd - superviseData.sendStart;
                        this.b.rs.recDataTime = superviseData.responseEnd - superviseData.responseStart;
                        this.b.rs.sendBeforeTime = superviseData.sendStart - this.d;
                        this.b.rs.sendDataTime = superviseData.sendEnd - superviseData.sendStart;
                        this.b.rs.sendDataSize = (long) (superviseData.bodySize + superviseData.compressSize);
                        this.b.rs.recDataSize = (long) (superviseData.recvBodySize + superviseData.recvCompressSize);
                        SessionStatistic sessionStatistic = k.this.mSessionStat;
                        sessionStatistic.recvSizeCount += (long) (superviseData.recvBodySize + superviseData.recvCompressSize);
                        sessionStatistic = k.this.mSessionStat;
                        sessionStatistic.sendSizeCount += (long) (superviseData.bodySize + superviseData.compressSize);
                    }
                }
            } catch (Exception e) {
            }
        }

        public void spdyOnStreamResponse(SpdySession spdySession, long j, Map<String, List<String>> map, Object obj) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(d.b(map, HttpConstant.STATUS));
            } catch (NumberFormatException e) {
                parseInt = 0;
            }
            if (parseInt > 0) {
                this.b.rs.ret = true;
                k.this.requestTimeoutCount = 0;
            }
            ALog.d(TAG, "spdyOnStreamResponse", k.this.mSeq, "httpStatusCode:", Integer.valueOf(parseInt));
            if (this.c != null) {
                this.c.onResponseCode(parseInt, d.a((Map) map));
            }
            k.this.handleCallbacks(EventType.HEADER_RECEIVE, null);
            try {
                this.f = Long.parseLong(d.b(map, "s-rt"));
            } catch (NumberFormatException e2) {
            }
            if (n.c(n.e(k.this.mHost))) {
                b.a().a(0, Integer.valueOf(parseInt));
            }
        }
    }

    static /* synthetic */ int access$504(k kVar) {
        int i = kVar.requestTimeoutCount + 1;
        kVar.requestTimeoutCount = i;
        return i;
    }

    public k(Context context, anet.channel.entity.a aVar, ConnType connType) {
        super(context, aVar, connType);
        this.mHasUnrevPing = false;
        this.mConnectedTime = 0;
        this.requestTimeoutCount = 0;
        init();
    }

    public Cancelable request(Request request, RequestCb requestCb) {
        RequestStatistic requestStatistic;
        SpdyErrorException e;
        TnetCancelable tnetCancelable = TnetCancelable.NULL;
        if (request != null) {
            requestStatistic = request.rs;
        } else {
            requestStatistic = new RequestStatistic(n.e(this.mHost), null);
        }
        requestStatistic.setConnType(this.mConnType);
        requestStatistic.setIPAndPort(this.mIp, this.mPort);
        if (request == null || requestCb == null) {
            if (requestCb != null) {
                requestCb.onFinish(ErrorConstant.ERROR_PARAM_ILLEGAL, ErrorConstant.getErrMsg(ErrorConstant.ERROR_PARAM_ILLEGAL), requestStatistic);
            }
            return tnetCancelable;
        }
        try {
            if (this.mSession == null || !isAvailable()) {
                requestCb.onFinish(ErrorConstant.ERROR_SESSION_INVALID, "Session\u4e0d\u53ef\u7528", request.rs);
                return tnetCancelable;
            }
            SpdyRequest spdyRequest;
            requestStatistic.start = System.currentTimeMillis();
            request.addHeader(":host", request.getHost());
            if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                ALog.i(TAG, com.umeng.a.d, request.getSeq(), "request URL", request.getUrlString());
                ALog.i(TAG, com.umeng.a.d, request.getSeq(), "request headers", request.getHeaders());
            }
            URL url = request.getUrl();
            if (TextUtils.isEmpty(this.mProxyIp) || this.mProxyPort <= 0) {
                spdyRequest = new SpdyRequest(url, request.getMethod().toString(), RequestPriority.DEFAULT_PRIORITY, request.getReadTimeout(), request.getConnectTimeout());
            } else {
                spdyRequest = new SpdyRequest(url, url.getHost(), url.getPort(), this.mProxyIp, this.mProxyPort, request.getMethod().toString(), RequestPriority.DEFAULT_PRIORITY, request.getReadTimeout(), request.getConnectTimeout(), 0);
            }
            spdyRequest.addHeaders(request.getHeaders());
            int submitRequest = this.mSession.submitRequest(spdyRequest, new SpdyDataProvider(request.getBody()), this, new a(request, requestCb));
            if (ALog.isPrintLog(1)) {
                ALog.d(TAG, com.umeng.a.d, request.getSeq(), "streamId", Integer.valueOf(submitRequest));
            }
            Cancelable tnetCancelable2 = new TnetCancelable(this.mSession, submitRequest, request.getSeq());
            try {
                SessionStatistic sessionStatistic = this.mSessionStat;
                sessionStatistic.requestCount++;
                sessionStatistic = this.mSessionStat;
                sessionStatistic.stdRCount++;
                this.mLastPingTime = System.currentTimeMillis();
                return tnetCancelable2;
            } catch (SpdyErrorException e2) {
                e = e2;
            } catch (Exception e3) {
                Exception e4 = e3;
                requestCb.onFinish(ErrorConstant.ERROR_EXCEPTION, ErrorConstant.formatMsg(ErrorConstant.ERROR_EXCEPTION, e4.toString()), requestStatistic);
                return tnetCancelable2;
            }
        } catch (SpdyErrorException e5) {
            e = e5;
            TnetCancelable tnetCancelable3 = tnetCancelable;
            if (e.SpdyErrorGetCode() == -1104 || e.SpdyErrorGetCode() == -1103) {
                ALog.e(TAG, "Send request on closed session!!!", this.mSeq, new Object[0]);
                notifyStatus(Status.DISCONNECTED, new c(EventType.DISCONNECTED, false, -1104, "Session is closed!"));
            }
            requestCb.onFinish(ErrorConstant.ERROR_TNET_EXCEPTION, ErrorConstant.formatMsg(ErrorConstant.ERROR_TNET_EXCEPTION, e.toString()), requestStatistic);
            return tnetCancelable2;
        } catch (Exception e6) {
            e4 = e6;
            tnetCancelable3 = tnetCancelable;
            requestCb.onFinish(ErrorConstant.ERROR_EXCEPTION, ErrorConstant.formatMsg(ErrorConstant.ERROR_EXCEPTION, e4.toString()), requestStatistic);
            return tnetCancelable2;
        }
    }

    public void sendCustomFrame(int i, byte[] bArr, int i2) {
    }

    protected void connect() {
        if (this.mStatus != Status.CONNECTING && this.mStatus != Status.CONNECTED && this.mStatus != Status.AUTH_SUCC) {
            try {
                if (this.mAgent != null) {
                    String valueOf = String.valueOf(System.currentTimeMillis());
                    ALog.e(TAG, "[connect]", this.mSeq, com.taobao.accs.internal.b.ELECTION_KEY_HOST, this.mHost, "connect ", this.mIp + ":" + this.mPort, "sessionId", valueOf, "SpdyProtocol,", this.mConnType.toProtocol(), "proxyIp,", this.mProxyIp, "proxyPort,", Integer.valueOf(this.mProxyPort));
                    SessionInfo sessionInfo = new SessionInfo(this.mIp, this.mPort, this.mHost, this.mProxyIp, this.mProxyPort, valueOf, this, this.mConnType.getTnetConType());
                    sessionInfo.setConnectionTimeoutMs(this.mConnTimeout);
                    sessionInfo.setPubKeySeqNum(this.mConnType.getTnetPublicKey());
                    this.mSession = this.mAgent.createSession(sessionInfo);
                    if (this.mSession.getRefCount() > 1) {
                        ALog.e(TAG, "get session ref count > 1!!!", this.mSeq, new Object[0]);
                        notifyStatus(Status.CONNECTED, new anet.channel.entity.b(EventType.CONNECTED));
                        auth();
                        return;
                    }
                    boolean z;
                    notifyStatus(Status.CONNECTING, null);
                    this.mLastPingTime = System.currentTimeMillis();
                    SessionStatistic sessionStatistic = this.mSessionStat;
                    StringBuilder stringBuilder = new StringBuilder();
                    if (TextUtils.isEmpty(this.mProxyIp)) {
                        z = false;
                    } else {
                        z = true;
                    }
                    sessionStatistic.isProxy = stringBuilder.append(z).toString();
                    this.mSessionStat.isTunnel = Bugly.SDK_IS_DEV;
                    this.mSessionStat.isBackground = GlobalAppRuntimeInfo.isAppBackground();
                    this.mConnectedTime = 0;
                }
            } catch (Throwable th) {
                notifyStatus(Status.CONNETFAIL, null);
                ALog.e(TAG, "connect exception ", this.mSeq, th, new Object[0]);
            }
        }
    }

    public void close() {
        ALog.e(TAG, "force close!", this.mSeq, "session", this);
        notifyStatus(Status.DISCONNECTING, null);
        try {
            if (this.mSession != null) {
                this.mSession.closeSession();
            }
        } catch (Exception e) {
        }
    }

    protected void onDisconnect() {
        this.mHasUnrevPing = false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void ping(boolean r8) {
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.session.k.ping(boolean):void");
        /*
        this = this;
        r5 = 1;
        r6 = 0;
        r0 = anet.channel.util.ALog.isPrintLog(r5);
        if (r0 == 0) goto L_0x0030;
    L_0x0008:
        r0 = "awcn.TnetSpdySession";
        r1 = "ping";
        r2 = r7.mSeq;
        r3 = 4;
        r3 = new java.lang.Object[r3];
        r4 = "host";
        r3[r6] = r4;
        r4 = r7.mHost;
        r3[r5] = r4;
        r4 = 2;
        r5 = "thread";
        r3[r4] = r5;
        r4 = 3;
        r5 = java.lang.Thread.currentThread();
        r5 = r5.getName();
        r3[r4] = r5;
        anet.channel.util.ALog.d(r0, r1, r2, r3);
    L_0x0030:
        if (r8 == 0) goto L_0x00a0;
    L_0x0032:
        r0 = r7.mSession;	 Catch:{ Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x00a1;
    L_0x0036:
        r0 = r7.mStatus;	 Catch:{ Exception -> 0x00d1 }
        r1 = anet.channel.Session.Status.CONNECTED;	 Catch:{ Exception -> 0x00d1 }
        if (r0 == r1) goto L_0x0042;
    L_0x003c:
        r0 = r7.mStatus;	 Catch:{ Exception -> 0x00d1 }
        r1 = anet.channel.Session.Status.AUTH_SUCC;	 Catch:{ Exception -> 0x00d1 }
        if (r0 != r1) goto L_0x00a0;
    L_0x0042:
        r0 = anet.channel.entity.EventType.PING_SEND;	 Catch:{ Exception -> 0x00d1 }
        r1 = 0;
        r7.handleCallbacks(r0, r1);	 Catch:{ Exception -> 0x00d1 }
        r0 = 1;
        r7.mHasUnrevPing = r0;	 Catch:{ Exception -> 0x00d1 }
        r0 = r7.mSessionStat;	 Catch:{ Exception -> 0x00d1 }
        r2 = r0.ppkgCount;	 Catch:{ Exception -> 0x00d1 }
        r4 = 1;
        r2 = r2 + r4;
        r0.ppkgCount = r2;	 Catch:{ Exception -> 0x00d1 }
        r0 = r7.mSession;	 Catch:{ Exception -> 0x00d1 }
        r0.submitPing();	 Catch:{ Exception -> 0x00d1 }
        r0 = 1;
        r0 = anet.channel.util.ALog.isPrintLog(r0);	 Catch:{ Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x0097;
    L_0x0060:
        r0 = "awcn.TnetSpdySession";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d1 }
        r1.<init>();	 Catch:{ Exception -> 0x00d1 }
        r2 = r7.mHost;	 Catch:{ Exception -> 0x00d1 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x00d1 }
        r2 = " submit ping ms:";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x00d1 }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00d1 }
        r4 = r7.mLastPingTime;	 Catch:{ Exception -> 0x00d1 }
        r2 = r2 - r4;
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x00d1 }
        r2 = " force:";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x00d1 }
        r1 = r1.append(r8);	 Catch:{ Exception -> 0x00d1 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x00d1 }
        r2 = r7.mSeq;	 Catch:{ Exception -> 0x00d1 }
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x00d1 }
        anet.channel.util.ALog.d(r0, r1, r2, r3);	 Catch:{ Exception -> 0x00d1 }
    L_0x0097:
        r7.setPingTimeout();	 Catch:{ Exception -> 0x00d1 }
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00d1 }
        r7.mLastPingTime = r0;	 Catch:{ Exception -> 0x00d1 }
    L_0x00a0:
        return;
    L_0x00a1:
        r0 = r7.mSessionStat;	 Catch:{ Exception -> 0x00d1 }
        if (r0 == 0) goto L_0x00ac;
    L_0x00a5:
        r0 = r7.mSessionStat;	 Catch:{ Exception -> 0x00d1 }
        r1 = "session null";
        r0.closeReason = r1;	 Catch:{ Exception -> 0x00d1 }
    L_0x00ac:
        r0 = "awcn.TnetSpdySession";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d1 }
        r1.<init>();	 Catch:{ Exception -> 0x00d1 }
        r2 = r7.mHost;	 Catch:{ Exception -> 0x00d1 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x00d1 }
        r2 = " session null";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x00d1 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x00d1 }
        r2 = r7.mSeq;	 Catch:{ Exception -> 0x00d1 }
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x00d1 }
        anet.channel.util.ALog.e(r0, r1, r2, r3);	 Catch:{ Exception -> 0x00d1 }
        r7.close();	 Catch:{ Exception -> 0x00d1 }
        goto L_0x00a0;
    L_0x00d1:
        r0 = move-exception;
        r1 = "awcn.TnetSpdySession";
        r2 = "ping";
        r3 = r7.mSeq;
        r4 = new java.lang.Object[r6];
        anet.channel.util.ALog.e(r1, r2, r3, r0, r4);
        goto L_0x00a0;
        */
    }

    protected void auth() {
    }

    public boolean isAvailable() {
        return this.mStatus == Status.AUTH_SUCC;
    }

    private void init() {
        try {
            SpdyAgent.enableDebug = false;
            this.mAgent = SpdyAgent.getInstance(this.mContext, SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION);
            this.mAgent.setAccsSslCallback(new l(this));
        } catch (Throwable e) {
            ALog.e(TAG, "Init failed.", null, e, new Object[0]);
        }
    }

    public void spdySessionConnectCB(SpdySession spdySession, SuperviseConnectInfo superviseConnectInfo) {
        anet.channel.entity.d bVar = new anet.channel.entity.b(EventType.CONNECTED);
        bVar.a = (long) superviseConnectInfo.connectTime;
        bVar.b = (long) superviseConnectInfo.handshakeTime;
        this.mSessionStat.connectionTime = (long) superviseConnectInfo.connectTime;
        this.mSessionStat.sslTime = (long) superviseConnectInfo.handshakeTime;
        this.mSessionStat.sslCalTime = (long) superviseConnectInfo.doHandshakeTime;
        this.mSessionStat.netType = NetworkStatusHelper.b();
        this.mConnectedTime = System.currentTimeMillis();
        notifyStatus(Status.CONNECTED, bVar);
        auth();
        ALog.e(TAG, "spdySessionConnectCB connect", this.mSeq, "connectTime", Integer.valueOf(superviseConnectInfo.connectTime), "sslTime:", Integer.valueOf(superviseConnectInfo.handshakeTime));
    }

    public void spdyPingRecvCallback(SpdySession spdySession, long j, Object obj) {
        if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            ALog.i(TAG, "ping receive", this.mSeq, HttpConstant.HOST, this.mHost, SocializeConstants.WEIBO_ID, Long.valueOf(j));
        }
        if (j >= 0) {
            this.mHasUnrevPing = false;
            handleCallbacks(EventType.PIND_RECEIVE, null);
        }
    }

    public void bioPingRecvCallback(SpdySession spdySession, int i) {
        if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            ALog.i(TAG, this.mHost + " ping receive " + i, this.mSeq, new Object[0]);
        }
    }

    public void spdyCustomControlFrameRecvCallback(SpdySession spdySession, Object obj, int i, int i2, int i3, int i4, byte[] bArr) {
    }

    public void spdySessionFailedError(SpdySession spdySession, int i, Object obj) {
        if (spdySession != null) {
            try {
                spdySession.cleanUp();
            } catch (Throwable e) {
                ALog.e(TAG, "[spdySessionFailedError]session clean up failed!", null, e, new Object[0]);
            }
        }
        notifyStatus(Status.CONNETFAIL, new anet.channel.entity.d(EventType.CONNECT_FAIL, i, "tnet connect fail"));
        ALog.e(TAG, null, this.mSeq, " errorId:", Integer.valueOf(i));
        this.mSessionStat.errorCode = (long) i;
        this.mSessionStat.ret = 0;
        this.mSessionStat.netType = NetworkStatusHelper.b();
        if (!this.isHorseRide) {
            AppMonitor.getInstance().commitStat(this.mSessionStat);
            AppMonitor.getInstance().commitAlarm(this.mSessionStat.getAlarmObject());
        }
    }

    public void spdySessionCloseCallback(SpdySession spdySession, Object obj, SuperviseConnectInfo superviseConnectInfo, int i) {
        ALog.e(TAG, "spdySessionCloseCallback", this.mSeq, " errorCode:", Integer.valueOf(i));
        if (spdySession != null) {
            try {
                spdySession.cleanUp();
            } catch (Throwable e) {
                ALog.e(TAG, "[spdySessionCloseCallback]session clean up failed!", null, e, new Object[0]);
            }
        }
        notifyStatus(Status.DISCONNECTED, new c(EventType.DISCONNECTED, false, i, TextUtils.isEmpty(this.mSessionStat.closeReason) ? new StringBuilder("tnet close error:").append(i).toString() : this.mSessionStat.closeReason + ":" + this.mSessionStat.errorCode));
        if (superviseConnectInfo != null) {
            this.mSessionStat.requestCount = (long) superviseConnectInfo.reused_counter;
            this.mSessionStat.liveTime = (long) superviseConnectInfo.keepalive_period_second;
        }
        if (this.mSessionStat.errorCode == 0) {
            this.mSessionStat.errorCode = (long) i;
        }
        this.mSessionStat.lastPingInterval = (int) (System.currentTimeMillis() - this.mLastPingTime);
        if (!this.isHorseRide) {
            AppMonitor.getInstance().commitStat(this.mSessionStat);
            AppMonitor.getInstance().commitAlarm(this.mSessionStat.getAlarmObject());
        }
    }

    public void spdyCustomControlFrameFailCallback(SpdySession spdySession, Object obj, int i, int i2) {
    }

    public byte[] getSSLMeta(SpdySession spdySession) {
        return Utils.SecurityGuardGetSslTicket2(this.mContext, spdySession.getDomain());
    }

    public int putSSLMeta(SpdySession spdySession, byte[] bArr) {
        return Utils.SecurityGuardPutSslTicket2(this.mContext, spdySession.getDomain(), bArr);
    }
}
