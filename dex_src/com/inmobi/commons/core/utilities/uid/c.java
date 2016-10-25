package com.inmobi.commons.core.utilities.uid;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import anet.channel.security.ISecurity;
import com.google.android.gms.common.GoogleApiAvailability;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.e;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: UidHelper.java
public class c {
    private static final String a;
    private static final Object b;
    private static c c;
    private static a d;
    private static String e;

    // compiled from: UidHelper.java
    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ b a;

        AnonymousClass_1(b bVar) {
            this.a = bVar;
        }

        public void run() {
            try {
                Class forName = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                Class forName2 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
                Object invoke = forName.getDeclaredMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{a.b()});
                String str = (String) forName2.getDeclaredMethod("getId", null).invoke(invoke, null);
                d.a(str);
                this.a.a(str);
                Boolean bool = (Boolean) forName2.getDeclaredMethod("isLimitAdTrackingEnabled", null).invoke(invoke, null);
                d.a(bool.booleanValue());
                this.a.a(bool.booleanValue());
            } catch (Throwable e) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to fetch advertising id.", e);
            } catch (Throwable e2) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to fetch advertising id.", e2);
            } catch (Throwable e22) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to fetch advertising id.", e22);
            } catch (Throwable e222) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to fetch advertising id.", e222);
            }
        }
    }

    static {
        a = c.class.getSimpleName();
        b = new Object();
    }

    public static c a() {
        c cVar = c;
        if (cVar == null) {
            synchronized (b) {
                cVar = c;
                if (cVar == null) {
                    cVar = new c();
                    c = cVar;
                }
            }
        }
        return cVar;
    }

    private c() {
    }

    public void b() {
        e();
        d();
        r();
        q();
        n();
    }

    private void q() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.inmobi.share.id");
        a.b().registerReceiver(new ImIdShareBroadCastReceiver(), intentFilter);
    }

    public String c() {
        return e;
    }

    private void r() {
        b bVar = new b();
        String c = bVar.c();
        if (c == null) {
            c = UUID.randomUUID().toString();
            bVar.b(c);
        }
        e = c;
    }

    public void d() {
        k();
    }

    public void e() {
        if (l()) {
            a j = j();
            if (j != null) {
                String b = j.b();
                if (b != null) {
                    Logger.a(InternalLogLevel.DEBUG, a, new StringBuilder("Publisher device Id is ").append(b).toString());
                    return;
                }
                return;
            }
            return;
        }
        Logger.a(InternalLogLevel.DEBUG, a, new StringBuilder("Publisher device Id is ").append(a(i())).toString());
    }

    String a(String str) {
        return a(str, "SHA-1");
    }

    String f() {
        return MessageService.MSG_DB_NOTIFY_REACHED;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    java.lang.String a(java.lang.String r6, java.lang.String r7) {
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.utilities.uid.c.a(java.lang.String, java.lang.String):java.lang.String");
        /*
        this = this;
        if (r6 == 0) goto L_0x000f;
    L_0x0002:
        r0 = "";
        r1 = r6.trim();	 Catch:{ NoSuchAlgorithmException -> 0x0047 }
        r0 = r0.equals(r1);	 Catch:{ NoSuchAlgorithmException -> 0x0047 }
        if (r0 == 0) goto L_0x0013;
    L_0x000f:
        r0 = "TEST_EMULATOR";
    L_0x0012:
        return r0;
    L_0x0013:
        r0 = java.security.MessageDigest.getInstance(r7);	 Catch:{ NoSuchAlgorithmException -> 0x0047 }
        r1 = r6.getBytes();	 Catch:{ NoSuchAlgorithmException -> 0x0047 }
        r0.update(r1);	 Catch:{ NoSuchAlgorithmException -> 0x0047 }
        r1 = r0.digest();	 Catch:{ NoSuchAlgorithmException -> 0x0047 }
        r2 = new java.lang.StringBuffer;	 Catch:{ NoSuchAlgorithmException -> 0x0047 }
        r2.<init>();	 Catch:{ NoSuchAlgorithmException -> 0x0047 }
        r0 = 0;
    L_0x0028:
        r3 = r1.length;	 Catch:{ NoSuchAlgorithmException -> 0x0047 }
        if (r0 >= r3) goto L_0x0042;
    L_0x002b:
        r3 = r1[r0];	 Catch:{ NoSuchAlgorithmException -> 0x0047 }
        r3 = r3 & 255;
        r3 = r3 + 256;
        r4 = 16;
        r3 = java.lang.Integer.toString(r3, r4);	 Catch:{ NoSuchAlgorithmException -> 0x0047 }
        r4 = 1;
        r3 = r3.substring(r4);	 Catch:{ NoSuchAlgorithmException -> 0x0047 }
        r2.append(r3);	 Catch:{ NoSuchAlgorithmException -> 0x0047 }
        r0 = r0 + 1;
        goto L_0x0028;
    L_0x0042:
        r0 = r2.toString();	 Catch:{ NoSuchAlgorithmException -> 0x0047 }
        goto L_0x0012;
    L_0x0047:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = 0;
        goto L_0x0012;
        */
    }

    String b(String str) {
        return a(str, ISecurity.SIGN_ALGORITHM_MD5);
    }

    String g() {
        return e.d();
    }

    String h() {
        return e.e();
    }

    String i() {
        String string = Secure.getString(a.b().getContentResolver(), "android_id");
        return string == null ? System.getString(a.b().getContentResolver(), "android_id") : string;
    }

    a j() {
        return d;
    }

    void k() {
        b bVar = new b();
        a aVar = new a();
        d = aVar;
        aVar.a(bVar.a());
        d.a(bVar.b());
        new Thread(new AnonymousClass_1(bVar)).start();
    }

    boolean l() {
        try {
            return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(a.b()) == 0;
        } catch (NoClassDefFoundError e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Google Play Services is not installed!");
            return false;
        } catch (RuntimeException e2) {
            e2.printStackTrace();
            Map hashMap = new HashMap();
            hashMap.put(JsInterface.FUNPLAY_AD_TRPE, "RuntimeException");
            hashMap.put(Constants.SHARED_MESSAGE_ID_FILE, e2.getMessage());
            com.inmobi.commons.core.c.a.a().a("root", "ExceptionCaught", hashMap);
            return false;
        }
    }

    public boolean m() {
        a j = a().j();
        return j != null ? j.a() : false;
    }

    protected void n() {
        b bVar = new b();
        String d = bVar.d();
        long e = bVar.e();
        if (d == null) {
            d = UUID.randomUUID().toString();
            bVar.c(d);
            bVar.b(c());
            bVar.d(c());
        }
        if (e == 0) {
            bVar.a(System.currentTimeMillis());
        }
        Intent intent = new Intent();
        intent.setAction("com.inmobi.share.id");
        intent.putExtra("imid", d);
        intent.putExtra("appendedid", bVar.f());
        intent.putExtra("imidts", bVar.e());
        intent.putExtra(SocialConstants.PARAM_APP_ID, bVar.c());
        a.b().sendBroadcast(intent);
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Generating and broadcasting IDs. ID:").append(d).append(" AID:").append(bVar.f()).toString());
    }

    String a(Context context) {
        return new b().d();
    }

    String b(Context context) {
        b bVar = new b();
        try {
            JSONObject jSONObject = new JSONObject();
            CharSequence c = bVar.c();
            if (c != null) {
                jSONObject.put("p", c);
            }
            String f = bVar.f();
            if (f != null && f.contains(c)) {
                f = f.replace(c, com.umeng.a.d);
            }
            Object substring;
            if (!(substring == null || substring.trim().length() == 0)) {
                if (substring.charAt(0) == ',') {
                    substring = substring.substring(1);
                }
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(substring);
                jSONObject.put("s", jSONArray);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
