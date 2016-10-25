package com.xunlei.downloadprovider.ad.common.d;

import anet.channel.security.ISecurity;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.sina.weibo.sdk.component.GameManager;
import com.tencent.connect.common.Constants;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.downloadprovider.model.protocol.report.b;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: AbstractRequestParam.java
public abstract class a {
    private static final String a;

    // compiled from: AbstractRequestParam.java
    public static class a extends a {
        public int a;
        public String b;
        public int c;

        public a() {
            this.b = com.umeng.a.d;
        }

        protected final Map<String, String> c() {
            Map hashMap = new HashMap();
            hashMap.put("guid", b.b());
            hashMap.put("items", e().toString());
            return hashMap;
        }

        private JSONArray e() {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("orderId", f());
                jSONObject.put("positionId", this.c);
                jSONObject.put("statType", this.a);
                jSONObject.put("statValue", 1);
                jSONArray.put(0, jSONObject);
                new StringBuilder("positionId: ").append(this.c);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONArray;
        }

        protected final String d() {
            return "http://api.tw06.xlmc.sandai.net/api/adp/addStat";
        }

        private long f() {
            try {
                return Long.parseLong(this.b);
            } catch (NumberFormatException e) {
                new StringBuilder("orderId is invalid: ").append(this.b);
                return 0;
            }
        }
    }

    protected abstract Map<String, String> c();

    protected abstract String d();

    static {
        a = a.class.getSimpleName();
    }

    private static String a(HashMap<String, String> hashMap, String str) throws IOException {
        Set<Entry> entrySet = new TreeMap(hashMap).entrySet();
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : entrySet) {
            stringBuilder.append(String.valueOf(entry.getKey())).append("=").append(String.valueOf(entry.getValue()));
        }
        stringBuilder.append(str);
        try {
            byte[] digest = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5).digest(stringBuilder.toString().getBytes(GameManager.DEFAULT_CHARSET));
            StringBuilder stringBuilder2 = new StringBuilder();
            for (byte b : digest) {
                String toHexString = Integer.toHexString(b & 255);
                if (toHexString.length() == 1) {
                    stringBuilder2.append(MessageService.MSG_DB_READY_REPORT);
                }
                stringBuilder2.append(toHexString);
            }
            return stringBuilder2.toString();
        } catch (Throwable e) {
            throw new IOException(e);
        }
    }

    public final String a() {
        return d();
    }

    public final Map<String, String> b() {
        Map hashMap = new HashMap();
        hashMap.put("appId", Constants.VIA_REPORT_TYPE_START_GROUP);
        hashMap.put(IXAdRequestInfo.V, MsgConstant.PROTOCOL_VERSION);
        hashMap.put("callId", System.currentTimeMillis());
        hashMap.putAll(c());
        try {
            hashMap.put("sig", a(hashMap, "ed35b80ab6de3944a96466be405de2fc"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    static /* synthetic */ String a(List list) {
        if (list == null) {
            throw new NullPointerException("ids cannot be null");
        }
        StringBuilder stringBuilder = new StringBuilder();
        int size = list.size();
        if (size <= 0) {
            return com.umeng.a.d;
        }
        stringBuilder.append((String) list.get(0));
        for (int i = 1; i < size; i++) {
            stringBuilder.append(new StringBuilder(MiPushClient.ACCEPT_TIME_SEPARATOR).append((String) list.get(i)).toString());
        }
        return stringBuilder.toString();
    }
}
