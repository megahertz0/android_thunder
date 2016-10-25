package com.xunlei.common.lixian.b;

import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.lixian.XLLX_INDEXINFO;
import com.xunlei.common.lixian.XLLX_TASKDETAIL;
import com.xunlei.common.lixian.XLLixianNormalTask;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.common.lixian.XLLixianUtil;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import java.io.IOException;
import java.util.List;
import org.apache.http.Header;

final class v implements BaseHttpClientListener {
    private /* synthetic */ u a;

    v(u uVar) {
        this.a = uVar;
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        this.a.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(this.a.getId()), null, this.a.getUserData()});
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        XLLixianTask[] xLLixianTaskArr;
        IOException iOException;
        int i2;
        a aVar = new a();
        if (aVar.b(bArr) && Integer.valueOf(aVar.a().a("header_cmdid").toString()).intValue() == 131) {
            b bVar = new b(aVar.b());
            try {
                List i3 = bVar.i();
                XLLixianTask[] xLLixianTaskArr2 = new XLLixianTask[i3.size()];
                int i4 = 0;
                while (i4 < i3.size()) {
                    try {
                        XLLX_TASKDETAIL xllx_taskdetail = new XLLX_TASKDETAIL();
                        this.a.a(((b) i3.get(i4)).j(), xllx_taskdetail);
                        xLLixianTaskArr2[i4] = XLLixianUtil.getInstance().getLixianTask(xllx_taskdetail.taskid);
                        if (xLLixianTaskArr2[i4] != null && xLLixianTaskArr2[i4].isNormalTask()) {
                            ((XLLixianNormalTask) xLLixianTaskArr2[i4]).setData(xllx_taskdetail);
                        }
                        i4++;
                    } catch (IOException e) {
                        xLLixianTaskArr = xLLixianTaskArr2;
                        iOException = e;
                        i2 = 0;
                    }
                }
                short c = bVar.c();
                short s;
                try {
                    int a = bVar.a();
                    long[] jArr = new long[a];
                    for (i2 = 0; i2 < a; i2++) {
                        jArr[i2] = bVar.b();
                    }
                    i3 = bVar.i();
                    for (int i5 = 0; i5 < i3.size(); i5++) {
                        XLLX_INDEXINFO xllx_indexinfo = new XLLX_INDEXINFO();
                        u.a(((b) i3.get(i5)).j(), xllx_indexinfo);
                        XLLixianTask lixianTask = XLLixianUtil.getInstance().getLixianTask(xllx_indexinfo.taskid);
                        if (lixianTask != null && lixianTask.isNormalTask()) {
                            ((XLLixianNormalTask) xLLixianTaskArr2[i5]).setData(xllx_indexinfo);
                        }
                    }
                    s = c;
                    xLLixianTaskArr = xLLixianTaskArr2;
                } catch (IOException e2) {
                    IOException iOException2 = e2;
                    s = c;
                    xLLixianTaskArr = xLLixianTaskArr2;
                    iOException = iOException2;
                    iOException.printStackTrace();
                    this.a.fireListener(new Object[]{Integer.valueOf(i2), " ", Integer.valueOf(this.a.getId()), xLLixianTaskArr, this.a.getUserData()});
                }
            } catch (IOException e22) {
                Object obj = null;
                iOException = e22;
                i2 = 0;
                iOException.printStackTrace();
                this.a.fireListener(new Object[]{Integer.valueOf(i2), " ", Integer.valueOf(this.a.getId()), xLLixianTaskArr, this.a.getUserData()});
            }
            this.a.fireListener(new Object[]{Integer.valueOf(i2), " ", Integer.valueOf(this.a.getId()), xLLixianTaskArr, this.a.getUserData()});
        }
    }
}
