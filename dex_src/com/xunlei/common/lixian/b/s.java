package com.xunlei.common.lixian.b;

import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.XLLixianTask;

public final class s extends XLLixianRequestBase {
    private w a;
    private o b;
    private int c;
    private XLLixianListener d;

    public s() {
        this.a = null;
        this.b = null;
        this.c = 0;
        this.d = new t(this);
    }

    public final void a(long j, boolean z, int i, int i2) {
        w wVar = new w();
        wVar.a(j, z);
        wVar.b(i2);
        wVar.a(i);
        o oVar = new o();
        this.a = wVar;
        this.a.attachListener(this.d);
        this.b = oVar;
        this.b.attachListener(this.d);
    }

    public final boolean execute() {
        return (this.b == null || this.a == null || this.a.commitTask() == 0) ? false : true;
    }

    public final boolean fireEvent(XLLixianListener xLLixianListener, Object... objArr) {
        return xLLixianListener.OnObtainLixianTasks(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLLixianTask[]) objArr[3], ((Integer) objArr[4]).intValue(), objArr[5]);
    }
}
