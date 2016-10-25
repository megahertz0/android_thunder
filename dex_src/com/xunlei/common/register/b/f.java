package com.xunlei.common.register.b;

import com.umeng.message.util.HttpRequest;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.common.register.XLRegisterUtil;
import com.xunlei.common.register.a.b;
import com.xunlei.common.register.a.b.a;
import com.xunlei.common.register.a.c;
import com.xunlei.xiazaibao.BuildConfig;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.Header;
import org.apache.http.HeaderElement;

// compiled from: XLGetVerifyCodeTask.java
public final class f extends b {
    private String b;

    public f(c cVar) {
        super(cVar);
        this.b = "M";
    }

    public final void d(String str) {
        if (!c(str)) {
            this.b = str;
        }
    }

    public final void e() {
        if (this.a != a.c) {
            this.a = a.b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("op=validateImg");
            stringBuilder.append("&");
            stringBuilder.append("from=");
            stringBuilder.append(b().g());
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
            stringBuilder.append(new StringBuilder("size=").append(this.b).toString());
            com.xunlei.common.register.a.a.a().b(null, stringBuilder.toString(), new BaseHttpClientListener() {
                public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                    XLLog.v("XLGetVerifyCodeTask", new StringBuilder("get verify code return code = ").append(i).append("#data length=").append(bArr.length).toString());
                    String str = BuildConfig.VERSION_NAME;
                    String str2 = str;
                    str = BuildConfig.VERSION_NAME;
                    String str3 = BuildConfig.VERSION_NAME;
                    for (int i2 = 0; i2 < headerArr.length; i2++) {
                        Header header = headerArr[i2];
                        if (header.getName().compareToIgnoreCase("Set-Cookie") == 0) {
                            HeaderElement[] elements = header.getElements();
                            String str4 = str;
                            str = str3;
                            for (int i3 = 0; i3 < elements.length; i3++) {
                                HeaderElement headerElement = elements[i3];
                                if (headerElement.getName().compareToIgnoreCase("VERIFY_KEY") == 0) {
                                    str4 = headerElement.getValue();
                                } else if (headerElement.getName().compareToIgnoreCase("VERIFY_TYPE") == 0) {
                                    str = headerElement.getValue();
                                }
                            }
                            str3 = str;
                            str = str4;
                        }
                        if (header.getName().compareToIgnoreCase(HttpRequest.l) == 0) {
                            str2 = header.getValue();
                        }
                        XLLog.v("XLGetVerifyCodeTask", "get verify code.");
                    }
                    f.this.a(SimpleLog.LOG_LEVEL_ERROR, new Object[]{Integer.valueOf(XLRegErrorCode.REG_SUCCEED), XLRegErrorCode.getErrorDesc(XLRegErrorCode.REG_SUCCEED), Integer.valueOf(f.this.a()), bArr, f.this.b, str2, str, str3});
                }

                public final void onFailure(Throwable th, byte[] bArr) {
                    XLLog.e("XLGetVerifyCodeTask", new StringBuilder("error code = ").append(th.getMessage()).toString());
                    f.this.a(SimpleLog.LOG_LEVEL_ERROR, new Object[]{Integer.valueOf(-1), XLRegErrorCode.getErrorDesc(-1), Integer.valueOf(f.this.a()), null, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME});
                }
            });
            this.a = a.d;
        }
    }
}
