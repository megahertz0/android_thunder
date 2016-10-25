package com.taobao.accs.internal;

import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UT;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.b;
import com.taobao.accs.utl.h;
import com.umeng.a;

// compiled from: Taobao
class g implements Runnable {
    final /* synthetic */ ServiceImpl a;

    g(ServiceImpl serviceImpl) {
        this.a = serviceImpl;
    }

    public void run() {
        ServiceImpl.d();
        this.a.a(this.a.b);
        UTMini.getInstance().commitEvent(UT.EVENT_ID, UtilityImpl.getDeviceId(this.a.b), UtilityImpl.getProxy(), "PROXY");
        long serviceAliveTime = UtilityImpl.getServiceAliveTime(this.a.b);
        ALog.d("ServiceImpl", "getServiceAliveTime", "aliveTime", Long.valueOf(serviceAliveTime));
        if (serviceAliveTime > 20000) {
            b.a(h.NAMESPACE, BaseMonitor.COUNT_SERVICE_ALIVE, a.d, (double) (serviceAliveTime / 1000));
        }
        UtilityImpl.setServiceTime(this.a.b, Constants.SP_KEY_SERVICE_START, System.currentTimeMillis());
    }
}
