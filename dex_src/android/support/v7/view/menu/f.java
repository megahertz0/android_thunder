package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.appcompat.R;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyCharacterMap.KeyData;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// compiled from: MenuBuilder.java
public class f implements SupportMenu {
    private static final int[] m;
    public final Context a;
    public a b;
    ArrayList<h> c;
    public ArrayList<h> d;
    public int e;
    CharSequence f;
    Drawable g;
    View h;
    boolean i;
    public CopyOnWriteArrayList<WeakReference<m>> j;
    h k;
    public boolean l;
    private final Resources n;
    private boolean o;
    private boolean p;
    private ArrayList<h> q;
    private boolean r;
    private ArrayList<h> s;
    private boolean t;
    private ContextMenuInfo u;
    private boolean v;
    private boolean w;
    private boolean x;
    private ArrayList<h> y;

    // compiled from: MenuBuilder.java
    public static interface a {
        void a(f fVar);

        boolean a(f fVar, MenuItem menuItem);
    }

    // compiled from: MenuBuilder.java
    public static interface b {
        boolean a(h hVar);
    }

    static {
        m = new int[]{1, 4, 5, 3, 2, 0};
    }

    public f(Context context) {
        boolean z = true;
        this.e = 0;
        this.v = false;
        this.w = false;
        this.i = false;
        this.x = false;
        this.y = new ArrayList();
        this.j = new CopyOnWriteArrayList();
        this.a = context;
        this.n = context.getResources();
        this.c = new ArrayList();
        this.q = new ArrayList();
        this.r = true;
        this.d = new ArrayList();
        this.s = new ArrayList();
        this.t = true;
        if (this.n.getConfiguration().keyboard == 1 || !this.n.getBoolean(R.bool.abc_config_showMenuShortcutsWhenKeyboardPresent)) {
            z = false;
        }
        this.p = z;
    }

    public final void a(m mVar) {
        a(mVar, this.a);
    }

    public final void a(m mVar, Context context) {
        this.j.add(new WeakReference(mVar));
        mVar.a(context, this);
        this.t = true;
    }

    public final void b(m mVar) {
        Iterator it = this.j.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            m mVar2 = (m) weakReference.get();
            if (mVar2 == null || mVar2 == mVar) {
                this.j.remove(weakReference);
            }
        }
    }

    public final void a(Bundle bundle) {
        if (!this.j.isEmpty()) {
            SparseArray sparseArray = new SparseArray();
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                m mVar = (m) weakReference.get();
                if (mVar == null) {
                    this.j.remove(weakReference);
                } else {
                    int b = mVar.b();
                    if (b > 0) {
                        Parcelable c = mVar.c();
                        if (c != null) {
                            sparseArray.put(b, c);
                        }
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
        }
    }

    public final void b(Bundle bundle) {
        int size = size();
        int i = 0;
        SparseArray sparseArray = null;
        while (i < size) {
            MenuItem item = getItem(i);
            View actionView = MenuItemCompat.getActionView(item);
            if (!(actionView == null || actionView.getId() == -1)) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                actionView.saveHierarchyState(sparseArray);
                if (MenuItemCompat.isActionViewExpanded(item)) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            SparseArray sparseArray2 = sparseArray;
            if (item.hasSubMenu()) {
                ((q) item.getSubMenu()).b(bundle);
            }
            i++;
            sparseArray = sparseArray2;
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(a(), sparseArray);
        }
    }

    public final void c(Bundle bundle) {
        if (bundle != null) {
            MenuItem item;
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(a());
            int size = size();
            for (int i = 0; i < size; i++) {
                item = getItem(i);
                View actionView = MenuItemCompat.getActionView(item);
                if (!(actionView == null || actionView.getId() == -1)) {
                    actionView.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((q) item.getSubMenu()).c(bundle);
                }
            }
            int i2 = bundle.getInt("android:menu:expandedactionview");
            if (i2 > 0) {
                item = findItem(i2);
                if (item != null) {
                    MenuItemCompat.expandActionView(item);
                }
            }
        }
    }

    protected String a() {
        return "android:menu:actionviewstates";
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    public MenuItem add(int i) {
        return a(0, 0, 0, this.n.getString(i));
    }

    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return a(i, i2, i3, charSequence);
    }

    public MenuItem add(int i, int i2, int i3, int i4) {
        return a(i, i2, i3, this.n.getString(i4));
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.n.getString(i));
    }

    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        h hVar = (h) a(i, i2, i3, charSequence);
        q qVar = new q(this.a, this, hVar);
        hVar.a(qVar);
        return qVar;
    }

    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.n.getString(i4));
    }

    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        PackageManager packageManager = this.a.getPackageManager();
        List queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i4 & 1) == 0) {
            removeGroup(i);
        }
        for (int i5 = 0; i5 < size; i5++) {
            Intent intent2;
            ResolveInfo resolveInfo = (ResolveInfo) queryIntentActivityOptions.get(i5);
            if (resolveInfo.specificIndex < 0) {
                intent2 = intent;
            } else {
                intent2 = intentArr[resolveInfo.specificIndex];
            }
            Intent intent3 = new Intent(intent2);
            intent3.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            MenuItem intent4 = add(i, i2, i3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent3);
            if (menuItemArr != null && resolveInfo.specificIndex >= 0) {
                menuItemArr[resolveInfo.specificIndex] = intent4;
            }
        }
        return size;
    }

    public void removeGroup(int i) {
        int i2;
        int size = size();
        for (i2 = 0; i2 < size; i2++) {
            if (((h) this.c.get(i2)).getGroupId() == i) {
                size = i2;
                break;
            }
        }
        size = -1;
        if (size >= 0) {
            int size2 = this.c.size() - size;
            int i3 = 0;
            while (true) {
                i2 = i3 + 1;
                if (i3 >= size2 || ((h) this.c.get(size)).getGroupId() != i) {
                    break;
                }
                a(size, false);
                i3 = i2;
            }
            a(true);
        }
    }

    private void a(int i, boolean z) {
        if (i >= 0 && i < this.c.size()) {
            this.c.remove(i);
            if (z) {
                a(true);
            }
        }
    }

    public void clear() {
        if (this.k != null) {
            b(this.k);
        }
        this.c.clear();
        a(true);
    }

    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            h hVar = (h) this.c.get(i2);
            if (hVar.getGroupId() == i) {
                hVar.a(z2);
                hVar.setCheckable(z);
            }
        }
    }

    public void setGroupVisible(int i, boolean z) {
        int size = this.c.size();
        int i2 = 0;
        Object obj = null;
        while (i2 < size) {
            boolean z2;
            h hVar = (h) this.c.get(i2);
            if (hVar.getGroupId() == i && hVar.b(z)) {
                z2 = true;
            } else {
                z2 = r2;
            }
            i2++;
            boolean z3 = z2;
        }
        if (z3) {
            a(true);
        }
    }

    public void setGroupEnabled(int i, boolean z) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            h hVar = (h) this.c.get(i2);
            if (hVar.getGroupId() == i) {
                hVar.setEnabled(z);
            }
        }
    }

    public boolean hasVisibleItems() {
        if (this.l) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (((h) this.c.get(i)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public MenuItem findItem(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            h hVar = (h) this.c.get(i2);
            if (hVar.getItemId() == i) {
                return hVar;
            }
            if (hVar.hasSubMenu()) {
                MenuItem findItem = hVar.getSubMenu().findItem(i);
                if (findItem != null) {
                    return findItem;
                }
            }
        }
        return null;
    }

    public int size() {
        return this.c.size();
    }

    public MenuItem getItem(int i) {
        return (MenuItem) this.c.get(i);
    }

    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return a(i, keyEvent) != null;
    }

    public void setQwertyMode(boolean z) {
        this.o = z;
        a(false);
    }

    boolean b() {
        return this.o;
    }

    public boolean c() {
        return this.p;
    }

    boolean a(f fVar, MenuItem menuItem) {
        return this.b != null && this.b.a(fVar, menuItem);
    }

    private static int a(ArrayList<h> arrayList, int i) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((h) arrayList.get(size)).a <= i) {
                return size + 1;
            }
        }
        return 0;
    }

    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        MenuItem a = a(i, keyEvent);
        boolean z = false;
        if (a != null) {
            z = a(a, null, i2);
        }
        if ((i2 & 2) != 0) {
            b(true);
        }
        return z;
    }

    private void a(List<h> list, int i, KeyEvent keyEvent) {
        boolean b = b();
        int metaState = keyEvent.getMetaState();
        KeyData keyData = new KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.c.size();
            for (int i2 = 0; i2 < size; i2++) {
                h hVar = (h) this.c.get(i2);
                if (hVar.hasSubMenu()) {
                    ((f) hVar.getSubMenu()).a((List) list, i, keyEvent);
                }
                char alphabeticShortcut = b ? hVar.getAlphabeticShortcut() : hVar.getNumericShortcut();
                if ((metaState & 5) == 0 && alphabeticShortcut != '\u0000') {
                    if ((alphabeticShortcut == keyData.meta[0] || alphabeticShortcut == keyData.meta[2] || (b && alphabeticShortcut == '\b' && i == 67)) && hVar.isEnabled()) {
                        list.add(hVar);
                    }
                }
            }
        }
    }

    private h a(int i, KeyEvent keyEvent) {
        List list = this.y;
        list.clear();
        a(list, i, keyEvent);
        if (list.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyData keyData = new KeyData();
        keyEvent.getKeyData(keyData);
        int size = list.size();
        if (size == 1) {
            return (h) list.get(0);
        }
        boolean b = b();
        for (int i2 = 0; i2 < size; i2++) {
            h hVar = (h) list.get(i2);
            char alphabeticShortcut = b ? hVar.getAlphabeticShortcut() : hVar.getNumericShortcut();
            if (alphabeticShortcut == keyData.meta[0] && (metaState & 2) == 0) {
                return hVar;
            }
            if (alphabeticShortcut == keyData.meta[2] && (metaState & 2) != 0) {
                return hVar;
            }
            if (b && alphabeticShortcut == '\b' && i == 67) {
                return hVar;
            }
        }
        return null;
    }

    public boolean performIdentifierAction(int i, int i2) {
        return a(findItem(i), null, i2);
    }

    public final boolean a(MenuItem menuItem, m mVar, int i) {
        int i2 = 0;
        h hVar = (h) menuItem;
        if (hVar == null || !hVar.isEnabled()) {
            return false;
        }
        boolean a = hVar.a();
        ActionProvider supportActionProvider = hVar.getSupportActionProvider();
        if (supportActionProvider == null || !supportActionProvider.hasSubMenu()) {
            int i3 = 0;
        } else {
            boolean z = true;
        }
        boolean expandActionView;
        if (hVar.h()) {
            expandActionView = hVar.expandActionView() | a;
            if (!expandActionView) {
                return expandActionView;
            }
            b(true);
            return expandActionView;
        } else if (hVar.hasSubMenu() || z) {
            b(false);
            if (!hVar.hasSubMenu()) {
                hVar.a(new q(this.a, this, hVar));
            }
            q qVar = (q) hVar.getSubMenu();
            if (z) {
                supportActionProvider.onPrepareSubMenu(qVar);
            }
            if (!this.j.isEmpty()) {
                boolean a2;
                if (mVar != null) {
                    a2 = mVar.a(qVar);
                }
                Iterator it = this.j.iterator();
                boolean z2 = a2;
                while (it.hasNext()) {
                    WeakReference weakReference = (WeakReference) it.next();
                    m mVar2 = (m) weakReference.get();
                    if (mVar2 == null) {
                        this.j.remove(weakReference);
                    } else {
                        if (z2) {
                            a2 = z2;
                        } else {
                            a2 = mVar2.a(qVar);
                        }
                        z2 = a2;
                    }
                }
                a2 = z2;
            }
            expandActionView = a | i2;
            if (expandActionView) {
                return expandActionView;
            }
            b(true);
            return expandActionView;
        } else {
            if ((i & 1) == 0) {
                b(true);
            }
            return a;
        }
    }

    public final void b(boolean z) {
        if (!this.x) {
            this.x = true;
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                m mVar = (m) weakReference.get();
                if (mVar == null) {
                    this.j.remove(weakReference);
                } else {
                    mVar.a(this, z);
                }
            }
            this.x = false;
        }
    }

    public void close() {
        b(true);
    }

    public void a(boolean z) {
        if (this.v) {
            this.w = true;
            return;
        }
        if (z) {
            this.r = true;
            this.t = true;
        }
        if (!this.j.isEmpty()) {
            d();
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                m mVar = (m) weakReference.get();
                if (mVar == null) {
                    this.j.remove(weakReference);
                } else {
                    mVar.a(z);
                }
            }
            e();
        }
    }

    public final void d() {
        if (!this.v) {
            this.v = true;
            this.w = false;
        }
    }

    public final void e() {
        this.v = false;
        if (this.w) {
            this.w = false;
            a(true);
        }
    }

    final void f() {
        this.r = true;
        a(true);
    }

    final void g() {
        this.t = true;
        a(true);
    }

    public final ArrayList<h> h() {
        if (!this.r) {
            return this.q;
        }
        this.q.clear();
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            h hVar = (h) this.c.get(i);
            if (hVar.isVisible()) {
                this.q.add(hVar);
            }
        }
        this.r = false;
        this.t = true;
        return this.q;
    }

    public final void i() {
        ArrayList h = h();
        if (this.t) {
            Iterator it = this.j.iterator();
            int i = 0;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                m mVar = (m) weakReference.get();
                if (mVar == null) {
                    this.j.remove(weakReference);
                } else {
                    i = mVar.a() | i;
                }
            }
            if (i != 0) {
                this.d.clear();
                this.s.clear();
                i = h.size();
                for (int i2 = 0; i2 < i; i2++) {
                    h hVar = (h) h.get(i2);
                    if (hVar.e()) {
                        this.d.add(hVar);
                    } else {
                        this.s.add(hVar);
                    }
                }
            } else {
                this.d.clear();
                this.s.clear();
                this.s.addAll(h());
            }
            this.t = false;
        }
    }

    public final ArrayList<h> j() {
        i();
        return this.s;
    }

    public void clearHeader() {
        this.g = null;
        this.f = null;
        this.h = null;
        a(false);
    }

    final void a(CharSequence charSequence, Drawable drawable, View view) {
        if (view != null) {
            this.h = view;
            this.f = null;
            this.g = null;
        } else {
            if (charSequence != null) {
                this.f = charSequence;
            }
            if (drawable != null) {
                this.g = drawable;
            }
            this.h = null;
        }
        a(false);
    }

    protected final f a(CharSequence charSequence) {
        a(charSequence, null, null);
        return this;
    }

    protected final f a(Drawable drawable) {
        a(null, drawable, null);
        return this;
    }

    public f k() {
        return this;
    }

    public boolean a(h hVar) {
        boolean z = false;
        if (!this.j.isEmpty()) {
            d();
            Iterator it = this.j.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                m mVar = (m) weakReference.get();
                if (mVar == null) {
                    this.j.remove(weakReference);
                } else {
                    z = mVar.a(hVar);
                    if (z) {
                        break;
                    }
                    z2 = z;
                }
            }
            z = z2;
            e();
            if (z) {
                this.k = hVar;
            }
        }
        return z;
    }

    public boolean b(h hVar) {
        boolean z = false;
        if (!this.j.isEmpty() && this.k == hVar) {
            d();
            Iterator it = this.j.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                m mVar = (m) weakReference.get();
                if (mVar == null) {
                    this.j.remove(weakReference);
                } else {
                    z = mVar.b(hVar);
                    if (z) {
                        break;
                    }
                    z2 = z;
                }
            }
            z = z2;
            e();
            if (z) {
                this.k = null;
            }
        }
        return z;
    }

    public final MenuItem a(int i, int i2, int i3, CharSequence charSequence) {
        int i4 = (-65536 & i3) >> 16;
        if (i4 < 0 || i4 >= m.length) {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        int i5 = (m[i4] << 16) | (65535 & i3);
        MenuItem hVar = new h(this, i, i2, i3, i5, charSequence, this.e);
        if (this.u != null) {
            hVar.d = this.u;
        }
        this.c.add(a(this.c, i5), hVar);
        a(true);
        return hVar;
    }

    public void removeItem(int i) {
        int i2;
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            if (((h) this.c.get(i3)).getItemId() == i) {
                i2 = i3;
                break;
            }
        }
        i2 = -1;
        a(i2, true);
    }
}
