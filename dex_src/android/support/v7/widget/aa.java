package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.appcompat.R;
import android.support.v7.b.a;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: AppCompatTextHelper.java
class aa {
    private static final int[] b;
    private static final int[] c;
    final TextView a;
    private ck d;
    private ck e;
    private ck f;
    private ck g;

    static aa a(TextView textView) {
        return VERSION.SDK_INT >= 17 ? new ab(textView) : new aa(textView);
    }

    static {
        b = new int[]{16842804, 16843119, 16843117, 16843120, 16843118};
        c = new int[]{R.attr.textAllCaps};
    }

    aa(TextView textView) {
        this.a = textView;
    }

    void a(AttributeSet attributeSet, int i) {
        int i2 = 1;
        Context context = this.a.getContext();
        r a = r.a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b, i, 0);
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        if (obtainStyledAttributes.hasValue(1)) {
            this.d = a(context, a, obtainStyledAttributes.getResourceId(1, 0));
        }
        if (obtainStyledAttributes.hasValue(XZBDevice.DOWNLOAD_LIST_RECYCLE)) {
            this.e = a(context, a, obtainStyledAttributes.getResourceId(XZBDevice.DOWNLOAD_LIST_RECYCLE, 0));
        }
        if (obtainStyledAttributes.hasValue(XZBDevice.DOWNLOAD_LIST_FAILED)) {
            this.f = a(context, a, obtainStyledAttributes.getResourceId(XZBDevice.DOWNLOAD_LIST_FAILED, 0));
        }
        if (obtainStyledAttributes.hasValue(XZBDevice.DOWNLOAD_LIST_ALL)) {
            this.g = a(context, a, obtainStyledAttributes.getResourceId(XZBDevice.DOWNLOAD_LIST_ALL, 0));
        }
        obtainStyledAttributes.recycle();
        if (!(this.a.getTransformationMethod() instanceof PasswordTransformationMethod)) {
            boolean z;
            int i3;
            boolean z2;
            if (resourceId != -1) {
                TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, R.styleable.TextAppearance);
                if (obtainStyledAttributes2.hasValue(R.styleable.TextAppearance_textAllCaps)) {
                    z = obtainStyledAttributes2.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
                    i3 = 1;
                } else {
                    z2 = false;
                    z = false;
                }
                obtainStyledAttributes2.recycle();
            } else {
                z2 = false;
                z = false;
            }
            TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(attributeSet, c, i, 0);
            if (obtainStyledAttributes3.hasValue(0)) {
                z = obtainStyledAttributes3.getBoolean(0, false);
            } else {
                i2 = i3;
            }
            obtainStyledAttributes3.recycle();
            if (i2 != 0) {
                a(z);
            }
        }
    }

    final void a(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, c);
        if (obtainStyledAttributes.hasValue(0)) {
            a(obtainStyledAttributes.getBoolean(0, false));
        }
        obtainStyledAttributes.recycle();
    }

    final void a(boolean z) {
        this.a.setTransformationMethod(z ? new a(this.a.getContext()) : null);
    }

    void a() {
        if (this.d != null || this.e != null || this.f != null || this.g != null) {
            Drawable[] compoundDrawables = this.a.getCompoundDrawables();
            a(compoundDrawables[0], this.d);
            a(compoundDrawables[1], this.e);
            a(compoundDrawables[2], this.f);
            a(compoundDrawables[3], this.g);
        }
    }

    final void a(Drawable drawable, ck ckVar) {
        if (drawable != null && ckVar != null) {
            r.a(drawable, ckVar, this.a.getDrawableState());
        }
    }

    protected static ck a(Context context, r rVar, int i) {
        ColorStateList b = rVar.b(context, i);
        if (b == null) {
            return null;
        }
        ck ckVar = new ck();
        ckVar.d = true;
        ckVar.a = b;
        return ckVar;
    }
}
