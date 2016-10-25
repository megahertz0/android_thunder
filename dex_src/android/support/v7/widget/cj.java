package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.support.v7.app.m;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// compiled from: TintContextWrapper.java
public final class cj extends ContextWrapper {
    private static final ArrayList<WeakReference<cj>> a;
    private Resources b;
    private final Theme c;

    static {
        a = new ArrayList();
    }

    public static Context a(Context context) {
        Object obj;
        if ((context instanceof cj) || (context.getResources() instanceof cl) || (context.getResources() instanceof cu)) {
            obj = null;
        } else if (!m.j() || VERSION.SDK_INT <= 20) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null) {
            return context;
        }
        Context context2;
        int size = a.size();
        for (int i = 0; i < size; i++) {
            WeakReference weakReference = (WeakReference) a.get(i);
            context2 = weakReference != null ? (cj) weakReference.get() : null;
            if (context2 != null && context2.getBaseContext() == context) {
                return context2;
            }
        }
        context2 = new cj(context);
        a.add(new WeakReference(context2));
        return context2;
    }

    private cj(Context context) {
        super(context);
        if (cu.a()) {
            this.c = getResources().newTheme();
            this.c.setTo(context.getTheme());
            return;
        }
        this.c = null;
    }

    public final Theme getTheme() {
        return this.c == null ? super.getTheme() : this.c;
    }

    public final void setTheme(int i) {
        if (this.c == null) {
            super.setTheme(i);
        } else {
            this.c.applyStyle(i, true);
        }
    }

    public final Resources getResources() {
        if (this.b == null) {
            this.b = this.c == null ? new cl(this, super.getResources()) : new cu(this, super.getResources());
        }
        return this.b;
    }
}
