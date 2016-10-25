package com.xunlei.common.lixian.b;

import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.XLLixianTask;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public final class o extends XLLixianRequestBase {
    private List a;
    private List b;
    private List c;
    private XLLixianListener d;

    public o() {
        this.a = new LinkedList();
        this.b = new LinkedList();
        this.c = new LinkedList();
        this.d = new p(this);
    }

    private void a(XLLixianRequestBase xLLixianRequestBase) {
        xLLixianRequestBase.attachListener(this.d);
        this.a.add(xLLixianRequestBase);
    }

    static /* synthetic */ void a(o oVar, XLLixianTask[] xLLixianTaskArr, boolean z) {
        q qVar = new q(oVar);
        Comparator rVar = new r(oVar);
        if (xLLixianTaskArr != null && xLLixianTaskArr.length != 0) {
            Arrays.sort(xLLixianTaskArr, rVar);
        }
    }

    private void a(XLLixianTask[] xLLixianTaskArr, boolean z) {
        Comparator qVar = new q(this);
        Comparator rVar = new r(this);
        if (xLLixianTaskArr != null && xLLixianTaskArr.length != 0) {
            if (z) {
                Arrays.sort(xLLixianTaskArr, qVar);
            } else {
                Arrays.sort(xLLixianTaskArr, rVar);
            }
        }
    }

    public final void a(XLLixianTask[] xLLixianTaskArr) {
        XLLixianRequestBase uVar = new u();
        XLLixianRequestBase mVar = new m();
        for (int i = 0; i < xLLixianTaskArr.length; i++) {
            if (xLLixianTaskArr[i].isBtTask()) {
                mVar.a(xLLixianTaskArr[i].getTaskId());
            } else {
                uVar.a(xLLixianTaskArr[i].getTaskId());
            }
        }
        a(uVar);
        a(mVar);
    }

    public final boolean execute() {
        for (XLLixianRequestBase xLLixianRequestBase : this.a) {
            if (xLLixianRequestBase.commitTask() != 0) {
                this.b.add(Integer.valueOf(xLLixianRequestBase.getId()));
            }
        }
        return this.b.size() != 0;
    }

    public final boolean fireEvent(XLLixianListener xLLixianListener, Object... objArr) {
        return xLLixianListener.OnUpdateLixianTasksDetail(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLLixianTask[]) objArr[3], objArr[4]);
    }
}
