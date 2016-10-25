package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.appcompat.R;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ScrollingTabContainerView.java
public final class bm extends HorizontalScrollView implements OnItemSelectedListener {
    private static final Interpolator i;
    Runnable a;
    int b;
    int c;
    private LinearLayoutCompat d;
    private Spinner e;
    private boolean f;
    private int g;
    private int h;

    // compiled from: ScrollingTabContainerView.java
    private class a extends BaseAdapter {
        private a() {
        }

        public final int getCount() {
            return bm.this.d.getChildCount();
        }

        public final Object getItem(int i) {
            return bm.this;
        }

        public final long getItemId(int i) {
            return (long) i;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                return bm.a(bm.this, (android.support.v7.app.ActionBar.a) getItem(i));
            }
            b bVar = (b) view;
            bm.this = (android.support.v7.app.ActionBar.a) getItem(i);
            bVar.a();
            return view;
        }
    }

    // compiled from: ScrollingTabContainerView.java
    private class b extends LinearLayoutCompat implements OnLongClickListener {
        android.support.v7.app.ActionBar.a a;
        private final int[] c;
        private TextView d;
        private ImageView e;
        private View f;

        public b(Context context, android.support.v7.app.ActionBar.a aVar) {
            super(context, null, R.attr.actionBarTabStyle);
            this.c = new int[]{16842964};
            this.a = aVar;
            cm a = cm.a(context, null, this.c, R.attr.actionBarTabStyle);
            if (a.e(0)) {
                setBackgroundDrawable(a.a(0));
            }
            a.a.recycle();
            setGravity(8388627);
            a();
        }

        public final void setSelected(boolean z) {
            Object obj = isSelected() != z ? 1 : null;
            super.setSelected(z);
            if (obj != null && z) {
                sendAccessibilityEvent(XZBDevice.DOWNLOAD_LIST_ALL);
            }
        }

        public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(android.support.v7.app.ActionBar.a.class.getName());
        }

        public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            if (VERSION.SDK_INT >= 14) {
                accessibilityNodeInfo.setClassName(android.support.v7.app.ActionBar.a.class.getName());
            }
        }

        public final void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (bm.this > 0 && getMeasuredWidth() > bm.this) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(bm.this, 1073741824), i2);
            }
        }

        public final void a() {
            android.support.v7.app.ActionBar.a aVar = this.a;
            View c = aVar.c();
            if (c != null) {
                b parent = c.getParent();
                if (parent != this) {
                    if (parent != null) {
                        parent.removeView(c);
                    }
                    addView(c);
                }
                this.f = c;
                if (this.d != null) {
                    this.d.setVisibility(XZBDevice.Wait);
                }
                if (this.e != null) {
                    this.e.setVisibility(XZBDevice.Wait);
                    this.e.setImageDrawable(null);
                    return;
                }
                return;
            }
            if (this.f != null) {
                removeView(this.f);
                this.f = null;
            }
            Drawable a = aVar.a();
            CharSequence b = aVar.b();
            if (a != null) {
                if (this.e == null) {
                    View imageView = new ImageView(getContext());
                    LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(-2, -2);
                    layoutParams.h = 16;
                    imageView.setLayoutParams(layoutParams);
                    addView(imageView, 0);
                    this.e = imageView;
                }
                this.e.setImageDrawable(a);
                this.e.setVisibility(0);
            } else if (this.e != null) {
                this.e.setVisibility(XZBDevice.Wait);
                this.e.setImageDrawable(null);
            }
            if (TextUtils.isEmpty(b)) {
                boolean z = false;
            } else {
                Object obj = 1;
            }
            if (z) {
                if (this.d == null) {
                    imageView = new AppCompatTextView(getContext(), null, R.attr.actionBarTabTextStyle);
                    imageView.setEllipsize(TruncateAt.END);
                    layoutParams = new LinearLayoutCompat.LayoutParams(-2, -2);
                    layoutParams.h = 16;
                    imageView.setLayoutParams(layoutParams);
                    addView(imageView);
                    this.d = imageView;
                }
                this.d.setText(b);
                this.d.setVisibility(0);
            } else if (this.d != null) {
                this.d.setVisibility(XZBDevice.Wait);
                this.d.setText(null);
            }
            if (this.e != null) {
                this.e.setContentDescription(aVar.d());
            }
            if (z || TextUtils.isEmpty(aVar.d())) {
                setOnLongClickListener(null);
                setLongClickable(false);
                return;
            }
            setOnLongClickListener(this);
        }

        public final boolean onLongClick(View view) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            Context context = getContext();
            int width = getWidth();
            int height = getHeight();
            int i = context.getResources().getDisplayMetrics().widthPixels;
            Toast makeText = Toast.makeText(context, this.a.d(), 0);
            makeText.setGravity(com.xunlei.tdlive.R.styleable.AppCompatTheme_actionButtonStyle, (iArr[0] + (width / 2)) - (i / 2), height);
            makeText.show();
            return true;
        }
    }

    static {
        i = new DecelerateInterpolator();
    }

    public final void onMeasure(int i, int i2) {
        Object obj = 1;
        int mode = MeasureSpec.getMode(i);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        int childCount = this.d.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.b = -1;
        } else {
            if (childCount > 2) {
                this.b = (int) (((float) MeasureSpec.getSize(i)) * 0.4f);
            } else {
                this.b = MeasureSpec.getSize(i) / 2;
            }
            this.b = Math.min(this.b, this.c);
        }
        mode = MeasureSpec.makeMeasureSpec(this.g, 1073741824);
        if (z || !this.f) {
            obj = (byte) 0;
        }
        if (obj != null) {
            this.d.measure(0, mode);
            if (this.d.getMeasuredWidth() <= MeasureSpec.getSize(i)) {
                b();
            } else if (!a()) {
                if (this.e == null) {
                    Spinner appCompatSpinner = new AppCompatSpinner(getContext(), null, R.attr.actionDropDownStyle);
                    appCompatSpinner.setLayoutParams(new LinearLayoutCompat.LayoutParams(-2, -1));
                    appCompatSpinner.setOnItemSelectedListener(this);
                    this.e = appCompatSpinner;
                }
                removeView(this.d);
                addView(this.e, new LayoutParams(-2, -1));
                if (this.e.getAdapter() == null) {
                    this.e.setAdapter(new a());
                }
                if (this.a != null) {
                    removeCallbacks(this.a);
                    this.a = null;
                }
                this.e.setSelection(this.h);
            }
        } else {
            b();
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i, mode);
        int measuredWidth2 = getMeasuredWidth();
        if (z && measuredWidth != measuredWidth2) {
            setTabSelected(this.h);
        }
    }

    private boolean a() {
        return this.e != null && this.e.getParent() == this;
    }

    public final void setAllowCollapse(boolean z) {
        this.f = z;
    }

    private boolean b() {
        if (a()) {
            removeView(this.e);
            addView(this.d, new LayoutParams(-2, -1));
            setTabSelected(this.e.getSelectedItemPosition());
        }
        return false;
    }

    public final void setTabSelected(int i) {
        this.h = i;
        int childCount = this.d.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            boolean z;
            View childAt = this.d.getChildAt(i2);
            if (i2 == i) {
                z = true;
            } else {
                z = false;
            }
            childAt.setSelected(z);
            if (z) {
                View childAt2 = this.d.getChildAt(i);
                if (this.a != null) {
                    removeCallbacks(this.a);
                }
                this.a = new bn(this, childAt2);
                post(this.a);
            }
        }
        if (this.e != null && i >= 0) {
            this.e.setSelection(i);
        }
    }

    public final void setContentHeight(int i) {
        this.g = i;
        requestLayout();
    }

    protected final void onConfigurationChanged(Configuration configuration) {
        if (VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        android.support.v7.view.a a = android.support.v7.view.a.a(getContext());
        TypedArray obtainStyledAttributes = a.a.obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(R.styleable.ActionBar_height, 0);
        Resources resources = a.a.getResources();
        if (!a.a()) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(R.dimen.abc_action_bar_stacked_max_height));
        }
        obtainStyledAttributes.recycle();
        setContentHeight(layoutDimension);
        this.c = a.a.getResources().getDimensionPixelSize(R.dimen.abc_action_bar_stacked_tab_max_width);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a != null) {
            post(this.a);
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            removeCallbacks(this.a);
        }
    }

    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
    }

    public final void onNothingSelected(AdapterView<?> adapterView) {
    }

    static /* synthetic */ b a(bm bmVar, android.support.v7.app.ActionBar.a aVar) {
        b bVar = new b(bmVar.getContext(), aVar);
        bVar.setBackgroundDrawable(null);
        bVar.setLayoutParams(new AbsListView.LayoutParams(-1, bmVar.g));
        return bVar;
    }
}
