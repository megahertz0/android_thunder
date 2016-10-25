package com.xunlei.downloadprovider.download.tasklist.list.a;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.toolbox.i;
import com.baidu.mobad.feeds.NativeResponse;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment.LOAD_TAG;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.h;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.m;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.m$a;
import com.xunlei.downloadprovider.download.tasklist.list.a.b.b;
import com.xunlei.downloadprovider.download.tasklist.list.b.a;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.web.BrowserUtil;
import java.util.Set;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: TaskAdCardViewHolder.java
public final class r extends a {
    private i k;
    private com.xunlei.downloadprovider.download.tasklist.list.a l;
    private e m;

    private r(View view, i iVar, com.xunlei.downloadprovider.download.tasklist.list.a aVar) {
        super(view);
        this.l = aVar;
        this.k = iVar;
    }

    public static r a(Context context, ViewGroup viewGroup, i iVar, com.xunlei.downloadprovider.download.tasklist.list.a aVar) {
        return new r(a.a(context, viewGroup), iVar, aVar);
    }

    public final void a(e eVar) {
        super.a(eVar);
        this.m = eVar;
        m.a();
        m.a(this);
    }

    public final void a() {
        this.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.b.setImageResource(R.drawable.download_ad_background);
    }

    public final void a(b bVar) {
        if (bVar != null) {
            Set set;
            String a;
            if (bVar.q == 0) {
                NativeADDataRef nativeADDataRef = bVar.a;
                set = com.xunlei.downloadprovider.download.tasklist.list.a.b.a.a().c;
                a = h.a((m$a) this);
                if (!set.contains(a)) {
                    set.add(a);
                    n.a().a(new h(this.a, nativeADDataRef, this.l.a(), d()));
                }
                if (nativeADDataRef.isAPP()) {
                    this.e.setVisibility(0);
                    if (!h.a(nativeADDataRef.getDownloadCount()).equals("0")) {
                        this.e.setText(h.a(nativeADDataRef.getDownloadCount()));
                        if (this.h != null) {
                            this.h.setText("\u817e\u8baf\u5e7f\u544a");
                        }
                        this.d.setVisibility(0);
                        if (nativeADDataRef.isAPP()) {
                            this.d.setRating(4.0f);
                        } else {
                            this.d.setRating(Float.valueOf((float) nativeADDataRef.getAPPScore()).floatValue() / 2.0f);
                        }
                        this.c.setText(nativeADDataRef.getDesc());
                        if (!TextUtils.isEmpty(nativeADDataRef.getImgUrl())) {
                            this.b.a(nativeADDataRef.getImgUrl(), this.k);
                        }
                        this.a.setOnClickListener(new w(this, nativeADDataRef));
                        if (com.xunlei.downloadprovider.util.r.c().e != null || com.xunlei.downloadprovider.util.r.c().e.d()) {
                            this.f.setVisibility(0);
                        } else {
                            this.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        }
                        this.f.setOnClickListener(new z(this));
                        if (nativeADDataRef.isAPP()) {
                            this.g.setText("\u6253\u5f00");
                        } else {
                            this.g.setText("\u5b89\u88c5");
                        }
                    }
                }
                this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                if (this.h != null) {
                    this.h.setText("\u817e\u8baf\u5e7f\u544a");
                }
                this.d.setVisibility(0);
                if (nativeADDataRef.isAPP()) {
                    this.d.setRating(4.0f);
                } else {
                    this.d.setRating(Float.valueOf((float) nativeADDataRef.getAPPScore()).floatValue() / 2.0f);
                }
                this.c.setText(nativeADDataRef.getDesc());
                if (TextUtils.isEmpty(nativeADDataRef.getImgUrl())) {
                    this.b.a(nativeADDataRef.getImgUrl(), this.k);
                }
                this.a.setOnClickListener(new w(this, nativeADDataRef));
                if (com.xunlei.downloadprovider.util.r.c().e != null) {
                }
                this.f.setVisibility(0);
                this.f.setOnClickListener(new z(this));
                if (nativeADDataRef.isAPP()) {
                    this.g.setText("\u6253\u5f00");
                } else {
                    this.g.setText("\u5b89\u88c5");
                }
            } else if (bVar.q == 3) {
                NativeResponse nativeResponse = bVar.b;
                set = com.xunlei.downloadprovider.download.tasklist.list.a.b.a.a().c;
                a = h.a((m$a) this);
                if (!set.contains(a)) {
                    set.add(a);
                    n.a().a(new b(this.a, nativeResponse, this.l.a(), d()));
                }
                this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.d.setVisibility(0);
                this.d.setRating(4.0f);
                if (this.h != null) {
                    this.h.setText("\u767e\u5ea6\u5e7f\u544a");
                }
                this.c.setText(nativeResponse.getDesc());
                if (!TextUtils.isEmpty(nativeResponse.getImageUrl())) {
                    this.b.a(nativeResponse.getImageUrl(), this.k);
                }
                this.a.setOnClickListener(new aa(this, nativeResponse));
                if (com.xunlei.downloadprovider.util.r.c().e == null || com.xunlei.downloadprovider.util.r.c().e.d()) {
                    this.f.setVisibility(0);
                } else {
                    this.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                }
                this.f.setOnClickListener(new ad(this));
                if (nativeResponse.isDownloadApp()) {
                    this.g.setText("\u5b89\u88c5");
                } else {
                    this.g.setText("\u6253\u5f00");
                }
            } else {
                Set set2 = com.xunlei.downloadprovider.download.tasklist.list.a.b.a.a().c;
                String a2 = h.a((m$a) this);
                if (!set2.contains(a2)) {
                    set2.add(a2);
                    if (bVar.q == 1) {
                        n.a().a(new e(this.l.a(), bVar.c.b, d(), bVar.m, bVar));
                    } else if (bVar.q == 2) {
                        n.a().a(new k(this.l.a(), bVar.c.b, d(), bVar.m, bVar));
                    }
                }
                this.c.setText(bVar.f);
                if (!TextUtils.isEmpty(bVar.j)) {
                    this.b.a(bVar.j, this.k);
                }
                if (this.h != null) {
                    this.h.setText("\u8fc5\u96f7\u5e7f\u544a");
                }
                if (!(TextUtils.isEmpty(bVar.l) || bVar.l.equals("0") || !bVar.k.equals(c.c))) {
                    this.e.setVisibility(0);
                    CharSequence a3 = h.a(bVar.l);
                    if (a3 == null || !a3.trim().equals("0")) {
                        this.e.setText(a3);
                        this.d.setVisibility(0);
                        if (!TextUtils.isEmpty(bVar.p) || bVar.p.equals("0")) {
                            this.d.setRating(4.0f);
                        } else {
                            this.d.setRating(Float.valueOf(bVar.p).floatValue());
                        }
                        this.a.setOnClickListener(new s(this, bVar));
                        if (com.xunlei.downloadprovider.util.r.c().e != null || com.xunlei.downloadprovider.util.r.c().e.d()) {
                            this.f.setVisibility(0);
                        } else {
                            this.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        }
                        this.f.setOnClickListener(new v(this, bVar));
                        if (bVar.k.equals(c.c)) {
                            this.g.setText("\u6253\u5f00");
                        } else {
                            this.g.setText("\u5b89\u88c5");
                        }
                    }
                }
                this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.d.setVisibility(0);
                if (TextUtils.isEmpty(bVar.p)) {
                }
                this.d.setRating(4.0f);
                this.a.setOnClickListener(new s(this, bVar));
                if (com.xunlei.downloadprovider.util.r.c().e != null) {
                }
                this.f.setVisibility(0);
                this.f.setOnClickListener(new v(this, bVar));
                if (bVar.k.equals(c.c)) {
                    this.g.setText("\u6253\u5f00");
                } else {
                    this.g.setText("\u5b89\u88c5");
                }
            }
            this.a.setVisibility(0);
        }
    }

    public final int b() {
        return this.l.a();
    }

    public final int d() {
        return (this.m == null || !(this.m.c instanceof Integer)) ? 1 : ((Integer) this.m.c).intValue() + 1;
    }

    public final void e() {
        this.l.h.f.remove(LOAD_TAG.LOAD_LIST_AD);
        this.a.post(new ae(this));
    }

    static /* synthetic */ void a(r rVar, b bVar) {
        String str = bVar.k;
        if (!str.equals("0")) {
            if (str.equals(c.f)) {
                if (!TextUtils.isEmpty(bVar.g)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("from_key", "adv_homeflow_video");
                    bundle.putString("ad_id", bVar.d);
                    BrowserUtil.a();
                    BrowserUtil.a(rVar.c(), bVar.g, bVar.h, 0, bundle);
                }
            } else if (str.equals(c.e)) {
                if (!TextUtils.isEmpty(bVar.g)) {
                    BrowserUtil.a();
                    BrowserUtil.a(rVar.c(), 0, bVar.g, true, null, false, null);
                }
            } else if (str.equals(c.c)) {
                String str2 = bVar.i;
                if (!TextUtils.isEmpty(str2)) {
                    g gVar = new g(3, str2, null);
                    if (rVar.l != null) {
                        switch (rVar.l.a()) {
                            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                                gVar.d = com.xunlei.downloadprovider.service.a.h;
                                break;
                            case SimpleLog.LOG_LEVEL_TRACE:
                                gVar.d = com.xunlei.downloadprovider.service.a.f;
                                break;
                            case SimpleLog.LOG_LEVEL_DEBUG:
                                gVar.d = com.xunlei.downloadprovider.service.a.g;
                                break;
                        }
                        gVar.d += bVar.d;
                    }
                    com.xunlei.downloadprovider.service.downloads.task.b bVar2 = new com.xunlei.downloadprovider.service.downloads.task.b();
                    bVar2.c = bVar.o;
                    bVar2.e = bVar.n;
                    bVar2.d = true;
                    ((ThunderTask) rVar.c()).createLocalTaskWithAdditionInfo(str2, bVar.n, 0, null, null, null, 0, gVar, null, false, bVar2);
                }
            }
        }
    }
}
