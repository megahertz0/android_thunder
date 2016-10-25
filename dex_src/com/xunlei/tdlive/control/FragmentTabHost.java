package com.xunlei.tdlive.control;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import com.xunlei.tdlive.base.i;
import java.util.ArrayList;
import java.util.List;

public class FragmentTabHost extends TabHost implements OnTabChangeListener {
    private final ArrayList<b> a;
    private FrameLayout b;
    private Context c;
    private FragmentManager d;
    private int e;
    private OnTabChangeListener f;
    private b g;
    private boolean h;
    private List<TabSpec> i;

    public FragmentTabHost(Context context) {
        super(context, null);
        this.a = new ArrayList();
        this.i = new ArrayList(2);
        a(context, null);
    }

    public FragmentTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new ArrayList();
        this.i = new ArrayList(2);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16842995}, 0, 0);
        this.e = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    private void a(Context context) {
        if (findViewById(16908307) == null) {
            View linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            addView(linearLayout, new LayoutParams(-1, -1));
            View tabWidget = new TabWidget(context);
            tabWidget.setId(16908307);
            tabWidget.setOrientation(0);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(-1, -2, 0.0f));
            tabWidget = new FrameLayout(context);
            tabWidget.setId(16908305);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(0, 0, 0.0f));
            tabWidget = new FrameLayout(context);
            this.b = tabWidget;
            this.b.setId(this.e);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
    }

    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    public void setup(Context context, FragmentManager fragmentManager) {
        a(context);
        super.setup();
        this.c = context;
        this.d = fragmentManager;
        a();
    }

    public void setup(Context context, FragmentManager fragmentManager, int i) {
        a(context);
        super.setup();
        this.c = context;
        this.d = fragmentManager;
        this.e = i;
        a();
        this.b.setId(i);
        if (getId() == -1) {
            setId(16908306);
        }
    }

    private void a() {
        if (this.b == null) {
            this.b = (FrameLayout) findViewById(this.e);
            if (this.b == null) {
                throw new IllegalStateException(new StringBuilder("No tab content FrameLayout found for id ").append(this.e).toString());
            }
        }
    }

    public void setOnTabChangedListener(OnTabChangeListener onTabChangeListener) {
        this.f = onTabChangeListener;
    }

    public void addTab(TabSpec tabSpec, Class<?> cls, Bundle bundle) {
        tabSpec.setContent(new a(this.c));
        String tag = tabSpec.getTag();
        b bVar = new b(tag, cls, bundle);
        if (this.h) {
            b.a(bVar, this.d.findFragmentByTag(tag));
            if (!(b.a(bVar) == null || b.a(bVar).isDetached())) {
                FragmentTransaction beginTransaction = this.d.beginTransaction();
                beginTransaction.detach(b.a(bVar));
                beginTransaction.commit();
            }
        }
        this.a.add(bVar);
        addTab(tabSpec);
        this.i.add(tabSpec);
    }

    public View getTabViewByTag(String str) {
        for (int i = 0; i < this.i.size(); i++) {
            if (((TabSpec) this.i.get(i)).getTag().equals(str)) {
                return getTabWidget().getChildTabViewAt(i);
            }
        }
        return null;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        FragmentTransaction fragmentTransaction = null;
        for (int i = 0; i < this.a.size(); i++) {
            b bVar = (b) this.a.get(i);
            b.a(bVar, this.d.findFragmentByTag(b.b(bVar)));
            if (b.a(bVar) != null && !b.a(bVar).isDetached()) {
                if (b.b(bVar).equals(currentTabTag)) {
                    this.g = bVar;
                } else {
                    if (fragmentTransaction == null) {
                        fragmentTransaction = this.d.beginTransaction();
                    }
                    fragmentTransaction.detach(b.a(bVar));
                }
            }
        }
        this.h = true;
        FragmentTransaction a = a(currentTabTag, fragmentTransaction);
        if (a != null) {
            try {
                a.commit();
                this.d.executePendingTransactions();
            } catch (Exception e) {
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.h = false;
    }

    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = getCurrentTabTag();
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentTabByTag(savedState.a);
    }

    public void onTabChanged(String str) {
        if (str == null || str.length() <= 0) {
            setCurrentTabByTag(b.b(this.g));
            return;
        }
        if (this.h) {
            FragmentTransaction a = a(str, null);
            if (a != null) {
                a.commit();
            }
        }
        if (this.f != null) {
            this.f.onTabChanged(str);
        }
    }

    private FragmentTransaction a(String str, FragmentTransaction fragmentTransaction) {
        b bVar = null;
        int i = 0;
        while (i < this.a.size()) {
            b bVar2 = (b) this.a.get(i);
            if (!b.b(bVar2).equals(str)) {
                bVar2 = bVar;
            }
            i++;
            bVar = bVar2;
        }
        if (bVar == null) {
            throw new IllegalStateException(new StringBuilder("No tab known for tag ").append(str).toString());
        }
        if (this.g != bVar) {
            if (fragmentTransaction == null) {
                fragmentTransaction = this.d.beginTransaction();
            }
            if (!(this.g == null || b.a(this.g) == null)) {
                fragmentTransaction.hide(b.a(this.g));
                if (b.a(this.g) instanceof i) {
                    ((i) b.a(this.g)).a();
                }
            }
            if (bVar != null) {
                if (b.a(bVar) == null) {
                    b.a(bVar, Fragment.instantiate(this.c, b.c(bVar).getName(), b.d(bVar)));
                    fragmentTransaction.add(this.e, b.a(bVar), b.b(bVar));
                } else {
                    if (b.a(bVar).isDetached()) {
                        fragmentTransaction.attach(b.a(bVar));
                    }
                    fragmentTransaction.show(b.a(bVar));
                }
                if (b.a(bVar) instanceof i) {
                    ((i) b.a(bVar)).a(false);
                }
            }
            this.g = bVar;
        }
        return fragmentTransaction;
    }

    public ReclickLinearLayout newClickableTab() {
        return new ReclickLinearLayout(this, getContext());
    }
}
