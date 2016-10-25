package com.xunlei.common.lixian.b;

import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.lixian.XLLX_TASKDETAIL;
import com.xunlei.common.lixian.XLLixianBtTask;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.common.lixian.XLLixianUtil;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.IOException;
import java.util.List;
import org.apache.http.Header;

final class n implements BaseHttpClientListener {
    private /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        this.a.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(this.a.getId()), null, this.a.getUserData()});
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        IOException iOException;
        XLLixianTask[] xLLixianTaskArr;
        IOException iOException2;
        int c;
        a aVar = new a();
        if (aVar.b(bArr) && Integer.valueOf(aVar.a().a("header_cmdid").toString()).intValue() == 135) {
            XLLixianTask[] xLLixianTaskArr2;
            b bVar = new b(aVar.b());
            try {
                List i2 = bVar.i();
                xLLixianTaskArr2 = new XLLixianTask[i2.size()];
                int i3 = 0;
                while (i3 < i2.size()) {
                    try {
                        XLLX_TASKDETAIL xllx_taskdetail = new XLLX_TASKDETAIL();
                        m.a(((b) i2.get(i3)).j(), xllx_taskdetail);
                        xLLixianTaskArr2[i3] = XLLixianUtil.getInstance().getLixianTask(xllx_taskdetail.taskid);
                        if (xLLixianTaskArr2[i3] != null && xLLixianTaskArr2[i3].isBtTask()) {
                            ((XLLixianBtTask) xLLixianTaskArr2[i3]).setData(xllx_taskdetail);
                        }
                        i3++;
                    } catch (IOException e) {
                        iOException = e;
                        xLLixianTaskArr = xLLixianTaskArr2;
                        iOException2 = iOException;
                    }
                }
                c = bVar.c();
            } catch (IOException e2) {
                iOException = e2;
                Object obj = null;
                iOException2 = iOException;
                iOException2.printStackTrace();
                xLLixianTaskArr2 = xLLixianTaskArr;
                c = 0;
                this.a.fireListener(new Object[]{Integer.valueOf(c), BuildConfig.VERSION_NAME, Integer.valueOf(this.a.getId()), xLLixianTaskArr2, this.a.getUserData()});
            }
            this.a.fireListener(new Object[]{Integer.valueOf(c), BuildConfig.VERSION_NAME, Integer.valueOf(this.a.getId()), xLLixianTaskArr2, this.a.getUserData()});
        }
    }
}
