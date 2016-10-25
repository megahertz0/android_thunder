package anet.channel.session;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import anet.channel.AccsFrameCb;
import anet.channel.AwcnConfig;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.RequestCb;
import anet.channel.Session.Status;
import anet.channel.SessionCenter;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.entity.ENV;
import anet.channel.heartbeat.HeartbeatManager;
import anet.channel.heartbeat.IHeartbeat;
import anet.channel.heartbeat.IHeartbeatFactory;
import anet.channel.request.Cancelable;
import anet.channel.request.Request;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.SessionStatistic;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import anet.channel.util.Utils;
import anet.channel.util.d;
import com.taobao.accs.utl.BaseMonitor;
import com.tencent.connect.common.Constants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import org.android.agoo.message.MessageService;
import org.android.spdy.RequestPriority;
import org.android.spdy.SpdyDataProvider;
import org.android.spdy.SpdyErrorException;
import org.android.spdy.SpdyRequest;
import org.android.spdy.SpdySession;
import org.android.spdy.SuperviseConnectInfo;
import org.android.spdy.SuperviseData;

// compiled from: Taobao
public class AccsSession extends k {
    public static final int CONN_TYPE_INAPP = 1;
    private static final String TAG = "awcn.AccsSession";
    private IHeartbeat heartbeat;
    private String mAppKey;
    private AccsFrameCb mFrameCb;

    // compiled from: Taobao
    class a extends b {
        a() {
        }

        public void spdyOnStreamResponse(SpdySession spdySession, long j, Map<String, List<String>> map, Object obj) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(d.b(map, HttpConstant.STATUS));
            } catch (NumberFormatException e) {
                parseInt = 0;
            }
            ALog.e(TAG, new StringBuilder("AUTH httpStatusCode: ").append(parseInt).toString(), AccsSession.this.mSeq, new Object[0]);
            if (parseInt == 200) {
                AccsSession.this.notifyStatus(Status.AUTH_SUCC, null);
                if (AccsSession.this.heartbeat != null) {
                    AccsSession.this.mLastPingTime = System.currentTimeMillis();
                    AccsSession.this.heartbeat.start();
                }
                AccsSession.this.mSessionStat.ret = 1;
                ALog.d(TAG, "spdyOnStreamResponse", AccsSession.this.mSeq, "authTime", Long.valueOf(AccsSession.this.mSessionStat.authTime));
            } else {
                AccsSession.this.onAuthFail(parseInt);
            }
            if (AccsSession.this.mConnectedTime > 0) {
                AccsSession.this.mSessionStat.authTime = System.currentTimeMillis() - AccsSession.this.mConnectedTime;
            }
            Object b = d.b(map, "x-at");
            if (!TextUtils.isEmpty(b)) {
                GlobalAppRuntimeInfo.mConnToken = b;
            }
        }

        public void spdyStreamCloseCallback(SpdySession spdySession, long j, int i, Object obj, SuperviseData superviseData) {
            if (i != 0) {
                ALog.e(TAG, new StringBuilder("AUTH spdyStreamCloseCallback: ").append(i).toString(), AccsSession.this.mSeq, new Object[0]);
                AccsSession.this.onAuthFail(i);
            }
        }
    }

    public AccsSession(Context context, anet.channel.entity.a aVar) {
        super(context, aVar, aVar.c());
        this.heartbeat = null;
        this.mAppKey = GlobalAppRuntimeInfo.getAppKey();
        this.mFrameCb = SessionCenter.getInstance().getDataChannelCb();
        this.mSessionStat.isKL = 1;
        this.autoReCreate = AwcnConfig.isAccsSessionAutoRecreate();
        IHeartbeatFactory heartbeatFactory = HeartbeatManager.getHeartbeatFactory();
        if (heartbeatFactory != null) {
            this.heartbeat = heartbeatFactory.createHeartbeat(this);
        }
    }

    public void setFrameCb(AccsFrameCb accsFrameCb) {
        ALog.i(TAG, "setFrameCb", this.mSeq, "AccsFrameCb", accsFrameCb);
        this.mFrameCb = accsFrameCb;
    }

    public void close() {
        if (this.heartbeat != null) {
            this.heartbeat.stop();
            this.heartbeat = null;
        }
        super.close();
    }

    public Cancelable request(Request request, RequestCb requestCb) {
        if (this.heartbeat != null) {
            this.mLastPingTime = System.currentTimeMillis();
            this.heartbeat.setNextHeartbeat(this.mLastPingTime + this.heartbeat.getInterval());
        }
        return super.request(request, requestCb);
    }

    private String buildAuthUrl() {
        String deviceId = Utils.getDeviceId(this.mContext);
        try {
            deviceId = URLEncoder.encode(deviceId);
        } catch (Throwable th) {
        }
        String appSign = Utils.getAppSign(this.mContext, this.mAppKey, Utils.getDeviceId(this.mContext), GlobalAppRuntimeInfo.mConnToken, MessageService.MSG_DB_NOTIFY_REACHED);
        StringBuilder stringBuilder = new StringBuilder(256);
        stringBuilder.append("https://").append(this.mIp).append(":").append(this.mPort).append("/accs/");
        stringBuilder.append("auth?1=").append(deviceId).append("&2=").append(appSign).append("&3=").append(this.mAppKey);
        if (GlobalAppRuntimeInfo.mConnToken != null) {
            stringBuilder.append("&4=").append(GlobalAppRuntimeInfo.mConnToken);
        }
        stringBuilder.append("&5=1&6=").append(NetworkStatusHelper.b()).append("&7=").append(Utils.getOperator(this.mContext)).append("&8=1.1.2&9=").append(System.currentTimeMillis()).append("&10=1&11=").append(VERSION.SDK_INT).append("&12=").append(this.mContext.getPackageName()).append("&13=").append(Utils.getAppVersion(this.mContext)).append("&14=").append(GlobalAppRuntimeInfo.getTtid()).append("&15=").append(Build.MODEL).append("&16=").append(Build.BRAND).append("&17=").append(Utils.getAccsVersion());
        if (this.isHorseRide) {
            stringBuilder.append("&18=ign-loc");
        }
        stringBuilder.append("&19=").append(SessionCenter.SECURITYGUARD_OFF ? 0 : 1);
        ALog.e(TAG, BaseMonitor.ALARM_POINT_AUTH, this.mSeq, "auth url", stringBuilder.toString());
        if (checkParam(deviceId, this.mAppKey, appSign)) {
            return r1;
        }
        ALog.e(TAG, "connect param error!", this.mSeq, new Object[0]);
        onAuthFail(ErrorConstant.ERROR_ACCS_AUTH_PARAM_INVALID);
        return null;
    }

    protected void auth() {
        if (this.mSession == null) {
            notifyStatus(Status.CONNETFAIL, null);
            return;
        }
        String buildAuthUrl = buildAuthUrl();
        if (buildAuthUrl != null) {
            try {
                SpdyRequest spdyRequest;
                URL url = new URL(buildAuthUrl);
                if (TextUtils.isEmpty(this.mProxyIp) || this.mProxyPort <= 0) {
                    spdyRequest = new SpdyRequest(url, Constants.HTTP_GET, RequestPriority.DEFAULT_PRIORITY, this.mReqTimeout, this.mConnTimeout);
                } else {
                    spdyRequest = new SpdyRequest(url, url.getHost(), url.getPort(), this.mProxyIp, this.mProxyPort, Constants.HTTP_GET, RequestPriority.DEFAULT_PRIORITY, this.mReqTimeout, this.mConnTimeout, 0);
                }
                spdyRequest.setDomain(this.mHost);
                this.mSession.submitRequest(spdyRequest, new SpdyDataProvider(null), this.mHost, new a());
            } catch (Throwable th) {
                ALog.e(TAG, "auth exception ", this.mSeq, th, new Object[0]);
                onAuthFail(ErrorConstant.ERROR_AUTH_EXCEPTION);
            }
        }
    }

    private void onAuthFail(int i) {
        notifyStatus(Status.AUTH_FAIL, null);
        if (this.mSessionStat != null) {
            this.mSessionStat.closeReason = "Accs_Auth_Fail";
            this.mSessionStat.errorCode = (long) i;
        }
        close();
    }

    private boolean checkParam(String str, String str2, String str3) {
        int i = CONN_TYPE_INAPP;
        if (GlobalAppRuntimeInfo.getEnv() == ENV.TEST) {
            return true;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            return true;
        }
        if (!TextUtils.isEmpty(str)) {
            if (TextUtils.isEmpty(str2)) {
                i = XZBDevice.DOWNLOAD_LIST_RECYCLE;
            } else if (TextUtils.isEmpty(str3)) {
                i = XZBDevice.DOWNLOAD_LIST_FAILED;
            }
        }
        AppMonitor.getInstance().commitStat(new ExceptionStatistic(-104, ErrorConstant.formatMsg(ErrorConstant.ERROR_ACCS_AUTH_PARAM_INVALID, new StringBuilder("1.1.2 errorCode=").append(i).toString()), "rt"));
        return false;
    }

    public void sendCustomFrame(int i, byte[] bArr, int i2) {
        try {
            ALog.d(TAG, "sendCustomFrame", this.mSeq, com.taobao.accs.common.Constants.KEY_DATA_ID, Integer.valueOf(i), JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(i2));
            if (this.mStatus != Status.AUTH_SUCC || this.mSession == null) {
                ALog.e(TAG, "sendCustomFrame", this.mSeq, new StringBuilder("sendCustomFrame con invalid mStatus:").append(this.mStatus).toString());
                onException(i, ErrorConstant.ERROR_SESSION_INVALID, false, "session invalid");
            } else if (bArr == null || bArr.length <= 16384) {
                this.mSession.sendCustomControlFrame(i, i2, 0, bArr == null ? 0 : bArr.length, bArr);
                SessionStatistic sessionStatistic = this.mSessionStat;
                sessionStatistic.requestCount++;
                sessionStatistic = this.mSessionStat;
                sessionStatistic.cfRCount++;
                this.mLastPingTime = System.currentTimeMillis();
                if (this.heartbeat != null) {
                    this.heartbeat.setNextHeartbeat(this.mLastPingTime + this.heartbeat.getInterval());
                }
            } else {
                onException(i, ErrorConstant.ERROR_DATA_TOO_LARGE, false, null);
            }
        } catch (SpdyErrorException e) {
            ALog.e(TAG, "sendCustomFrame error", this.mSeq, e, new Object[0]);
            onException(i, ErrorConstant.ERROR_TNET_EXCEPTION, false, new StringBuilder("SpdyErrorException: ").append(e.toString()).toString());
        } catch (Throwable e2) {
            ALog.e(TAG, "sendCustomFrame error", this.mSeq, e2, new Object[0]);
            onException(i, ErrorConstant.ERROR_EXCEPTION, false, e2.toString());
        }
    }

    private void onException(int i, int i2, boolean z, String str) {
        if (this.mFrameCb != null) {
            this.mFrameCb.onException(i, i2, z, str);
        }
    }

    protected Runnable getRecvTimeOutRunnable() {
        return new a(this);
    }

    public void spdySessionCloseCallback(SpdySession spdySession, Object obj, SuperviseConnectInfo superviseConnectInfo, int i) {
        if (this.heartbeat != null) {
            this.heartbeat.stop();
        }
        super.spdySessionCloseCallback(spdySession, obj, superviseConnectInfo, i);
    }

    public void spdyCustomControlFrameRecvCallback(SpdySession spdySession, Object obj, int i, int i2, int i3, int i4, byte[] bArr) {
        ALog.i(TAG, "[spdyCustomControlFrameRecvCallback]", this.mSeq, "len", Integer.valueOf(i4), "frameCb", this.mFrameCb);
        if (ALog.isPrintLog(CONN_TYPE_INAPP)) {
            String str = com.umeng.a.d;
            if (i4 < 512) {
                for (byte b : bArr) {
                    str = str + Integer.toHexString(b & 255) + " ";
                }
                ALog.d(TAG, null, this.mSeq, "str", str);
            }
        }
        if (this.mFrameCb != null) {
            this.mFrameCb.onDataReceive(this, bArr, i, i2);
        } else {
            ALog.e(TAG, "AccsFrameCb is null", this.mSeq, new Object[0]);
            AppMonitor.getInstance().commitStat(new ExceptionStatistic(-105, null, "rt"));
        }
        SessionStatistic sessionStatistic = this.mSessionStat;
        sessionStatistic.inceptCount++;
    }

    public void spdyCustomControlFrameFailCallback(SpdySession spdySession, Object obj, int i, int i2) {
        ALog.e(TAG, "spdyCustomControlFrameFailCallback", this.mSeq, com.taobao.accs.common.Constants.KEY_DATA_ID, Integer.valueOf(i));
        onException(i, i2, true, "tnet error");
    }
}
