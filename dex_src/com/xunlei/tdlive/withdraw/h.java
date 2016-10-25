package com.xunlei.tdlive.withdraw;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

// compiled from: VerifyCodeCheckPage.java
class h implements TextWatcher {
    final /* synthetic */ f a;

    h(f fVar) {
        this.a = fVar;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        TextView c = f.c(this.a);
        boolean z = editable.length() > 0 && f.d(this.a).getText().length() > 0;
        c.setEnabled(z);
    }
}
