package com.xunlei.common.pay.c.b;

import android.text.TextUtils;
import com.tencent.mm.sdk.modelbiz.OpenWebview.Req;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.base.XLUtilTools;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.common.pay.a.b;
import com.xunlei.common.pay.a.e;
import com.xunlei.common.pay.a.f;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.common.pay.param.XLWxContractParam;
import com.xunlei.xllib.R;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.Header;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XLWxContractTask.java
public class c extends e {
    private static final String d;
    private static int e = 0;
    private static int h = 0;
    private static int i = 1;
    private static int j = 2;
    private static int k = 3;
    private XLOnPayListener c;
    private XLWxContractParam f;
    private XLContractResp g;
    private int l;
    private String m;
    private b n;
    private IWXAPI o;

    public c() {
        this.c = new XLOnPayListener() {
            public final void onWxPay(int i, String str, Object obj, int i2) {
            }

            public final void onNbPay(int i, String str, Object obj, int i2) {
            }

            public final void onGetPrice(int i, String str, Object obj, int i2, String str2) {
            }

            public final void onContractOperate(int i, String str, Object obj, int i2, XLContractResp xLContractResp) {
                if (xLContractResp.mOperateType == 8193) {
                    if (i == 0) {
                        c.this.g = xLContractResp;
                    }
                    c.this.l = 1;
                    c.e().c().post(new Runnable() {
                        public final void run() {
                            AnonymousClass_1.this.c();
                        }
                    });
                }
            }

            public final void onAliPay(int i, String str, Object obj, int i2) {
            }
        };
        this.f = null;
        this.g = null;
        this.l = 0;
        this.m = null;
        this.n = null;
        this.o = null;
    }

    static {
        d = c.class.getSimpleName();
        e = 268435463;
    }

    public final void c() {
        if (this.l == 0) {
            XLWxContractParam xLWxContractParam = new XLWxContractParam();
            xLWxContractParam.mVasType = this.f.mVasType;
            xLWxContractParam.mOrderType = this.f.mOrderType;
            xLWxContractParam.mMonth = this.f.mMonth;
            xLWxContractParam.mUserId = this.f.mUserId;
            xLWxContractParam.mSessionId = this.f.mSessionId;
            xLWxContractParam.mQueryAllContract = true;
            e bVar = new b();
            bVar.a();
            bVar.a(xLWxContractParam);
            bVar.b(this.c);
            bVar.a("xl-query-contract");
            f.a().a(bVar);
        } else if (this.l == 1) {
            if (this.g == null) {
                this.l = 3;
                a((int) XLPayErrorCode.XLP_CONTRACT_QUERY_ERROR);
            } else if (this.g.mContractStatus != 12290) {
                this.l = 3;
                a((int) XLPayErrorCode.XLP_CONTRACT_EXIST);
            } else if (this.o.isWXAppInstalled()) {
                a = this.n.a();
                XLLog.v(d, new StringBuilder("getPayBusinessOrder url = ").append(a).toString());
                f.a().e().get(f.a().d(), a, null, new BaseHttpClientListener() {
                    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                        String str = new String(bArr);
                        XLLog.v(d, new StringBuilder("getPayBusinessOrder buffer = ").append(str).toString());
                        try {
                            JSONObject jSONObject = new JSONObject(XLUtilTools.parseJSONPString(str));
                            if (jSONObject.optInt("ret", XLPayErrorCode.XLP_GET_ORDER_ERROR) != 0) {
                                c.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
                                return;
                            }
                            str = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND);
                            if (TextUtils.isEmpty(str)) {
                                c.this.a((int) R.styleable.AppCompatTheme_dividerVertical);
                                return;
                            }
                            c.this.m = str;
                            c.this.l = SimpleLog.LOG_LEVEL_DEBUG;
                            c.e().c().post(new Runnable() {
                                public final void run() {
                                    AnonymousClass_2.this.c();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                            XLLog.e(d, "getPayBusinessOrder json error.");
                            c.this.l = MqttConnectOptions.MQTT_VERSION_3_1;
                            c.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
                        }
                    }

                    public final void onFailure(Throwable th, byte[] bArr) {
                        XLLog.e(d, new StringBuilder("getPayBusinessOrder error = ").append(th.getMessage()).toString());
                        c.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
                    }
                });
            } else {
                a((int) R.styleable.AppCompatTheme_borderlessButtonStyle);
            }
        } else if (this.l == 2) {
            a = this.m;
            Req req = new Req();
            req.url = a;
            req.transaction = new StringBuilder("xl_sdk_contract#").append(b()).toString();
            this.o.sendReq(req);
            a(0);
        }
    }

    public final void a(XLPayParam xLPayParam) {
        this.f = (XLWxContractParam) xLPayParam;
        this.f.mPayType = e;
        this.f.mOperateType = 8192;
        this.n = com.xunlei.common.pay.a.c.a(this.f);
        this.o = WXAPIFactory.createWXAPI(f.a().d(), this.f.mWxAppId, false);
        this.o.registerApp(this.f.mWxAppId);
        this.l = 0;
    }

    public final XLPayParam d() {
        return this.f;
    }

    private void h() {
        String a = this.n.a();
        XLLog.v(d, new StringBuilder("getPayBusinessOrder url = ").append(a).toString());
        f.a().e().get(f.a().d(), a, null, new BaseHttpClientListener() {
            public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                String str = new String(bArr);
                XLLog.v(d, new StringBuilder("getPayBusinessOrder buffer = ").append(str).toString());
                try {
                    JSONObject jSONObject = new JSONObject(XLUtilTools.parseJSONPString(str));
                    if (jSONObject.optInt("ret", XLPayErrorCode.XLP_GET_ORDER_ERROR) != 0) {
                        c.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
                        return;
                    }
                    str = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND);
                    if (TextUtils.isEmpty(str)) {
                        c.this.a((int) R.styleable.AppCompatTheme_dividerVertical);
                        return;
                    }
                    c.this.m = str;
                    c.this.l = SimpleLog.LOG_LEVEL_DEBUG;
                    c.e().c().post(new Runnable() {
                        public final void run() {
                            AnonymousClass_2.this.c();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    XLLog.e(d, "getPayBusinessOrder json error.");
                    c.this.l = MqttConnectOptions.MQTT_VERSION_3_1;
                    c.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
                }
            }

            public final void onFailure(Throwable th, byte[] bArr) {
                XLLog.e(d, new StringBuilder("getPayBusinessOrder error = ").append(th.getMessage()).toString());
                c.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
            }
        });
    }

    private void a(int i) {
        XLContractResp xLContractResp = new XLContractResp();
        xLContractResp.mContractType = 4097;
        xLContractResp.mOperateType = 8192;
        f.a().a(Integer.valueOf(e), Integer.valueOf(i), XLPayErrorCode.getErrorDesc(i), f(), Integer.valueOf(b()), xLContractResp);
    }

    private void a(String str) {
        Req req = new Req();
        req.url = str;
        req.transaction = new StringBuilder("xl_sdk_contract#").append(b()).toString();
        this.o.sendReq(req);
    }

    private void i() {
        XLWxContractParam xLWxContractParam = new XLWxContractParam();
        xLWxContractParam.mVasType = this.f.mVasType;
        xLWxContractParam.mOrderType = this.f.mOrderType;
        xLWxContractParam.mMonth = this.f.mMonth;
        xLWxContractParam.mUserId = this.f.mUserId;
        xLWxContractParam.mSessionId = this.f.mSessionId;
        xLWxContractParam.mQueryAllContract = true;
        e bVar = new b();
        bVar.a();
        bVar.a(xLWxContractParam);
        bVar.b(this.c);
        bVar.a("xl-query-contract");
        f.a().a(bVar);
    }
}
