package com.qq.e.comm.services;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.qq.e.comm.constants.Constants.KEYS;
import com.qq.e.comm.managers.plugin.PM;
import com.qq.e.comm.managers.setting.SM;
import com.qq.e.comm.managers.status.APPStatus;
import com.qq.e.comm.managers.status.DeviceStatus;
import com.qq.e.comm.net.NetworkCallBack;
import com.qq.e.comm.net.NetworkClient.Priority;
import com.qq.e.comm.net.NetworkClientImpl;
import com.qq.e.comm.net.rr.Request;
import com.qq.e.comm.net.rr.Response;
import com.qq.e.comm.net.rr.S2SSRequest;
import com.qq.e.comm.services.RetCodeService.RetCodeInfo;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private static final a a;
    private volatile Boolean b;

    final class AnonymousClass_1 implements NetworkCallBack {
        private /* synthetic */ SM a;
        private /* synthetic */ PM b;
        private /* synthetic */ long c;

        AnonymousClass_1(SM sm, PM pm, long j) {
            this.a = sm;
            this.b = pm;
            this.c = j;
        }

        public final void onException(Exception exception) {
            GDTLogger.e("ActivateError", exception);
            RetCodeService.getInstance().send(new RetCodeInfo("sdk.e.qq.com", "launch", com.umeng.a.d, -1, (int) (System.currentTimeMillis() - this.c), 0, 0, 1));
        }

        public final void onResponse(Request request, Response response) {
            try {
                if (response.getStatusCode() == 200) {
                    String stringContent = response.getStringContent();
                    GDTLogger.d(new StringBuilder("ACTIVERESPONSE:").append(stringContent).toString());
                    if (StringUtil.isEmpty(stringContent)) {
                        GDTLogger.report("SDK Server response empty string,maybe zip or tea format error");
                        RetCodeService.getInstance().send(new RetCodeInfo("sdk.e.qq.com", "launch", com.umeng.a.d, response.getStatusCode(), (int) (System.currentTimeMillis() - this.c), 0, 0, 1));
                        return;
                    }
                    JSONObject jSONObject = new JSONObject(stringContent);
                    int i = -1;
                    if (jSONObject.has(KEYS.RET)) {
                        i = jSONObject.getInt(KEYS.RET);
                    }
                    if (i != 0) {
                        GDTLogger.e(new StringBuilder("Response Error,retCode=").append(i).toString());
                    } else {
                        if (jSONObject.has("suid")) {
                            stringContent = jSONObject.getString("suid");
                            if (!StringUtil.isEmpty(stringContent)) {
                                this.a.updateSUID(stringContent);
                            }
                        }
                        if (jSONObject.has(Constants.KEY_SID)) {
                            stringContent = jSONObject.getString(Constants.KEY_SID);
                            if (!StringUtil.isEmpty(stringContent)) {
                                this.a.updateSID(stringContent);
                            }
                        }
                        if (jSONObject.has("sig")) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("sig");
                            if (jSONObject.has(com.alipay.sdk.sys.a.j)) {
                                String string;
                                jSONObject = jSONObject.getJSONObject(com.alipay.sdk.sys.a.j);
                                if (jSONObject.has("app") && jSONObject2.has("app")) {
                                    string = jSONObject.getString("app");
                                    this.a.updateDEVCloudSetting(jSONObject2.getString("app"), string);
                                }
                                if (jSONObject.has("sdk") && jSONObject2.has("sdk")) {
                                    string = jSONObject.getString("sdk");
                                    this.a.updateSDKCloudSetting(jSONObject2.getString("sdk"), string);
                                }
                                if (jSONObject.has("c")) {
                                    this.a.updateContextSetting(jSONObject.getString("c"));
                                } else {
                                    this.a.updateContextSetting(null);
                                }
                            }
                            if (jSONObject2.has("jar") && jSONObject2.has(SocialConstants.PARAM_URL)) {
                                this.b.update(jSONObject2.getString("jar"), jSONObject2.getString(SocialConstants.PARAM_URL));
                            }
                        }
                    }
                } else {
                    GDTLogger.e(new StringBuilder("SDK server response code error while launch or activate,code:").append(response.getStatusCode()).toString());
                }
                RetCodeService.getInstance().send(new RetCodeInfo("sdk.e.qq.com", "launch", com.umeng.a.d, response.getStatusCode(), (int) (System.currentTimeMillis() - this.c), 0, 0, 1));
            } catch (Throwable e) {
                GDTLogger.e("ActivateError", e);
                RetCodeService.getInstance().send(new RetCodeInfo("sdk.e.qq.com", "launch", com.umeng.a.d, response.getStatusCode(), (int) (System.currentTimeMillis() - this.c), 0, 0, 1));
            } catch (Throwable e2) {
                GDTLogger.e("Parse Active or launch response exception", e2);
                RetCodeService.getInstance().send(new RetCodeInfo("sdk.e.qq.com", "launch", com.umeng.a.d, response.getStatusCode(), (int) (System.currentTimeMillis() - this.c), 0, 0, 1));
            }
        }
    }

    static {
        a = new a();
    }

    public a() {
        this.b = Boolean.valueOf(false);
    }

    public static a a() {
        return a;
    }

    private static String a(Context context) {
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    private static String a(SM sm, PM pm, DeviceStatus deviceStatus, APPStatus aPPStatus, Context context, long j) {
        JSONObject a;
        JSONObject jSONObject = new JSONObject();
        try {
            a = com.qq.e.comm.a.a(sm);
            try {
                String str = "sig";
                JSONObject jSONObject2 = new JSONObject();
                if (sm != null) {
                    jSONObject2.putOpt("app", sm.getDevCloudSettingSig());
                    jSONObject2.putOpt("sdk", sm.getSdkCloudSettingSig());
                }
                if (pm != null) {
                    jSONObject2.putOpt("jar", pm.getLocalSig());
                    jSONObject2.putOpt(KEYS.PLUGIN_VERSION, Integer.valueOf(pm.getPluginVersion()));
                }
                a.put(str, jSONObject2);
                str = "dev";
                jSONObject2 = new JSONObject();
                if (deviceStatus != null) {
                    jSONObject2.putOpt("did", deviceStatus.getPlainDid());
                    jSONObject2.putOpt(IXAdRequestInfo.TEST_MODE, deviceStatus.model);
                    jSONObject2.putOpt("lg", deviceStatus.getLanguage());
                    jSONObject2.putOpt(IXAdRequestInfo.WIDTH, Integer.valueOf(deviceStatus.getDeviceWidth()));
                    jSONObject2.putOpt(IXAdRequestInfo.HEIGHT, Integer.valueOf(deviceStatus.getDeviceHeight()));
                    jSONObject2.putOpt("dd", Integer.valueOf(deviceStatus.getDeviceDensity()));
                    jSONObject2.putOpt("apil", Integer.valueOf(deviceStatus.getVersion()));
                    jSONObject2.putOpt(Constants.KEY_OS_VERSION, anet.channel.strategy.dispatch.a.ANDROID);
                    jSONObject2.putOpt("op", deviceStatus.getOperator());
                    jSONObject2.putOpt("mf", Build.MANUFACTURER);
                }
                a.put(str, jSONObject2);
                a.put("app", com.qq.e.comm.a.a(aPPStatus));
                jSONObject = com.qq.e.comm.a.a(deviceStatus);
                jSONObject.putOpt("process", a(context));
                a.put("c", jSONObject);
                a.put("sdk", com.qq.e.comm.a.a(pm));
                jSONObject = new JSONObject();
                jSONObject2 = new JSONObject();
                jSONObject2.put("sdk_init_time", (System.nanoTime() - j) / 1000000);
                jSONObject.put("performance", jSONObject2);
                a.put(KEYS.BIZ, jSONObject);
            } catch (JSONException e) {
                Throwable e2 = e;
                GDTLogger.e("JSONException while build init req", e2);
                return a.toString();
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            a = jSONObject;
            e2 = th;
            GDTLogger.e("JSONException while build init req", e2);
            return a.toString();
        }
        return a.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r5, java.lang.Throwable r6) {
        throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.services.a.a(java.lang.String, java.lang.Throwable):void");
        /*
        r0 = com.qq.e.comm.managers.GDTADManager.getInstance();
        if (r0 == 0) goto L_0x00c5;
    L_0x0006:
        r0 = com.qq.e.comm.managers.GDTADManager.getInstance();
        r0 = r0.isInitialized();
        if (r0 == 0) goto L_0x00c5;
    L_0x0010:
        r0 = com.qq.e.comm.managers.GDTADManager.getInstance();	 Catch:{ Throwable -> 0x00bd }
        r0 = r0.getSM();	 Catch:{ Throwable -> 0x00bd }
        r0 = com.qq.e.comm.a.a(r0);	 Catch:{ Throwable -> 0x00bd }
        r1 = "c";
        r2 = com.qq.e.comm.managers.GDTADManager.getInstance();	 Catch:{ Throwable -> 0x00bd }
        r2 = r2.getDeviceStatus();	 Catch:{ Throwable -> 0x00bd }
        r2 = com.qq.e.comm.a.a(r2);	 Catch:{ Throwable -> 0x00bd }
        r0.put(r1, r2);	 Catch:{ Throwable -> 0x00bd }
        r1 = "app";
        r2 = com.qq.e.comm.managers.GDTADManager.getInstance();	 Catch:{ Throwable -> 0x00bd }
        r2 = r2.getAppStatus();	 Catch:{ Throwable -> 0x00bd }
        r2 = com.qq.e.comm.a.a(r2);	 Catch:{ Throwable -> 0x00bd }
        r0.put(r1, r2);	 Catch:{ Throwable -> 0x00bd }
        r1 = new java.util.HashMap;	 Catch:{ Throwable -> 0x00bd }
        r1.<init>();	 Catch:{ Throwable -> 0x00bd }
        if (r6 == 0) goto L_0x00ad;
    L_0x0047:
        r2 = "extype";
        r3 = r6.getClass();	 Catch:{ Throwable -> 0x00bd }
        r3 = r3.getName();	 Catch:{ Throwable -> 0x00bd }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x00bd }
        r2 = "ext";
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00bd }
        r3.<init>();	 Catch:{ Throwable -> 0x00bd }
        r3 = r3.append(r5);	 Catch:{ Throwable -> 0x00bd }
        r4 = "\r";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00bd }
        r4 = r6.getMessage();	 Catch:{ Throwable -> 0x00bd }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00bd }
        r4 = "\r";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00bd }
        r4 = r6.getStackTrace();	 Catch:{ Throwable -> 0x00bd }
        r4 = java.util.Arrays.toString(r4);	 Catch:{ Throwable -> 0x00bd }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x00bd }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x00bd }
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x00bd }
    L_0x008a:
        r2 = new org.json.JSONObject;	 Catch:{ Throwable -> 0x00bd }
        r2.<init>(r1);	 Catch:{ Throwable -> 0x00bd }
        r1 = "biz";
        r0.put(r1, r2);	 Catch:{ Throwable -> 0x00bd }
        r1 = new com.qq.e.comm.net.rr.S2SSRequest;	 Catch:{ Throwable -> 0x00bd }
        r2 = "http://sdk.e.qq.com/err";
        r0 = r0.toString();	 Catch:{ Throwable -> 0x00bd }
        r0 = r0.getBytes();	 Catch:{ Throwable -> 0x00bd }
        r1.<init>(r2, r0);	 Catch:{ Throwable -> 0x00bd }
        r0 = com.qq.e.comm.net.NetworkClientImpl.getInstance();	 Catch:{ Throwable -> 0x00bd }
        r0.submit(r1);	 Catch:{ Throwable -> 0x00bd }
    L_0x00ac:
        return;
    L_0x00ad:
        r2 = "extype";
        r3 = "";
        r1.put(r2, r3);	 Catch:{ Throwable -> 0x00bd }
        r2 = "ex";
        r1.put(r2, r5);	 Catch:{ Throwable -> 0x00bd }
        goto L_0x008a;
    L_0x00bd:
        r0 = move-exception;
        r1 = "Exception While build s2ss error report req";
        com.qq.e.comm.util.GDTLogger.w(r1, r0);
        goto L_0x00ac;
    L_0x00c5:
        r0 = "Report Not Work while  ADManager  not Inited";
        com.qq.e.comm.util.GDTLogger.w(r0);
        goto L_0x00ac;
        */
    }

    public final void a(Context context, SM sm, PM pm, DeviceStatus deviceStatus, APPStatus aPPStatus, long j) {
        if (!this.b.booleanValue()) {
            synchronized (this.b) {
                if (this.b.booleanValue()) {
                    return;
                }
                String a = a(sm, pm, deviceStatus, aPPStatus, context, j);
                String str = "http://sdk.e.qq.com/activate";
                if (!StringUtil.isEmpty(sm.getSuid())) {
                    str = "http://sdk.e.qq.com/launch";
                }
                NetworkCallBack anonymousClass_1 = new AnonymousClass_1(sm, pm, System.currentTimeMillis());
                NetworkClientImpl.getInstance().submit(new S2SSRequest(str, a.getBytes()), Priority.High, anonymousClass_1);
                this.b = Boolean.valueOf(true);
            }
        }
    }
}
