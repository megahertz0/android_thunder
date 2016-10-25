package com.alipay.b.a.a.c.b;

import android.content.Context;
import com.alipay.b.a.a.c.a;
import com.alipay.b.a.a.c.a.c;
import com.alipay.tscenter.biz.rpc.vkeydfp.request.DeviceDataReportRequest;
import com.alipay.tscenter.biz.rpc.vkeydfp.result.DeviceDataReportResult;
import org.android.agoo.message.MessageService;

public final class b implements a {
    private static a a;
    private static a b;

    static {
        a = null;
        b = null;
    }

    public static a a(Context context, String str) {
        a aVar = null;
        if (context == null) {
            return null;
        }
        if (a == null) {
            if (context != null) {
                aVar = com.alipay.b.a.a.c.b.a(context, str);
            }
            b = aVar;
            a = new b();
        }
        return a;
    }

    public final com.alipay.b.a.a.c.a.b a(c cVar) {
        DeviceDataReportRequest deviceDataReportRequest = new DeviceDataReportRequest();
        deviceDataReportRequest.os = cVar.a();
        deviceDataReportRequest.apdid = cVar.b();
        deviceDataReportRequest.pubApdid = cVar.c();
        deviceDataReportRequest.priApdid = cVar.d();
        deviceDataReportRequest.token = cVar.e();
        deviceDataReportRequest.umidToken = cVar.f();
        deviceDataReportRequest.version = cVar.g();
        deviceDataReportRequest.lastTime = cVar.h();
        deviceDataReportRequest.dataMap = cVar.i();
        DeviceDataReportResult a = b.a(deviceDataReportRequest);
        com.alipay.b.a.a.c.a.b bVar = new com.alipay.b.a.a.c.a.b();
        if (a == null) {
            return null;
        }
        bVar.a = a.success;
        bVar.b = a.resultCode;
        bVar.c = a.apdid;
        bVar.d = a.token;
        bVar.e = a.currentTime;
        bVar.f = a.version;
        bVar.g = a.vkeySwitch;
        bVar.i = a.appListVer;
        String str = a.bugTrackSwitch;
        bVar.h = MessageService.MSG_DB_READY_REPORT;
        bVar.j = MessageService.MSG_DB_READY_REPORT;
        if (!com.alipay.b.a.a.a.a.b(str) || str.length() <= 0) {
            return bVar;
        }
        bVar.h = str.charAt(0);
        return bVar;
    }

    public final boolean a(String str) {
        return b.a(str);
    }
}
