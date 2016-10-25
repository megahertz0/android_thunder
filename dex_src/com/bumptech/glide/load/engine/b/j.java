package com.bumptech.glide.load.engine.b;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: MemorySizeCalculator.java
public final class j {
    public final int a;
    public final int b;
    private final Context c;

    // compiled from: MemorySizeCalculator.java
    static interface b {
        int a();

        int b();
    }

    // compiled from: MemorySizeCalculator.java
    private static class a implements b {
        private final DisplayMetrics a;

        public a(DisplayMetrics displayMetrics) {
            this.a = displayMetrics;
        }

        public final int a() {
            return this.a.widthPixels;
        }

        public final int b() {
            return this.a.heightPixels;
        }
    }

    public j(Context context) {
        this(context, (ActivityManager) context.getSystemService("activity"), new a(context.getResources().getDisplayMetrics()));
    }

    private j(Context context, ActivityManager activityManager, b bVar) {
        float f;
        this.c = context;
        float memoryClass = (float) ((activityManager.getMemoryClass() * 1024) * 1024);
        if (a(activityManager)) {
            f = 0.33f;
        } else {
            f = 0.4f;
        }
        int round = Math.round(f * memoryClass);
        int a = (bVar.a() * bVar.b()) * 4;
        int i = a * 4;
        a *= 2;
        if (a + i <= round) {
            this.b = a;
            this.a = i;
        } else {
            int round2 = Math.round(((float) round) / 6.0f);
            this.b = round2 * 2;
            this.a = round2 * 4;
        }
        if (Log.isLoggable("MemorySizeCalculator", XZBDevice.DOWNLOAD_LIST_FAILED)) {
            new StringBuilder("Calculated memory cache size: ").append(a(this.b)).append(" pool size: ").append(a(this.a)).append(" memory class limited? ").append(a + i > round).append(" max size: ").append(a(round)).append(" memoryClass: ").append(activityManager.getMemoryClass()).append(" isLowMemoryDevice: ").append(a(activityManager));
        }
    }

    private String a(int i) {
        return Formatter.formatFileSize(this.c, (long) i);
    }

    @TargetApi(19)
    private static boolean a(ActivityManager activityManager) {
        int i = VERSION.SDK_INT;
        return i < 11 || (i >= 19 && activityManager.isLowRamDevice());
    }
}
