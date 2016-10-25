package com.umeng.message;

import android.text.TextUtils;
import com.umeng.common.UmLog;
import com.umeng.common.UmengMessageDeviceConfig;
import com.umeng.common.inter.ITagManager;
import com.umeng.message.proguard.j;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;

class UTrack$3 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ UTrack$ICallBack c;
    final /* synthetic */ UTrack d;

    UTrack$3(UTrack uTrack, String str, String str2, UTrack$ICallBack uTrack$ICallBack) {
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
        if (TextUtils.isEmpty(this.a)) {
            UmLog.e(UTrack.a(), "removeAlias: empty type");
            str = str2 + "removeAlias: empty type";
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
        try {
            JSONObject b = UTrack.b(this.d);
            if (uTrack$SuccessState == null) {
                b.put(j.z, this.b);
                b.put(AgooConstants.MESSAGE_TYPE, this.a);
                b.put("ts", System.currentTimeMillis());
            } else if (uTrack$SuccessState == UTrack$SuccessState.d) {
                b.put(ITagManager.FAIL, obj);
            } else if (uTrack$SuccessState == UTrack$SuccessState.a) {
                b.put("success", str3);
            }
            UTrack.b().removeAlias(this.b, this.a, b, this.c, true);
        } catch (Exception e) {
            exception = e;
            b = null;
            if (exception.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                UTrack.b().removeAlias(this.b, this.a, b, this.c, false);
            } else {
                this.c.onMessage(false, new StringBuilder("alias:").append(this.b).append("\u79fb\u9664\u5931\u8d25:").append(exception.getMessage()).toString());
            }
        }
    }
}
