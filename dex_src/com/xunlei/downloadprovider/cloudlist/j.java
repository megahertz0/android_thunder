package com.xunlei.downloadprovider.cloudlist;

import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.downloadprovider.a.f;
import com.xunlei.downloadprovider.cloudlist.a.a;
import com.xunlei.downloadprovider.cloudlist.a.b;

// compiled from: CloudLixianBTSubFileObatainer.java
final class j extends XLLixianListener {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public final boolean OnObtainBtSubTasks(int i, String str, int i2, XLLixianTask xLLixianTask, XLLixianTask[] xLLixianTaskArr, int i3, int i4, Object obj) {
        if (i == 0) {
            boolean z = false;
            if (xLLixianTaskArr == null || xLLixianTaskArr.length <= 0) {
                z = true;
            } else {
                String a = f.a(xLLixianTask.getDetailInfo().commit_time * 1000);
                for (XLLixianTask xLLixianTask2 : xLLixianTaskArr) {
                    if (xLLixianTask2.getDetailInfo() != null) {
                        a aVar = new a();
                        aVar.a = xLLixianTask2.getDetailInfo().taskname;
                        aVar.b = -1;
                        aVar.c = xLLixianTask2.getDetailInfo().filesize;
                        aVar.d = xLLixianTask2.getDetailInfo().url;
                        aVar.e = xLLixianTask2.getDetailInfo().cid;
                        aVar.f = xLLixianTask2.getDetailInfo().gcid;
                        aVar.g = a;
                        if (xLLixianTask2.getDetailInfo().filetype.getClassType() == 100) {
                            aVar.h = true;
                        } else {
                            aVar.h = false;
                        }
                        this.a.a.add(aVar);
                    }
                }
            }
            if (this.a.b != null) {
                b bVar = this.a.b;
                this.a.a.size();
                bVar.a(i, obj, z);
            }
        } else {
            i.a(this.a, i, obj);
        }
        return true;
    }
}
