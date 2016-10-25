package com.qq.e.comm;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.qq.e.comm.constants.Constants.PLUGIN;
import com.qq.e.comm.constants.CustomPkgConstants;
import com.qq.e.comm.managers.plugin.PM;
import com.qq.e.comm.managers.setting.SM;
import com.qq.e.comm.managers.status.APPStatus;
import com.qq.e.comm.managers.status.DeviceStatus;
import com.qq.e.comm.managers.status.SDKStatus;
import com.qq.e.comm.util.FileUtil;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import com.taobao.accs.common.Constants;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    private String a;
    private com.qq.e.comm.managers.setting.a b;

    public a(String str, com.qq.e.comm.managers.setting.a aVar) {
        this.a = str;
        this.b = aVar;
    }

    public static JSONObject a(PM pm) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt(Constants.KEY_ELECTION_SDKV, SDKStatus.getSDKVersion());
        jSONObject.putOpt("pv", Integer.valueOf(pm.getPluginVersion()));
        return jSONObject;
    }

    public static JSONObject a(SM sm) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (sm != null) {
            jSONObject.putOpt("suid", sm.getSuid());
            jSONObject.putOpt(Constants.KEY_SID, sm.getSid());
        }
        return jSONObject;
    }

    public static JSONObject a(APPStatus aPPStatus) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (aPPStatus != null) {
            jSONObject.putOpt(com.alipay.sdk.sys.a.i, aPPStatus.getAPPName());
            jSONObject.putOpt(Constants.SP_KEY_APPKEY, aPPStatus.getAPPID());
            jSONObject.putOpt("appv", aPPStatus.getAPPVersion());
        }
        return jSONObject;
    }

    public static JSONObject a(DeviceStatus deviceStatus) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (deviceStatus != null) {
            jSONObject.putOpt("so", deviceStatus.getScreenOrientation());
            jSONObject.putOpt("dn", deviceStatus.getDataNet());
            jSONObject.putOpt(anet.channel.strategy.dispatch.a.LATITUDE, deviceStatus.getLat());
            jSONObject.putOpt(anet.channel.strategy.dispatch.a.OTHER, deviceStatus.getLng());
            for (Entry entry : deviceStatus.getLacAndCeilId().entrySet()) {
                jSONObject.putOpt((String) entry.getKey(), entry.getValue());
            }
        }
        return jSONObject;
    }

    public static boolean a(Context context) {
        try {
            if (b(context)) {
                if (a(context, Class.forName(CustomPkgConstants.getADActivityName()))) {
                    if (b(context, Class.forName(CustomPkgConstants.getDownLoadServiceName()))) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            GDTLogger.e("Exception While check SDK Env", th);
            return false;
        }
    }

    public static boolean a(Context context, File file, File file2) {
        AssetManager assets = context.getAssets();
        try {
            if (Arrays.binarySearch(assets.list(CustomPkgConstants.getAssetPluginDir()), CustomPkgConstants.getAssetPluginName()) < 0) {
                return false;
            }
            String str = CustomPkgConstants.getAssetPluginDir() + File.separator + CustomPkgConstants.getAssetPluginName();
            StringUtil.writeTo(new StringBuilder("532#####").append(PLUGIN.ASSET_PLUGIN_SIG).toString(), file2);
            if (StringUtil.isEmpty(CustomPkgConstants.getAssetPluginXorKey())) {
                return FileUtil.copyTo(assets.open(str), file);
            }
            InputStream open = assets.open(str);
            OutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bytes = CustomPkgConstants.getAssetPluginXorKey().getBytes();
            byte[] bArr = new byte[1024];
            int length = bytes.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                int read = open.read(bArr);
                if (read > 0) {
                    int i3 = 0;
                    while (i3 < read) {
                        int i4 = i + 1;
                        if (i >= 64) {
                            i = i2 + 1;
                            bArr[i3] = (byte) (bytes[i2 % length] ^ bArr[i3]);
                        } else {
                            i = i2;
                        }
                        i3++;
                        i2 = i;
                        i = i4;
                    }
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    open.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Throwable th) {
            GDTLogger.report("Exception while init default plugin manager", th);
            return false;
        }
    }

    private static boolean a(Context context, Class<?>... clsArr) {
        int i = 0;
        while (i <= 0) {
            try {
                Intent intent = new Intent();
                intent.setClass(context, clsArr[0]);
                if (context.getPackageManager().resolveActivity(intent, AccessibilityNodeInfoCompat.ACTION_CUT) == null) {
                    GDTLogger.e(String.format("Activity[%s] is required in AndroidManifest.xml", new Object[]{clsArr[0].getName()}));
                    return false;
                }
                i++;
            } catch (Throwable th) {
                GDTLogger.e("Exception while checking required activities", th);
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(byte[] r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.a.a(byte[]):byte[]");
        /*
        r0 = 0;
        if (r4 == 0) goto L_0x0006;
    L_0x0003:
        r1 = r4.length;
        if (r1 != 0) goto L_0x0008;
    L_0x0006:
        r0 = r4;
    L_0x0007:
        return r0;
    L_0x0008:
        r3 = new java.io.ByteArrayOutputStream;
        r3.<init>();
        r2 = new java.util.zip.GZIPOutputStream;	 Catch:{ Exception -> 0x0028, all -> 0x003b }
        r2.<init>(r3);	 Catch:{ Exception -> 0x0028, all -> 0x003b }
        r2.write(r4);	 Catch:{ Exception -> 0x004e }
        r2.finish();	 Catch:{ Exception -> 0x004e }
        r0 = r3.toByteArray();	 Catch:{ Exception -> 0x004e }
        r2.close();	 Catch:{ Exception -> 0x0023 }
        r3.close();	 Catch:{ Exception -> 0x0023 }
        goto L_0x0007;
    L_0x0023:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0007;
    L_0x0028:
        r1 = move-exception;
        r2 = r0;
    L_0x002a:
        r1.printStackTrace();	 Catch:{ all -> 0x004c }
        if (r2 == 0) goto L_0x0032;
    L_0x002f:
        r2.close();	 Catch:{ Exception -> 0x0036 }
    L_0x0032:
        r3.close();	 Catch:{ Exception -> 0x0036 }
        goto L_0x0007;
    L_0x0036:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0007;
    L_0x003b:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x003e:
        if (r2 == 0) goto L_0x0043;
    L_0x0040:
        r2.close();	 Catch:{ Exception -> 0x0047 }
    L_0x0043:
        r3.close();	 Catch:{ Exception -> 0x0047 }
    L_0x0046:
        throw r0;
    L_0x0047:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0046;
    L_0x004c:
        r0 = move-exception;
        goto L_0x003e;
    L_0x004e:
        r1 = move-exception;
        goto L_0x002a;
        */
    }

    private static boolean b(Context context) {
        String[] strArr = new String[]{MsgConstant.PERMISSION_INTERNET, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE, MsgConstant.PERMISSION_ACCESS_WIFI_STATE, MsgConstant.PERMISSION_READ_PHONE_STATE, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE};
        int i = 0;
        while (i < 5) {
            try {
                if (context.checkCallingOrSelfPermission(strArr[i]) == -1) {
                    GDTLogger.e(String.format("Permission %s is required in AndroidManifest.xml", new Object[]{strArr[i]}));
                    return false;
                }
                i++;
            } catch (Throwable th) {
                GDTLogger.e("Check required Permissions error", th);
                return false;
            }
        }
        return true;
    }

    private static boolean b(Context context, Class<?>... clsArr) {
        int i = 0;
        while (i <= 0) {
            try {
                Class cls = clsArr[0];
                Intent intent = new Intent();
                intent.setClass(context, cls);
                if (context.getPackageManager().resolveService(intent, AccessibilityNodeInfoCompat.ACTION_CUT) == null) {
                    GDTLogger.e(String.format("Service[%s] is required in AndroidManifest.xml", new Object[]{cls.getName()}));
                    return false;
                }
                i++;
            } catch (Throwable th) {
                GDTLogger.e("Exception while checking required services", th);
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] b(byte[] r7) {
        throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.a.b(byte[]):byte[]");
        /*
        r0 = 0;
        if (r7 == 0) goto L_0x0006;
    L_0x0003:
        r1 = r7.length;
        if (r1 != 0) goto L_0x0008;
    L_0x0006:
        r0 = r7;
    L_0x0007:
        return r0;
    L_0x0008:
        r3 = new java.io.ByteArrayInputStream;
        r3.<init>(r7);
        r4 = new java.io.ByteArrayOutputStream;
        r4.<init>();
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r1];
        r2 = new java.util.zip.GZIPInputStream;	 Catch:{ Exception -> 0x0068, all -> 0x0052 }
        r2.<init>(r3);	 Catch:{ Exception -> 0x0068, all -> 0x0052 }
    L_0x001b:
        r5 = r2.read(r1);	 Catch:{ Exception -> 0x0027 }
        r6 = -1;
        if (r5 == r6) goto L_0x003c;
    L_0x0022:
        r6 = 0;
        r4.write(r1, r6, r5);	 Catch:{ Exception -> 0x0027 }
        goto L_0x001b;
    L_0x0027:
        r1 = move-exception;
    L_0x0028:
        r1.printStackTrace();	 Catch:{ all -> 0x0066 }
        if (r2 == 0) goto L_0x0030;
    L_0x002d:
        r2.close();	 Catch:{ Exception -> 0x0037 }
    L_0x0030:
        r3.close();	 Catch:{ Exception -> 0x0037 }
        r4.close();	 Catch:{ Exception -> 0x0037 }
        goto L_0x0007;
    L_0x0037:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0007;
    L_0x003c:
        r4.flush();	 Catch:{ Exception -> 0x0027 }
        r0 = r4.toByteArray();	 Catch:{ Exception -> 0x0027 }
        r2.close();	 Catch:{ Exception -> 0x004d }
        r3.close();	 Catch:{ Exception -> 0x004d }
        r4.close();	 Catch:{ Exception -> 0x004d }
        goto L_0x0007;
    L_0x004d:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0007;
    L_0x0052:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x0055:
        if (r2 == 0) goto L_0x005a;
    L_0x0057:
        r2.close();	 Catch:{ Exception -> 0x0061 }
    L_0x005a:
        r3.close();	 Catch:{ Exception -> 0x0061 }
        r4.close();	 Catch:{ Exception -> 0x0061 }
    L_0x0060:
        throw r0;
    L_0x0061:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0060;
    L_0x0066:
        r0 = move-exception;
        goto L_0x0055;
    L_0x0068:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0028;
        */
    }

    public String a() {
        return this.a;
    }

    public com.qq.e.comm.managers.setting.a b() {
        return this.b;
    }
}
