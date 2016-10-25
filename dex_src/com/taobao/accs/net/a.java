package com.taobao.accs.net;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.SessionCenter;
import anet.channel.entity.ENV;
import anet.channel.util.HttpConstant;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.client.AccsConfig;
import com.taobao.accs.client.AccsConfig.SECURITY_TYPE;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.data.Message;
import com.taobao.accs.data.b;
import com.taobao.accs.ut.statistics.c;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.h;
import com.tencent.connect.common.Constants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.LinkedHashMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// compiled from: Taobao
public abstract class a {
    public static final int ACCS_RECEIVE_TIMEOUT = 40000;
    public static final int INAPP = 1;
    public static final int SERVICE = 0;
    private static volatile a f;
    private static final byte[] i;
    protected int a;
    protected Context b;
    protected b c;
    protected int d;
    protected LinkedHashMap<Integer, Message> e;
    private long g;
    private volatile boolean h;
    private Runnable j;
    private ScheduledFuture<?> k;

    public abstract void a();

    protected abstract void a(Message message, boolean z);

    protected abstract void a(String str, String str2);

    public abstract void a(boolean z, boolean z2);

    public abstract boolean a(String str);

    public abstract boolean b();

    public abstract void c();

    public abstract c d();

    protected abstract String e();

    static {
        f = null;
        i = new byte[0];
    }

    public static a a(Context context, int i) {
        if (f == null || !f.b()) {
            synchronized (i) {
                if (f == null || !f.b()) {
                    if (i == 0) {
                        f = new l(context, i);
                    } else {
                        f = new i(context, i);
                    }
                }
            }
        }
        return f;
    }

    protected a(Context context, int i) {
        this.d = 0;
        this.g = 0;
        this.h = false;
        this.e = new BaseConnection$1(this);
        this.a = i;
        this.b = context.getApplicationContext();
        this.c = b.a(context);
        this.c.b = this.a;
        com.taobao.accs.common.a.a().schedule(new b(this), 5000, TimeUnit.MILLISECONDS);
        a(this.b);
    }

    protected String a(int i) {
        switch (i) {
            case INAPP:
                return "CONNECTED";
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return "CONNECTING";
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return "DISCONNECTED";
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return "DISCONNECTING";
            default:
                return "DISCONNECTED";
        }
    }

    public void f() {
    }

    public void b(Message message, boolean z) {
        if (message.isAck || UtilityImpl.isNetworkConnected(this.b)) {
            long a;
            if (message.getType() != 2) {
                a = this.c.d.a(message.serviceId, message.bizId);
            } else {
                a = 0;
            }
            if (a == -1) {
                ALog.e(e(), new StringBuilder("servier limit high. dataId:").append(message.dataId).toString(), new Object[0]);
                this.c.a(message, (int) ErrorCode.SERVIER_HIGH_LIMIT);
                return;
            } else if (a == -1000) {
                ALog.e(e(), new StringBuilder("servier limit high for brush. dataId:").append(message.dataId).toString(), new Object[0]);
                this.c.a(message, (int) ErrorCode.SERVIER_HIGH_LIMIT_BRUSH);
                return;
            } else {
                if (a > 0) {
                    if (System.currentTimeMillis() > this.g) {
                        message.delyTime = a;
                    } else {
                        message.delyTime = (a + this.g) - System.currentTimeMillis();
                    }
                    this.g = System.currentTimeMillis() + message.delyTime;
                    ALog.e(e(), new StringBuilder("send message, ").append(Message.b.b(message.getType())).append(" delay:").append(message.delyTime).append(" dataId:").append(message.dataId).toString(), new Object[0]);
                } else if (ALog.isPrintLog(Level.D)) {
                    ALog.d(e(), new StringBuilder("send message, ").append(Message.b.b(message.getType())).append(" delay:").append(message.delyTime).append(" dataId:").append(message.dataId).toString(), new Object[0]);
                }
                try {
                    if (message.isTimeOut()) {
                        this.c.a(message, (int) com.tencent.connect.dataprovider.ErrorCode.FileIsEmpty);
                        return;
                    } else {
                        a(message, z);
                        return;
                    }
                } catch (RejectedExecutionException e) {
                    this.c.a(message, (int) ErrorCode.MESSAGE_QUEUE_FULL);
                    ALog.e(e(), "msg queue full", "size", Integer.valueOf(com.taobao.accs.common.a.b().getQueue().size()));
                }
            }
        }
        ALog.e(e(), new StringBuilder("no network:").append(message.dataId).toString(), new Object[0]);
        this.c.a(message, (int) Constants.ERROR_LOCATION_TIMEOUT);
    }

    protected void a(String str, long j) {
        com.taobao.accs.common.a.a().schedule(new c(this, str), j, TimeUnit.MILLISECONDS);
    }

    protected boolean a(Message message, int i) {
        boolean z = true;
        try {
            if (message.retryTimes > 3) {
                return false;
            }
            message.retryTimes++;
            message.delyTime = (long) i;
            ALog.e(e(), new StringBuilder("reSend dataid:").append(message.dataId).append(" retryTimes:").append(message.retryTimes).toString(), new Object[0]);
            b(message, true);
            try {
                if (message.getNetPermanceMonitor() == null) {
                    return true;
                }
                message.getNetPermanceMonitor().take_date = 0;
                message.getNetPermanceMonitor().to_tnet_date = 0;
                message.getNetPermanceMonitor().retry_times = message.retryTimes;
                if (message.retryTimes != 1) {
                    return true;
                }
                com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_POINT_RESEND, "total", 0.0d);
                return true;
            } catch (Throwable th) {
                Throwable th2 = th;
                this.c.a(message, (int) com.tencent.connect.dataprovider.ErrorCode.FileNotExist);
                ALog.e(e(), "reSend error", th2, new Object[0]);
                return z;
            }
        } catch (Throwable th3) {
            th2 = th3;
            z = false;
            this.c.a(message, (int) com.tencent.connect.dataprovider.ErrorCode.FileNotExist);
            ALog.e(e(), "reSend error", th2, new Object[0]);
            return z;
        }
    }

    protected void b(int i) {
        if (i < 0) {
            ALog.e(e(), "reSendAck", com.taobao.accs.common.Constants.KEY_DATA_ID, Integer.valueOf(i));
            Message message = (Message) this.e.get(Integer.valueOf(i));
            if (message != null) {
                a(message, 5000);
                com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_POINT_RESEND, BaseMonitor.COUNT_ACK, 0.0d);
            }
        }
    }

    protected void g() {
        if (this.j == null) {
            this.j = new d(this);
        }
        h();
        this.k = com.taobao.accs.common.a.a().schedule(this.j, HttpConstant.RECV_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    protected void h() {
        if (this.k != null) {
            this.k.cancel(true);
        }
    }

    public static String a(Context context, String str) {
        String toString = new StringBuilder("https://").append(TextUtils.isEmpty(str) ? com.umeng.a.d : str).append(AccsConfig.ACCS_CENTER_HOSTS[0]).toString();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder append = stringBuilder.append("https://");
            if (TextUtils.isEmpty(str)) {
                str = com.umeng.a.d;
            }
            append.append(str);
            stringBuilder.append(AccsConfig.ACCS_CENTER_HOSTS[UtilityImpl.getMode(context)]);
            return stringBuilder.toString();
        } catch (Throwable th) {
            ALog.e("InAppConnection", "getHost", th, new Object[0]);
            return toString;
        }
    }

    protected void a(Context context) {
        if (!this.h) {
            try {
                ENV env = ENV.ONLINE;
                if (UtilityImpl.isDebugMode(context)) {
                    env = ENV.TEST;
                } else if (UtilityImpl.isPreviewMode(context)) {
                    env = ENV.PREPARE;
                } else {
                    env = ENV.ONLINE;
                }
                if (AccsConfig.mSecurityType == SECURITY_TYPE.SECURITY_OFF) {
                    SessionCenter.init(context, UtilityImpl.getAppkey(context), UtilityImpl.getTtId(context), GlobalClientInfo.getInstance(this.b).getAppSecret());
                } else {
                    SessionCenter.init(context, UtilityImpl.getAppkey(context));
                }
                SessionCenter.getInstance().switchEnv(env);
                ALog.i(e(), "init awcn success", new Object[0]);
                this.h = true;
            } catch (Throwable th) {
                ALog.e(e(), "initAwcn", th, new Object[0]);
            }
        }
    }

    public static String b(Context context) {
        String str = AccsConfig.ACCS_CHANNEL_HOSTS[UtilityImpl.getMode(context)];
        ALog.i("SpdyConnection", new StringBuilder("getChannelHost:").append(str).toString(), new Object[0]);
        return str == null ? com.umeng.a.d : str;
    }
}
