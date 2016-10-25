package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;
import com.xunlei.downloadprovider.commonview.dialog.x;
import com.xunlei.downloadprovider.download.center.widget.t;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import java.util.ArrayList;
import java.util.List;

// compiled from: XZBTaskControl.java
public class am implements d {
    private static final String a;
    private String b;
    private Context c;
    private XZBDevice d;
    private List<al> e;
    private long f;
    private long g;
    private Toast h;
    private x i;

    static {
        a = am.class.getSimpleName();
    }

    public am(Context context, XZBDevice xZBDevice) {
        this.c = context;
        this.d = xZBDevice;
        XZBShouleiUtil.getInstance().updateDeviceList(XZBShouleiUtil.QUERYDEVICE_USERDATA_XZB_DOWNLOAD_CENTER, new an(this));
    }

    public final XZBDevice b() {
        return this.d;
    }

    public final void a(al alVar) {
        this.b = alVar.getId();
        Intent intent = new Intent("show_task_detail");
        intent.putExtra("task_serial_extra", alVar);
        this.c.sendBroadcast(intent);
    }

    public final void a(List<al> list) {
        this.e = list;
        for (al alVar : list) {
            if (TextUtils.equals(alVar.getId(), this.b)) {
                new StringBuilder("updateFrontTaskInfo frontTaskId = ").append(this.b);
                Intent intent = new Intent("update_task_detail");
                intent.putExtra("task_serial_extra", alVar);
                this.c.sendBroadcast(intent);
                return;
            }
        }
    }

    public final void a(List<al> list, String str, c cVar) {
        XZBShouleiUtil.getInstance().startTask(this.d, new ArrayList(list), str, new ao(this, cVar));
    }

    public final void a(List<al> list, String str, b bVar) {
        XZBShouleiUtil.getInstance().pauseTask(this.d, new ArrayList(list), str, new ap(this, bVar));
    }

    public final void a(List<al> list, String str, a aVar) {
        t tVar = new t(this.c);
        tVar.d = new aq(this, aVar, str);
        tVar.e = new ar(this, aVar, str, list, tVar);
        tVar.show();
    }

    public final void a(long j, long j2) {
        this.f = j;
        this.g = j2;
    }

    public final long a() {
        return this.f;
    }

    static /* synthetic */ void a(am amVar, String str) {
        if (amVar.h == null) {
            amVar.h = new Toast(amVar.c);
        }
        Toast.makeText(amVar.c, str, 0).show();
    }
}
