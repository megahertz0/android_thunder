package com.xunlei.downloadprovider.member.register.view;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// compiled from: LoginRegisterToast.java
public final class a {
    private static long d;
    private static CharSequence e;
    private static long f;
    boolean a;
    Object b;
    Method c;
    private boolean g;
    private Toast h;
    private Context i;
    private long j;
    private Method k;
    private WindowManager l;
    private LayoutParams m;
    private View n;
    private Handler o;
    private Runnable p;

    static {
        d = 0;
        e = com.umeng.a.d;
        f = 0;
    }

    private a(Context context, int i, CharSequence charSequence) {
        this.j = 2000;
        this.a = false;
        this.o = new Handler();
        this.p = new b(this);
        long currentTimeMillis = System.currentTimeMillis();
        if (charSequence == null || 3500 <= 0 || (charSequence.equals(e) && currentTimeMillis - d < f)) {
            this.g = true;
            return;
        }
        d = currentTimeMillis;
        e = charSequence;
        f = 3500;
        this.i = context;
        if (this.h == null) {
            this.h = new Toast(this.i);
        }
        this.n = ((LayoutInflater) this.i.getSystemService("layout_inflater")).inflate(2130969004, null);
        TextView textView = (TextView) this.n.findViewById(2131757068);
        textView.setText(charSequence);
        textView.setBackgroundResource(i == 1 ? 2130839464 : 2130839465);
        this.j = 3500;
    }

    public final void a() {
        if (!this.g && !this.a) {
            this.h.setView(this.n);
            try {
                Field declaredField = this.h.getClass().getDeclaredField("mTN");
                declaredField.setAccessible(true);
                this.b = declaredField.get(this.h);
                this.k = this.b.getClass().getMethod("show", new Class[0]);
                this.c = this.b.getClass().getMethod("hide", new Class[0]);
                declaredField = this.b.getClass().getDeclaredField("mParams");
                declaredField.setAccessible(true);
                this.m = (LayoutParams) declaredField.get(this.b);
                this.m.flags = 40;
                this.m.windowAnimations = 2131427931;
                declaredField = this.b.getClass().getDeclaredField("mNextView");
                declaredField.setAccessible(true);
                declaredField.set(this.b, this.h.getView());
                this.l = (WindowManager) this.i.getApplicationContext().getSystemService("window");
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.h.setGravity(R.styleable.AppCompatTheme_homeAsUpIndicator, 0, g.a(this.i, 60.0f));
            try {
                this.k.invoke(this.b, new Object[0]);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.a = true;
            this.o.postDelayed(this.p, this.j);
        }
    }

    public static a a(Context context, int i, CharSequence charSequence) {
        Toast makeText = Toast.makeText(context, charSequence, 0);
        a aVar = new a(context, i, charSequence);
        aVar.h = makeText;
        return aVar;
    }

    public static void a(Context context, int i) {
        a(context, 1, context.getString(i)).a();
    }

    public static void b(Context context, int i) {
        a(context, context.getString(i));
    }

    public static void a(Context context, String str) {
        a(context, XZBDevice.DOWNLOAD_LIST_RECYCLE, str).a();
    }
}
