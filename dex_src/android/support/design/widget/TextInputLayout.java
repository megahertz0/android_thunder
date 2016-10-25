package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.InsetDrawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.design.R;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.Space;
import android.support.v7.widget.ao;
import android.support.v7.widget.r;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alipay.sdk.util.h;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.Message;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class TextInputLayout extends LinearLayout {
    private EditText a;
    private boolean b;
    private CharSequence c;
    private Paint d;
    private LinearLayout e;
    private int f;
    private boolean g;
    private TextView h;
    private int i;
    private boolean j;
    private CharSequence k;
    private boolean l;
    private TextView m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    private ColorStateList r;
    private ColorStateList s;
    private final l t;
    private boolean u;
    private bf v;
    private boolean w;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        CharSequence a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.a = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.a, parcel, i);
        }

        public String toString() {
            return new StringBuilder("TextInputLayout.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" error=").append(this.a).append(h.d).toString();
        }

        static {
            CREATOR = new bd();
        }
    }

    private class a extends AccessibilityDelegateCompat {
        private a() {
        }

        public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(TextInputLayout.class.getSimpleName());
        }

        public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            CharSequence charSequence = TextInputLayout.this.t.i;
            if (!TextUtils.isEmpty(charSequence)) {
                accessibilityEvent.getText().add(charSequence);
            }
        }

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName(TextInputLayout.class.getSimpleName());
            CharSequence charSequence = TextInputLayout.this.t.i;
            if (!TextUtils.isEmpty(charSequence)) {
                accessibilityNodeInfoCompat.setText(charSequence);
            }
            if (TextInputLayout.this != null) {
                accessibilityNodeInfoCompat.setLabelFor(TextInputLayout.this);
            }
            charSequence = TextInputLayout.this.h != null ? TextInputLayout.this.h.getText() : null;
            if (!TextUtils.isEmpty(charSequence)) {
                accessibilityNodeInfoCompat.setContentInvalid(true);
                accessibilityNodeInfoCompat.setError(charSequence);
            }
        }
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextInputLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.t = new l(this);
        be.a(context);
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        this.t.a(a.b);
        l lVar = this.t;
        lVar.j = new AccelerateInterpolator();
        lVar.b();
        this.t.d(8388659);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TextInputLayout, i, R.style.Widget_Design_TextInputLayout);
        this.b = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_hintEnabled, true);
        setHint(obtainStyledAttributes.getText(R.styleable.TextInputLayout_android_hint));
        this.u = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_hintAnimationEnabled, true);
        if (obtainStyledAttributes.hasValue(R.styleable.TextInputLayout_android_textColorHint)) {
            ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.TextInputLayout_android_textColorHint);
            this.s = colorStateList;
            this.r = colorStateList;
        }
        if (obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
            setHintTextAppearance(obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, 0));
        }
        this.i = obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, 0);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_errorEnabled, false);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.TextInputLayout_counterEnabled, false);
        setCounterMaxLength(obtainStyledAttributes.getInt(R.styleable.TextInputLayout_counterMaxLength, -1));
        this.o = obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_counterTextAppearance, 0);
        this.p = obtainStyledAttributes.getResourceId(R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        obtainStyledAttributes.recycle();
        setErrorEnabled(z);
        setCounterEnabled(z2);
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        ViewCompat.setAccessibilityDelegate(this, new a());
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        if (view instanceof EditText) {
            setEditText((EditText) view);
            super.addView(view, 0, a(layoutParams));
            return;
        }
        super.addView(view, i, layoutParams);
    }

    public void setTypeface(Typeface typeface) {
        this.t.a(typeface);
    }

    public Typeface getTypeface() {
        return this.t.a();
    }

    private void setEditText(EditText editText) {
        if (this.a != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        this.a = editText;
        this.t.a(this.a.getTypeface());
        l lVar = this.t;
        float textSize = this.a.getTextSize();
        if (lVar.d != textSize) {
            lVar.d = textSize;
            lVar.b();
        }
        int gravity = this.a.getGravity();
        this.t.d((8388615 & gravity) | 48);
        this.t.c(gravity);
        this.a.addTextChangedListener(new az(this));
        if (this.r == null) {
            this.r = this.a.getHintTextColors();
        }
        if (this.b && TextUtils.isEmpty(this.c)) {
            setHint(this.a.getHint());
            this.a.setHint(null);
        }
        if (this.m != null) {
            a(this.a.getText().length());
        }
        if (this.e != null) {
            a();
        }
        a(false);
    }

    private LinearLayout.LayoutParams a(LayoutParams layoutParams) {
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
        } else {
            layoutParams = new LinearLayout.LayoutParams(layoutParams);
        }
        if (this.b) {
            if (this.d == null) {
                this.d = new Paint();
            }
            this.d.setTypeface(this.t.a());
            this.d.setTextSize(this.t.e);
            layoutParams.topMargin = (int) (-this.d.ascent());
        } else {
            layoutParams.topMargin = 0;
        }
        return layoutParams;
    }

    private void a(boolean z) {
        Object obj = 1;
        if (this.a == null || TextUtils.isEmpty(this.a.getText())) {
            Object obj2 = null;
        } else {
            int i = 1;
        }
        int[] drawableState = getDrawableState();
        int length = drawableState.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (drawableState[i2] == 16842908) {
                i2 = 1;
                break;
            }
        }
        Object obj3 = null;
        if (TextUtils.isEmpty(getError())) {
            obj = null;
        }
        if (this.r != null) {
            this.t.b(this.r.getDefaultColor());
        }
        if (this.q && this.m != null) {
            this.t.a(this.m.getCurrentTextColor());
        } else if (obj3 != null && this.s != null) {
            this.t.a(this.s.getDefaultColor());
        } else if (this.r != null) {
            this.t.a(this.r.getDefaultColor());
        }
        if (obj2 == null && obj3 == null && r1 == null) {
            if (this.v != null && this.v.a.b()) {
                this.v.a.e();
            }
            if (z && this.u) {
                a((float) AutoScrollHelper.RELATIVE_UNSPECIFIED);
                return;
            } else {
                this.t.a((float) AutoScrollHelper.RELATIVE_UNSPECIFIED);
                return;
            }
        }
        if (this.v != null && this.v.a.b()) {
            this.v.a.e();
        }
        if (z && this.u) {
            a(1.0f);
        } else {
            this.t.a(1.0f);
        }
    }

    public EditText getEditText() {
        return this.a;
    }

    public void setHint(CharSequence charSequence) {
        if (this.b) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(Message.FLAG_RET);
        }
    }

    private void setHintInternal(CharSequence charSequence) {
        this.c = charSequence;
        this.t.a(charSequence);
    }

    public CharSequence getHint() {
        return this.b ? this.c : null;
    }

    public void setHintEnabled(boolean z) {
        if (z != this.b) {
            this.b = z;
            CharSequence hint = this.a.getHint();
            if (!this.b) {
                if (!TextUtils.isEmpty(this.c) && TextUtils.isEmpty(hint)) {
                    this.a.setHint(this.c);
                }
                setHintInternal(null);
            } else if (!TextUtils.isEmpty(hint)) {
                if (TextUtils.isEmpty(this.c)) {
                    setHint(hint);
                }
                this.a.setHint(null);
            }
            if (this.a != null) {
                this.a.setLayoutParams(a(this.a.getLayoutParams()));
            }
        }
    }

    public void setHintTextAppearance(int i) {
        this.t.e(i);
        this.s = ColorStateList.valueOf(this.t.f);
        if (this.a != null) {
            a(false);
            this.a.setLayoutParams(a(this.a.getLayoutParams()));
            this.a.requestLayout();
        }
    }

    private void a(TextView textView, int i) {
        if (this.e == null) {
            this.e = new LinearLayout(getContext());
            this.e.setOrientation(0);
            addView(this.e, -1, Tabs.TAB_CREATE_REACH_MAX_COUNT);
            this.e.addView(new Space(getContext()), new LinearLayout.LayoutParams(0, 0, 1.0f));
            if (this.a != null) {
                a();
            }
        }
        this.e.setVisibility(0);
        this.e.addView(textView, i);
        this.f++;
    }

    private void a() {
        ViewCompat.setPaddingRelative(this.e, ViewCompat.getPaddingStart(this.a), 0, ViewCompat.getPaddingEnd(this.a), this.a.getPaddingBottom());
    }

    private void a(TextView textView) {
        if (this.e != null) {
            this.e.removeView(textView);
            int i = this.f - 1;
            this.f = i;
            if (i == 0) {
                this.e.setVisibility(XZBDevice.Wait);
            }
        }
    }

    public void setErrorEnabled(boolean z) {
        if (this.g != z) {
            if (this.h != null) {
                ViewCompat.animate(this.h).cancel();
            }
            if (z) {
                this.h = new TextView(getContext());
                try {
                    this.h.setTextAppearance(getContext(), this.i);
                } catch (Exception e) {
                    this.h.setTextAppearance(getContext(), R.style.TextAppearance_AppCompat_Caption);
                    this.h.setTextColor(ContextCompat.getColor(getContext(), R.color.design_textinput_error_color_light));
                }
                this.h.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                ViewCompat.setAccessibilityLiveRegion(this.h, 1);
                a(this.h, 0);
            } else {
                this.j = false;
                b();
                a(this.h);
                this.h = null;
            }
            this.g = z;
        }
    }

    public void setError(CharSequence charSequence) {
        if (!TextUtils.equals(this.k, charSequence)) {
            this.k = charSequence;
            if (!this.g) {
                if (!TextUtils.isEmpty(charSequence)) {
                    setErrorEnabled(true);
                } else {
                    return;
                }
            }
            boolean isLaidOut = ViewCompat.isLaidOut(this);
            this.j = !TextUtils.isEmpty(charSequence);
            ViewCompat.animate(this.h).cancel();
            if (this.j) {
                this.h.setText(charSequence);
                this.h.setVisibility(0);
                if (isLaidOut) {
                    if (ViewCompat.getAlpha(this.h) == 1.0f) {
                        ViewCompat.setAlpha(this.h, AutoScrollHelper.RELATIVE_UNSPECIFIED);
                    }
                    ViewCompat.animate(this.h).alpha(1.0f).setDuration(Constants.ST_UPLOAD_MAX_COUNT).setInterpolator(a.d).setListener(new ba(this)).start();
                }
            } else if (this.h.getVisibility() == 0) {
                if (isLaidOut) {
                    ViewCompat.animate(this.h).alpha(AutoScrollHelper.RELATIVE_UNSPECIFIED).setDuration(Constants.ST_UPLOAD_MAX_COUNT).setInterpolator(a.c).setListener(new bb(this, charSequence)).start();
                } else {
                    this.h.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                }
            }
            b();
            a(true);
        }
    }

    public void setCounterEnabled(boolean z) {
        if (this.l != z) {
            if (z) {
                this.m = new TextView(getContext());
                this.m.setMaxLines(1);
                try {
                    this.m.setTextAppearance(getContext(), this.o);
                } catch (Exception e) {
                    this.m.setTextAppearance(getContext(), R.style.TextAppearance_AppCompat_Caption);
                    this.m.setTextColor(ContextCompat.getColor(getContext(), R.color.design_textinput_error_color_light));
                }
                a(this.m, -1);
                if (this.a == null) {
                    a(0);
                } else {
                    a(this.a.getText().length());
                }
            } else {
                a(this.m);
                this.m = null;
            }
            this.l = z;
        }
    }

    public void setCounterMaxLength(int i) {
        if (this.n != i) {
            if (i > 0) {
                this.n = i;
            } else {
                this.n = -1;
            }
            if (this.l) {
                a(this.a == null ? 0 : this.a.getText().length());
            }
        }
    }

    public int getCounterMaxLength() {
        return this.n;
    }

    private void a(int i) {
        boolean z = this.q;
        if (this.n == -1) {
            this.m.setText(String.valueOf(i));
            this.q = false;
        } else {
            this.q = i > this.n;
            if (z != this.q) {
                this.m.setTextAppearance(getContext(), this.q ? this.p : this.o);
            }
            this.m.setText(getContext().getString(R.string.character_counter_pattern, new Object[]{Integer.valueOf(i), Integer.valueOf(this.n)}));
        }
        if (this.a != null && z != this.q) {
            a(false);
            b();
        }
    }

    private static void a(Drawable drawable) {
        Drawable drawable2 = drawable;
        while (true) {
            drawable2.clearColorFilter();
            if (VERSION.SDK_INT == 21 || VERSION.SDK_INT == 22) {
                if (!(drawable2 instanceof InsetDrawable)) {
                    if (!(drawable2 instanceof DrawableWrapper)) {
                        break;
                    }
                    drawable2 = ((DrawableWrapper) drawable2).getWrappedDrawable();
                } else {
                    drawable2 = ((InsetDrawable) drawable2).getDrawable();
                }
            } else {
                return;
            }
        }
        if (drawable2 instanceof DrawableContainer) {
            DrawableContainerState drawableContainerState = (DrawableContainerState) ((DrawableContainer) drawable2).getConstantState();
            if (drawableContainerState != null) {
                int childCount = drawableContainerState.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    a(drawableContainerState.getChild(i));
                }
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        if (this.j) {
            savedState.a = getError();
        }
        return savedState;
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            setError(savedState.a);
            requestLayout();
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public CharSequence getError() {
        return this.g ? this.k : null;
    }

    public void setHintAnimationEnabled(boolean z) {
        this.u = z;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.b) {
            this.t.a(canvas);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.b && this.a != null) {
            int left = this.a.getLeft() + this.a.getCompoundPaddingLeft();
            int right = this.a.getRight() - this.a.getCompoundPaddingRight();
            this.t.a(left, this.a.getTop() + this.a.getCompoundPaddingTop(), right, this.a.getBottom() - this.a.getCompoundPaddingBottom());
            this.t.b(left, getPaddingTop(), right, (i4 - i2) - getPaddingBottom());
            this.t.b();
        }
    }

    public void refreshDrawableState() {
        super.refreshDrawableState();
        a(ViewCompat.isLaidOut(this));
    }

    private void a(float f) {
        if (this.t.a != f) {
            if (this.v == null) {
                this.v = bq.a();
                this.v.a(a.a);
                this.v.a((int) Impl.STATUS_SUCCESS);
                this.v.a(new bc(this));
            }
            this.v.a(this.t.a, f);
            this.v.a.a();
        }
    }

    private void b() {
        Drawable background;
        int i = VERSION.SDK_INT;
        if (i == 21 || i == 22) {
            background = this.a.getBackground();
            if (!(background == null || this.w)) {
                Drawable newDrawable = background.getConstantState().newDrawable();
                if (background instanceof DrawableContainer) {
                    boolean a;
                    DrawableContainer drawableContainer = (DrawableContainer) background;
                    ConstantState constantState = newDrawable.getConstantState();
                    if (VERSION.SDK_INT >= 9) {
                        a = s.a(drawableContainer, constantState);
                    } else {
                        a = s.b(drawableContainer, constantState);
                    }
                    this.w = a;
                }
                if (!this.w) {
                    this.a.setBackgroundDrawable(newDrawable);
                    this.w = true;
                }
            }
        }
        background = this.a.getBackground();
        if (background != null) {
            if (ao.c(background)) {
                background = background.mutate();
            }
            if (this.j && this.h != null) {
                background.setColorFilter(r.a(this.h.getCurrentTextColor(), Mode.SRC_IN));
            } else if (!this.q || this.m == null) {
                a(background);
                this.a.refreshDrawableState();
            } else {
                background.setColorFilter(r.a(this.m.getCurrentTextColor(), Mode.SRC_IN));
            }
        }
    }
}
