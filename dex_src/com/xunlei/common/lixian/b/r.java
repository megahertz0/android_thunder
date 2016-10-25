package com.xunlei.common.lixian.b;

import com.xunlei.common.lixian.XLLixianTask;
import java.util.Comparator;

final class r implements Comparator {
    private /* synthetic */ o a;

    r(o oVar) {
    }

    private static int a(XLLixianTask xLLixianTask, XLLixianTask xLLixianTask2) {
        return xLLixianTask.getDetailInfo().commit_time > xLLixianTask2.getDetailInfo().commit_time ? -1 : xLLixianTask.getDetailInfo().commit_time < xLLixianTask2.getDetailInfo().commit_time ? 1 : 0;
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        XLLixianTask xLLixianTask = (XLLixianTask) obj;
        XLLixianTask xLLixianTask2 = (XLLixianTask) obj2;
        return xLLixianTask.getDetailInfo().commit_time > xLLixianTask2.getDetailInfo().commit_time ? -1 : xLLixianTask.getDetailInfo().commit_time < xLLixianTask2.getDetailInfo().commit_time ? 1 : 0;
    }
}
