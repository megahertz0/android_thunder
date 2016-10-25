package anetwork.channel.g;

import android.text.TextUtils;
import anet.channel.RequestCb;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.request.Request;
import anet.channel.statist.FlowStatistic;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import anet.channel.util.StringUtils;
import anet.channel.util.d;
import anetwork.channel.a.c;
import anetwork.channel.entity.j;
import anetwork.channel.h.a;
import anetwork.channel.h.b;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import java.util.Map;

// compiled from: Taobao
final class e implements RequestCb {
    final /* synthetic */ Request a;
    final /* synthetic */ a b;

    e(a aVar, Request request) {
        this.b = aVar;
        this.a = request;
    }

    public final void onResponseCode(int i, Map<String, List<String>> map) {
        if (!this.b.d.get()) {
            if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                StringBuilder stringBuilder = new StringBuilder("[onResponseCode]");
                stringBuilder.append("responseCode:").append(i);
                if (map != null) {
                    stringBuilder.append(", header:").append(map.toString());
                }
                ALog.i("ANet.UnifiedNetworkTask", stringBuilder.toString(), this.b.i.c, new Object[0]);
            }
            if (d.a(this.a, i, map)) {
                this.b.d.compareAndSet(false, true);
                j jVar = this.b.i.a;
                jVar.b = this.a.getUrlString();
                jVar.c = null;
                String[] parseURL = StringUtils.parseURL(jVar.b);
                if (parseURL != null) {
                    jVar.c = parseURL[1];
                }
                jVar.e = null;
                a.a().submit(new a(this.b.i));
                return;
            }
            try {
                if (this.b.i.f != null) {
                    this.b.i.f.cancel(false);
                    this.b.i.f = null;
                }
                b.a(this.b.i.a.b, map);
                this.b.c = i;
                this.b.b = b.a(map);
                if (this.b.i.g != null) {
                    this.b.g = new anetwork.channel.a.b(this.b.b);
                    c cVar = this.b.i.g;
                    map = c.a(map);
                }
                this.b.i.b.a(i, map);
            } catch (Throwable e) {
                ALog.w("ANet.UnifiedNetworkTask", "[onResponseCode] error.", this.b.i.c, e, new Object[0]);
            }
        }
    }

    public final void onDataReceive(anet.channel.a.a aVar, boolean z) {
        if (!this.b.d.get()) {
            if (this.b.a == 0) {
                ALog.i("ANet.UnifiedNetworkTask", "[onDataReceive] receive first data chunk!", this.b.i.c, new Object[0]);
            }
            if (z) {
                ALog.i("ANet.UnifiedNetworkTask", "[onDataReceive] receive all data chunk!", this.b.i.c, new Object[0]);
            }
            if (ALog.isPrintLog(1)) {
                ALog.d("ANet.UnifiedNetworkTask", "[onDataReceive]", this.b.i.c, SocializeConstants.JSON_DATA, new String(aVar.a(), 0, aVar.c()));
            }
            try {
                if (this.b.g != null) {
                    anetwork.channel.a.b bVar = this.b.g;
                    bVar.a.write(aVar.a(), 0, aVar.c());
                    if (z) {
                        anet.channel.c.c.a(new f(this), 0);
                    }
                }
                a aVar2 = this.b;
                aVar2.a++;
                this.b.i.b.a(this.b.a, this.b.b, aVar);
            } catch (Throwable e) {
                ALog.w("ANet.UnifiedNetworkTask", "[onDataReceive] error.", this.b.i.c, e, new Object[0]);
            }
        }
    }

    public final void onFinish(int i, String str, RequestStatistic requestStatistic) {
        if (!this.b.d.getAndSet(true)) {
            if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                ALog.i("ANet.UnifiedNetworkTask", "[onFinish]", this.b.i.c, "statusCode", Integer.valueOf(i), SocialConstants.PARAM_SEND_MSG, str);
            }
            if (i >= 0 || !this.b.i.a.b()) {
                if (this.b.c == 0) {
                    this.b.c = i;
                }
                requestStatistic.retryTimes = this.b.i.a.f;
                requestStatistic.statusCode = this.b.c;
                requestStatistic.msg = str;
                requestStatistic.url = this.a.getUrlString();
                if (i != -200) {
                    AppMonitor.getInstance().commitStat(requestStatistic);
                }
                this.b.f.c = this.b.c;
                anetwork.channel.f.a aVar = this.b.f;
                if (requestStatistic != null) {
                    long j;
                    aVar.a = requestStatistic.protocolType;
                    aVar.b = requestStatistic.ret;
                    aVar.d = requestStatistic.host;
                    if (!(requestStatistic.ip == null || requestStatistic.port == 0)) {
                        aVar.f = String.format("%s:%d", new Object[]{requestStatistic.ip, Integer.valueOf(requestStatistic.port)});
                    }
                    aVar.h = requestStatistic.isSSL;
                    aVar.m = requestStatistic.oneWayTime;
                    aVar.s = requestStatistic.firstDataTime;
                    aVar.r = requestStatistic.sendBeforeTime;
                    aVar.t = requestStatistic.recDataTime;
                    aVar.y = requestStatistic.sendDataSize;
                    aVar.z = requestStatistic.recDataSize;
                    aVar.v = requestStatistic.serverRT;
                    if (aVar.t != 0) {
                        j = aVar.z / aVar.t;
                    } else {
                        j = aVar.z;
                    }
                    aVar.B = j;
                }
                if (ALog.isPrintLog(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
                    ALog.i("ANet.UnifiedNetworkTask", this.b.f.toString(), this.b.i.c, new Object[0]);
                }
                if (i >= 0) {
                    anet.channel.monitor.a.a().a(requestStatistic.start, requestStatistic.start + requestStatistic.oneWayTime, requestStatistic.recDataSize);
                }
                a.a.a(this.b.i.a.b, this.b.f);
                AppMonitor.getInstance().commitStat(new FlowStatistic(this.b.h, requestStatistic));
                anetwork.channel.f.b a = anetwork.channel.f.b.a();
                Object obj = this.b.i.a.b;
                long currentTimeMillis = System.currentTimeMillis();
                if (a.a && !TextUtils.isEmpty(obj) && currentTimeMillis > 0) {
                    if (a.c.remove(anetwork.channel.f.b.a(obj)) && a.c.isEmpty()) {
                        currentTimeMillis = System.currentTimeMillis() - a.b;
                        ALog.i("awcn.StatisticReqTimes", new StringBuilder("this req spend times: ").append(currentTimeMillis).toString(), null, new Object[0]);
                        a.e = currentTimeMillis + a.e;
                    }
                }
                this.b.i.a(this.b.c, str, this.b.f);
                return;
            }
            j jVar = this.b.i.a;
            jVar.f++;
            this.b.i.a.c();
            this.b.i.e = new a(this.b.i);
            a.a().submit(this.b.i.e);
        }
    }
}
