package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ActionProvider.SubUiVisibilityListener;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.l;
import android.support.v7.view.menu.n;
import android.support.v7.view.menu.q;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;

final class ActionMenuPresenter extends android.support.v7.view.menu.b implements SubUiVisibilityListener {
    private e A;
    private a B;
    private b C;
    d i;
    Drawable j;
    boolean k;
    int l;
    boolean m;
    boolean n;
    c o;
    final f p;
    int q;
    private boolean r;
    private boolean s;
    private int t;
    private int u;
    private boolean v;
    private boolean w;
    private int x;
    private final SparseBooleanArray y;
    private View z;

    private static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR;
        public int a;

        SavedState() {
        }

        SavedState(Parcel parcel) {
            this.a = parcel.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
        }

        static {
            CREATOR = new i();
        }
    }

    private class a extends l {
        final /* synthetic */ ActionMenuPresenter g;
        private q h;

        public a(ActionMenuPresenter actionMenuPresenter, Context context, q qVar) {
            boolean z = false;
            this.g = actionMenuPresenter;
            super(context, qVar, null, false, R.attr.actionOverflowMenuStyle);
            this.h = qVar;
            if (!((h) qVar.getItem()).e()) {
                View view;
                if (actionMenuPresenter.i == null) {
                    view = (View) actionMenuPresenter.g;
                } else {
                    view = actionMenuPresenter.i;
                }
                this.b = view;
            }
            this.d = actionMenuPresenter.p;
            int size = qVar.size();
            for (int i = 0; i < size; i++) {
                MenuItem item = qVar.getItem(i);
                if (item.isVisible() && item.getIcon() != null) {
                    z = true;
                    break;
                }
            }
            this.e = z;
        }

        public final void onDismiss() {
            super.onDismiss();
            this.g.B = null;
            this.g.q = 0;
        }
    }

    private class b extends android.support.v7.view.menu.ActionMenuItemView.b {
        private b() {
        }

        public final ListPopupWindow a() {
            return ActionMenuPresenter.this.B != null ? ActionMenuPresenter.this.B.c : null;
        }
    }

    private class c implements Runnable {
        private e b;

        public c(e eVar) {
            this.b = eVar;
        }

        public final void run() {
            android.support.v7.view.menu.f h = ActionMenuPresenter.this.c;
            if (h.b != null) {
                h.b.a(h);
            }
            View view = (View) ActionMenuPresenter.this.g;
            if (!(view == null || view.getWindowToken() == null || !this.b.d())) {
                ActionMenuPresenter.this.A = this.b;
            }
            ActionMenuPresenter.this.o = null;
        }
    }

    private class d extends AppCompatImageView implements android.support.v7.widget.ActionMenuView.a {
        private final float[] b;

        public d(Context context) {
            super(context, null, R.attr.actionOverflowButtonStyle);
            this.b = new float[2];
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            setOnTouchListener(new h(this, this, ActionMenuPresenter.this));
        }

        public final boolean performClick() {
            if (!super.performClick()) {
                playSoundEffect(0);
                ActionMenuPresenter.this.e();
            }
            return true;
        }

        public final boolean c() {
            return false;
        }

        public final boolean d() {
            return false;
        }

        protected final boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (!(drawable == null || background == null)) {
                int width = getWidth();
                int height = getHeight();
                int max = Math.max(width, height) / 2;
                width = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                height = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                DrawableCompat.setHotspotBounds(background, width - max, height - max, width + max, height + max);
            }
            return frame;
        }
    }

    private class e extends l {
        public e(Context context, android.support.v7.view.menu.f fVar, View view) {
            super(context, fVar, view, true, R.attr.actionOverflowMenuStyle);
            this.f = 8388613;
            this.d = ActionMenuPresenter.this.p;
        }

        public final void onDismiss() {
            super.onDismiss();
            if (ActionMenuPresenter.this.c != null) {
                ActionMenuPresenter.this.c.close();
            }
            ActionMenuPresenter.this.A = null;
        }
    }

    private class f implements android.support.v7.view.menu.m.a {
        private f() {
        }

        public final boolean a(android.support.v7.view.menu.f fVar) {
            if (fVar == null) {
                return false;
            }
            ActionMenuPresenter.this.q = ((q) fVar).getItem().getItemId();
            android.support.v7.view.menu.m.a aVar = ActionMenuPresenter.this.f;
            return aVar != null ? aVar.a(fVar) : false;
        }

        public final void a(android.support.v7.view.menu.f fVar, boolean z) {
            if (fVar instanceof q) {
                ((q) fVar).m.b(false);
            }
            android.support.v7.view.menu.m.a aVar = ActionMenuPresenter.this.f;
            if (aVar != null) {
                aVar.a(fVar, z);
            }
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
        this.y = new SparseBooleanArray();
        this.p = new f();
    }

    public final void a(Context context, android.support.v7.view.menu.f fVar) {
        boolean z = true;
        super.a(context, fVar);
        Resources resources = context.getResources();
        android.support.v7.view.a a = android.support.v7.view.a.a(context);
        if (!this.s) {
            if (VERSION.SDK_INT < 19 && ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(a.a))) {
                z = false;
            }
            this.r = z;
        }
        if (!this.w) {
            this.t = a.a.getResources().getDisplayMetrics().widthPixels / 2;
        }
        if (!this.m) {
            this.l = a.a.getResources().getInteger(R.integer.abc_max_action_buttons);
        }
        int i = this.t;
        if (this.r) {
            if (this.i == null) {
                this.i = new d(this.a);
                if (this.k) {
                    this.i.setImageDrawable(this.j);
                    this.j = null;
                    this.k = false;
                }
                int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
                this.i.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i -= this.i.getMeasuredWidth();
        } else {
            this.i = null;
        }
        this.u = i;
        this.x = (int) (56.0f * resources.getDisplayMetrics().density);
        this.z = null;
    }

    public final void d() {
        this.r = true;
        this.s = true;
    }

    public final n a(ViewGroup viewGroup) {
        n a = super.a(viewGroup);
        ((ActionMenuView) a).setPresenter(this);
        return a;
    }

    public final View a(h hVar, View view, ViewGroup viewGroup) {
        View actionView = hVar.getActionView();
        if (actionView == null || hVar.h()) {
            actionView = super.a(hVar, view, viewGroup);
        }
        actionView.setVisibility(hVar.isActionViewExpanded() ? XZBDevice.Wait : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(ActionMenuView.a(layoutParams));
        }
        return actionView;
    }

    public final void a(h hVar, android.support.v7.view.menu.n.a aVar) {
        aVar.a(hVar);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.g);
        if (this.C == null) {
            this.C = new b();
        }
        actionMenuItemView.setPopupCallback(this.C);
    }

    public final boolean c(h hVar) {
        return hVar.e();
    }

    public final void a(boolean z) {
        int i;
        Object obj = 1;
        int i2 = 0;
        ((View) this.g).getParent();
        super.a(z);
        ((View) this.g).requestLayout();
        if (this.c != null) {
            android.support.v7.view.menu.f fVar = this.c;
            fVar.i();
            ArrayList arrayList = fVar.d;
            int size = arrayList.size();
            for (i = 0; i < size; i++) {
                ActionProvider supportActionProvider = ((h) arrayList.get(i)).getSupportActionProvider();
                if (supportActionProvider != null) {
                    supportActionProvider.setSubUiVisibilityListener(this);
                }
            }
        }
        ArrayList j = this.c != null ? this.c.j() : null;
        if (this.r && j != null) {
            i = j.size();
            if (i == 1) {
                int i3;
                if (((h) j.get(0)).isActionViewExpanded()) {
                    i3 = 0;
                } else {
                    i3 = 1;
                }
                i2 = i3;
            } else {
                int i4;
                if (i <= 0) {
                    i4 = 0;
                }
                i2 = i4;
            }
        }
        if (i2 != 0) {
            if (this.i == null) {
                this.i = new d(this.a);
            }
            ViewGroup viewGroup = (ViewGroup) this.i.getParent();
            if (viewGroup != this.g) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.i);
                }
                ((ActionMenuView) this.g).addView(this.i, ActionMenuView.a());
            }
        } else if (this.i != null && this.i.getParent() == this.g) {
            ((ViewGroup) this.g).removeView(this.i);
        }
        ((ActionMenuView) this.g).setOverflowReserved(this.r);
    }

    public final boolean a(ViewGroup viewGroup, int i) {
        return viewGroup.getChildAt(i) == this.i ? false : super.a(viewGroup, i);
    }

    public final boolean a(q qVar) {
        if (!qVar.hasVisibleItems()) {
            return false;
        }
        View view;
        q qVar2 = qVar;
        while (qVar2.m != this.c) {
            qVar2 = (q) qVar2.m;
        }
        h item = qVar2.getItem();
        ViewGroup viewGroup = (ViewGroup) this.g;
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if ((childAt instanceof android.support.v7.view.menu.n.a) && ((android.support.v7.view.menu.n.a) childAt).getItemData() == item) {
                    view = childAt;
                    break;
                }
            }
        }
        view = null;
        if (view == null) {
            if (this.i == null) {
                return false;
            }
            view = this.i;
        }
        this.q = qVar.getItem().getItemId();
        this.B = new a(this, this.b, qVar);
        this.B.b = view;
        if (this.B.d()) {
            super.a(qVar);
            return true;
        }
        throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
    }

    public final boolean e() {
        if (!this.r || i() || this.c == null || this.g == null || this.o != null || this.c.j().isEmpty()) {
            return false;
        }
        this.o = new c(new e(this.b, this.c, this.i));
        ((View) this.g).post(this.o);
        super.a(null);
        return true;
    }

    public final boolean f() {
        if (this.o == null || this.g == null) {
            l lVar = this.A;
            if (lVar == null) {
                return false;
            }
            lVar.e();
            return true;
        }
        ((View) this.g).removeCallbacks(this.o);
        this.o = null;
        return true;
    }

    public final boolean g() {
        return f() | h();
    }

    public final boolean h() {
        if (this.B == null) {
            return false;
        }
        this.B.e();
        return true;
    }

    public final boolean i() {
        return this.A != null && this.A.f();
    }

    public final boolean a() {
        int i;
        ArrayList h = this.c.h();
        int size = h.size();
        int i2 = this.l;
        int i3 = this.u;
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.g;
        int i4 = 0;
        int i5 = 0;
        Object obj = null;
        int i6 = 0;
        while (i6 < size) {
            h hVar = (h) h.get(i6);
            if (hVar.g()) {
                i4++;
            } else if (hVar.f()) {
                i5++;
            } else {
                obj = 1;
            }
            if (this.n && hVar.isActionViewExpanded()) {
                Object obj2 = null;
            } else {
                i = i2;
            }
            i6++;
            i2 = i;
        }
        if (this.r) {
            if (obj != null || i4 + i5 > i2) {
                i2--;
            }
        }
        i6 = i2 - i4;
        SparseBooleanArray sparseBooleanArray = this.y;
        sparseBooleanArray.clear();
        if (this.v) {
            int i7 = i3 / this.x;
            i5 = ((i3 % this.x) / i7) + this.x;
            i = i7;
        } else {
            i5 = 0;
            obj2 = null;
        }
        i2 = 0;
        int i8 = 0;
        i4 = i;
        while (i8 < size) {
            h hVar2 = (h) h.get(i8);
            View a;
            int i9;
            if (hVar2.g()) {
                a = a(hVar2, this.z, viewGroup);
                if (this.z == null) {
                    this.z = a;
                }
                if (this.v) {
                    i = i4 - ActionMenuView.a(a, i5, i4, makeMeasureSpec, 0);
                } else {
                    a.measure(makeMeasureSpec, makeMeasureSpec);
                    i = i4;
                }
                i4 = a.getMeasuredWidth();
                i9 = i3 - i4;
                if (i2 != 0) {
                    i4 = i2;
                }
                i2 = hVar2.getGroupId();
                if (i2 != 0) {
                    sparseBooleanArray.put(i2, true);
                }
                hVar2.c(true);
                i7 = i9;
                i2 = i6;
            } else if (hVar2.f()) {
                boolean z;
                int groupId = hVar2.getGroupId();
                boolean z2 = sparseBooleanArray.get(groupId);
                boolean z3 = ((i6 > 0 || z2) && i3 > 0 && (!this.v || i4 > 0)) ? 1 : 0;
                if (z3) {
                    a = a(hVar2, this.z, viewGroup);
                    if (this.z == null) {
                        this.z = a;
                    }
                    if (this.v) {
                        int a2 = ActionMenuView.a(a, i5, i4, makeMeasureSpec, 0);
                        i4 -= a2;
                        if (a2 == 0) {
                            i = 0;
                        }
                    } else {
                        a.measure(makeMeasureSpec, makeMeasureSpec);
                    }
                    i9 = a.getMeasuredWidth();
                    i3 -= i9;
                    if (i2 == 0) {
                        i2 = i9;
                    }
                    if (this.v) {
                        z = i & (i3 >= 0 ? 1 : 0);
                        i9 = i4;
                    } else {
                        z = i & (i3 + i2 > 0 ? 1 : 0);
                        i9 = i4;
                    }
                } else {
                    z = z3;
                    i9 = i4;
                }
                if (z && groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                    i = i6;
                } else if (z2) {
                    sparseBooleanArray.put(groupId, false);
                    i4 = i6;
                    for (i6 = 0; i6 < i8; i6++) {
                        hVar = (h) h.get(i6);
                        if (hVar.getGroupId() == groupId) {
                            if (hVar.e()) {
                                i4++;
                            }
                            hVar.c(false);
                        }
                    }
                    i = i4;
                } else {
                    i = i6;
                }
                if (z) {
                    i--;
                }
                hVar2.c(z);
                i4 = i2;
                i7 = i3;
                i2 = i;
                i = i9;
            } else {
                hVar2.c(false);
                i = i4;
                i7 = i3;
                i4 = i2;
                i2 = i6;
            }
            i8++;
            i3 = i7;
            i6 = i2;
            i2 = i4;
            i4 = i;
        }
        return true;
    }

    public final void a(android.support.v7.view.menu.f fVar, boolean z) {
        g();
        super.a(fVar, z);
    }

    public final Parcelable c() {
        Parcelable savedState = new SavedState();
        savedState.a = this.q;
        return savedState;
    }

    public final void a(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            if (savedState.a > 0) {
                MenuItem findItem = this.c.findItem(savedState.a);
                if (findItem != null) {
                    a((q) findItem.getSubMenu());
                }
            }
        }
    }

    public final void onSubUiVisibilityChanged(boolean z) {
        if (z) {
            super.a(null);
        } else {
            this.c.b(false);
        }
    }

    public final void a(ActionMenuView actionMenuView) {
        this.g = actionMenuView;
        actionMenuView.a = this.c;
    }
}
