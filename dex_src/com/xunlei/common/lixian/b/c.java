package com.xunlei.common.lixian.b;

import com.xunlei.common.lixian.XLLX_INITDATA;
import com.xunlei.common.lixian.XLLX_NEWTASK;
import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.d;
import com.xunlei.common.lixian.a.e;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public final class c extends XLLixianRequestBase {
    private List a;

    public c() {
        this.a = new LinkedList();
    }

    public final void a(XLLX_NEWTASK xllx_newtask) {
        this.a.add(xllx_newtask);
    }

    public final boolean execute() {
        a aVar = new a((short) 12);
        e eVar = new e();
        try {
            XLLX_INITDATA initData = super.getInitData();
            eVar.a(initData.userJumpKey);
            eVar.a(initData.userId);
            eVar.c(initData.userVipLevel);
            eVar.c(0);
            List linkedList = new LinkedList();
            for (int i = 0; i < this.a.size(); i++) {
                e eVar2 = new e();
                XLLX_NEWTASK xllx_newtask = (XLLX_NEWTASK) this.a.get(i);
                eVar2.a(xllx_newtask.url);
                eVar2.a(xllx_newtask.ref_url);
                eVar2.a(xllx_newtask.cookies);
                eVar2.a(xllx_newtask.taskname, "GB18030");
                eVar2.a(xllx_newtask.cid);
                eVar2.a(xllx_newtask.gcid);
                eVar2.a(xllx_newtask.filesize);
                eVar2.a(xllx_newtask.filetype);
                eVar2.a(xllx_newtask.groupid);
                eVar2.flush();
                linkedList.add(eVar2);
            }
            eVar.a(linkedList);
            eVar.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        aVar.a(eVar.a());
        d.a().a(aVar.c(), new d(this));
        return true;
    }

    public final boolean fireEvent(XLLixianListener xLLixianListener, Object... objArr) {
        return xLLixianListener.OnCreateLixianTask(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLLixianTask) objArr[3], objArr[4]);
    }
}
