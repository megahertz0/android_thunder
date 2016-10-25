package com.xunlei.common.member.c;

import android.os.Bundle;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.xiazaibao.BuildConfig;
import org.apache.http.Header;
import org.apache.http.client.HttpResponseException;

// compiled from: UserPreVerifyCodeTask.java
final class j$1 implements BaseHttpClientListener {
    private /* synthetic */ j a;

    j$1(j jVar) {
        this.a = jVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        j.a(this.a, j.c());
        Bundle bundle = new Bundle();
        String str = new String(bArr);
        XLLog.v("UserPreVerifyCodeTask", str);
        int intValue = Integer.valueOf(str.replace("\r\n", BuildConfig.VERSION_NAME)).intValue();
        if (intValue == 200) {
            intValue = 0;
        }
        bundle.putInt("errorCode", intValue);
        bundle.putString("action", "UserPreVerifyCodeTask");
        bundle.putString("errorDesc", str);
        this.a.g().a(this.a, bundle);
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.e("UserPreVerifyCodeTask", new StringBuilder("error = ").append(th.getMessage()).toString());
        j.a(this.a, j.c());
        int i = 16781312;
        String str = "\u672a\u77e5\u9519\u8bef";
        if (th instanceof HttpResponseException) {
            HttpResponseException httpResponseException = (HttpResponseException) th;
            i = httpResponseException.getStatusCode();
            str = httpResponseException.getMessage();
        }
        Bundle bundle = new Bundle();
        bundle.putString("action", "UserPreVerifyCodeTask");
        bundle.putInt("errorCode", i);
        bundle.putString("errorDesc", str);
        this.a.g().a(this.a, bundle);
    }
}
