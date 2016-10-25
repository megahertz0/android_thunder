package com.xunlei.common.member.b;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.alipay.sdk.app.AuthTask;
import com.umeng.message.util.HttpRequest;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.member.XLHttpHeader;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.act.XLAlipayParam;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.http.Header;

// compiled from: XLAlipayAuth.java
public class b {
    private static final String a;
    private static String b = "2088911130724615";
    private static String c = "2015031800037197";
    private static int d = 4132;
    private static String e = "http://dynamic.i.xunlei.com/alipay_verify/verify.php?action=sign";
    private static String f = "http://dynamic.i.xunlei.com/alipay_verify/verify.php?action=verify_alipay";
    private d g;
    private Activity h;
    private c i;
    private String j;
    private XLAlipayParam k;
    private Handler l;

    // compiled from: XLAlipayAuth.java
    final class AnonymousClass_4 implements Runnable {
        private /* synthetic */ String a;

        AnonymousClass_4(String str) {
            this.a = str;
        }

        public final void run() {
            AuthTask authTask = new AuthTask(b.this.h);
            boolean z = true;
            if (b.this.k.mNeedLoading == 0) {
                z = false;
            }
            String auth = authTask.auth(this.a, z);
            Message message = new Message();
            message.what = 4132;
            message.obj = auth;
            b.this.l.sendMessage(message);
        }
    }

    static {
        a = b.class.getSimpleName();
    }

    public b(d dVar, XLAlipayParam xLAlipayParam, Activity activity) {
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = new Handler() {
            public final void handleMessage(Message message) {
                switch (message.what) {
                    case 4132:
                        b.this.i = new c((String) message.obj);
                        String c = b.this.i.c();
                        String a = b.this.i.a();
                        if (TextUtils.isEmpty(c) || TextUtils.isEmpty(a)) {
                            b.this.a(16781283, null, null, null);
                        } else {
                            b.a(b.this, c, a);
                        }
                    default:
                        break;
                }
            }
        };
        this.g = dVar;
        this.k = xLAlipayParam;
        this.h = activity;
    }

    private String c() {
        return (((((((("apiname=\"com.alipay.account.auth\"" + "&app_id=\"" + "2015031800037197" + "\"") + "&app_name=\"mc\"") + "&auth_type=\"AUTHACCOUNT\"") + "&biz_type=\"openservice\"") + "&pid=\"" + "2088911130724615" + "\"") + "&product_id=\"WAP_FAST_LOGIN\"") + "&scope=\"kuaijie\"") + "&target_id=\"" + this.k.mTargetId + "\"") + "&sign_date=\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()) + "\"";
    }

    private void a(String str) {
        try {
            String encode = URLEncoder.encode(str, CharsetConvert.UTF_8);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("data=").append(encode);
            XLHttpHeader xLHttpHeader = new XLHttpHeader(HttpRequest.l, HttpRequest.b);
            m.a().j().a(new Header[]{xLHttpHeader}, "http://dynamic.i.xunlei.com/alipay_verify/verify.php?action=sign", stringBuffer.toString().getBytes(), new BaseHttpClientListener() {
                public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    String str = new String(bArr);
                    XLLog.v(a, new StringBuilder("remote signRequestData succeed sign = ").append(str).toString());
                    b.this.a(0, str);
                }

                public final void onFailure(Throwable th, byte[] bArr) {
                    XLLog.e(a, new StringBuilder("signRequestData error = ").append(th.getMessage()).toString());
                    b.this.a(-1, null);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            XLLog.e(a, new StringBuilder("signRequestData error = ").append(e.getMessage()).toString());
            a(-1, null);
        }
    }

    private void a(String str, String str2) {
        boolean z = false;
        XLLog.v(a, String.format("verifySignRespData content = %s, sign = %s", new Object[]{str, str2}));
        try {
            String encode = URLEncoder.encode(str, CharsetConvert.UTF_8);
            String encode2 = URLEncoder.encode(str2, CharsetConvert.UTF_8);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("data=").append(encode).append("&sign=").append(encode2);
            XLHttpHeader xLHttpHeader = new XLHttpHeader(HttpRequest.l, HttpRequest.b);
            Header[] headerArr = new Header[]{xLHttpHeader};
            byte[] bytes = stringBuffer.toString().getBytes();
            z = new BaseHttpClientListener() {
                public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    boolean z = true;
                    int intValue = Integer.valueOf(new String(bArr)).intValue();
                    XLLog.v(a, new StringBuilder("remote verifySignRespData succeed verify = ").append(intValue).toString());
                    b bVar = b.this;
                    if (intValue != 1) {
                        z = false;
                    }
                    bVar.a(z);
                }

                public final void onFailure(Throwable th, byte[] bArr) {
                    XLLog.e(a, new StringBuilder("verifySignRespData error = ").append(th.getMessage()).toString());
                    b.this.a(false);
                }
            };
            m.a().j().a(headerArr, "http://dynamic.i.xunlei.com/alipay_verify/verify.php?action=verify_alipay", bytes, z);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            XLLog.e(a, new StringBuilder("verifySignRespData error = ").append(e.getMessage()).toString());
            a(z);
        }
    }

    private static String d() {
        return "sign_type=\"RSA\"";
    }

    private static String e() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    private void a(int i, String str, String str2, String str3) {
        if (this.g != null) {
            this.g.onAlipayAuth(i, str, str2, str3);
        }
    }

    private void a(int i, String str) {
        String str2 = null;
        if (i == 0) {
            try {
                str2 = "&sign=\"";
                String str3 = this.j + str2 + URLEncoder.encode(str, CharsetConvert.UTF_8) + "\"&sign_type=\"RSA\"";
                XLLog.v("XLAlipayAuth", str3);
                new Thread(new AnonymousClass_4(str3)).start();
                return;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                a(268439552, str2, str2, "2015031800037197");
            }
        }
        a(268439555, null, null, null);
    }

    private void a(boolean z) {
        if (z) {
            Object b = this.i.b();
            String f = this.i.f();
            String e = this.i.e();
            if (TextUtils.equals(b, "9000") && TextUtils.equals(this.i.d(), "200")) {
                a(0, f, e, "2015031800037197");
                return;
            } else if (TextUtils.equals(b, "9000")) {
                a(Integer.valueOf(this.i.d()).intValue(), f, e, "2015031800037197");
                return;
            } else {
                a(Integer.valueOf(b).intValue(), f, e, "2015031800037197");
                return;
            }
        }
        a(268439554, null, null, null);
    }

    public final void a() {
        this.j = (((((((("apiname=\"com.alipay.account.auth\"" + "&app_id=\"" + "2015031800037197" + "\"") + "&app_name=\"mc\"") + "&auth_type=\"AUTHACCOUNT\"") + "&biz_type=\"openservice\"") + "&pid=\"" + "2088911130724615" + "\"") + "&product_id=\"WAP_FAST_LOGIN\"") + "&scope=\"kuaijie\"") + "&target_id=\"" + this.k.mTargetId + "\"") + "&sign_date=\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()) + "\"";
        try {
            String encode = URLEncoder.encode(this.j, CharsetConvert.UTF_8);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("data=").append(encode);
            XLHttpHeader xLHttpHeader = new XLHttpHeader(HttpRequest.l, HttpRequest.b);
            m.a().j().a(new Header[]{xLHttpHeader}, "http://dynamic.i.xunlei.com/alipay_verify/verify.php?action=sign", stringBuffer.toString().getBytes(), new BaseHttpClientListener() {
                public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    String str = new String(bArr);
                    XLLog.v(a, new StringBuilder("remote signRequestData succeed sign = ").append(str).toString());
                    b.this.a(0, str);
                }

                public final void onFailure(Throwable th, byte[] bArr) {
                    XLLog.e(a, new StringBuilder("signRequestData error = ").append(th.getMessage()).toString());
                    b.this.a(-1, null);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            XLLog.e(a, new StringBuilder("signRequestData error = ").append(e.getMessage()).toString());
            a(-1, null);
        }
    }

    static /* synthetic */ void a(b bVar, String str, String str2) {
        boolean z = false;
        XLLog.v(a, String.format("verifySignRespData content = %s, sign = %s", new Object[]{str, str2}));
        try {
            String encode = URLEncoder.encode(str, CharsetConvert.UTF_8);
            String encode2 = URLEncoder.encode(str2, CharsetConvert.UTF_8);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("data=").append(encode).append("&sign=").append(encode2);
            XLHttpHeader xLHttpHeader = new XLHttpHeader(HttpRequest.l, HttpRequest.b);
            Header[] headerArr = new Header[]{xLHttpHeader};
            byte[] bytes = stringBuffer.toString().getBytes();
            z = new BaseHttpClientListener() {
                public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    boolean z = true;
                    int intValue = Integer.valueOf(new String(bArr)).intValue();
                    XLLog.v(a, new StringBuilder("remote verifySignRespData succeed verify = ").append(intValue).toString());
                    b bVar = b.this;
                    if (intValue != 1) {
                        z = false;
                    }
                    bVar.a(z);
                }

                public final void onFailure(Throwable th, byte[] bArr) {
                    XLLog.e(a, new StringBuilder("verifySignRespData error = ").append(th.getMessage()).toString());
                    b.this.a(false);
                }
            };
            m.a().j().a(headerArr, "http://dynamic.i.xunlei.com/alipay_verify/verify.php?action=verify_alipay", bytes, z);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            XLLog.e(a, new StringBuilder("verifySignRespData error = ").append(e.getMessage()).toString());
            bVar.a(z);
        }
    }
}
