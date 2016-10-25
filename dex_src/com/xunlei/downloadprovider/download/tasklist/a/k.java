package com.xunlei.downloadprovider.download.tasklist.a;

import android.os.SystemClock;
import android.text.TextUtils;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.downloadprovider.member.a.a.b;
import com.xunlei.downloadprovider.service.downloads.task.a.k.a;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

// compiled from: TaskListManager.java
final class k extends a<List<a>> {
    final /* synthetic */ boolean a;
    final /* synthetic */ h b;

    k(h hVar, List list, boolean z) {
        this.b = hVar;
        this.a = z;
        super(list);
    }

    public final /* synthetic */ void a(Object obj) {
        List<a> list = (List) obj;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (h.a(this.b).a == 0) {
            h.a(this.b).a = elapsedRealtime;
        }
        HashSet hashSet = new HashSet(this.b.a.keySet());
        if (!list.isEmpty()) {
            boolean z = false;
            long j = 0;
            Object obj2 = null;
            long j2 = 0;
            for (a aVar : list) {
                long j3;
                boolean z2;
                long j4;
                hashSet.remove(Long.valueOf(aVar.mTaskId));
                h.a(aVar);
                h.b(aVar);
                if (aVar.mTaskStatus == 2) {
                    z = true;
                    j2 += aVar.mDownloadSpeed;
                    if (aVar.mHasLixianSpeedup) {
                        j += aVar.mLixianSpeed;
                        obj2 = 1;
                    }
                    if (aVar.mHasVipChannelSpeedup) {
                        j3 = j + aVar.mVipChannelSpeed;
                        j = j2;
                        int i = 1;
                        z2 = true;
                        j4 = j3;
                        z = z2;
                        j3 = j;
                        j = j4;
                        obj2 = r6;
                        j2 = j3;
                    }
                }
                j3 = j;
                j = j2;
                Object obj3 = obj2;
                z2 = z;
                j4 = j3;
                z = z2;
                j3 = j;
                j = j4;
                obj2 = obj3;
                j2 = j3;
            }
            if (j2 < 102400) {
                int i2 = 1;
            } else {
                Object obj4 = null;
            }
            Object obj5 = j2 < 51200 ? 1 : null;
            int i3 = XLRegErrorCode.REG_IP_CONTROL;
            com.xunlei.downloadprovider.download.d.a.a();
            if (!TextUtils.isEmpty(b.a("netspeed"))) {
                com.xunlei.downloadprovider.download.d.a.a();
                i3 = Integer.parseInt(b.a("netspeed"));
            }
            Object obj6 = j2 > ((long) (i3 * 1024)) ? 1 : null;
            if (!z || r10 == null) {
                h.a(this.b).e = 0;
                h.a(this.b).b = false;
            } else {
                h$a a = h.a(this.b);
                a.e += elapsedRealtime - h.a(this.b).a;
                if (h.a(this.b).e >= 10000) {
                    h.a(this.b).b = true;
                }
            }
            if (!z || obj5 == null) {
                h.a(this.b).f = 0;
                h.a(this.b).c = false;
            } else {
                h$a a2 = h.a(this.b);
                a2.f += elapsedRealtime - h.a(this.b).a;
                if (h.a(this.b).f >= 10000) {
                    h.a(this.b).c = true;
                }
            }
            if (!z || obj6 == null) {
                h.a(this.b).d = false;
            } else {
                h.a(this.b).d = true;
            }
            h.a(this.b).i.a(Boolean.valueOf(z));
            h$a a3 = h.a(this.b);
            boolean z3 = obj2 != null && j > 0;
            a3.g = z3;
            h.a(this.b).a = elapsedRealtime;
            h.a(this.b).h = j2;
            h.b(this.b).execute(new l(this, list));
        }
        List arrayList = new ArrayList();
        if (!hashSet.isEmpty()) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                TaskRunningInfo taskRunningInfo = (TaskRunningInfo) this.b.a.remove((Long) it.next());
                if (taskRunningInfo != null) {
                    arrayList.add(taskRunningInfo);
                }
            }
        }
        if (!arrayList.isEmpty()) {
            this.b.a(arrayList);
        }
        try {
            this.b.b.a(list, this.a);
            this.b.c.a(list, this.a);
            this.b.d.a(list, this.a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new StringBuilder("UpdateTaskInfoList: cost = ").append(SystemClock.elapsedRealtime() - elapsedRealtime).append("ms, size =  ").append(list.size());
    }
}
