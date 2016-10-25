package com.xunlei.common.lixian.b;

import com.xunlei.common.lixian.XLLX_DOWNLOADSTATUS;
import com.xunlei.common.lixian.XLLX_INITDATA;
import com.xunlei.common.lixian.XLLX_TASKDETAIL;
import com.xunlei.common.lixian.XLLixianFileType;
import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import com.xunlei.common.lixian.a.d;
import com.xunlei.common.lixian.a.e;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.IOException;

public final class g extends XLLixianRequestBase {
    private long a;
    private int b;
    private int c;
    private int d;
    private int e;

    public g() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 50;
        this.e = 0;
    }

    private static boolean a(byte[] bArr, XLLX_TASKDETAIL xllx_taskdetail) {
        b bVar = new b(bArr);
        try {
            xllx_taskdetail.taskid = bVar.b();
            xllx_taskdetail.filesize = bVar.b();
            xllx_taskdetail.filetype = new XLLixianFileType(bVar.a());
            xllx_taskdetail.cid = bVar.e();
            xllx_taskdetail.gcid = bVar.e();
            xllx_taskdetail.taskname = XLLixianRequestBase.readUTF8(bVar);
            xllx_taskdetail.download_status = XLLX_DOWNLOADSTATUS.get(bVar.a());
            xllx_taskdetail.speed = bVar.a();
            xllx_taskdetail.progress = bVar.a();
            xllx_taskdetail.usedTime = bVar.a();
            xllx_taskdetail.leftLiveTime = (int) bVar.b();
            xllx_taskdetail.lixian_url = XLLixianRequestBase.readUTF8(bVar);
            xllx_taskdetail.url = XLLixianRequestBase.readUTF8(bVar);
            xllx_taskdetail.ref_url = XLLixianRequestBase.readUTF8(bVar);
            xllx_taskdetail.cookies = bVar.a(BuildConfig.VERSION_NAME);
            xllx_taskdetail.file_attr = bVar.a();
            xllx_taskdetail.status = bVar.a();
            xllx_taskdetail.message = XLLixianRequestBase.readUTF8(bVar);
            int a = bVar.a();
            xllx_taskdetail.extLixianUrllist = new String[a];
            for (int i = 0; i < a; i++) {
                xllx_taskdetail.extLixianUrllist[i] = new String();
                xllx_taskdetail.extLixianUrllist[i] = XLLixianRequestBase.readUTF8(bVar);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void a(int i, int i2, int i3, int i4) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public final void a(long j) {
        this.a = j;
    }

    public final boolean execute() {
        if (this.a <= 0) {
            return false;
        }
        a aVar = new a((short) 24);
        e eVar = new e();
        try {
            XLLX_INITDATA initData = super.getInitData();
            eVar.a(initData.userJumpKey);
            eVar.a(initData.userId);
            eVar.c(initData.userVipLevel);
            eVar.a(this.a);
            eVar.a(0);
            eVar.a(this.b);
            eVar.a(this.c);
            eVar.a(this.d);
            eVar.a(this.e);
            eVar.a(0);
            eVar.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        aVar.a(eVar.a());
        d.a().a(aVar.c(), new h(this));
        return true;
    }

    public final boolean fireEvent(XLLixianListener xLLixianListener, Object... objArr) {
        return xLLixianListener.OnObtainBtSubTasks(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLLixianTask) objArr[3], (XLLixianTask[]) objArr[4], ((Integer) objArr[5]).intValue(), ((Integer) objArr[6]).intValue(), objArr[7]);
    }
}
