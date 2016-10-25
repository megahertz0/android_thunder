package com.umeng.message;

import android.text.TextUtils;
import com.umeng.common.UmLog;
import com.umeng.common.UmengMessageDeviceConfig;
import com.umeng.common.inter.ITagManager;
import com.umeng.message.proguard.j;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;

class UTrack$11 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ UTrack$ICallBack c;
    final /* synthetic */ UTrack d;

    UTrack$11(UTrack uTrack, String str, String str2, UTrack$ICallBack uTrack$ICallBack) {
        this.d = uTrack;
        this.a = str;
        this.b = str2;
        this.c = uTrack$ICallBack;
    }

    public void run() {
        String str;
        UTrack$SuccessState uTrack$SuccessState;
        Exception exception;
        String str2 = BuildConfig.VERSION_NAME;
        String str3 = BuildConfig.VERSION_NAME;
        str2 = str2 + "utdid:" + UmengMessageDeviceConfig.getUtdid(UTrack.a(this.d)) + ",deviceToken:" + MessageSharedPrefs.getInstance(UTrack.a(this.d)).getDeviceToken() + ";";
        if (TextUtils.isEmpty(this.a)) {
            UmLog.e(UTrack.a(), "addAlias: empty type");
            str = str2 + "addAlias: empty type;";
            uTrack$SuccessState = UTrack$SuccessState.d;
        } else {
            str = str2;
            uTrack$SuccessState = null;
        }
        if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(UTrack.a(this.d)))) {
            UmLog.e(UTrack.a(), "UTDID is empty");
            str = str + "UTDID is empty;";
            uTrack$SuccessState = UTrack$SuccessState.d;
        }
        if (TextUtils.isEmpty(MessageSharedPrefs.getInstance(UTrack.a(this.d)).getDeviceToken())) {
            UmLog.e(UTrack.a(), "RegistrationId is empty");
            Object obj = obj + "RegistrationId is empty;";
            uTrack$SuccessState = UTrack$SuccessState.d;
        }
        String str4;
        if (MessageSharedPrefs.getInstance(UTrack.a(this.d)).isAliasSet(0, this.b, this.a)) {
            UmLog.d(UTrack.a(), String.format("addAlias: <%s, %s> has been synced to the server before. Ingore this request.", new Object[]{this.b, this.a}));
            str3 = str3 + String.format("addAlias: <%s, %s> has been synced to the server before. Ingore this request;", new Object[]{this.b, this.a});
            uTrack$SuccessState = UTrack$SuccessState.a;
            str4 = str3;
        } else {
            str4 = str3;
        }
        try {
            JSONObject b = UTrack.b(this.d);
            if (uTrack$SuccessState == null) {
                b.put(j.z, this.b);
                b.put(AgooConstants.MESSAGE_TYPE, this.a);
                b.put("last_alias", MessageSharedPrefs.getInstance(UTrack.a(this.d)).getLastAlias(0, this.a));
                b.put("ts", System.currentTimeMillis());
            } else if (uTrack$SuccessState == UTrack$SuccessState.d) {
                b.put(ITagManager.FAIL, obj);
            } else if (uTrack$SuccessState == UTrack$SuccessState.a) {
                b.put("success", r4);
            }
            UTrack.b().addAlias(this.b, this.a, b, this.c, true);
        } catch (Exception e) {
            exception = e;
            b = null;
            if (exception.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                UTrack.b().addAlias(this.b, this.a, b, this.c, false);
            } else {
                this.c.onMessage(false, new StringBuilder("alias:").append(this.b).append("\u6dfb\u52a0\u5931\u8d25:").append(exception.getMessage()).toString());
            }
            MessageSharedPrefs.getInstance(UTrack.a(this.d)).addAlias(this.b, this.a, 0, 1, exception.getMessage());
        }
    }
}
