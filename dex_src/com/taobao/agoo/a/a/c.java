package com.taobao.agoo.a.a;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.d.a;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;

// compiled from: Taobao
public class c extends b {
    public static final String JSON_CMD_REGISTER = "register";
    public String a;
    public String b;
    public String c;
    public String d;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;

    public c() {
        this.d = "212";
    }

    public byte[] a() {
        try {
            ALog.i("RegisterDO", "buildData", SocializeConstants.JSON_DATA, new a().a(b.JSON_CMD, this.e).a(Constants.KEY_APP_KEY, this.a).a(MsgConstant.KEY_UTDID, this.b).a(Constants.KEY_APP_VERSION, this.c).a(Constants.KEY_SDK_VERSION, this.d).a(Constants.KEY_TTID, this.f).a(JsInterface.KEY_APK_NAME, this.g).a("c0", this.h).a("c1", this.i).a("c2", this.j).a(StatKeys.KEY_CRASH, this.k).a("c4", this.l).a("c5", this.m).a("c6", this.n).a().toString());
            return new a().a(b.JSON_CMD, this.e).a(Constants.KEY_APP_KEY, this.a).a(MsgConstant.KEY_UTDID, this.b).a(Constants.KEY_APP_VERSION, this.c).a(Constants.KEY_SDK_VERSION, this.d).a(Constants.KEY_TTID, this.f).a(JsInterface.KEY_APK_NAME, this.g).a("c0", this.h).a("c1", this.i).a("c2", this.j).a(StatKeys.KEY_CRASH, this.k).a("c4", this.l).a("c5", this.m).a("c6", this.n).a().toString().getBytes("utf-8");
        } catch (Throwable th) {
            ALog.e("RegisterDO", "buildData", th, new Object[0]);
            return null;
        }
    }

    public static byte[] a(Context context, String str, String str2) {
        c cVar;
        byte[] a;
        Throwable th;
        c cVar2 = null;
        try {
            Object deviceId = UtilityImpl.getDeviceId(context);
            String packageName = context.getPackageName();
            Object obj = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(deviceId) || TextUtils.isEmpty(obj)) {
                ALog.e("RegisterDO", "buildRegister param null", Constants.KEY_APP_KEY, str, MsgConstant.KEY_UTDID, deviceId, Constants.KEY_APP_VERSION, obj);
                return null;
            }
            cVar = new c();
            try {
                String subscriberId;
                cVar.e = JSON_CMD_REGISTER;
                cVar.a = str;
                cVar.b = deviceId;
                cVar.c = obj;
                cVar.f = str2;
                cVar.g = packageName;
                cVar.h = Build.BRAND;
                cVar.i = Build.MODEL;
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                cVar.j = telephonyManager != null ? telephonyManager.getDeviceId() : null;
                if (telephonyManager != null) {
                    subscriberId = telephonyManager.getSubscriberId();
                } else {
                    subscriberId = null;
                }
                cVar.k = subscriberId;
                a = cVar.a();
            } catch (Throwable th2) {
                th = th2;
                ALog.e("RegisterDO", "buildRegister", th, new Object[0]);
                a = cVar == null ? null : cVar.a();
                return a;
            }
            return a;
        } catch (Throwable th3) {
            th = th3;
            if (cVar2 != null) {
                cVar2.a();
            }
            throw th;
        }
    }
}
