package com.xunlei.downloadprovider.xlui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;

public class ZHTextView extends TextView {
    private b a;
    private CharSequence b;
    private boolean c;
    private float d;

    private static class a {
        public int a;
        public int b;

        private a() {
        }
    }

    private static class b {
        public ArrayList<a> a;
        public CharSequence b;
        public int c;
        public float d;

        private b() {
            this.a = new ArrayList();
            this.b = null;
        }
    }

    public ZHTextView(Context context) {
        super(context);
        this.c = true;
        this.d = 0.0f;
        a(null, 0);
    }

    public ZHTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = true;
        this.d = 0.0f;
        a(attributeSet, 0);
    }

    public ZHTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = true;
        this.d = 0.0f;
        a(attributeSet, 0);
    }

    @TargetApi(21)
    public ZHTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.c = true;
        this.d = 0.0f;
        a(attributeSet, i2);
    }

    public float getTextIndentPadding() {
        return this.d;
    }

    public void setTextIndentPadding(float f) {
        this.d = f;
        if (VERSION.SDK_INT < 18 || !isInLayout()) {
            requestLayout();
        }
        invalidate();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onDraw(android.graphics.Canvas r22) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.xlui.widget.ZHTextView.onDraw(android.graphics.Canvas):void");
        /*
        this = this;
        r0 = r21;
        r1 = r0.c;
        if (r1 == 0) goto L_0x01c2;
    L_0x0006:
        r0 = r21;
        r1 = r0.b;
        if (r1 == 0) goto L_0x002e;
    L_0x000c:
        r0 = r21;
        r1 = r0.b;
        r0 = r21;
        r2 = r0.a;
        r2 = r2.b;
        r1 = r1.equals(r2);
        if (r1 != 0) goto L_0x002e;
    L_0x001c:
        r0 = r21;
        r1 = r0.b;
        r0 = r21;
        r2 = r0.a;
        r2 = r2.c;
        r0 = r21;
        r0.a(r1, r2);
        r21.invalidate();
    L_0x002e:
        r22.save();
        r7 = r21.getPaint();
        r1 = r21.getCurrentTextColor();
        r7.setColor(r1);
        r1 = r7.ascent();
        r12 = (int) r1;
        r1 = r7.descent();
        r13 = (int) r1;
        r2 = 1;
        r1 = 0;
        r3 = android.os.Build.VERSION.SDK_INT;
        r4 = 16;
        if (r3 < r4) goto L_0x01c6;
    L_0x004e:
        r2 = r21.getMaxLines();
        r1 = r21.getLineSpacingExtra();
        r1 = (int) r1;
        r8 = r1;
        r1 = r2;
    L_0x0059:
        r2 = 0;
        r0 = r21;
        r3 = r0.a;
        r3 = r3.a;
        r3 = r3.size();
        r1 = java.lang.Math.min(r1, r3);
        r14 = java.lang.Math.max(r2, r1);
        r1 = r14 + -1;
        r1 = r1 * r8;
        r2 = -r12;
        r2 = r2 + r13;
        r2 = r2 * r14;
        r1 = r1 + r2;
        r2 = r21.getGravity();
        r3 = r2 & 16;
        if (r3 == 0) goto L_0x00fb;
    L_0x007b:
        r2 = r21.getPaddingTop();
        r3 = r21.getMeasuredHeight();
        r4 = r21.getPaddingBottom();
        r3 = r3 - r4;
        r4 = r21.getPaddingTop();
        r3 = r3 - r4;
        r1 = r3 - r1;
        r1 = r1 / 2;
        r1 = r1 + r2;
        r9 = r1;
    L_0x0093:
        r1 = r21.getEllipsize();
        if (r1 == 0) goto L_0x0112;
    L_0x0099:
        r1 = 1;
        r10 = r1;
    L_0x009b:
        r0 = r21;
        r1 = r0.a;
        r1 = r1.b;
        if (r1 == 0) goto L_0x01be;
    L_0x00a3:
        r1 = 0;
        r11 = r1;
    L_0x00a5:
        if (r11 >= r14) goto L_0x01be;
    L_0x00a7:
        r0 = r21;
        r1 = r0.a;
        r1 = r1.a;
        r1 = r1.get(r11);
        r1 = (com.xunlei.downloadprovider.xlui.widget.ZHTextView.a) r1;
        r2 = r21.getPaddingLeft();
        r5 = (float) r2;
        if (r11 != 0) goto L_0x00c1;
    L_0x00ba:
        r0 = r21;
        r2 = r0.a;
        r2 = r2.d;
        r5 = r5 + r2;
    L_0x00c1:
        if (r10 == 0) goto L_0x019d;
    L_0x00c3:
        r2 = r14 + -1;
        if (r11 != r2) goto L_0x019d;
    L_0x00c7:
        r2 = r21.getMeasuredWidth();	 Catch:{ Exception -> 0x01b8 }
        if (r2 == 0) goto L_0x019d;
    L_0x00cd:
        r0 = r21;
        r2 = r0.a;	 Catch:{ Exception -> 0x01b8 }
        r2 = r2.a;	 Catch:{ Exception -> 0x01b8 }
        r2 = r2.size();	 Catch:{ Exception -> 0x01b8 }
        if (r2 <= r14) goto L_0x019d;
    L_0x00d9:
        r0 = r21;
        r2 = r0.a;	 Catch:{ Exception -> 0x01b8 }
        r2 = r2.b;	 Catch:{ Exception -> 0x01b8 }
        r3 = r21.getMeasuredWidth();	 Catch:{ Exception -> 0x01b8 }
        if (r3 > 0) goto L_0x0115;
    L_0x00e5:
        r1 = "";
    L_0x00e8:
        r2 = r9 - r12;
        r3 = r11 * r8;
        r2 = r2 + r3;
        r3 = -r12;
        r3 = r3 + r13;
        r3 = r3 * r11;
        r2 = r2 + r3;
        r2 = (float) r2;	 Catch:{ Exception -> 0x01b8 }
        r0 = r22;
        r0.drawText(r1, r5, r2, r7);	 Catch:{ Exception -> 0x01b8 }
    L_0x00f7:
        r1 = r11 + 1;
        r11 = r1;
        goto L_0x00a5;
    L_0x00fb:
        r2 = r2 & 80;
        if (r2 == 0) goto L_0x010c;
    L_0x00ff:
        r2 = r21.getMeasuredHeight();
        r1 = r2 - r1;
        r2 = r21.getPaddingBottom();
        r1 = r1 - r2;
        r9 = r1;
        goto L_0x0093;
    L_0x010c:
        r1 = r21.getPaddingTop();
        r9 = r1;
        goto L_0x0093;
    L_0x0112:
        r1 = 0;
        r10 = r1;
        goto L_0x009b;
    L_0x0115:
        r15 = "";
        r16 = r21.getPaint();	 Catch:{ Exception -> 0x01b8 }
        if (r2 != 0) goto L_0x0194;
    L_0x011e:
        r2 = "";
        r6 = r2;
    L_0x0122:
        r17 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01b8 }
        r17.<init>();	 Catch:{ Exception -> 0x01b8 }
        r2 = r1.a;	 Catch:{ Exception -> 0x01b8 }
        r3 = r1.b;	 Catch:{ Exception -> 0x01b8 }
        r2 = r6.substring(r2, r3);	 Catch:{ Exception -> 0x01b8 }
        r0 = r17;
        r0.append(r2);	 Catch:{ Exception -> 0x01b8 }
        r2 = "....";
        r0 = r17;
        r2 = r0.append(r2);	 Catch:{ Exception -> 0x01b8 }
        r2.append(r15);	 Catch:{ Exception -> 0x01b8 }
        r3 = r17.toString();	 Catch:{ Exception -> 0x01b8 }
        r2 = r1.b;	 Catch:{ Exception -> 0x01b8 }
        r20 = r2;
        r2 = r3;
        r3 = r20;
    L_0x014b:
        r0 = r16;
        r4 = r0.measureText(r2);	 Catch:{ Exception -> 0x01b8 }
        r18 = r21.getMeasuredWidth();	 Catch:{ Exception -> 0x01b8 }
        r19 = r21.getPaddingLeft();	 Catch:{ Exception -> 0x01b8 }
        r18 = r18 - r19;
        r19 = r21.getPaddingRight();	 Catch:{ Exception -> 0x01b8 }
        r18 = r18 - r19;
        r0 = r18;
        r0 = (float) r0;	 Catch:{ Exception -> 0x01b8 }
        r18 = r0;
        r4 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1));
        if (r4 <= 0) goto L_0x019a;
    L_0x016a:
        r2 = 0;
        r4 = r17.length();	 Catch:{ Exception -> 0x01b8 }
        r0 = r17;
        r0.delete(r2, r4);	 Catch:{ Exception -> 0x01b8 }
        r2 = r1.a;	 Catch:{ Exception -> 0x01b8 }
        r2 = r6.substring(r2, r3);	 Catch:{ Exception -> 0x01b8 }
        r0 = r17;
        r0.append(r2);	 Catch:{ Exception -> 0x01b8 }
        r2 = "....";
        r0 = r17;
        r2 = r0.append(r2);	 Catch:{ Exception -> 0x01b8 }
        r2.append(r15);	 Catch:{ Exception -> 0x01b8 }
        r4 = r17.toString();	 Catch:{ Exception -> 0x01b8 }
        r2 = r3 + -1;
        r3 = r2;
        r2 = r4;
        goto L_0x014b;
    L_0x0194:
        r2 = java.lang.String.valueOf(r2);	 Catch:{ Exception -> 0x01b8 }
        r6 = r2;
        goto L_0x0122;
    L_0x019a:
        r1 = r2;
        goto L_0x00e8;
    L_0x019d:
        r0 = r21;
        r2 = r0.a;	 Catch:{ Exception -> 0x01b8 }
        r2 = r2.b;	 Catch:{ Exception -> 0x01b8 }
        r3 = r1.a;	 Catch:{ Exception -> 0x01b8 }
        r4 = r1.b;	 Catch:{ Exception -> 0x01b8 }
        r1 = r9 - r12;
        r6 = r11 * r8;
        r1 = r1 + r6;
        r6 = -r12;
        r6 = r6 + r13;
        r6 = r6 * r11;
        r1 = r1 + r6;
        r6 = (float) r1;	 Catch:{ Exception -> 0x01b8 }
        r1 = r22;
        r1.drawText(r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x01b8 }
        goto L_0x00f7;
    L_0x01b8:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00f7;
    L_0x01be:
        r22.restore();
    L_0x01c1:
        return;
    L_0x01c2:
        super.onDraw(r22);
        goto L_0x01c1;
    L_0x01c6:
        r8 = r1;
        r1 = r2;
        goto L_0x0059;
        */
    }

    protected void onMeasure(int i, int i2) {
        if (this.c) {
            int i3;
            CharSequence charSequence = this.b;
            Paint paint = getPaint();
            Object valueOf = charSequence == null ? BuildConfig.VERSION_NAME : String.valueOf(charSequence);
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            if (TextUtils.isEmpty(valueOf)) {
                i3 = 0;
            } else {
                i3 = (int) (((paint.measureText(valueOf) + getTextIndentPadding()) + ((float) getPaddingLeft())) + ((float) getPaddingRight()));
            }
            if (mode == 1073741824) {
                i3 = size;
            } else if (mode == Integer.MIN_VALUE) {
                i3 = Math.min(i3, size);
            }
            a(charSequence, (i3 - getPaddingLeft()) - getPaddingRight());
            mode = this.a.a.size();
            Paint paint2 = getPaint();
            int mode2 = MeasureSpec.getMode(i2);
            size = MeasureSpec.getSize(i2);
            float ascent = paint2.ascent();
            float descent = paint2.descent();
            int i4 = 1;
            float f = 0.0f;
            if (VERSION.SDK_INT >= 16) {
                i4 = getMaxLines();
                f = getLineSpacingExtra();
            }
            if (mode2 != 1073741824) {
                ascent = (-ascent) + descent;
                int paddingTop = (int) ((((float) getPaddingTop()) + ascent) + ((float) getPaddingBottom()));
                i4 = Math.max(0, Math.min(mode, i4));
                int paddingTop2 = (int) ((((f * ((float) (i4 - 1))) + (((float) i4) * ascent)) + ((float) getPaddingTop())) + ((float) getPaddingBottom()));
                if (size == 0 || mode2 == Integer.MIN_VALUE) {
                    size = Math.max(paddingTop2, paddingTop);
                } else {
                    size = Math.min(paddingTop2, size);
                }
            }
            setMeasuredDimension(i3, size);
            return;
        }
        super.onMeasure(i, i2);
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        this.b = getText();
        if (VERSION.SDK_INT >= 18 && !isInLayout()) {
            requestLayout();
        }
        invalidate();
    }

    private void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ZHTextView, i, 0);
        this.d = obtainStyledAttributes.getDimension(0, this.d);
        obtainStyledAttributes.recycle();
        this.b = getText();
        this.a = new b();
        this.a.b = getText();
        this.a.d = this.d;
        if (this.d > 0.0f && VERSION.SDK_INT >= 18 && !isInLayout()) {
            requestLayout();
        }
        invalidate();
    }

    private void a(CharSequence charSequence, int i) {
        if (i > 0 && charSequence != null) {
            b bVar = this.a;
            bVar.a.clear();
            bVar.b = null;
            this.a.b = charSequence;
            this.a.c = i;
            this.a.d = getTextIndentPadding();
            Paint paint = getPaint();
            int length = charSequence.length();
            int i2 = (int) (((float) i) - this.a.d);
            int i3 = i2 <= 0 ? i : i2;
            int i4 = 0;
            int i5 = 0;
            while (i5 < length) {
                if (i4 == 0) {
                    i2 = i3;
                } else {
                    i2 = i;
                }
                i2 = paint.breakText(charSequence, i5, length, true, (float) i2, null);
                if (i2 > 0) {
                    a aVar = new a();
                    aVar.a = i5;
                    aVar.b = i5 + i2;
                    this.a.a.add(aVar);
                    i5 += i2;
                    i4++;
                } else {
                    return;
                }
            }
        }
    }
}
