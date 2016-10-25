package com.taobao.accs.net;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.AccsFrameCb;
import anet.channel.Session;
import anet.channel.SessionCenter;
import anet.channel.session.AccsSession;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.common.a;
import com.taobao.accs.data.Message;
import com.taobao.accs.ut.monitor.NetPerformanceMonitor;
import com.taobao.accs.ut.statistics.c;
import com.taobao.accs.ut.statistics.d;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UT;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.b;
import com.taobao.accs.utl.h;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// compiled from: Taobao
public class i extends a implements AccsFrameCb {
    private boolean f;
    private ScheduledFuture<?> g;
    private Runnable h;

    protected i(Context context, int i) {
        super(context, i);
        this.f = true;
        this.g = null;
        this.h = new k(this);
        if (!h.a(true)) {
            Object tnetLogFilePath = UtilityImpl.getTnetLogFilePath(this.b, "inapp");
            ALog.d("InAppConnection", new StringBuilder("config tnet log path:").append(tnetLogFilePath).toString(), new Object[0]);
            if (!TextUtils.isEmpty(tnetLogFilePath)) {
                Session.configTnetALog(context, tnetLogFilePath, UtilityImpl.TNET_FILE_SIZE, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            }
        }
        this.g = a.a().schedule(this.h, 120000, TimeUnit.MILLISECONDS);
    }

    public synchronized void a() {
        this.f = true;
        a(this.b);
        ALog.d("InAppConnection", this.a + " start", new Object[0]);
    }

    protected void a(Message message, boolean z) {
        if (!this.f || message == null) {
            ALog.e("InAppConnection", new StringBuilder("not running or msg null! ").append(this.f).toString(), new Object[0]);
            return;
        }
        try {
            if (a.b().getQueue().size() > 1000) {
                throw new RejectedExecutionException(h.NAMESPACE);
            }
            ScheduledFuture schedule = a.b().schedule(new j(this, message), message.delyTime, TimeUnit.MILLISECONDS);
            if (message.getType() == 1 && message.cunstomDataId != null) {
                if (message.isControlFrame() && a(message.cunstomDataId)) {
                    this.c.b(message);
                }
                this.c.a.put(message.cunstomDataId, schedule);
            }
            NetPerformanceMonitor netPermanceMonitor = message.getNetPermanceMonitor();
            if (netPermanceMonitor != null) {
                netPermanceMonitor.setDeviceId(UtilityImpl.getDeviceId(this.b));
                netPermanceMonitor.setConnType(this.a);
                netPermanceMonitor.onEnterQueueData();
            }
        } catch (RejectedExecutionException e) {
            this.c.a(message, (int) ErrorCode.MESSAGE_QUEUE_FULL);
            ALog.e("InAppConnection", new StringBuilder("send queue full count:").append(a.b().getQueue().size()).toString(), new Object[0]);
        } catch (Throwable th) {
            this.c.a(message, (int) com.tencent.connect.dataprovider.ErrorCode.FileNotExist);
            ALog.e("InAppConnection", "send error", th, new Object[0]);
        }
    }

    public void f() {
        ALog.e("InAppConnection", this.a + "shut down", new Object[0]);
        this.f = false;
    }

    public boolean b() {
        return this.f;
    }

    public void c() {
        this.d = 0;
    }

    public void a(boolean z, boolean z2) {
    }

    public c d() {
        return null;
    }

    protected void a(String str, String str2) {
        try {
            Message a = this.c.a(str);
            if (a != null && a.host != null) {
                Session session = SessionCenter.getInstance().get(a.host.toString(), 0);
                if (session != null) {
                    session.checkAvailable();
                }
            }
        } catch (Throwable e) {
            ALog.e("InAppConnection", "onTimeOut", e, new Object[0]);
        }
    }

    public void onDataReceive(AccsSession accsSession, byte[] bArr, int i, int i2) {
        if (ALog.isPrintLog(Level.I)) {
            ALog.i("InAppConnection", new StringBuilder("onDataReceive, type:").append(i2).append(" len:").append(bArr.length).toString(), new Object[0]);
        }
        if (i2 == 200) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                this.c.a(bArr, accsSession.getHost());
                d g = this.c.g();
                if (g != null) {
                    g.c = String.valueOf(currentTimeMillis);
                    g.g = this.a == 0 ? NotificationCompatApi21.CATEGORY_SERVICE : "inapp";
                    g.commitUT();
                }
            } catch (Throwable th) {
                ALog.e("InAppConnection", "onDataReceive ", th, new Object[0]);
                th.printStackTrace();
                UTMini.getInstance().commitEvent(UT.EVENT_ID, "DATA_RECEIVE", UtilityImpl.getStackMsg(th));
            }
            ALog.d("InAppConnection", "try handle msg", new Object[0]);
            return;
        }
        ALog.e("InAppConnection", new StringBuilder("drop frame len:").append(bArr.length).append(" frameType").append(i2).toString(), new Object[0]);
    }

    public void onException(int i, int i2, boolean z, String str) {
        ALog.e("InAppConnection", new StringBuilder("errorId:").append(i2).append("detail:").append(str).append(" dataId:").append(i).append(" needRetry:").append(z).toString(), new Object[0]);
        if (i > 0) {
            Message b = this.c.b(UtilityImpl.int2String(i));
            if (b != null) {
                if (z) {
                    if (!a(b, IHost.CLIENT_NOFITY_INIT)) {
                        this.c.a(b, i2);
                    }
                    if (b.getNetPermanceMonitor() != null) {
                        b.a(h.NAMESPACE, BaseMonitor.COUNT_POINT_RESEND, "total_tnet", 0.0d);
                    }
                } else {
                    this.c.a(b, i2);
                }
            }
        }
        if (i < 0 && z) {
            b(i);
        }
    }

    public boolean a(String str) {
        if (str == null) {
            return false;
        }
        boolean cancel;
        ScheduledFuture scheduledFuture = (ScheduledFuture) this.c.a.get(str);
        if (scheduledFuture != null) {
            cancel = scheduledFuture.cancel(false);
        } else {
            cancel = false;
        }
        if (!cancel) {
            return cancel;
        }
        ALog.e(e(), "cancel", "customDataId", str);
        return cancel;
    }

    protected String e() {
        return "InAppConnection";
    }

    protected void a(Context context) {
        super.a(context);
        SessionCenter.getInstance().setDataReceiveCb(this);
    }
}
