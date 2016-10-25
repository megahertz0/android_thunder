package com.xunlei.downloadprovider.web;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.android.agoo.common.a;

public class EllipsizingTextView extends TextView {
    private final List<Object> a;
    private boolean b;
    private boolean c;
    private boolean d;
    private String e;
    private int f;
    private float g;
    private float h;

    public EllipsizingTextView(Context context) {
        super(context);
        this.a = new ArrayList();
        this.f = -1;
        this.g = 1.0f;
        this.h = 0.0f;
    }

    public EllipsizingTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new ArrayList();
        this.f = -1;
        this.g = 1.0f;
        this.h = 0.0f;
    }

    public EllipsizingTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new ArrayList();
        this.f = -1;
        this.g = 1.0f;
        this.h = 0.0f;
    }

    public void setMaxLines(int i) {
        super.setMaxLines(i);
        this.f = i;
        this.c = true;
    }

    public int getMaxLines() {
        return this.f;
    }

    public void setLineSpacing(float f, float f2) {
        this.h = f;
        this.g = f2;
        super.setLineSpacing(f, f2);
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (!this.d) {
            this.e = charSequence.toString();
            this.c = true;
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.c) {
            String str;
            boolean z;
            Iterator it;
            super.setEllipsize(null);
            int maxLines = getMaxLines();
            String str2 = this.e;
            if (maxLines != -1) {
                Layout a = a(str2);
                if (a.getLineCount() > maxLines) {
                    str2 = this.e.substring(0, a.getLineEnd(maxLines - 1) - 1).trim();
                    a = a(str2 + "...");
                    while (a.getLineCount() > maxLines) {
                        int lastIndexOf = str2.lastIndexOf(a.ORDERED);
                        if (lastIndexOf == -1) {
                            break;
                        }
                        str2 = str2.substring(0, lastIndexOf);
                    }
                    str = str2 + "...";
                    z = true;
                    if (!r3.equals(getText())) {
                        this.d = true;
                        setText(r3);
                        this.d = false;
                    }
                    this.c = false;
                    if (z != this.b) {
                        this.b = z;
                        it = this.a.iterator();
                        while (it.hasNext()) {
                            it.next();
                        }
                    }
                }
            }
            str = str2;
            z = false;
            if (r3.equals(getText())) {
                this.d = true;
                setText(r3);
                this.d = false;
            }
            this.c = false;
            if (z != this.b) {
                this.b = z;
                it = this.a.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            }
        }
        super.onDraw(canvas);
    }

    private Layout a(String str) {
        return new StaticLayout(str, getPaint(), (getWidth() - getPaddingLeft()) - getPaddingRight(), Alignment.ALIGN_NORMAL, this.g, this.h, false);
    }

    public void setEllipsize(TruncateAt truncateAt) {
    }
}
