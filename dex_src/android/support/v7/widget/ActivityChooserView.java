package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.widget.j.c;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class ActivityChooserView extends ViewGroup {
    ActionProvider a;
    private final a b;
    private final b c;
    private final LinearLayoutCompat d;
    private final Drawable e;
    private final FrameLayout f;
    private final ImageView g;
    private final FrameLayout h;
    private final ImageView i;
    private final int j;
    private final DataSetObserver k;
    private final OnGlobalLayoutListener l;
    private ListPopupWindow m;
    private OnDismissListener n;
    private boolean o;
    private int p;
    private boolean q;
    private int r;

    public static class InnerLayout extends LinearLayoutCompat {
        private static final int[] a;

        static {
            a = new int[]{16842964};
        }

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            cm a = cm.a(context, attributeSet, a);
            setBackgroundDrawable(a.a(0));
            a.a.recycle();
        }
    }

    private class a extends BaseAdapter {
        j a;
        boolean b;
        private int d;
        private boolean e;
        private boolean f;

        private a() {
            this.d = 4;
        }

        public final int getItemViewType(int i) {
            return (this.f && i == getCount() - 1) ? 1 : 0;
        }

        public final int getViewTypeCount() {
            return XZBDevice.DOWNLOAD_LIST_FAILED;
        }

        public final int getCount() {
            int a = this.a.a();
            if (!(this.b || this.a.b() == null)) {
                a--;
            }
            a = Math.min(a, this.d);
            return this.f ? a + 1 : a;
        }

        public final Object getItem(int i) {
            switch (getItemViewType(i)) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    if (!(this.b || this.a.b() == null)) {
                        i++;
                    }
                    return this.a.a(i);
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    return null;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public final long getItemId(int i) {
            return (long) i;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            switch (getItemViewType(i)) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    if (view == null || view.getId() != R.id.list_item) {
                        view = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, viewGroup, false);
                    }
                    PackageManager packageManager = ActivityChooserView.this.getContext().getPackageManager();
                    ResolveInfo resolveInfo = (ResolveInfo) getItem(i);
                    ((ImageView) view.findViewById(R.id.icon)).setImageDrawable(resolveInfo.loadIcon(packageManager));
                    ((TextView) view.findViewById(R.id.title)).setText(resolveInfo.loadLabel(packageManager));
                    if (this.b && i == 0 && this.e) {
                        ViewCompat.setActivated(view, true);
                        return view;
                    }
                    ViewCompat.setActivated(view, false);
                    return view;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (view != null && view.getId() == 1) {
                        return view;
                    }
                    view = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, viewGroup, false);
                    view.setId(1);
                    ((TextView) view.findViewById(R.id.title)).setText(ActivityChooserView.this.getContext().getString(R.string.abc_activity_chooser_view_see_all));
                    return view;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public final int a() {
            int i = 0;
            int i2 = this.d;
            this.d = Integer.MAX_VALUE;
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
            int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(0, 0);
            int count = getCount();
            View view = null;
            int i3 = 0;
            while (i < count) {
                view = getView(i, view, null);
                view.measure(makeMeasureSpec, makeMeasureSpec2);
                i3 = Math.max(i3, view.getMeasuredWidth());
                i++;
            }
            this.d = i2;
            return i3;
        }

        public final void a(int i) {
            if (this.d != i) {
                this.d = i;
                notifyDataSetChanged();
            }
        }

        public final void a(boolean z) {
            if (this.f != z) {
                this.f = z;
                notifyDataSetChanged();
            }
        }

        public final void a(boolean z, boolean z2) {
            if (this.b != z || this.e != z2) {
                this.b = z;
                this.e = z2;
                notifyDataSetChanged();
            }
        }
    }

    private class b implements OnClickListener, OnLongClickListener, OnItemClickListener, OnDismissListener {
        private b() {
        }

        public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            switch (((a) adapterView.getAdapter()).getItemViewType(i)) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    ActivityChooserView.this.b();
                    if (!ActivityChooserView.this.o) {
                        if (!ActivityChooserView.this.b.b) {
                            i++;
                        }
                        Intent b = ActivityChooserView.this.b(i);
                        if (b != null) {
                            b.addFlags(anet.channel.a.b.MAX_POOL_SIZE);
                            ActivityChooserView.this.getContext().startActivity(b);
                        }
                    } else if (i > 0) {
                        j jVar = ActivityChooserView.this;
                        synchronized (ActivityChooserView.this) {
                            float f;
                            jVar.d();
                            android.support.v7.widget.j.a aVar = (android.support.v7.widget.j.a) jVar.b.get(i);
                            android.support.v7.widget.j.a aVar2 = (android.support.v7.widget.j.a) jVar.b.get(0);
                            if (aVar2 != null) {
                                f = (aVar2.b - aVar.b) + 5.0f;
                            } else {
                                f = 1.0f;
                            }
                            jVar.a(new c(new ComponentName(ActivityChooserView.this.activityInfo.packageName, ActivityChooserView.this.activityInfo.name), System.currentTimeMillis(), f));
                        }
                    }
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    ActivityChooserView.this.a((int) InMobiClientPositioning.NO_REPEAT);
                default:
                    throw new IllegalArgumentException();
            }
        }

        public final void onClick(View view) {
            if (view == ActivityChooserView.this.h) {
                ActivityChooserView.this.b();
                Intent b = ActivityChooserView.this.b(ActivityChooserView.this.a(ActivityChooserView.this.b()));
                if (b != null) {
                    b.addFlags(anet.channel.a.b.MAX_POOL_SIZE);
                    ActivityChooserView.this.getContext().startActivity(b);
                }
            } else if (view == ActivityChooserView.this.f) {
                ActivityChooserView.this.o = false;
                ActivityChooserView.this.a(ActivityChooserView.this.p);
            } else {
                throw new IllegalArgumentException();
            }
        }

        public final boolean onLongClick(View view) {
            if (view == ActivityChooserView.this.h) {
                if (ActivityChooserView.this.b.getCount() > 0) {
                    ActivityChooserView.this.o = true;
                    ActivityChooserView.this.a(ActivityChooserView.this.p);
                }
                return true;
            }
            throw new IllegalArgumentException();
        }

        public final void onDismiss() {
            if (ActivityChooserView.this.n != null) {
                ActivityChooserView.this.n.onDismiss();
            }
            if (ActivityChooserView.this != null) {
                ActivityChooserView.this.subUiVisibilityChanged(false);
            }
        }
    }

    static /* synthetic */ void c(ActivityChooserView activityChooserView) {
        if (activityChooserView.b.getCount() > 0) {
            activityChooserView.f.setEnabled(true);
        } else {
            activityChooserView.f.setEnabled(false);
        }
        int a = activityChooserView.b.a.a();
        int c = activityChooserView.b.a.c();
        if (a == 1 || (a > 1 && c > 0)) {
            activityChooserView.h.setVisibility(0);
            ResolveInfo b = activityChooserView.b.a.b();
            PackageManager packageManager = activityChooserView.getContext().getPackageManager();
            activityChooserView.i.setImageDrawable(b.loadIcon(packageManager));
            if (activityChooserView.r != 0) {
                CharSequence loadLabel = b.loadLabel(packageManager);
                activityChooserView.h.setContentDescription(activityChooserView.getContext().getString(activityChooserView.r, new Object[]{loadLabel}));
            }
        } else {
            activityChooserView.h.setVisibility(XZBDevice.Wait);
        }
        if (activityChooserView.h.getVisibility() == 0) {
            activityChooserView.d.setBackgroundDrawable(activityChooserView.e);
        } else {
            activityChooserView.d.setBackgroundDrawable(null);
        }
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new k(this);
        this.l = new l(this);
        this.p = 4;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActivityChooserView, i, 0);
        this.p = obtainStyledAttributes.getInt(R.styleable.ActivityChooserView_initialActivityCount, XZBDevice.DOWNLOAD_LIST_ALL);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
        obtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(R.layout.abc_activity_chooser_view, this, true);
        this.c = new b();
        this.d = (LinearLayoutCompat) findViewById(R.id.activity_chooser_view_content);
        this.e = this.d.getBackground();
        this.h = (FrameLayout) findViewById(R.id.default_activity_button);
        this.h.setOnClickListener(this.c);
        this.h.setOnLongClickListener(this.c);
        this.i = (ImageView) this.h.findViewById(R.id.image);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.expand_activities_button);
        frameLayout.setOnClickListener(this.c);
        frameLayout.setOnTouchListener(new m(this, frameLayout));
        this.f = frameLayout;
        this.g = (ImageView) frameLayout.findViewById(R.id.image);
        this.g.setImageDrawable(drawable);
        this.b = new a();
        this.b.registerDataSetObserver(new n(this));
        Resources resources = context.getResources();
        this.j = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
    }

    public void setActivityChooserModel(j jVar) {
        a aVar = this.b;
        j jVar2 = aVar.c.b.a;
        if (jVar2 != null && aVar.c.isShown()) {
            jVar2.unregisterObserver(aVar.c.k);
        }
        aVar.a = jVar;
        if (jVar != null && aVar.c.isShown()) {
            jVar.registerObserver(aVar.c.k);
        }
        aVar.notifyDataSetChanged();
        if (getListPopupWindow().c.isShowing()) {
            b();
            a();
        }
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.g.setImageDrawable(drawable);
    }

    public void setExpandActivityOverflowButtonContentDescription(int i) {
        this.g.setContentDescription(getContext().getString(i));
    }

    public void setProvider(ActionProvider actionProvider) {
        this.a = actionProvider;
    }

    public final boolean a() {
        if (getListPopupWindow().c.isShowing() || !this.q) {
            return false;
        }
        this.o = false;
        a(this.p);
        return true;
    }

    private void a(int i) {
        if (this.b.a == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.l);
        boolean z = this.h.getVisibility() == 0;
        int a = this.b.a.a();
        int i2;
        if (z) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (i == Integer.MAX_VALUE || a <= r3 + i) {
            this.b.a(false);
            this.b.a(i);
        } else {
            this.b.a(true);
            this.b.a(i - 1);
        }
        ListPopupWindow listPopupWindow = getListPopupWindow();
        if (!listPopupWindow.c.isShowing()) {
            if (this.o || !z) {
                this.b.a(true, z);
            } else {
                this.b.a(false, false);
            }
            listPopupWindow.a(Math.min(this.b.a(), this.j));
            listPopupWindow.b();
            if (this.a != null) {
                this.a.subUiVisibilityChanged(true);
            }
            listPopupWindow.d.setContentDescription(getContext().getString(R.string.abc_activitychooserview_choose_application));
        }
    }

    public final boolean c() {
        return getListPopupWindow().c.isShowing();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        j jVar = this.b.a;
        if (jVar != null) {
            jVar.registerObserver(this.k);
        }
        this.q = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        j jVar = this.b.a;
        if (jVar != null) {
            jVar.unregisterObserver(this.k);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.l);
        }
        if (c()) {
            b();
        }
        this.q = false;
    }

    protected void onMeasure(int i, int i2) {
        View view = this.d;
        if (this.h.getVisibility() != 0) {
            i2 = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i2), 1073741824);
        }
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.d.layout(0, 0, i3 - i, i4 - i2);
        if (!c()) {
            b();
        }
    }

    public j getDataModel() {
        return this.b.a;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.n = onDismissListener;
    }

    public void setInitialActivityCount(int i) {
        this.p = i;
    }

    public void setDefaultActionButtonContentDescription(int i) {
        this.r = i;
    }

    private ListPopupWindow getListPopupWindow() {
        if (this.m == null) {
            this.m = new ListPopupWindow(getContext());
            this.m.a(this.b);
            this.m.l = this;
            this.m.c();
            this.m.m = this.c;
            this.m.a(this.c);
        }
        return this.m;
    }

    public final boolean b() {
        if (getListPopupWindow().c.isShowing()) {
            getListPopupWindow().d();
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this.l);
            }
        }
        return true;
    }
}
