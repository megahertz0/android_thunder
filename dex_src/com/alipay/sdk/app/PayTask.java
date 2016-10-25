package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.data.c;
import com.alipay.sdk.packet.impl.d;
import com.alipay.sdk.sys.b;
import com.alipay.sdk.util.H5PayResultModel;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.i;
import com.alipay.sdk.util.k;
import com.qq.e.comm.constants.Constants.KEYS;
import com.taobao.accs.common.Constants;
import com.tencent.open.GameAppOperation;
import com.tencent.open.SocialConstants;
import com.xunlei.download.Downloads.Impl;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class PayTask {
    static final Object a;
    private Activity b;
    private com.alipay.sdk.widget.a c;
    private String d;
    private String e;
    private String f;
    private Map<String, a> g;

    private class a {
        String a;
        String b;

        private a() {
            this.a = com.umeng.a.d;
            this.b = com.umeng.a.d;
        }

        private String a() {
            return this.a;
        }

        private void a(String str) {
            this.a = str;
        }

        private String b() {
            return this.b;
        }

        private void b(String str) {
            this.b = str;
        }
    }

    static {
        a = e.class;
    }

    public PayTask(Activity activity) {
        this.d = "wappaygw.alipay.com/service/rest.htm";
        this.e = "mclient.alipay.com/service/rest.htm";
        this.f = "mclient.alipay.com/home/exterfaceAssign.htm";
        this.g = new HashMap();
        this.b = activity;
        b a = b.a();
        Context context = this.b;
        c.a();
        a.a(context);
        com.alipay.sdk.app.statistic.a.a(activity);
        this.c = new com.alipay.sdk.widget.a(activity, com.alipay.sdk.widget.a.b);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String pay(java.lang.String r11, boolean r12) {
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.pay(java.lang.String, boolean):java.lang.String");
        /*
        this = this;
        r3 = 0;
        r0 = 0;
        monitor-enter(r10);
        if (r12 == 0) goto L_0x0008;
    L_0x0005:
        r10.b();	 Catch:{ all -> 0x0118 }
    L_0x0008:
        r1 = new com.alipay.sdk.sys.a;	 Catch:{ Throwable -> 0x0100 }
        r2 = r10.b;	 Catch:{ Throwable -> 0x0100 }
        r1.<init>(r2);	 Catch:{ Throwable -> 0x0100 }
        r2 = r1.a(r11);	 Catch:{ Throwable -> 0x0100 }
        r1 = "paymethod=\"expressGateway\"";
        r1 = r2.contains(r1);	 Catch:{ Throwable -> 0x0100 }
        if (r1 != 0) goto L_0x0072;
    L_0x001c:
        r1 = r10.b;	 Catch:{ Throwable -> 0x0100 }
        r1 = com.alipay.sdk.util.k.b(r1);	 Catch:{ Throwable -> 0x0100 }
        if (r1 == 0) goto L_0x0072;
    L_0x0024:
        r4 = new com.alipay.sdk.util.e;	 Catch:{ Throwable -> 0x0100 }
        r1 = r10.b;	 Catch:{ Throwable -> 0x0100 }
        r5 = new com.alipay.sdk.app.g;	 Catch:{ Throwable -> 0x0100 }
        r5.<init>(r10);	 Catch:{ Throwable -> 0x0100 }
        r4.<init>(r1, r5);	 Catch:{ Throwable -> 0x0100 }
        r1 = r4.a(r2);	 Catch:{ Throwable -> 0x0100 }
        r5 = 0;
        r4.a = r5;	 Catch:{ Throwable -> 0x0100 }
        r4 = "failed";
        r4 = android.text.TextUtils.equals(r1, r4);	 Catch:{ Throwable -> 0x0100 }
        if (r4 != 0) goto L_0x0072;
    L_0x0040:
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Throwable -> 0x0100 }
        if (r2 == 0) goto L_0x004a;
    L_0x0046:
        r1 = com.alipay.sdk.app.h.a();	 Catch:{ Throwable -> 0x0100 }
    L_0x004a:
        r5 = r10.b;	 Catch:{ Throwable -> 0x0100 }
        r2 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Throwable -> 0x00f4 }
        if (r2 == 0) goto L_0x0077;
    L_0x0052:
        r2 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Throwable -> 0x00f4 }
        if (r2 != 0) goto L_0x005e;
    L_0x0058:
        r2 = "pref_trade_token";
        com.alipay.sdk.util.i.a(r5, r2, r0);	 Catch:{ Throwable -> 0x00f4 }
    L_0x005e:
        r0 = com.alipay.sdk.data.a.b();	 Catch:{ all -> 0x0118 }
        r2 = r10.b;	 Catch:{ all -> 0x0118 }
        r0.a(r2);	 Catch:{ all -> 0x0118 }
        r10.c();	 Catch:{ all -> 0x0118 }
        r0 = r10.b;	 Catch:{ all -> 0x0118 }
        com.alipay.sdk.app.statistic.a.a(r0, r11);	 Catch:{ all -> 0x0118 }
        r0 = r1;
    L_0x0070:
        monitor-exit(r10);
        return r0;
    L_0x0072:
        r1 = r10.b(r2);	 Catch:{ Throwable -> 0x0100 }
        goto L_0x004a;
    L_0x0077:
        r2 = ";";
        r6 = r1.split(r2);	 Catch:{ Throwable -> 0x00f4 }
        r4 = r3;
    L_0x007f:
        r2 = r6.length;	 Catch:{ Throwable -> 0x00f4 }
        if (r4 >= r2) goto L_0x0052;
    L_0x0082:
        r2 = r6[r4];	 Catch:{ Throwable -> 0x00f4 }
        r7 = "result={";
        r2 = r2.startsWith(r7);	 Catch:{ Throwable -> 0x00f4 }
        if (r2 == 0) goto L_0x00d9;
    L_0x008d:
        r2 = r6[r4];	 Catch:{ Throwable -> 0x00f4 }
        r7 = "}";
        r2 = r2.endsWith(r7);	 Catch:{ Throwable -> 0x00f4 }
        if (r2 == 0) goto L_0x00d9;
    L_0x0098:
        r2 = r6[r4];	 Catch:{ Throwable -> 0x00f4 }
        r7 = 8;
        r8 = r6[r4];	 Catch:{ Throwable -> 0x00f4 }
        r8 = r8.length();	 Catch:{ Throwable -> 0x00f4 }
        r8 = r8 + -1;
        r2 = r2.substring(r7, r8);	 Catch:{ Throwable -> 0x00f4 }
        r7 = "&";
        r7 = r2.split(r7);	 Catch:{ Throwable -> 0x00f4 }
        r2 = r3;
    L_0x00b0:
        r8 = r7.length;	 Catch:{ Throwable -> 0x00f4 }
        if (r2 >= r8) goto L_0x00d9;
    L_0x00b3:
        r8 = r7[r2];	 Catch:{ Throwable -> 0x00f4 }
        r9 = "trade_token=\"";
        r8 = r8.startsWith(r9);	 Catch:{ Throwable -> 0x00f4 }
        if (r8 == 0) goto L_0x00dd;
    L_0x00be:
        r8 = r7[r2];	 Catch:{ Throwable -> 0x00f4 }
        r9 = "\"";
        r8 = r8.endsWith(r9);	 Catch:{ Throwable -> 0x00f4 }
        if (r8 == 0) goto L_0x00dd;
    L_0x00c9:
        r0 = r7[r2];	 Catch:{ Throwable -> 0x00f4 }
        r8 = 13;
        r2 = r7[r2];	 Catch:{ Throwable -> 0x00f4 }
        r2 = r2.length();	 Catch:{ Throwable -> 0x00f4 }
        r2 = r2 + -1;
        r0 = r0.substring(r8, r2);	 Catch:{ Throwable -> 0x00f4 }
    L_0x00d9:
        r2 = r4 + 1;
        r4 = r2;
        goto L_0x007f;
    L_0x00dd:
        r8 = r7[r2];	 Catch:{ Throwable -> 0x00f4 }
        r9 = "trade_token=";
        r8 = r8.startsWith(r9);	 Catch:{ Throwable -> 0x00f4 }
        if (r8 == 0) goto L_0x00f1;
    L_0x00e8:
        r0 = r7[r2];	 Catch:{ Throwable -> 0x00f4 }
        r2 = 12;
        r0 = r0.substring(r2);	 Catch:{ Throwable -> 0x00f4 }
        goto L_0x00d9;
    L_0x00f1:
        r2 = r2 + 1;
        goto L_0x00b0;
    L_0x00f4:
        r0 = move-exception;
        r2 = "biz";
        r3 = "SaveTradeTokenError";
        com.alipay.sdk.app.statistic.a.a(r2, r3, r0);	 Catch:{ Throwable -> 0x0100 }
        goto L_0x005e;
    L_0x0100:
        r0 = move-exception;
        r0 = com.alipay.sdk.app.h.a();	 Catch:{ all -> 0x011b }
        r1 = com.alipay.sdk.data.a.b();	 Catch:{ all -> 0x0118 }
        r2 = r10.b;	 Catch:{ all -> 0x0118 }
        r1.a(r2);	 Catch:{ all -> 0x0118 }
        r10.c();	 Catch:{ all -> 0x0118 }
        r1 = r10.b;	 Catch:{ all -> 0x0118 }
        com.alipay.sdk.app.statistic.a.a(r1, r11);	 Catch:{ all -> 0x0118 }
        goto L_0x0070;
    L_0x0118:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x011b:
        r0 = move-exception;
        r1 = com.alipay.sdk.data.a.b();	 Catch:{ all -> 0x0118 }
        r2 = r10.b;	 Catch:{ all -> 0x0118 }
        r1.a(r2);	 Catch:{ all -> 0x0118 }
        r10.c();	 Catch:{ all -> 0x0118 }
        r1 = r10.b;	 Catch:{ all -> 0x0118 }
        com.alipay.sdk.app.statistic.a.a(r1, r11);	 Catch:{ all -> 0x0118 }
        throw r0;	 Catch:{ all -> 0x0118 }
        */
    }

    public synchronized String fetchTradeToken() {
        return i.b(this.b, h.a, com.umeng.a.d);
    }

    public synchronized String fetchOrderInfoFromH5PayUrl(String str) {
        String trim;
        try {
            if (!TextUtils.isEmpty(str)) {
                String trim2;
                trim = str.trim();
                if (trim.startsWith(new StringBuilder("https://").append(this.d).toString()) || trim.startsWith(new StringBuilder("http://").append(this.d).toString())) {
                    trim2 = trim.replaceFirst(new StringBuilder("(http|https)://").append(this.d).append("\\?").toString(), com.umeng.a.d).trim();
                    if (!TextUtils.isEmpty(trim2)) {
                        trim = new StringBuilder("_input_charset=\"utf-8\"&ordertoken=\"").append(k.a("<request_token>", "</request_token>", (String) k.a(trim2).get("req_data"))).append("\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"").append(new com.alipay.sdk.sys.a(this.b).a("sc", "h5tonative")).append(h.f).toString();
                    }
                }
                if (trim.startsWith(new StringBuilder("https://").append(this.e).toString()) || trim.startsWith(new StringBuilder("http://").append(this.e).toString())) {
                    trim2 = trim.replaceFirst(new StringBuilder("(http|https)://").append(this.e).append("\\?").toString(), com.umeng.a.d).trim();
                    if (!TextUtils.isEmpty(trim2)) {
                        trim = new StringBuilder("_input_charset=\"utf-8\"&ordertoken=\"").append(k.a("<request_token>", "</request_token>", (String) k.a(trim2).get("req_data"))).append("\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"").append(new com.alipay.sdk.sys.a(this.b).a("sc", "h5tonative")).append(h.f).toString();
                    }
                }
                if ((trim.startsWith(new StringBuilder("https://").append(this.f).toString()) || trim.startsWith(new StringBuilder("http://").append(this.f).toString())) && trim.contains("alipay.wap.create.direct.pay.by.user") && !TextUtils.isEmpty(trim.replaceFirst(new StringBuilder("(http|https)://").append(this.f).append("\\?").toString(), com.umeng.a.d).trim())) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(SocialConstants.PARAM_URL, str);
                        jSONObject.put("bizcontext", new com.alipay.sdk.sys.a(this.b).a("sc", "h5tonative"));
                        trim = new StringBuilder("new_external_info==").append(jSONObject.toString()).toString();
                    } catch (Throwable th) {
                    }
                }
                if (Pattern.compile("^(http|https)://(maliprod\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mali\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mclient\\.alipay\\.com\\/w\\/trade_pay\\.do.?)").matcher(str).find()) {
                    trim = k.a("?", com.umeng.a.d, str);
                    if (!TextUtils.isEmpty(trim)) {
                        Map a = k.a(trim);
                        StringBuilder stringBuilder = new StringBuilder();
                        if (a(false, true, com.alipay.sdk.app.statistic.c.F, stringBuilder, a, com.alipay.sdk.app.statistic.c.F, "alipay_trade_no")) {
                            a(true, false, "pay_phase_id", stringBuilder, a, "payPhaseId", "pay_phase_id", "out_relation_id");
                            stringBuilder.append("&biz_sub_type=\"TRADE\"");
                            stringBuilder.append("&biz_type=\"trade\"");
                            trim = (String) a.get(GameAppOperation.QQFAV_DATALINE_APPNAME);
                            if (TextUtils.isEmpty(trim) && !TextUtils.isEmpty((CharSequence) a.get(Impl.COLUMN_CID))) {
                                trim = "ali1688";
                            } else if (TextUtils.isEmpty(trim)) {
                                if (!(TextUtils.isEmpty((CharSequence) a.get(Constants.KEY_SID)) && TextUtils.isEmpty((CharSequence) a.get("s_id")))) {
                                    trim = "tb";
                                }
                            }
                            stringBuilder.append(new StringBuilder("&app_name=\"").append(trim).append(h.f).toString());
                            if (a(true, true, "extern_token", stringBuilder, a, "extern_token", Impl.COLUMN_CID, Constants.KEY_SID, "s_id")) {
                                a(true, false, "appenv", stringBuilder, a, "appenv");
                                stringBuilder.append("&pay_channel_id=\"alipay_sdk\"");
                                a aVar = new a();
                                aVar.a = (String) a.get("return_url");
                                aVar.b = (String) a.get("pay_order_id");
                                trim = stringBuilder.toString() + "&bizcontext=\"" + new com.alipay.sdk.sys.a(this.b).a("sc", "h5tonative") + h.f;
                                this.g.put(trim, aVar);
                            } else {
                                trim = com.umeng.a.d;
                            }
                        }
                    }
                }
            }
        } catch (Throwable th2) {
        }
        trim = com.umeng.a.d;
        return trim;
    }

    private static boolean a(boolean z, boolean z2, String str, StringBuilder stringBuilder, Map<String, String> map, String... strArr) {
        String str2;
        String str3 = com.umeng.a.d;
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = strArr[i];
            if (!TextUtils.isEmpty((CharSequence) map.get(obj))) {
                str2 = (String) map.get(obj);
                break;
            }
        }
        str2 = str3;
        if (TextUtils.isEmpty(str2)) {
            if (z2) {
                return false;
            }
        } else if (z) {
            stringBuilder.append(com.alipay.sdk.sys.a.b).append(str).append("=\"").append(str2).append(h.f);
        } else {
            stringBuilder.append(str).append("=\"").append(str2).append(h.f);
        }
        return true;
    }

    public synchronized H5PayResultModel h5Pay(String str, boolean z) {
        H5PayResultModel h5PayResultModel;
        synchronized (this) {
            H5PayResultModel h5PayResultModel2 = new H5PayResultModel();
            try {
                str.trim();
                String[] split = pay(str, z).split(h.b);
                Map hashMap = new HashMap();
                for (String str2 : split) {
                    String substring = str2.substring(0, str2.indexOf("={"));
                    String str3 = substring + "={";
                    hashMap.put(substring, str2.substring(str3.length() + str2.indexOf(str3), str2.lastIndexOf(h.d)));
                }
                if (hashMap.containsKey("resultStatus")) {
                    h5PayResultModel2.setResultCode((String) hashMap.get("resultStatus"));
                }
                if (hashMap.containsKey("callBackUrl")) {
                    h5PayResultModel2.setReturnUrl((String) hashMap.get("callBackUrl"));
                } else if (hashMap.containsKey("result")) {
                    String str4;
                    try {
                        str4 = (String) hashMap.get("result");
                    } catch (Throwable th) {
                    }
                    if (str4.length() > 15) {
                        a aVar = (a) this.g.get(str);
                        if (aVar != null) {
                            if (TextUtils.isEmpty(aVar.b)) {
                                h5PayResultModel2.setReturnUrl(aVar.a);
                            } else {
                                h5PayResultModel2.setReturnUrl(com.alipay.sdk.data.a.b().j.replace("$OrderId$", aVar.b));
                            }
                            this.g.remove(str);
                            h5PayResultModel = h5PayResultModel2;
                        } else {
                            CharSequence a = k.a("&callBackUrl=\"", h.f, str4);
                            if (TextUtils.isEmpty(a)) {
                                a = k.a("&call_back_url=\"", h.f, str4);
                                if (TextUtils.isEmpty(a)) {
                                    a = k.a("&return_url=\"", h.f, str4);
                                    if (TextUtils.isEmpty(a)) {
                                        a = URLDecoder.decode(k.a(com.alipay.sdk.cons.a.o, com.alipay.sdk.sys.a.b, str4), "utf-8");
                                        if (TextUtils.isEmpty(a)) {
                                            str4 = URLDecoder.decode(k.a("&callBackUrl=", com.alipay.sdk.sys.a.b, str4), "utf-8");
                                            if (TextUtils.isEmpty(str4)) {
                                                str4 = com.alipay.sdk.data.a.b().j;
                                            }
                                            h5PayResultModel2.setReturnUrl(URLDecoder.decode(str4, "utf-8"));
                                        }
                                    }
                                }
                            }
                            CharSequence charSequence = a;
                            if (TextUtils.isEmpty(str4)) {
                                str4 = com.alipay.sdk.data.a.b().j;
                            }
                            h5PayResultModel2.setReturnUrl(URLDecoder.decode(str4, "utf-8"));
                        }
                    } else {
                        a aVar2 = (a) this.g.get(str);
                        if (aVar2 != null) {
                            h5PayResultModel2.setReturnUrl(aVar2.a);
                            this.g.remove(str);
                            h5PayResultModel = h5PayResultModel2;
                        }
                    }
                }
            } catch (Throwable th2) {
            }
            h5PayResultModel = h5PayResultModel2;
        }
        return h5PayResultModel;
    }

    private static String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str3.length() + str.indexOf(str3), str.lastIndexOf(h.d));
    }

    private com.alipay.sdk.util.e.a a() {
        return new g(this);
    }

    private void b() {
        if (this.c != null) {
            this.c.a();
        }
    }

    private void c() {
        if (this.c != null) {
            this.c.b();
        }
    }

    private String a(String str) {
        String a = new com.alipay.sdk.sys.a(this.b).a(str);
        if (a.contains("paymethod=\"expressGateway\"")) {
            return b(a);
        }
        if (!k.b(this.b)) {
            return b(a);
        }
        e eVar = new e(this.b, new g(this));
        String a2 = eVar.a(a);
        eVar.a = null;
        if (TextUtils.equals(a2, e.b)) {
            return b(a);
        }
        return TextUtils.isEmpty(a2) ? h.a() : a2;
    }

    public String getVersion() {
        return com.alipay.sdk.cons.a.e;
    }

    private String b(String str) {
        i iVar;
        int i = 0;
        b();
        try {
            List a = com.alipay.sdk.protocol.b.a(new d().a(this.b, str).a().optJSONObject(com.alipay.sdk.cons.c.c).optJSONObject(com.alipay.sdk.cons.c.d));
            for (int i2 = 0; i2 < a.size(); i2++) {
                if (((com.alipay.sdk.protocol.b) a.get(i2)).a == com.alipay.sdk.protocol.a.c) {
                    String[] strArr = ((com.alipay.sdk.protocol.b) a.get(i2)).b;
                    if (strArr.length == 3 && TextUtils.equals(com.alipay.sdk.cons.b.c, strArr[0])) {
                        Context context = b.a().a;
                        com.alipay.sdk.tid.b a2 = com.alipay.sdk.tid.b.a();
                        if (!TextUtils.isEmpty(strArr[1]) && !TextUtils.isEmpty(strArr[2])) {
                            a2.a = strArr[1];
                            a2.b = strArr[2];
                            com.alipay.sdk.tid.a aVar = new com.alipay.sdk.tid.a(context);
                            aVar.a(com.alipay.sdk.util.a.a(context).a(), com.alipay.sdk.util.a.a(context).b(), a2.a, a2.b);
                            aVar.close();
                        }
                    }
                }
            }
            c();
            while (i < a.size()) {
                if (((com.alipay.sdk.protocol.b) a.get(i)).a == com.alipay.sdk.protocol.a.b) {
                    String a3 = a((com.alipay.sdk.protocol.b) a.get(i));
                    c();
                    return a3;
                }
                i++;
            }
            c();
            iVar = null;
        } catch (Exception e) {
            aVar.close();
        } catch (Throwable e2) {
            i a4 = i.a(i.d.h);
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.a, e2);
            c();
            iVar = a4;
        } catch (Throwable e22) {
            com.alipay.sdk.app.statistic.a.a(KEYS.BIZ, com.alipay.sdk.app.statistic.c.q, e22);
            c();
            iVar = null;
        }
        if (iVar == null) {
            iVar = i.a(i.b.h);
        }
        return h.a(iVar.h, iVar.i, com.umeng.a.d);
    }

    private String a(com.alipay.sdk.protocol.b bVar) {
        String[] strArr = bVar.b;
        Intent intent = new Intent(this.b, H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(SocialConstants.PARAM_URL, strArr[0]);
        if (strArr.length == 2) {
            bundle.putString("cookie", strArr[1]);
        }
        intent.putExtras(bundle);
        this.b.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException e) {
                return h.a();
            }
        }
        String str = h.a;
        return TextUtils.isEmpty(str) ? h.a() : str;
    }
}
