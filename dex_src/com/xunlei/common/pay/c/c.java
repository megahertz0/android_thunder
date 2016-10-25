package com.xunlei.common.pay.c;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.base.XLUtilTools;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.common.pay.XLPayType;
import com.xunlei.common.pay.a.b;
import com.xunlei.common.pay.a.e;
import com.xunlei.common.pay.a.f;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.common.pay.param.XLWxPayParam;
import com.xunlei.xllib.R;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XLWxPayTask.java
public class c extends e {
    private static int c = 0;
    private static int d = 1;
    private static int e = 2;
    private static final String f;
    private XLWxPayParam g;
    private b h;
    private int i;
    private String j;

    static {
        f = c.class.getSimpleName();
    }

    public c() {
        this.g = null;
        this.h = null;
        this.i = 0;
        this.j = null;
        this.a = 268435457;
    }

    public final void a(int i) {
        int i2;
        Object obj = R.styleable.AppCompatTheme_selectableItemBackgroundBorderless;
        if (i == 0) {
            obj = null;
        }
        if (i == -2) {
            obj = R.styleable.AppCompatTheme_buttonBarButtonStyle;
        }
        if (i == -4) {
            i2 = R.styleable.AppCompatTheme_selectableItemBackground;
        }
        b(i2);
    }

    public final void c() {
        if (this.i == 0) {
            String a = this.h.a();
            XLLog.v(f, new StringBuilder("getPayBusinessOrder url = ").append(a).toString());
            f.a().e().get(f.a().d(), a, null, new BaseHttpClientListener() {
                public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    String str = new String(bArr);
                    XLLog.v(f, new StringBuilder("getPayBusinessOrder buffer = ").append(str).toString());
                    try {
                        JSONObject jSONObject = new JSONObject(XLUtilTools.parseJSONPString(str));
                        int optInt = jSONObject.optInt("ret", XLPayErrorCode.XLP_GET_ORDER_ERROR);
                        if (optInt != 0) {
                            c.this.b(optInt);
                            return;
                        }
                        str = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND);
                        if (str != null) {
                            c.this.j = str;
                            c.this.i = 1;
                            c.e().c().post(new Runnable() {
                                public final void run() {
                                    AnonymousClass_1.this.c();
                                }
                            });
                            return;
                        }
                        c.this.i = SimpleLog.LOG_LEVEL_DEBUG;
                        c.this.b(XLPayErrorCode.XLP_GET_ORDER_ERROR);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        XLLog.e(f, "getPayBusinessOrder json error.");
                    }
                }

                public final void onFailure(Throwable th, byte[] bArr) {
                    XLLog.e(f, new StringBuilder("getPayBusinessOrder error = ").append(th.getMessage()).toString());
                    c.this.b(XLPayErrorCode.XLP_GET_ORDER_ERROR);
                }
            });
        } else if (this.i == 1) {
            a(this.j);
        }
    }

    public final void a(XLPayParam xLPayParam) {
        this.g = (XLWxPayParam) xLPayParam;
        this.g.mPayType = this.a;
        this.h = com.xunlei.common.pay.a.c.a(this.g);
    }

    public final XLPayParam d() {
        return this.g;
    }

    private void h() {
        String a = this.h.a();
        XLLog.v(f, new StringBuilder("getPayBusinessOrder url = ").append(a).toString());
        f.a().e().get(f.a().d(), a, null, new BaseHttpClientListener() {
            public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                String str = new String(bArr);
                XLLog.v(f, new StringBuilder("getPayBusinessOrder buffer = ").append(str).toString());
                try {
                    JSONObject jSONObject = new JSONObject(XLUtilTools.parseJSONPString(str));
                    int optInt = jSONObject.optInt("ret", XLPayErrorCode.XLP_GET_ORDER_ERROR);
                    if (optInt != 0) {
                        c.this.b(optInt);
                        return;
                    }
                    str = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND);
                    if (str != null) {
                        c.this.j = str;
                        c.this.i = 1;
                        c.e().c().post(new Runnable() {
                            public final void run() {
                                AnonymousClass_1.this.c();
                            }
                        });
                        return;
                    }
                    c.this.i = SimpleLog.LOG_LEVEL_DEBUG;
                    c.this.b(XLPayErrorCode.XLP_GET_ORDER_ERROR);
                } catch (JSONException e) {
                    e.printStackTrace();
                    XLLog.e(f, "getPayBusinessOrder json error.");
                }
            }

            public final void onFailure(Throwable th, byte[] bArr) {
                XLLog.e(f, new StringBuilder("getPayBusinessOrder error = ").append(th.getMessage()).toString());
                c.this.b(XLPayErrorCode.XLP_GET_ORDER_ERROR);
            }
        });
    }

    private void b(int i) {
        if (this.b != null) {
            XLLog.v(f, "xlWxPay callBack To local listener");
            this.b.onWxPay(i, XLPayErrorCode.getErrorDesc(i), f(), b());
            return;
        }
        f.a().a(Integer.valueOf(XLPayType.XL_WX_PAY), Integer.valueOf(i), XLPayErrorCode.getErrorDesc(i), f(), Integer.valueOf(b()));
    }

    private boolean a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            PayReq payReq = new PayReq();
            payReq.appId = jSONObject.getString("appId");
            payReq.partnerId = jSONObject.getString("partnerId");
            payReq.prepayId = jSONObject.getString("prepayId");
            payReq.nonceStr = jSONObject.getString("nonceStr");
            payReq.timeStamp = jSONObject.getString("timeStamp");
            payReq.packageValue = jSONObject.getString("packageValue");
            payReq.sign = jSONObject.getString("sign");
            payReq.extData = String.valueOf(b());
            payReq.transaction = "xl_sdk_wx_pay_req";
            IWXAPI createWXAPI = WXAPIFactory.createWXAPI(f.a().d(), this.g.mAppId);
            if (createWXAPI.isWXAppInstalled()) {
                createWXAPI.registerApp(this.g.mAppId);
                createWXAPI.sendReq(payReq);
                return true;
            }
            b(R.styleable.AppCompatTheme_borderlessButtonStyle);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            XLLog.e(f, new StringBuilder("sendWeiXinReq error = ").append(e.getMessage()).toString());
            b(R.styleable.AppCompatTheme_buttonBarStyle);
            return false;
        }
    }
}
