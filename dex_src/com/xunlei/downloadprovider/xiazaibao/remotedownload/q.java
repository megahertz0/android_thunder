package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.content.Context;
import android.support.v7.widget.RecyclerView.t;
import android.view.ViewGroup;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskInfo;
import com.xunlei.xiazaibao.shoulei.DownloadPathType;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: RemoteDownloadListAdapter.java
public final class q extends android.support.v7.widget.RecyclerView.a<g> {
    final al a;
    final al b;
    List<al> c;
    boolean d;
    a e;
    com.xunlei.downloadprovider.xiazaibao.remotedownload.g.a f;
    d g;
    int h;
    private Context i;
    private int j;

    // compiled from: RemoteDownloadListAdapter.java
    public static interface a {
        void a();
    }

    public final /* synthetic */ void onBindViewHolder(t tVar, int i) {
        g gVar = (g) tVar;
        al alVar = (al) this.c.get(i);
        gVar.a(this.d);
        gVar.d = this;
        gVar.e = this.f;
        gVar.b = this.g;
        if (gVar instanceof a) {
            ((a) gVar).a = this.h;
        }
        gVar.a(alVar);
        if (this.j != 0) {
            return;
        }
        if (i == 0 && !XZBShouleiUtil.getInstance().isClickSettingDownloadDeviceFromXZBTaskList(this.i) && XZBShouleiUtil.getInstance().getDownloadPathTypeMsg().getDownloadPathType() == DownloadPathType.XZB) {
            if (gVar != null && (gVar instanceof ac)) {
                ((ac) gVar).f.setVisibility(0);
            }
        } else if (gVar != null && (gVar instanceof ac)) {
            ((ac) gVar).a();
        }
    }

    public q(Context context, d dVar, int i) {
        this.a = new al();
        this.b = new al();
        this.i = context;
        this.c = new ArrayList();
        this.g = dVar;
        this.j = i;
    }

    public final long getItemId(int i) {
        return super.getItemId(i);
    }

    public final int getItemCount() {
        if (this.c.isEmpty()) {
            return 0;
        }
        return (this.d && a()) ? this.c.size() - 1 : this.c.size();
    }

    public final int getItemViewType(int i) {
        if (((al) this.c.get(i)) == this.a) {
            int i2 = 1;
        } else {
            Object obj = null;
        }
        if (obj != null) {
            return SimpleLog.LOG_LEVEL_DEBUG;
        }
        if (((al) this.c.get(i)) == this.b) {
            i2 = 1;
        } else {
            obj = null;
        }
        return obj != null ? MqttConnectOptions.MQTT_VERSION_3_1 : 1;
    }

    final boolean a() {
        return !this.c.isEmpty() && this.c.contains(this.a);
    }

    public final void b() {
        notifyDataSetChanged();
        this.g.a(this.c);
    }

    public final void c() {
        if (this.e != null) {
            this.e.a();
        }
    }

    public final List<al> d() {
        List<al> arrayList = new ArrayList();
        for (al alVar : this.c) {
            if (alVar.a) {
                arrayList.add(alVar);
            }
        }
        return arrayList;
    }

    public final void a(List<DownloadTaskInfo> list, boolean z) {
        if (!this.d) {
            this.c.clear();
            for (DownloadTaskInfo downloadTaskInfo : list) {
                this.c.add(new al(downloadTaskInfo));
            }
            if (z) {
                this.c.add(this.a);
            }
        }
    }

    public final void b(List<DownloadTaskInfo> list, boolean z) {
        if (!this.d) {
            if (this.c.size() > 0 && a()) {
                this.c.remove(this.a);
            }
            for (DownloadTaskInfo downloadTaskInfo : list) {
                this.c.add(new al(downloadTaskInfo));
            }
            if (z) {
                this.c.add(this.a);
            }
        }
    }

    public final /* synthetic */ t onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 2) {
            return f.a(viewGroup.getContext(), viewGroup);
        }
        return i == 3 ? a.a(viewGroup.getContext(), viewGroup) : ac.a(viewGroup.getContext());
    }
}
