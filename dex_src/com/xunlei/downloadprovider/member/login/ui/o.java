package com.xunlei.downloadprovider.member.login.ui;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.xunlei.downloadprovider.personal.settings.HelpActivity;

// compiled from: LoginActivity.java
final class o extends ClickableSpan {
    final /* synthetic */ LoginActivity a;

    o(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public final void onClick(View view) {
        HelpActivity.a(this.a, "file:///android_asset/reg_protocol/protocol.html", this.a.getString(2131232167));
    }

    public final void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(false);
        textPaint.setColor(-15559434);
    }
}
