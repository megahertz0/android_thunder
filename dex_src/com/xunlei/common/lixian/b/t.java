package com.xunlei.common.lixian.b;

import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianTask;

final class t extends XLLixianListener {
    private /* synthetic */ s a;

    t(s sVar) {
        this.a = sVar;
    }

    public final boolean OnObtainLixianTasks(int i, String str, int i2, XLLixianTask[] xLLixianTaskArr, int i3, Object obj) {
        if (i == 0) {
            this.a.c = i3;
            if (xLLixianTaskArr.length != 0) {
                this.a.b.a(xLLixianTaskArr);
                if (this.a.b.commitTask() == 0) {
                    this.a.fireListener(new Object[]{Integer.valueOf(-1), str, Integer.valueOf(this.a.getId()), xLLixianTaskArr, Integer.valueOf(i3), this.a.getUserData()});
                }
                return false;
            }
        }
        this.a.fireListener(new Object[]{Integer.valueOf(i), str, Integer.valueOf(this.a.getId()), xLLixianTaskArr, Integer.valueOf(i3), this.a.getUserData()});
        return false;
    }

    public final boolean OnUpdateLixianTasksDetail(int i, String str, int i2, XLLixianTask[] xLLixianTaskArr, Object obj) {
        this.a.fireListener(new Object[]{Integer.valueOf(i), str, Integer.valueOf(this.a.getId()), xLLixianTaskArr, Integer.valueOf(this.a.c), this.a.getUserData()});
        return false;
    }
}
