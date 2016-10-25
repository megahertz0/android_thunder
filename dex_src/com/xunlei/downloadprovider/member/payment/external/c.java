package com.xunlei.downloadprovider.member.payment.external;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

// compiled from: NoLineClickableSpan.java
public class c extends ClickableSpan {
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(textPaint.linkColor);
        textPaint.setUnderlineText(false);
    }

    public void onClick(View view) {
    }
}
