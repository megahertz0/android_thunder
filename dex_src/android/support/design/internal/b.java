package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.R;
import android.support.v7.view.menu.m;
import android.support.v7.view.menu.q;
import android.support.v7.widget.RecyclerView.t;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Iterator;
import org.android.spdy.SpdyAgent;

// compiled from: NavigationMenuPresenter.java
public final class b implements m {
    public NavigationMenuView a;
    public LinearLayout b;
    android.support.v7.view.menu.f c;
    public int d;
    public b e;
    public LayoutInflater f;
    int g;
    boolean h;
    public ColorStateList i;
    public ColorStateList j;
    public Drawable k;
    public int l;
    int m;
    final OnClickListener n;
    private android.support.v7.view.menu.m.a o;

    // compiled from: NavigationMenuPresenter.java
    private static abstract class j extends t {
        public j(View view) {
            super(view);
        }
    }

    // compiled from: NavigationMenuPresenter.java
    private static class a extends j {
        public a(View view) {
            super(view);
        }
    }

    // compiled from: NavigationMenuPresenter.java
    private class b extends android.support.v7.widget.RecyclerView.a<j> {
        final ArrayList<d> a;
        boolean b;
        private android.support.v7.view.menu.h d;
        private ColorDrawable e;

        public final /* synthetic */ void onBindViewHolder(t tVar, int i) {
            j jVar = (j) tVar;
            switch (getItemViewType(i)) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) jVar.itemView;
                    navigationMenuItemView.setIconTintList(b.this.j);
                    if (b.this.h) {
                        b.this.setTextAppearance(navigationMenuItemView.getContext(), b.this.g);
                    }
                    if (b.this.i != null) {
                        navigationMenuItemView.setTextColor(b.this.i);
                    }
                    navigationMenuItemView.setBackgroundDrawable(b.this.k != null ? b.this.k.getConstantState().newDrawable() : null);
                    navigationMenuItemView.a(((f) this.a.get(i)).a);
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    ((TextView) jVar.itemView).setText(((f) this.a.get(i)).a.getTitle());
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    e eVar = (e) this.a.get(i);
                    jVar.itemView.setPadding(0, eVar.a, 0, eVar.b);
                default:
                    break;
            }
        }

        public final /* synthetic */ void onViewRecycled(t tVar) {
            j jVar = (j) tVar;
            if (jVar instanceof g) {
                NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) jVar.itemView;
                if (navigationMenuItemView.d != null) {
                    navigationMenuItemView.d.removeAllViews();
                }
                b.this.setCompoundDrawables(null, null, null, null);
            }
        }

        public b() {
            this.a = new ArrayList();
            a();
        }

        public final long getItemId(int i) {
            return (long) i;
        }

        public final int getItemCount() {
            return this.a.size();
        }

        public final int getItemViewType(int i) {
            d dVar = (d) this.a.get(i);
            if (dVar instanceof e) {
                return XZBDevice.DOWNLOAD_LIST_RECYCLE;
            }
            if (dVar instanceof c) {
                return XZBDevice.DOWNLOAD_LIST_FAILED;
            }
            if (dVar instanceof f) {
                return ((f) dVar).a.hasSubMenu() ? 1 : 0;
            } else {
                throw new RuntimeException("Unknown item type.");
            }
        }

        final void a() {
            if (!this.b) {
                this.b = true;
                this.a.clear();
                this.a.add(new c());
                Object obj = -1;
                int i = 0;
                Object obj2 = null;
                int size = b.this.h().size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj3;
                    int i3;
                    int i4;
                    android.support.v7.view.menu.h hVar = (android.support.v7.view.menu.h) b.this.h().get(i2);
                    if (hVar.isChecked()) {
                        a(hVar);
                    }
                    if (hVar.isCheckable()) {
                        hVar.a(false);
                    }
                    int i5;
                    if (hVar.hasSubMenu()) {
                        SubMenu subMenu = hVar.getSubMenu();
                        if (subMenu.hasVisibleItems()) {
                            if (i2 != 0) {
                                this.a.add(new e(b.this.m, 0));
                            }
                            this.a.add(new f((byte) 0));
                            Object obj4 = null;
                            int size2 = this.a.size();
                            int size3 = subMenu.size();
                            for (i5 = 0; i5 < size3; i5++) {
                                android.support.v7.view.menu.h hVar2 = (android.support.v7.view.menu.h) subMenu.getItem(i5);
                                if (hVar2.isVisible()) {
                                    if (obj4 == null && hVar2.getIcon() != null) {
                                        obj4 = 1;
                                    }
                                    if (hVar2.isCheckable()) {
                                        hVar2.a(false);
                                    }
                                    if (hVar.isChecked()) {
                                        a(hVar);
                                    }
                                    this.a.add(new f((byte) 0));
                                }
                            }
                            if (obj4 != null) {
                                a(size2, this.a.size());
                            }
                        }
                        obj3 = obj2;
                        i3 = i;
                        i4 = r5;
                    } else {
                        Object obj5;
                        i5 = hVar.getGroupId();
                        if (i5 != r5) {
                            i = this.a.size();
                            obj2 = hVar.getIcon() != null ? 1 : null;
                            if (i2 != 0) {
                                i++;
                                this.a.add(new e(b.this.m, b.this.m));
                                obj5 = obj2;
                                i4 = i;
                            }
                            obj5 = obj2;
                            i4 = i;
                        } else {
                            if (obj2 == null && hVar.getIcon() != null) {
                                obj2 = 1;
                                a(i, this.a.size());
                            }
                            obj5 = obj2;
                            i4 = i;
                        }
                        if (obj5 != null && hVar.getIcon() == null) {
                            hVar.setIcon(17170445);
                        }
                        this.a.add(new f((byte) 0));
                        obj3 = obj5;
                        i3 = i4;
                        i4 = i5;
                    }
                    i2++;
                    i = i3;
                    int i6 = i4;
                    obj2 = obj3;
                }
                this.b = false;
            }
        }

        private void a(int i, int i2) {
            while (i < i2) {
                MenuItem menuItem = ((f) this.a.get(i)).a;
                if (menuItem.getIcon() == null) {
                    if (this.e == null) {
                        this.e = new ColorDrawable(0);
                    }
                    menuItem.setIcon(this.e);
                }
                i++;
            }
        }

        public final void a(android.support.v7.view.menu.h hVar) {
            if (this.d != hVar && hVar.isCheckable()) {
                if (this.d != null) {
                    this.d.setChecked(false);
                }
                this.d = hVar;
                hVar.setChecked(true);
            }
        }

        public final Bundle b() {
            Bundle bundle = new Bundle();
            if (this.d != null) {
                bundle.putInt("android:menu:checked", this.d.getItemId());
            }
            SparseArray sparseArray = new SparseArray();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                d dVar = (d) it.next();
                if (dVar instanceof f) {
                    android.support.v7.view.menu.h hVar = ((f) dVar).a;
                    View actionView = hVar != null ? hVar.getActionView() : null;
                    if (actionView != null) {
                        SparseArray parcelableSparseArray = new ParcelableSparseArray();
                        actionView.saveHierarchyState(parcelableSparseArray);
                        sparseArray.put(hVar.getItemId(), parcelableSparseArray);
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:action_views", sparseArray);
            return bundle;
        }

        public final /* synthetic */ t onCreateViewHolder(ViewGroup viewGroup, int i) {
            switch (i) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    return new g(b.this.f, viewGroup, b.this.n);
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    return new i(b.this.f, viewGroup);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    return new h(b.this.f, viewGroup);
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    return new a(b.this.b);
                default:
                    return null;
            }
        }
    }

    // compiled from: NavigationMenuPresenter.java
    private static interface d {
    }

    // compiled from: NavigationMenuPresenter.java
    private static class c implements d {
        private c() {
        }
    }

    // compiled from: NavigationMenuPresenter.java
    private static class e implements d {
        final int a;
        final int b;

        public e(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    // compiled from: NavigationMenuPresenter.java
    private static class f implements d {
        final android.support.v7.view.menu.h a;

        private f(android.support.v7.view.menu.h hVar) {
            this.a = hVar;
        }
    }

    // compiled from: NavigationMenuPresenter.java
    private static class g extends j {
        public g(LayoutInflater layoutInflater, ViewGroup viewGroup, OnClickListener onClickListener) {
            super(layoutInflater.inflate(R.layout.design_navigation_item, viewGroup, false));
            this.itemView.setOnClickListener(onClickListener);
        }
    }

    // compiled from: NavigationMenuPresenter.java
    private static class h extends j {
        public h(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_separator, viewGroup, false));
        }
    }

    // compiled from: NavigationMenuPresenter.java
    private static class i extends j {
        public i(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_subheader, viewGroup, false));
        }
    }

    public b() {
        this.n = new c(this);
    }

    public final void a(Context context, android.support.v7.view.menu.f fVar) {
        this.f = LayoutInflater.from(context);
        this.c = fVar;
        this.m = context.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
    }

    public final void a(boolean z) {
        if (this.e != null) {
            b bVar = this.e;
            bVar.a();
            bVar.notifyDataSetChanged();
        }
    }

    public final boolean a(q qVar) {
        return false;
    }

    public final void a(android.support.v7.view.menu.f fVar, boolean z) {
        if (this.o != null) {
            this.o.a(fVar, z);
        }
    }

    public final boolean a() {
        return false;
    }

    public final boolean a(android.support.v7.view.menu.h hVar) {
        return false;
    }

    public final boolean b(android.support.v7.view.menu.h hVar) {
        return false;
    }

    public final int b() {
        return this.d;
    }

    public final Parcelable c() {
        Parcelable bundle = new Bundle();
        if (this.a != null) {
            SparseArray sparseArray = new SparseArray();
            this.a.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        }
        if (this.e != null) {
            bundle.putBundle("android:menu:adapter", this.e.b());
        }
        return bundle;
    }

    public final void a(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.a.restoreHierarchyState(sparseParcelableArray);
        }
        Bundle bundle2 = bundle.getBundle("android:menu:adapter");
        if (bundle2 != null) {
            d dVar;
            b bVar = this.e;
            int i = bundle2.getInt("android:menu:checked", 0);
            if (i != 0) {
                bVar.b = true;
                Iterator it = bVar.a.iterator();
                while (it.hasNext()) {
                    dVar = (d) it.next();
                    if (dVar instanceof f) {
                        android.support.v7.view.menu.h hVar = ((f) dVar).a;
                        if (hVar != null && hVar.getItemId() == i) {
                            bVar.a(hVar);
                            break;
                        }
                    }
                }
                bVar.b = false;
                bVar.a();
            }
            SparseArray sparseParcelableArray2 = bundle2.getSparseParcelableArray("android:menu:action_views");
            Iterator it2 = bVar.a.iterator();
            while (it2.hasNext()) {
                dVar = (d) it2.next();
                if (dVar instanceof f) {
                    View actionView;
                    android.support.v7.view.menu.h hVar2 = ((f) dVar).a;
                    if (hVar2 != null) {
                        actionView = hVar2.getActionView();
                    } else {
                        actionView = null;
                    }
                    if (actionView != null) {
                        actionView.restoreHierarchyState((SparseArray) sparseParcelableArray2.get(hVar2.getItemId()));
                    }
                }
            }
        }
    }

    public final void a(ColorStateList colorStateList) {
        this.j = colorStateList;
        a(false);
    }

    public final void b(ColorStateList colorStateList) {
        this.i = colorStateList;
        a(false);
    }

    public final void a(int i) {
        this.g = i;
        this.h = true;
        a(false);
    }

    public final void a(Drawable drawable) {
        this.k = drawable;
        a(false);
    }

    public final void b(boolean z) {
        if (this.e != null) {
            this.e.b = z;
        }
    }
}
