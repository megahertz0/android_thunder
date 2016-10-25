package com.xunlei.common.pay.c.a;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.base.XLUtilTools;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLContractor$XLContractStatus;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.common.pay.a.b;
import com.xunlei.common.pay.a.c;
import com.xunlei.common.pay.a.e;
import com.xunlei.common.pay.a.f;
import com.xunlei.common.pay.param.XLAliPayContractParam;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.xllib.R;
import org.apache.http.Header;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XLAlipayContractQueryTask.java
public class d extends e {
    private static final String c;
    private static int d = 0;
    private static int f = 0;
    private static int g = 1;
    private XLAliPayContractParam e;
    private int h;
    private b i;
    private XLOnPayListener j;

    public d() {
        this.e = null;
        this.h = 0;
        this.i = null;
        this.j = null;
    }

    static {
        c = d.class.getSimpleName();
        d = 268435462;
    }

    public final void c() {
        switch (this.h) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                String d = this.i.d();
                XLLog.v(c, new StringBuilder("getPayBusinessOrder url = ").append(d).toString());
                f.a().e().get(f.a().d(), d, null, new BaseHttpClientListener() {
                    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                        String str = new String(bArr);
                        XLLog.v(c, new StringBuilder("getPayBusinessOrder buffer = ").append(str).toString());
                        try {
                            JSONObject jSONObject = new JSONObject(XLUtilTools.parseJSONPString(str));
                            if (jSONObject.optInt("ret", R.styleable.AppCompatTheme_editTextStyle) != 0) {
                                d.a(d.this, R.styleable.AppCompatTheme_editTextStyle, 0);
                                return;
                            }
                            int i2;
                            String optString = jSONObject.optString("status");
                            Object obj = XLContractor$XLContractStatus.XL_CONTRACT_SYSTEMERROR;
                            if ("NORMAL".compareTo(optString) == 0) {
                                obj = XLContractor$XLContractStatus.XL_CONTRACT_NORMAL;
                            }
                            if ("STOP".compareTo(optString) == 0) {
                                obj = XLContractor$XLContractStatus.XL_CONTRACT_STOP;
                            }
                            if ("UNSIGN".compareTo(optString) == 0) {
                                i2 = XLContractor$XLContractStatus.XL_CONTRACT_NO;
                            }
                            d.a(d.this, 0, i2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            XLLog.e(c, "getPayBusinessOrder json error.");
                            d.this.h = 1;
                            d.a(d.this, R.styleable.AppCompatTheme_editTextStyle, 0);
                        }
                    }

                    public final void onFailure(Throwable th, byte[] bArr) {
                        XLLog.e(c, new StringBuilder("getPayBusinessOrder error = ").append(th.getMessage()).toString());
                        d.a(d.this, R.styleable.AppCompatTheme_editTextStyle, 0);
                    }
                });
            default:
                break;
        }
    }

    public final void a(XLPayParam xLPayParam) {
        this.e = (XLAliPayContractParam) xLPayParam;
        this.e.mPayType = d;
        this.e.mOperateType = 8193;
        this.i = c.a(this.e);
    }

    public final XLPayParam d() {
        return this.e;
    }

    public final void b(XLOnPayListener xLOnPayListener) {
        this.j = xLOnPayListener;
    }

    private void h() {
        String d = this.i.d();
        XLLog.v(c, new StringBuilder("getPayBusinessOrder url = ").append(d).toString());
        f.a().e().get(f.a().d(), d, null, new BaseHttpClientListener() {
            public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                String str = new String(bArr);
                XLLog.v(c, new StringBuilder("getPayBusinessOrder buffer = ").append(str).toString());
                try {
                    JSONObject jSONObject = new JSONObject(XLUtilTools.parseJSONPString(str));
                    if (jSONObject.optInt("ret", R.styleable.AppCompatTheme_editTextStyle) != 0) {
                        d.a(d.this, R.styleable.AppCompatTheme_editTextStyle, 0);
                        return;
                    }
                    int i2;
                    String optString = jSONObject.optString("status");
                    Object obj = XLContractor$XLContractStatus.XL_CONTRACT_SYSTEMERROR;
                    if ("NORMAL".compareTo(optString) == 0) {
                        obj = XLContractor$XLContractStatus.XL_CONTRACT_NORMAL;
                    }
                    if ("STOP".compareTo(optString) == 0) {
                        obj = XLContractor$XLContractStatus.XL_CONTRACT_STOP;
                    }
                    if ("UNSIGN".compareTo(optString) == 0) {
                        i2 = XLContractor$XLContractStatus.XL_CONTRACT_NO;
                    }
                    d.a(d.this, 0, i2);
                } catch (JSONException e) {
                    e.printStackTrace();
                    XLLog.e(c, "getPayBusinessOrder json error.");
                    d.this.h = 1;
                    d.a(d.this, R.styleable.AppCompatTheme_editTextStyle, 0);
                }
            }

            public final void onFailure(Throwable th, byte[] bArr) {
                XLLog.e(c, new StringBuilder("getPayBusinessOrder error = ").append(th.getMessage()).toString());
                d.a(d.this, R.styleable.AppCompatTheme_editTextStyle, 0);
            }
        });
    }

    private void a(int i, int i2) {
        XLContractResp xLContractResp = new XLContractResp();
        xLContractResp.mContractType = 4096;
        xLContractResp.mOperateType = 8193;
        xLContractResp.mContractStatus = i2;
        if (this.j == null) {
            f.a().a(Integer.valueOf(d), Integer.valueOf(i), XLPayErrorCode.getErrorDesc(i), f(), Integer.valueOf(b()), xLContractResp);
            return;
        }
        this.j.onContractOperate(i, XLPayErrorCode.getErrorDesc(i), f(), b(), xLContractResp);
    }

    static /* synthetic */ void a(d dVar, int i, int i2) {
        XLContractResp xLContractResp = new XLContractResp();
        xLContractResp.mContractType = 4096;
        xLContractResp.mOperateType = 8193;
        xLContractResp.mContractStatus = i2;
        if (dVar.j == null) {
            f.a().a(Integer.valueOf(d), Integer.valueOf(i), XLPayErrorCode.getErrorDesc(i), dVar.f(), Integer.valueOf(dVar.b()), xLContractResp);
            return;
        }
        dVar.j.onContractOperate(i, XLPayErrorCode.getErrorDesc(i), dVar.f(), dVar.b(), xLContractResp);
    }
}
