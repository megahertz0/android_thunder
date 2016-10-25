package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.d;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import com.uc.addon.sdk.remote.Tabs;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
    private static final boolean a;
    private static final boolean b;
    private static final int[] c;
    private r d;
    private p e;
    private Context f;
    private android.support.v7.widget.ListPopupWindow.b g;
    private SpinnerAdapter h;
    private boolean i;
    private b j;
    private int k;
    private final Rect l;

    private static class a implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter a;
        private ListAdapter b;

        public a(SpinnerAdapter spinnerAdapter, Theme theme) {
            this.a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.b = (ListAdapter) spinnerAdapter;
            }
            if (theme != null && a && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                    themedSpinnerAdapter.setDropDownViewTheme(theme);
                }
            }
        }

        public final int getCount() {
            return this.a == null ? 0 : this.a.getCount();
        }

        public final Object getItem(int i) {
            return this.a == null ? null : this.a.getItem(i);
        }

        public final long getItemId(int i) {
            return this.a == null ? -1 : this.a.getItemId(i);
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        public final View getDropDownView(int i, View view, ViewGroup viewGroup) {
            return this.a == null ? null : this.a.getDropDownView(i, view, viewGroup);
        }

        public final boolean hasStableIds() {
            return this.a != null && this.a.hasStableIds();
        }

        public final void registerDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.a != null) {
                this.a.registerDataSetObserver(dataSetObserver);
            }
        }

        public final void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.a != null) {
                this.a.unregisterDataSetObserver(dataSetObserver);
            }
        }

        public final boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.b;
            return listAdapter != null ? listAdapter.areAllItemsEnabled() : true;
        }

        public final boolean isEnabled(int i) {
            ListAdapter listAdapter = this.b;
            return listAdapter != null ? listAdapter.isEnabled(i) : true;
        }

        public final int getItemViewType(int i) {
            return 0;
        }

        public final int getViewTypeCount() {
            return 1;
        }

        public final boolean isEmpty() {
            return getCount() == 0;
        }
    }

    private class b extends ListPopupWindow {
        CharSequence a;
        private ListAdapter n;
        private final Rect o;

        public b(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.o = new Rect();
            this.l = AppCompatSpinner.this;
            c();
            this.k = 0;
            this.m = new x(this, AppCompatSpinner.this);
        }

        public final void a(ListAdapter listAdapter) {
            super.a(listAdapter);
            this.n = listAdapter;
        }

        final void a() {
            int i;
            int i2;
            Drawable background = this.c.getBackground();
            if (background != null) {
                background.getPadding(AppCompatSpinner.this.l);
                i = cw.a(AppCompatSpinner.this) ? AppCompatSpinner.this.l.right : -AppCompatSpinner.this.l.left;
            } else {
                Rect b = AppCompatSpinner.this.l;
                AppCompatSpinner.this.l.right = 0;
                b.left = 0;
                i = 0;
            }
            int paddingLeft = AppCompatSpinner.this.getPaddingLeft();
            int paddingRight = AppCompatSpinner.this.getPaddingRight();
            int width = AppCompatSpinner.this.getWidth();
            if (AppCompatSpinner.this.k == -2) {
                int a = AppCompatSpinner.this.a((SpinnerAdapter) this.n, this.c.getBackground());
                i2 = (AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels - AppCompatSpinner.this.l.left) - AppCompatSpinner.this.l.right;
                if (a <= i2) {
                    i2 = a;
                }
                a(Math.max(i2, (width - paddingLeft) - paddingRight));
            } else if (AppCompatSpinner.this.k == -1) {
                a((width - paddingLeft) - paddingRight);
            } else {
                a(AppCompatSpinner.this.k);
            }
            if (cw.a(AppCompatSpinner.this)) {
                i2 = ((width - paddingRight) - this.e) + i;
            } else {
                i2 = i + paddingLeft;
            }
            this.f = i2;
        }

        public final void b() {
            boolean isShowing = this.c.isShowing();
            a();
            e();
            super.b();
            this.d.setChoiceMode(1);
            int selectedItemPosition = AppCompatSpinner.this.getSelectedItemPosition();
            a aVar = this.d;
            if (this.c.isShowing() && aVar != null) {
                a.a(aVar, false);
                aVar.setSelection(selectedItemPosition);
                if (VERSION.SDK_INT >= 11 && aVar.getChoiceMode() != 0) {
                    aVar.setItemChecked(selectedItemPosition, true);
                }
            }
            if (!isShowing) {
                ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    OnGlobalLayoutListener yVar = new y(this);
                    viewTreeObserver.addOnGlobalLayoutListener(yVar);
                    a(new z(this, yVar));
                }
            }
        }

        static /* synthetic */ boolean a(b bVar, View view) {
            return ViewCompat.isAttachedToWindow(view) && view.getGlobalVisibleRect(bVar.o);
        }
    }

    static {
        boolean z;
        if (VERSION.SDK_INT >= 23) {
            z = true;
        } else {
            z = false;
        }
        a = z;
        if (VERSION.SDK_INT >= 16) {
            z = true;
        } else {
            z = false;
        }
        b = z;
        c = new int[]{16843505};
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.spinnerStyle);
    }

    public AppCompatSpinner(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, (byte) 0);
    }

    private AppCompatSpinner(Context context, AttributeSet attributeSet, int i, byte b) {
        this(context, attributeSet, i, -1);
    }

    private AppCompatSpinner(Context context, AttributeSet attributeSet, int i, int i2) {
        Context dVar;
        AppCompatSpinner appCompatSpinner;
        TypedArray obtainStyledAttributes;
        b bVar;
        cm a;
        CharSequence[] textArray;
        SpinnerAdapter arrayAdapter;
        Throwable th;
        TypedArray typedArray = null;
        super(context, attributeSet, i);
        this.l = new Rect();
        cm a2 = cm.a(context, attributeSet, R.styleable.Spinner, i);
        this.d = r.a();
        this.e = new p(this, this.d);
        int e = a2.e(R.styleable.Spinner_popupTheme, 0);
        if (e != 0) {
            dVar = new d(context, e);
            appCompatSpinner = this;
        } else if (a) {
            dVar = null;
            appCompatSpinner = this;
        } else {
            dVar = context;
            appCompatSpinner = this;
        }
        appCompatSpinner.f = dVar;
        if (this.f != null) {
            if (VERSION.SDK_INT >= 11) {
                try {
                    obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c, i, 0);
                } catch (Exception e2) {
                    obtainStyledAttributes = null;
                    if (obtainStyledAttributes != null) {
                        obtainStyledAttributes.recycle();
                    }
                    if (i2 == 1) {
                        bVar = new b(this.f, attributeSet, i);
                        a = cm.a(this.f, attributeSet, R.styleable.Spinner, i);
                        this.k = a.d(R.styleable.Spinner_android_dropDownWidth, Tabs.TAB_CREATE_REACH_MAX_COUNT);
                        bVar.a(a.a(R.styleable.Spinner_android_popupBackground));
                        bVar.a = a2.a.getString(R.styleable.Spinner_android_prompt);
                        a.a.recycle();
                        this.j = bVar;
                        this.g = new w(this, this, bVar);
                    }
                    textArray = a2.a.getTextArray(R.styleable.Spinner_android_entries);
                    if (textArray != null) {
                        arrayAdapter = new ArrayAdapter(context, 17367048, textArray);
                        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        setAdapter(arrayAdapter);
                    }
                    a2.a.recycle();
                    this.i = true;
                    if (this.h != null) {
                        setAdapter(this.h);
                        this.h = null;
                    }
                    this.e.a(attributeSet, i);
                } catch (Throwable th2) {
                    th = th2;
                    if (typedArray != null) {
                        typedArray.recycle();
                    }
                    throw th;
                }
                try {
                    if (obtainStyledAttributes.hasValue(0)) {
                        i2 = obtainStyledAttributes.getInt(0, 0);
                    }
                    if (obtainStyledAttributes != null) {
                        obtainStyledAttributes.recycle();
                    }
                } catch (Exception e3) {
                    if (obtainStyledAttributes != null) {
                        obtainStyledAttributes.recycle();
                    }
                    if (i2 == 1) {
                        bVar = new b(this.f, attributeSet, i);
                        a = cm.a(this.f, attributeSet, R.styleable.Spinner, i);
                        this.k = a.d(R.styleable.Spinner_android_dropDownWidth, Tabs.TAB_CREATE_REACH_MAX_COUNT);
                        bVar.a(a.a(R.styleable.Spinner_android_popupBackground));
                        bVar.a = a2.a.getString(R.styleable.Spinner_android_prompt);
                        a.a.recycle();
                        this.j = bVar;
                        this.g = new w(this, this, bVar);
                    }
                    textArray = a2.a.getTextArray(R.styleable.Spinner_android_entries);
                    if (textArray != null) {
                        arrayAdapter = new ArrayAdapter(context, 17367048, textArray);
                        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        setAdapter(arrayAdapter);
                    }
                    a2.a.recycle();
                    this.i = true;
                    if (this.h != null) {
                        setAdapter(this.h);
                        this.h = null;
                    }
                    this.e.a(attributeSet, i);
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    typedArray = obtainStyledAttributes;
                    th = th4;
                    if (typedArray != null) {
                        typedArray.recycle();
                    }
                    throw th;
                }
            }
            i2 = 1;
            if (i2 == 1) {
                bVar = new b(this.f, attributeSet, i);
                a = cm.a(this.f, attributeSet, R.styleable.Spinner, i);
                this.k = a.d(R.styleable.Spinner_android_dropDownWidth, Tabs.TAB_CREATE_REACH_MAX_COUNT);
                bVar.a(a.a(R.styleable.Spinner_android_popupBackground));
                bVar.a = a2.a.getString(R.styleable.Spinner_android_prompt);
                a.a.recycle();
                this.j = bVar;
                this.g = new w(this, this, bVar);
            }
        }
        textArray = a2.a.getTextArray(R.styleable.Spinner_android_entries);
        if (textArray != null) {
            arrayAdapter = new ArrayAdapter(context, 17367048, textArray);
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            setAdapter(arrayAdapter);
        }
        a2.a.recycle();
        this.i = true;
        if (this.h != null) {
            setAdapter(this.h);
            this.h = null;
        }
        this.e.a(attributeSet, i);
    }

    public Context getPopupContext() {
        if (this.j != null) {
            return this.f;
        }
        return a ? super.getPopupContext() : null;
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        if (this.j != null) {
            this.j.a(drawable);
        } else if (b) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    public void setPopupBackgroundResource(int i) {
        setPopupBackgroundDrawable(ContextCompat.getDrawable(getPopupContext(), i));
    }

    public Drawable getPopupBackground() {
        if (this.j != null) {
            return this.j.c.getBackground();
        }
        return b ? super.getPopupBackground() : null;
    }

    public void setDropDownVerticalOffset(int i) {
        if (this.j != null) {
            ListPopupWindow listPopupWindow = this.j;
            listPopupWindow.g = i;
            listPopupWindow.h = true;
        } else if (b) {
            super.setDropDownVerticalOffset(i);
        }
    }

    public int getDropDownVerticalOffset() {
        if (this.j == null) {
            return b ? super.getDropDownVerticalOffset() : 0;
        } else {
            ListPopupWindow listPopupWindow = this.j;
            return !listPopupWindow.h ? 0 : listPopupWindow.g;
        }
    }

    public void setDropDownHorizontalOffset(int i) {
        if (this.j != null) {
            this.j.f = i;
        } else if (b) {
            super.setDropDownHorizontalOffset(i);
        }
    }

    public int getDropDownHorizontalOffset() {
        if (this.j != null) {
            return this.j.f;
        }
        return b ? super.getDropDownHorizontalOffset() : 0;
    }

    public void setDropDownWidth(int i) {
        if (this.j != null) {
            this.k = i;
        } else if (b) {
            super.setDropDownWidth(i);
        }
    }

    public int getDropDownWidth() {
        if (this.j != null) {
            return this.k;
        }
        return b ? super.getDropDownWidth() : 0;
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (this.i) {
            super.setAdapter(spinnerAdapter);
            if (this.j != null) {
                this.j.a(new a(spinnerAdapter, (this.f == null ? getContext() : this.f).getTheme()));
                return;
            }
            return;
        }
        this.h = spinnerAdapter;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.j != null && this.j.c.isShowing()) {
            this.j.d();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return (this.g == null || !this.g.onTouch(this, motionEvent)) ? super.onTouchEvent(motionEvent) : true;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.j != null && MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    public boolean performClick() {
        if (this.j == null) {
            return super.performClick();
        }
        if (!this.j.c.isShowing()) {
            this.j.b();
        }
        return true;
    }

    public void setPrompt(CharSequence charSequence) {
        if (this.j != null) {
            this.j.a = charSequence;
        } else {
            super.setPrompt(charSequence);
        }
    }

    public CharSequence getPrompt() {
        return this.j != null ? this.j.a : super.getPrompt();
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        if (this.e != null) {
            this.e.a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.e != null) {
            this.e.b(null);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.e != null) {
            this.e.a(colorStateList);
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        return this.e != null ? this.e.a() : null;
    }

    public void setSupportBackgroundTintMode(Mode mode) {
        if (this.e != null) {
            this.e.a(mode);
        }
    }

    public Mode getSupportBackgroundTintMode() {
        return this.e != null ? this.e.b() : null;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.e != null) {
            this.e.c();
        }
    }

    private int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        int max2 = Math.max(0, max - (15 - (min - max)));
        View view = null;
        int i = 0;
        max = 0;
        while (max2 < min) {
            View view2;
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != max) {
                view2 = null;
            } else {
                itemViewType = max;
                view2 = view;
            }
            view = spinnerAdapter.getView(max2, view2, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i = Math.max(i, view.getMeasuredWidth());
            max2++;
            max = itemViewType;
        }
        if (drawable == null) {
            return i;
        }
        drawable.getPadding(this.l);
        return (this.l.left + this.l.right) + i;
    }
}
