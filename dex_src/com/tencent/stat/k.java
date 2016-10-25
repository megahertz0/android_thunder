package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.a.e;
import com.tencent.stat.a.f;
import com.tencent.stat.common.p;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

class k implements Runnable {
    private e a;
    private StatReportStrategy b;
    private c c;

    public k(e eVar) {
        this.b = null;
        this.c = new l(this);
        this.a = eVar;
        this.b = StatConfig.getStatSendStrategy();
    }

    private void a() {
        if (n.b().a() > 0) {
            n.b().a(this.a, null);
            n.b().a(-1);
            return;
        }
        a(true);
    }

    private void a(boolean z) {
        d.b().a(this.a, this.c);
    }

    public void run() {
        try {
            if (!StatConfig.isEnableStatService()) {
                return;
            }
            if (this.a.a() == f.c || this.a.d().length() <= StatConfig.getMaxReportEventLength()) {
                if (StatConfig.getMaxSessionStatReportCount() > 0) {
                    if (StatConfig.getCurSessionStatReportCount() >= StatConfig.getMaxSessionStatReportCount()) {
                        StatService.b().e((Object) "Times for reporting events has reached the limit of StatConfig.getMaxSessionStatReportCount() in current session.");
                        return;
                    }
                    StatConfig.c();
                }
                StatService.b().i(new StringBuilder("Lauch stat task in thread:").append(Thread.currentThread().getName()).toString());
                Context c = this.a.c();
                if (com.tencent.stat.common.k.h(c)) {
                    if (StatConfig.isEnableSmartReporting() && this.b != StatReportStrategy.ONLY_WIFI_NO_CACHE && com.tencent.stat.common.k.g(c)) {
                        this.b = StatReportStrategy.INSTANT;
                    }
                    switch (h.a[this.b.ordinal()]) {
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            a();
                            return;
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            if (com.tencent.stat.common.k.e(c)) {
                                a();
                                return;
                            } else {
                                n.a(c).a(this.a, null);
                                return;
                            }
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                            n.a(c).a(this.a, null);
                            return;
                        case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                            if (n.a(this.a.c()) != null) {
                                n.a(c).a(this.a, new m(this));
                                return;
                            }
                            return;
                        case R.styleable.Toolbar_contentInsetEnd:
                            try {
                                n.a(c).a(this.a, null);
                                String str = "last_period_ts";
                                Long valueOf = Long.valueOf(p.a(c, str, 0));
                                Long valueOf2 = Long.valueOf(System.currentTimeMillis());
                                if (Long.valueOf(Long.valueOf(valueOf2.longValue() - valueOf.longValue()).longValue() / 60000).longValue() > ((long) StatConfig.getSendPeriodMinutes())) {
                                    n.a(c).a(-1);
                                    p.b(c, str, valueOf2.longValue());
                                    return;
                                }
                                return;
                            } catch (Exception e) {
                                StatService.b().e(e);
                            }
                        case R.styleable.Toolbar_contentInsetLeft:
                            if (com.tencent.stat.common.k.e(c)) {
                                a(false);
                                return;
                            }
                            return;
                        default:
                            StatService.b().error(new StringBuilder("Invalid stat strategy:").append(StatConfig.getStatSendStrategy()).toString());
                            return;
                    }
                }
                n.a(c).a(this.a, null);
                return;
            }
            StatService.b().e(new StringBuilder("Event length exceed StatConfig.getMaxReportEventLength(): ").append(StatConfig.getMaxReportEventLength()).toString());
        } catch (Object th) {
            StatService.b().e(th);
        }
    }
}
