package com.xunlei.common.member.c;

import android.os.Bundle;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.xiazaibao.BuildConfig;
import org.apache.http.Header;
import org.apache.http.client.HttpResponseException;

// compiled from: UserVerifyedCodeTask.java
final class s$1 implements BaseHttpClientListener {
    private /* synthetic */ s a;

    s$1(s sVar) {
        this.a = sVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        s.a(this.a, "/verify/%s/%s?t=%s&header=true");
        Bundle bundle = new Bundle();
        String str = new String(bArr);
        XLLog.v("UserVerifyedCodeTask", new StringBuilder("verify code ").append(str).toString());
        int intValue = Integer.valueOf(str.replace("\r\n", BuildConfig.VERSION_NAME)).intValue();
        if (intValue == 200) {
            intValue = 0;
        }
        bundle.putInt("errorCode", intValue);
        bundle.putString("action", "UserVerifyedCodeTask");
        bundle.putString("errorDesc", str);
        this.a.g().a(this.a, bundle);
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.e("UserVerifyedCodeTask", new StringBuilder("error code = ").append(th.getMessage()).toString());
        s.a(this.a, "/verify/%s/%s?t=%s&header=true");
        int i = 16781312;
        String str = "\u672a\u77e5\u9519\u8bef";
        if (th instanceof HttpResponseException) {
            HttpResponseException httpResponseException = (HttpResponseException) th;
            i = httpResponseException.getStatusCode();
            str = httpResponseException.getMessage();
        }
        Bundle bundle = new Bundle();
        bundle.putString("action", "UserVerifyedCodeTask");
        bundle.putInt("errorCode", i);
        bundle.putString("errorDesc", str);
        this.a.g().a(this.a, bundle);
    }
}
