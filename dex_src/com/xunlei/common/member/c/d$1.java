package com.xunlei.common.member.c;

import android.os.Bundle;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import org.apache.http.Header;

// compiled from: UserGetLixianCapacityTask.java
final class d$1 implements BaseHttpClientListener {
    private /* synthetic */ d a;

    d$1(d dVar) {
        this.a = dVar;
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        Bundle bundle = new Bundle();
        String str = new String(bArr);
        XLLog.v("UserGetLixianCapacity result", str);
        if (d.a(this.a, str)) {
            bundle.putInt("errorCode", 0);
        } else {
            bundle.putInt("errorCode", 16781312);
        }
        bundle.putString("action", "user_get_lixian_capacity");
        this.a.g().a(this.a, bundle);
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        XLLog.e("UserGetLixianCapacity", new StringBuilder("error = ").append(th.getMessage()).toString());
        Bundle bundle = new Bundle();
        bundle.putString("action", "user_get_lixian_capacity");
        bundle.putInt("errorCode", 16781312);
        this.a.g().a(this.a, bundle);
    }
}
