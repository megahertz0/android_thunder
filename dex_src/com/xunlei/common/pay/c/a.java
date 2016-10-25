package com.xunlei.common.pay.c;

import com.alipay.sdk.app.PayTask;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.base.XLUtilTools;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.common.pay.a.b;
import com.xunlei.common.pay.a.c;
import com.xunlei.common.pay.a.d;
import com.xunlei.common.pay.a.e;
import com.xunlei.common.pay.a.f;
import com.xunlei.common.pay.param.XLAlipayParam;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.xllib.R;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XLAlipayTask.java
public class a extends e {
    private static final String c;
    private static int d = 0;
    private static int e = 1;
    private static int f = 2;
    private XLAlipayParam g;
    private b h;
    private int i;
    private String j;

    static {
        c = a.class.getSimpleName();
    }

    public a(int i) {
        this.g = null;
        this.h = null;
        this.i = 0;
        this.j = null;
        this.a = i;
    }

    public final void c() {
        if (this.i == 0) {
            String a = this.h.a();
            XLLog.v(c, new StringBuilder("getPayBusinessOrder url = ").append(a).toString());
            f.a().e().get(f.a().d(), a, null, new BaseHttpClientListener() {
                public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    String str = new String(bArr);
                    XLLog.v(c, new StringBuilder("getPayBusinessOrder buffer = ").append(str).toString());
                    try {
                        JSONObject jSONObject = new JSONObject(XLUtilTools.parseJSONPString(str));
                        int optInt = jSONObject.optInt("ret", XLPayErrorCode.XLP_GET_ORDER_ERROR);
                        if (optInt != 0) {
                            a.this.a(optInt);
                            return;
                        }
                        str = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND);
                        if (str != null) {
                            a.this.j = str;
                            a.this.i = 1;
                            a.e().c().post(new Runnable() {
                                public final void run() {
                                    AnonymousClass_1.this.c();
                                }
                            });
                            return;
                        }
                        a.this.i = SimpleLog.LOG_LEVEL_DEBUG;
                        a.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        XLLog.e(c, "getPayBusinessOrder json error.");
                    }
                }

                public final void onFailure(Throwable th, byte[] bArr) {
                    XLLog.e(c, new StringBuilder("getPayBusinessOrder error = ").append(th.getMessage()).toString());
                    a.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
                }
            });
        } else if (this.i == 1) {
            new Thread(new Runnable() {

                // compiled from: XLAlipayTask.java
                final class AnonymousClass_1 implements Runnable {
                    private /* synthetic */ d a;

                    AnonymousClass_1(d dVar) {
                        this.a = dVar;
                    }

                    public final void run() {
                        a.a(AnonymousClass_2.this.a, this.a);
                    }
                }

                public final void run() {
                    PayTask payTask = new PayTask(a.this.g.mActivity);
                    boolean z = true;
                    if (a.this.g.mNeedLoading == 0) {
                        z = false;
                    }
                    a.e().c().post(new AnonymousClass_1(new d(payTask.pay(a.this.j, z))));
                }
            }).start();
        }
    }

    public final void a(XLPayParam xLPayParam) {
        this.g = (XLAlipayParam) xLPayParam;
        this.g.mPayType = this.a;
        this.h = c.a(this.g);
    }

    public final XLPayParam d() {
        return this.g;
    }

    private void h() {
        String a = this.h.a();
        XLLog.v(c, new StringBuilder("getPayBusinessOrder url = ").append(a).toString());
        f.a().e().get(f.a().d(), a, null, new BaseHttpClientListener() {
            public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                String str = new String(bArr);
                XLLog.v(c, new StringBuilder("getPayBusinessOrder buffer = ").append(str).toString());
                try {
                    JSONObject jSONObject = new JSONObject(XLUtilTools.parseJSONPString(str));
                    int optInt = jSONObject.optInt("ret", XLPayErrorCode.XLP_GET_ORDER_ERROR);
                    if (optInt != 0) {
                        a.this.a(optInt);
                        return;
                    }
                    str = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND);
                    if (str != null) {
                        a.this.j = str;
                        a.this.i = 1;
                        a.e().c().post(new Runnable() {
                            public final void run() {
                                AnonymousClass_1.this.c();
                            }
                        });
                        return;
                    }
                    a.this.i = SimpleLog.LOG_LEVEL_DEBUG;
                    a.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
                } catch (JSONException e) {
                    e.printStackTrace();
                    XLLog.e(c, "getPayBusinessOrder json error.");
                }
            }

            public final void onFailure(Throwable th, byte[] bArr) {
                XLLog.e(c, new StringBuilder("getPayBusinessOrder error = ").append(th.getMessage()).toString());
                a.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
            }
        });
    }

    private void a(int i) {
        if (this.b != null) {
            XLLog.v(c, "xlAliPay callBack To local listener");
            this.b.onAliPay(i, XLPayErrorCode.getErrorDesc(i), f(), b());
            return;
        }
        f.a().a(Integer.valueOf(this.a), Integer.valueOf(i), XLPayErrorCode.getErrorDesc(i), f(), Integer.valueOf(b()));
    }

    private void i() {
        new Thread(new Runnable() {

            // compiled from: XLAlipayTask.java
            final class AnonymousClass_1 implements Runnable {
                private /* synthetic */ d a;

                AnonymousClass_1(d dVar) {
                    this.a = dVar;
                }

                public final void run() {
                    a.a(AnonymousClass_2.this.a, this.a);
                }
            }

            public final void run() {
                PayTask payTask = new PayTask(a.this.g.mActivity);
                boolean z = true;
                if (a.this.g.mNeedLoading == 0) {
                    z = false;
                }
                a.e().c().post(new AnonymousClass_1(new d(payTask.pay(a.this.j, z))));
            }
        }).start();
    }

    private void a(d dVar) {
        if (dVar.a.equals("9000")) {
            if (dVar.b.indexOf("success=\"true\"") != -1) {
                a(0);
                return;
            }
        }
        if (dVar.a.equals("6001")) {
            a((int) R.styleable.AppCompatTheme_buttonStyleSmall);
        } else {
            a((int) R.styleable.AppCompatTheme_buttonStyle);
        }
    }

    static /* synthetic */ void a(a aVar, d dVar) {
        if (dVar.a.equals("9000")) {
            if (dVar.b.indexOf("success=\"true\"") != -1) {
                aVar.a(0);
                return;
            }
        }
        if (dVar.a.equals("6001")) {
            aVar.a((int) R.styleable.AppCompatTheme_buttonStyleSmall);
        } else {
            aVar.a((int) R.styleable.AppCompatTheme_buttonStyle);
        }
    }
}
