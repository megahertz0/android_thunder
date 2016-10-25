package com.xunlei.common.lixian.b;

import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.lixian.XLLX_USERINFO;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.IOException;
import org.apache.http.Header;

final class z implements BaseHttpClientListener {
    private /* synthetic */ y a;

    z(y yVar) {
        this.a = yVar;
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        this.a.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(this.a.getId()), null, this.a.getUserData()});
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        a aVar = new a();
        if (!aVar.b(bArr)) {
            this.a.fireListener(new Object[]{Integer.valueOf(-1), BuildConfig.VERSION_NAME, Integer.valueOf(this.a.getId()), null, this.a.getUserData()});
        } else if (Integer.valueOf(aVar.a().a("header_cmdid").toString()).intValue() == 144) {
            b bVar = new b(aVar.b());
            try {
                int a = bVar.a();
                String readUTF8 = XLLixianRequestBase.readUTF8(bVar);
                XLLX_USERINFO xllx_userinfo = null;
                if (a == 0) {
                    xllx_userinfo = new XLLX_USERINFO();
                    y.a(xllx_userinfo, bVar.g());
                }
                this.a.fireListener(new Object[]{Integer.valueOf(a), readUTF8, Integer.valueOf(this.a.getId()), xllx_userinfo, this.a.getUserData()});
            } catch (IOException e) {
                e.printStackTrace();
                this.a.fireListener(new Object[]{Integer.valueOf(-1), e.getMessage(), Integer.valueOf(this.a.getId()), null, this.a.getUserData()});
            }
        }
    }
}
