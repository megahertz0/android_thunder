package com.xunlei.common.lixian.b;

import android.os.Bundle;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.lixian.XLLX_DOWNLOADSTATUS;
import com.xunlei.common.lixian.XLLX_TASKDETAIL;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.common.lixian.XLLixianTaskManager;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.e;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.IOException;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.Header;

final class b implements BaseHttpClientListener {
    private /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        this.a.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(this.a.getId()), Long.valueOf(0), BuildConfig.VERSION_NAME, null, null, this.a.getUserData()});
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        a aVar = new a();
        if (aVar.b(bArr)) {
            Object a = aVar.a().a("header_cmdid");
            if (Integer.valueOf(a.toString()).intValue() != 141) {
                e.a("XLLixianRequestTaskIdList", new StringBuilder("responsed cmdid is ").append(String.valueOf(a)).toString());
            }
            com.xunlei.common.lixian.a.b bVar = new com.xunlei.common.lixian.a.b(aVar.b());
            try {
                Bundle bundle = new Bundle();
                int a2 = bVar.a();
                String readUTF8 = XLLixianRequestBase.readUTF8(bVar);
                if (a2 == 11) {
                    this.a.f = true;
                    this.a.execute();
                } else if (a2 == 0) {
                    String e = bVar.e();
                    bVar.b();
                    bVar.b();
                    bVar.b();
                    bVar.b();
                    bVar.b();
                    bVar.a();
                    bVar.b();
                    bVar.b();
                    bVar.b();
                    bVar.b();
                    bVar.b();
                    long b = bVar.b();
                    int a3 = bVar.a();
                    long[] jArr = new long[a3];
                    for (int i2 = 0; i2 < a3; i2++) {
                        jArr[i2] = bVar.b();
                    }
                    List i3 = bVar.i();
                    XLLixianTask[] xLLixianTaskArr = new XLLixianTask[i3.size()];
                    for (a3 = 0; a3 < i3.size(); a3++) {
                        com.xunlei.common.lixian.a.b bVar2 = (com.xunlei.common.lixian.a.b) i3.get(a3);
                        XLLX_TASKDETAIL xllx_taskdetail = new XLLX_TASKDETAIL();
                        xllx_taskdetail.download_status = XLLX_DOWNLOADSTATUS.get(bVar2.a());
                        xllx_taskdetail.progress = bVar2.a();
                        xllx_taskdetail.lixian_url = XLLixianRequestBase.readUTF8(bVar2);
                        xllx_taskdetail.ref_url = XLLixianRequestBase.readUTF8(bVar2);
                        xllx_taskdetail.cookies = bVar2.e();
                        xllx_taskdetail.taskid = jArr[a3];
                        xLLixianTaskArr[a3] = XLLixianTaskManager.createTask(jArr[a3], SimpleLog.LOG_LEVEL_FATAL);
                        xLLixianTaskArr[a3].setData(xllx_taskdetail);
                    }
                    bVar.b();
                    bVar.a();
                    bVar.b();
                    bVar.a();
                    bVar.a();
                    this.a.fireListener(new Object[]{Integer.valueOf(a2), readUTF8, Integer.valueOf(this.a.getId()), Long.valueOf(b), e, this.a.e, xLLixianTaskArr, this.a.getUserData()});
                } else {
                    this.a.fireListener(new Object[]{Integer.valueOf(a2), readUTF8, Integer.valueOf(this.a.getId()), Long.valueOf(0), BuildConfig.VERSION_NAME, null, null, this.a.getUserData()});
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                this.a.fireListener(new Object[]{Integer.valueOf(-1), e2.getMessage(), Integer.valueOf(this.a.getId()), Long.valueOf(0), BuildConfig.VERSION_NAME, null, null, this.a.getUserData()});
            }
        }
    }
}
