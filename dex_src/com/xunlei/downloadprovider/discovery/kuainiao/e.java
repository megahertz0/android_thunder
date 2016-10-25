package com.xunlei.downloadprovider.discovery.kuainiao;

import android.text.TextUtils;
import com.xunlei.common.accelerator.XLAccelUtil;
import com.xunlei.common.accelerator.bean.KnParams;
import com.xunlei.common.accelerator.bean.XLAccelBandInfo;
import com.xunlei.common.accelerator.http.XLAccelHttpReqInfo;
import com.xunlei.common.base.XLAlarmBaseTimer;
import com.xunlei.common.base.XLAlarmBaseTimer$TimerListener;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.discovery.kuainiao.a.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

// compiled from: KuaiNiaoManager.java
public final class e implements XLAlarmBaseTimer$TimerListener, com.xunlei.downloadprovider.discovery.kuainiao.a.a {
    private static e c;
    public boolean a;
    private boolean b;
    private boolean d;
    private List<a> e;

    // compiled from: KuaiNiaoManager.java
    public static interface a {
        void a(int i, int i2, KnParams knParams);

        void a(int i, XLAccelBandInfo xLAccelBandInfo);
    }

    public static e a() {
        if (c == null) {
            c = new e();
        }
        return c;
    }

    private e() {
        this.d = false;
        this.e = new ArrayList();
        BrothersApplication.i().a();
        XLAccelUtil.getAccelerator().attachListener(this);
    }

    public final void b() {
        if (!this.b) {
            this.b = true;
            BrothersApplication.i();
            b.b();
        }
    }

    public static void c() {
        BrothersApplication.i();
        XLAccelUtil.getAccelerator().queryStatus();
    }

    public final void a(a aVar) {
        if (!this.e.contains(aVar)) {
            this.e.add(aVar);
        }
    }

    public final void b(a aVar) {
        this.e.remove(aVar);
    }

    private void a(int i, int i2, KnParams knParams) {
        for (a aVar : this.e) {
            aVar.a(i, i2, knParams);
        }
    }

    private void a(int i, XLAccelBandInfo xLAccelBandInfo) {
        this.b = false;
        if (i != 0 || xLAccelBandInfo == null) {
            this.a = false;
        } else {
            this.a = true;
        }
        for (a aVar : this.e) {
            aVar.a(i, xLAccelBandInfo);
        }
    }

    public final void callBack(int i, int i2, String str) {
        XLAccelBandInfo xLAccelBandInfo = null;
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.Success:
                a(-1, null);
                a(i2, XLAccelUtil.getAccelerator().isKuaiNiao(), XLAccelUtil.getAccelerator().getKnParams());
                d();
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                if (!this.d) {
                    this.d = true;
                    XLAlarmBaseTimer.getInstance().registerTimer(124442, XLAccelHttpReqInfo.getInitTryStatusTime(), false, this);
                }
            default:
                d();
                if (!TextUtils.isEmpty(XLAccelUtil.getAccelerator().getBandInfo())) {
                    xLAccelBandInfo = new XLAccelBandInfo();
                }
                a(0, xLAccelBandInfo);
                a(i2, XLAccelUtil.getAccelerator().isKuaiNiao(), XLAccelUtil.getAccelerator().getKnParams());
        }
    }

    private void d() {
        if (this.d) {
            this.d = false;
            XLAlarmBaseTimer.getInstance().unRegisterTimer(124442);
        }
    }

    public final void onTimerTick(int i) {
        d();
        XLAccelUtil.getAccelerator().reInit();
    }
}
