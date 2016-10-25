package com.xunlei.downloadprovider.download.tasklist.a;

import android.os.SystemClock;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskBasicInfo;
import java.util.HashMap;
import java.util.List;

// compiled from: TaskListManager.java
final class n implements Runnable {
    final /* synthetic */ h$b a;

    n(h$b com_xunlei_downloadprovider_download_tasklist_a_h_b) {
        this.a = com_xunlei_downloadprovider_download_tasklist_a_h_b;
    }

    public final void run() {
        h$b com_xunlei_downloadprovider_download_tasklist_a_h_b = this.a;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Object obj = null;
        while (true) {
            Object obj2;
            c cVar;
            long elapsedRealtime2;
            Object obj3 = null;
            synchronized (com_xunlei_downloadprovider_download_tasklist_a_h_b.b) {
                if (com_xunlei_downloadprovider_download_tasklist_a_h_b.b.isEmpty()) {
                    obj2 = 1;
                    cVar = null;
                } else {
                    c cVar2;
                    if (SystemClock.elapsedRealtime() - elapsedRealtime >= 1000 || com_xunlei_downloadprovider_download_tasklist_a_h_b.e) {
                        com_xunlei_downloadprovider_download_tasklist_a_h_b.e = false;
                        c cVar3 = (c) com_xunlei_downloadprovider_download_tasklist_a_h_b.b.removeFirst();
                        while (!com_xunlei_downloadprovider_download_tasklist_a_h_b.b.isEmpty()) {
                            TaskBasicInfo taskBasicInfo;
                            int i;
                            cVar2 = (c) com_xunlei_downloadprovider_download_tasklist_a_h_b.b.getFirst();
                            if (cVar2.b) {
                                cVar3.b = true;
                            }
                            List<TaskBasicInfo> list = cVar2.c;
                            HashMap hashMap = new HashMap();
                            if (!(cVar3.c == null || cVar3.c.isEmpty())) {
                                for (TaskBasicInfo taskBasicInfo2 : cVar3.c) {
                                    hashMap.put(Long.valueOf(taskBasicInfo2.mTaskId), taskBasicInfo2);
                                }
                            }
                            if (list != null) {
                                if (!list.isEmpty()) {
                                    int i2 = 0;
                                    for (TaskBasicInfo taskBasicInfo3 : list) {
                                        taskBasicInfo2 = (TaskBasicInfo) hashMap.get(Long.valueOf(taskBasicInfo3.mTaskId));
                                        if (taskBasicInfo2 != null) {
                                            taskBasicInfo2.deepCopyFrom(taskBasicInfo3);
                                            hashMap.remove(Long.valueOf(taskBasicInfo3.mTaskId));
                                        } else {
                                            i2++;
                                        }
                                    }
                                    i = i2;
                                    if (hashMap.size() > 0) {
                                        i += hashMap.size();
                                    }
                                    if (i != 0) {
                                        com_xunlei_downloadprovider_download_tasklist_a_h_b.b.remove(cVar2);
                                    } else {
                                        com_xunlei_downloadprovider_download_tasklist_a_h_b.b.remove(cVar2);
                                        cVar3 = cVar2;
                                    }
                                }
                            }
                            i = 0;
                            if (hashMap.size() > 0) {
                                i += hashMap.size();
                            }
                            if (i != 0) {
                                com_xunlei_downloadprovider_download_tasklist_a_h_b.b.remove(cVar2);
                                cVar3 = cVar2;
                            } else {
                                com_xunlei_downloadprovider_download_tasklist_a_h_b.b.remove(cVar2);
                            }
                        }
                        cVar2 = cVar3;
                    }
                    obj2 = obj;
                    cVar = cVar2;
                }
            }
            if (cVar != null) {
                elapsedRealtime2 = SystemClock.elapsedRealtime();
                com_xunlei_downloadprovider_download_tasklist_a_h_b.a(cVar.c, cVar.b);
                cVar.a();
                a aVar = com_xunlei_downloadprovider_download_tasklist_a_h_b.c;
                if (cVar != null) {
                    synchronized (aVar.a) {
                        if (aVar.a.size() >= aVar.b) {
                        } else {
                            aVar.a.add(cVar);
                        }
                    }
                }
            } else {
                elapsedRealtime2 = elapsedRealtime;
            }
            if (obj2 == null) {
                elapsedRealtime = elapsedRealtime2;
                obj = obj2;
            } else {
                return;
            }
        }
    }
}
