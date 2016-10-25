package com.xunlei.common.member.c;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import org.apache.http.Header;

// compiled from: UserGetAuthQRCodeTask.java
final class b$2 implements BaseHttpClientListener {
    private /* synthetic */ b a;

    b$2(b bVar) {
        this.a = bVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        if (i == 200) {
            try {
                XLLog.v("UserGetAuthQRCodeTask", new StringBuilder("get qr image error = ").append(Integer.valueOf(new String(bArr)).intValue()).toString());
                b.b(this.a, 50331650);
                b.a(this.a, 1026);
            } catch (NumberFormatException e) {
                XLLog.v("UserGetAuthQRCodeTask", "get qr image succeed!");
                b.a(this.a, bArr);
                b.b(this.a, 0);
                b.a(this.a, 1026);
            }
        }
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        b.b(this.a, 50331650);
        b.a(this.a, 1026);
    }
}
