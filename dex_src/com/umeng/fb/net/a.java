package com.umeng.fb.net;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.fb.model.Reply;
import com.umeng.fb.model.Store;
import com.umeng.fb.util.Log;
import com.umeng.fb.util.b;
import com.umeng.fb.util.c;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.OauthHelper;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: FbClient.java
public class a {
    public static final String a = "http://fb.umeng.com";
    public static final String b = "http://fb.umeng.com/api/v2/user/getuid";
    public static final String c = "http://fb.umeng.com/api/v2/feedback/reply/new";
    public static final String d = "http://fb.umeng.com/api/v2/feedback/reply/full_show";
    public static final String e = "http://fb.umeng.com/api/v2/feedback/new";
    public static final String f = "http://fb.umeng.com/api/v2/user/update";
    public static final String g = "dev_reply";
    private static final String h;
    private static final int j = 30000;
    private Context i;

    static {
        h = a.class.getName();
    }

    public a(Context context) {
        this.i = context;
        b();
    }

    public List<Reply> a(String str) throws IOException, JSONException {
        Map hashMap = new HashMap();
        hashMap.put("feedback_id", str);
        hashMap.put(OauthHelper.APP_KEY, b.p(this.i));
        JSONObject a = a(d, hashMap);
        Log.c(h, new StringBuilder("getDevReply resp: ").append(a).toString());
        JSONArray jSONArray = a.getJSONObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA).getJSONArray("result");
        List<Reply> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                Reply fromJson = Reply.fromJson(jSONArray.getJSONObject(i));
                if (g.equals(fromJson.type)) {
                    arrayList.add(fromJson);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public void a(JSONObject jSONObject) {
        try {
            JSONObject a = c.a(this.i);
            a.put(com.xunlei.download.proguard.c.f, Store.getInstance(this.i).getUid());
            a.put("userinfo", jSONObject.toString());
            Log.c(h, "sendUserInfo url -- http://fb.umeng.com/api/v2/user/update");
            Log.c(h, new StringBuilder("sendUserInfo request-- ").append(a).toString());
            Log.c(h, new StringBuilder("sendUserInfo response -- ").append(a(a, f)).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map a(String str, Reply reply) {
        Map hashMap = new HashMap();
        try {
            JSONObject a = c.a(this.i);
            a.put("content", reply.content);
            a.put("feedback_id", str);
            a.put("reply_id", reply.reply_id);
            a.put(AgooConstants.MESSAGE_TYPE, Reply.TYPE_USER_REPLY);
            a = a(a, c);
            Log.c(h, new StringBuilder("sendUserReply response --").append(a.toString()).toString());
            String string = a.getJSONObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA).getString("feedback_id");
            long j = a.getJSONObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA).getLong("created_at");
            hashMap.put("feedback_id", string);
            hashMap.put("created_at", Long.valueOf(j));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public Map b(String str, Reply reply) {
        Map hashMap = new HashMap();
        try {
            JSONObject a = c.a(this.i);
            a.put("content", reply.content);
            a.put(com.xunlei.download.proguard.c.f, Store.getInstance(this.i).getUid());
            a.put("device_uuid", Store.getInstance(this.i).getDeviceUUID());
            a.put("feedback_id", str);
            if (Store.getInstance(this.i).getUserInfo().getRemarkJson() != null) {
                a.put("remark", Store.getInstance(this.i).getUserInfo().getRemarkJson().toString());
            }
            JSONObject a2 = a(a, e);
            Log.c(h, new StringBuilder("newFeedback request --").append(a.toString()).toString());
            String string = a2.getJSONObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA).getString("feedback_id");
            long j = a2.getJSONObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA).getLong("created_at");
            hashMap.put("feedback_id", string);
            hashMap.put("created_at", Long.valueOf(j));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public String a() {
        try {
            String str;
            JSONObject a = c.a(this.i);
            StringBuilder stringBuilder = new StringBuilder(b);
            stringBuilder.append("?");
            Iterator keys = a.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                stringBuilder.append(URLEncoder.encode(str, CharsetConvert.UTF_8) + "=" + URLEncoder.encode(a.get(str).toString(), CharsetConvert.UTF_8) + "&");
            }
            if ('&' == stringBuilder.charAt(stringBuilder.length() - 1)) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            str = b(stringBuilder.toString()).getJSONObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA).getString(com.xunlei.download.proguard.c.f);
            Log.c(h, new StringBuilder("FbClient.getUid: ").append(str).toString());
            Store.getInstance(this.i).setUid(str);
            return str;
        } catch (Exception e) {
            return BuildConfig.VERSION_NAME;
        }
    }

    private static void b() {
        if (Integer.parseInt(VERSION.SDK) < 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.json.JSONObject a(org.json.JSONObject r4, java.lang.String r5) throws java.io.IOException {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.fb.net.a.a(org.json.JSONObject, java.lang.String):org.json.JSONObject");
        /*
        r0 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r0.<init>(r5);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r0 = r0.openConnection();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = 1;
        r0.setDoOutput(r1);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = "POST";
        r0.setRequestMethod(r1);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = "Content-Type";
        r2 = "application/json";
        r0.setRequestProperty(r1, r2);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = r4.toString();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r2 = new java.io.BufferedOutputStream;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r3 = r0.getOutputStream();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r2.<init>(r3);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = r1.getBytes();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r2.write(r1);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r2.flush();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = r0.getResponseCode();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 == r2) goto L_0x005d;
    L_0x003d:
        r1 = new java.lang.RuntimeException;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r2 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r3 = "Failed : HTTP error code : ";
        r2.<init>(r3);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r0 = r0.getResponseCode();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r0 = r2.append(r0);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r0 = r0.toString();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1.<init>(r0);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        throw r1;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
    L_0x0057:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x005b:
        r0 = 0;
    L_0x005c:
        return r0;
    L_0x005d:
        r1 = new java.io.BufferedReader;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r2 = new java.io.InputStreamReader;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r3 = r0.getInputStream();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r2.<init>(r3);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1.<init>(r2);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r2 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r2.<init>();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
    L_0x0070:
        r3 = r1.readLine();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        if (r3 == 0) goto L_0x007f;
    L_0x0076:
        r2.append(r3);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        goto L_0x0070;
    L_0x007a:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x005b;
    L_0x007f:
        r1.close();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r0.disconnect();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r0 = h;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r3 = "jsonHttpPost: ";
        r1.<init>(r3);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = r1.append(r5);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = r1.toString();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        com.umeng.fb.util.Log.c(r0, r1);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r0 = h;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r3 = "\t request:\n";
        r1.<init>(r3);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r3 = r4.toString();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = r1.append(r3);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = r1.toString();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        com.umeng.fb.util.Log.c(r0, r1);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r0 = h;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r3 = "\t response:\n";
        r1.<init>(r3);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r3 = r2.toString();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = r1.append(r3);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = r1.toString();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        com.umeng.fb.util.Log.c(r0, r1);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r0 = new org.json.JSONObject;	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r1 = r2.toString();	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        r0.<init>(r1);	 Catch:{ MalformedURLException -> 0x0057, JSONException -> 0x007a }
        goto L_0x005c;
        */
    }

    private static JSONObject a(String str, Map<String, Object> map) throws IOException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        if (stringBuilder.charAt(stringBuilder.length() - 1) != '?' && map.size() > 0) {
            stringBuilder.append('?');
        }
        for (String str2 : map.keySet()) {
            stringBuilder.append(URLEncoder.encode(str2, CharsetConvert.UTF_8) + "=" + URLEncoder.encode(map.get(str2), CharsetConvert.UTF_8) + "&");
        }
        if ('&' == stringBuilder.charAt(stringBuilder.length() - 1)) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return b(stringBuilder.toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.json.JSONObject b(java.lang.String r4) throws java.io.IOException {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.fb.net.a.b(java.lang.String):org.json.JSONObject");
        /*
        r0 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r0.<init>(r4);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r0 = r0.openConnection();	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r1 = "GET";
        r0.setRequestMethod(r1);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r1 = r0.getResponseCode();	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r1 == r2) goto L_0x0039;
    L_0x0019:
        r1 = new java.lang.RuntimeException;	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r2 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r3 = "Failed : HTTP error code : ";
        r2.<init>(r3);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r0 = r0.getResponseCode();	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r0 = r2.append(r0);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r0 = r0.toString();	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r1.<init>(r0);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        throw r1;	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
    L_0x0033:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0037:
        r0 = 0;
    L_0x0038:
        return r0;
    L_0x0039:
        r1 = new java.io.BufferedReader;	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r2 = new java.io.InputStreamReader;	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r3 = r0.getInputStream();	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r2.<init>(r3);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r1.<init>(r2);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r2 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r2.<init>();	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
    L_0x004c:
        r3 = r1.readLine();	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        if (r3 == 0) goto L_0x005b;
    L_0x0052:
        r2.append(r3);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        goto L_0x004c;
    L_0x0056:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0037;
    L_0x005b:
        r1.close();	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r0.disconnect();	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r0 = h;	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r1 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r3 = "jsonHttpGet: ";
        r1.<init>(r3);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r1 = r1.append(r4);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r1 = r1.toString();	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        com.umeng.fb.util.Log.c(r0, r1);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r0 = h;	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r1 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r3 = "\t ";
        r1.<init>(r3);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r3 = r2.toString();	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r1 = r1.append(r3);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r1 = r1.toString();	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        com.umeng.fb.util.Log.c(r0, r1);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r0 = new org.json.JSONObject;	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r1 = r2.toString();	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        r0.<init>(r1);	 Catch:{ MalformedURLException -> 0x0033, JSONException -> 0x0056 }
        goto L_0x0038;
        */
    }
}
