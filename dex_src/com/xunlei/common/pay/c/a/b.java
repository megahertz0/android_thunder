package com.xunlei.common.pay.c.a;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.base.XLUtilTools;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.encrypt.URLCoder;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.common.pay.a.c;
import com.xunlei.common.pay.a.e;
import com.xunlei.common.pay.a.f;
import com.xunlei.common.pay.param.XLAliPayContractParam;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.xllib.R;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.Header;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XLAliPayContractTask.java
public class b extends e {
    private static final String d;
    private static int e = 0;
    private static int h = 0;
    private static int i = 1;
    private static int j = 2;
    private static int k = 3;
    private XLOnPayListener c;
    private XLAliPayContractParam f;
    private XLContractResp g;
    private int l;
    private String m;
    private com.xunlei.common.pay.a.b n;

    public b() {
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
                        b.this.g = xLContractResp;
                    }
                    b.this.l = 1;
                    b.e().c().post(new Runnable() {
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
    }

    static {
        d = b.class.getSimpleName();
        e = 268435462;
    }

    public final void c() {
        if (this.l == 0) {
            XLAliPayContractParam xLAliPayContractParam = new XLAliPayContractParam();
            xLAliPayContractParam.mVasType = this.f.mVasType;
            xLAliPayContractParam.mOrderType = this.f.mOrderType;
            xLAliPayContractParam.mMonth = this.f.mMonth;
            xLAliPayContractParam.mUserId = this.f.mUserId;
            xLAliPayContractParam.mSessionId = this.f.mSessionId;
            xLAliPayContractParam.mQueryAllContract = true;
            e dVar = new d();
            dVar.a();
            dVar.a(xLAliPayContractParam);
            dVar.b(this.c);
            dVar.a("xl-query-contract");
            f.a().a(dVar);
        } else if (this.l == 1) {
            if (this.g == null) {
                this.l = 3;
                a((int) XLPayErrorCode.XLP_CONTRACT_QUERY_ERROR);
            } else if (this.g.mContractStatus != 12290) {
                this.l = 3;
                a((int) XLPayErrorCode.XLP_CONTRACT_EXIST);
            } else if (XLUtilTools.isApplicationInstalled(f.a().d(), "com.eg.android.AlipayGphone")) {
                a = this.n.a();
                XLLog.v(d, new StringBuilder("getPayBusinessOrder url = ").append(a).toString());
                f.a().e().get(f.a().d(), a, null, new BaseHttpClientListener() {
                    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                        String str = new String(bArr);
                        XLLog.v(d, new StringBuilder("getPayBusinessOrder buffer = ").append(str).toString());
                        try {
                            JSONObject jSONObject = new JSONObject(XLUtilTools.parseJSONPString(str));
                            if (jSONObject.optInt("ret", XLPayErrorCode.XLP_GET_ORDER_ERROR) != 0) {
                                b.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
                                return;
                            }
                            str = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND);
                            if (TextUtils.isEmpty(str)) {
                                b.this.a((int) R.styleable.AppCompatTheme_radioButtonStyle);
                                return;
                            }
                            b.this.m = str;
                            b.this.l = SimpleLog.LOG_LEVEL_DEBUG;
                            b.e().c().post(new Runnable() {
                                public final void run() {
                                    AnonymousClass_2.this.c();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                            XLLog.e(d, "getPayBusinessOrder json error.");
                            b.this.l = MqttConnectOptions.MQTT_VERSION_3_1;
                            b.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
                        }
                    }

                    public final void onFailure(Throwable th, byte[] bArr) {
                        XLLog.e(d, new StringBuilder("getPayBusinessOrder error = ").append(th.getMessage()).toString());
                        b.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
                    }
                });
            } else {
                a((int) R.styleable.AppCompatTheme_checkboxStyle);
            }
        } else if (this.l == 2) {
            a = URLCoder.encode(this.m, CharsetConvert.UTF_8);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(new StringBuilder("alipays://platformapi/startapp?appId=20000067&url=").append(a).toString()));
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            f.a().d().startActivity(intent);
            a(0);
        }
    }

    public final void a(XLPayParam xLPayParam) {
        this.f = (XLAliPayContractParam) xLPayParam;
        this.f.mPayType = e;
        this.f.mOperateType = 8192;
        this.n = c.a(this.f);
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
                        b.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
                        return;
                    }
                    str = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_EXTEND);
                    if (TextUtils.isEmpty(str)) {
                        b.this.a((int) R.styleable.AppCompatTheme_radioButtonStyle);
                        return;
                    }
                    b.this.m = str;
                    b.this.l = SimpleLog.LOG_LEVEL_DEBUG;
                    b.e().c().post(new Runnable() {
                        public final void run() {
                            AnonymousClass_2.this.c();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    XLLog.e(d, "getPayBusinessOrder json error.");
                    b.this.l = MqttConnectOptions.MQTT_VERSION_3_1;
                    b.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
                }
            }

            public final void onFailure(Throwable th, byte[] bArr) {
                XLLog.e(d, new StringBuilder("getPayBusinessOrder error = ").append(th.getMessage()).toString());
                b.this.a((int) XLPayErrorCode.XLP_GET_ORDER_ERROR);
            }
        });
    }

    private void a(int i) {
        XLContractResp xLContractResp = new XLContractResp();
        xLContractResp.mContractType = 4096;
        xLContractResp.mOperateType = 8192;
        f.a().a(Integer.valueOf(e), Integer.valueOf(i), XLPayErrorCode.getErrorDesc(i), f(), Integer.valueOf(b()), xLContractResp);
    }

    private static void a(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(new StringBuilder("alipays://platformapi/startapp?appId=20000067&url=").append(str).toString()));
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        f.a().d().startActivity(intent);
    }

    private void i() {
        XLAliPayContractParam xLAliPayContractParam = new XLAliPayContractParam();
        xLAliPayContractParam.mVasType = this.f.mVasType;
        xLAliPayContractParam.mOrderType = this.f.mOrderType;
        xLAliPayContractParam.mMonth = this.f.mMonth;
        xLAliPayContractParam.mUserId = this.f.mUserId;
        xLAliPayContractParam.mSessionId = this.f.mSessionId;
        xLAliPayContractParam.mQueryAllContract = true;
        e dVar = new d();
        dVar.a();
        dVar.a(xLAliPayContractParam);
        dVar.b(this.c);
        dVar.a("xl-query-contract");
        f.a().a(dVar);
    }
}
