package com.xunlei.common.lixian.b;

import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.IOException;
import org.apache.http.Header;

final class l implements BaseHttpClientListener {
    private /* synthetic */ k a;

    l(k kVar) {
        this.a = kVar;
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        this.a.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(this.a.getId()), null, this.a.getUserData()});
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        int a;
        String readUTF8;
        IOException e;
        a aVar = new a();
        if (aVar.b(bArr) && Integer.valueOf(aVar.a().a("header_cmdid").toString()).intValue() == 149) {
            b bVar = new b(aVar.b());
            String str = BuildConfig.VERSION_NAME;
            byte[] bArr2 = null;
            try {
                a = bVar.a();
                try {
                    readUTF8 = XLLixianRequestBase.readUTF8(bVar);
                    try {
                        bArr2 = bVar.h().j();
                        try {
                            bVar.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    } catch (IOException e3) {
                        e2 = e3;
                        e2.printStackTrace();
                        bVar.close();
                        this.a.fireListener(new Object[]{Integer.valueOf(a), readUTF8, Integer.valueOf(this.a.getId()), bArr2, this.a.getUserData()});
                    }
                } catch (IOException e4) {
                    IOException iOException = e4;
                    readUTF8 = str;
                    e2 = iOException;
                    e2.printStackTrace();
                    try {
                        bVar.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                    this.a.fireListener(new Object[]{Integer.valueOf(a), readUTF8, Integer.valueOf(this.a.getId()), bArr2, this.a.getUserData()});
                }
            } catch (IOException e42) {
                a = 0;
                String str2 = str;
                e22 = e42;
                readUTF8 = str2;
                try {
                    e22.printStackTrace();
                    bVar.close();
                } catch (Throwable th) {
                    try {
                        bVar.close();
                    } catch (IOException e422) {
                        e422.printStackTrace();
                    }
                }
                this.a.fireListener(new Object[]{Integer.valueOf(a), readUTF8, Integer.valueOf(this.a.getId()), bArr2, this.a.getUserData()});
            }
            this.a.fireListener(new Object[]{Integer.valueOf(a), readUTF8, Integer.valueOf(this.a.getId()), bArr2, this.a.getUserData()});
        }
    }
}
