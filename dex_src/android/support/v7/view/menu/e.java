package android.support.v7.view.menu;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.g;
import android.support.v7.app.h;
import android.support.v7.app.i;
import android.support.v7.app.j;
import android.support.v7.app.k;
import android.support.v7.appcompat.R;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.tencent.open.yyb.AppbarJsBridge;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;

// compiled from: ListMenuPresenter.java
public final class e implements m, OnItemClickListener {
    Context a;
    public LayoutInflater b;
    f c;
    public ExpandedMenuView d;
    int e;
    int f;
    public android.support.v7.view.menu.m.a g;
    public a h;
    private int i;
    private int j;

    // compiled from: ListMenuPresenter.java
    private class a extends BaseAdapter {
        private int b;

        public final /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a() {
            this.b = -1;
            a();
        }

        public final int getCount() {
            int size = e.this.c.j().size() - e.this.i;
            return this.b < 0 ? size : size - 1;
        }

        public final h a(int i) {
            ArrayList j = e.this.c.j();
            int a = e.this.i + i;
            if (this.b >= 0 && a >= this.b) {
                a++;
            }
            return (h) j.get(a);
        }

        public final long getItemId(int i) {
            return (long) i;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            View inflate;
            if (view == null) {
                inflate = e.this.b.inflate(e.this.f, viewGroup, false);
            } else {
                inflate = view;
            }
            ((android.support.v7.view.menu.n.a) inflate).a(a(i));
            return inflate;
        }

        private void a() {
            h hVar = e.this.c.k;
            if (hVar != null) {
                ArrayList j = e.this.c.j();
                int size = j.size();
                for (int i = 0; i < size; i++) {
                    if (((h) j.get(i)) == hVar) {
                        this.b = i;
                        return;
                    }
                }
            }
            this.b = -1;
        }

        public final void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    public e(Context context, int i) {
        this(i);
        this.a = context;
        this.b = LayoutInflater.from(this.a);
    }

    private e(int i) {
        this.f = i;
        this.e = 0;
    }

    public final void a(Context context, f fVar) {
        if (this.e != 0) {
            this.a = new ContextThemeWrapper(context, this.e);
            this.b = LayoutInflater.from(this.a);
        } else if (this.a != null) {
            this.a = context;
            if (this.b == null) {
                this.b = LayoutInflater.from(this.a);
            }
        }
        this.c = fVar;
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    public final ListAdapter d() {
        if (this.h == null) {
            this.h = new a();
        }
        return this.h;
    }

    public final void a(boolean z) {
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
    }

    public final boolean a(q qVar) {
        if (!qVar.hasVisibleItems()) {
            return false;
        }
        g gVar = new g(qVar);
        f fVar = gVar.a;
        android.support.v7.app.k.a aVar = new android.support.v7.app.k.a(fVar.a);
        gVar.c = new e(aVar.a.a, R.layout.abc_list_menu_item_layout);
        gVar.c.g = gVar;
        gVar.a.a(gVar.c);
        aVar.a.t = gVar.c.d();
        aVar.a.u = gVar;
        View view = fVar.h;
        if (view != null) {
            aVar.a.g = view;
        } else {
            aVar.a.d = fVar.g;
            aVar.a.f = fVar.f;
        }
        aVar.a.r = gVar;
        k kVar = new k(aVar.a.a, aVar.b);
        android.support.v7.app.a.a aVar2 = aVar.a;
        android.support.v7.app.a a = kVar.a;
        if (aVar2.g != null) {
            a.C = aVar2.g;
        } else {
            if (aVar2.f != null) {
                a.a(aVar2.f);
            }
            if (aVar2.d != null) {
                Drawable drawable = aVar2.d;
                a.y = drawable;
                a.x = 0;
                if (a.z != null) {
                    if (drawable != null) {
                        a.z.setVisibility(0);
                        a.z.setImageDrawable(drawable);
                    } else {
                        a.z.setVisibility(XZBDevice.Wait);
                    }
                }
            }
            if (aVar2.c != 0) {
                a.a(aVar2.c);
            }
            if (aVar2.e != 0) {
                int i = aVar2.e;
                TypedValue typedValue = new TypedValue();
                a.a.getTheme().resolveAttribute(i, typedValue, true);
                a.a(typedValue.resourceId);
            }
        }
        if (aVar2.h != null) {
            CharSequence charSequence = aVar2.h;
            a.e = charSequence;
            if (a.B != null) {
                a.B.setText(charSequence);
            }
        }
        if (aVar2.i != null) {
            a.a(-1, aVar2.i, aVar2.j, null);
        }
        if (aVar2.k != null) {
            a.a(Tabs.TAB_CREATE_REACH_MAX_COUNT, aVar2.k, aVar2.l, null);
        }
        if (aVar2.m != null) {
            a.a(AppbarJsBridge.Code_Java_Exception, aVar2.m, aVar2.n, null);
        }
        if (!(aVar2.s == null && aVar2.H == null && aVar2.t == null)) {
            ListAdapter listAdapter;
            ListView listView = (ListView) aVar2.b.inflate(a.H, null);
            if (!aVar2.D) {
                int i2;
                if (aVar2.E) {
                    i2 = a.J;
                } else {
                    i2 = a.K;
                }
                if (aVar2.H != null) {
                    Context context = aVar2.a;
                    Cursor cursor = aVar2.H;
                    String[] strArr = new String[1];
                    strArr[0] = aVar2.I;
                    int[] iArr = new int[1];
                    iArr[0] = 16908308;
                    SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context, i2, cursor, strArr, iArr);
                } else if (aVar2.t != null) {
                    listAdapter = aVar2.t;
                } else {
                    listAdapter = new c(aVar2.a, i2, aVar2.s);
                }
            } else if (aVar2.H == null) {
                listAdapter = new g(aVar2, aVar2.a, a.I, aVar2.s, listView);
            } else {
                h hVar = new h(aVar2, aVar2.a, aVar2.H, listView, a);
            }
            a.D = listAdapter;
            a.E = aVar2.F;
            if (aVar2.u != null) {
                listView.setOnItemClickListener(new i(aVar2, a));
            } else if (aVar2.G != null) {
                listView.setOnItemClickListener(new j(aVar2, listView, a));
            }
            if (aVar2.K != null) {
                listView.setOnItemSelectedListener(aVar2.K);
            }
            if (aVar2.E) {
                listView.setChoiceMode(1);
            } else if (aVar2.D) {
                listView.setChoiceMode(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            }
            a.f = listView;
        }
        if (aVar2.w != null) {
            if (aVar2.B) {
                View view2 = aVar2.w;
                int i3 = aVar2.x;
                int i4 = aVar2.y;
                int i5 = aVar2.z;
                int i6 = aVar2.A;
                a.g = view2;
                a.h = 0;
                a.m = true;
                a.i = i3;
                a.j = i4;
                a.k = i5;
                a.l = i6;
            } else {
                a.g = aVar2.w;
                a.h = 0;
                a.m = false;
            }
        } else if (aVar2.v != 0) {
            i = aVar2.v;
            a.g = null;
            a.h = i;
            a.m = false;
        }
        kVar.setCancelable(aVar.a.o);
        if (aVar.a.o) {
            kVar.setCanceledOnTouchOutside(true);
        }
        kVar.setOnCancelListener(aVar.a.p);
        kVar.setOnDismissListener(aVar.a.q);
        if (aVar.a.r != null) {
            kVar.setOnKeyListener(aVar.a.r);
        }
        gVar.b = kVar;
        gVar.b.setOnDismissListener(gVar);
        LayoutParams attributes = gVar.b.getWindow().getAttributes();
        attributes.type = 1003;
        attributes.flags |= 131072;
        gVar.b.show();
        if (this.g != null) {
            this.g.a(qVar);
        }
        return true;
    }

    public final void a(f fVar, boolean z) {
        if (this.g != null) {
            this.g.a(fVar, z);
        }
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.c.a(this.h.a(i), (m) this, 0);
    }

    public final boolean a() {
        return false;
    }

    public final boolean a(h hVar) {
        return false;
    }

    public final boolean b(h hVar) {
        return false;
    }

    public final int b() {
        return this.j;
    }

    public final Parcelable c() {
        if (this.d == null) {
            return null;
        }
        Parcelable bundle = new Bundle();
        SparseArray sparseArray = new SparseArray();
        if (this.d != null) {
            this.d.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        return bundle;
    }

    public final void a(Parcelable parcelable) {
        SparseArray sparseParcelableArray = ((Bundle) parcelable).getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.d.restoreHierarchyState(sparseParcelableArray);
        }
    }
}
