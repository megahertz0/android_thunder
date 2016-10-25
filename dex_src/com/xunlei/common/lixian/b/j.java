package com.xunlei.common.lixian.b;

import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.lixian.XLLX_BTFILE;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.IOException;
import java.util.List;
import org.apache.http.Header;

final class j implements BaseHttpClientListener {
    private /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        this.a.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(this.a.getId()), null, BuildConfig.VERSION_NAME, this.a.getUserData()});
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        IOException iOException;
        XLLX_BTFILE[] xllx_btfileArr;
        String str;
        int i2;
        IOException iOException2;
        Object obj;
        a aVar = new a();
        if (aVar.b(bArr) && Integer.valueOf(aVar.a().a("header_cmdid").toString()).intValue() == 150) {
            b bVar = new b(aVar.b());
            String str2 = BuildConfig.VERSION_NAME;
            String str3 = BuildConfig.VERSION_NAME;
            try {
                int a = bVar.a();
                try {
                    str2 = XLLixianRequestBase.readUTF8(bVar);
                    bVar.b();
                    bVar.a();
                    bVar.a();
                    str3 = bVar.e();
                    XLLixianRequestBase.readUTF8(bVar);
                    List i3 = bVar.i();
                    XLLX_BTFILE[] xllx_btfileArr2 = new XLLX_BTFILE[i3.size()];
                    int i4 = 0;
                    while (i4 < i3.size()) {
                        try {
                            XLLX_BTFILE xllx_btfile = new XLLX_BTFILE();
                            i.a(this.a, (b) i3.get(i4), xllx_btfile);
                            xllx_btfileArr2[i4] = xllx_btfile;
                            i4++;
                        } catch (IOException e) {
                            iOException = e;
                            xllx_btfileArr = xllx_btfileArr2;
                            str = str3;
                            str3 = str2;
                            i2 = a;
                            iOException2 = iOException;
                        }
                    }
                    try {
                        bVar.close();
                        xllx_btfileArr = xllx_btfileArr2;
                        str = str3;
                        str3 = str2;
                        i2 = a;
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        xllx_btfileArr = xllx_btfileArr2;
                        str = str3;
                        str3 = str2;
                        i2 = a;
                    }
                } catch (IOException e22) {
                    iOException = e22;
                    obj = null;
                    str = str3;
                    str3 = str2;
                    i2 = a;
                    iOException2 = iOException;
                    iOException2.printStackTrace();
                    try {
                        bVar.close();
                    } catch (IOException iOException22) {
                        iOException22.printStackTrace();
                    }
                    this.a.fireListener(new Object[]{Integer.valueOf(i2), str3, Integer.valueOf(this.a.getId()), xllx_btfileArr, str, this.a.getUserData()});
                }
            } catch (IOException e222) {
                iOException22 = e222;
                obj = null;
                str = str3;
                str3 = str2;
                i2 = 0;
                try {
                    iOException22.printStackTrace();
                    bVar.close();
                } catch (Throwable th) {
                    try {
                        bVar.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                this.a.fireListener(new Object[]{Integer.valueOf(i2), str3, Integer.valueOf(this.a.getId()), xllx_btfileArr, str, this.a.getUserData()});
            }
            this.a.fireListener(new Object[]{Integer.valueOf(i2), str3, Integer.valueOf(this.a.getId()), xllx_btfileArr, str, this.a.getUserData()});
        }
    }
}
