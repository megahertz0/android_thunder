package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

// compiled from: AppCompatDelegate.java
public abstract class m {
    static int a;
    private static boolean b;

    public abstract ActionBar a();

    public abstract View a(int i);

    public abstract void a(Configuration configuration);

    public abstract void a(Bundle bundle);

    public abstract void a(View view);

    public abstract void a(View view, LayoutParams layoutParams);

    public abstract void a(CharSequence charSequence);

    public abstract MenuInflater b();

    public abstract void b(int i);

    public abstract void b(Bundle bundle);

    public abstract void b(View view, LayoutParams layoutParams);

    public abstract void c();

    public abstract boolean c(int i);

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    public abstract boolean i();

    static {
        a = -1;
        b = false;
    }

    public static m a(Activity activity, l lVar) {
        return a(activity, activity.getWindow(), lVar);
    }

    public static m a(Dialog dialog, l lVar) {
        return a(dialog.getContext(), dialog.getWindow(), lVar);
    }

    private static m a(Context context, Window window, l lVar) {
        int i = VERSION.SDK_INT;
        if (i >= 23) {
            return new q(context, window, lVar);
        }
        if (i >= 14) {
            return new p(context, window, lVar);
        }
        return i >= 11 ? new o(context, window, lVar) : new AppCompatDelegateImplV7(context, window, lVar);
    }

    m() {
    }

    public static boolean j() {
        return b;
    }
}
