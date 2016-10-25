package com.xunlei.downloadprovider.download.taskDetail.widget;

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

public class ZHTextViewExpandable extends TextView {
    private c a;
    private CharSequence b;
    private int c;
    private a d;
    private boolean e;
    private float f;

    static interface a {
        void a(int i);
    }

    private static class b {
        public int a;
        public int b;

        private b() {
        }
    }

    private static class c {
        public ArrayList<b> a;
        public CharSequence b;
        public int c;
        public float d;

        private c() {
            this.a = new ArrayList();
            this.b = null;
        }
    }

    public int getMaxLine() {
        return this.c;
    }

    public void setMaxLine(int i) {
        this.c = i;
        invalidate();
        requestLayout();
    }

    public a getListener() {
        return this.d;
    }

    public void setListener(a aVar) {
        this.d = aVar;
    }

    public ZHTextViewExpandable(Context context) {
        super(context);
        this.c = 2;
        this.e = true;
        this.f = 0.0f;
        a();
    }

    public ZHTextViewExpandable(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 2;
        this.e = true;
        this.f = 0.0f;
        a();
    }

    public ZHTextViewExpandable(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 2;
        this.e = true;
        this.f = 0.0f;
        a();
    }

    public float getTextIndentPadding() {
        return this.f;
    }

    public void setTextIndentPadding(float f) {
        this.f = f;
        if (VERSION.SDK_INT < 18 || !isInLayout()) {
            requestLayout();
        }
        invalidate();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onDraw(android.graphics.Canvas r22) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.download.taskDetail.widget.ZHTextViewExpandable.onDraw(android.graphics.Canvas):void");
        /*
        this = this;
        r0 = r21;
        r1 = r0.e;
        if (r1 == 0) goto L_0x0189;
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
        r11 = (int) r1;
        r1 = r7.descent();
        r12 = (int) r1;
        r2 = r21.getMaxLine();
        r1 = 0;
        r3 = android.os.Build.VERSION.SDK_INT;
        r4 = 16;
        if (r3 < r4) goto L_0x018d;
    L_0x0051:
        r1 = r21.getLineSpacingExtra();
        r1 = (int) r1;
        r8 = r1;
    L_0x0057:
        r1 = 0;
        r0 = r21;
        r3 = r0.a;
        r3 = r3.a;
        r3 = r3.size();
        r2 = java.lang.Math.min(r2, r3);
        r13 = java.lang.Math.max(r1, r2);
        r21.getGravity();
        r14 = r21.getPaddingTop();
        r1 = r21.getEllipsize();
        if (r1 == 0) goto L_0x00d9;
    L_0x0077:
        r1 = 1;
        r9 = r1;
    L_0x0079:
        r0 = r21;
        r1 = r0.a;
        r1 = r1.b;
        if (r1 == 0) goto L_0x0185;
    L_0x0081:
        r1 = 0;
        r10 = r1;
    L_0x0083:
        if (r10 >= r13) goto L_0x0185;
    L_0x0085:
        r0 = r21;
        r1 = r0.a;
        r1 = r1.a;
        r1 = r1.get(r10);
        r1 = (com.xunlei.downloadprovider.download.taskDetail.widget.ZHTextViewExpandable.b) r1;
        r2 = r21.getPaddingLeft();
        r5 = (float) r2;
        if (r10 != 0) goto L_0x009f;
    L_0x0098:
        r0 = r21;
        r2 = r0.a;
        r2 = r2.d;
        r5 = r5 + r2;
    L_0x009f:
        if (r9 == 0) goto L_0x0164;
    L_0x00a1:
        r2 = r13 + -1;
        if (r10 != r2) goto L_0x0164;
    L_0x00a5:
        r2 = r21.getMeasuredWidth();	 Catch:{ Exception -> 0x017f }
        if (r2 == 0) goto L_0x0164;
    L_0x00ab:
        r0 = r21;
        r2 = r0.a;	 Catch:{ Exception -> 0x017f }
        r2 = r2.a;	 Catch:{ Exception -> 0x017f }
        r2 = r2.size();	 Catch:{ Exception -> 0x017f }
        if (r2 <= r13) goto L_0x0164;
    L_0x00b7:
        r0 = r21;
        r2 = r0.a;	 Catch:{ Exception -> 0x017f }
        r2 = r2.b;	 Catch:{ Exception -> 0x017f }
        r3 = r21.getMeasuredWidth();	 Catch:{ Exception -> 0x017f }
        if (r3 > 0) goto L_0x00dc;
    L_0x00c3:
        r1 = "";
    L_0x00c6:
        r2 = r14 - r11;
        r3 = r10 * r8;
        r2 = r2 + r3;
        r3 = -r11;
        r3 = r3 + r12;
        r3 = r3 * r10;
        r2 = r2 + r3;
        r2 = (float) r2;	 Catch:{ Exception -> 0x017f }
        r0 = r22;
        r0.drawText(r1, r5, r2, r7);	 Catch:{ Exception -> 0x017f }
    L_0x00d5:
        r1 = r10 + 1;
        r10 = r1;
        goto L_0x0083;
    L_0x00d9:
        r1 = 0;
        r9 = r1;
        goto L_0x0079;
    L_0x00dc:
        r15 = "";
        r16 = r21.getPaint();	 Catch:{ Exception -> 0x017f }
        if (r2 != 0) goto L_0x015b;
    L_0x00e5:
        r2 = "";
        r6 = r2;
    L_0x00e9:
        r17 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x017f }
        r17.<init>();	 Catch:{ Exception -> 0x017f }
        r2 = r1.a;	 Catch:{ Exception -> 0x017f }
        r3 = r1.b;	 Catch:{ Exception -> 0x017f }
        r2 = r6.substring(r2, r3);	 Catch:{ Exception -> 0x017f }
        r0 = r17;
        r0.append(r2);	 Catch:{ Exception -> 0x017f }
        r2 = "....";
        r0 = r17;
        r2 = r0.append(r2);	 Catch:{ Exception -> 0x017f }
        r2.append(r15);	 Catch:{ Exception -> 0x017f }
        r3 = r17.toString();	 Catch:{ Exception -> 0x017f }
        r2 = r1.b;	 Catch:{ Exception -> 0x017f }
        r20 = r2;
        r2 = r3;
        r3 = r20;
    L_0x0112:
        r0 = r16;
        r4 = r0.measureText(r2);	 Catch:{ Exception -> 0x017f }
        r18 = r21.getMeasuredWidth();	 Catch:{ Exception -> 0x017f }
        r19 = r21.getPaddingLeft();	 Catch:{ Exception -> 0x017f }
        r18 = r18 - r19;
        r19 = r21.getPaddingRight();	 Catch:{ Exception -> 0x017f }
        r18 = r18 - r19;
        r0 = r18;
        r0 = (float) r0;	 Catch:{ Exception -> 0x017f }
        r18 = r0;
        r4 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1));
        if (r4 <= 0) goto L_0x0161;
    L_0x0131:
        r2 = 0;
        r4 = r17.length();	 Catch:{ Exception -> 0x017f }
        r0 = r17;
        r0.delete(r2, r4);	 Catch:{ Exception -> 0x017f }
        r2 = r1.a;	 Catch:{ Exception -> 0x017f }
        r2 = r6.substring(r2, r3);	 Catch:{ Exception -> 0x017f }
        r0 = r17;
        r0.append(r2);	 Catch:{ Exception -> 0x017f }
        r2 = "....";
        r0 = r17;
        r2 = r0.append(r2);	 Catch:{ Exception -> 0x017f }
        r2.append(r15);	 Catch:{ Exception -> 0x017f }
        r4 = r17.toString();	 Catch:{ Exception -> 0x017f }
        r2 = r3 + -1;
        r3 = r2;
        r2 = r4;
        goto L_0x0112;
    L_0x015b:
        r2 = java.lang.String.valueOf(r2);	 Catch:{ Exception -> 0x017f }
        r6 = r2;
        goto L_0x00e9;
    L_0x0161:
        r1 = r2;
        goto L_0x00c6;
    L_0x0164:
        r0 = r21;
        r2 = r0.a;	 Catch:{ Exception -> 0x017f }
        r2 = r2.b;	 Catch:{ Exception -> 0x017f }
        r3 = r1.a;	 Catch:{ Exception -> 0x017f }
        r4 = r1.b;	 Catch:{ Exception -> 0x017f }
        r1 = r14 - r11;
        r6 = r10 * r8;
        r1 = r1 + r6;
        r6 = -r11;
        r6 = r6 + r12;
        r6 = r6 * r10;
        r1 = r1 + r6;
        r6 = (float) r1;	 Catch:{ Exception -> 0x017f }
        r1 = r22;
        r1.drawText(r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x017f }
        goto L_0x00d5;
    L_0x017f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00d5;
    L_0x0185:
        r22.restore();
    L_0x0188:
        return;
    L_0x0189:
        super.onDraw(r22);
        goto L_0x0188;
    L_0x018d:
        r8 = r1;
        goto L_0x0057;
        */
    }

    protected void onMeasure(int i, int i2) {
        if (this.e) {
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
            int size2 = this.a.a.size();
            Paint paint2 = getPaint();
            mode = MeasureSpec.getMode(i2);
            size = MeasureSpec.getSize(i2);
            float ascent = paint2.ascent();
            float descent = paint2.descent();
            int maxLine = getMaxLine();
            float f = 0.0f;
            if (VERSION.SDK_INT >= 16) {
                f = getLineSpacingExtra();
            }
            if (mode != 1073741824) {
                ascent = (-ascent) + descent;
                int paddingTop = (int) ((((float) getPaddingTop()) + ascent) + ((float) getPaddingBottom()));
                int max = Math.max(0, Math.min(size2, maxLine));
                int paddingTop2 = (int) ((((f * ((float) (max - 1))) + (((float) max) * ascent)) + ((float) getPaddingTop())) + ((float) getPaddingBottom()));
                if (size == 0 || mode == Integer.MIN_VALUE) {
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

    private void a() {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, R.styleable.ZHTextView, 0, 0);
        this.f = obtainStyledAttributes.getDimension(0, this.f);
        obtainStyledAttributes.recycle();
        this.b = getText();
        this.a = new c();
        this.a.b = getText();
        this.a.d = this.f;
        if (this.f > 0.0f && VERSION.SDK_INT >= 18 && !isInLayout()) {
            requestLayout();
        }
        invalidate();
    }

    public int getLineCount() {
        return this.a != null ? this.a.a.size() : 0;
    }

    private void a(CharSequence charSequence, int i) {
        if (i > 0 && charSequence != null) {
            c cVar = this.a;
            cVar.a.clear();
            cVar.b = null;
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
                if (i2 <= 0) {
                    break;
                }
                b bVar = new b();
                bVar.a = i5;
                bVar.b = i5 + i2;
                this.a.a.add(bVar);
                i5 += i2;
                i4++;
            }
            new StringBuilder(" lineCount----: ").append(this.a.a.size());
            if (this.d != null) {
                this.d.a(this.a.a.size());
            }
        }
    }
}
