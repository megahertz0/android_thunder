package com.xunlei.common.lixian.b;

import com.xunlei.common.lixian.XLLX_DOWNLOADSTATUS;
import com.xunlei.common.lixian.XLLX_INITDATA;
import com.xunlei.common.lixian.XLLX_TASKDETAIL;
import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import com.xunlei.common.lixian.a.d;
import com.xunlei.common.lixian.a.e;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public final class m extends XLLixianRequestBase {
    private List a;

    public m() {
        this.a = new LinkedList();
    }

    private static boolean a(byte[] bArr, XLLX_TASKDETAIL xllx_taskdetail) {
        b bVar = new b(bArr);
        try {
            xllx_taskdetail.taskid = bVar.b();
            xllx_taskdetail.status = bVar.a();
            xllx_taskdetail.message = XLLixianRequestBase.readUTF8(bVar);
            if (xllx_taskdetail.status == 0) {
                xllx_taskdetail.download_status = XLLX_DOWNLOADSTATUS.get(bVar.a());
                xllx_taskdetail.speed = bVar.a();
                xllx_taskdetail.progress = bVar.a();
                xllx_taskdetail.usedTime = bVar.a();
                xllx_taskdetail.commit_time = bVar.b();
                xllx_taskdetail.taskname = XLLixianRequestBase.readUTF8(bVar);
                xllx_taskdetail.filesize = bVar.b();
                xllx_taskdetail.leftLiveTime = bVar.a();
                xllx_taskdetail.url = XLLixianRequestBase.readUTF8(bVar);
                xllx_taskdetail.classvalue = bVar.b();
                xllx_taskdetail.file_attr = bVar.a();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void a(long j) {
        this.a.add(Long.valueOf(j));
    }

    public final boolean execute() {
        if (this.a.size() <= 0) {
            return false;
        }
        a aVar = new a((short) 7);
        e eVar = new e();
        try {
            XLLX_INITDATA initData = super.getInitData();
            eVar.a(initData.userJumpKey);
            eVar.a(initData.userId);
            eVar.a(this.a.size());
            for (int i = 0; i < this.a.size(); i++) {
                eVar.a(((Long) this.a.get(i)).longValue());
            }
            eVar.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        aVar.a(eVar.a());
        d.a().a(aVar.c(), new n(this));
        return true;
    }

    public final boolean fireEvent(XLLixianListener xLLixianListener, Object... objArr) {
        return xLLixianListener.OnUpdateLixianTasksDetail(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLLixianTask[]) objArr[3], objArr[4]);
    }
}
