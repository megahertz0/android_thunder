package com.qq.e.comm.managers.setting;

import android.content.Context;
import android.util.Base64;
import android.util.Pair;
import com.qq.e.comm.constants.Constants.KEYS;
import com.qq.e.comm.constants.Constants.SETTING;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import com.sina.weibo.sdk.component.GameManager;
import java.io.File;
import java.io.IOException;
import org.json.JSONObject;

public class a {
    JSONObject a;

    public a() {
        this.a = new JSONObject();
    }

    public a(String str) {
        this();
        GDTLogger.d(new StringBuilder("Initialize GDTAPPSetting,Json=").append(str).toString());
        if (!StringUtil.isEmpty(str)) {
            try {
                this.a = new JSONObject(str);
            } catch (Throwable e) {
                GDTLogger.e("JsonException While build GDTAPPSetting Instance from JSON", e);
            }
        }
    }

    private static Pair<String, String> a(Context context, String str) {
        File dir = context.getDir(SETTING.SETTINGDIR, 0);
        if (!dir.exists()) {
            return null;
        }
        File file = new File(dir, str + ".sig");
        File file2 = new File(dir, str + ".cfg");
        if (!file.exists() || !file2.exists()) {
            return null;
        }
        try {
            return new Pair(StringUtil.readAll(file), StringUtil.readAll(file2));
        } catch (IOException e) {
            return null;
        }
    }

    public static com.qq.e.comm.a a(Context context) {
        Pair a = a(context, SETTING.DEV_CLOUD_SETTING);
        if (a == null) {
            return null;
        }
        try {
            if (com.qq.e.comm.util.a.a().a((String) a.first, (String) a.second)) {
                return new com.qq.e.comm.a((String) a.first, new a(new String(Base64.decode((String) a.second, 0), GameManager.DEFAULT_CHARSET)));
            }
            GDTLogger.e("verify local dev cloud setting fail");
            return null;
        } catch (Throwable th) {
            GDTLogger.e("exception while loading local dev cloud setting", th);
            return null;
        }
    }

    public static boolean a(Context context, String str, String str2) {
        return a(context, SETTING.SDK_CLOUD_SETTING, str, str2);
    }

    private static boolean a(Context context, String str, String str2, String str3) {
        if (StringUtil.isEmpty(str2) || StringUtil.isEmpty(str3)) {
            GDTLogger.e(String.format("Fail to update Cloud setting due to sig or setting is empty,name=%s\tsig=%s\tsetting=%s", new Object[]{str, str2, str3}));
            return false;
        } else if (com.qq.e.comm.util.a.a().a(str2, str3)) {
            return b(context, str, str2, str3);
        } else {
            GDTLogger.e(String.format("Fail to update Cloud setting due to sig verify fail,name=%s\tsig=%s\tsetting=%s", new Object[]{str, str2, str3}));
            return false;
        }
    }

    public static d b(Context context) {
        Pair a = a(context, SETTING.SDK_CLOUD_SETTING);
        if (a == null) {
            return null;
        }
        try {
            if (com.qq.e.comm.util.a.a().a((String) a.first, (String) a.second)) {
                return new d(new c(new String(Base64.decode((String) a.second, 0), GameManager.DEFAULT_CHARSET)), (byte) 0);
            }
            GDTLogger.e("verify local sdk cloud setting fail");
            return null;
        } catch (Throwable th) {
            GDTLogger.e("exception while loading local sdk cloud setting", th);
            return null;
        }
    }

    public static boolean b(Context context, String str, String str2) {
        return a(context, SETTING.DEV_CLOUD_SETTING, str, str2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean b(android.content.Context r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.managers.setting.a.b(android.content.Context, java.lang.String, java.lang.String, java.lang.String):boolean");
        /*
        r2 = 0;
        r0 = 0;
        r1 = "e_qq_com_setting";
        r1 = r7.getDir(r1, r0);
        r3 = r1.exists();
        if (r3 != 0) goto L_0x0012;
    L_0x000f:
        r1.mkdirs();
    L_0x0012:
        r4 = new java.io.File;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3 = r3.append(r8);
        r5 = ".cfg";
        r3 = r3.append(r5);
        r3 = r3.toString();
        r4.<init>(r1, r3);
        r5 = new java.io.File;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3 = r3.append(r8);
        r6 = ".sig";
        r3 = r3.append(r6);
        r3 = r3.toString();
        r5.<init>(r1, r3);
        r3 = new java.io.FileWriter;	 Catch:{ Exception -> 0x005c, all -> 0x0071 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x005c, all -> 0x0071 }
        r3.write(r10);	 Catch:{ Exception -> 0x0089, all -> 0x0080 }
        r1 = new java.io.FileWriter;	 Catch:{ Exception -> 0x0089, all -> 0x0080 }
        r1.<init>(r5);	 Catch:{ Exception -> 0x0089, all -> 0x0080 }
        r1.write(r9);	 Catch:{ Exception -> 0x008d, all -> 0x0082 }
        r3.close();	 Catch:{ Exception -> 0x0090 }
        r1.close();	 Catch:{ Exception -> 0x0090 }
    L_0x005a:
        r0 = 1;
    L_0x005b:
        return r0;
    L_0x005c:
        r1 = move-exception;
        r1 = r2;
    L_0x005e:
        r4.delete();	 Catch:{ all -> 0x0085 }
        r5.delete();	 Catch:{ all -> 0x0085 }
        if (r2 == 0) goto L_0x0069;
    L_0x0066:
        r2.close();	 Catch:{ Exception -> 0x006f }
    L_0x0069:
        if (r1 == 0) goto L_0x005b;
    L_0x006b:
        r1.close();	 Catch:{ Exception -> 0x006f }
        goto L_0x005b;
    L_0x006f:
        r1 = move-exception;
        goto L_0x005b;
    L_0x0071:
        r0 = move-exception;
        r3 = r2;
    L_0x0073:
        if (r3 == 0) goto L_0x0078;
    L_0x0075:
        r3.close();	 Catch:{ Exception -> 0x007e }
    L_0x0078:
        if (r2 == 0) goto L_0x007d;
    L_0x007a:
        r2.close();	 Catch:{ Exception -> 0x007e }
    L_0x007d:
        throw r0;
    L_0x007e:
        r1 = move-exception;
        goto L_0x007d;
    L_0x0080:
        r0 = move-exception;
        goto L_0x0073;
    L_0x0082:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0073;
    L_0x0085:
        r0 = move-exception;
        r3 = r2;
        r2 = r1;
        goto L_0x0073;
    L_0x0089:
        r1 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x005e;
    L_0x008d:
        r2 = move-exception;
        r2 = r3;
        goto L_0x005e;
    L_0x0090:
        r0 = move-exception;
        goto L_0x005a;
        */
    }

    final Object a(String str) {
        return this.a.opt(str);
    }

    final Object a(String str, String str2) {
        JSONObject optJSONObject = this.a.optJSONObject(KEYS.PLACEMENTS);
        optJSONObject = optJSONObject != null ? optJSONObject.optJSONObject(str2) : null;
        return optJSONObject != null ? optJSONObject.opt(str) : null;
    }
}
