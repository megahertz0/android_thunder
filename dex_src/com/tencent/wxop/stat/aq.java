package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.a.e;
import com.tencent.wxop.stat.common.k;
import com.tencent.wxop.stat.common.p;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

class aq {
    private static volatile long f;
    private e a;
    private StatReportStrategy b;
    private boolean c;
    private Context d;
    private long e;

    static {
        f = 0;
    }

    public aq(e eVar) {
        this.b = null;
        this.c = false;
        this.d = null;
        this.e = System.currentTimeMillis();
        this.a = eVar;
        this.b = StatConfig.getStatSendStrategy();
        this.c = eVar.f();
        this.d = eVar.e();
    }

    private void a(h hVar) {
        i.b(StatServiceImpl.t).a(this.a, hVar);
    }

    private void b() {
        if (this.a.d() != null && this.a.d().isSendImmediately()) {
            this.b = StatReportStrategy.INSTANT;
        }
        if (StatConfig.j && a.a(StatServiceImpl.t).e()) {
            this.b = StatReportStrategy.INSTANT;
        }
        if (StatConfig.isDebugEnable()) {
            StatServiceImpl.q.i(new StringBuilder("strategy=").append(this.b.name()).toString());
        }
        switch (ag.a[this.b.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                c();
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                au.a(this.d).a(this.a, null, this.c, false);
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.q.i(new StringBuilder("PERIOD currTime=").append(this.e).append(",nextPeriodSendTs=").append(StatServiceImpl.c).append(",difftime=").append(StatServiceImpl.c - this.e).toString());
                }
                if (StatServiceImpl.c == 0) {
                    StatServiceImpl.c = p.a(this.d, "last_period_ts", 0);
                    if (this.e > StatServiceImpl.c) {
                        StatServiceImpl.e(this.d);
                    }
                    long sendPeriodMinutes = this.e + ((long) ((StatConfig.getSendPeriodMinutes() * 60) * 1000));
                    if (StatServiceImpl.c > sendPeriodMinutes) {
                        StatServiceImpl.c = sendPeriodMinutes;
                    }
                    d.a(this.d).a();
                }
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.q.i(new StringBuilder("PERIOD currTime=").append(this.e).append(",nextPeriodSendTs=").append(StatServiceImpl.c).append(",difftime=").append(StatServiceImpl.c - this.e).toString());
                }
                if (this.e > StatServiceImpl.c) {
                    StatServiceImpl.e(this.d);
                }
            case XZBDevice.DOWNLOAD_LIST_FAILED:
            case XZBDevice.DOWNLOAD_LIST_ALL:
                au.a(this.d).a(this.a, null, this.c, false);
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                au.a(this.d).a(this.a, new ar(this), this.c, true);
            case R.styleable.Toolbar_contentInsetEnd:
                if (a.a(StatServiceImpl.t).c() == 1) {
                    c();
                } else {
                    au.a(this.d).a(this.a, null, this.c, false);
                }
            case R.styleable.Toolbar_contentInsetLeft:
                if (k.e(this.d)) {
                    a(new as(this));
                }
            default:
                StatServiceImpl.q.error(new StringBuilder("Invalid stat strategy:").append(StatConfig.getStatSendStrategy()).toString());
        }
    }

    private void c() {
        if (au.b().a <= 0 || !StatConfig.l) {
            a(new at(this));
            return;
        }
        au.b().a(this.a, null, this.c, true);
        au.b().a(-1);
    }

    private boolean d() {
        if (StatConfig.h > 0) {
            if (this.e > StatServiceImpl.h) {
                StatServiceImpl.g.clear();
                StatServiceImpl.h = this.e + StatConfig.i;
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.q.i(new StringBuilder("clear methodsCalledLimitMap, nextLimitCallClearTime=").append(StatServiceImpl.h).toString());
                }
            }
            Integer valueOf = Integer.valueOf(this.a.a().a());
            Integer num = (Integer) StatServiceImpl.g.get(valueOf);
            if (num != null) {
                StatServiceImpl.g.put(valueOf, Integer.valueOf(num.intValue() + 1));
                if (num.intValue() > StatConfig.h) {
                    if (StatConfig.isDebugEnable()) {
                        StatServiceImpl.q.e(new StringBuilder("event ").append(this.a.g()).append(" was discard, cause of called limit, current:").append(num).append(", limit:").append(StatConfig.h).append(", period:").append(StatConfig.i).append(" ms").toString());
                    }
                    return true;
                }
            }
            StatServiceImpl.g.put(valueOf, Integer.valueOf(1));
        }
        return false;
    }

    public void a() {
        if (!d()) {
            if (StatConfig.m > 0 && this.e >= f) {
                StatServiceImpl.flushDataToDB(this.d);
                f = this.e + StatConfig.n;
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.q.i(new StringBuilder("nextFlushTime=").append(f).toString());
                }
            }
            if (a.a(this.d).f()) {
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.q.i(new StringBuilder("sendFailedCount=").append(StatServiceImpl.a).toString());
                }
                if (StatServiceImpl.a()) {
                    au.a(this.d).a(this.a, null, this.c, false);
                    if (this.e - StatServiceImpl.b > 1800000) {
                        StatServiceImpl.d(this.d);
                        return;
                    }
                    return;
                }
                b();
                return;
            }
            au.a(this.d).a(this.a, null, this.c, false);
        }
    }
}
