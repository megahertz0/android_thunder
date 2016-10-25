package com.alipay.sdk.packet;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import android.widget.TextView;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.data.c;
import com.alipay.sdk.net.a;
import com.alipay.sdk.sys.b;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.j;
import com.alipay.sdk.util.k;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.android.agoo.message.MessageService;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class d {
    public static final String a = "msp-gzip";
    public static final String b = "Msp-Param";
    public static final String c = "Operation-Type";
    public static final String d = "content-type";
    public static final String e = "Version";
    public static final String f = "AppId";
    public static final String g = "des-mode";
    public static final String h = "namespace";
    public static final String i = "api_name";
    public static final String j = "api_version";
    public static final String k = "data";
    public static final String l = "params";
    public static final String m = "public_key";
    public static final String n = "device";
    public static final String o = "action";
    public static final String p = "type";
    public static final String q = "method";
    private static a t;
    protected boolean r;
    protected boolean s;

    public abstract JSONObject a() throws JSONException;

    public d() {
        this.r = true;
        this.s = true;
    }

    public List<Header> a(boolean z, String str) {
        List<Header> arrayList = new ArrayList();
        arrayList.add(new BasicHeader(a, String.valueOf(z)));
        arrayList.add(new BasicHeader(c, "alipay.msp.cashier.dispatch.bytes"));
        arrayList.add(new BasicHeader(d, "application/octet-stream"));
        arrayList.add(new BasicHeader(e, SocializeConstants.PROTOCOL_VERSON));
        arrayList.add(new BasicHeader(f, "TAOBAO"));
        arrayList.add(new BasicHeader(b, a.a(str)));
        arrayList.add(new BasicHeader(g, "CBC"));
        return arrayList;
    }

    public String b() {
        return "4.9.0";
    }

    public String c() throws JSONException {
        HashMap hashMap = new HashMap();
        hashMap.put(n, Build.MODEL);
        hashMap.put(h, "com.alipay.mobilecashier");
        hashMap.put(i, "com.alipay.mcpay");
        hashMap.put(j, b());
        return a(hashMap, new HashMap());
    }

    public static JSONObject a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(p, str);
        jSONObject2.put(q, str2);
        jSONObject.put(o, jSONObject2);
        return jSONObject;
    }

    public String a(String str, JSONObject jSONObject) {
        b a = b.a();
        com.alipay.sdk.tid.b a2 = com.alipay.sdk.tid.b.a();
        JSONObject a3 = com.alipay.sdk.util.b.a(new JSONObject(), jSONObject);
        try {
            String a4;
            String b;
            String e;
            String a5;
            a3.put(com.alipay.sdk.cons.b.c, a2.a);
            String str2 = com.alipay.sdk.cons.b.b;
            c a6 = c.a();
            Context context = b.a().a;
            com.alipay.sdk.util.a a7 = com.alipay.sdk.util.a.a(context);
            if (TextUtils.isEmpty(a6.a)) {
                a4 = k.a();
                b = k.b();
                e = k.e(context);
                a5 = j.a(context);
                a6.a = "Msp/15.2.0" + " (" + a4 + h.b + b + h.b + e + h.b + a5.substring(0, a5.indexOf(HttpConstant.SCHEME_SPLIT)) + h.b + k.f(context) + h.b + Float.toString(new TextView(context).getTextSize());
            }
            e = com.alipay.sdk.util.a.b(context).p;
            a5 = "-1;-1";
            String str3 = MessageService.MSG_DB_NOTIFY_REACHED;
            String a8 = a7.a();
            String b2 = a7.b();
            Context context2 = b.a().a;
            SharedPreferences sharedPreferences = context2.getSharedPreferences("virtualImeiAndImsi", 0);
            String string = sharedPreferences.getString("virtual_imsi", null);
            if (TextUtils.isEmpty(string)) {
                if (TextUtils.isEmpty(com.alipay.sdk.tid.b.a().a)) {
                    Object c = b.a().c();
                    string = TextUtils.isEmpty(c) ? c.b() : c.substring(XZBDevice.DOWNLOAD_LIST_FAILED, 18);
                } else {
                    string = com.alipay.sdk.util.a.a(context2).a();
                }
                sharedPreferences.edit().putString("virtual_imsi", string).commit();
            }
            b = string;
            context2 = b.a().a;
            SharedPreferences sharedPreferences2 = context2.getSharedPreferences("virtualImeiAndImsi", 0);
            string = sharedPreferences2.getString("virtual_imei", null);
            if (TextUtils.isEmpty(string)) {
                string = TextUtils.isEmpty(com.alipay.sdk.tid.b.a().a) ? c.b() : com.alipay.sdk.util.a.a(context2).b();
                sharedPreferences2.edit().putString("virtual_imei", string).commit();
            }
            a4 = string;
            if (a2 != null) {
                a6.c = a2.b;
            }
            String replace = Build.MANUFACTURER.replace(h.b, " ");
            String replace2 = Build.MODEL.replace(h.b, " ");
            boolean b3 = b.b();
            String str4 = a7.a;
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
            String ssid = connectionInfo != null ? connectionInfo.getSSID() : WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
            connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
            string = connectionInfo != null ? connectionInfo.getBSSID() : "00";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a6.a).append(h.b).append(e).append(h.b).append(a5).append(h.b).append(str3).append(h.b).append(a8).append(h.b).append(b2).append(h.b).append(a6.c).append(h.b).append(replace).append(h.b).append(replace2).append(h.b).append(b3).append(h.b).append(str4).append(";-1;-1;").append(a6.b).append(h.b).append(b).append(h.b).append(a4).append(h.b).append(ssid).append(h.b).append(string);
            if (a2 != null) {
                HashMap hashMap = new HashMap();
                hashMap.put(com.alipay.sdk.cons.b.c, a2.a);
                hashMap.put(MsgConstant.KEY_UTDID, b.a().c());
                c = a6.b(context, hashMap);
                if (!TextUtils.isEmpty(c)) {
                    stringBuilder.append(h.b).append(c);
                }
            }
            stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
            a3.put(str2, stringBuilder.toString());
            a3.put(com.alipay.sdk.cons.b.e, k.b(a.a));
            a3.put(com.alipay.sdk.cons.b.f, k.a(a.a));
            a3.put(com.alipay.sdk.cons.b.d, str);
            a3.put(org.android.agoo.common.b.PROPERTY_APP_KEY, com.alipay.sdk.cons.a.c);
            a3.put(MsgConstant.KEY_UTDID, a.c());
            a3.put(com.alipay.sdk.cons.b.j, a2.b);
        } catch (Throwable th) {
        }
        return a3.toString();
    }

    private static boolean a(HttpResponse httpResponse) {
        String str = null;
        String str2 = a;
        if (httpResponse != null) {
            Header[] allHeaders = httpResponse.getAllHeaders();
            if (allHeaders != null && allHeaders.length > 0) {
                for (Header header : allHeaders) {
                    if (header != null) {
                        String name = header.getName();
                        if (name != null && name.equalsIgnoreCase(str2)) {
                            str = header.getValue();
                            break;
                        }
                    }
                }
            }
        }
        return Boolean.valueOf(str).booleanValue();
    }

    private static String a(HttpResponse httpResponse, String str) {
        if (httpResponse == null) {
            return null;
        }
        Header[] allHeaders = httpResponse.getAllHeaders();
        if (allHeaders == null || allHeaders.length <= 0) {
            return null;
        }
        for (Header header : allHeaders) {
            if (header != null) {
                String name = header.getName();
                if (name != null && name.equalsIgnoreCase(str)) {
                    return header.getValue();
                }
            }
        }
        return null;
    }

    public static String a(HashMap<String, String> hashMap, HashMap<String, String> hashMap2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        for (Entry entry : hashMap.entrySet()) {
            jSONObject2.put((String) entry.getKey(), entry.getValue());
        }
        JSONObject jSONObject3 = new JSONObject();
        for (Entry entry2 : hashMap2.entrySet()) {
            jSONObject3.put((String) entry2.getKey(), entry2.getValue());
        }
        jSONObject2.put(l, jSONObject3);
        jSONObject.put(k, jSONObject2);
        return jSONObject.toString();
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject(k);
            if (!jSONObject.has(l)) {
                return false;
            }
            String optString = jSONObject.getJSONObject(l).optString(m, null);
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            b.a();
            c.a().a(optString);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    private static a b(Context context, String str) {
        if (t == null) {
            t = new a(context, str);
        } else if (!TextUtils.equals(str, t.b)) {
            t.b = str;
        }
        return t;
    }

    private b a(Context context) throws Throwable {
        return a(context, com.umeng.a.d, j.a(context), true);
    }

    public b a(Context context, String str) throws Throwable {
        return a(context, str, j.a(context), true);
    }

    private b a(Context context, String str, String str2) throws Throwable {
        return a(context, str, str2, true);
    }

    public final b a(Context context, String str, String str2, boolean z) throws Throwable {
        String str3 = null;
        e eVar = new e(this.s);
        c a = eVar.a(new b(c(), a(str, a())), this.r);
        if (t == null) {
            t = new a(context, str2);
        } else if (!TextUtils.equals(str2, t.b)) {
            t.b = str2;
        }
        HttpResponse a2 = t.a(a.b, a(a.a, str));
        String str4 = a;
        if (a2 != null) {
            Header[] allHeaders = a2.getAllHeaders();
            if (allHeaders != null && allHeaders.length > 0) {
                for (Header header : allHeaders) {
                    if (header != null) {
                        String name = header.getName();
                        if (name != null && name.equalsIgnoreCase(str4)) {
                            str3 = header.getValue();
                            break;
                        }
                    }
                }
            }
        }
        b a3 = eVar.a(new c(Boolean.valueOf(str3).booleanValue(), b(a2)));
        return (a3 != null && a(a3.a) && z) ? a(context, str, str2, false) : a3;
    }

    private static byte[] b(HttpResponse httpResponse) throws IllegalStateException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] bArr = new byte[1024];
        try {
            InputStream content = httpResponse.getEntity().getContent();
            try {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int read = content.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream2.write(bArr, 0, read);
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        byteArrayOutputStream = byteArrayOutputStream2;
                    }
                }
                bArr = byteArrayOutputStream2.toByteArray();
                if (content != null) {
                    try {
                        content.close();
                    } catch (Exception e) {
                    }
                }
                try {
                    byteArrayOutputStream2.close();
                } catch (Exception e2) {
                }
                return bArr;
            } catch (Throwable th3) {
                th2 = th3;
                if (content != null) {
                    content.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th2;
            }
        } catch (Throwable th4) {
            th2 = th4;
            content = null;
            if (content != null) {
                try {
                    content.close();
                } catch (Exception e3) {
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Exception e4) {
                }
            }
            throw th2;
        }
    }
}
