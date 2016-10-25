package com.xunlei.common.pay.c.b;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.base.XLUtilTools;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.common.pay.a.b;
import com.xunlei.common.pay.a.c;
import com.xunlei.common.pay.a.e;
import com.xunlei.common.pay.a.f;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.common.pay.param.XLWxContractParam;
import com.xunlei.xllib.R;
import org.apache.http.Header;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XLWxContractCancleTask.java
public class a extends e {
    private static final String c;
    private static int d = 0;
    private static int f = 0;
    private static int g = 1;
    private XLWxContractParam e;
    private int h;
    private b i;

    public a() {
        this.e = null;
        this.h = 0;
        this.i = null;
    }

    static {
        c = a.class.getSimpleName();
        d = 268435463;
    }

    public final void c() {
        switch (this.h) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                String c = this.i.c();
                XLLog.v(c, new StringBuilder("getPayBusinessOrder url = ").append(c).toString());
                f.a().e().get(f.a().d(), c, null, new BaseHttpClientListener() {
                    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                        String str = new String(bArr);
                        XLLog.v(c, new StringBuilder("getPayBusinessOrder buffer = ").append(str).toString());
                        try {
                            int optInt = new JSONObject(XLUtilTools.parseJSONPString(str)).optInt("ret", R.styleable.AppCompatTheme_dividerHorizontal);
                            if (optInt == 99) {
                                optInt = XLPayErrorCode.XLP_GATE_NOT_CONTRACT;
                            }
                            if (optInt != 0) {
                                a.a(a.this, optInt);
                            } else {
                                a.a(a.this, 0);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            XLLog.e(c, "getPayBusinessOrder json error.");
                            a.this.h = 1;
                            a.a(a.this, R.styleable.AppCompatTheme_dividerHorizontal);
                        }
                    }

                    public final void onFailure(Throwable th, byte[] bArr) {
                        XLLog.e(c, new StringBuilder("getPayBusinessOrder error = ").append(th.getMessage()).toString());
                        a.a(a.this, R.styleable.AppCompatTheme_dividerHorizontal);
                    }
                });
            default:
                break;
        }
    }

    public final void a(XLPayParam xLPayParam) {
        this.e = (XLWxContractParam) xLPayParam;
        this.e.mPayType = d;
        this.e.mOperateType = 8194;
        this.i = c.a(this.e);
    }

    public final XLPayParam d() {
        return this.e;
    }

    private void h() {
        String c = this.i.c();
        XLLog.v(c, new StringBuilder("getPayBusinessOrder url = ").append(c).toString());
        f.a().e().get(f.a().d(), c, null, new BaseHttpClientListener() {
            public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                String str = new String(bArr);
                XLLog.v(c, new StringBuilder("getPayBusinessOrder buffer = ").append(str).toString());
                try {
                    int optInt = new JSONObject(XLUtilTools.parseJSONPString(str)).optInt("ret", R.styleable.AppCompatTheme_dividerHorizontal);
                    if (optInt == 99) {
                        optInt = XLPayErrorCode.XLP_GATE_NOT_CONTRACT;
                    }
                    if (optInt != 0) {
                        a.a(a.this, optInt);
                    } else {
                        a.a(a.this, 0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    XLLog.e(c, "getPayBusinessOrder json error.");
                    a.this.h = 1;
                    a.a(a.this, R.styleable.AppCompatTheme_dividerHorizontal);
                }
            }

            public final void onFailure(Throwable th, byte[] bArr) {
                XLLog.e(c, new StringBuilder("getPayBusinessOrder error = ").append(th.getMessage()).toString());
                a.a(a.this, R.styleable.AppCompatTheme_dividerHorizontal);
            }
        });
    }

    private void a(int i) {
        XLContractResp xLContractResp = new XLContractResp();
        xLContractResp.mContractType = 4097;
        xLContractResp.mOperateType = 8194;
        f.a().a(Integer.valueOf(d), Integer.valueOf(i), XLPayErrorCode.getErrorDesc(i), f(), Integer.valueOf(b()), xLContractResp);
    }

    static /* synthetic */ void a(a aVar, int i) {
        XLContractResp xLContractResp = new XLContractResp();
        xLContractResp.mContractType = 4097;
        xLContractResp.mOperateType = 8194;
        f.a().a(Integer.valueOf(d), Integer.valueOf(i), XLPayErrorCode.getErrorDesc(i), aVar.f(), Integer.valueOf(aVar.b()), xLContractResp);
    }
}
