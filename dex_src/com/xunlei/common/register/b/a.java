package com.xunlei.common.register.b;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.common.register.XLRegisterUtil;
import com.xunlei.common.register.a.b;
import com.xunlei.common.register.a.c;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.TnetStatusCode;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XLCheckBindTask.java
public final class a extends b {
    private String b;
    private int c;

    public a(c cVar) {
        super(cVar);
        this.b = BuildConfig.VERSION_NAME;
        this.c = 1;
    }

    public final void d(String str) {
        if (str != null) {
            this.b = str;
        }
    }

    public final void a(int i) {
        this.c = i;
    }

    public final void e() {
        if (this.a != com.xunlei.common.register.a.b.a.c) {
            this.a = com.xunlei.common.register.a.b.a.b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("op=checkBind");
            stringBuilder.append("&");
            stringBuilder.append("from=");
            stringBuilder.append(b().g());
            stringBuilder.append("&");
            stringBuilder.append(new StringBuilder("account=").append(this.b).toString());
            stringBuilder.append("&");
            stringBuilder.append("type=");
            stringBuilder.append(this.c);
            stringBuilder.append("&");
            stringBuilder.append("businessType=");
            stringBuilder.append(XLRegisterUtil.getInstance().getBusinessType());
            stringBuilder.append("&");
            stringBuilder.append("appName=");
            stringBuilder.append(d());
            stringBuilder.append("&");
            stringBuilder.append("sdkVersion=");
            stringBuilder.append(b().e());
            stringBuilder.append("&");
            stringBuilder.append("clientVersion=");
            stringBuilder.append(b().f());
            stringBuilder.append("&");
            stringBuilder.append("deviceID=");
            stringBuilder.append(d.a());
            stringBuilder.append("&");
            stringBuilder.append("callback=");
            com.xunlei.common.register.a.a.a().b(null, stringBuilder.toString(), new BaseHttpClientListener() {
                public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    if (i == 200) {
                        String str = new String(bArr);
                        XLLog.v("XLCheckBindTask", str);
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            int optInt = jSONObject.optInt("result");
                            int i2 = jSONObject.getInt("binded");
                            a.this.a(SimpleLog.LOG_LEVEL_DEBUG, new Object[]{Integer.valueOf(optInt), XLRegErrorCode.getErrorDesc(optInt), Integer.valueOf(a.this.a()), Integer.valueOf(i2)});
                            return;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            a.this.a(SimpleLog.LOG_LEVEL_DEBUG, new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), XLRegErrorCode.getErrorDesc(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), Integer.valueOf(a.this.a()), Integer.valueOf(-1)});
                        }
                    }
                    XLLog.v("XLCheckBindTask", new StringBuilder("error = ").append(i).toString());
                    a.this.a(SimpleLog.LOG_LEVEL_DEBUG, new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), XLRegErrorCode.getErrorDesc(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), Integer.valueOf(a.this.a()), Integer.valueOf(-1)});
                }

                public final void onFailure(Throwable th, byte[] bArr) {
                    XLLog.e("XLCheckBindTask", new StringBuilder("error = ").append(th.getMessage()).toString());
                    a.this.a(SimpleLog.LOG_LEVEL_DEBUG, new Object[]{Integer.valueOf(-1), XLRegErrorCode.getErrorDesc(-1), Integer.valueOf(a.this.a()), Integer.valueOf(-1)});
                }
            });
            this.a = com.xunlei.common.register.a.b.a.d;
        }
    }
}
