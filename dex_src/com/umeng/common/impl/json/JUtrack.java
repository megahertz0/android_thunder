package com.umeng.common.impl.json;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.umeng.common.UmLog;
import com.umeng.common.inter.ITagManager;
import com.umeng.common.inter.IUtrack;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import com.umeng.message.UTrack$ICallBack;
import com.umeng.message.proguard.j;
import com.umeng.message.proguard.k;
import com.umeng.message.provider.a;
import com.umeng.message.util.HttpRequest;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.analytics.b.c;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.agoo.common.AgooConstants;
import org.apache.commons.logging.impl.SimpleLog;
import org.json.JSONObject;

public class JUtrack implements IUtrack {
    private static final String a;
    private Context b;

    static {
        a = JUtrack.class.getSimpleName();
    }

    public JUtrack(Context context) {
        this.b = context;
    }

    public void sendMsgLog(JSONObject jSONObject, String str, int i, long j, boolean z) throws Exception {
        JSONObject sendRequest;
        if (z) {
            sendRequest = sendRequest(jSONObject, MsgConstant.LOG_ENDPOINT);
        } else {
            sendRequest = sendRequest(jSONObject, MsgConstant.LOG_ENDPOINT.replace("https", "http"));
        }
        if (sendRequest != null && TextUtils.equals(sendRequest.getString("success"), ITagManager.SUCCESS)) {
            k.a(this.b).a(str, i);
            if (i != 0) {
                k.a(this.b).b(str);
            }
        }
    }

    public void trackAppLaunch(JSONObject jSONObject, boolean z) throws Exception {
        JSONObject sendRequest;
        if (z) {
            sendRequest = sendRequest(jSONObject, MsgConstant.LAUNCH_ENDPOINT);
        } else {
            sendRequest = sendRequest(jSONObject, MsgConstant.LAUNCH_ENDPOINT.replace("https", "http"));
        }
        if (sendRequest != null && TextUtils.equals(sendRequest.getString("success"), ITagManager.SUCCESS)) {
            k.a(this.b).a(System.currentTimeMillis());
            int i = sendRequest.getInt("launch_policy");
            UmLog.d(a, new StringBuilder("launch_policy:").append(i).toString());
            int i2 = sendRequest.getInt("tag_policy");
            UmLog.d(a, new StringBuilder("tag_policy:").append(i2).toString());
            if (i > 0) {
                MessageSharedPrefs.getInstance(this.b).setAppLaunchLogSendPolicy(i);
            }
            if (i2 > 0) {
                MessageSharedPrefs.getInstance(this.b).setTagSendPolicy(i2);
            }
        }
    }

    public void trackRegister(JSONObject jSONObject, boolean z) throws Exception {
        JSONObject sendRequest;
        if (z) {
            sendRequest = sendRequest(jSONObject, MsgConstant.REGISTER_ENDPOINT);
        } else {
            sendRequest = sendRequest(jSONObject, MsgConstant.REGISTER_ENDPOINT.replace("https", "http"));
        }
        if (sendRequest != null && TextUtils.equals(sendRequest.getString("success"), ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).setHasResgister(true);
            if (TextUtils.isEmpty(MessageSharedPrefs.getInstance(this.b).getDeviceToken())) {
                UmLog.e(a, "setRegisteredToUmeng: empty registration id");
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void trackLocation(org.json.JSONObject r4, boolean r5) {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.common.impl.json.JUtrack.trackLocation(org.json.JSONObject, boolean):void");
        /*
        this = this;
        if (r5 == 0) goto L_0x0023;
    L_0x0002:
        r0 = com.umeng.message.MsgConstant.LBS_ENDPOINT;	 Catch:{ Exception -> 0x0034 }
        r0 = sendRequest(r4, r0);	 Catch:{ Exception -> 0x0034 }
    L_0x0008:
        if (r0 == 0) goto L_0x0022;
    L_0x000a:
        r1 = "success";
        r0 = r0.getString(r1);	 Catch:{ Exception -> 0x0034 }
        r1 = "ok";
        r0 = android.text.TextUtils.equals(r0, r1);	 Catch:{ Exception -> 0x0034 }
        if (r0 == 0) goto L_0x0022;
    L_0x001a:
        r0 = a;	 Catch:{ Exception -> 0x0034 }
        r1 = "location track success";
        com.umeng.common.UmLog.d(r0, r1);	 Catch:{ Exception -> 0x0034 }
    L_0x0022:
        return;
    L_0x0023:
        r0 = com.umeng.message.MsgConstant.LBS_ENDPOINT;	 Catch:{ Exception -> 0x0034 }
        r1 = "https";
        r2 = "http";
        r0 = r0.replace(r1, r2);	 Catch:{ Exception -> 0x0034 }
        r0 = sendRequest(r4, r0);	 Catch:{ Exception -> 0x0034 }
        goto L_0x0008;
    L_0x0034:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0022;
        */
    }

    public void addAlias(String str, String str2, JSONObject jSONObject, UTrack$ICallBack uTrack$ICallBack, boolean z) throws Exception {
        String optString = jSONObject.optString(ITagManager.FAIL, BuildConfig.VERSION_NAME);
        String optString2 = jSONObject.optString("success", BuildConfig.VERSION_NAME);
        UmLog.i(a, new StringBuilder("keyfail:").append(optString).append(",keysuccess:").append(optString2).toString());
        if (optString.equals(BuildConfig.VERSION_NAME) && optString2.equals(BuildConfig.VERSION_NAME)) {
            JSONObject sendRequest;
            if (z) {
                sendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_ENDPOINT);
            } else {
                sendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_ENDPOINT.replace("https", "http"));
            }
            if (sendRequest == null || !TextUtils.equals(sendRequest.optString("success", BuildConfig.VERSION_NAME), ITagManager.SUCCESS)) {
                MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, 1, new StringBuilder("\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25alias:").append(str).append(",type:").append(str2).append(",devicetoken:").append(MessageSharedPrefs.getInstance(this.b).getDeviceToken()).toString());
                uTrack$ICallBack.onMessage(false, new StringBuilder("alias:").append(str).append("\u6dfb\u52a0\u5931\u8d25").toString());
                return;
            }
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, 0, BuildConfig.VERSION_NAME);
            uTrack$ICallBack.onMessage(true, new StringBuilder("alias:").append(str).append("\u6dfb\u52a0\u6210\u529f").toString());
            return;
        }
        if (!optString.equals(BuildConfig.VERSION_NAME)) {
            uTrack$ICallBack.onMessage(false, new StringBuilder("alias:").append(str).append("\u6dfb\u52a0\u5931\u8d25").toString());
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, 1, optString);
        }
        if (!optString2.equals(BuildConfig.VERSION_NAME)) {
            uTrack$ICallBack.onMessage(true, new StringBuilder("alias:").append(str).append("\u5df2\u7ecf\u6dfb\u52a0").toString());
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, SimpleLog.LOG_LEVEL_DEBUG, optString2);
        }
    }

    public void addExclusiveAlias(String str, String str2, JSONObject jSONObject, UTrack$ICallBack uTrack$ICallBack, boolean z) throws Exception {
        String optString = jSONObject.optString(ITagManager.FAIL, BuildConfig.VERSION_NAME);
        String optString2 = jSONObject.optString("success", BuildConfig.VERSION_NAME);
        if (optString.equals(BuildConfig.VERSION_NAME) && optString2.equals(BuildConfig.VERSION_NAME)) {
            JSONObject sendRequest;
            if (z) {
                sendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_EXCLUSIVE_ENDPOINT);
            } else {
                sendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_EXCLUSIVE_ENDPOINT.replace("https", "http"));
            }
            if (sendRequest == null || !TextUtils.equals(sendRequest.optString("success", BuildConfig.VERSION_NAME), ITagManager.SUCCESS)) {
                MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, 1, new StringBuilder("\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25alias:").append(str).append(",type:").append(str2).append(",devicetoken:").append(MessageSharedPrefs.getInstance(this.b).getDeviceToken()).toString());
                uTrack$ICallBack.onMessage(false, new StringBuilder("alias:").append(str).append("\u6dfb\u52a0\u5931\u8d25").toString());
                return;
            }
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, 0, BuildConfig.VERSION_NAME);
            uTrack$ICallBack.onMessage(true, new StringBuilder("alias:").append(str).append("\u6dfb\u52a0\u6210\u529f").toString());
            return;
        }
        if (!optString.equals(BuildConfig.VERSION_NAME)) {
            uTrack$ICallBack.onMessage(false, new StringBuilder("alias:").append(str).append("\u6dfb\u52a0\u5931\u8d25").toString());
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, 1, optString);
        }
        if (!optString2.equals(BuildConfig.VERSION_NAME)) {
            uTrack$ICallBack.onMessage(true, new StringBuilder("alias:").append(str).append("\u5df2\u7ecf\u6dfb\u52a0").toString());
            MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, SimpleLog.LOG_LEVEL_DEBUG, optString2);
        }
    }

    public void removeAlias(String str, String str2, JSONObject jSONObject, UTrack$ICallBack uTrack$ICallBack, boolean z) throws Exception {
        JSONObject sendRequest;
        if (z) {
            sendRequest = sendRequest(jSONObject, MsgConstant.DELETE_ALIAS_ENDPOINT);
        } else {
            sendRequest = sendRequest(jSONObject, MsgConstant.DELETE_ALIAS_ENDPOINT.replace("https", "http"));
        }
        if (sendRequest != null && TextUtils.equals(sendRequest.getString("success"), ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).removeAlias(0, str, str2);
            uTrack$ICallBack.onMessage(true, new StringBuilder("alias:").append(str).append(",type:").append(str2).append("\u5220\u9664\u6210\u529f").toString());
        }
    }

    public static JSONObject sendRequest(JSONObject jSONObject, String str) throws Exception {
        String b = HttpRequest.c((CharSequence) str).H().r(HttpRequest.c).i(jSONObject.toString()).b(CharsetConvert.UTF_8);
        UmLog.d(a, new StringBuilder("sendRequest() url=").append(str).append("\n request = ").append(jSONObject).append("\n response = ").append(b).toString());
        return new JSONObject(b);
    }

    public void sendAliasFailLog(JSONObject jSONObject) {
        String[] strArr = new String[]{c.f};
        ContentResolver contentResolver = this.b.getContentResolver();
        a.a(this.b);
        Cursor query = contentResolver.query(a.d, new String[]{j.C, AgooConstants.MESSAGE_TIME}, "error=?", strArr, null);
        if (query != null) {
            query.moveToFirst();
            while (!query.isAfterLast()) {
                a(jSONObject, query.getString(query.getColumnIndex(j.C)), query.getLong(query.getColumnIndex(AgooConstants.MESSAGE_TIME)));
                query.moveToNext();
            }
            if (query != null) {
                query.close();
            }
        }
    }

    private void a(JSONObject jSONObject, String str, long j) {
        try {
            if (!str.equals(BuildConfig.VERSION_NAME)) {
                jSONObject.put("aliasFail", str);
                JSONObject sendRequest = sendRequest(jSONObject, MsgConstant.ALIAS_LOG);
                if (sendRequest != null && TextUtils.equals(sendRequest.optString("success", BuildConfig.VERSION_NAME), ITagManager.SUCCESS)) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, c.c);
                    String[] strArr = new String[]{String.valueOf(j)};
                    ContentResolver contentResolver = this.b.getContentResolver();
                    a.a(this.b);
                    contentResolver.update(a.d, contentValues, "time=?", strArr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
