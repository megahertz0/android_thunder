package android.support.v7.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources.Theme;
import android.support.v7.appcompat.R;
import android.view.LayoutInflater;

// compiled from: ContextThemeWrapper.java
public final class d extends ContextWrapper {
    public int a;
    private Theme b;
    private LayoutInflater c;

    public d(Context context, int i) {
        super(context);
        this.a = i;
    }

    public final void setTheme(int i) {
        if (this.a != i) {
            this.a = i;
            a();
        }
    }

    public final Theme getTheme() {
        if (this.b != null) {
            return this.b;
        }
        if (this.a == 0) {
            this.a = R.style.Theme_AppCompat_Light;
        }
        a();
        return this.b;
    }

    public final Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.c == null) {
            this.c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.c;
    }

    private void a() {
        if (this.b == null) {
            boolean z = true;
        } else {
            Object obj = null;
        }
        if (z) {
            this.b = getResources().newTheme();
            Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.b.setTo(theme);
            }
        }
        this.b.applyStyle(this.a, true);
    }
}
