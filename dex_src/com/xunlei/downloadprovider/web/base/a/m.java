package com.xunlei.downloadprovider.web.base.a;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.xunlei.downloadprovider.c.a.n;
import org.android.spdy.SpdyProtocol;

// compiled from: CommentItemViewHolder.java
final class m extends ClickableSpan {
    final /* synthetic */ n a;
    final /* synthetic */ j b;

    m(j jVar, n nVar) {
        this.b = jVar;
        this.a = nVar;
    }

    public final void onClick(View view) {
        if (j.e(this.b) != null) {
            j.e(this.b).a(view, SpdyProtocol.CUSTOM, this.a);
        }
    }

    public final void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(Color.parseColor("#555555"));
        textPaint.setUnderlineText(false);
    }
}
