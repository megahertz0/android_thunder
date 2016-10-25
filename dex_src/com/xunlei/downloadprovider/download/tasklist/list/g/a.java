package com.xunlei.downloadprovider.download.tasklist.list.g;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.download.tasklist.list.b.d;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import com.xunlei.downloadprovider.frame.user.bx;
import com.xunlei.downloadprovider.member.b.c;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.g;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: VipRenewRemindViewHolder.java
public final class a extends d implements g {
    com.xunlei.downloadprovider.member.b.a.d h;
    private com.xunlei.downloadprovider.download.tasklist.list.a k;
    private e l;
    private int m;
    private bx n;
    private Context o;
    private String p;
    private j q;

    private a(View view) {
        super(view);
        this.m = 0;
        this.n = new bx();
        this.a.setImageResource(R.drawable.xunlei_vip_default);
        this.c.setText("\u60a8\u7684\u767d\u91d1\u4f1a\u5458\u5373\u5c06\u5230\u671f");
        this.d.setText("\u9a6c\u4e0a\u7eed\u8d39\u7acb\u4eab\u4f4e\u81f38\u6298\u4f18\u60e0");
        this.g.setText("\u4f18\u60e0");
        this.f.setText("\u7acb\u5373\u7eed\u8d39");
        view.setOnClickListener(new b(this));
        this.e.setOnClickListener(new c(this));
        this.f.setOnClickListener(new d(this));
        LoginHelper.a().a(this);
    }

    private void b() {
        this.k.a(this.l);
        this.l = null;
        this.k.h.l = false;
        this.k.h.g = false;
        this.k.notifyDataSetChanged();
    }

    public final void a(e eVar) {
        if (this.l == null) {
            this.l = eVar;
        }
        this.h = (com.xunlei.downloadprovider.member.b.a.d) eVar.a(c.class);
        if (this.n == null) {
            this.n = new bx();
        }
        if (this.h != null) {
            this.c.setText(this.h.e);
            this.d.setText(this.h.f);
            this.f.setText(this.h.g);
        }
        if (!(this.n == null || this.n == null)) {
            if (this.n.b()) {
                this.a.setImageResource(R.drawable.xunlei_vip_platinum);
            } else if (this.n.d()) {
                this.a.setImageResource(R.drawable.xunlei_vip_super);
            } else {
                this.a.setImageResource(R.drawable.xunlei_vip_default);
            }
        }
        long j = (long) this.h.k;
        if (this.m == 0) {
            ThunderReporter.g a = ThunderReporter.g.a("android_renewTip", "renewTip_show", "renewTip_show").a("from", "dl_center_top", MqttConnectOptions.MQTT_VERSION_3_1);
            String str = "is_login";
            LoginHelper.a();
            ThunderReporter.g b = a.a(str, LoginHelper.c() ? com.xunlei.analytics.b.c.f : "0", MqttConnectOptions.MQTT_VERSION_3_1).a("is_vip", LoginHelper.a().f() ? com.xunlei.analytics.b.c.f : "0", MqttConnectOptions.MQTT_VERSION_3_1).b("renewdays", j);
            ThunderReporter.a(b);
            ThunderReporter.a(b, true);
            if (this.n == null) {
                this.n = new bx();
            }
            int i = this.h.k;
            if (this.n != null) {
                if (this.n.a()) {
                    if (i >= -3 && i < 0) {
                        this.p = "v_an_shoulei_push_xzzx_03pt";
                    } else if (i == 0) {
                        this.p = "v_an_shoulei_push_xzzx_0pt";
                    } else if (i > 0 && i <= 4) {
                        this.p = "v_an_shoulei_push_xzzx_4pt";
                    } else if (i >= 14 && i <= 15) {
                        this.p = "v_an_shoulei_push_xzzx_15pt";
                    }
                } else if (this.n.b()) {
                    if (i >= -3 && i < 0) {
                        this.p = "v_an_shoulei_push_xzzx_03bj";
                    } else if (i == 0) {
                        this.p = "v_an_shoulei_push_xzzx_0bj";
                    } else if (i > 0 && i <= 4) {
                        this.p = "v_an_shoulei_push_xzzx_4bj";
                    } else if (i >= 14 && i <= 15) {
                        this.p = "v_an_shoulei_push_xzzx_15bj";
                    }
                } else if (this.n.d()) {
                    if (i >= -3 && i < 0) {
                        this.p = "v_an_shoulei_push_xzzx_03sp";
                    } else if (i == 0) {
                        this.p = "v_an_shoulei_push_xzzx_0sp";
                    } else if (i > 0 && i <= 4) {
                        this.p = "v_an_shoulei_push_xzzx_4sp";
                    } else if (i >= 14 && i <= 15) {
                        this.p = "v_an_shoulei_push_xzzx_15sp";
                    }
                }
            }
            this.m++;
        }
    }

    public final void a() {
        this.k.h.m = true;
        b();
    }

    public static a a(Context context, ViewGroup viewGroup, com.xunlei.downloadprovider.download.a.a aVar, com.xunlei.downloadprovider.download.tasklist.list.a aVar2) {
        a aVar3 = new a(LayoutInflater.from(context).inflate(R.layout.layout_task_card_template_basic_vip_promotion_card, viewGroup, false));
        aVar3.a(aVar);
        aVar3.k = aVar2;
        aVar3.o = context;
        return aVar3;
    }

    static /* synthetic */ void a(a aVar) {
        if (aVar.q == null) {
            aVar.q = new j(aVar.o, "vip_renew");
        }
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if (aVar.n != null) {
            aVar.q.a(new StringBuilder("dateAndUser").append(aVar.n.d).toString(), format + aVar.n.d);
        }
    }
}
