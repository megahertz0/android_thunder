package com.inmobi.ads;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.uc.addon.sdk.remote.Tabs;
import java.util.HashMap;
import java.util.Map;

// compiled from: NativeStrandDataSource.java
final class y extends PagerAdapter {
    private static final String a;
    private static Handler e;
    private boolean b;
    private final x c;
    private ac d;
    private Map<Integer, Runnable> f;

    // compiled from: NativeStrandDataSource.java
    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ Object a;

        AnonymousClass_1(Object obj) {
            this.a = obj;
        }

        public void run() {
            y.this.d.a((View) this.a);
        }
    }

    // compiled from: NativeStrandDataSource.java
    class AnonymousClass_2 implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ ViewGroup b;
        final /* synthetic */ ViewGroup c;
        final /* synthetic */ v d;

        AnonymousClass_2(int i, ViewGroup viewGroup, ViewGroup viewGroup2, v vVar) {
            this.a = i;
            this.b = viewGroup;
            this.c = viewGroup2;
            this.d = vVar;
        }

        public void run() {
            if (!y.this.b) {
                y.this.f.remove(Integer.valueOf(this.a));
                y.this.d.a(this.b, this.c, this.d);
            }
        }
    }

    static {
        a = y.class.getSimpleName();
        e = new Handler(Looper.getMainLooper());
    }

    y(x xVar, ac acVar) {
        this.f = new HashMap();
        this.c = xVar;
        this.d = acVar;
    }

    public final int getItemPosition(Object obj) {
        Object tag = obj == null ? null : ((View) obj).getTag();
        return (tag == null || !(tag instanceof Integer)) ? Tabs.TAB_CREATE_REACH_MAX_COUNT : ((Integer) tag).intValue();
    }

    public final int getCount() {
        return this.c.d();
    }

    public final boolean isViewFromObject(View view, Object obj) {
        return view.equals(obj);
    }

    @TargetApi(21)
    public final Object instantiateItem(ViewGroup viewGroup, int i) {
        Logger.a(InternalLogLevel.INTERNAL, aj.class.getSimpleName(), new StringBuilder("Inflating card at index:").append(i).toString());
        NativeStrandAsset a = this.c.a(i);
        if (a == null) {
            return null;
        }
        Object a2 = a(i, viewGroup, a);
        a2.setLayoutParams(aj.a(a, viewGroup));
        a2.setTag(Integer.valueOf(i));
        viewGroup.addView(a2);
        return a2;
    }

    public final void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
        Runnable runnable = (Runnable) this.f.get(Integer.valueOf(i));
        if (runnable != null) {
            e.removeCallbacks(runnable);
            Logger.a(InternalLogLevel.INTERNAL, aj.class.getSimpleName(), new StringBuilder("Cleared pending task at position:").append(i).toString());
        }
        e.post(new AnonymousClass_1(obj));
    }

    private ViewGroup a(int i, ViewGroup viewGroup, v vVar) {
        ViewGroup a = this.d.a(viewGroup, vVar);
        int abs = Math.abs(this.d.a() - i);
        Runnable anonymousClass_2 = new AnonymousClass_2(i, a, viewGroup, vVar);
        this.f.put(Integer.valueOf(i), anonymousClass_2);
        e.postDelayed(anonymousClass_2, (long) (abs * 50));
        return a;
    }

    final void a() {
        this.b = true;
        for (Runnable runnable : this.f.values()) {
            e.removeCallbacks(runnable);
        }
        this.f.clear();
    }
}
