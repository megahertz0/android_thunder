package com.xunlei.common.lixian.b;

import android.os.Bundle;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.lixian.XLLX_DOWNLOADSTATUS;
import com.xunlei.common.lixian.XLLX_TASKBASIC;
import com.xunlei.common.lixian.XLLX_TASKDETAIL;
import com.xunlei.common.lixian.XLLixianFileType;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.common.lixian.XLLixianTaskManager;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import com.xunlei.common.lixian.e;
import java.io.IOException;
import java.util.List;
import org.apache.http.Header;

final class d implements BaseHttpClientListener {
    private /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final void onFailure(Throwable th, byte[] bArr) {
        this.a.fireListener(new Object[]{Integer.valueOf(-1), th.getMessage(), Integer.valueOf(this.a.getId()), null, this.a.getUserData()});
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        a aVar = new a();
        if (aVar.b(bArr)) {
            Object a = aVar.a().a("header_cmdid");
            if (Integer.valueOf(a.toString()).intValue() != 140) {
                e.a("XLLixianRequestTaskIdList", new StringBuilder("responsed cmdid is ").append(String.valueOf(a)).toString());
            }
            b bVar = new b(aVar.b());
            try {
                Bundle bundle = new Bundle();
                int a2 = bVar.a();
                String readUTF8 = XLLixianRequestBase.readUTF8(bVar);
                if (a2 == 0) {
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
                    List i2 = bVar.i();
                    XLLixianTask[] xLLixianTaskArr = new XLLixianTask[i2.size()];
                    for (int i3 = 0; i3 < i2.size(); i3++) {
                        b bVar2 = (b) i2.get(i3);
                        XLLX_TASKBASIC xllx_taskbasic = new XLLX_TASKBASIC();
                        XLLX_TASKDETAIL xllx_taskdetail = new XLLX_TASKDETAIL();
                        xllx_taskbasic.status = bVar2.a();
                        xllx_taskdetail.message = XLLixianRequestBase.readUTF8(bVar2);
                        long b = bVar2.b();
                        xllx_taskbasic.taskid = b;
                        xllx_taskdetail.taskid = b;
                        xllx_taskdetail.url = bVar2.e();
                        xllx_taskdetail.taskname = XLLixianRequestBase.readUTF8(bVar2);
                        xllx_taskdetail.cid = bVar2.e();
                        xllx_taskdetail.gcid = bVar2.e();
                        xllx_taskdetail.filesize = bVar2.b();
                        xllx_taskdetail.filetype = new XLLixianFileType(bVar2.a());
                        xllx_taskdetail.download_status = XLLX_DOWNLOADSTATUS.get(bVar2.a());
                        xllx_taskdetail.progress = bVar2.a();
                        xllx_taskdetail.lixian_url = bVar2.e();
                        xllx_taskdetail.ref_url = bVar2.e();
                        xllx_taskdetail.cookies = bVar2.e();
                        xllx_taskdetail.classvalue = bVar2.b();
                        xllx_taskdetail.leftLiveTime = bVar2.a();
                        xllx_taskdetail.suffix_type = bVar2.a();
                        xllx_taskdetail.commit_time = bVar2.b();
                        xLLixianTaskArr[i3] = XLLixianTaskManager.createTask(b, 0);
                        xLLixianTaskArr[i3].setData(xllx_taskbasic);
                        xLLixianTaskArr[i3].setData(xllx_taskdetail);
                    }
                    this.a.fireListener(new Object[]{Integer.valueOf(a2), readUTF8, Integer.valueOf(this.a.getId()), xLLixianTaskArr[0], this.a.getUserData()});
                    return;
                }
                this.a.fireListener(new Object[]{Integer.valueOf(a2), readUTF8, Integer.valueOf(this.a.getId()), null, this.a.getUserData()});
            } catch (IOException e) {
                e.printStackTrace();
                this.a.fireListener(new Object[]{Integer.valueOf(-1), e.getMessage(), Integer.valueOf(this.a.getId()), null, this.a.getUserData()});
            }
        }
    }
}
