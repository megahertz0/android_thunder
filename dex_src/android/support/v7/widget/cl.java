package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

// compiled from: TintResources.java
final class cl extends bj {
    private final WeakReference<Context> a;

    public cl(Context context, Resources resources) {
        super(resources);
        this.a = new WeakReference(context);
    }

    public final Drawable getDrawable(int i) throws NotFoundException {
        Drawable drawable = super.getDrawable(i);
        Context context = (Context) this.a.get();
        if (!(drawable == null || context == null)) {
            r.a();
            r.a(context, i, drawable);
        }
        return drawable;
    }
}
