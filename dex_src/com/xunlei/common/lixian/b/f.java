package com.xunlei.common.lixian.b;

import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.lixian.XLLX_TASKRESPSTATUS;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.XLLixianTaskManager;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import java.io.IOException;
import java.util.List;
import org.android.spdy.TnetStatusCode;
import org.apache.http.Header;

final class f implements BaseHttpClientListener {
    private /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        this.a.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(this.a.getId()), null, this.a.getUserData()});
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        a aVar = new a();
        if (aVar.b(bArr) && Integer.valueOf(aVar.a().a("header_cmdid").toString()).intValue() == 138) {
            b bVar = new b(aVar.b());
            try {
                List i2 = bVar.i();
                XLLX_TASKRESPSTATUS[] xllx_taskrespstatusArr = new XLLX_TASKRESPSTATUS[i2.size()];
                for (int i3 = 0; i3 < i2.size(); i3++) {
                    XLLX_TASKRESPSTATUS xllx_taskrespstatus = new XLLX_TASKRESPSTATUS();
                    e.a(this.a, ((b) i2.get(i3)).j(), xllx_taskrespstatus);
                    xllx_taskrespstatusArr[i3] = xllx_taskrespstatus;
                    if (xllx_taskrespstatus.status == 0) {
                        XLLixianTaskManager.removeTask(xllx_taskrespstatus.taskid);
                    }
                }
                int a = bVar.a();
                String readUTF8 = XLLixianRequestBase.readUTF8(bVar);
                this.a.fireListener(new Object[]{Integer.valueOf(a), readUTF8, Integer.valueOf(this.a.getId()), xllx_taskrespstatusArr, this.a.getUserData()});
                try {
                    bVar.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                try {
                    e2.printStackTrace();
                    this.a.fireListener(new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), e2.getMessage(), Integer.valueOf(this.a.getId()), null, this.a.getUserData()});
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
