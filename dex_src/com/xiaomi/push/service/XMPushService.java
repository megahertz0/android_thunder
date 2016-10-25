package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import com.taobao.accs.data.Message;
import com.tencent.connect.common.Constants;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.XMPushService.g;
import com.xiaomi.smack.p;
import com.xiaomi.xmpush.thrift.ad;
import com.xiaomi.xmpush.thrift.o;
import com.xiaomi.xmpush.thrift.r;
import com.xiaomi.xmpush.thrift.s;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.android.agoo.message.MessageService;

public class XMPushService extends Service implements com.xiaomi.smack.d {
    public static int a;
    private static final int f;
    private com.xiaomi.smack.b b;
    private ae c;
    private d d;
    private long e;
    private com.xiaomi.smack.l g;
    private com.xiaomi.smack.a h;
    private b i;
    private PacketSync j;
    private e k;
    private com.xiaomi.smack.f l;

    public static abstract class g extends com.xiaomi.push.service.e.b {
        public g(int i) {
            super(i);
        }

        public abstract void a();

        public abstract String b();

        public void run() {
            if (!(this.a == 4 || this.a == 8)) {
                com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("JOB: ").append(b()).toString());
            }
            a();
        }
    }

    class a extends g {
        com.xiaomi.push.service.x.b b;

        public a(com.xiaomi.push.service.x.b bVar) {
            super(9);
            this.b = null;
            this.b = bVar;
        }

        public void a() {
            try {
                if (XMPushService.this.e()) {
                    com.xiaomi.push.service.x.b b = x.a().b(this.b.h, this.b.b);
                    if (b == null) {
                        com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("ignore bind because the channel ").append(this.b.h).append(" is removed ").toString());
                        return;
                    } else if (b.m == com.xiaomi.push.service.x.c.a) {
                        b.a(com.xiaomi.push.service.x.c.b, 0, 0, null, null);
                        XMPushService.this.h.a(b);
                        com.xiaomi.stats.g.a(XMPushService.this, b);
                        return;
                    } else {
                        com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("trying duplicate bind, ingore! ").append(b.m).toString());
                        return;
                    }
                }
                com.xiaomi.channel.commonutils.logger.b.d("trying bind while the connection is not created, quit!");
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                XMPushService.this.a((int) XZBDevice.Stop, e);
            }
        }

        public String b() {
            return new StringBuilder("bind the client. ").append(this.b.h).toString();
        }
    }

    static class b extends g {
        private final com.xiaomi.push.service.x.b b;

        public b(com.xiaomi.push.service.x.b bVar) {
            super(12);
            this.b = bVar;
        }

        public void a() {
            this.b.a(com.xiaomi.push.service.x.c.a, 1, R.styleable.Toolbar_navigationContentDescription, null, null);
        }

        public String b() {
            return new StringBuilder("bind time out. chid=").append(this.b.h).toString();
        }

        public boolean equals(Object obj) {
            return !(obj instanceof b) ? false : TextUtils.equals(((b) obj).b.h, this.b.h);
        }

        public int hashCode() {
            return this.b.h.hashCode();
        }
    }

    public class c extends g {
        c() {
            super(1);
        }

        public void a() {
            if (XMPushService.this.a()) {
                XMPushService.this.o();
            } else {
                com.xiaomi.channel.commonutils.logger.b.a("should not connect. quit the job.");
            }
        }

        public String b() {
            return "do reconnect..";
        }
    }

    class d extends BroadcastReceiver {
        d() {
        }

        public void onReceive(Context context, Intent intent) {
            XMPushService.this.k();
        }
    }

    public class e extends g {
        public int b;
        public Exception c;

        e(int i, Exception exception) {
            super(2);
            this.b = i;
            this.c = exception;
        }

        public void a() {
            XMPushService.this.a(this.b, this.c);
        }

        public String b() {
            return "disconnect the connection.";
        }
    }

    class f extends g {
        private Intent c;

        public f(Intent intent) {
            super(15);
            this.c = null;
            this.c = intent;
        }

        public void a() {
            XMPushService.this.a(this.c);
        }

        public String b() {
            return new StringBuilder("Handle intent action = ").append(this.c.getAction()).toString();
        }
    }

    class h extends g {
        public h() {
            super(5);
        }

        public void a() {
            XMPushService.this.k.b();
        }

        public String b() {
            return "ask the job queue to quit";
        }
    }

    public class i extends Binder {
    }

    class j extends g {
        private com.xiaomi.smack.packet.d c;

        public j(com.xiaomi.smack.packet.d dVar) {
            super(8);
            this.c = null;
            this.c = dVar;
        }

        public void a() {
            XMPushService.this.j.a(this.c);
        }

        public String b() {
            return "receive a message.";
        }
    }

    class k extends g {
        public k() {
            super(4);
        }

        public void a() {
            if (XMPushService.this.e()) {
                try {
                    com.xiaomi.stats.g.a();
                    XMPushService.this.h.n();
                } catch (Exception e) {
                    com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                    XMPushService.this.a((int) XZBDevice.Stop, e);
                }
            }
        }

        public String b() {
            return "send ping..";
        }
    }

    class l extends g {
        com.xiaomi.push.service.x.b b;

        public l(com.xiaomi.push.service.x.b bVar) {
            super(4);
            this.b = null;
            this.b = bVar;
        }

        public void a() {
            try {
                this.b.a(com.xiaomi.push.service.x.c.a, 1, R.styleable.Toolbar_titleMarginBottom, null, null);
                XMPushService.this.h.a(this.b.h, this.b.b);
                this.b.a(com.xiaomi.push.service.x.c.b, 1, R.styleable.Toolbar_titleMarginBottom, null, null);
                XMPushService.this.h.a(this.b);
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                XMPushService.this.a((int) XZBDevice.Stop, e);
            }
        }

        public String b() {
            return new StringBuilder("rebind the client. ").append(this.b.h).toString();
        }
    }

    class m extends g {
        m() {
            super(3);
        }

        public void a() {
            XMPushService.this.a((int) XZBDevice.Success, null);
            if (XMPushService.this.a()) {
                XMPushService.this.o();
            }
        }

        public String b() {
            return "reset the connection.";
        }
    }

    class n extends g {
        com.xiaomi.push.service.x.b b;
        int c;
        String d;
        String e;

        public n(com.xiaomi.push.service.x.b bVar, int i, String str, String str2) {
            super(9);
            this.b = null;
            this.b = bVar;
            this.c = i;
            this.d = str;
            this.e = str2;
        }

        public void a() {
            if (!(this.b.m == com.xiaomi.push.service.x.c.a || XMPushService.this.h == null)) {
                try {
                    XMPushService.this.h.a(this.b.h, this.b.b);
                } catch (Exception e) {
                    com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
                    XMPushService.this.a((int) XZBDevice.Stop, e);
                }
            }
            this.b.a(com.xiaomi.push.service.x.c.a, this.c, 0, this.e, this.d);
        }

        public String b() {
            return new StringBuilder("unbind the channel. ").append(this.b.h).toString();
        }
    }

    static {
        f = Process.myPid();
        HostManager.addReservedHost("app.chat.xiaomi.net", "app.chat.xiaomi.net");
        HostManager.addReservedHost("app.chat.xiaomi.net", "42.62.94.2:443");
        HostManager.addReservedHost("app.chat.xiaomi.net", "114.54.23.2");
        HostManager.addReservedHost("app.chat.xiaomi.net", "111.13.142.2");
        HostManager.addReservedHost("app.chat.xiaomi.net", "111.206.200.2");
        com.xiaomi.smack.l.a = true;
        a = 1;
    }

    public XMPushService() {
        this.e = 0;
        this.j = null;
        this.k = null;
        this.l = new ai(this);
    }

    @TargetApi(11)
    public static Notification a(Context context) {
        Intent intent = new Intent(context, XMPushService.class);
        if (VERSION.SDK_INT >= 11) {
            Builder builder = new Builder(context);
            builder.setSmallIcon(context.getApplicationInfo().icon);
            builder.setContentTitle("Push Service");
            builder.setContentText("Push Service");
            builder.setContentIntent(PendingIntent.getActivity(context, 0, intent, 0));
            return builder.getNotification();
        }
        Notification notification = new Notification();
        notification.setLatestEventInfo(context, "Push Service", "Push Service", PendingIntent.getService(context, 0, intent, 0));
        return notification;
    }

    public static com.xiaomi.smack.packet.c a(f fVar, Context context, o oVar) {
        try {
            com.xiaomi.smack.packet.c cVar = new com.xiaomi.smack.packet.c();
            cVar.l(Constants.VIA_SHARE_TYPE_TEXT);
            cVar.m("xiaomi.com");
            cVar.n(fVar.a);
            cVar.b(true);
            cVar.f("push");
            cVar.o(oVar.f);
            String str = fVar.a;
            oVar.g.b = str.substring(0, str.indexOf("@"));
            oVar.g.d = str.substring(str.indexOf("/") + 1);
            str = String.valueOf(com.xiaomi.channel.commonutils.string.a.a(ad.a(ad.a(fVar.c, cVar.k()), ad.a(oVar))));
            com.xiaomi.smack.packet.a aVar = new com.xiaomi.smack.packet.a("s", null, null, null);
            aVar.b(str);
            cVar.a(aVar);
            com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("try send mi push message. packagename:").append(oVar.f).append(" action:").append(oVar.a).toString());
            return cVar;
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return null;
        }
    }

    private com.xiaomi.smack.packet.c a(com.xiaomi.smack.packet.c cVar, String str) {
        byte[] a = ad.a(str, cVar.k());
        com.xiaomi.smack.packet.c cVar2 = new com.xiaomi.smack.packet.c();
        cVar2.n(cVar.n());
        cVar2.m(cVar.m());
        cVar2.k(cVar.k());
        cVar2.l(cVar.l());
        cVar2.b(true);
        String a2 = ad.a(a, com.xiaomi.smack.util.g.c(cVar.a()));
        com.xiaomi.smack.packet.a aVar = new com.xiaomi.smack.packet.a("s", null, null, null);
        aVar.b(a2);
        cVar2.a(aVar);
        return cVar2;
    }

    private com.xiaomi.smack.packet.d a(com.xiaomi.smack.packet.d dVar, String str, String str2, boolean z) {
        x a = x.a();
        List b = a.b(str);
        if (b.isEmpty()) {
            com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("open channel should be called first before sending a packet, pkg=").append(str).toString());
        } else {
            dVar.o(str);
            String l = dVar.l();
            if (TextUtils.isEmpty(l)) {
                l = (String) b.get(0);
                dVar.l(l);
            }
            com.xiaomi.push.service.x.b b2 = a.b(l, dVar.n());
            if (!e()) {
                com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("drop a packet as the channel is not connected, chid=").append(l).toString());
            } else if (b2 == null || b2.m != com.xiaomi.push.service.x.c.c) {
                com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("drop a packet as the channel is not opened, chid=").append(l).toString());
            } else if (TextUtils.equals(str2, b2.j)) {
                return ((dVar instanceof com.xiaomi.smack.packet.c) && z) ? a((com.xiaomi.smack.packet.c) dVar, b2.i) : dVar;
            } else {
                com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("invalid session. ").append(str2).toString());
            }
        }
        return null;
    }

    public static <T extends org.apache.thrift.b<T, ?>> o a(String str, String str2, T t, com.xiaomi.xmpush.thrift.a aVar) {
        byte[] a = ad.a(t);
        o oVar = new o();
        com.xiaomi.xmpush.thrift.j jVar = new com.xiaomi.xmpush.thrift.j();
        jVar.a = 5;
        jVar.b = "fakeid";
        oVar.a(jVar);
        oVar.a(ByteBuffer.wrap(a));
        oVar.a(aVar);
        oVar.c(true);
        oVar.b(str);
        oVar.a(false);
        oVar.a(str2);
        return oVar;
    }

    private String a(String str) {
        return "<iq id='0' chid='0' type='get'><ping>%1$s%2$s</ping></iq>";
    }

    private void a(Intent intent) {
        com.xiaomi.push.service.x.b bVar = null;
        boolean z = true;
        boolean z2 = false;
        x a = x.a();
        String stringExtra;
        if (z.d.equalsIgnoreCase(intent.getAction()) || z.j.equalsIgnoreCase(intent.getAction())) {
            stringExtra = intent.getStringExtra(z.q);
            if (TextUtils.isEmpty(intent.getStringExtra(z.u))) {
                com.xiaomi.channel.commonutils.logger.b.a("security is empty. ignore.");
            } else if (stringExtra != null) {
                boolean a2 = a(stringExtra, intent);
                com.xiaomi.push.service.x.b b = b(stringExtra, intent);
                if (!com.xiaomi.channel.commonutils.network.d.d(this)) {
                    this.i.a(this, b, false, XZBDevice.DOWNLOAD_LIST_RECYCLE, null);
                } else if (!e()) {
                    a(true);
                } else if (b.m == com.xiaomi.push.service.x.c.a) {
                    c(new a(b));
                } else if (a2) {
                    c(new l(b));
                } else if (b.m == com.xiaomi.push.service.x.c.b) {
                    com.xiaomi.channel.commonutils.logger.b.a(String.format("the client is binding. %1$s %2$s.", new Object[]{b.h, b.b}));
                } else if (b.m == com.xiaomi.push.service.x.c.c) {
                    this.i.a(this, b, true, 0, null);
                }
            } else {
                com.xiaomi.channel.commonutils.logger.b.d("channel id is empty, do nothing!");
            }
        } else if (z.i.equalsIgnoreCase(intent.getAction())) {
            stringExtra = intent.getStringExtra(z.y);
            stringExtra = intent.getStringExtra(z.q);
            Object stringExtra2 = intent.getStringExtra(z.p);
            com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("Service called closechannel chid = ").append(stringExtra).append(" userId = ").append(stringExtra2).toString());
            if (TextUtils.isEmpty(stringExtra)) {
                for (String stringExtra3 : a.b(stringExtra3)) {
                    a(stringExtra3, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
                }
            } else if (TextUtils.isEmpty(stringExtra2)) {
                a(stringExtra, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
            } else {
                a(stringExtra, stringExtra2, XZBDevice.DOWNLOAD_LIST_RECYCLE, null, null);
            }
        } else if (z.e.equalsIgnoreCase(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(z.y);
            stringExtra = intent.getStringExtra(z.B);
            Bundle bundleExtra = intent.getBundleExtra("ext_packet");
            com.xiaomi.smack.packet.d a3 = a(new com.xiaomi.smack.packet.c(bundleExtra), stringExtra3, stringExtra, intent.getBooleanExtra("ext_encrypt", true));
            if (a3 != null) {
                c(new af(this, a3));
            }
        } else if (z.g.equalsIgnoreCase(intent.getAction())) {
            stringExtra = intent.getStringExtra(z.y);
            stringExtra = intent.getStringExtra(z.B);
            Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("ext_packets");
            com.xiaomi.smack.packet.c[] cVarArr = new com.xiaomi.smack.packet.c[parcelableArrayExtra.length];
            boolean booleanExtra = intent.getBooleanExtra("ext_encrypt", true);
            while (r3 < parcelableArrayExtra.length) {
                cVarArr[r3] = new com.xiaomi.smack.packet.c((Bundle) parcelableArrayExtra[r3]);
                cVarArr[r3] = (com.xiaomi.smack.packet.c) a(cVarArr[r3], stringExtra, stringExtra, booleanExtra);
                if (cVarArr[r3] != null) {
                    r3++;
                } else {
                    return;
                }
            }
            c(new a(this, cVarArr));
        } else if (z.f.equalsIgnoreCase(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(z.y);
            stringExtra = intent.getStringExtra(z.B);
            bVar = new com.xiaomi.smack.packet.b(intent.getBundleExtra("ext_packet"));
            if (a(bVar, stringExtra3, stringExtra, false) != null) {
                c(new af(this, bVar));
            }
        } else if (z.h.equalsIgnoreCase(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(z.y);
            stringExtra = intent.getStringExtra(z.B);
            bVar = new com.xiaomi.smack.packet.f(intent.getBundleExtra("ext_packet"));
            if (a(bVar, stringExtra3, stringExtra, false) != null) {
                c(new af(this, bVar));
            }
        } else if (z.k.equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(z.q);
            stringExtra = intent.getStringExtra(z.p);
            if (stringExtra3 != null) {
                com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("request reset connection from chid = ").append(stringExtra3).toString());
                com.xiaomi.push.service.x.b b2 = x.a().b(stringExtra3, stringExtra);
                if (b2 != null && b2.i.equals(intent.getStringExtra(z.u)) && b2.m == com.xiaomi.push.service.x.c.c) {
                    com.xiaomi.smack.a g = g();
                    if (g == null || !g.a(System.currentTimeMillis() - 15000)) {
                        c(new m());
                    }
                }
            }
        } else if (z.l.equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(z.y);
            List b3 = a.b(stringExtra3);
            if (b3.isEmpty()) {
                com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("open channel should be called first before update info, pkg=").append(stringExtra3).toString());
                return;
            }
            stringExtra3 = intent.getStringExtra(z.q);
            Object stringExtra4 = intent.getStringExtra(z.p);
            if (TextUtils.isEmpty(stringExtra3)) {
                stringExtra3 = (String) b3.get(0);
            }
            if (TextUtils.isEmpty(stringExtra4)) {
                c = a.c(stringExtra3);
                if (!(c == null || c.isEmpty())) {
                    bVar = (com.xiaomi.push.service.x.b) c.iterator().next();
                }
            } else {
                bVar = a.b(stringExtra3, stringExtra4);
            }
            if (bVar != null) {
                if (intent.hasExtra(z.w)) {
                    bVar.f = intent.getStringExtra(z.w);
                }
                if (intent.hasExtra(z.x)) {
                    bVar.g = intent.getStringExtra(z.x);
                }
            }
        } else if ("com.xiaomi.mipush.REGISTER_APP".equals(intent.getAction())) {
            if (ab.a(getApplicationContext()).a() && ab.a(getApplicationContext()).b() == 0) {
                com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("register without being provisioned. ").append(intent.getStringExtra("mipush_app_package")).toString());
                return;
            }
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            String stringExtra5 = intent.getStringExtra("mipush_app_package");
            boolean booleanExtra2 = intent.getBooleanExtra("mipush_env_chanage", false);
            r3 = intent.getIntExtra("mipush_env_type", 1);
            h.a((Context) this).c(stringExtra5);
            if (!booleanExtra2 || "com.xiaomi.xmsf".equals(getPackageName())) {
                a(byteArrayExtra, stringExtra5);
            } else {
                c(new ao(this, 14, r3, byteArrayExtra, stringExtra5));
            }
        } else if ("com.xiaomi.mipush.SEND_MESSAGE".equals(intent.getAction()) || "com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
            stringExtra = intent.getStringExtra("mipush_app_package");
            byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
            z2 = intent.getBooleanExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
            c = x.a().c(Constants.VIA_SHARE_TYPE_TEXT);
            if ("com.xiaomi.mipush.UNREGISTER_APP".equals(intent.getAction())) {
                h.a((Context) this).b(stringExtra);
            }
            if (c.isEmpty()) {
                if (z2) {
                    j.b(stringExtra, byteArrayExtra2);
                }
            } else if (((com.xiaomi.push.service.x.b) c.iterator().next()).m == com.xiaomi.push.service.x.c.c) {
                c(new ap(this, 4, stringExtra, byteArrayExtra2));
            } else if (z2) {
                j.b(stringExtra, byteArrayExtra2);
            }
        } else if (ac.a.equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra("uninstall_pkg_name");
            if (stringExtra3 != null && !TextUtils.isEmpty(stringExtra3.trim())) {
                try {
                    getPackageManager().getPackageInfo(stringExtra3, Message.FLAG_REQ_BIT2);
                    z = false;
                } catch (NameNotFoundException e) {
                }
                if ("com.xiaomi.channel".equals(stringExtra3) && !x.a().c(MessageService.MSG_DB_NOTIFY_REACHED).isEmpty() && r9) {
                    a(MessageService.MSG_DB_NOTIFY_REACHED, 0);
                    com.xiaomi.channel.commonutils.logger.b.a("close the miliao channel as the app is uninstalled.");
                    return;
                }
                SharedPreferences sharedPreferences = getSharedPreferences("pref_registered_pkg_names", 0);
                stringExtra = sharedPreferences.getString(stringExtra3, null);
                if (!TextUtils.isEmpty(stringExtra) && r9) {
                    Editor edit = sharedPreferences.edit();
                    edit.remove(stringExtra3);
                    edit.commit();
                    if (r.e(this, stringExtra3)) {
                        r.d(this, stringExtra3);
                    }
                    r.b(this, stringExtra3);
                    if (e() && stringExtra != null) {
                        try {
                            a(a(stringExtra3, stringExtra));
                            com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("uninstall ").append(stringExtra3).append(" msg sent").toString());
                        } catch (Exception e2) {
                            com.xiaomi.channel.commonutils.logger.b.d(new StringBuilder("Fail to send Message: ").append(e2.getMessage()).toString());
                            a((int) XZBDevice.Stop, e2);
                        }
                    }
                }
            }
        } else if ("com.xiaomi.mipush.CLEAR_NOTIFICATION".equals(intent.getAction())) {
            stringExtra3 = intent.getStringExtra(z.y);
            intExtra = intent.getIntExtra(z.z, 0);
            if (!TextUtils.isEmpty(stringExtra3)) {
                if (intExtra >= 0) {
                    r.a((Context) this, stringExtra3, intExtra);
                } else if (intExtra == -1) {
                    r.b(this, stringExtra3);
                }
            }
        } else if ("com.xiaomi.mipush.SET_NOTIFICATION_TYPE".equals(intent.getAction())) {
            stringExtra = intent.getStringExtra(z.y);
            CharSequence stringExtra6 = intent.getStringExtra(z.C);
            CharSequence b4;
            if (intent.hasExtra(z.A)) {
                intExtra = intent.getIntExtra(z.A, 0);
                b4 = com.xiaomi.channel.commonutils.string.c.b(stringExtra + intExtra);
            } else {
                b4 = com.xiaomi.channel.commonutils.string.c.b(stringExtra);
                intExtra = 0;
                z2 = true;
            }
            if (TextUtils.isEmpty(stringExtra) || !TextUtils.equals(stringExtra6, r0)) {
                com.xiaomi.channel.commonutils.logger.b.d(new StringBuilder("invalid notification for ").append(stringExtra).toString());
            } else if (z2) {
                r.d(this, stringExtra);
            } else {
                r.b((Context) this, stringExtra, intExtra);
            }
        }
    }

    private void a(String str, int i) {
        Collection<com.xiaomi.push.service.x.b> c = x.a().c(str);
        if (c != null) {
            for (com.xiaomi.push.service.x.b bVar : c) {
                if (bVar != null) {
                    a(new n(bVar, i, null, null));
                }
            }
        }
        x.a().a(str);
    }

    private boolean a(String str, Intent intent) {
        com.xiaomi.push.service.x.b b = x.a().b(str, intent.getStringExtra(z.p));
        boolean z = false;
        if (b == null || str == null) {
            return false;
        }
        Object stringExtra = intent.getStringExtra(z.B);
        String stringExtra2 = intent.getStringExtra(z.u);
        if (!(TextUtils.isEmpty(b.j) || TextUtils.equals(stringExtra, b.j))) {
            com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("session changed. old session=").append(b.j).append(", new session=").append(stringExtra).append(" chid = ").append(str).toString());
            z = true;
        }
        if (stringExtra2.equals(b.i)) {
            return z;
        }
        com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("security changed. chid = ").append(str).append(" sechash = ").append(com.xiaomi.channel.commonutils.string.c.a(stringExtra2)).toString());
        return true;
    }

    private com.xiaomi.push.service.x.b b(String str, Intent intent) {
        com.xiaomi.push.service.x.b b = x.a().b(str, intent.getStringExtra(z.p));
        if (b == null) {
            b = new com.xiaomi.push.service.x.b(this);
        }
        b.h = intent.getStringExtra(z.q);
        b.b = intent.getStringExtra(z.p);
        b.c = intent.getStringExtra(z.s);
        b.a = intent.getStringExtra(z.y);
        b.f = intent.getStringExtra(z.w);
        b.g = intent.getStringExtra(z.x);
        b.e = intent.getBooleanExtra(z.v, false);
        b.i = intent.getStringExtra(z.u);
        b.j = intent.getStringExtra(z.B);
        b.d = intent.getStringExtra(z.t);
        b.k = this.i;
        b.l = getApplicationContext();
        x.a().a(b);
        return b;
    }

    private void c(g gVar) {
        this.k.a((com.xiaomi.push.service.e.b) gVar);
    }

    private void j() {
        if (g.a(getApplicationContext()) != null) {
            com.xiaomi.push.service.x.b a = g.a(getApplicationContext()).a(this);
            a(a);
            x.a().a(a);
            if (com.xiaomi.channel.commonutils.network.d.d(getApplicationContext())) {
                a(true);
            }
        }
    }

    private void k() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo != null) {
            com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("network changed, ").append(activeNetworkInfo.toString()).toString());
        } else {
            com.xiaomi.channel.commonutils.logger.b.a("network changed, no active network");
        }
        if (com.xiaomi.stats.e.b() != null) {
            com.xiaomi.stats.e.b().b();
        }
        this.g.r();
        if (com.xiaomi.channel.commonutils.network.d.d(this)) {
            if (e() && l()) {
                m();
            }
            if (!(e() || f())) {
                this.k.b(1);
                a(new c());
            }
            com.xiaomi.push.log.b.a((Context) this).a();
        } else {
            a(new e(2, null));
        }
        n();
    }

    private boolean l() {
        return System.currentTimeMillis() - this.e >= 30000;
    }

    private void m() {
        this.e = System.currentTimeMillis();
        if (this.k.d()) {
            com.xiaomi.channel.commonutils.logger.b.d("ERROR, the job controller is blocked.");
            x.a().a((Context) this, (int) XZBDevice.Predownload);
            stopSelf();
        } else if (!e()) {
            a(true);
        } else if (this.h.q() || com.xiaomi.channel.commonutils.network.d.e(this)) {
            a(new k());
        } else {
            a(new e(17, null));
            a(true);
        }
    }

    private void n() {
        if (!a()) {
            com.xiaomi.push.service.timers.a.a();
        } else if (!com.xiaomi.push.service.timers.a.b()) {
            com.xiaomi.push.service.timers.a.a(true);
        }
    }

    private void o() {
        if (this.h != null && this.h.h()) {
            com.xiaomi.channel.commonutils.logger.b.d("try to connect while connecting.");
        } else if (this.h == null || !this.h.i()) {
            this.b.b(com.xiaomi.channel.commonutils.network.d.f(this));
            p();
            if (this.h == null) {
                x.a().a((Context) this);
                sendBroadcast(new Intent("miui.intent.action.NETWORK_BLOCKED"));
                return;
            }
            sendBroadcast(new Intent("miui.intent.action.NETWORK_CONNECTED"));
        } else {
            com.xiaomi.channel.commonutils.logger.b.d("try to connect while is connected.");
        }
    }

    private void p() {
        try {
            this.g.a(this.l, new as(this));
            this.g.t();
            this.h = this.g;
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a("fail to create xmpp connection", (Throwable) e);
            this.g.a(new com.xiaomi.smack.packet.f(com.xiaomi.smack.packet.f.b.b), (int) XZBDevice.DOWNLOAD_LIST_FAILED, e);
        }
    }

    private void q() {
        if (VERSION.SDK_INT < 18) {
            startForeground(f, new Notification());
        } else {
            bindService(new Intent(this, XMJobService.class), new ak(this), 1);
        }
    }

    public com.xiaomi.smack.l a(com.xiaomi.smack.b bVar) {
        return new com.xiaomi.smack.l(this, bVar);
    }

    public com.xiaomi.smack.packet.c a(byte[] bArr) {
        o oVar = new o();
        try {
            ad.a((org.apache.thrift.b) oVar, bArr);
            return a(g.a(this), (Context) this, oVar);
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return null;
        }
    }

    public o a(String str, String str2) {
        org.apache.thrift.b rVar = new r();
        rVar.b(str2);
        rVar.c("app_uninstalled");
        rVar.a(com.xiaomi.smack.packet.d.j());
        rVar.a(false);
        return a(str, str2, rVar, com.xiaomi.xmpush.thrift.a.i);
    }

    public void a(int i) {
        this.k.b(i);
    }

    public void a(int i, Exception exception) {
        com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("disconnect ").append(hashCode()).append(", ").append(this.h == null ? null : Integer.valueOf(this.h.hashCode())).toString());
        if (this.h != null) {
            this.h.a(new com.xiaomi.smack.packet.f(com.xiaomi.smack.packet.f.b.b), i, exception);
            this.h = null;
        }
        a((int) R.styleable.Toolbar_contentInsetLeft);
        a((int) XZBDevice.DOWNLOAD_LIST_ALL);
        x.a().a((Context) this, i);
    }

    public void a(g gVar) {
        a(gVar, 0);
    }

    public void a(g gVar, long j) {
        this.k.a((com.xiaomi.push.service.e.b) gVar, j);
    }

    public void a(com.xiaomi.push.service.x.b bVar) {
        bVar.a(new aq(this));
    }

    public void a(com.xiaomi.smack.a aVar) {
        this.c.a();
        Iterator it = x.a().b().iterator();
        while (it.hasNext()) {
            a(new a((com.xiaomi.push.service.x.b) it.next()));
        }
    }

    public void a(com.xiaomi.smack.a aVar, int i, Exception exception) {
        a(false);
    }

    public void a(com.xiaomi.smack.a aVar, Exception exception) {
        a(false);
    }

    public void a(com.xiaomi.smack.packet.d dVar) {
        if (this.h != null) {
            this.h.a(dVar);
            return;
        }
        throw new p("try send msg while connection is null.");
    }

    public void a(o oVar) {
        if (this.h != null) {
            com.xiaomi.smack.packet.d a = a(g.a(this), (Context) this, oVar);
            if (a != null) {
                this.h.a(a);
                return;
            }
            return;
        }
        throw new p("try send msg while connection is null.");
    }

    public void a(String str, String str2, int i, String str3, String str4) {
        com.xiaomi.push.service.x.b b = x.a().b(str, str2);
        if (b != null) {
            a(new n(b, i, str4, str3));
        }
        x.a().a(str, str2);
    }

    public void a(String str, byte[] bArr) {
        if (this.h != null) {
            com.xiaomi.smack.packet.d a = a(bArr);
            if (a != null) {
                this.h.a(a);
                return;
            } else {
                j.a(this, str, bArr, 70000003, "not a valid message");
                return;
            }
        }
        throw new p("try send msg while connection is null.");
    }

    public void a(boolean z) {
        this.c.a(z);
    }

    public void a(byte[] bArr, String str) {
        if (bArr == null) {
            j.a(this, str, bArr, 70000003, "null payload");
            com.xiaomi.channel.commonutils.logger.b.a("register request without payload");
            return;
        }
        org.apache.thrift.b oVar = new o();
        try {
            ad.a(oVar, bArr);
            if (oVar.a == com.xiaomi.xmpush.thrift.a.a) {
                org.apache.thrift.b sVar = new s();
                try {
                    ad.a(sVar, oVar.f());
                    j.a(oVar.j(), bArr);
                    a(new i(this, oVar.j(), sVar.d(), sVar.h(), bArr));
                    return;
                } catch (Throwable e) {
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                    j.a(this, str, bArr, 70000003, " data action error.");
                }
            }
            j.a(this, str, bArr, 70000003, " registration action required.");
            com.xiaomi.channel.commonutils.logger.b.a("register request with invalid payload");
        } catch (Throwable e2) {
            com.xiaomi.channel.commonutils.logger.b.a(e2);
            j.a(this, str, bArr, 70000003, " data container error.");
        }
    }

    public void a(com.xiaomi.smack.packet.d[] dVarArr) {
        if (this.h != null) {
            this.h.a(dVarArr);
            return;
        }
        throw new p("try send msg while connection is null.");
    }

    public boolean a() {
        return com.xiaomi.channel.commonutils.network.d.d(this) && x.a().c() > 0 && !b();
    }

    public void b(g gVar) {
        this.k.a(gVar.a, (com.xiaomi.push.service.e.b) gVar);
    }

    public void b(com.xiaomi.push.service.x.b bVar) {
        if (bVar != null) {
            long a = bVar.a();
            com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("schedule rebind job in ").append(a / 1000).toString());
            a(new a(bVar), a);
        }
    }

    public void b(com.xiaomi.smack.a aVar) {
        com.xiaomi.channel.commonutils.logger.b.c("begin to connect...");
    }

    public boolean b() {
        try {
            Class forName = Class.forName("miui.os.Build");
            return forName.getField("IS_CM_CUSTOMIZATION_TEST").getBoolean(null) || forName.getField("IS_CU_CUSTOMIZATION_TEST").getBoolean(null);
        } catch (Throwable th) {
            return false;
        }
    }

    public boolean b(int i) {
        return this.k.a(i);
    }

    public b c() {
        return new b();
    }

    public b d() {
        return this.i;
    }

    public boolean e() {
        return this.h != null && this.h.i();
    }

    public boolean f() {
        return this.h != null && this.h.h();
    }

    public com.xiaomi.smack.a g() {
        return this.h;
    }

    public void h() {
        a(new aj(this, 10), 15000);
    }

    public IBinder onBind(Intent intent) {
        return new i();
    }

    public void onCreate() {
        super.onCreate();
        com.xiaomi.smack.util.h.a(this);
        f a = g.a(this);
        if (a != null) {
            com.xiaomi.channel.commonutils.misc.a.a(a.g);
        }
        aa.a(this);
        this.b = new al(this, null, 5222, "xiaomi.com", null);
        this.b.a(true);
        this.g = a(this.b);
        this.g.b(a("xiaomi.com"));
        Fallback fallback = new Fallback("mibind.chat.gslb.mi-idc.com");
        this.i = c();
        try {
            if (TextUtils.equals((String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class}).invoke(null, new Object[]{"sys.boot_completed"}), MessageService.MSG_DB_NOTIFY_REACHED)) {
                this.i.a((Context) this);
            }
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
        com.xiaomi.push.service.timers.a.a((Context) this);
        this.g.a(this);
        this.j = new PacketSync(this);
        this.c = new ae(this);
        new c().a();
        this.k = new e("Connection Controller Thread");
        a(new am(this, 11));
        x a2 = x.a();
        a2.e();
        a2.a(new an(this));
        this.d = new d();
        registerReceiver(this.d, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        if (!TextUtils.equals(getPackageName(), "com.xiaomi.xmsf")) {
            q();
        }
        com.xiaomi.channel.commonutils.logger.b.a(new StringBuilder("XMPushService created pid = ").append(f).toString());
    }

    public void onDestroy() {
        unregisterReceiver(this.d);
        this.k.c();
        a(new ar(this, 2));
        a(new h());
        x.a().e();
        x.a().a((Context) this, (int) XZBDevice.Delete);
        x.a().d();
        this.g.b(this);
        ag.a().b();
        com.xiaomi.push.service.timers.a.a();
        super.onDestroy();
        com.xiaomi.channel.commonutils.logger.b.a("Service destroyed");
    }

    public void onStart(Intent intent, int i) {
        if (intent == null) {
            com.xiaomi.channel.commonutils.logger.b.d("onStart() with intent NULL");
        } else {
            com.xiaomi.channel.commonutils.logger.b.c(String.format("onStart() with intent.Action = %s, chid = %s", new Object[]{intent.getAction(), intent.getStringExtra(z.q)}));
        }
        if (intent != null && intent.getAction() != null) {
            if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction())) {
                com.xiaomi.channel.commonutils.logger.b.a("Service called on timer");
                m();
            } else if ("com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
                com.xiaomi.channel.commonutils.logger.b.a("Service called on check alive.");
                if (l()) {
                    m();
                }
            } else if (!"com.xiaomi.push.network_status_changed".equalsIgnoreCase(intent.getAction())) {
                a(new f(intent));
            }
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        onStart(intent, i2);
        return a;
    }
}
