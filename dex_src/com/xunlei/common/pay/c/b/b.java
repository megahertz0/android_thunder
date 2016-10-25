package com.xunlei.common.pay.c.b;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.base.XLUtilTools;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLContractor$XLContractStatus;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.common.pay.XLPayErrorCode;
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

// compiled from: XLWxContractQueryTask.java
public class b extends e {
    private static final String c;
    private static int d = 0;
    private static int g = 0;
    private static int h = 1;
    private XLWxContractParam e;
    private XLOnPayListener f;
    private int i;
    private com.xunlei.common.pay.a.b j;

    public b() {
        this.e = null;
        this.f = null;
        this.i = 0;
        this.j = null;
    }

    static {
        c = b.class.getSimpleName();
        d = 268435463;
    }

    public final void c() {
        switch (this.i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                String d = this.j.d();
                XLLog.v(c, new StringBuilder("getPayBusinessOrder url = ").append(d).toString());
                f.a().e().get(f.a().d(), d, null, new BaseHttpClientListener() {
                    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                        String str = new String(bArr);
                        XLLog.v(c, new StringBuilder("getPayBusinessOrder buffer = ").append(str).toString());
                        try {
                            JSONObject jSONObject = new JSONObject(XLUtilTools.parseJSONPString(str));
                            if (jSONObject.optInt("ret", R.styleable.AppCompatTheme_activityChooserViewStyle) != 0) {
                                b.a(b.this, R.styleable.AppCompatTheme_activityChooserViewStyle, 0);
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
                            b.a(b.this, 0, i2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            XLLog.e(c, "getPayBusinessOrder json error.");
                            b.this.i = 1;
                            b.a(b.this, R.styleable.AppCompatTheme_activityChooserViewStyle, 0);
                        }
                    }

                    public final void onFailure(Throwable th, byte[] bArr) {
                        XLLog.e(c, new StringBuilder("getPayBusinessOrder error = ").append(th.getMessage()).toString());
                        b.a(b.this, R.styleable.AppCompatTheme_activityChooserViewStyle, 0);
                    }
                });
            default:
                break;
        }
    }

    public final void a(XLPayParam xLPayParam) {
        this.e = (XLWxContractParam) xLPayParam;
        this.e.mPayType = d;
        this.e.mOperateType = 8193;
        this.j = c.a(this.e);
    }

    public final XLPayParam d() {
        return this.e;
    }

    public final void b(XLOnPayListener xLOnPayListener) {
        this.f = xLOnPayListener;
    }

    private void h() {
        String d = this.j.d();
        XLLog.v(c, new StringBuilder("getPayBusinessOrder url = ").append(d).toString());
        f.a().e().get(f.a().d(), d, null, new BaseHttpClientListener() {
            public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                String str = new String(bArr);
                XLLog.v(c, new StringBuilder("getPayBusinessOrder buffer = ").append(str).toString());
                try {
                    JSONObject jSONObject = new JSONObject(XLUtilTools.parseJSONPString(str));
                    if (jSONObject.optInt("ret", R.styleable.AppCompatTheme_activityChooserViewStyle) != 0) {
                        b.a(b.this, R.styleable.AppCompatTheme_activityChooserViewStyle, 0);
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
                    b.a(b.this, 0, i2);
                } catch (JSONException e) {
                    e.printStackTrace();
                    XLLog.e(c, "getPayBusinessOrder json error.");
                    b.this.i = 1;
                    b.a(b.this, R.styleable.AppCompatTheme_activityChooserViewStyle, 0);
                }
            }

            public final void onFailure(Throwable th, byte[] bArr) {
                XLLog.e(c, new StringBuilder("getPayBusinessOrder error = ").append(th.getMessage()).toString());
                b.a(b.this, R.styleable.AppCompatTheme_activityChooserViewStyle, 0);
            }
        });
    }

    private void a(int i, int i2) {
        XLContractResp xLContractResp = new XLContractResp();
        xLContractResp.mContractType = 4097;
        xLContractResp.mOperateType = 8193;
        xLContractResp.mContractStatus = i2;
        if (this.f == null) {
            f.a().a(Integer.valueOf(d), Integer.valueOf(i), XLPayErrorCode.getErrorDesc(i), f(), Integer.valueOf(b()), xLContractResp);
            return;
        }
        this.f.onContractOperate(i, XLPayErrorCode.getErrorDesc(i), f(), b(), xLContractResp);
    }

    static /* synthetic */ void a(b bVar, int i, int i2) {
        XLContractResp xLContractResp = new XLContractResp();
        xLContractResp.mContractType = 4097;
        xLContractResp.mOperateType = 8193;
        xLContractResp.mContractStatus = i2;
        if (bVar.f == null) {
            f.a().a(Integer.valueOf(d), Integer.valueOf(i), XLPayErrorCode.getErrorDesc(i), bVar.f(), Integer.valueOf(bVar.b()), xLContractResp);
            return;
        }
        bVar.f.onContractOperate(i, XLPayErrorCode.getErrorDesc(i), bVar.f(), bVar.b(), xLContractResp);
    }
}
