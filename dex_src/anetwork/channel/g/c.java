package anetwork.channel.g;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.Session;
import anet.channel.SessionCenter;
import anet.channel.entity.ConnType.TypeLevel;
import anet.channel.request.Cancelable;
import anet.channel.request.Request;
import anet.channel.request.Request.Builder;
import anet.channel.request.Request.Method;
import anet.channel.session.e;
import anet.channel.statist.FlowStatistic;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import anetwork.channel.a.b;
import anetwork.channel.a.d;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.aidl.k;
import anetwork.channel.entity.f;
import anetwork.channel.entity.j;
import com.tencent.connect.common.Constants;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

// compiled from: Taobao
public final class c {
    j a;
    f b;
    String c;
    int d;
    volatile a e;
    volatile Future f;
    volatile anetwork.channel.a.c g;

    // compiled from: Taobao
    private class a implements Runnable {
        int a;
        int b;
        int c;
        AtomicBoolean d;
        volatile Cancelable e;
        anetwork.channel.f.a f;
        b g;
        String h;

        a() {
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = new AtomicBoolean(false);
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = "other";
            this.f = new anetwork.channel.f.a();
            this.f.d = c.this.a.c;
            if (c.this.a.d().containsKey(FlowStatistic.F_REFER_PARAM)) {
                this.h = (String) c.this.a.d().remove(FlowStatistic.F_REFER_PARAM);
            }
        }

        public final void run() {
            Map d;
            Session session;
            Session eVar;
            Method method;
            if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                ALog.i("ANet.UnifiedNetworkTask", "exec request", c.this.c, "retryTimes", Integer.valueOf(c.this.a.f));
            }
            if (c.this.g != null) {
                byte[] a;
                long currentTimeMillis = System.currentTimeMillis();
                anetwork.channel.a.c cVar = c.this.g;
                if (cVar.a != null) {
                    a = cVar.a.a();
                } else {
                    a = null;
                }
                if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                    String str = "ANet.UnifiedNetworkTask";
                    String str2 = "handle image cache";
                    String str3 = c.this.c;
                    Object[] objArr = new Object[4];
                    objArr[0] = "Found cache";
                    objArr[1] = Boolean.valueOf(a != null);
                    objArr[2] = "cost";
                    objArr[3] = Long.valueOf(System.currentTimeMillis() - currentTimeMillis);
                    ALog.i(str, str2, str3, objArr);
                }
                if (a != null) {
                    if (this.d.compareAndSet(false, true)) {
                        f fVar = c.this.b;
                        anetwork.channel.a.c cVar2 = c.this.g;
                        fVar.a(Impl.STATUS_SUCCESS, anetwork.channel.a.c.a());
                        c.this.b.a(0, a.length, anet.channel.a.a.a(a));
                        c.this.a(Impl.STATUS_SUCCESS, null, this.f);
                        return;
                    }
                    return;
                }
            }
            if (c.this.d == 1 && anetwork.channel.b.b.c() && c.this.a.b()) {
                String str4;
                SessionCenter instance = SessionCenter.getInstance();
                String str5 = c.this.a.b;
                d = c.this.a.d();
                if (d != null) {
                    str4 = (String) d.get(HttpConstant.X_HOST_CNAME);
                    if (!TextUtils.isEmpty(str4)) {
                        str4 = str5.replace(c.this.a.c, str4);
                        session = instance.get(str4, TypeLevel.SPDY, (long) c.this.a.h);
                    }
                }
                str4 = str5;
                session = instance.get(str4, TypeLevel.SPDY, (long) c.this.a.h);
            } else {
                session = null;
            }
            if (session == null && anetwork.channel.b.b.d() && !NetworkStatusHelper.f()) {
                session = SessionCenter.getInstance().get(c.this.a.b, TypeLevel.HTTP, 0);
            }
            if (session == null) {
                ALog.i("ANet.UnifiedNetworkTask", "create HttpSession with local DNS", c.this.c, new Object[0]);
                eVar = new e(GlobalAppRuntimeInfo.getContext(), new anet.channel.entity.a(StringUtils.buildString(c.this.a.d, HttpConstant.SCHEME_SPLIT, c.this.a.c), c.this.c, null));
            } else {
                eVar = session;
            }
            this.f.a = eVar.getConnType().toProtocol();
            this.f.h = eVar.getConnType().isSSL();
            ALog.i("ANet.UnifiedNetworkTask", "tryGetSession", c.this.c, "Session", eVar);
            j jVar = c.this.a;
            Builder url = new Builder().setUrl(jVar.b);
            if (Constants.HTTP_GET.equalsIgnoreCase(jVar.a.h)) {
                method = Method.GET;
            } else {
                method = Method.POST;
            }
            url = url.setMethod(method).setBody(jVar.a.b).setRedirectEnable(jVar.a.f).setBizId(String.valueOf(jVar.a.l)).setSeq(jVar.a()).setReadTimeout(c.this).setConnectTimeout(jVar.h).setRequestStatistic(jVar.k);
            d = jVar.d();
            if (d != null) {
                url.setHeaders(new HashMap(d));
            }
            List<anetwork.channel.f> list = c.this;
            if (list != null) {
                for (anetwork.channel.f fVar2 : list) {
                    url.addParam(fVar2.a(), fVar2.b());
                }
            }
            if (jVar.a.e != null) {
                url.setCharset(jVar.a.e);
            }
            Request build = url.build();
            if (eVar != null) {
                anetwork.channel.f.b a2 = anetwork.channel.f.b.a();
                Object obj = c.this.a.b;
                if (a2.a && !TextUtils.isEmpty(obj)) {
                    String a3 = anetwork.channel.f.b.a(obj);
                    if (a2.d.contains(a3)) {
                        if (a2.c.isEmpty()) {
                            a2.b = System.currentTimeMillis();
                        }
                        a2.c.add(a3);
                    }
                }
                this.e = eVar.request(build, new e(this, build));
            }
        }
    }

    public c(j jVar, k kVar, int i) {
        this.d = 1;
        this.g = null;
        this.a = jVar;
        this.c = anetwork.channel.h.c.a(jVar.a(), i == 0 ? "HTTP" : "DGRD");
        jVar.j = this.c;
        this.b = new f(kVar, jVar);
        this.b.b = this.c;
        this.d = i;
        if (d.a(jVar)) {
            this.g = new anetwork.channel.a.c();
        }
    }

    final void a(int i, String str, anetwork.channel.f.a aVar) {
        if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            ALog.i("ANet.UnifiedNetworkTask", "onRequestFinish", this.c, "statusCode", Integer.valueOf(i));
        }
        if (this.f != null) {
            this.f.cancel(false);
            this.f = null;
        }
        if (aVar != null) {
            aVar.c = i;
        }
        DefaultFinishEvent defaultFinishEvent = new DefaultFinishEvent(i, aVar);
        if (str != null) {
            defaultFinishEvent.c = str;
        }
        this.b.a(defaultFinishEvent);
    }
}
