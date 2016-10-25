package com.xunlei.downloadprovider.download.tasklist.list.d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.download.tasklist.list.b.d;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import com.xunlei.downloadprovider.download.util.k;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: KuainiaoTrialRemindViewHolder.java
public final class b extends d {
    private boolean A;
    private k B;
    private com.xunlei.downloadprovider.download.tasklist.list.a h;
    private Context k;
    private e l;
    private String m;
    private int n;
    private int o;
    private long p;
    private long q;
    private int r;
    private int s;
    private boolean t;
    private long u;
    private long v;
    private j w;
    private a x;
    private com.xunlei.downloadprovider.commonview.dialog.d y;
    private Handler z;

    // compiled from: KuainiaoTrialRemindViewHolder.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[b.a().length];
            try {
                a[b.a - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.b - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[b.c - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[b.d - 1] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[b.e - 1] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[b.f - 1] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    // compiled from: KuainiaoTrialRemindViewHolder.java
    private class a extends BroadcastReceiver {
        private a() {
        }

        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                b.a(b.this, context);
            }
        }
    }

    // compiled from: KuainiaoTrialRemindViewHolder.java
    enum b {
        ;

        public static int[] a() {
            return (int[]) g.clone();
        }

        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = 5;
            f = 6;
            g = new int[]{a, b, c, d, e, f};
        }
    }

    static /* synthetic */ void a(b bVar, long j) {
        if (j > 60) {
            if (j % 60 == 0) {
                bVar.r = ((int) j) / 60;
            } else {
                bVar.r = ((int) j) / 60;
                bVar.s = ((int) j) % 60;
            }
            bVar.t = false;
            return;
        }
        bVar.r = -1;
        bVar.s = (int) j;
        bVar.t = true;
    }

    static /* synthetic */ long v(b bVar) {
        long j = bVar.v;
        bVar.v = j - 1;
        return j;
    }

    static /* synthetic */ long z(b bVar) {
        long j = bVar.u;
        bVar.u = j - 1;
        return j;
    }

    private b(View view) {
        super(view);
        this.m = "hasNotTrial";
        this.n = 20;
        this.o = 100;
        this.p = 6;
        this.q = 12;
        this.u = this.p;
        this.v = this.q;
        this.x = new a();
        this.y = null;
        this.z = new Handler();
        LoginHelper.a();
        this.A = LoginHelper.c();
        this.B = new k(this, this.z);
        this.a.setImageResource(R.drawable.xunlei_vip_default);
        this.c.setText(BrothersApplication.a().getString(R.string.download_list_kuainiao_default_tip));
        this.d.setText(BrothersApplication.a().getString(R.string.download_list_kuainiao_detail_tip));
        this.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.f.setText(BrothersApplication.a().getString(R.string.download_list_kuainiao_free_trial));
        this.m = "hasNotTrial";
        view.setOnClickListener(new c(this));
        this.e.setOnClickListener(new d(this));
        this.f.setOnClickListener(new e(this));
    }

    public final void a(e eVar) {
        if (this.l == null) {
            this.l = eVar;
        }
        a(this.k.getString(R.string.download_list_kuainiao_has_not_trial_tip, new Object[]{Integer.valueOf(this.n), Integer.valueOf(this.o)}), false, this.k.getString(R.string.download_list_kuainiao_free_trial), b.a);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.k.registerReceiver(this.x, intentFilter);
    }

    private void a(String str, boolean z, String str2, int i) {
        this.c.setText(str);
        this.d.setVisibility(z ? 0 : SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.f.setText(str2);
        switch (AnonymousClass_1.a[i - 1]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                this.m = "hasNotTrial";
            case SimpleLog.LOG_LEVEL_DEBUG:
                this.m = "speedUp";
            case MqttConnectOptions.MQTT_VERSION_3_1:
                this.m = "inTrial";
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                this.m = "afterTrial";
            case SimpleLog.LOG_LEVEL_ERROR:
                this.m = "mobileNetException";
            case SimpleLog.LOG_LEVEL_FATAL:
                this.m = "netUnavailble";
            default:
                break;
        }
    }

    public static b a(Context context, ViewGroup viewGroup, com.xunlei.downloadprovider.download.a.a aVar, com.xunlei.downloadprovider.download.tasklist.list.a aVar2) {
        b bVar = new b(LayoutInflater.from(context).inflate(R.layout.layout_task_card_template_basic_kuainiao_trial_card, viewGroup, false));
        bVar.a(aVar);
        bVar.h = aVar2;
        bVar.k = context;
        return bVar;
    }

    static /* synthetic */ void c(b bVar) {
        bVar.h.a(bVar.l);
        bVar.l = null;
        bVar.h.h.i = false;
        bVar.h.notifyDataSetChanged();
    }

    static /* synthetic */ void d(b bVar) {
        if (bVar.w == null) {
            bVar.w = new j(bVar.k, "kuainiao_trial");
        }
        bVar.w.a(new StringBuilder("is_kuainiao_close").append(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).toString(), true);
    }

    static /* synthetic */ void a(b bVar, boolean z) {
        if (z) {
            XLToast.a(bVar.k, XLToastType.XLTOAST_TYPE_NONE, "\u5feb\u9e1f\u8bd5\u7528120S");
        } else {
            XLToast.a(bVar.k, XLToastType.XLTOAST_TYPE_NONE, "\u5feb\u9e1f\u8bd5\u752860S");
        }
        bVar.z.postDelayed(new j(bVar), 5000);
    }

    static /* synthetic */ void g(b bVar) {
        if (bVar.y != null) {
            bVar.y.dismiss();
            bVar.y = null;
        }
        bVar.y = new com.xunlei.downloadprovider.commonview.dialog.d(bVar.k);
        bVar.y.a(bVar.k.getString(R.string.download_list_kuainiao_login_tip));
        bVar.y.c(bVar.k.getString(com.xunlei.downloadprovidershare.R.string.cancel));
        bVar.y.d(bVar.k.getString(com.xunlei.downloadprovidershare.R.string.login));
        bVar.y.a(new g(bVar));
        bVar.y.b(new h(bVar));
        bVar.y.show();
    }

    static /* synthetic */ void a(b bVar, Context context) {
        if (!com.xunlei.xllib.a.b.a(context)) {
            bVar.a(bVar.k.getString(R.string.download_list_kuainiao_net_unavailable), false, bVar.k.getString(R.string.download_list_kuainiao_retry), b.f);
            bVar.f.setEnabled(false);
        } else if (com.xunlei.xllib.a.b.f(context)) {
            bVar.f.setEnabled(true);
            bVar.f.setBackgroundResource(R.drawable.task_card_button_orange);
        } else if (com.xunlei.xllib.a.b.e(context)) {
            bVar.a(bVar.k.getString(R.string.download_list_kuainiao_net_mobile), false, bVar.k.getString(R.string.download_list_kuainiao_retry), b.e);
            bVar.f.setEnabled(false);
        }
    }
}
