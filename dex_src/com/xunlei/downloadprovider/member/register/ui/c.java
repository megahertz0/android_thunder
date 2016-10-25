package com.xunlei.downloadprovider.member.register.ui;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.xunlei.downloadprovider.personal.settings.HelpActivity;

// compiled from: MobileSetupActivity.java
final class c extends ClickableSpan {
    final /* synthetic */ MobileSetupActivity a;

    c(MobileSetupActivity mobileSetupActivity) {
        this.a = mobileSetupActivity;
    }

    public final void onClick(View view) {
        HelpActivity.a(this.a, "file:///android_asset/reg_protocol/protocol.html", this.a.getString(2131232167));
    }

    public final void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
        textPaint.setColor(-15559434);
    }
}
