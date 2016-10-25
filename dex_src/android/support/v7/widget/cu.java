package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.app.m;
import java.lang.ref.WeakReference;

// compiled from: VectorEnabledTintResources.java
public final class cu extends Resources {
    private final WeakReference<Context> a;

    public static boolean a() {
        return m.j() && VERSION.SDK_INT <= 20;
    }

    public cu(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.a = new WeakReference(context);
    }

    public final Drawable getDrawable(int i) throws NotFoundException {
        Context context = (Context) this.a.get();
        if (context == null) {
            return super.getDrawable(i);
        }
        r a = r.a();
        Drawable a2 = a.a(context, i);
        if (a2 == null) {
            a2 = super.getDrawable(i);
        }
        return a2 != null ? a.a(context, i, false, a2) : null;
    }
}
