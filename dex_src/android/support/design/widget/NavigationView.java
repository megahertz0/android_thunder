package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.design.R;
import android.support.design.internal.NavigationMenuView;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.design.internal.b;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.view.g;
import android.support.v7.view.menu.f;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.m;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import org.android.spdy.SpdyAgent;

public class NavigationView extends ScrimInsetsFrameLayout {
    private static final int[] a;
    private static final int[] b;
    private final android.support.design.internal.a c;
    private final b d;
    private a e;
    private int f;
    private MenuInflater g;

    public static class SavedState extends BaseSavedState {
        public static final Creator<android.support.design.widget.NavigationView.SavedState> CREATOR;
        public Bundle a;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            this.a = parcel.readBundle(classLoader);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.a);
        }

        static {
            CREATOR = ParcelableCompat.newCreator(new af());
        }
    }

    public static interface a {
        boolean a();
    }

    static {
        a = new int[]{16842912};
        b = new int[]{-16842910};
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationView(Context context, AttributeSet attributeSet, int i) {
        ColorStateList colorStateList;
        int resourceId;
        boolean z;
        super(context, attributeSet, i);
        this.d = new b();
        be.a(context);
        this.c = new android.support.design.internal.a(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.NavigationView, i, R.style.Widget_Design_NavigationView);
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(R.styleable.NavigationView_android_background));
        if (obtainStyledAttributes.hasValue(R.styleable.NavigationView_elevation)) {
            ViewCompat.setElevation(this, (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.NavigationView_elevation, 0));
        }
        ViewCompat.setFitsSystemWindows(this, obtainStyledAttributes.getBoolean(R.styleable.NavigationView_android_fitsSystemWindows, false));
        this.f = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NavigationView_android_maxWidth, 0);
        if (obtainStyledAttributes.hasValue(R.styleable.NavigationView_itemIconTint)) {
            colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.NavigationView_itemIconTint);
        } else {
            colorStateList = a(16842808);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.NavigationView_itemTextAppearance)) {
            resourceId = obtainStyledAttributes.getResourceId(R.styleable.NavigationView_itemTextAppearance, 0);
            z = true;
        } else {
            resourceId = 0;
            z = false;
        }
        ColorStateList colorStateList2 = null;
        if (obtainStyledAttributes.hasValue(R.styleable.NavigationView_itemTextColor)) {
            colorStateList2 = obtainStyledAttributes.getColorStateList(R.styleable.NavigationView_itemTextColor);
        }
        if (!z && r5 == null) {
            colorStateList2 = a(16842806);
        }
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.NavigationView_itemBackground);
        this.c.a(new ae(this));
        this.d.d = 1;
        this.d.a(context, this.c);
        this.d.a(colorStateList);
        if (z) {
            this.d.a(resourceId);
        }
        this.d.b(colorStateList2);
        this.d.a(drawable);
        this.c.a(this.d);
        b bVar = this.d;
        if (bVar.a == null) {
            bVar.a = (NavigationMenuView) bVar.f.inflate(R.layout.design_navigation_menu, this, false);
            if (bVar.e == null) {
                bVar.e = new b();
            }
            bVar.b = (LinearLayout) bVar.f.inflate(R.layout.design_navigation_item_header, bVar.a, false);
            bVar.a.setAdapter(bVar.e);
        }
        addView(bVar.a);
        if (obtainStyledAttributes.hasValue(R.styleable.NavigationView_menu)) {
            int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.NavigationView_menu, 0);
            this.d.b(true);
            getMenuInflater().inflate(resourceId2, this.c);
            this.d.b(false);
            this.d.a(false);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.NavigationView_headerLayout)) {
            resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.NavigationView_headerLayout, 0);
            bVar = this.d;
            bVar.b.addView(bVar.f.inflate(resourceId2, bVar.b, false));
            bVar.a.setPadding(0, 0, 0, bVar.a.getPaddingBottom());
        }
        obtainStyledAttributes.recycle();
    }

    protected Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = new Bundle();
        this.c.a(savedState.a);
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            f fVar = this.c;
            SparseArray sparseParcelableArray = savedState.a.getSparseParcelableArray("android:menu:presenters");
            if (sparseParcelableArray != null && !fVar.j.isEmpty()) {
                Iterator it = fVar.j.iterator();
                while (it.hasNext()) {
                    WeakReference weakReference = (WeakReference) it.next();
                    m mVar = (m) weakReference.get();
                    if (mVar == null) {
                        fVar.j.remove(weakReference);
                    } else {
                        int b = mVar.b();
                        if (b > 0) {
                            Parcelable parcelable2 = (Parcelable) sparseParcelableArray.get(b);
                            if (parcelable2 != null) {
                                mVar.a(parcelable2);
                            }
                        }
                    }
                }
                return;
            }
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void setNavigationItemSelectedListener(a aVar) {
        this.e = aVar;
    }

    protected void onMeasure(int i, int i2) {
        switch (MeasureSpec.getMode(i)) {
            case ExploreByTouchHelper.INVALID_ID:
                i = MeasureSpec.makeMeasureSpec(Math.min(MeasureSpec.getSize(i), this.f), 1073741824);
                break;
            case SpdyAgent.ACCS_TEST_SERVER:
                i = MeasureSpec.makeMeasureSpec(this.f, 1073741824);
                break;
        }
        super.onMeasure(i, i2);
    }

    protected final void a(Rect rect) {
        b bVar = this.d;
        int i = rect.top;
        if (bVar.l != i) {
            bVar.l = i;
            if (bVar.b.getChildCount() == 0) {
                bVar.a.setPadding(0, bVar.l, 0, bVar.a.getPaddingBottom());
            }
        }
    }

    public Menu getMenu() {
        return this.c;
    }

    public int getHeaderCount() {
        return this.d.b.getChildCount();
    }

    public ColorStateList getItemIconTintList() {
        return this.d.j;
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.d.a(colorStateList);
    }

    public ColorStateList getItemTextColor() {
        return this.d.i;
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.d.b(colorStateList);
    }

    public Drawable getItemBackground() {
        return this.d.k;
    }

    public void setItemBackgroundResource(int i) {
        setItemBackground(ContextCompat.getDrawable(getContext(), i));
    }

    public void setItemBackground(Drawable drawable) {
        this.d.a(drawable);
    }

    public void setCheckedItem(int i) {
        MenuItem findItem = this.c.findItem(i);
        if (findItem != null) {
            this.d.e.a((h) findItem);
        }
    }

    public void setItemTextAppearance(int i) {
        this.d.a(i);
    }

    private MenuInflater getMenuInflater() {
        if (this.g == null) {
            this.g = new g(getContext());
        }
        return this.g;
    }

    private ColorStateList a(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateList = getResources().getColorStateList(typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = colorStateList.getDefaultColor();
        return new ColorStateList(new int[][]{b, a, EMPTY_STATE_SET}, new int[]{colorStateList.getColorForState(b, defaultColor), i2, defaultColor});
    }
}
