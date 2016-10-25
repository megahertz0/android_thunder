package com.android.volley.toolbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.android.volley.Request;
import com.android.volley.toolbox.i.c;
import com.android.volley.toolbox.i.d;

public class NetworkImageView extends ImageView {
    private String a;
    private int b;
    private int c;
    private i d;
    private c e;

    public NetworkImageView(Context context) {
        this(context, null);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public final void a(String str, i iVar) {
        this.a = str;
        this.d = iVar;
        a(false);
    }

    public void setDefaultImageResId(int i) {
        this.b = i;
    }

    public void setErrorImageResId(int i) {
        this.c = i;
    }

    private void a(boolean z) {
        Object obj;
        Object obj2;
        int width = getWidth();
        int height = getHeight();
        ScaleType scaleType = getScaleType();
        Object obj3 = null;
        if (getLayoutParams() != null) {
            obj = getLayoutParams().width == -2 ? 1 : null;
            obj3 = getLayoutParams().height == -2 ? 1 : null;
            obj2 = obj;
        } else {
            obj2 = null;
        }
        obj = (obj2 == null || obj3 == null) ? null : 1;
        if (width != 0 || height != 0 || obj != null) {
            if (TextUtils.isEmpty(this.a)) {
                if (this.e != null) {
                    this.e.a();
                    this.e = null;
                }
                a();
                return;
            }
            if (!(this.e == null || this.e.c == null)) {
                if (!this.e.c.equals(this.a)) {
                    this.e.a();
                    a();
                } else {
                    return;
                }
            }
            if (obj2 != null) {
                width = 0;
            }
            if (obj3 != null) {
                height = 0;
            }
            i iVar = this.d;
            String str = this.a;
            d qVar = new q(this, z);
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
            }
            c cVar;
            String toString = new StringBuilder(str.length() + 12).append("#W").append(width).append("#H").append(height).append("#S").append(scaleType.ordinal()).append(str).toString();
            Bitmap a = iVar.b.a(toString);
            if (a != null) {
                cVar = new c(iVar, a, str, null, null);
                qVar.a(cVar, true);
            } else {
                cVar = new c(iVar, null, str, toString, qVar);
                qVar.a(cVar, true);
                a aVar = (a) iVar.c.get(toString);
                if (aVar != null) {
                    aVar.c.add(cVar);
                } else {
                    Request mVar = new m(str, new j(iVar, toString), width, height, scaleType, Config.RGB_565, new k(iVar, toString));
                    iVar.a.a(mVar);
                    iVar.c.put(toString, new a(iVar, mVar, cVar));
                }
            }
            this.e = cVar;
        }
    }

    private void a() {
        if (this.b != 0) {
            setImageResource(this.b);
        } else {
            setImageBitmap(null);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a(true);
    }

    protected void onDetachedFromWindow() {
        if (this.e != null) {
            this.e.a();
            setImageBitmap(null);
            this.e = null;
        }
        super.onDetachedFromWindow();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
