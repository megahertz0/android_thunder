package com.xunlei.common.lixian.b;

import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.xiazaibao.BuildConfig;

final class p extends XLLixianListener {
    private /* synthetic */ o a;

    p(o oVar) {
        this.a = oVar;
    }

    public final boolean OnUpdateLixianTasksDetail(int i, String str, int i2, XLLixianTask[] xLLixianTaskArr, Object obj) {
        int i3;
        for (i3 = 0; i3 < this.a.b.size(); i3++) {
            XLLixianTask[] xLLixianTaskArr2;
            if (((Integer) this.a.b.get(i3)).intValue() == i2) {
                this.a.b.remove(i3);
                int i4 = 0;
                while (i == 0 && i4 < xLLixianTaskArr.length) {
                    if (!this.a.c.contains(xLLixianTaskArr[i4])) {
                        this.a.c.add(xLLixianTaskArr[i4]);
                    }
                    i4++;
                }
                if (this.a.b.size() == 0) {
                    xLLixianTaskArr2 = new XLLixianTask[this.a.c.size()];
                    for (i3 = 0; i3 < this.a.c.size(); i3++) {
                        xLLixianTaskArr2[i3] = (XLLixianTask) this.a.c.get(i3);
                    }
                    o.a(this.a, xLLixianTaskArr2, false);
                    this.a.fireListener(new Object[]{Integer.valueOf(0), BuildConfig.VERSION_NAME, Integer.valueOf(this.a.getId()), xLLixianTaskArr2, this.a.getUserData()});
                }
                return false;
            }
        }
        if (this.a.b.size() == 0) {
            xLLixianTaskArr2 = new XLLixianTask[this.a.c.size()];
            for (i3 = 0; i3 < this.a.c.size(); i3++) {
                xLLixianTaskArr2[i3] = (XLLixianTask) this.a.c.get(i3);
            }
            o.a(this.a, xLLixianTaskArr2, false);
            this.a.fireListener(new Object[]{Integer.valueOf(0), BuildConfig.VERSION_NAME, Integer.valueOf(this.a.getId()), xLLixianTaskArr2, this.a.getUserData()});
        }
        return false;
    }
}
