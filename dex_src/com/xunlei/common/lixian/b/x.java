package com.xunlei.common.lixian.b;

import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.lixian.XLLX_TASKBASIC;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.common.lixian.XLLixianTaskManager;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import java.io.IOException;
import java.util.List;
import org.apache.http.Header;

final class x implements BaseHttpClientListener {
    private /* synthetic */ w a;

    x(w wVar) {
        this.a = wVar;
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        this.a.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(this.a.getId()), null, Integer.valueOf(0), this.a.getUserData()});
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        int i2 = 0;
        a aVar = new a();
        if (aVar.b(bArr) && Integer.valueOf(aVar.a().a("header_cmdid").toString()).intValue() == 143) {
            b bVar = new b(aVar.b());
            try {
                int a = bVar.a();
                String readUTF8 = XLLixianRequestBase.readUTF8(bVar);
                XLLixianTask[] xLLixianTaskArr = null;
                if (a == 0) {
                    List i3 = bVar.i();
                    xLLixianTaskArr = new XLLixianTask[i3.size()];
                    for (int i4 = 0; i4 < i3.size(); i4++) {
                        XLLX_TASKBASIC xllx_taskbasic = new XLLX_TASKBASIC();
                        w.a(this.a, ((b) i3.get(i4)).j(), xllx_taskbasic);
                        xLLixianTaskArr[i4] = XLLixianTaskManager.createTask(xllx_taskbasic.taskid, xllx_taskbasic.res_type);
                        xLLixianTaskArr[i4].setData(xllx_taskbasic);
                    }
                    i2 = bVar.a();
                }
                this.a.fireListener(new Object[]{Integer.valueOf(a), readUTF8, Integer.valueOf(this.a.getId()), xLLixianTaskArr, Integer.valueOf(i2), this.a.getUserData()});
                try {
                    bVar.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                try {
                    e2.printStackTrace();
                    this.a.fireListener(new Object[]{Integer.valueOf(-1), e2.getMessage(), Integer.valueOf(this.a.getId()), null, Integer.valueOf(0), this.a.getUserData()});
                    try {
                        bVar.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                } catch (Throwable th) {
                    try {
                        bVar.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
    }
}
