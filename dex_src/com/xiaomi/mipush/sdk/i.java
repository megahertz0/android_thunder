package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.string.d;
import com.xiaomi.push.service.r;
import com.xiaomi.push.service.v;
import com.xiaomi.push.service.z;
import com.xiaomi.xmpush.thrift.a;
import com.xiaomi.xmpush.thrift.aa;
import com.xiaomi.xmpush.thrift.ac;
import com.xiaomi.xmpush.thrift.ad;
import com.xiaomi.xmpush.thrift.f;
import com.xiaomi.xmpush.thrift.h;
import com.xiaomi.xmpush.thrift.k;
import com.xiaomi.xmpush.thrift.n;
import com.xiaomi.xmpush.thrift.o;
import com.xiaomi.xmpush.thrift.p;
import com.xiaomi.xmpush.thrift.q;
import com.xiaomi.xmpush.thrift.t;
import com.xiaomi.xmpush.thrift.w;
import com.xiaomi.xmpush.thrift.y;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;
import org.android.spdy.SpdyAgent;

public class i {
    private static i a;
    private static Queue<String> c;
    private static Object d;
    private Context b;

    static {
        a = null;
        d = new Object();
    }

    private i(Context context) {
        this.b = context.getApplicationContext();
        if (this.b == null) {
            this.b = context;
        }
    }

    public static Intent a(Context context, String str, Map<String, String> map) {
        URISyntaxException uRISyntaxException;
        MalformedURLException e;
        if (map == null || !map.containsKey("notify_effect")) {
            return null;
        }
        Intent launchIntentForPackage;
        String str2 = (String) map.get("notify_effect");
        if (z.a.equals(str2)) {
            try {
                launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            } catch (Exception e2) {
                b.d(new StringBuilder("Cause: ").append(e2.getMessage()).toString());
                launchIntentForPackage = null;
            }
        } else {
            Intent parseUri;
            if (z.b.equals(str2)) {
                if (map.containsKey("intent_uri")) {
                    str2 = (String) map.get("intent_uri");
                    if (str2 != null) {
                        try {
                            parseUri = Intent.parseUri(str2, 1);
                            try {
                                parseUri.setPackage(str);
                                launchIntentForPackage = parseUri;
                            } catch (URISyntaxException e3) {
                                URISyntaxException uRISyntaxException2 = e3;
                                launchIntentForPackage = parseUri;
                                uRISyntaxException = uRISyntaxException2;
                                b.d(new StringBuilder("Cause: ").append(uRISyntaxException.getMessage()).toString());
                                if (launchIntentForPackage == null) {
                                    return null;
                                }
                                launchIntentForPackage.addFlags(268435456);
                                return context.getPackageManager().resolveActivity(launchIntentForPackage, AccessibilityNodeInfoCompat.ACTION_CUT) != null ? null : launchIntentForPackage;
                            }
                        } catch (URISyntaxException e32) {
                            uRISyntaxException = e32;
                            launchIntentForPackage = null;
                            b.d(new StringBuilder("Cause: ").append(uRISyntaxException.getMessage()).toString());
                            if (launchIntentForPackage == null) {
                                return null;
                            }
                            launchIntentForPackage.addFlags(268435456);
                            if (context.getPackageManager().resolveActivity(launchIntentForPackage, AccessibilityNodeInfoCompat.ACTION_CUT) != null) {
                            }
                        }
                    }
                    launchIntentForPackage = null;
                } else if (map.containsKey("class_name")) {
                    str2 = (String) map.get("class_name");
                    parseUri = new Intent();
                    parseUri.setComponent(new ComponentName(str, str2));
                    try {
                        if (map.containsKey("intent_flag")) {
                            parseUri.setFlags(Integer.parseInt((String) map.get("intent_flag")));
                        }
                        launchIntentForPackage = parseUri;
                    } catch (NumberFormatException e4) {
                        b.d(new StringBuilder("Cause by intent_flag: ").append(e4.getMessage()).toString());
                        launchIntentForPackage = parseUri;
                    }
                }
            } else if (z.c.equals(str2)) {
                str2 = (String) map.get("web_uri");
                if (str2 != null) {
                    str2 = str2.trim();
                    String toString = (str2.startsWith("http://") || str2.startsWith("https://")) ? str2 : new StringBuilder("http://").append(str2).toString();
                    try {
                        str2 = new URL(toString).getProtocol();
                    } catch (MalformedURLException e5) {
                        e = e5;
                        parseUri = null;
                        b.d(new StringBuilder("Cause: ").append(e.getMessage()).toString());
                        launchIntentForPackage = parseUri;
                        if (launchIntentForPackage == null) {
                            return null;
                        }
                        launchIntentForPackage.addFlags(268435456);
                        if (context.getPackageManager().resolveActivity(launchIntentForPackage, AccessibilityNodeInfoCompat.ACTION_CUT) != null) {
                        }
                    }
                    if (HttpConstant.HTTP.equals(str2) || com.alipay.sdk.cons.b.a.equals(str2)) {
                        launchIntentForPackage = new Intent("android.intent.action.VIEW");
                        try {
                            launchIntentForPackage.setData(Uri.parse(toString));
                        } catch (MalformedURLException e6) {
                            MalformedURLException malformedURLException = e6;
                            parseUri = launchIntentForPackage;
                            e = malformedURLException;
                            b.d(new StringBuilder("Cause: ").append(e.getMessage()).toString());
                            launchIntentForPackage = parseUri;
                            if (launchIntentForPackage == null) {
                                return null;
                            }
                            launchIntentForPackage.addFlags(268435456);
                            if (context.getPackageManager().resolveActivity(launchIntentForPackage, AccessibilityNodeInfoCompat.ACTION_CUT) != null) {
                            }
                        }
                    }
                    launchIntentForPackage = null;
                }
            }
            launchIntentForPackage = null;
        }
        if (launchIntentForPackage == null) {
            return null;
        }
        launchIntentForPackage.addFlags(268435456);
        try {
            if (context.getPackageManager().resolveActivity(launchIntentForPackage, AccessibilityNodeInfoCompat.ACTION_CUT) != null) {
            }
        } catch (Exception e22) {
            b.d(new StringBuilder("Cause: ").append(e22.getMessage()).toString());
            return null;
        }
    }

    private a a(o oVar, boolean z, byte[] bArr) {
        a aVar = null;
        try {
            org.apache.thrift.b a = h.a(this.b, oVar);
            if (a == null) {
                b.d(new StringBuilder("receiving an un-recognized message. ").append(oVar.a).toString());
                return null;
            }
            b.c(new StringBuilder("receive a message.").append(a).toString());
            a a2 = oVar.a();
            b.a(new StringBuilder("processing a message, action=").append(a2).toString());
            List list;
            switch (AnonymousClass_1.a[a2.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (!a.a(this.b).l() || z) {
                        w wVar = (w) a;
                        h l = wVar.l();
                        if (l == null) {
                            b.d("receive an empty message without push content, drop it");
                            return null;
                        }
                        CharSequence charSequence;
                        String b;
                        if (z) {
                            if (r.b(oVar)) {
                                MiPushClient.reportIgnoreRegMessageClicked(this.b, l.b(), oVar.m(), oVar.f, l.d());
                            } else {
                                MiPushClient.reportMessageClicked(this.b, l.b(), oVar.m(), l.d());
                            }
                        }
                        if (!z) {
                            if (!TextUtils.isEmpty(wVar.j()) && MiPushClient.aliasSetTime(this.b, wVar.j()) < 0) {
                                MiPushClient.addAlias(this.b, wVar.j());
                            } else if (!TextUtils.isEmpty(wVar.h()) && MiPushClient.topicSubscribedTime(this.b, wVar.h()) < 0) {
                                MiPushClient.addTopic(this.b, wVar.h());
                            }
                        }
                        if (oVar.h == null || oVar.h.s() == null) {
                            charSequence = null;
                        } else {
                            String str = (String) oVar.h.j.get("jobkey");
                        }
                        if (TextUtils.isEmpty(charSequence)) {
                            b = l.b();
                        } else {
                            CharSequence charSequence2 = charSequence;
                        }
                        if (z || !a(this.b, b)) {
                            Serializable generateMessage = PushMessageHelper.generateMessage(wVar, oVar.m(), z);
                            if (generateMessage.getPassThrough() == 0 && !z && r.a(generateMessage.getExtra())) {
                                r.a(this.b, oVar, bArr);
                                return null;
                            }
                            b.a(new StringBuilder("receive a message, msgid=").append(l.b()).append(", jobkey=").append(b).toString());
                            if (z && generateMessage.getExtra() != null && generateMessage.getExtra().containsKey("notify_effect")) {
                                Map extra = generateMessage.getExtra();
                                String str2 = (String) extra.get("notify_effect");
                                if (r.b(oVar)) {
                                    Intent a3 = a(this.b, oVar.f, extra);
                                    if (a3 == null) {
                                        b.a("Getting Intent fail from ignore reg message. ");
                                        return null;
                                    }
                                    Object f = l.f();
                                    if (!TextUtils.isEmpty(f)) {
                                        a3.putExtra("payload", f);
                                    }
                                    this.b.startActivity(a3);
                                    return null;
                                }
                                Intent a4 = a(this.b, this.b.getPackageName(), extra);
                                if (a4 == null) {
                                    return null;
                                }
                                if (!str2.equals(z.c)) {
                                    a4.putExtra(PushMessageHelper.KEY_MESSAGE, generateMessage);
                                }
                                this.b.startActivity(a4);
                                return null;
                            }
                            Serializable serializable = generateMessage;
                        } else {
                            b.a(new StringBuilder("drop a duplicate message, key=").append(b).toString());
                        }
                        if (oVar.m() != null || z) {
                            return aVar;
                        }
                        a(wVar, oVar);
                        return aVar;
                    }
                    b.a("receive a message in pause state. drop it");
                    return null;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    t tVar = (t) a;
                    if (tVar.f == 0) {
                        a.a(this.b).b(tVar.h, tVar.i);
                    }
                    if (TextUtils.isEmpty(tVar.h)) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(tVar.h);
                    }
                    aVar = PushMessageHelper.generateCommandMessage(MiPushClient.COMMAND_REGISTER, list, tVar.f, tVar.g, null);
                    j.a(this.b).d();
                    return aVar;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    if (((aa) a).f == 0) {
                        a.a(this.b).h();
                        MiPushClient.clearExtras(this.b);
                    }
                    PushMessageHandler.a();
                    return null;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    y yVar = (y) a;
                    if (yVar.f == 0) {
                        MiPushClient.addTopic(this.b, yVar.h());
                    }
                    if (TextUtils.isEmpty(yVar.h())) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(yVar.h());
                    }
                    return PushMessageHelper.generateCommandMessage(MiPushClient.COMMAND_SUBSCRIBE_TOPIC, list, yVar.f, yVar.g, yVar.k());
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    ac acVar = (ac) a;
                    if (acVar.f == 0) {
                        MiPushClient.removeTopic(this.b, acVar.h());
                    }
                    if (TextUtils.isEmpty(acVar.h())) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(acVar.h());
                    }
                    return PushMessageHelper.generateCommandMessage(MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC, list, acVar.f, acVar.g, acVar.k());
                case R.styleable.Toolbar_contentInsetEnd:
                    n nVar = (n) a;
                    Object e = nVar.e();
                    List k = nVar.k();
                    if (nVar.g == 0) {
                        if (TextUtils.equals(e, MiPushClient.COMMAND_SET_ACCEPT_TIME) && k != null && k.size() > 1) {
                            MiPushClient.addAcceptTime(this.b, (String) k.get(0), (String) k.get(1));
                            if ("00:00".equals(k.get(0)) && "00:00".equals(k.get(1))) {
                                a.a(this.b).a(true);
                            } else {
                                a.a(this.b).a(false);
                            }
                            list = a(TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault(), k);
                            return PushMessageHelper.generateCommandMessage(e, list, nVar.g, nVar.h, nVar.m());
                        } else if (TextUtils.equals(e, MiPushClient.COMMAND_SET_ALIAS) && k != null && k.size() > 0) {
                            MiPushClient.addAlias(this.b, (String) k.get(0));
                            list = k;
                            return PushMessageHelper.generateCommandMessage(e, list, nVar.g, nVar.h, nVar.m());
                        } else if (TextUtils.equals(e, MiPushClient.COMMAND_UNSET_ALIAS) && k != null && k.size() > 0) {
                            MiPushClient.removeAlias(this.b, (String) k.get(0));
                            list = k;
                            return PushMessageHelper.generateCommandMessage(e, list, nVar.g, nVar.h, nVar.m());
                        } else if (TextUtils.equals(e, MiPushClient.COMMAND_SET_ACCOUNT) && k != null && k.size() > 0) {
                            MiPushClient.addAccount(this.b, (String) k.get(0));
                            list = k;
                            return PushMessageHelper.generateCommandMessage(e, list, nVar.g, nVar.h, nVar.m());
                        } else if (TextUtils.equals(e, MiPushClient.COMMAND_UNSET_ACCOUNT) && k != null && k.size() > 0) {
                            MiPushClient.removeAccount(this.b, (String) k.get(0));
                        }
                    }
                    list = k;
                    return PushMessageHelper.generateCommandMessage(e, list, nVar.g, nVar.h, nVar.m());
                case R.styleable.Toolbar_contentInsetLeft:
                    com.xiaomi.xmpush.thrift.r rVar = (com.xiaomi.xmpush.thrift.r) a;
                    if ("registration id expired".equalsIgnoreCase(rVar.e)) {
                        MiPushClient.reInitialize(this.b);
                        return null;
                    } else if ("client_info_update_ok".equalsIgnoreCase(rVar.e)) {
                        if (rVar.h() == null || !rVar.h().containsKey(org.android.agoo.common.b.PROPERTY_APP_VERSION)) {
                            return null;
                        }
                        a.a(this.b).a((String) rVar.h().get(org.android.agoo.common.b.PROPERTY_APP_VERSION));
                        return null;
                    } else if ("awake_app".equalsIgnoreCase(rVar.e)) {
                        if (rVar.h() == null || !rVar.h().containsKey("packages")) {
                            return null;
                        }
                        MiPushClient.awakeApps(this.b, ((String) rVar.h().get("packages")).split(MiPushClient.ACCEPT_TIME_SEPARATOR));
                        return null;
                    } else if (f.l.p.equalsIgnoreCase(rVar.e)) {
                        qVar = new q();
                        try {
                            ad.a(qVar, rVar.l());
                            com.xiaomi.push.service.w.a(v.a(this.b), qVar);
                            return null;
                        } catch (Throwable e2) {
                            b.a(e2);
                            return null;
                        }
                    } else if (!f.m.p.equalsIgnoreCase(rVar.e)) {
                        return null;
                    } else {
                        qVar = new p();
                        try {
                            ad.a(qVar, rVar.l());
                            com.xiaomi.push.service.w.a(v.a(this.b), qVar);
                            return null;
                        } catch (Throwable e22) {
                            b.a(e22);
                            return null;
                        }
                    }
                default:
                    return null;
            }
        } catch (Throwable e222) {
            b.a(e222);
            b.d("receive a message which action string is not valid. is the reg expired?");
            return null;
        }
    }

    private a a(o oVar, byte[] bArr) {
        String str = null;
        try {
            org.apache.thrift.b a = h.a(this.b, oVar);
            if (a == null) {
                b.d(new StringBuilder("message arrived: receiving an un-recognized message. ").append(oVar.a).toString());
                return null;
            }
            b.c(new StringBuilder("message arrived: receive a message.").append(a).toString());
            a a2 = oVar.a();
            b.a(new StringBuilder("message arrived: processing an arrived message, action=").append(a2).toString());
            switch (AnonymousClass_1.a[a2.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    w wVar = (w) a;
                    h l = wVar.l();
                    if (l == null) {
                        b.d("message arrived: receive an empty message without push content, drop it");
                        return null;
                    }
                    if (!(oVar.h == null || oVar.h.s() == null)) {
                        str = (String) oVar.h.j.get("jobkey");
                    }
                    MiPushMessage generateMessage = PushMessageHelper.generateMessage(wVar, oVar.m(), false);
                    generateMessage.setArrivedMessage(true);
                    b.a(new StringBuilder("message arrived: receive a message, msgid=").append(l.b()).append(", jobkey=").append(str).toString());
                    return generateMessage;
                default:
                    return null;
            }
        } catch (Throwable e) {
            b.a(e);
            b.d("message arrived: receive a message which action string is not valid. is the reg expired?");
            return null;
        }
    }

    public static i a(Context context) {
        if (a == null) {
            a = new i(context);
        }
        return a;
    }

    private void a(o oVar) {
        com.xiaomi.xmpush.thrift.i m = oVar.m();
        k kVar = new k();
        kVar.b(oVar.h());
        kVar.a(m.b());
        kVar.a(m.d());
        if (!TextUtils.isEmpty(m.f())) {
            kVar.c(m.f());
        }
        kVar.a(ad.a(this.b, oVar.f));
        j.a(this.b).a(kVar, a.f, false, oVar.m());
    }

    private void a(w wVar, o oVar) {
        com.xiaomi.xmpush.thrift.i m = oVar.m();
        org.apache.thrift.b kVar = new k();
        kVar.b(wVar.e());
        kVar.a(wVar.c());
        kVar.a(wVar.l().h());
        if (!TextUtils.isEmpty(wVar.h())) {
            kVar.c(wVar.h());
        }
        if (!TextUtils.isEmpty(wVar.j())) {
            kVar.d(wVar.j());
        }
        kVar.a(ad.a(this.b, oVar.f));
        j.a(this.b).a(kVar, a.f, m);
    }

    private static boolean a(Context context, String str) {
        boolean z = false;
        synchronized (d) {
            SharedPreferences j = a.a(context).j();
            if (c == null) {
                String[] split = j.getString("pref_msg_ids", com.umeng.a.d).split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                c = new LinkedList();
                int length = split.length;
                for (int i = 0; i < length; i++) {
                    c.add(split[i]);
                }
            }
            if (c.contains(str)) {
                z = true;
            } else {
                c.add(str);
                if (c.size() > 25) {
                    c.poll();
                }
                String a = d.a(c, MiPushClient.ACCEPT_TIME_SEPARATOR);
                Editor edit = j.edit();
                edit.putString("pref_msg_ids", a);
                edit.commit();
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.mipush.sdk.PushMessageHandler.a a(android.content.Intent r11) {
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.i.a(android.content.Intent):com.xiaomi.mipush.sdk.PushMessageHandler$a");
        /*
        this = this;
        r1 = 0;
        r6 = 0;
        r2 = r11.getAction();
        r0 = new java.lang.StringBuilder;
        r3 = "receive an intent from server, action=";
        r0.<init>(r3);
        r0 = r0.append(r2);
        r0 = r0.toString();
        com.xiaomi.channel.commonutils.logger.b.a(r0);
        r0 = "mrt";
        r0 = r11.getStringExtra(r0);
        if (r0 != 0) goto L_0x002a;
    L_0x0022:
        r4 = java.lang.System.currentTimeMillis();
        r0 = java.lang.Long.toString(r4);
    L_0x002a:
        r3 = "com.xiaomi.mipush.RECEIVE_MESSAGE";
        r3 = r3.equals(r2);
        if (r3 == 0) goto L_0x014f;
    L_0x0033:
        r2 = "mipush_payload";
        r2 = r11.getByteArrayExtra(r2);
        r3 = "mipush_notified";
        r3 = r11.getBooleanExtra(r3, r6);
        if (r2 != 0) goto L_0x004b;
    L_0x0043:
        r0 = "receiving an empty message, drop";
        com.xiaomi.channel.commonutils.logger.b.d(r0);
        r0 = r1;
    L_0x004a:
        return r0;
    L_0x004b:
        r4 = new com.xiaomi.xmpush.thrift.o;
        r4.<init>();
        com.xiaomi.xmpush.thrift.ad.a(r4, r2);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r5 = r10.b;	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r5 = com.xiaomi.mipush.sdk.a.a(r5);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r6 = r4.m();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r7 = r4.a();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r8 = com.xiaomi.xmpush.thrift.a.e;	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        if (r7 != r8) goto L_0x0090;
    L_0x0065:
        if (r6 == 0) goto L_0x0090;
    L_0x0067:
        r7 = r5.l();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        if (r7 != 0) goto L_0x0090;
    L_0x006d:
        if (r3 != 0) goto L_0x0090;
    L_0x006f:
        if (r6 == 0) goto L_0x008d;
    L_0x0071:
        r7 = r4.m();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r8 = "mrt";
        r7.a(r8, r0);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r0 = r4.m();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r7 = "mat";
        r8 = java.lang.System.currentTimeMillis();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r8 = java.lang.Long.toString(r8);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r0.a(r7, r8);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
    L_0x008d:
        r10.a(r4);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
    L_0x0090:
        r0 = r4.a();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r7 = com.xiaomi.xmpush.thrift.a.e;	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        if (r0 != r7) goto L_0x00fa;
    L_0x0098:
        r0 = r4.c();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        if (r0 != 0) goto L_0x00fa;
    L_0x009e:
        r0 = com.xiaomi.push.service.r.b(r4);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        if (r0 != 0) goto L_0x00c7;
    L_0x00a4:
        r2 = "drop an un-encrypted messages. %1$s, %2$s";
        r0 = 2;
        r3 = new java.lang.Object[r0];	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r0 = 0;
        r4 = r4.j();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r3[r0] = r4;	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r4 = 1;
        if (r6 == 0) goto L_0x00c3;
    L_0x00b4:
        r0 = r6.b();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
    L_0x00b8:
        r3[r4] = r0;	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r0 = java.lang.String.format(r2, r3);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        com.xiaomi.channel.commonutils.logger.b.a(r0);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r0 = r1;
        goto L_0x004a;
    L_0x00c3:
        r0 = "";
        goto L_0x00b8;
    L_0x00c7:
        if (r3 == 0) goto L_0x00dc;
    L_0x00c9:
        r0 = r6.s();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        if (r0 == 0) goto L_0x00dc;
    L_0x00cf:
        r0 = r6.s();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r7 = "notify_effect";
        r0 = r0.containsKey(r7);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        if (r0 != 0) goto L_0x00fa;
    L_0x00dc:
        r0 = "drop an un-encrypted messages. %1$s, %2$s";
        r2 = 2;
        r2 = new java.lang.Object[r2];	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r3 = 0;
        r4 = r4.j();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r2[r3] = r4;	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r3 = 1;
        r4 = r6.b();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r2[r3] = r4;	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r0 = java.lang.String.format(r0, r2);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        com.xiaomi.channel.commonutils.logger.b.a(r0);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r0 = r1;
        goto L_0x004a;
    L_0x00fa:
        r0 = r5.i();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        if (r0 != 0) goto L_0x011b;
    L_0x0100:
        r0 = r4.a;	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r6 = com.xiaomi.xmpush.thrift.a.a;	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        if (r0 == r6) goto L_0x011b;
    L_0x0106:
        r0 = com.xiaomi.push.service.r.b(r4);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        if (r0 == 0) goto L_0x0112;
    L_0x010c:
        r0 = r10.a(r4, r3, r2);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        goto L_0x004a;
    L_0x0112:
        r0 = "receive message without registration. need unregister or re-register!";
        com.xiaomi.channel.commonutils.logger.b.d(r0);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
    L_0x0118:
        r0 = r1;
        goto L_0x004a;
    L_0x011b:
        r0 = r5.i();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        if (r0 == 0) goto L_0x0149;
    L_0x0121:
        r0 = r5.n();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        if (r0 == 0) goto L_0x0149;
    L_0x0127:
        r0 = r4.a;	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r2 = com.xiaomi.xmpush.thrift.a.b;	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        if (r0 != r2) goto L_0x013e;
    L_0x012d:
        r5.h();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        r0 = r10.b;	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        com.xiaomi.mipush.sdk.MiPushClient.clearExtras(r0);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        com.xiaomi.mipush.sdk.PushMessageHandler.a();	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        goto L_0x0118;
    L_0x0139:
        r0 = move-exception;
        com.xiaomi.channel.commonutils.logger.b.a(r0);
        goto L_0x0118;
    L_0x013e:
        r0 = r10.b;	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        com.xiaomi.mipush.sdk.MiPushClient.unregisterPush(r0);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        goto L_0x0118;
    L_0x0144:
        r0 = move-exception;
        com.xiaomi.channel.commonutils.logger.b.a(r0);
        goto L_0x0118;
    L_0x0149:
        r0 = r10.a(r4, r3, r2);	 Catch:{ f -> 0x0139, Exception -> 0x0144 }
        goto L_0x004a;
    L_0x014f:
        r0 = "com.xiaomi.mipush.ERROR";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x01bc;
    L_0x0158:
        r0 = new com.xiaomi.mipush.sdk.MiPushCommandMessage;
        r0.<init>();
        r1 = new com.xiaomi.xmpush.thrift.o;
        r1.<init>();
        r2 = "mipush_payload";
        r2 = r11.getByteArrayExtra(r2);	 Catch:{ f -> 0x0227 }
        if (r2 == 0) goto L_0x016e;
    L_0x016b:
        com.xiaomi.xmpush.thrift.ad.a(r1, r2);	 Catch:{ f -> 0x0227 }
    L_0x016e:
        r1 = r1.a();
        r1 = java.lang.String.valueOf(r1);
        r0.setCommand(r1);
        r1 = "mipush_error_code";
        r1 = r11.getIntExtra(r1, r6);
        r2 = (long) r1;
        r0.setResultCode(r2);
        r1 = "mipush_error_msg";
        r1 = r11.getStringExtra(r1);
        r0.setReason(r1);
        r1 = new java.lang.StringBuilder;
        r2 = "receive a error message. code = ";
        r1.<init>(r2);
        r2 = "mipush_error_code";
        r2 = r11.getIntExtra(r2, r6);
        r1 = r1.append(r2);
        r2 = ", msg= ";
        r1 = r1.append(r2);
        r2 = "mipush_error_msg";
        r2 = r11.getStringExtra(r2);
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.xiaomi.channel.commonutils.logger.b.d(r1);
        goto L_0x004a;
    L_0x01bc:
        r0 = "com.xiaomi.mipush.MESSAGE_ARRIVED";
        r0 = r0.equals(r2);
        if (r0 == 0) goto L_0x0118;
    L_0x01c5:
        r0 = "mipush_payload";
        r0 = r11.getByteArrayExtra(r0);
        if (r0 != 0) goto L_0x01d7;
    L_0x01ce:
        r0 = "message arrived: receiving an empty message, drop";
        com.xiaomi.channel.commonutils.logger.b.d(r0);
        r0 = r1;
        goto L_0x004a;
    L_0x01d7:
        r2 = new com.xiaomi.xmpush.thrift.o;
        r2.<init>();
        com.xiaomi.xmpush.thrift.ad.a(r2, r0);	 Catch:{ f -> 0x01f3, Exception -> 0x0207 }
        r3 = r10.b;	 Catch:{ f -> 0x01f3, Exception -> 0x0207 }
        r3 = com.xiaomi.mipush.sdk.a.a(r3);	 Catch:{ f -> 0x01f3, Exception -> 0x0207 }
        r4 = com.xiaomi.push.service.r.b(r2);	 Catch:{ f -> 0x01f3, Exception -> 0x0207 }
        if (r4 == 0) goto L_0x01f9;
    L_0x01eb:
        r0 = "message arrived: receive ignore reg message, ignore!";
        com.xiaomi.channel.commonutils.logger.b.d(r0);	 Catch:{ f -> 0x01f3, Exception -> 0x0207 }
        goto L_0x0118;
    L_0x01f3:
        r0 = move-exception;
        com.xiaomi.channel.commonutils.logger.b.a(r0);
        goto L_0x0118;
    L_0x01f9:
        r4 = r3.i();	 Catch:{ f -> 0x01f3, Exception -> 0x0207 }
        if (r4 != 0) goto L_0x020d;
    L_0x01ff:
        r0 = "message arrived: receive message without registration. need unregister or re-register!";
        com.xiaomi.channel.commonutils.logger.b.d(r0);	 Catch:{ f -> 0x01f3, Exception -> 0x0207 }
        goto L_0x0118;
    L_0x0207:
        r0 = move-exception;
        com.xiaomi.channel.commonutils.logger.b.a(r0);
        goto L_0x0118;
    L_0x020d:
        r4 = r3.i();	 Catch:{ f -> 0x01f3, Exception -> 0x0207 }
        if (r4 == 0) goto L_0x0221;
    L_0x0213:
        r3 = r3.n();	 Catch:{ f -> 0x01f3, Exception -> 0x0207 }
        if (r3 == 0) goto L_0x0221;
    L_0x0219:
        r0 = "message arrived: app info is invalidated";
        com.xiaomi.channel.commonutils.logger.b.d(r0);	 Catch:{ f -> 0x01f3, Exception -> 0x0207 }
        goto L_0x0118;
    L_0x0221:
        r0 = r10.a(r2, r0);	 Catch:{ f -> 0x01f3, Exception -> 0x0207 }
        goto L_0x004a;
    L_0x0227:
        r2 = move-exception;
        goto L_0x016e;
        */
    }

    public List<String> a(TimeZone timeZone, TimeZone timeZone2, List<String> list) {
        if (timeZone.equals(timeZone2)) {
            return list;
        }
        long rawOffset = (long) (((timeZone.getRawOffset() - timeZone2.getRawOffset()) / 1000) / 60);
        long parseLong = Long.parseLong(((String) list.get(0)).split(":")[0]);
        long parseLong2 = Long.parseLong(((String) list.get(0)).split(":")[1]);
        parseLong = ((((parseLong * 60) + parseLong2) - rawOffset) + 1440) % 1440;
        long parseLong3 = (((Long.parseLong(((String) list.get(1)).split(":")[1]) + (60 * Long.parseLong(((String) list.get(1)).split(":")[0]))) - rawOffset) + 1440) % 1440;
        List arrayList = new ArrayList();
        arrayList.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(parseLong / 60), Long.valueOf(parseLong % 60)}));
        arrayList.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(parseLong3 / 60), Long.valueOf(parseLong3 % 60)}));
        return arrayList;
    }
}
