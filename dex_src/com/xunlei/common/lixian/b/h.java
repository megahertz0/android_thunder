package com.xunlei.common.lixian.b;

import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.lixian.XLLX_TASKDETAIL;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.common.lixian.XLLixianTaskManager;
import com.xunlei.common.lixian.XLLixianUtil;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.IOException;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.Header;

final class h implements BaseHttpClientListener {
    private /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        this.a.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(this.a.getId()), null, null, Integer.valueOf(0), Integer.valueOf(0), this.a.getUserData()});
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        int a;
        IOException iOException;
        IOException iOException2;
        a aVar = new a();
        if (aVar.b(bArr) && Integer.valueOf(aVar.a().a("header_cmdid").toString()).intValue() == 152) {
            int a2;
            String str;
            int i2;
            XLLixianTask[] xLLixianTaskArr;
            int i3;
            XLLixianTask lixianTask = XLLixianUtil.getInstance().getLixianTask(this.a.a);
            Object obj = null;
            Object obj2 = null;
            Object obj3 = null;
            String str2 = BuildConfig.VERSION_NAME;
            b bVar = new b(aVar.b());
            try {
                XLLixianTask[] xLLixianTaskArr2;
                int a3 = bVar.a();
                str2 = XLLixianRequestBase.readUTF8(bVar);
                if (a3 == 0) {
                    a2 = bVar.a();
                    try {
                        a = bVar.a();
                        try {
                            List i4 = bVar.i();
                            XLLixianTask[] xLLixianTaskArr3 = new XLLixianTask[i4.size()];
                            obj3 = null;
                            int i5 = 0;
                            while (i5 < i4.size()) {
                                try {
                                    XLLX_TASKDETAIL xllx_taskdetail = new XLLX_TASKDETAIL();
                                    g.a(((b) i4.get(i5)).j(), xllx_taskdetail);
                                    xLLixianTaskArr3[i5] = XLLixianTaskManager.createTask(xllx_taskdetail.taskid, SimpleLog.LOG_LEVEL_FATAL);
                                    xLLixianTaskArr3[i5].setData(xllx_taskdetail);
                                    i5++;
                                } catch (IOException e) {
                                    iOException = e;
                                    str = str2;
                                    i2 = a2;
                                    a2 = a;
                                    xLLixianTaskArr = xLLixianTaskArr3;
                                    i3 = a3;
                                    iOException2 = iOException;
                                }
                            }
                            xLLixianTaskArr2 = xLLixianTaskArr3;
                            i3 = a;
                            a = a2;
                        } catch (IOException e2) {
                            iOException = e2;
                            i3 = a3;
                            iOException2 = iOException;
                            String str3 = str2;
                            i2 = a2;
                            a2 = a;
                            obj = obj3;
                            str = str3;
                            iOException2.printStackTrace();
                            this.a.fireListener(new Object[]{Integer.valueOf(i3), str, Integer.valueOf(this.a.getId()), lixianTask, xLLixianTaskArr, Integer.valueOf(a2), Integer.valueOf(i2), this.a.getUserData()});
                        }
                    } catch (IOException e3) {
                        iOException = e3;
                        obj = null;
                        str = str2;
                        i2 = a2;
                        a2 = 0;
                        i3 = a3;
                        iOException2 = iOException;
                        iOException2.printStackTrace();
                        this.a.fireListener(new Object[]{Integer.valueOf(i3), str, Integer.valueOf(this.a.getId()), lixianTask, xLLixianTaskArr, Integer.valueOf(a2), Integer.valueOf(i2), this.a.getUserData()});
                    }
                }
                a2 = i3;
                i3 = a3;
                XLLixianTask[] xLLixianTaskArr4 = xLLixianTaskArr2;
                str = str2;
                i2 = a;
                xLLixianTaskArr = xLLixianTaskArr4;
            } catch (IOException e4) {
                iOException = e4;
                a2 = 0;
                i3 = 0;
                iOException2 = iOException;
                str = str2;
                i2 = 0;
                obj = null;
                iOException2.printStackTrace();
                this.a.fireListener(new Object[]{Integer.valueOf(i3), str, Integer.valueOf(this.a.getId()), lixianTask, xLLixianTaskArr, Integer.valueOf(a2), Integer.valueOf(i2), this.a.getUserData()});
            }
            this.a.fireListener(new Object[]{Integer.valueOf(i3), str, Integer.valueOf(this.a.getId()), lixianTask, xLLixianTaskArr, Integer.valueOf(a2), Integer.valueOf(i2), this.a.getUserData()});
        }
    }
}
