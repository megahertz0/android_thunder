package com.xunlei.downloadprovider.download.util;

import android.os.Message;
import android.text.TextUtils;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.d.b;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.kernel.c;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: FreeTrialHelper.java
public class g {
    public static int a;
    public static int b;
    public static int c;
    private static g h;
    private static float j;
    public volatile long d;
    public boolean e;
    public Map<Long, a> f;
    public boolean g;
    private boolean i;

    // compiled from: FreeTrialHelper.java
    public static class a {
        public long a;
        public int b;
        public long c;
        public int d;
        public boolean e;
        public long f;

        public a() {
            this.a = -1;
            this.b = -1;
            this.c = -1;
            this.d = -1;
            this.f = 0;
        }

        public final JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(DownloadBtFileExplorerActivity.EXTRA_KEY_NAME_TASK_ID, this.a);
                jSONObject.put("time", this.c);
                jSONObject.put("state", this.b);
                jSONObject.put("closeFlag", this.d);
                jSONObject.put("noti", this.e);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }
    }

    static {
        a = 0;
        b = 1;
        c = 2;
    }

    public static g a() {
        if (h == null) {
            synchronized (g.class) {
                if (h == null) {
                    h = new g();
                }
            }
        }
        return h;
    }

    public final boolean b() {
        if (this.f == null) {
            return false;
        }
        if (this.d > 0) {
            return ((a) this.f.get(Long.valueOf(this.d))).b > 0;
        } else {
            return false;
        }
    }

    public final void c() {
        if (this.d > 0) {
            ((a) this.f.get(Long.valueOf(this.d))).b = b;
        }
    }

    public final boolean a(long j) {
        if (this.f == null) {
            return false;
        }
        a aVar = (a) this.f.get(Long.valueOf(j));
        if (aVar != null) {
            return aVar.b > a;
        } else {
            return false;
        }
    }

    public static Map<Long, a> d() {
        Map<Long, a> treeMap = new TreeMap();
        Object b = aa.b(BrothersApplication.a(), "trialed_tasks");
        if (TextUtils.isEmpty(b)) {
            return treeMap;
        }
        try {
            JSONArray jSONArray = new JSONObject(b).getJSONArray("tasks");
            long time = new Date().getTime();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                a aVar = new a();
                aVar.a = jSONObject.optLong(DownloadBtFileExplorerActivity.EXTRA_KEY_NAME_TASK_ID, -1);
                aVar.d = jSONObject.optInt("closeFlag", -1);
                aVar.c = jSONObject.optLong("time", -1);
                aVar.b = jSONObject.optInt("state", -1);
                aVar.e = jSONObject.optBoolean("noti", true);
                if (aVar.a > 0 && b.a(time, aVar.c)) {
                    treeMap.put(Long.valueOf(aVar.a), aVar);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return treeMap;
    }

    public final void b(long j) {
        if (this.f != null && this.d > 0) {
            ((a) this.f.get(Long.valueOf(this.d))).f = j;
        }
    }

    public final long e() {
        if (this.f == null) {
            return -1;
        }
        return this.d > 0 ? ((a) this.f.get(Long.valueOf(this.d))).f : 0;
    }

    private g() {
        this.d = -1;
    }

    public final void c(long j) {
        if (this.f != null) {
            this.d = j;
            if (j != -1) {
                a aVar = new a();
                aVar.a = j;
                aVar.c = new Date().getTime();
                aVar.b = a;
                this.i = false;
                this.f.put(Long.valueOf(this.d), aVar);
            }
        }
    }

    public final void a(long j, int i) {
        if (this.f != null) {
            a aVar = (a) this.f.get(Long.valueOf(j));
            if (aVar != null) {
                aVar.b = i;
            }
        }
    }

    public final void a(long j, int i, boolean z) {
        if (this.f != null) {
            a aVar = (a) this.f.get(Long.valueOf(j));
            if (aVar != null) {
                aVar.d = i;
                if (z) {
                    this.i = true;
                }
            }
            new StringBuilder("setCloseFlag ").append(j).append("  ").append(i);
        }
    }

    public final boolean d(long j) {
        a aVar = (a) this.f.get(Long.valueOf(j));
        if (aVar == null) {
            return true;
        }
        return !aVar.e;
    }

    public final void e(long j) {
        a aVar = (a) this.f.get(Long.valueOf(j));
        if (aVar != null) {
            aVar.e = true;
        }
    }

    public final void a(List<TaskRunningInfo> list) {
        if (this.f != null && this.d != -1 && !d.a(list)) {
            for (TaskRunningInfo taskRunningInfo : list) {
                if (taskRunningInfo.getTaskId() == this.d) {
                    this.d = -1;
                    this.f.remove(Long.valueOf(taskRunningInfo.getTaskId()));
                    return;
                }
            }
        }
    }

    public final void a(TaskRunningInfo taskRunningInfo) {
        List arrayList = new ArrayList();
        arrayList.add(taskRunningInfo);
        a(arrayList);
    }

    public final void b(List<TaskInfo> list) {
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        for (TaskInfo taskInfo : list) {
            taskInfo.mIsEnteredHighSpeedTrial = com.xunlei.downloadprovider.service.downloads.task.d.f(taskInfo.getTaskId());
            taskInfo.mFreeTrialTimes = com.xunlei.downloadprovider.service.downloads.task.d.e(taskInfo.getTaskId());
            if (this.d == taskInfo.getTaskId()) {
                LoginHelper.a();
                if (!(LoginHelper.c() && LoginHelper.a().f()) && taskInfo.mIsEnteredHighSpeedTrial) {
                    com.xunlei.downloadprovider.service.downloads.task.d.a();
                    taskInfo.mFreeTrialRemainTime = (long) com.xunlei.downloadprovider.service.downloads.task.d.g(taskInfo.getTaskId());
                    new StringBuilder("  check free trial ----   free_remain time : ").append(taskInfo.mFreeTrialRemainTime);
                    if (n.a(taskInfo)) {
                        List arrayList = new ArrayList();
                        arrayList.add(taskInfo);
                        com.xunlei.downloadprovider.notification.a a = com.xunlei.downloadprovider.notification.a.a(BrothersApplication.a());
                        Message obtainMessage = a.h.obtainMessage();
                        obtainMessage.obj = arrayList;
                        obtainMessage.what = 10003;
                        a.h.sendMessage(obtainMessage);
                    }
                }
            }
        }
    }

    public final void f() {
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        com.xunlei.downloadprovider.service.downloads.task.d.h(this.d);
    }

    public static void g() {
        LoginHelper a = LoginHelper.a();
        if ((!LoginHelper.c() || !a.f()) && com.xunlei.downloadprovider.service.downloads.task.d.a() != null && !com.xunlei.downloadprovider.service.downloads.task.d.q()) {
            com.xunlei.downloadprovider.service.downloads.task.d.b(true);
        }
    }

    public final void f(long j) {
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        LoginHelper.a();
        if (!(LoginHelper.c() && LoginHelper.a().f())) {
            if (!com.xunlei.downloadprovider.service.downloads.task.d.q()) {
                com.xunlei.downloadprovider.service.downloads.task.d.b(true);
            }
            c.a().a.enterHighSpeedTrial(j);
        }
        LoginHelper.a();
        if (LoginHelper.c()) {
            j = 0.2f;
        } else {
            j = 0.1f;
        }
        a(j, b);
    }

    public static float h() {
        return j;
    }

    public static String a(String str) {
        com.xunlei.downloadprovider.download.d.a.a();
        String a = com.xunlei.downloadprovider.member.a.a.b.a("after_trial-text");
        if (a == null || !a.contains("%s")) {
            a = "\u8bd5\u7528\u7ed3\u675f,\u4f1a\u5458%s\u5185\u4e0b\u5b8c";
        }
        return String.format(a, new Object[]{str});
    }

    public static String i() {
        LoginHelper.a();
        if (!LoginHelper.c()) {
            return "\u767b\u5f55\u514d\u8d39\u8bd5\u7528\u4f1a\u5458\u52a0\u901f\u7279\u6743,\u4e0b\u8f7d\u66f4\u5feb";
        }
        com.xunlei.downloadprovider.download.d.a.a();
        return com.xunlei.downloadprovider.member.a.a.b.a("before_trial-text");
    }

    public static String b(String str) {
        com.xunlei.downloadprovider.download.d.a.a();
        String a = com.xunlei.downloadprovider.member.a.a.b.a("on_trial-text");
        if (a == null || !a.contains("%s")) {
            a = "\u4f1a\u5458\u52a0\u901f:\u8fd8\u5269%s,\u4f1a\u5458\u4e0d\u9650\u91cf";
        }
        return String.format(a, new Object[]{str});
    }

    public static String j() {
        return "\u5bf9\u4e0d\u8d77,\u8fdb\u5165\u8bd5\u7528\u5931\u8d25";
    }

    public static String k() {
        return "\u5f00\u901a\u4f1a\u5458,\u7545\u4eab\u4f1a\u5458\u52a0\u901f";
    }

    public static String c(String str) {
        com.xunlei.downloadprovider.download.d.a.a();
        String a = com.xunlei.downloadprovider.member.a.a.b.a("ending_trial-text");
        if (a == null || !a.contains("%s")) {
            a = "\u8bd5\u7528\u5c06\u7ed3\u675f,\u4f1a\u5458%s\u5185\u4e0b\u5b8c";
        }
        return String.format(a, new Object[]{str});
    }

    public static String l() {
        com.xunlei.downloadprovider.download.d.a.a();
        return com.xunlei.downloadprovider.member.a.a.b.a("after_trial-button");
    }

    public static String m() {
        com.xunlei.downloadprovider.download.d.a.a();
        return com.xunlei.downloadprovider.member.a.a.b.a("before_trial-button");
    }

    public static String n() {
        com.xunlei.downloadprovider.download.d.a.a();
        return com.xunlei.downloadprovider.member.a.a.b.a("on_trial-button");
    }

    public static String o() {
        return "\u70b9\u51fb\u53cd\u9988";
    }
}
