package com.xunlei.downloadprovider.model.protocol;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"UseSparseArrays"})
// compiled from: ApkIconController.java
public final class a {
    private static a a;
    private Map<Integer, SoftReference<Drawable>> b;

    static {
        a = new a();
    }

    private static String b(TaskRunningInfo taskRunningInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        BrothersApplication brothersApplication = BrothersApplication.a;
        return stringBuilder.append(com.xunlei.downloadprovider.businessutil.a.i()).append(taskRunningInfo.mLastModifiedTime).append("_").append(taskRunningInfo.mTaskId).toString();
    }

    public static a a() {
        return a;
    }

    public final Drawable a(TaskRunningInfo taskRunningInfo) {
        try {
            SoftReference softReference = (SoftReference) this.b.get(Long.valueOf(taskRunningInfo.mTaskId));
            Drawable c;
            if (softReference == null) {
                c = c(taskRunningInfo);
                if (c == null) {
                    return c;
                }
                this.b.put(Integer.valueOf((int) taskRunningInfo.mTaskId), new SoftReference(c));
                return c;
            }
            c = (Drawable) softReference.get();
            if (c != null) {
                return c;
            }
            c = c(taskRunningInfo);
            if (c == null) {
                return c;
            }
            this.b.put(Integer.valueOf((int) taskRunningInfo.mTaskId), new SoftReference(c));
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private a() {
        this.b = new HashMap(8);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.drawable.Drawable c(com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.model.protocol.a.c(com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo):android.graphics.drawable.Drawable");
        /*
        r1 = 0;
        r2 = b(r4);	 Catch:{ Exception -> 0x0034 }
        r0 = com.xunlei.downloadprovider.d.c.b(r2);	 Catch:{ Exception -> 0x0034 }
        if (r0 == 0) goto L_0x0040;
    L_0x000b:
        r0 = new android.graphics.drawable.BitmapDrawable;	 Catch:{ Exception -> 0x0034 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x0034 }
        r2 = r0.getBitmap();	 Catch:{ Exception -> 0x0034 }
        if (r2 == 0) goto L_0x0040;
    L_0x0016:
        if (r0 != 0) goto L_0x0033;
    L_0x0018:
        r1 = 8;
        r2 = r4.mTaskStatus;	 Catch:{ Exception -> 0x003c }
        if (r1 != r2) goto L_0x0033;
    L_0x001e:
        r1 = com.xunlei.downloadprovider.app.BrothersApplication.a;	 Catch:{ Exception -> 0x003c }
        r2 = r4.mLocalFileName;	 Catch:{ Exception -> 0x003c }
        r1 = com.xunlei.downloadprovider.a.c.a(r1, r2);	 Catch:{ Exception -> 0x003c }
        if (r1 == 0) goto L_0x0033;
    L_0x0028:
        r0 = r1.b();	 Catch:{ Exception -> 0x003c }
        r1 = b(r4);	 Catch:{ Exception -> 0x003e }
        com.xunlei.downloadprovider.model.protocol.b.a(r1, r0);	 Catch:{ Exception -> 0x003e }
    L_0x0033:
        return r0;
    L_0x0034:
        r0 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r3;
    L_0x0038:
        r1.printStackTrace();
        goto L_0x0033;
    L_0x003c:
        r1 = move-exception;
        goto L_0x0038;
    L_0x003e:
        r1 = move-exception;
        goto L_0x0033;
    L_0x0040:
        r0 = r1;
        goto L_0x0016;
        */
    }
}
