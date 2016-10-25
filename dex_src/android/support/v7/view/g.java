package android.support.v7.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.j;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import com.tencent.open.wpa.WPA;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.android.spdy.SpdyAgent;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// compiled from: SupportMenuInflater.java
public final class g extends MenuInflater {
    private static final Class<?>[] a;
    private static final Class<?>[] b;
    private final Object[] c;
    private final Object[] d;
    private Context e;
    private Object f;

    // compiled from: SupportMenuInflater.java
    private static class a implements OnMenuItemClickListener {
        private static final Class<?>[] a;
        private Object b;
        private Method c;

        static {
            a = new Class[]{MenuItem.class};
        }

        public a(Object obj, String str) {
            this.b = obj;
            Class cls = obj.getClass();
            try {
                this.c = cls.getMethod(str, a);
            } catch (Throwable e) {
                InflateException inflateException = new InflateException(new StringBuilder("Couldn't resolve menu item onClick handler ").append(str).append(" in class ").append(cls.getName()).toString());
                inflateException.initCause(e);
                throw inflateException;
            }
        }

        public final boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.c.invoke(this.b, new Object[]{menuItem})).booleanValue();
                }
                this.c.invoke(this.b, new Object[]{menuItem});
                return true;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    // compiled from: SupportMenuInflater.java
    private class b {
        Menu a;
        int b;
        int c;
        int d;
        int e;
        boolean f;
        boolean g;
        boolean h;
        int i;
        int j;
        CharSequence k;
        CharSequence l;
        int m;
        char n;
        char o;
        int p;
        boolean q;
        boolean r;
        boolean s;
        int t;
        int u;
        String v;
        String w;
        String x;
        ActionProvider y;

        public b(Menu menu) {
            this.a = menu;
            a();
        }

        public final void a() {
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = true;
            this.g = true;
        }

        static char a(String str) {
            return str == null ? '\u0000' : str.charAt(0);
        }

        final void a(MenuItem menuItem) {
            boolean z = true;
            menuItem.setChecked(this.q).setVisible(this.r).setEnabled(this.s).setCheckable(this.p > 0).setTitleCondensed(this.l).setIcon(this.m).setAlphabeticShortcut(this.n).setNumericShortcut(this.o);
            if (this.t >= 0) {
                MenuItemCompat.setShowAsAction(menuItem, this.t);
            }
            if (this.x != null) {
                if (g.this.e.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new a(g.b(g.this), this.x));
            }
            if (this.p >= 2) {
                if (menuItem instanceof h) {
                    ((h) menuItem).a(true);
                } else if (menuItem instanceof j) {
                    j jVar = (j) menuItem;
                    try {
                        if (jVar.e == null) {
                            jVar.e = ((SupportMenuItem) jVar.d).getClass().getDeclaredMethod("setExclusiveCheckable", new Class[]{Boolean.TYPE});
                        }
                        jVar.e.invoke(jVar.d, new Object[]{Boolean.valueOf(true)});
                    } catch (Exception e) {
                    }
                }
            }
            if (this.v != null) {
                MenuItemCompat.setActionView(menuItem, (View) a(this.v, a, g.this.c));
            } else {
                z = false;
            }
            if (this.u > 0 && !r2) {
                MenuItemCompat.setActionView(menuItem, this.u);
            }
            if (this.y != null) {
                MenuItemCompat.setActionProvider(menuItem, this.y);
            }
        }

        public final SubMenu b() {
            this.h = true;
            SubMenu addSubMenu = this.a.addSubMenu(this.b, this.i, this.j, this.k);
            a(addSubMenu.getItem());
            return addSubMenu;
        }

        final <T> T a(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor constructor = g.this.e.getClassLoader().loadClass(str).getConstructor(clsArr);
                constructor.setAccessible(true);
                return constructor.newInstance(objArr);
            } catch (Exception e) {
                return null;
            }
        }
    }

    static {
        Class[] clsArr = new Class[]{Context.class};
        a = clsArr;
        b = clsArr;
    }

    public g(Context context) {
        super(context);
        this.e = context;
        this.c = new Object[]{context};
        this.d = this.c;
    }

    public final void inflate(int i, Menu menu) {
        if (menu instanceof SupportMenu) {
            Object obj = null;
            try {
                obj = this.e.getResources().getLayout(i);
                a(obj, Xml.asAttributeSet(obj), menu);
                if (obj != null) {
                    obj.close();
                }
            } catch (Throwable e) {
                throw new InflateException("Error inflating menu XML", e);
            } catch (Throwable e2) {
                throw new InflateException("Error inflating menu XML", e2);
            } catch (Throwable th) {
                if (r1 != null) {
                    r1.close();
                }
            }
        } else {
            super.inflate(i, menu);
        }
    }

    private void a(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
        b bVar = new b(menu);
        int eventType = xmlPullParser.getEventType();
        Object obj = null;
        Object obj2 = null;
        while (eventType != 2) {
            eventType = xmlPullParser.next();
            if (eventType == 1) {
                break;
            }
        }
        String name = xmlPullParser.getName();
        if (name.equals("menu")) {
            eventType = xmlPullParser.next();
            Object obj3 = null;
            while (obj3 == null) {
                Object obj4;
                Object obj5;
                switch (eventType) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        throw new RuntimeException("Unexpected end of document");
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        if (obj == null) {
                            name = xmlPullParser.getName();
                            if (name.equals(WPA.CHAT_TYPE_GROUP)) {
                                TypedArray obtainStyledAttributes = bVar.z.e.obtainStyledAttributes(attributeSet, R.styleable.MenuGroup);
                                bVar.b = obtainStyledAttributes.getResourceId(R.styleable.MenuGroup_android_id, 0);
                                bVar.c = obtainStyledAttributes.getInt(R.styleable.MenuGroup_android_menuCategory, 0);
                                bVar.d = obtainStyledAttributes.getInt(R.styleable.MenuGroup_android_orderInCategory, 0);
                                bVar.e = obtainStyledAttributes.getInt(R.styleable.MenuGroup_android_checkableBehavior, 0);
                                bVar.f = obtainStyledAttributes.getBoolean(R.styleable.MenuGroup_android_visible, true);
                                bVar.g = obtainStyledAttributes.getBoolean(R.styleable.MenuGroup_android_enabled, true);
                                obtainStyledAttributes.recycle();
                                obj4 = obj3;
                                obj3 = obj2;
                                obj2 = obj;
                            } else if (name.equals("item")) {
                                TypedArray obtainStyledAttributes2 = bVar.z.e.obtainStyledAttributes(attributeSet, R.styleable.MenuItem);
                                bVar.i = obtainStyledAttributes2.getResourceId(R.styleable.MenuItem_android_id, 0);
                                bVar.j = (obtainStyledAttributes2.getInt(R.styleable.MenuItem_android_menuCategory, bVar.c) & -65536) | (obtainStyledAttributes2.getInt(R.styleable.MenuItem_android_orderInCategory, bVar.d) & 65535);
                                bVar.k = obtainStyledAttributes2.getText(R.styleable.MenuItem_android_title);
                                bVar.l = obtainStyledAttributes2.getText(R.styleable.MenuItem_android_titleCondensed);
                                bVar.m = obtainStyledAttributes2.getResourceId(R.styleable.MenuItem_android_icon, 0);
                                bVar.n = b.a(obtainStyledAttributes2.getString(R.styleable.MenuItem_android_alphabeticShortcut));
                                bVar.o = b.a(obtainStyledAttributes2.getString(R.styleable.MenuItem_android_numericShortcut));
                                if (obtainStyledAttributes2.hasValue(R.styleable.MenuItem_android_checkable)) {
                                    bVar.p = obtainStyledAttributes2.getBoolean(R.styleable.MenuItem_android_checkable, false) ? 1 : 0;
                                } else {
                                    bVar.p = bVar.e;
                                }
                                bVar.q = obtainStyledAttributes2.getBoolean(R.styleable.MenuItem_android_checked, false);
                                bVar.r = obtainStyledAttributes2.getBoolean(R.styleable.MenuItem_android_visible, bVar.f);
                                bVar.s = obtainStyledAttributes2.getBoolean(R.styleable.MenuItem_android_enabled, bVar.g);
                                bVar.t = obtainStyledAttributes2.getInt(R.styleable.MenuItem_showAsAction, -1);
                                bVar.x = obtainStyledAttributes2.getString(R.styleable.MenuItem_android_onClick);
                                bVar.u = obtainStyledAttributes2.getResourceId(R.styleable.MenuItem_actionLayout, 0);
                                bVar.v = obtainStyledAttributes2.getString(R.styleable.MenuItem_actionViewClass);
                                bVar.w = obtainStyledAttributes2.getString(R.styleable.MenuItem_actionProviderClass);
                                if ((bVar.w != null ? 1 : null) != null && bVar.u == 0 && bVar.v == null) {
                                    bVar.y = (ActionProvider) bVar.a(bVar.w, b, bVar.z.d);
                                } else {
                                    bVar.y = null;
                                }
                                obtainStyledAttributes2.recycle();
                                bVar.h = false;
                                obj4 = obj3;
                                obj3 = obj2;
                                obj2 = obj;
                            } else if (name.equals("menu")) {
                                a(xmlPullParser, attributeSet, bVar.b());
                                obj4 = obj3;
                                obj3 = obj2;
                                obj2 = obj;
                            } else {
                                obj2 = 1;
                                obj5 = obj3;
                                String str = name;
                                obj4 = obj5;
                            }
                        }
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        name = xmlPullParser.getName();
                        if (obj != null && name.equals(obj2)) {
                            obj2 = null;
                            obj5 = obj3;
                            obj3 = null;
                            obj4 = obj5;
                        } else if (name.equals(WPA.CHAT_TYPE_GROUP)) {
                            bVar.a();
                            obj4 = obj3;
                            obj3 = obj2;
                            obj2 = obj;
                        } else if (name.equals("item")) {
                            if (!bVar.h) {
                                if (bVar.y == null || !bVar.y.hasSubMenu()) {
                                    bVar.h = true;
                                    bVar.a(bVar.a.add(bVar.b, bVar.i, bVar.j, bVar.k));
                                    obj4 = obj3;
                                    obj3 = obj2;
                                    obj2 = obj;
                                } else {
                                    bVar.b();
                                    obj4 = obj3;
                                    obj3 = obj2;
                                    obj2 = obj;
                                }
                            }
                            obj4 = obj3;
                            obj3 = obj2;
                            obj2 = obj;
                        } else {
                            if (name.equals("menu")) {
                                obj4 = 1;
                                obj3 = obj2;
                                obj2 = obj;
                            }
                            obj4 = obj3;
                            obj3 = obj2;
                            obj2 = obj;
                        }
                        break;
                    default:
                        obj4 = obj3;
                        obj3 = obj2;
                        obj2 = obj;
                        break;
                }
                obj5 = obj4;
                eventType = xmlPullParser.next();
                obj = obj2;
                obj2 = obj3;
                obj3 = obj5;
            }
            return;
        }
        throw new RuntimeException(new StringBuilder("Expecting menu, got ").append(name).toString());
    }

    static /* synthetic */ Object b(g gVar) {
        if (gVar.f == null) {
            Object obj = gVar.e;
            while (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) {
                obj = ((ContextWrapper) obj).getBaseContext();
            }
            gVar.f = obj;
        }
        return gVar.f;
    }
}
