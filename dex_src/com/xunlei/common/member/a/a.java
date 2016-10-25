package com.xunlei.common.member.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import anet.channel.util.HttpConstant;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.base.XLUtilTools;
import com.xunlei.common.httpclient.AsyncHttpClient;
import com.xunlei.common.httpclient.BaseHttpClient;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.member.c.v;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: AsyncHttpProxy.java
public final class a {
    private static final String a = "portalCache";
    private static final a g;
    private long b;
    private final String c;
    private final String d;
    private final String e;
    private final int f;
    @SuppressLint({"UseSparseArrays"})
    private Map<Integer, List<String>> h;
    private int i;
    private int j;
    private boolean k;
    private int l;
    private List<b> m;
    private Context n;
    private String o;
    private int p;
    private String q;
    private int r;

    // compiled from: AsyncHttpProxy.java
    public class a {
        private static int i = 110;
        private static int j = 101;
        private static String k = "AC69F5CCC8BDE47CD3D371603748378C9CFAD2938A6B021E0E191013975AD683F5CBF9ADE8BD7D46B4D2EC2D78AF146F1DD2D50DC51446BB8880B8CE88D476694DFC60594393BEEFAA16F5DBCEBE22F89D640F5336E42F587DC4AFEDEFEAC36CF007009CCCE5C1ACB4FF06FBA69802A8085C2C54BADD0597FC83E6870F1E36FD";
        private static String l = "010001";
        private static int m = 100;
        private static int n = 100;
        private static String o = "android";
        byte[] a;
        int b;
        int c;
        int d;
        int e;
        b f;
        int g;

        public a() {
            this.a = null;
            this.b = 1;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = null;
            this.g = a.this.c();
        }

        public final void a(byte[] bArr) {
            this.a = bArr;
        }

        public final void a(int i) {
            this.b = i;
        }

        public final void b(int i) {
            this.e = i;
        }

        public final void a(b bVar) {
            this.f = bVar;
        }

        private static boolean b() {
            if (m.a().u() != 0) {
                return false;
            }
            m.a().d(1);
            return true;
        }

        private void a(String str) {
            if (this.e != 0) {
                g gVar = new g();
                gVar.a = XLUtilTools.getServerDomain(str);
                gVar.b = this.c + (this.d * 3);
                m.a().a(this.e, gVar);
            }
        }

        private String b(byte[] bArr) {
            String str = com.umeng.a.d;
            try {
                return new String(bArr, "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                XLLog.v(a.this.q, new StringBuilder("transformSingleCharString error = ").append(e.getMessage()).toString());
                return str;
            }
        }

        public final void a() {
            String a;
            int i = 1;
            if (this.b == 7) {
                if (m.a().u() == 0) {
                    m.a().d(1);
                } else {
                    i = 0;
                }
                if (i == 0) {
                    XLLog.v("AsyncHttpProxyReq", "go home boy!");
                    return;
                } else {
                    XLLog.v("AsyncHttpProxyReq", "go ahead boy!");
                    a = a.this.a(this.b, a.this.i);
                }
            } else {
                a = a.this.a(this.b, this.g);
            }
            if (a == null) {
                a = "https://login.mobile.reg2t.sandai.net:443";
            }
            XLLog.v(a.this.q, new StringBuilder("send request use url = ").append(a).append("#request=").append(hashCode()).toString());
            m.a().k().post(a.this.n, a, null, this.a, new AnonymousClass_1(this, a));
        }

        static /* synthetic */ void a(com.xunlei.common.member.a.a.a aVar, String str) {
            if (aVar.e != 0) {
                g gVar = new g();
                gVar.a = XLUtilTools.getServerDomain(str);
                gVar.b = aVar.c + (aVar.d * 3);
                m.a().a(aVar.e, gVar);
            }
        }
    }

    static {
        g = new a();
    }

    private a() {
        this.b = 432000000;
        this.h = new HashMap();
        this.i = 0;
        this.j = 0;
        this.k = false;
        this.l = 1000000;
        this.m = new ArrayList();
        this.n = null;
        this.o = "1.0.0";
        this.p = 0;
        this.q = "AsyncHttpProxy";
        this.r = 0;
    }

    public final void a(Context context, int i, String str) {
        KeyStore instance;
        Exception e;
        SSLSocketFactory cVar;
        SSLSocketFactory sSLSocketFactory;
        KeyManagementException keyManagementException;
        BaseHttpClient k;
        UnrecoverableKeyException unrecoverableKeyException;
        NoSuchAlgorithmException noSuchAlgorithmException;
        KeyStoreException keyStoreException;
        Object obj;
        if (!this.k) {
            List a;
            this.k = true;
            this.n = context;
            this.o = str;
            this.p = i;
            this.i = 0;
            this.j = 0;
            SharedPreferences sharedPreferences = this.n.getSharedPreferences(a, 0);
            String string = sharedPreferences.getString("PortalSrvList", "[{\"port\":\"443\",\"type\":\"https\",\"ip\":\"112.80.23.195\"}]");
            String string2 = sharedPreferences.getString("LoginSrvList", "[{\"port\":\"443\",\"type\":\"https\",\"ip\":\"123.150.173.56\"},{\"port\":\"443\",\"type\":\"https\",\"ip\":\"61.155.183.195\"},{\"port\":\"443\",\"type\":\"https\",\"ip\":\"112.80.23.195\"},{\"port\":\"443\",\"type\":\"https\",\"ip\":\"123.39.70.185\"},{\"port\":\"443\",\"type\":\"https\",\"ip\":\"123.150.173.185\"},{\"port\":\"443\",\"type\":\"https\",\"ip\":\"125.39.70.56\"}]");
            try {
                List a2 = a(new JSONArray(string));
                a2.add(0, "https://login.mobile.reg2t.sandai.net:443");
                this.h.put(Integer.valueOf(R.styleable.Toolbar_contentInsetLeft), a2);
                a = a(new JSONArray(string2));
                a.add(0, "https://login.mobile.reg2t.sandai.net:443");
                this.h.put(Integer.valueOf(1), a);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            a = new ArrayList();
            a.add(0, "http://dy.cdn.vip.xunlei.com:80/fcg-bin/cgi_query_capacity.fcg?userid=");
            this.h.put(Integer.valueOf(XZBDevice.Pause), a);
            a = new ArrayList();
            a.add(0, "http://dynamic.cloud.vip.xunlei.com/interface/query_user_info?uid=");
            this.h.put(Integer.valueOf(XZBDevice.Stop), a);
            try {
                instance = KeyStore.getInstance(KeyStore.getDefaultType());
                try {
                    instance.load(null, null);
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    cVar = new c(instance);
                    try {
                        cVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                    } catch (KeyManagementException e4) {
                        KeyManagementException keyManagementException2 = e4;
                        sSLSocketFactory = cVar;
                        keyManagementException = keyManagementException2;
                        keyManagementException.printStackTrace();
                        cVar = sSLSocketFactory;
                        k = m.a().k();
                        if (!(k instanceof AsyncHttpClient)) {
                            ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                        }
                    } catch (UnrecoverableKeyException e5) {
                        UnrecoverableKeyException unrecoverableKeyException2 = e5;
                        sSLSocketFactory = cVar;
                        unrecoverableKeyException = unrecoverableKeyException2;
                        unrecoverableKeyException.printStackTrace();
                        cVar = sSLSocketFactory;
                        k = m.a().k();
                        if (!(k instanceof AsyncHttpClient)) {
                            ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                        }
                    } catch (NoSuchAlgorithmException e6) {
                        NoSuchAlgorithmException noSuchAlgorithmException2 = e6;
                        sSLSocketFactory = cVar;
                        noSuchAlgorithmException = noSuchAlgorithmException2;
                        noSuchAlgorithmException.printStackTrace();
                        cVar = sSLSocketFactory;
                        k = m.a().k();
                        if (!(k instanceof AsyncHttpClient)) {
                            ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                        }
                    } catch (KeyStoreException e7) {
                        KeyStoreException keyStoreException2 = e7;
                        sSLSocketFactory = cVar;
                        keyStoreException = keyStoreException2;
                        keyStoreException.printStackTrace();
                        cVar = sSLSocketFactory;
                        k = m.a().k();
                        if (!(k instanceof AsyncHttpClient)) {
                            ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                        }
                    }
                    k = m.a().k();
                    if (!(k instanceof AsyncHttpClient)) {
                        ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                    }
                }
            } catch (Exception e8) {
                e = e8;
                instance = null;
                e.printStackTrace();
                try {
                    cVar = new c(instance);
                    cVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                } catch (KeyManagementException e42) {
                    keyManagementException = e42;
                    obj = null;
                    keyManagementException.printStackTrace();
                    cVar = sSLSocketFactory;
                    k = m.a().k();
                    if (!(k instanceof AsyncHttpClient)) {
                        ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                    }
                } catch (UnrecoverableKeyException e52) {
                    unrecoverableKeyException = e52;
                    obj = null;
                    unrecoverableKeyException.printStackTrace();
                    cVar = sSLSocketFactory;
                    k = m.a().k();
                    if (!(k instanceof AsyncHttpClient)) {
                        ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                    }
                } catch (NoSuchAlgorithmException e62) {
                    noSuchAlgorithmException = e62;
                    obj = null;
                    noSuchAlgorithmException.printStackTrace();
                    cVar = sSLSocketFactory;
                    k = m.a().k();
                    if (!(k instanceof AsyncHttpClient)) {
                        ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                    }
                } catch (KeyStoreException e72) {
                    keyStoreException = e72;
                    obj = null;
                    keyStoreException.printStackTrace();
                    cVar = sSLSocketFactory;
                    k = m.a().k();
                    if (!(k instanceof AsyncHttpClient)) {
                        ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                    }
                }
                k = m.a().k();
                if (!(k instanceof AsyncHttpClient)) {
                    ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                }
            }
            cVar = new c(instance);
            cVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            k = m.a().k();
            if (!(k instanceof AsyncHttpClient)) {
                ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
            }
        }
    }

    private static void e() {
        KeyStore instance;
        SSLSocketFactory cVar;
        SSLSocketFactory sSLSocketFactory;
        KeyManagementException keyManagementException;
        BaseHttpClient k;
        UnrecoverableKeyException unrecoverableKeyException;
        NoSuchAlgorithmException noSuchAlgorithmException;
        KeyStoreException keyStoreException;
        try {
            instance = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                instance.load(null, null);
            } catch (Exception e) {
                Exception e2 = e;
                e2.printStackTrace();
                cVar = new c(instance);
                try {
                    cVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                } catch (KeyManagementException e3) {
                    KeyManagementException keyManagementException2 = e3;
                    sSLSocketFactory = cVar;
                    keyManagementException = keyManagementException2;
                    keyManagementException.printStackTrace();
                    cVar = sSLSocketFactory;
                    k = m.a().k();
                    if (!(k instanceof AsyncHttpClient)) {
                        ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                    }
                } catch (UnrecoverableKeyException e4) {
                    UnrecoverableKeyException unrecoverableKeyException2 = e4;
                    sSLSocketFactory = cVar;
                    unrecoverableKeyException = unrecoverableKeyException2;
                    unrecoverableKeyException.printStackTrace();
                    cVar = sSLSocketFactory;
                    k = m.a().k();
                    if (!(k instanceof AsyncHttpClient)) {
                        ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                    }
                } catch (NoSuchAlgorithmException e5) {
                    NoSuchAlgorithmException noSuchAlgorithmException2 = e5;
                    sSLSocketFactory = cVar;
                    noSuchAlgorithmException = noSuchAlgorithmException2;
                    noSuchAlgorithmException.printStackTrace();
                    cVar = sSLSocketFactory;
                    k = m.a().k();
                    if (!(k instanceof AsyncHttpClient)) {
                        ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                    }
                } catch (KeyStoreException e6) {
                    KeyStoreException keyStoreException2 = e6;
                    sSLSocketFactory = cVar;
                    keyStoreException = keyStoreException2;
                    keyStoreException.printStackTrace();
                    cVar = sSLSocketFactory;
                    k = m.a().k();
                    if (!(k instanceof AsyncHttpClient)) {
                        ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                    }
                }
                k = m.a().k();
                if (!(k instanceof AsyncHttpClient)) {
                    ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                }
            }
        } catch (Exception e7) {
            e2 = e7;
            instance = null;
            e2.printStackTrace();
            try {
                cVar = new c(instance);
                cVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            } catch (KeyManagementException e32) {
                keyManagementException = e32;
                Object obj = null;
                keyManagementException.printStackTrace();
                cVar = sSLSocketFactory;
                k = m.a().k();
                if (!(k instanceof AsyncHttpClient)) {
                    ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                }
            } catch (UnrecoverableKeyException e42) {
                unrecoverableKeyException = e42;
                obj = null;
                unrecoverableKeyException.printStackTrace();
                cVar = sSLSocketFactory;
                k = m.a().k();
                if (!(k instanceof AsyncHttpClient)) {
                    ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                }
            } catch (NoSuchAlgorithmException e52) {
                noSuchAlgorithmException = e52;
                obj = null;
                noSuchAlgorithmException.printStackTrace();
                cVar = sSLSocketFactory;
                k = m.a().k();
                if (!(k instanceof AsyncHttpClient)) {
                    ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                }
            } catch (KeyStoreException e62) {
                keyStoreException = e62;
                obj = null;
                keyStoreException.printStackTrace();
                cVar = sSLSocketFactory;
                k = m.a().k();
                if (!(k instanceof AsyncHttpClient)) {
                    ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
                }
            }
            k = m.a().k();
            if (!(k instanceof AsyncHttpClient)) {
                ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
            }
        }
        cVar = new c(instance);
        cVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        k = m.a().k();
        if (!(k instanceof AsyncHttpClient)) {
            ((AsyncHttpClient) k).setSSLSocketFactory(cVar);
        }
    }

    public static void a() {
    }

    public static a b() {
        return g;
    }

    private static List<String> a(JSONArray jSONArray) {
        List<String> arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.opt(i);
            if (jSONObject != null) {
                arrayList.add(jSONObject.optString(JsInterface.FUNPLAY_AD_TRPE) + HttpConstant.SCHEME_SPLIT + jSONObject.optString("ip") + ":" + jSONObject.optString("port"));
            }
        }
        return arrayList;
    }

    private void f() {
        List a;
        SharedPreferences sharedPreferences = this.n.getSharedPreferences(a, 0);
        String string = sharedPreferences.getString("PortalSrvList", "[{\"port\":\"443\",\"type\":\"https\",\"ip\":\"112.80.23.195\"}]");
        String string2 = sharedPreferences.getString("LoginSrvList", "[{\"port\":\"443\",\"type\":\"https\",\"ip\":\"123.150.173.56\"},{\"port\":\"443\",\"type\":\"https\",\"ip\":\"61.155.183.195\"},{\"port\":\"443\",\"type\":\"https\",\"ip\":\"112.80.23.195\"},{\"port\":\"443\",\"type\":\"https\",\"ip\":\"123.39.70.185\"},{\"port\":\"443\",\"type\":\"https\",\"ip\":\"123.150.173.185\"},{\"port\":\"443\",\"type\":\"https\",\"ip\":\"125.39.70.56\"}]");
        try {
            List a2 = a(new JSONArray(string));
            a2.add(0, "https://login.mobile.reg2t.sandai.net:443");
            this.h.put(Integer.valueOf(R.styleable.Toolbar_contentInsetLeft), a2);
            a = a(new JSONArray(string2));
            a.add(0, "https://login.mobile.reg2t.sandai.net:443");
            this.h.put(Integer.valueOf(1), a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        a = new ArrayList();
        a.add(0, "http://dy.cdn.vip.xunlei.com:80/fcg-bin/cgi_query_capacity.fcg?userid=");
        this.h.put(Integer.valueOf(XZBDevice.Pause), a);
        a = new ArrayList();
        a.add(0, "http://dynamic.cloud.vip.xunlei.com/interface/query_user_info?uid=");
        this.h.put(Integer.valueOf(XZBDevice.Stop), a);
    }

    private boolean a(b bVar) {
        if (this.m.contains(bVar)) {
            return false;
        }
        this.m.add(bVar);
        return true;
    }

    public final void a(Bundle bundle) {
        Iterator it = this.m.iterator();
        while (it.hasNext()) {
            it.next();
            if (bundle.getString(JsInterface.FUNPLAY_AD_TRPE).equalsIgnoreCase("onRetry")) {
                bundle.getInt(ParamKey.COUNT);
                bundle.getString("address");
            }
        }
    }

    private boolean b(b bVar) {
        return this.m.remove(bVar);
    }

    public final synchronized int c() {
        return this.j;
    }

    private synchronized void c(int i) {
        this.j = i;
    }

    public final synchronized int a(int i) {
        if (i != 7) {
            i = 1;
        }
        return ((List) this.h.get(Integer.valueOf(i))).size();
    }

    public final String a(int i, int i2) {
        if (i != 7) {
            i = 1;
        }
        List list = (List) this.h.get(Integer.valueOf(i));
        return (list == null || i2 < 0 || i2 >= list.size()) ? "https://login.mobile.reg2t.sandai.net:443" : (String) list.get(i2);
    }

    private void a(String str, byte[] bArr, BaseHttpClientListener baseHttpClientListener) {
        m.a().k().post(this.n, str, null, bArr, baseHttpClientListener);
    }

    public final void a(Header[] headerArr, String str, byte[] bArr, BaseHttpClientListener baseHttpClientListener) {
        m.a().k().post(this.n, str, headerArr, bArr, baseHttpClientListener);
    }

    private void a(byte[] bArr, int i, b bVar) {
        a aVar = new a();
        aVar.a = bArr;
        aVar.f = bVar;
        aVar.b = 7;
        aVar.a();
    }

    public final void a(byte[] bArr, int i, b bVar, int i2) {
        a aVar = new a();
        aVar.a = bArr;
        aVar.f = bVar;
        aVar.b = i;
        aVar.e = i2;
        aVar.a();
    }

    public final void a(String str, BaseHttpClientListener baseHttpClientListener) {
        m.a().k().get(this.n, str, null, baseHttpClientListener);
    }

    private void a(String str, Header[] headerArr, BaseHttpClientListener baseHttpClientListener) {
        m.a().k().get(this.n, str, headerArr, baseHttpClientListener);
    }

    public final void d() {
        if ((System.currentTimeMillis() - this.n.getSharedPreferences(a, 0).getLong("PrePortalTime", 0)) - this.b >= 0) {
            g();
        }
    }

    private boolean g() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("protocolVersion", R.styleable.AppCompatTheme_spinnerStyle);
            int i = this.l + 1;
            this.l = i;
            jSONObject.put("sequenceNo", i);
            jSONObject.put(anet.channel.strategy.dispatch.a.PLATFORM_VERSION, 1);
            jSONObject.put("businessType", this.p);
            jSONObject.put("clientVersion", this.o);
            jSONObject.put("isCompressed", 0);
            jSONObject.put("cmdID", R.styleable.Toolbar_maxButtonHeight);
            jSONObject.put("userID", com.umeng.a.d);
            jSONObject.put("sessionID", com.umeng.a.d);
            i = this.r + 1;
            this.r = i;
            jSONObject.put("portalCount", i);
            jSONObject.put(SocialConstants.PARAM_APPNAME, new StringBuilder("ANDROID-").append(m.a().m()).toString());
            jSONObject.put("devicesign", v.b());
            jSONObject.put(Constants.KEY_SDK_VERSION, m.a().f());
            byte[] bytes = jSONObject.toString().getBytes();
            b anonymousClass_1 = new b() {
                public final void a(String str) {
                    try {
                        JSONObject jSONObject = new JSONObject(str.toString());
                        if (jSONObject.getInt(Constants.KEY_ERROR_CODE) == 0) {
                            Editor edit = a.this.n.getSharedPreferences(a, 0).edit();
                            JSONArray optJSONArray = jSONObject.optJSONArray("loginSrvIpList");
                            List a = a.a(optJSONArray);
                            if (a.size() > 0) {
                                a.add(0, "https://login.mobile.reg2t.sandai.net:443");
                                a.this.h.put(Integer.valueOf(1), a);
                                edit.putString("LoginSrvList", optJSONArray.toString());
                            }
                            JSONArray optJSONArray2 = jSONObject.optJSONArray("portalSrvIpList");
                            List a2 = a.a(optJSONArray2);
                            if (a2.size() > 0) {
                                a2.add(0, "https://login.mobile.reg2t.sandai.net:443");
                                a.this.h.put(Integer.valueOf(R.styleable.Toolbar_contentInsetLeft), a2);
                                edit.putString("PortalSrvList", optJSONArray2.toString());
                            }
                            edit.putLong("PrePortalTime", System.currentTimeMillis());
                            edit.commit();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                public final void a(Throwable th) {
                    super.a(th);
                }
            };
            a aVar = new a();
            aVar.a = bytes;
            aVar.f = anonymousClass_1;
            aVar.b = 7;
            aVar.a();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    public final String b(int i) {
        List list = (List) this.h.get(Integer.valueOf(i));
        return list != null ? (String) list.get(0) : com.umeng.a.d;
    }
}
