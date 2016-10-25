package com.xunlei.common.lixian.b;

import com.xunlei.common.lixian.XLLX_INITDATA;
import com.xunlei.common.lixian.XLLX_TASKBASIC;
import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import com.xunlei.common.lixian.a.d;
import com.xunlei.common.lixian.a.e;
import java.io.IOException;

public final class w extends XLLixianRequestBase {
    private static int a = 0;
    private static int b = 1;
    private static int c = 2;
    private int d;
    private long e;
    private boolean f;
    private int g;

    public w() {
        this.d = 50;
        this.e = 0;
        this.f = true;
        this.g = 0;
    }

    private static boolean a(byte[] bArr, XLLX_TASKBASIC xllx_taskbasic) {
        try {
            b bVar = new b(bArr);
            xllx_taskbasic.status = bVar.a();
            xllx_taskbasic.taskid = bVar.b();
            xllx_taskbasic.download_status = bVar.d();
            xllx_taskbasic.commit_time = bVar.b();
            xllx_taskbasic.res_type = bVar.a();
            xllx_taskbasic.classvalue = bVar.b();
            xllx_taskbasic.database = bVar.a();
            xllx_taskbasic.delete_time = bVar.b();
            xllx_taskbasic.flag = bVar.a();
            xllx_taskbasic.suffix_type = bVar.a();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void a(int i) {
        this.g = i;
    }

    public final void a(long j, boolean z) {
        this.e = j;
        this.f = z;
    }

    public final void b(int i) {
        this.d = i;
    }

    public final boolean execute() {
        int i = 0;
        a aVar = new a((short) 15);
        e eVar = new e();
        try {
            XLLX_INITDATA initData = super.getInitData();
            eVar.a(initData.userJumpKey);
            eVar.a(initData.userId);
            eVar.c(initData.userVipLevel);
            eVar.a(0);
            eVar.a(this.e);
            eVar.a(this.d);
            if (!this.f) {
                i = 1;
            }
            eVar.a(i);
            eVar.a(0);
            eVar.a(this.g);
            eVar.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        aVar.a(eVar.a());
        d.a().a(aVar.c(), new x(this));
        return true;
    }

    public final boolean fireEvent(XLLixianListener xLLixianListener, Object... objArr) {
        return xLLixianListener.OnObtainLixianTasks(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLLixianTask[]) objArr[3], ((Integer) objArr[4]).intValue(), objArr[5]);
    }
}
