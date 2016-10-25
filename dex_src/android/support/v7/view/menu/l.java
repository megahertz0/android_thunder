package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.v7.appcompat.R;
import android.support.v7.widget.ListPopupWindow;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;
import java.util.ArrayList;

// compiled from: MenuPopupHelper.java
public class l implements m, OnKeyListener, OnGlobalLayoutListener, OnItemClickListener, OnDismissListener {
    static final int a;
    public View b;
    public ListPopupWindow c;
    public android.support.v7.view.menu.m.a d;
    public boolean e;
    public int f;
    private final Context g;
    private final LayoutInflater h;
    private final f i;
    private final a j;
    private final boolean k;
    private final int l;
    private final int m;
    private final int n;
    private ViewTreeObserver o;
    private ViewGroup p;
    private boolean q;
    private int r;

    // compiled from: MenuPopupHelper.java
    private class a extends BaseAdapter {
        private f b;
        private int c;

        public final /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(f fVar) {
            this.c = -1;
            this.b = fVar;
            a();
        }

        public final int getCount() {
            ArrayList j = l.this.k ? this.b.j() : this.b.h();
            return this.c < 0 ? j.size() : j.size() - 1;
        }

        public final h a(int i) {
            ArrayList j = l.this.k ? this.b.j() : this.b.h();
            if (this.c >= 0 && i >= this.c) {
                i++;
            }
            return (h) j.get(i);
        }

        public final long getItemId(int i) {
            return (long) i;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            View inflate;
            if (view == null) {
                inflate = l.this.h.inflate(a, viewGroup, false);
            } else {
                inflate = view;
            }
            android.support.v7.view.menu.n.a aVar = (android.support.v7.view.menu.n.a) inflate;
            if (l.this.e) {
                ((ListMenuItemView) inflate).setForceShowIcon(true);
            }
            aVar.a(a(i));
            return inflate;
        }

        private void a() {
            h hVar = l.this.i.k;
            if (hVar != null) {
                ArrayList j = l.this.i.j();
                int size = j.size();
                for (int i = 0; i < size; i++) {
                    if (((h) j.get(i)) == hVar) {
                        this.c = i;
                        return;
                    }
                }
            }
            this.c = -1;
        }

        public final void notifyDataSetChanged() {
            a();
            super.notifyDataSetChanged();
        }
    }

    static {
        a = R.layout.abc_popup_menu_item_layout;
    }

    private l(Context context, f fVar, View view) {
        this(context, fVar, view, false, R.attr.popupMenuStyle);
    }

    public l(Context context, f fVar, View view, boolean z, int i) {
        this(context, fVar, view, z, i, (byte) 0);
    }

    private l(Context context, f fVar, View view, boolean z, int i, byte b) {
        this.f = 0;
        this.g = context;
        this.h = LayoutInflater.from(context);
        this.i = fVar;
        this.j = new a(this.i);
        this.k = z;
        this.m = i;
        this.n = 0;
        Resources resources = context.getResources();
        this.l = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.b = view;
        fVar.a((m) this, context);
    }

    public final boolean d() {
        int i = 0;
        this.c = new ListPopupWindow(this.g, null, this.m, this.n);
        this.c.a((OnDismissListener) this);
        this.c.m = this;
        this.c.a(this.j);
        this.c.c();
        View view = this.b;
        if (view == null) {
            return false;
        }
        if (this.o == null) {
            boolean z = true;
        } else {
            int i2 = 0;
        }
        this.o = view.getViewTreeObserver();
        if (z) {
            this.o.addOnGlobalLayoutListener(this);
        }
        this.c.l = view;
        this.c.i = this.f;
        if (!this.q) {
            ListAdapter listAdapter = this.j;
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
            int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(0, 0);
            int count = listAdapter.getCount();
            int i3 = 0;
            int i4 = 0;
            View view2 = null;
            while (i3 < count) {
                View view3;
                i2 = listAdapter.getItemViewType(i3);
                if (i2 != i4) {
                    i4 = i2;
                    view3 = null;
                } else {
                    view3 = view2;
                }
                if (this.p == null) {
                    this.p = new FrameLayout(this.g);
                }
                view2 = listAdapter.getView(i3, view3, this.p);
                view2.measure(makeMeasureSpec, makeMeasureSpec2);
                i2 = view2.getMeasuredWidth();
                if (i2 >= this.l) {
                    i = this.l;
                    break;
                }
                if (i2 <= i) {
                    i2 = i;
                }
                i3++;
                i = i2;
            }
            this.r = i;
            this.q = true;
        }
        this.c.a(this.r);
        this.c.e();
        this.c.b();
        this.c.d.setOnKeyListener(this);
        return true;
    }

    public final void e() {
        if (f()) {
            this.c.d();
        }
    }

    public void onDismiss() {
        this.c = null;
        this.i.close();
        if (this.o != null) {
            if (!this.o.isAlive()) {
                this.o = this.b.getViewTreeObserver();
            }
            this.o.removeGlobalOnLayoutListener(this);
            this.o = null;
        }
    }

    public final boolean f() {
        return this.c != null && this.c.c.isShowing();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        a aVar = this.j;
        aVar.b.a(aVar.a(i), null, 0);
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        e();
        return true;
    }

    public void onGlobalLayout() {
        if (f()) {
            View view = this.b;
            if (view == null || !view.isShown()) {
                e();
            } else if (f()) {
                this.c.b();
            }
        }
    }

    public final void a(Context context, f fVar) {
    }

    public final void a(boolean z) {
        this.q = false;
        if (this.j != null) {
            this.j.notifyDataSetChanged();
        }
    }

    public final boolean a(q qVar) {
        if (qVar.hasVisibleItems()) {
            boolean z;
            l lVar = new l(this.g, qVar, this.b);
            lVar.d = this.d;
            int size = qVar.size();
            for (int i = 0; i < size; i++) {
                MenuItem item = qVar.getItem(i);
                if (item.isVisible() && item.getIcon() != null) {
                    z = true;
                    break;
                }
            }
            z = false;
            lVar.e = z;
            if (lVar.d()) {
                if (this.d == null) {
                    return true;
                }
                this.d.a(qVar);
                return true;
            }
        }
        return false;
    }

    public final void a(f fVar, boolean z) {
        if (fVar == this.i) {
            e();
            if (this.d != null) {
                this.d.a(fVar, z);
            }
        }
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
        return 0;
    }

    public final Parcelable c() {
        return null;
    }

    public final void a(Parcelable parcelable) {
    }
}
