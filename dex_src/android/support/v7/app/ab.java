package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.d;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.R;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

// compiled from: AppCompatViewInflater.java
final class ab {
    private static final Class<?>[] a;
    private static final int[] b;
    private static final String[] c;
    private static final Map<String, Constructor<? extends View>> d;
    private final Object[] e;

    // compiled from: AppCompatViewInflater.java
    private static class a implements OnClickListener {
        private final View a;
        private final String b;
        private Method c;
        private Context d;

        public a(View view, String str) {
            this.a = view;
            this.b = str;
        }

        public final void onClick(View view) {
            if (this.c == null) {
                Context context = this.a.getContext();
                while (context != null) {
                    try {
                        if (!context.isRestricted()) {
                            Method method = context.getClass().getMethod(this.b, new Class[]{View.class});
                            if (method != null) {
                                this.c = method;
                                this.d = context;
                            }
                        }
                    } catch (NoSuchMethodException e) {
                    }
                    if (context instanceof ContextWrapper) {
                        context = ((ContextWrapper) context).getBaseContext();
                    } else {
                        context = null;
                    }
                }
                int id = this.a.getId();
                throw new IllegalStateException(new StringBuilder("Could not find method ").append(this.b).append("(View) in a parent or ancestor Context for android:onClick attribute defined on view ").append(this.a.getClass()).append(id == -1 ? com.umeng.a.d : new StringBuilder(" with id '").append(this.a.getContext().getResources().getResourceEntryName(id)).append("'").toString()).toString());
            }
            try {
                this.c.invoke(this.d, new Object[]{view});
            } catch (Throwable e2) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e2);
            } catch (Throwable e22) {
                throw new IllegalStateException("Could not execute method for android:onClick", e22);
            }
        }
    }

    ab() {
        this.e = new Object[2];
    }

    static {
        a = new Class[]{Context.class, AttributeSet.class};
        b = new int[]{16843375};
        c = new String[]{"android.widget.", "android.view.", "android.webkit."};
        d = new ArrayMap();
    }

    final View a(Context context, String str, AttributeSet attributeSet) {
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            this.e[0] = context;
            this.e[1] = attributeSet;
            View a;
            if (-1 == str.indexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight)) {
                for (int i = 0; i < c.length; i++) {
                    a = a(context, str, c[i]);
                    if (a != null) {
                        this.e[0] = null;
                        this.e[1] = null;
                        return a;
                    }
                }
                this.e[0] = null;
                this.e[1] = null;
                return null;
            }
            a = a(context, str, null);
            this.e[0] = null;
            this.e[1] = null;
            return a;
        } catch (Exception e) {
            this.e[0] = null;
            this.e[1] = null;
            return null;
        }
    }

    static void a(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if (!(context instanceof ContextWrapper)) {
            return;
        }
        if (VERSION.SDK_INT < 15 || ViewCompat.hasOnClickListeners(view)) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b);
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                view.setOnClickListener(new a(view, string));
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View a(android.content.Context r3, java.lang.String r4, java.lang.String r5) throws java.lang.ClassNotFoundException, android.view.InflateException {
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.ab.a(android.content.Context, java.lang.String, java.lang.String):android.view.View");
        /*
        this = this;
        r0 = d;
        r0 = r0.get(r4);
        r0 = (java.lang.reflect.Constructor) r0;
        if (r0 != 0) goto L_0x0036;
    L_0x000a:
        r1 = r3.getClassLoader();	 Catch:{ Exception -> 0x0045 }
        if (r5 == 0) goto L_0x0043;
    L_0x0010:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0045 }
        r0.<init>();	 Catch:{ Exception -> 0x0045 }
        r0 = r0.append(r5);	 Catch:{ Exception -> 0x0045 }
        r0 = r0.append(r4);	 Catch:{ Exception -> 0x0045 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0045 }
    L_0x0021:
        r0 = r1.loadClass(r0);	 Catch:{ Exception -> 0x0045 }
        r1 = android.view.View.class;
        r0 = r0.asSubclass(r1);	 Catch:{ Exception -> 0x0045 }
        r1 = a;	 Catch:{ Exception -> 0x0045 }
        r0 = r0.getConstructor(r1);	 Catch:{ Exception -> 0x0045 }
        r1 = d;	 Catch:{ Exception -> 0x0045 }
        r1.put(r4, r0);	 Catch:{ Exception -> 0x0045 }
    L_0x0036:
        r1 = 1;
        r0.setAccessible(r1);	 Catch:{ Exception -> 0x0045 }
        r1 = r2.e;	 Catch:{ Exception -> 0x0045 }
        r0 = r0.newInstance(r1);	 Catch:{ Exception -> 0x0045 }
        r0 = (android.view.View) r0;	 Catch:{ Exception -> 0x0045 }
    L_0x0042:
        return r0;
    L_0x0043:
        r0 = r4;
        goto L_0x0021;
    L_0x0045:
        r0 = move-exception;
        r0 = 0;
        goto L_0x0042;
        */
    }

    static Context a(Context context, AttributeSet attributeSet, boolean z) {
        int resourceId;
        int resourceId2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, android.support.v7.appcompat.R.styleable.View, 0, 0);
        if (z) {
            resourceId = obtainStyledAttributes.getResourceId(android.support.v7.appcompat.R.styleable.View_android_theme, 0);
        } else {
            resourceId = 0;
        }
        if (resourceId == 0) {
            resourceId2 = obtainStyledAttributes.getResourceId(android.support.v7.appcompat.R.styleable.View_theme, 0);
        } else {
            resourceId2 = resourceId;
        }
        obtainStyledAttributes.recycle();
        if (resourceId2 != 0) {
            return ((context instanceof d) && ((d) context).a == resourceId2) ? context : new d(context, resourceId2);
        } else {
            return context;
        }
    }
}
