package com.taobao.accs.utl;

import android.content.Context;
import com.alipay.sdk.packet.d;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.ChannelService;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;
import com.taobao.orange.OrangeConfig;
import com.taobao.orange.OrangeConfigListenerV1;
import com.tencent.bugly.Bugly;
import java.util.HashMap;
import java.util.Map;

// compiled from: Taobao
public class h {
    public static final String NAMESPACE = "accs";
    private static boolean a;

    // compiled from: Taobao
    public static class a implements OrangeConfigListenerV1 {
        public void onConfigUpdate(String str, boolean z) {
            if (GlobalClientInfo.getContext() == null) {
                ALog.e("OrangeAdapter", "onConfigUpdate context null", new Object[0]);
                return;
            }
            try {
                ALog.i("OrangeAdapter", "onConfigUpdate", d.h, str);
                if (str != null && NAMESPACE.equals(str)) {
                    h.d();
                    Map hashMap = new HashMap();
                    hashMap.put(Constants.SP_KEY_TNET_LOG_OFF, Boolean.valueOf(h.a(NAMESPACE, Constants.SP_KEY_TNET_LOG_OFF, Bugly.SDK_IS_DEV)));
                    hashMap.put(Constants.SP_KEY_ELECTION_ENABLE, Boolean.valueOf(h.a(NAMESPACE, Constants.SP_KEY_ELECTION_ENABLE, "true")));
                    hashMap.put(Constants.SP_KEY_HB_SMART_ENABLE, Boolean.valueOf(h.a(NAMESPACE, "heartbeat_smart_enable", "true")));
                    h.b(GlobalClientInfo.getContext(), hashMap);
                    h.b(GlobalClientInfo.getContext(), ChannelService.SUPPORT_FOREGROUND_VERSION_KEY, UtilityImpl.String2Int(h.a(NAMESPACE, ChannelService.SUPPORT_FOREGROUND_VERSION_KEY, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE)));
                }
            } catch (Throwable th) {
                ALog.e("OrangeAdapter", "onConfigUpdate", th, new Object[0]);
            }
        }
    }

    static {
        a = false;
        try {
            Class.forName("com.taobao.orange.OrangeConfig");
            a = true;
        } catch (Exception e) {
            a = false;
        }
    }

    public static void a(String[] strArr, OrangeConfigListenerV1 orangeConfigListenerV1) {
        if (a) {
            OrangeConfig.getInstance().registerListener(strArr, orangeConfigListenerV1);
        } else {
            ALog.w("OrangeAdapter", "no orange sdk", new Object[0]);
        }
    }

    public static String a(String str, String str2, String str3) {
        if (a) {
            return OrangeConfig.getInstance().getConfig(str, str2, str3);
        }
        ALog.w("OrangeAdapter", "no orange sdk", new Object[0]);
        return str3;
    }

    public static boolean a() {
        boolean booleanValue;
        try {
            booleanValue = Boolean.valueOf(a(NAMESPACE, "main_function_enable", "true")).booleanValue();
        } catch (Throwable th) {
            ALog.e("OrangeAdapter", "isAccsEnabled", th, new Object[0]);
            booleanValue = true;
        }
        ALog.i("OrangeAdapter", "isAccsEnabled", "enable", Boolean.valueOf(booleanValue));
        return booleanValue;
    }

    public static boolean b() {
        boolean a;
        try {
            a = a(GlobalClientInfo.getContext(), Constants.SP_KEY_HB_SMART_ENABLE, true);
        } catch (Throwable th) {
            ALog.e("OrangeAdapter", "isSmartHb", th, new Object[0]);
            a = true;
        }
        ALog.d("OrangeAdapter", "isSmartHb", "result", Boolean.valueOf(a));
        return a;
    }

    public static boolean a(boolean z) {
        boolean a;
        try {
            String str = "default";
            if (z) {
                str = a(NAMESPACE, Constants.SP_KEY_TNET_LOG_OFF, "default");
            }
            if (str.equals("default")) {
                a = a(GlobalClientInfo.getContext(), Constants.SP_KEY_TNET_LOG_OFF, false);
            } else {
                a = Boolean.valueOf(str).booleanValue();
                try {
                    b(GlobalClientInfo.getContext(), Constants.SP_KEY_TNET_LOG_OFF, a);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    ALog.e("OrangeAdapter", "isTnetLogOff", th2, new Object[0]);
                    ALog.i("OrangeAdapter", "isTnetLogOff", "result", Boolean.valueOf(a));
                    return a;
                }
            }
        } catch (Throwable th3) {
            th2 = th3;
            a = false;
            ALog.e("OrangeAdapter", "isTnetLogOff", th2, new Object[0]);
            ALog.i("OrangeAdapter", "isTnetLogOff", "result", Boolean.valueOf(a));
            return a;
        }
        ALog.i("OrangeAdapter", "isTnetLogOff", "result", Boolean.valueOf(a));
        return a;
    }

    public static boolean c() {
        boolean a;
        boolean z = GlobalClientInfo.c;
        try {
            a = a(GlobalClientInfo.getContext(), Constants.SP_KEY_ELECTION_ENABLE, GlobalClientInfo.c);
        } catch (Throwable th) {
            ALog.e("OrangeAdapter", "isElectionEnable", th, new Object[0]);
            a = z;
        }
        if (!a) {
            ALog.e("OrangeAdapter", "isElectionEnable", "result", Boolean.valueOf(a));
        }
        return a;
    }

    private static boolean a(Context context, String str, boolean z) {
        try {
            return context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getBoolean(str, z);
        } catch (Throwable e) {
            ALog.e("OrangeAdapter", "getConfigFromSP fail:", e, "key", str);
            return z;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void b(android.content.Context r10, java.lang.String r11, boolean r12) {
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.utl.h.b(android.content.Context, java.lang.String, boolean):void");
        /*
        r9 = 4;
        r8 = 3;
        r7 = 2;
        r6 = 1;
        r5 = 0;
        if (r10 != 0) goto L_0x0014;
    L_0x0007:
        r0 = "OrangeAdapter";
        r1 = "saveTLogOffToSP context null";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0044 }
        com.taobao.accs.utl.ALog.e(r0, r1, r2);	 Catch:{ Exception -> 0x0044 }
    L_0x0013:
        return;
    L_0x0014:
        r0 = "ACCS_SDK";
        r1 = 0;
        r0 = r10.getSharedPreferences(r0, r1);	 Catch:{ Exception -> 0x0044 }
        r0 = r0.edit();	 Catch:{ Exception -> 0x0044 }
        r0.putBoolean(r11, r12);	 Catch:{ Exception -> 0x0044 }
        r0.apply();	 Catch:{ Exception -> 0x0044 }
    L_0x0026:
        r0 = "OrangeAdapter";
        r1 = "saveConfigToSP";
        r2 = new java.lang.Object[r9];
        r3 = "key";
        r2[r5] = r3;
        r2[r6] = r11;
        r3 = "value";
        r2[r7] = r3;
        r3 = java.lang.Boolean.valueOf(r12);
        r2[r8] = r3;
        com.taobao.accs.utl.ALog.i(r0, r1, r2);
        goto L_0x0013;
    L_0x0044:
        r0 = move-exception;
        r1 = "OrangeAdapter";
        r2 = "saveConfigToSP fail:";
        r3 = new java.lang.Object[r9];
        r4 = "key";
        r3[r5] = r4;
        r3[r6] = r11;
        r4 = "value";
        r3[r7] = r4;
        r4 = java.lang.Boolean.valueOf(r12);
        r3[r8] = r4;
        com.taobao.accs.utl.ALog.e(r1, r2, r0, r3);
        goto L_0x0026;
        */
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void b(android.content.Context r10, java.lang.String r11, int r12) {
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.utl.h.b(android.content.Context, java.lang.String, int):void");
        /*
        r9 = 4;
        r8 = 3;
        r7 = 2;
        r6 = 1;
        r5 = 0;
        if (r10 != 0) goto L_0x0014;
    L_0x0007:
        r0 = "OrangeAdapter";
        r1 = "saveTLogOffToSP context null";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0044 }
        com.taobao.accs.utl.ALog.e(r0, r1, r2);	 Catch:{ Exception -> 0x0044 }
    L_0x0013:
        return;
    L_0x0014:
        r0 = "ACCS_SDK";
        r1 = 0;
        r0 = r10.getSharedPreferences(r0, r1);	 Catch:{ Exception -> 0x0044 }
        r0 = r0.edit();	 Catch:{ Exception -> 0x0044 }
        r0.putInt(r11, r12);	 Catch:{ Exception -> 0x0044 }
        r0.apply();	 Catch:{ Exception -> 0x0044 }
    L_0x0026:
        r0 = "OrangeAdapter";
        r1 = "saveConfigToSP";
        r2 = new java.lang.Object[r9];
        r3 = "key";
        r2[r5] = r3;
        r2[r6] = r11;
        r3 = "value";
        r2[r7] = r3;
        r3 = java.lang.Integer.valueOf(r12);
        r2[r8] = r3;
        com.taobao.accs.utl.ALog.i(r0, r1, r2);
        goto L_0x0013;
    L_0x0044:
        r0 = move-exception;
        r1 = "OrangeAdapter";
        r2 = "saveConfigToSP fail:";
        r3 = new java.lang.Object[r9];
        r4 = "key";
        r3[r5] = r4;
        r3[r6] = r11;
        r4 = "value";
        r3[r7] = r4;
        r4 = java.lang.Integer.valueOf(r12);
        r3[r8] = r4;
        com.taobao.accs.utl.ALog.e(r1, r2, r0, r3);
        goto L_0x0026;
        */
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void b(android.content.Context r8, java.util.Map<java.lang.String, java.lang.Boolean> r9) {
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.utl.h.b(android.content.Context, java.util.Map):void");
        /*
        r7 = 2;
        r6 = 1;
        r5 = 0;
        if (r9 == 0) goto L_0x000b;
    L_0x0005:
        r0 = r9.size();	 Catch:{ Exception -> 0x0040 }
        if (r0 != 0) goto L_0x000c;
    L_0x000b:
        return;
    L_0x000c:
        r0 = "ACCS_SDK";
        r1 = 0;
        r0 = r8.getSharedPreferences(r0, r1);	 Catch:{ Exception -> 0x0040 }
        r2 = r0.edit();	 Catch:{ Exception -> 0x0040 }
        r0 = r9.entrySet();	 Catch:{ Exception -> 0x0040 }
        r3 = r0.iterator();	 Catch:{ Exception -> 0x0040 }
    L_0x0020:
        r0 = r3.hasNext();	 Catch:{ Exception -> 0x0040 }
        if (r0 == 0) goto L_0x006e;
    L_0x0026:
        r0 = r3.next();	 Catch:{ Exception -> 0x0040 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ Exception -> 0x0040 }
        r1 = r0.getKey();	 Catch:{ Exception -> 0x0040 }
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x0040 }
        r0 = r0.getValue();	 Catch:{ Exception -> 0x0040 }
        r0 = (java.lang.Boolean) r0;	 Catch:{ Exception -> 0x0040 }
        r0 = r0.booleanValue();	 Catch:{ Exception -> 0x0040 }
        r2.putBoolean(r1, r0);	 Catch:{ Exception -> 0x0040 }
        goto L_0x0020;
    L_0x0040:
        r0 = move-exception;
        r1 = "OrangeAdapter";
        r2 = "saveConfigsToSP fail:";
        r3 = new java.lang.Object[r7];
        r4 = "configs";
        r3[r5] = r4;
        r4 = r9.toString();
        r3[r6] = r4;
        com.taobao.accs.utl.ALog.e(r1, r2, r0, r3);
    L_0x0057:
        r0 = "OrangeAdapter";
        r1 = "saveConfigsToSP";
        r2 = new java.lang.Object[r7];
        r3 = "configs";
        r2[r5] = r3;
        r3 = r9.toString();
        r2[r6] = r3;
        com.taobao.accs.utl.ALog.i(r0, r1, r2);
        goto L_0x000b;
    L_0x006e:
        r2.apply();	 Catch:{ Exception -> 0x0040 }
        goto L_0x0057;
        */
    }

    public static void d() {
        if (!a()) {
            ALog.e("OrangeAdapter", "force disable service", new Object[0]);
            ACCSManager.forceDisableService(GlobalClientInfo.getContext());
        } else if (UtilityImpl.getFocusDisableStatus(GlobalClientInfo.getContext())) {
            ALog.i("OrangeAdapter", "force enable service", new Object[0]);
            ACCSManager.forceEnableService(GlobalClientInfo.getContext());
        }
    }
}
